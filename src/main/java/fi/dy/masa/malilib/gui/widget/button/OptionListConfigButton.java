package fi.dy.masa.malilib.gui.widget.button;

import javax.annotation.Nullable;
import fi.dy.masa.malilib.config.option.OptionListConfig;
import fi.dy.masa.malilib.util.StringUtils;

public class OptionListConfigButton extends GenericButton
{
    private final OptionListConfig<?> config;
    @Nullable private final String prefixTranslationKey;

    public OptionListConfigButton(int x, int y, int width, int height, OptionListConfig<?> config)
    {
        this(x, y, width, height, config, null);
    }

    public OptionListConfigButton(int x, int y, int width, int height, OptionListConfig<?> config, @Nullable String prefixTranslationKey)
    {
        super(x, y, width, height, "");

        this.config = config;
        this.prefixTranslationKey = prefixTranslationKey;

        this.updateDisplayString();
    }

    @Override
    protected boolean onMouseClickedImpl(int mouseX, int mouseY, int mouseButton)
    {
        this.config.cycleValue(mouseButton == 0);
        this.updateDisplayString();

        return super.onMouseClickedImpl(mouseX, mouseY, mouseButton);
    }

    @Override
    protected String generateDisplayString()
    {
        String configDisplayText = this.config.getOptionListValue().getDisplayName();

        if (this.prefixTranslationKey != null)
        {
            return StringUtils.translate(this.prefixTranslationKey, configDisplayText);
        }
        else
        {
            return configDisplayText;
        }
    }
}
