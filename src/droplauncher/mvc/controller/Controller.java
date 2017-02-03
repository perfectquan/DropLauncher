package droplauncher.mvc.controller;

import adakite.utils.AdakiteUtils;
import droplauncher.bwapi.BWAPI;
import droplauncher.bwheadless.BotModule;
import droplauncher.mvc.model.Model;
import droplauncher.mvc.view.LaunchButtonText;
import droplauncher.mvc.view.SimpleAlert;
import droplauncher.mvc.view.View;
import droplauncher.starcraft.Race;
import droplauncher.util.Util;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Controller {

  private Model model;
  private View view;

  public Controller() {

  }

  public void setModel(Model model) {
    this.model = model;
  }

  public void setView(View view) {
    this.view = view;
  }

  private void startBWHeadless() {
    this.model.startBWHeadless();
  }

  private void stopBWHeadless() {
    this.model.stopBWHeadless();
  }

  /* ************************************************************ */
  /* Events for a view */
  /* ************************************************************ */

  public BotModule getBotModule() {
    return this.model.getBWHeadless().getBotModule();
  }

  public String getBwapiDllVersion() {
    String dll = this.model.getBWHeadless().getBwapiDll();
    if (AdakiteUtils.isNullOrEmpty(dll)) {
      return "";
    } else {
      String version = BWAPI.getBwapiVersion(Paths.get(dll));
      return version;
    }
  }

  public String getBotName() {
    return this.model.getBWHeadless().getBotName();
  }

  public Race getBotRace() {
    return this.model.getBWHeadless().getBotRace();
  }

  /* ************************************************************ */
  /* Events from a view */
  /* ************************************************************ */

  public void mnuFileSelectBotFilesClicked() {

  }

  public void mnuFileExitClicked() {

  }

  public void mnuEditSettingsClicked() {

  }

  public void mnuHelpAboutClicked() {

  }

  public void filesDropped(List<File> files) {
    this.model.filesDropped(files);
    this.view.update();
  }

  public void botRaceChanged(String str) {
    if (str.equals(Race.TERRAN.toString())) {
      this.model.getBWHeadless().setBotRace(Race.TERRAN);
    } else if (str.equals(Race.ZERG.toString())) {
      this.model.getBWHeadless().setBotRace(Race.ZERG);
    } else if (str.equals(Race.PROTOSS.toString())) {
      this.model.getBWHeadless().setBotRace(Race.PROTOSS);
    } else if (str.equals(Race.RANDOM.toString())) {
      this.model.getBWHeadless().setBotRace(Race.RANDOM);
    }
  }

  public void botNameChanged(String str) {
    this.model.getBWHeadless().setBotName(str);
    this.view.update();
  }

  public void btnLaunchClicked() {
    if (!this.model.getBWHeadless().isRunning()) {
      if (!this.model.getBWHeadless().isReady()) {
        /* Display error message. */
        new SimpleAlert().showAndWait(
            AlertType.ERROR,
            "Not Ready",
            "The program is not ready due to the following error: " + Util.newline(2) +
            this.model.getBWHeadless().getReadyStatus().toString()
        );
      } else {
        /* Start bwheadless. */
        this.view.btnLaunchEnabled(false);
        new Thread(() -> {
          startBWHeadless();
          Platform.runLater(() -> {
            this.view.btnLaunchSetText(LaunchButtonText.EJECT.toString());
            this.view.btnLaunchEnabled(true);
          });
        }).start();
      }
    } else {
      /* Stop bwheadless. */
      this.view.btnLaunchEnabled(false);
      new Thread(() -> {
        stopBWHeadless();
        Platform.runLater(() -> {
          this.view.btnLaunchSetText(LaunchButtonText.LAUNCH.toString());
          this.view.btnLaunchEnabled(true);
        });
      }).start();
    }
  }

}
