/*
 * Copyright (C) 2017 Adakite
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
Software versioning:

major.minor[.build][status]

Statuses:
a for alpha
b for beta
rc for release candidate
omit for (final) release
*/

package droplauncher;

import adakite.ini.Ini;
import adakite.util.AdakiteUtils;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Constants class for global constants.
 */
public class DropLauncher {

  public enum PropertyKey {

    /**
     * DropLauncher version.
     */
    VERSION("version"),

    /**
     * Whether to eject the bot after DropLauncher detects the game has ended.
     */
    AUTO_EJECT_BOT("auto_eject_bot"),

    /**
     * Whether to connect the bot again after it has been ejected.
     */
    AUTO_BOT_REJOIN("auto_bot_rejoin")

    ;

    private final String str;

    private PropertyKey(String str) {
      this.str = str;
    }

    @Override
    public String toString() {
      return this.str;
    }

  }

  public static final String PROGRAM_NAME = "DropLauncher";
  public static final String PROGRAM_VERSION = "0.5.24a";
  public static final String PROGRAM_AUTHOR = "Adakite";
  public static final String PROGRAM_TITLE = PROGRAM_NAME + " v" + PROGRAM_VERSION;
  public static final String PROGRAM_GITHUB = "https://github.com/AdakiteSystems/DropLauncher";
  public static final String PROGRAM_LICENSE = "AGPL 3.0";
  public static final String PROGRAM_LICENSE_LINK = "https://www.gnu.org/licenses/agpl-3.0.en.html";
  public static final String PROGRAM_DESC = "DropLauncher is designed to be a simple tool to aid players in setting up and playing 1v1 StarCraft: Brood War games against any BWAPI bot using Local Area Network (UDP) as the connection type.";
  public static final String PROGRAM_ABOUT
      = DropLauncher.PROGRAM_NAME + AdakiteUtils.newline()
      + "Author: " + DropLauncher.PROGRAM_AUTHOR + AdakiteUtils.newline()
      + "Version: " + DropLauncher.PROGRAM_VERSION + AdakiteUtils.newline(2)
      + DropLauncher.PROGRAM_DESC + AdakiteUtils.newline(2)
      + "License: " + DropLauncher.PROGRAM_LICENSE + AdakiteUtils.newline()
      + DropLauncher.PROGRAM_LICENSE_LINK + AdakiteUtils.newline(2)
      + "Source:" + AdakiteUtils.newline()
      + DropLauncher.PROGRAM_GITHUB + AdakiteUtils.newline()
      ;

  public static final Path ROOT_DIRECTORY = Paths.get("");

  public static final Path DATA_DIRECTORY = ROOT_DIRECTORY.resolve("data");
  public static final Path BINARY_DIRECTORY = ROOT_DIRECTORY.resolve("bin");

//  public static final String TEMP_DIRECTORY = Paths.get(System.getProperty("user.home"), "DropLauncher").toString();
  public static final Path TEMP_DIRECTORY = DATA_DIRECTORY.resolve("tmp");

//  public static final Prefs PREF_ROOT = new Prefs(Preferences.userRoot().node("droplauncher"));
  private static final Ini INI = new Ini();
  public static final Path SETTINGS_FILE = DATA_DIRECTORY.resolve("settings.ini");

  private DropLauncher() {}

  public static Ini getSettings() {
    return INI;
  }

  public static URL getResource(String resourcePath) {
    return DropLauncher.class.getResource(resourcePath);
  }

}
