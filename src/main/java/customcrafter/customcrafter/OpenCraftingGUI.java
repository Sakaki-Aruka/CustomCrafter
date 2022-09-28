package customcrafter.customcrafter;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

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
        Inventory inventory = Bukkit.createInventory(null,27,"CustomCrafter");

        inventory.setContents(new createGUI().fill(3));
        player.openInventory(inventory);
        guiOpen.put(player,3);
        return true;

    }
}
