package customcrafter.customcrafter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

import static customcrafter.customcrafter.SettingsLoad.recipeAndResult;


public class Debug implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command,String label,String[] args){
        Player player = (Player) sender;
        for(Map.Entry<Map<Map<Integer,ItemStack>,Integer>,ItemStack> entry:recipeAndResult.entrySet()){
            player.sendMessage("---");
            player.sendMessage("key:"+entry.getKey());
            player.sendMessage("value:"+entry.getValue());
            player.sendMessage("---");
        }

        return true;
    }
}
