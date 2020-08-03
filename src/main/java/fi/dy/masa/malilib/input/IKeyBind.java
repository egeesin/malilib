package fi.dy.masa.malilib.input;

import java.util.List;
import javax.annotation.Nullable;
import com.google.gson.JsonElement;

public interface IKeyBind
{
    /**
     * Sets the mod name owning this keybind. Used for the popup toast rendering.
     * @param modId
     */
    void setModId(String modId);

    boolean isValid();

    /**
     * Returns true if the keybind was pressed down during the current game tick.
     */
    boolean isPressed();

    /**
     * Returns true if the keybind is currently being held down.
     */
    boolean isKeyBindHeld();

    /**
     * Checks and updated the pressed status, and fires the callback, if one is set.
     * Returns true if further processing of the just pressed key should be cancelled.
     * This return value can be determined by the callback, if one has been set.
     * Without a callback, the return value will be false to not cancel further processing.
     * @return
     */
    boolean updateIsPressed();

    KeyBindSettings getSettings();

    KeyBindSettings getDefaultSettings();

    /**
     * Set the settings for this keybind.
     * @param settings
     */
    void setSettings(KeyBindSettings settings);

    boolean isModified();

    boolean isDirty();

    void cacheSavedValue();

    void clearKeys();

    void resetToDefault();

    void addKey(int keyCode);

    String getStringValue();

    String getDefaultStringValue();

    void setValueFromString(String str);

    void removeKey(int keyCode);

    /**
     * Check if this keybind is only a single key, matching the given key code.
     * This is mainly meant for checking equality against vanilla keybinds.
     * @param keyCode
     * @return
     */
    boolean matches(int keyCode);

    boolean overlaps(IKeyBind other);

    void tick();

    String getKeysDisplayString();

    List<Integer> getKeys();

    void setCallback(@Nullable IHotkeyCallback callback);

    boolean areSettingsModified();

    void resetSettingsToDefaults();

    void setValueFromJsonElement(JsonElement element, String hotkeyName);

    JsonElement getAsJsonElement();
}