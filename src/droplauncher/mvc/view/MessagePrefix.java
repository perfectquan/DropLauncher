package droplauncher.mvc.view;

import adakite.util.AdakiteUtils;

/**
 * Enum for prepending strings to messages.
 */
public enum MessagePrefix {

  COPY("copy"),
  KILL("kill"),
  DELETE("delete"),
  BWHEADLESS("bwh"),
  CLIENT("client"),
  DROPLAUNCHER("DropLauncher")
  ;

  private final String str;

  private MessagePrefix(String str) {
    this.str = str;
  }

  /**
   * Returns the string version of this enum with an appended
   * colon character and space.
   *
   * @param str specified message to include
   */
  public String get(String str) {
    String ret = this.str + ": ";
    if (!AdakiteUtils.isNullOrEmpty(str)) {
      ret += str;
    }
    return ret;
  }

  public String get() {
    return get(null);
  }

  @Override
  public String toString() {
    return this.str;
  }

}
