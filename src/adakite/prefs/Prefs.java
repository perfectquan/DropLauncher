package adakite.prefs;

import adakite.util.AdakiteUtils;
import java.util.prefs.Preferences;

/**
 * Wrapper class for {@link java.util.prefs.Preferences}.
 */
public class Prefs {

  private Preferences prefs;

  private Prefs() {}

  public Prefs(Preferences prefs) {
    this.prefs = prefs;
  }

  /**
   * Sets the specified key to the specified value.
   *
   * @param key specified key
   * @param val specified value
   */
  public void set(String key, String val) {
    this.prefs.put(key, val);
  }

  /**
   * Returns the corresponding value of the specified key.
   *
   * @param key specified key
   * @throws IllegalStateException if the value has not been previously set
   */
  public String get(String key) {
    String val = this.prefs.get(key, "");
    if (AdakiteUtils.isNullOrEmpty(val)) {
      throw new IllegalStateException("value not set for: " + key);
    }
    return val;
  }

  /**
   * Tests whether the specified key has been previously set.
   *
   * @param key specified key
   */
  public boolean hasValue(String key) {
    try {
      get(key);
      return true;
    } catch (Exception ex) {
      return false;
    }
  }

  /**
   * Tests whether the corresponding value to the specified key is TRUE
   * or FALSE.
   *
   * @param key specified key
   */
  public boolean isEnabled(String key) {
    try {
      String val = get(key);
      return val.equalsIgnoreCase(Boolean.TRUE.toString());
    } catch (Exception ex) {
      return false;
    }
  }

  /**
   * Sets the specified key to the specified boolean value.
   *
   * @param key specified key
   * @param enabled specified boolean value
   */
  public void setEnabled(String key, boolean enabled) {
    set(key, Boolean.toString(enabled));
  }

  public Prefs getChild(String nodeName) {
    Preferences child = this.prefs.node(nodeName);
    return new Prefs(child);
  }

}