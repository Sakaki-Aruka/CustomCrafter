package customcrafter.customcrafter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import static customcrafter.customcrafter.SettingsLoad.recipeAndResult;

public final class CustomCrafter extends JavaPlugin implements CommandExecutor {

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
        getCommand("ccreload").setExecutor(this);

        getServer().getPluginManager().registerEvents(new Events(),this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command,String label,String[] args){
        if(sender.isOp()){
            sender.sendMessage("[Custom Crafter]:Data reload -start-");

            recipeAndResult.clear();
            reloadConfig();
            this.load();

            sender.sendMessage("[Custom Crafter]:Data reload -end-");
            return true;
        }else{
            return false;
        }
    }
}
