package customcrafter.customcrafter;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OpenCraftingGUI implements CommandExecutor {

    public static Map<Player,Integer> guiOpen = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command,String label,String[] args){
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        player.openInventory(new createGUI().create9());
        guiOpen.put(player,3);
        return true;

    }
}
