package fi.dy.masa.malilib.config.option;

import fi.dy.masa.malilib.input.IHotkey;
import fi.dy.masa.malilib.input.IKeyBind;
import fi.dy.masa.malilib.input.KeyCallbackToggleBooleanConfigWithMessage;
import fi.dy.masa.malilib.input.KeyBindMulti;
import fi.dy.masa.malilib.input.KeyBindSettings;
import fi.dy.masa.malilib.util.StringUtils;

public class HotkeyedBooleanConfig extends BooleanConfig implements IHotkey
{
    protected final IKeyBind keyBind;

    public HotkeyedBooleanConfig(String name, boolean defaultValue, String defaultHotkey, String comment)
    {
        this(name, defaultValue, defaultHotkey, comment, StringUtils.splitCamelCase(name));
    }

    public HotkeyedBooleanConfig(String name, boolean defaultValue, String defaultHotkey, String comment, String prettyName)
    {
        this(name, defaultValue, defaultHotkey, KeyBindSettings.DEFAULT, comment, prettyName);
    }

    public HotkeyedBooleanConfig(String name, boolean defaultValue, String defaultHotkey, KeyBindSettings settings, String comment, String prettyName)
    {
        super(name, defaultValue, comment, prettyName);

        this.keyBind = KeyBindMulti.fromStorageString(name, defaultHotkey, settings);
        this.keyBind.setCallback(new KeyCallbackToggleBooleanConfigWithMessage(this));
    }

    @Override
    public IKeyBind getKeyBind()
    {
        return this.keyBind;
    }

    @Override
    public void resetToDefault()
    {
        super.resetToDefault();

        this.keyBind.resetToDefault();
    }
}