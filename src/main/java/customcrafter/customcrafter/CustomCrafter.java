package customcrafter.customcrafter;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomCrafter extends JavaPlugin {

    public static FileConfiguration FC;

    public void load(){
        new SettingsLoad().fc(getConfig());
    }

    @Override
    public void onEnable() {
        //settings load
        saveDefaultConfig();
        this.load();
        getCommand("customcrafterdebug").setExecutor(new Debug());
        getCommand("customopen").setExecutor(new OpenCraftingGUI());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
