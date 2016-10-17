/* MainWindow.java */

// CHECKSTYLE:OFF

package droplauncher;

import droplauncher.bwapi.Bwapi;
import droplauncher.config.ConfigFile;
import droplauncher.bwheadless.BwHeadless;
import droplauncher.bwheadless.GameTypes;
import droplauncher.bwheadless.PredefinedVariables;
import droplauncher.debugging.Debugging;
import droplauncher.filedroplist.FileDropList;
import droplauncher.filedroplist.FileDropListener;
import droplauncher.starcraft.Races;
import droplauncher.tools.MD5Checksum;
import droplauncher.tools.MainTools;
import droplauncher.tools.MemoryFile;

import filedrop.FileDrop;
import java.awt.Color;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class for main window.
 *
 * @author Adakite Systems
 * @author adakitesystems@gmail.com
 */
public class MainWindow extends JFrame {

  private static final Logger LOGGER = LogManager.getRootLogger();

  public static MainWindow mainWindow;
  private BwHeadless bwheadless;

  /**
   * Creates new form MainWindow.
   */
  /* Changed from public to private. */
  private MainWindow(BwHeadless bwheadless) {
    this.bwheadless = bwheadless;
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the NetBeans Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    btngrpGameType = new javax.swing.ButtonGroup();
    btngrpRace = new javax.swing.ButtonGroup();
    boxDropFiles = new javax.swing.JLabel();
    btnLaunch = new javax.swing.JButton();
    rbRaceTerran = new javax.swing.JRadioButton();
    rbRaceZerg = new javax.swing.JRadioButton();
    rbRaceProtoss = new javax.swing.JRadioButton();
    rbRaceRandom = new javax.swing.JRadioButton();
    txtBotName = new javax.swing.JTextField();
    lblBotName = new javax.swing.JLabel();
    lblBwapiDll = new javax.swing.JLabel();
    lblBwapiDllVer = new javax.swing.JLabel();
    lblBotFile = new javax.swing.JLabel();
    lblBwapiDllText = new javax.swing.JLabel();
    lblBwapiDllVersionText = new javax.swing.JLabel();
    lblBotFileText = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    boxDropFiles.setBackground(new java.awt.Color(0, 53, 137));
    boxDropFiles.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
    boxDropFiles.setForeground(new java.awt.Color(204, 204, 204));
    boxDropFiles.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    boxDropFiles.setText("Drop bot files here");
    boxDropFiles.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
    boxDropFiles.setOpaque(true);

    btnLaunch.setText("Launch");
    btnLaunch.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnLaunchActionPerformed(evt);
      }
    });

    btngrpRace.add(rbRaceTerran);
    rbRaceTerran.setText("Terran");
    rbRaceTerran.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        rbRaceTerranActionPerformed(evt);
      }
    });

    btngrpRace.add(rbRaceZerg);
    rbRaceZerg.setText("Zerg");
    rbRaceZerg.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        rbRaceZergActionPerformed(evt);
      }
    });

    btngrpRace.add(rbRaceProtoss);
    rbRaceProtoss.setText("Protoss");
    rbRaceProtoss.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        rbRaceProtossActionPerformed(evt);
      }
    });

    btngrpRace.add(rbRaceRandom);
    rbRaceRandom.setText("Random");
    rbRaceRandom.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        rbRaceRandomActionPerformed(evt);
      }
    });

    txtBotName.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        txtBotNameKeyPressed(evt);
      }
      public void keyReleased(java.awt.event.KeyEvent evt) {
        txtBotNameKeyReleased(evt);
      }
    });

    lblBotName.setText(" Bot name:");

    lblBwapiDll.setText("BWAPI.dll:");

    lblBwapiDllVer.setText("BWAPI Version:");

    lblBotFile.setText("Bot file:");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(261, 261, 261)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(rbRaceTerran)
              .addComponent(rbRaceZerg))
            .addGap(44, 44, 44)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(rbRaceRandom)
              .addComponent(rbRaceProtoss))
            .addContainerGap(28, Short.MAX_VALUE))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(boxDropFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                  .addComponent(lblBwapiDll, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblBotFile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBwapiDllVer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                  .addComponent(lblBwapiDllText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(lblBotFileText, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                  .addComponent(lblBwapiDllVersionText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(btnLaunch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(txtBotName)
              .addComponent(lblBotName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addGap(17, 17, 17)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lblBwapiDll)
          .addComponent(lblBwapiDllText))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lblBwapiDllVer)
          .addComponent(lblBwapiDllVersionText))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lblBotFile)
          .addComponent(lblBotFileText))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
        .addComponent(lblBotName)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
          .addGroup(layout.createSequentialGroup()
            .addComponent(txtBotName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(rbRaceProtoss)
              .addComponent(rbRaceTerran))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(rbRaceZerg)
              .addComponent(rbRaceRandom))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnLaunch, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(boxDropFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(7, 7, 7))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void btnLaunchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaunchActionPerformed
    bwheadless.launch();
  }//GEN-LAST:event_btnLaunchActionPerformed

  private void rbRaceTerranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbRaceTerranActionPerformed
    bwheadless.setBotRace(Races.TERRAN);
  }//GEN-LAST:event_rbRaceTerranActionPerformed

  private void rbRaceProtossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbRaceProtossActionPerformed
    bwheadless.setBotRace(Races.PROTOSS);
  }//GEN-LAST:event_rbRaceProtossActionPerformed

  private void rbRaceRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbRaceRandomActionPerformed
    bwheadless.setBotRace(Races.RANDOM);
  }//GEN-LAST:event_rbRaceRandomActionPerformed

  private void rbRaceZergActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbRaceZergActionPerformed
    bwheadless.setBotRace(Races.ZERG);
  }//GEN-LAST:event_rbRaceZergActionPerformed

  private void txtBotNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBotNameKeyPressed
    // TODO add your handling code here:
  }//GEN-LAST:event_txtBotNameKeyPressed

  private void txtBotNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBotNameKeyReleased
    // TODO add your handling code here:
  }//GEN-LAST:event_txtBotNameKeyReleased

  /**
   * Main function called when main window is displayed.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) throws IOException {
    /* Set the Nimbus look and feel if available. */
    try {
      for (UIManager.LookAndFeelInfo info :
          UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException
        | InstantiationException
        | IllegalAccessException
        | UnsupportedLookAndFeelException ex) {
      LOGGER.error(ex.getMessage(), ex);
    }

    BwHeadless bwheadless = new BwHeadless();

    /* Create and display the form. */
    mainWindow = new MainWindow(bwheadless);
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        mainWindow.setTitle(DropLauncher.PROGRAM_NAME);
        mainWindow.setResizable(false);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);

        mainWindow.updateInfo();
      }
    });

    /*
     * Add FileDrop Listener. All valid dropped files are added to the
     * static container object named FileDropList.
     */
//    FileDrop fileDrop = new FileDrop(
//        mainWindow.boxDropFiles,
//        new FileDrop.Listener() {
//          @Override
//          public void filesDropped(File[] files) {
//
//            bwheadless.readDroppedFiles();
//            FileDropList.INSTANCE.clear();
//          }
//    });
    FileDrop.Listener listener = new FileDropListener(bwheadless);

    /* DEBUGGING --- start */
//    ArrayList<String> argsList = new ArrayList<>();
//    argsList.add("a");
//    argsList.add("b");
//    argsList.add("c");
//    argsList.add("d");
//    Object[] argsArray = argsList.toArray();
//    String[] argsArrayArray = (String[])argsArray;
//    int len = argsArray.length;
//    for (int i = 0; i < len; i++)
//    {
//      System.out.println(argsArray[i]);
//    }
//    ProcessBuilder p = new ProcessBuilder(argsArrayArray);
//    p.start();



//    ProcessPipe pipe = new ProcessPipe();
//    String path = "bwheadless_newer.exe";
//    TokenArray pipeArgs = new TokenArray();
////    pipeArgs.add("-e \"S:\\install\\StarCraft\\StarCraft.exe\"");
//    pipeArgs.add("-e");
//    pipeArgs.add("\"S:\\install\\StarCraft\\StarCraft.exe\"");
//    pipeArgs.add("-j");
////    pipeArgs.add("-n IronBot");
//    pipeArgs.add("-n");
//    pipeArgs.add("IronBot");
////    pipeArgs.add("-r Terran");
//    pipeArgs.add("-r");
//    pipeArgs.add("Terran");
////    pipeArgs.add("-l BWAPI.dll");
//    pipeArgs.add("-l");
//    pipeArgs.add("BWAPI.dll");
//    pipeArgs.add("--lan");
////    pipeArgs.add("--installpath \"S:\\install\\StarCraft\"");
//    pipeArgs.add("--installpath");
//    pipeArgs.add("\"S:\\install\\StarCraft\"");
//    if (!pipe.open(path, pipeArgs.toStringArray())) {
//      System.out.println("error opening pipe");
//    }
//    System.out.println(path + " " + pipeArgs.toString());



//    MemoryFile mf = new MemoryFile();
//    mf.readIntoMemory("bwapi.ini");
//    int index = mf.getIndexStartsWith("ai");
//    String newDll;
//    String tmpLine;
//    TokenArray ta = new TokenArray();
//    if (index >= 0) {
//      System.out.println(mf.getLines().get(index));
//      tmpLine = mf.getLines().get(index);
//      newDll = tmpLine.substring(tmpLine.indexOf("=") + 2, tmpLine.length());
//      newDll = MainTools.getParentDirectory(newDll) + "\\NewBot.dll";
//      System.out.println("ai = " + newDll);
////      mf.writeToDisk(mf.getPath());
//    }
//    mf.printToConsole();



//      ConfigFile cf = new ConfigFile();
//      if (cf.open("bwapi.ini")) {
////        cf.setVariable("ai", "S:\\install\\StarCraft\\bwapi-data\\AI\\LetaBot.dll");
////        cf.setVariable("holiday", "ON");
////        System.out.println("ai = " + cf.getValue("ai"));
////        System.out.println("holiday = " + cf.getValue("holiday"));
////        cf.enableVariable("ai");
////        System.out.println("ai = " + cf.getValue("ai"));
//        System.out.println(cf.getValue("ai"));
//        cf.disableVariable("ai");
//        System.out.println(cf.getValue("ai"));
//        cf.enableVariable("ai");
//        cf.setVariable("ai", "S:\\install\\StarCraft\\bwapi-data\\AI\\Iron.dll");
//        System.out.println(cf.getValue("ai"));
//      } else {
//        System.out.println("error");
//      }

//    System.out.println(BwHeadless.getInstance().bwapiDllChecksums.getValue("BWAPI.dll 4.1.0b"));
//    System.out.println(BwHeadless.getInstance().bwapiDllChecksums.getName("5d5128709ba714aa9c6095598bcf4624"));


//    bwheadless.setStarcraftExe("S:\\install\\StarCraft\\StarCraft.exe");
//    bwheadless.setBwapiDll("S:\\install\\StarCraft\\bwapi-data\\BWAPI.dll");
//    bwheadless.setBotDll("S:\\install\\StarCraft\\bwapi-data\\AI\\KillerBot.dll");
//    bwheadless.setBotName("KillerBot");
//    bwheadless.setGameType(GameType.LAN);
//    bwheadless.setBotRace(Race.ZERG);
////    bwheadless.setBotClient("bwheadless.exe");
//    bwheadless.launch();
//    bwheadless.eject();

//    bwheadless.ensureDefaultConfigFile();
//    ConfigFile cf = new ConfigFile();
//    cf.open(new File(BwHeadless.DEFAULT_CFG_FILE));
//    cf.setVariable(PredefinedVariables.BOT_RACE.toString(), Race.RANDOM.toString());
//    bwheadless.setBotClient(new File("bwheadless.exe"));
//    bwheadless.loadConfigFile(new File(BwHeadless.DEFAULT_CFG_FILE));

    /* DEBUGGING --- end */
  }

  public void updateInfo() {
    Races race = bwheadless.getBotRace();
    if (race == Races.RANDOM) {
      rbRaceRandom.setSelected(true);
    } else if (race == Races.TERRAN) {
      rbRaceTerran.setSelected(true);
    } else if (race == Races.ZERG) {
      rbRaceZerg.setSelected(true);
    } else if (race == Races.PROTOSS) {
      rbRaceProtoss.setSelected(true);
    }

    String botName = bwheadless.getBotName();
    txtBotName.setText(botName);

    if (bwheadless.getBotClient() != null) {
      lblBotFileText.setText(bwheadless.getBotClient().getName());
    } else if (bwheadless.getBotDll() != null) {
      lblBotFileText.setText(bwheadless.getBotDll().getName());
    }

    if (bwheadless.getBwapiDll() != null) {
      lblBwapiDllText.setText(bwheadless.getBwapiDll().getName());
      String version = Bwapi.getBwapiVersion(bwheadless.getBwapiDll());
      if (version != null) {
        lblBwapiDllVersionText.setText(version);
      } else {
        lblBwapiDllVersionText.setText("unknown");
      }
    }
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel boxDropFiles;
  private javax.swing.JButton btnLaunch;
  private javax.swing.ButtonGroup btngrpGameType;
  private javax.swing.ButtonGroup btngrpRace;
  private javax.swing.JLabel lblBotFile;
  private javax.swing.JLabel lblBotFileText;
  private javax.swing.JLabel lblBotName;
  private javax.swing.JLabel lblBwapiDll;
  private javax.swing.JLabel lblBwapiDllText;
  private javax.swing.JLabel lblBwapiDllVer;
  private javax.swing.JLabel lblBwapiDllVersionText;
  private javax.swing.JRadioButton rbRaceProtoss;
  private javax.swing.JRadioButton rbRaceRandom;
  private javax.swing.JRadioButton rbRaceTerran;
  private javax.swing.JRadioButton rbRaceZerg;
  private javax.swing.JTextField txtBotName;
  // End of variables declaration//GEN-END:variables

}
