package fi.dy.masa.malilib.config;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class ConfigManager implements IConfigManager
{
    public static final IConfigManager INSTANCE = new ConfigManager();

    private final Map<String, IConfigHandler> configHandlers = new HashMap<>();

    @Override
    public void registerConfigHandler(String modId, IConfigHandler handler)
    {
        if (this.configHandlers.containsKey(modId))
        {
            throw new IllegalArgumentException("Tried to override an existing config handler for mod ID '" + modId + "'");
        }

        final String modName = handler.getModName();
        handler.getConfigsPerCategories().values().forEach((list) -> list.forEach((config) -> config.setModName(modName) ));

        this.configHandlers.put(modId, handler);
    }

    @Override
    @Nullable
    public IConfigHandler getConfigHandler(String modId)
    {
        return this.configHandlers.get(modId);
    }

    @Override
    public void onConfigsChanged(String modId)
    {
        IConfigHandler handler = this.configHandlers.get(modId);

        if (handler != null)
        {
            handler.onConfigsChanged();
        }
    }

    /**
     * NOT PUBLIC API - DO NOT CALL
     */
    public void loadAllConfigs()
    {
        for (IConfigHandler handler : this.configHandlers.values())
        {
            handler.load();
        }
    }

    /**
     * NOT PUBLIC API - DO NOT CALL
     */
    public void saveAllConfigs()
    {
        for (IConfigHandler handler : this.configHandlers.values())
        {
            handler.saveIfDirty();
        }
    }
}
