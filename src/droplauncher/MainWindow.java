/* MainWindow.java */

// CHECKSTYLE:OFF

package droplauncher;



import droplauncher.bwapi.Bwapi;
import droplauncher.bwheadless.BwHeadless;
import droplauncher.filedroplist.FileDropListener;
import droplauncher.starcraft.Race;
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

  private BwHeadless bwheadless;

  /**
   * Creates new form MainWindow.
   */
  /* Changed from public to private. */
  private MainWindow(BwHeadless bwheadless) {
    this.bwheadless = bwheadless;

    initComponents();

    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        setTitle(DropLauncher.PROGRAM_NAME);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        updateInfo();
      }
    });
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
    bwheadless.setBotRace(Race.TERRAN);
  }//GEN-LAST:event_rbRaceTerranActionPerformed

  private void rbRaceProtossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbRaceProtossActionPerformed
    bwheadless.setBotRace(Race.PROTOSS);
  }//GEN-LAST:event_rbRaceProtossActionPerformed

  private void rbRaceRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbRaceRandomActionPerformed
    bwheadless.setBotRace(Race.RANDOM);
  }//GEN-LAST:event_rbRaceRandomActionPerformed

  private void rbRaceZergActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbRaceZergActionPerformed
    bwheadless.setBotRace(Race.ZERG);
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

    /* Create and display the form. */
    BwHeadless bwheadless = new BwHeadless();
    MainWindow mainWindow = new MainWindow(bwheadless);
    bwheadless.setMainWindow(mainWindow);

    /*
     * Add FileDrop Listener. All valid dropped files are added to the
     * static container object named FileDropList.
     */
    FileDrop.Listener listener = new FileDropListener(bwheadless);
    new FileDrop(mainWindow.boxDropFiles, listener);
  }

  public void updateInfo() {
    Race race = bwheadless.getBotRace();
    if (race == Race.RANDOM) {
      rbRaceRandom.setSelected(true);
    } else if (race == Race.TERRAN) {
      rbRaceTerran.setSelected(true);
    } else if (race == Race.ZERG) {
      rbRaceZerg.setSelected(true);
    } else if (race == Race.PROTOSS) {
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
