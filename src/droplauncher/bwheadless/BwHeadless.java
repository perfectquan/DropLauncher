package droplauncher.bwheadless;

import droplauncher.starcraft.Race;
import droplauncher.util.Constants;
import java.io.File;
import java.util.logging.Logger;

/**
 * Class for handling communication with bwheadless.exe and
 * starting the bot client if present.
 */
public class BwHeadless {

  private static final Logger LOGGER = Logger.getLogger(BwHeadless.class.getName());
  private static final boolean CLASS_DEBUG = (Constants.DEBUG && true);

  public static final File BW_HEADLESS_EXE = new File("bwheadless.exe");

  public static final String DEFAULT_BOT_NAME = "BOT";

//  private ProcessPipe bwHeadlessPipe; /* required */
//  private ProcessPipe botClientPipe;  /* required only when DLL is absent */
  private File        starcraftExe;   /* required */
  private File        bwapiDll;       /* required */
  private String      botName;        /* required */
  private File        botDll;         /* required only when client is absent */
  private File        botClient;      /* *.exe or *.jar, required only when DLL is absent  */
  private Race        botRace;        /* required */
  private GameType    gameType;       /* required */

}
