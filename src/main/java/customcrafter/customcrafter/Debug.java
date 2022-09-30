package customcrafter.customcrafter;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Map;

import static customcrafter.customcrafter.OpenCraftingGUI.guiOpen;
import static customcrafter.customcrafter.SettingsLoad.recipeAndResult;
import static customcrafter.customcrafter.SettingsLoadNew.recipeAndResultN;


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

        for(Map.Entry<Player,Integer> entry:guiOpen.entrySet()){
            player.sendMessage("guiPage:"+entry.getValue());
        }

        for(Map.Entry<ArrayList<ItemStack>,ItemStack> entry:recipeAndResultN.entrySet()){
            player.sendMessage("---");
            player.sendMessage("new key:"+entry.getKey());
            player.sendMessage("---");
            player.sendMessage("new value:"+entry.getValue());
            player.sendMessage("---");
        }

        return true;
    }
}
