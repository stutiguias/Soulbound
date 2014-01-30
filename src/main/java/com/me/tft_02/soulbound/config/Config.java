package com.me.tft_02.soulbound.config;

import java.util.ArrayList;
import java.util.List;

public class Config extends AutoUpdateConfigLoader {
    private static Config instance;

    private Config() {
        super("config.yml");
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }

        return instance;
    }

    @Override
    protected void loadKeys() {}

    public boolean getStatsTrackingEnabled() { return config.getBoolean("General.Stats_Tracking", true); }
    public boolean getVerboseLoggingEnabled() { return config.getBoolean("General.Verbose_Logging", false); }
    public boolean getConfigOverwriteEnabled() { return config.getBoolean("General.Config_Update_Overwrite", true); }

    /* @formatter:on */

    /* SOULBOUND SETTINGS */
    public boolean getFeedbackEnabled() { return config.getBoolean("Soulbound.Feedback_Messages_Enabled", true); }
    public boolean getPreventItemDrop() { return config.getBoolean("Soulbound.Prevent_Item_Drop", false); }
    public boolean getDeleteOnDrop() { return config.getBoolean("Soulbound.Delete_On_Drop", false); }
    public boolean getInfiniteDurability() { return config.getBoolean("Soulbound.Infinite_Durability", false); }

    public List<String> getBlockedCommands() { return config.getStringList("Soulbound.Blocked_Commands"); }
    public List<String> getBindCommands() { return config.getStringList("Soulbound.Commands_Bind_When_Used"); }

}
