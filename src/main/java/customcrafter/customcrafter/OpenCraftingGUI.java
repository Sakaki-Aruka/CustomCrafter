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

public class OpenCraftingGUI implements CommandExecutor {

    public static ArrayList<Player> guiOpen = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command,String label,String[] args){
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        Inventory inventory = Bukkit.createInventory(null,27,"CustomCrafter");
        Material blackGlass = Material.BLACK_STAINED_GLASS_PANE;
        this.setSupport(inventory,3,7,blackGlass);
        this.setSupport(inventory,12,17,blackGlass);
        this.setSupport(inventory,21,22,blackGlass);

        player.openInventory(inventory);
        guiOpen.add(player);
        return true;

    }

    public void setSupport(Inventory inventory, int start, int end,Material material){
        ItemStack itemStack = new ItemStack(material);
        for(int i=start;i<=end;i++){
            inventory.setItem(i,itemStack);
        }
    }
}
