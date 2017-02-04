package droplauncher.bwheadless;

import adakite.util.AdakiteUtils;
import droplauncher.bwapi.BWAPI;
import droplauncher.starcraft.Race;
import droplauncher.starcraft.Starcraft;
import droplauncher.util.Constants;
import droplauncher.util.ProcessPipe;
import droplauncher.util.SettingsKey;
import droplauncher.util.Util;
import droplauncher.util.windows.Windows;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Class for handling execution and communication with the
 * bwheadless.exe process.
 */
public class BWHeadless {

  private static final Logger LOGGER = Logger.getLogger(BWHeadless.class.getName());
  private static final boolean CLASS_DEBUG = (Constants.DEBUG && true);
  private static final boolean SET_DEBUG = true;

  public static final String BWHEADLESS_EXE = "bwheadless.exe";
  public static final String BWHEADLESS_INI_SECTION = "bwheadless";

  public static final String DEFAULT_BOT_NAME = "BOT";
  public static final Race DEFAULT_BOT_RACE = Race.RANDOM;
  public static final NetworkProvider DEFAULT_NETWORK_PROVIDER = NetworkProvider.LAN;
  public static final ConnectMode DEFAULT_CONNECT_MODE = ConnectMode.JOIN;

  private ProcessPipe bwheadlessPipe;
  private ProcessPipe botPipe;

  private BotModule botModule;
  private Settings settings;

  private ArrayList<Path> extraBotFiles;

  private IniFile iniFile;

  public BWHeadless() {
    this.bwheadlessPipe = new ProcessPipe();
    this.botPipe = new ProcessPipe();

    this.botModule = new BotModule();

    this.settings = new Settings();
    this.settings.set(SettingsKey.BOT_NAME.toString(), DEFAULT_BOT_NAME);
    this.settings.set(SettingsKey.BOT_RACE.toString(), DEFAULT_BOT_RACE.toString());
    this.settings.set(SettingsKey.NETWORK_PROVIDER.toString(), DEFAULT_NETWORK_PROVIDER.toString());
    this.settings.set(SettingsKey.CONNECT_MODE.toString(), DEFAULT_CONNECT_MODE.toString());

    this.extraBotFiles = new ArrayList<>();

    this.iniFile = null;
  }

  public Settings getSettings() {
    return this.settings;
  }

  public void mergeSettings(Settings settings) {
    settings.merge(this.settings);
    this.settings = settings;
  }

  public ArrayList<Path> getExtraBotFiles() {
    return this.extraBotFiles;
  }

  /**
   * Tests if the program has sufficient information to run bwheadless.
   *
   * @see #getReadyStatus()
   * @return
   *     true if ready,
   *     otherwise false
   */
  public boolean isReady() {
    return (getReadyStatus() == ReadyStatus.READY);
  }

  /**
   * Returns a response depending on whether the program is ready
   * to run bwheadless.
   *
   * @return
   *     {@link ReadyStatus#READY} if ready,
   *     otherwise the corresponding value that is preventing status
   *     from being ready. E.g. {@link ReadyStatus#STARTCRAFT_EXE}
   */
  public ReadyStatus getReadyStatus() {
    if (!AdakiteUtils.fileExists(Paths.get(BWHEADLESS_EXE))) {
      return ReadyStatus.BWHEADLESS_EXE;
    } else if (!this.settings.hasValue(SettingsKey.STARCRAFT_EXE.toString())
        || !AdakiteUtils.fileExists(Paths.get(this.settings.getValue(SettingsKey.STARCRAFT_EXE.toString())))) {
      return ReadyStatus.STARTCRAFT_EXE;
    } else if (!this.settings.hasValue(SettingsKey.BWAPI_DLL.toString())
        || !AdakiteUtils.fileExists(Paths.get(this.settings.getValue(SettingsKey.BWAPI_DLL.toString())))) {
      return ReadyStatus.BWAPI_DLL;
    } else if (!this.settings.hasValue(SettingsKey.BOT_NAME.toString())) {
      return ReadyStatus.BOT_NAME;
    } else if (this.botModule.getType() == BotModule.Type.UNKNOWN
        || !AdakiteUtils.fileExists(this.botModule.getPath())) {
      /* If both the bot DLL and bot client fields are missing. */
      return ReadyStatus.BOT_FILE;
    } else if (!this.settings.hasValue(SettingsKey.BOT_RACE.toString())) {
      return ReadyStatus.BOT_RACE;
    } else if (!this.settings.hasValue(SettingsKey.NETWORK_PROVIDER.toString())) {
      return ReadyStatus.NETWORK_PROVIDER;
    } else if (!this.settings.hasValue(SettingsKey.CONNECT_MODE.toString())) {
      return ReadyStatus.CONNECT_MODE;
    } else {
      return ReadyStatus.READY;
    }
  }

  /**
   * Tests is the pipe is open between this program and bwheadless.
   *
   * @return
   *     true if the pipe is open,
   *     otherwise false
   */
  public boolean isRunning() {
    return this.bwheadlessPipe.isOpen();
  }

  /**
   * Attempts to start bwheadless after configuring and checking settings.
   */
  public void start() {
    if (isRunning() || !isReady()) {
      return;
    }

    try {
      configureBwapi();
    } catch (IOException ex) {
      LOGGER.log(Constants.DEFAULT_LOG_LEVEL, null, ex);
    }

    String starcraftDirectory = AdakiteUtils.getParentDirectory(Paths.get(this.settings.getValue(SettingsKey.STARCRAFT_EXE.toString()))).toAbsolutePath().toString();

    /* Compile bwheadless arguments. */
    ArrayList<String> bwargs = new ArrayList<>(); /* bwheadless arguments */
    bwargs.add(Argument.STARCRAFT_EXE.toString());
    bwargs.add(this.settings.getValue(SettingsKey.STARCRAFT_EXE.toString()));
    bwargs.add(Argument.JOIN_GAME.toString());
    bwargs.add(Argument.BOT_NAME.toString());
    bwargs.add(this.settings.getValue(SettingsKey.BOT_NAME.toString()));
    bwargs.add(Argument.BOT_RACE.toString());
    bwargs.add(this.settings.getValue(SettingsKey.BOT_RACE.toString()));
    bwargs.add(Argument.LOAD_DLL.toString());
    bwargs.add(this.settings.getValue(SettingsKey.BWAPI_DLL.toString()));
    bwargs.add(Argument.ENABLE_LAN.toString());
    bwargs.add(Argument.STARCRAFT_INSTALL_PATH.toString());
    bwargs.add(starcraftDirectory);
    String[] bargsArray = Util.toStringArray(bwargs);

    /* Start bwheadless. */
    this.bwheadlessPipe.open(Paths.get(BWHEADLESS_EXE), bargsArray, starcraftDirectory);

    //TODO: Pipe client output to a UI component.
    /* Start bot client in a command prompt. */
    if (this.botModule.getType() == BotModule.Type.CLIENT) {
      if (AdakiteUtils.getFileExtension(this.botModule.getPath()).equalsIgnoreCase("jar")) {
        ArrayList<String> clargs = new ArrayList<>();
        for (String arg : Windows.DEFAULT_JAR_ARGS) {
          clargs.add(arg);
        }
        clargs.add(this.botModule.getPath().toAbsolutePath().toString());
        String[] cargsArray = Util.toStringArray(clargs);
        this.botPipe.open(Paths.get(this.settings.getValue(SettingsKey.JAVA_EXE.toString())), cargsArray, starcraftDirectory);
      } else if (AdakiteUtils.getFileExtension(this.botModule.getPath()).equalsIgnoreCase("exe")) {
        ArrayList<String> clargs = new ArrayList<>();
        clargs.add(this.botModule.toString());
        String[] cargsArray = Util.toStringArray(clargs);
        this.botPipe.open(Windows.CMD_EXE, cargsArray, starcraftDirectory);
      }
    }
  }

  public void stop() {
    this.bwheadlessPipe.close();
  }

  /**
   * Configures BWAPI settings and related files.
   *
   * @throws IOException
   */
  private void configureBwapi() throws IOException {
    /* Determine StarCraft directory from StarCraft.exe path. */
    String starcraftDirectory = AdakiteUtils.getParentDirectory(Paths.get(this.settings.getValue(SettingsKey.STARCRAFT_EXE.toString()))).toAbsolutePath().toString();

    /* Configure BWAPI INI file. */
    IniFile bwapiIni = new IniFile();
    bwapiIni.open(Paths.get(starcraftDirectory, BWAPI.BWAPI_DATA_INI));
    if (this.botModule.getType() == BotModule.Type.DLL) {
      bwapiIni.setVariable("ai",
          "ai",
          BWAPI.BWAPI_DATA_AI_DIR + File.separator + this.botModule.getPath().getFileName().toString()
      );
    } else {
      bwapiIni.disableVariable("ai", "ai");
    }
    bwapiIni.disableVariable("ai", "ai_dbg");

    /* Prepare to copy bot files to StarCraft directory. */
    Path src = null;
    Path dest = null;
    if (this.botModule.getType() == BotModule.Type.DLL) {
      /* Prepare to copy DLL to bwapi-data directory. */
      src = this.botModule.getPath();
      dest = Paths.get(starcraftDirectory,
          BWAPI.BWAPI_DATA_AI_DIR,
          Paths.get(this.botModule.toString()).getFileName().toString()
      );
      this.botModule.setPath(dest);
    } else if (this.botModule.getType() == BotModule.Type.CLIENT) {
      /* Prepare to copy client to StarCraft root directory. */
      src = this.botModule.getPath();
      dest = Paths.get(starcraftDirectory,
          this.botModule.getPath().getFileName().toString()
      );
      this.botModule.setPath(dest);
    }
    /* Copy. */
    AdakiteUtils.createDirectory(dest.getParent());
    Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);

    /* Copy misc files to common paths. */
    Path readPath = Paths.get(starcraftDirectory, BWAPI.BWAPI_DATA_DIR_READ);
    Path writePath = Paths.get(starcraftDirectory, BWAPI.BWAPI_DATA_DIR_WRITE);
    Path aiPath = Paths.get(starcraftDirectory, BWAPI.BWAPI_DATA_AI_DIR);
    AdakiteUtils.createDirectory(readPath);
    AdakiteUtils.createDirectory(writePath);
    AdakiteUtils.createDirectory(aiPath);
    for (Path path : this.extraBotFiles) {
      Files.copy(path, Paths.get(readPath.toAbsolutePath().toString(), path.getFileName().toString()), StandardCopyOption.REPLACE_EXISTING);
      Files.copy(path, Paths.get(writePath.toAbsolutePath().toString(), path.getFileName().toString()), StandardCopyOption.REPLACE_EXISTING);
      Files.copy(path, Paths.get(aiPath.toAbsolutePath().toString(), path.getFileName().toString()), StandardCopyOption.REPLACE_EXISTING);
    }
  }

  public IniFile getIniFile() {
    return this.iniFile;
  }

  public void setIniFile(IniFile iniFile) {
    this.iniFile = iniFile;
  }

  public void setStarcraftExe(String starcraftExe) {
    this.settings.set(SettingsKey.STARCRAFT_EXE.toString(), starcraftExe);
    updateSettingsFile(SettingsKey.STARCRAFT_EXE.toString(), this.settings.getValue(SettingsKey.STARCRAFT_EXE.toString()));

    if (SET_DEBUG) {
      System.out.println("setStarcraftExe = " + this.settings.getValue(SettingsKey.STARCRAFT_EXE.toString()));
    }
  }

  public void setBwapiDll(String bwapiDll) {
    this.settings.set(SettingsKey.BWAPI_DLL.toString(), bwapiDll);
    updateSettingsFile(SettingsKey.BWAPI_DLL.toString(), this.settings.getValue(SettingsKey.BWAPI_DLL.toString()));

    if (SET_DEBUG) {
      System.out.println("setBwapiDll = " + this.settings.getValue(SettingsKey.BWAPI_DLL.toString()));
    }
  }

  public void setBotName(String botName) {
    String cleaned = Starcraft.cleanProfileName(botName);
    if (cleaned.equals(this.settings.getValue(SettingsKey.BOT_NAME.toString()))) {
      return;
    }

    if (AdakiteUtils.isNullOrEmpty(cleaned)) {
      this.settings.set(SettingsKey.BOT_NAME.toString(), DEFAULT_BOT_NAME);
    } else {
      this.settings.set(SettingsKey.BOT_NAME.toString(), cleaned);
    }
    updateSettingsFile(SettingsKey.BOT_NAME.toString(), this.settings.getValue(SettingsKey.BOT_NAME.toString()));

    if (SET_DEBUG) {
      System.out.println("setBotName = " + this.settings.getValue(SettingsKey.BOT_NAME.toString()));
    }
  }

  public BotModule getBotModule() {
    return this.botModule;
  }

  public void setBotModule(String botModule) {
    this.extraBotFiles.clear();
    this.botModule.setPath(botModule);
    updateSettingsFile(SettingsKey.BOT_MODULE.toString(), this.botModule.toString());

    if (SET_DEBUG) {
      System.out.println("setBotModule = " + botModule);
    }
  }

  public void setBotRace(Race botRace) {
    this.settings.set(SettingsKey.BOT_RACE.toString(), botRace.toString());
    updateSettingsFile(SettingsKey.BOT_RACE.toString(), this.settings.getValue(SettingsKey.BOT_RACE.toString()));

    if (SET_DEBUG) {
      System.out.println("setBotRace = " + this.settings.getValue(SettingsKey.BOT_RACE.toString()));
    }
  }

  public void setNetworkProvider(NetworkProvider networkProvider) {
    this.settings.set(SettingsKey.NETWORK_PROVIDER.toString(), networkProvider.toString());
    updateSettingsFile(SettingsKey.NETWORK_PROVIDER.toString(), this.settings.getValue(SettingsKey.NETWORK_PROVIDER.toString()));

    if (SET_DEBUG) {
      System.out.println("setNetworkProvider = " + this.settings.getValue(SettingsKey.NETWORK_PROVIDER.toString()));
    }
  }

  public void setConnectMode(ConnectMode connectMode) {
    this.settings.set(SettingsKey.CONNECT_MODE.toString(), connectMode.toString());
    updateSettingsFile(SettingsKey.CONNECT_MODE.toString(), this.settings.getValue(SettingsKey.CONNECT_MODE.toString()));

    if (SET_DEBUG) {
      System.out.println("setConnectMode = " + this.settings.getValue(SettingsKey.CONNECT_MODE.toString()));
    }
  }

  /**
   * Sets the specified variable and updates the class INI file.
   *
   * Catches and reports all exceptions thrown by
   * {@link droplauncher.ini.IniFile#setVariable(java.lang.String, java.lang.String, java.lang.String)}.
   *
   * @param name specified section name
   * @param key specified key
   * @param val specified value
   */
  private void updateSettingsFile(String name, String key, String val) {
    try {
      this.iniFile.setVariable(name, key, val);
    } catch (Exception ex) {
      if (CLASS_DEBUG) {
        LOGGER.log(Constants.DEFAULT_LOG_LEVEL, null, ex);
      }
    }
  }

  /**
   * Sets the specified variable and updates the class INI file. The default
   * section name is {@link #BWHEADLESS_INI_SECTION}.
   *
   * @param key specified key
   * @param val specified value
   *
   * @see #updateSettingsFile(java.lang.String, java.lang.String, java.lang.String)
   */
  private void updateSettingsFile(String key, String val) {
    updateSettingsFile(BWHEADLESS_INI_SECTION, key, val);
  }

  /**
   * Reads the specified IniFile and sets class member variables accordingly.
   *
   * @param iniFile specified IniFile object
   */
  public void readSettingsFile(IniFile iniFile) {
    String val;
    if (!AdakiteUtils.isNullOrEmpty(val = iniFile.getValue(BWHEADLESS_INI_SECTION, SettingsKey.STARCRAFT_EXE.toString()))) {
      setStarcraftExe(val);
    }
    if (!AdakiteUtils.isNullOrEmpty(val = iniFile.getValue(BWHEADLESS_INI_SECTION, SettingsKey.BWAPI_DLL.toString()))) {
      setBwapiDll(val);
    }
    if (!AdakiteUtils.isNullOrEmpty(val = iniFile.getValue(BWHEADLESS_INI_SECTION, SettingsKey.BOT_NAME.toString()))) {
      setBotName(val);
    } else {
      setBotName(DEFAULT_BOT_NAME);
    }
    if (!AdakiteUtils.isNullOrEmpty(val = iniFile.getValue(BWHEADLESS_INI_SECTION, SettingsKey.BOT_MODULE.toString()))) {
      setBotModule(val);
    }
    if (!AdakiteUtils.isNullOrEmpty(val = iniFile.getValue(BWHEADLESS_INI_SECTION, SettingsKey.BOT_RACE.toString()))) {
      if (val.equalsIgnoreCase(Race.TERRAN.toString())) {
        setBotRace(Race.TERRAN);
      } else if (val.equalsIgnoreCase(Race.ZERG.toString())) {
        setBotRace(Race.ZERG);
      } else if (val.equalsIgnoreCase(Race.PROTOSS.toString())) {
        setBotRace(Race.PROTOSS);
      } else if(val.equalsIgnoreCase((Race.RANDOM.toString()))) {
        setBotRace(Race.RANDOM);
      } else {
        /* Unrecognized Race. */
        setBotRace(DEFAULT_BOT_RACE);
      }
    } else {
      /* Race wasn't set. */
      setBotRace(DEFAULT_BOT_RACE);
    }
    if (AdakiteUtils.isNullOrEmpty(val = iniFile.getValue(BWHEADLESS_INI_SECTION, SettingsKey.NETWORK_PROVIDER.toString()))) {
      if (val.equalsIgnoreCase(NetworkProvider.LAN.toString())) {
        setNetworkProvider(NetworkProvider.LAN);
      } else {
        /* Unrecognized NetworkProvider. */
        setNetworkProvider(DEFAULT_NETWORK_PROVIDER);
      }
    } else {
      /* NetworkProvider wasn't set. */
      setNetworkProvider(DEFAULT_NETWORK_PROVIDER);
    }
    if (AdakiteUtils.isNullOrEmpty(val = iniFile.getValue(BWHEADLESS_INI_SECTION, SettingsKey.CONNECT_MODE.toString()))) {
      if (val.equalsIgnoreCase(ConnectMode.JOIN.toString())) {
        setConnectMode(ConnectMode.JOIN);
      } else {
        /* Unrecognized JoinMode. */
        setConnectMode(DEFAULT_CONNECT_MODE);
      }
    } else {
      /* JoinMode wasn't set. */
      setConnectMode(DEFAULT_CONNECT_MODE);
    }
  }

}
