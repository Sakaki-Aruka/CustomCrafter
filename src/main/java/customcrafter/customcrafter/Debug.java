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
        for(Map.Entry<Map<Integer,Map<Integer,ItemStack>>,ItemStack> entry:recipeAndResult.entrySet()){
            player.sendMessage("---");
            player.sendMessage("result:"+entry.getValue());
            for(Map.Entry<Integer,Map<Integer,ItemStack>> entry2:entry.getKey().entrySet()){
                player.sendMessage("---");
                player.sendMessage("Craft-Type:"+entry2.getKey());
                for(Map.Entry<Integer,ItemStack> entry3:entry2.getValue().entrySet()){
                    player.sendMessage("---");
                    player.sendMessage("Slot:"+entry3.getKey());
                    player.sendMessage("---");
                    player.sendMessage("ItemStack:"+entry3.getValue());
                    player.sendMessage("---");
                }
            }
        }

        return true;
    }
}
