package customcrafter.customcrafter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.UUID;

import static customcrafter.customcrafter.OpenCraftingGUI.guiOpen;

public class GUIclose {
    public void closeMain(InventoryCloseEvent event){
        UUID uuid = event.getPlayer().getUniqueId();
        Player player = Bukkit.getPlayer(uuid);

        if(guiOpen.contains(player)){
            guiOpen.remove(player);
        }
    }
}
