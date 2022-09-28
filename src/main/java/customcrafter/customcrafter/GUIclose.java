package customcrafter.customcrafter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static customcrafter.customcrafter.OpenCraftingGUI.guiOpen;

public class GUIclose {
    public static Map<Player,Integer> newPageBuffer = new HashMap<>();

    public void closeMain(InventoryCloseEvent event){
        UUID uuid = event.getPlayer().getUniqueId();
        Player player = Bukkit.getPlayer(uuid);


        if(guiOpen.containsKey(player)){
            guiOpen.remove(player);

        }
    }
}
