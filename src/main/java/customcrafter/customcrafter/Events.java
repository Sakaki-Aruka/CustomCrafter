package customcrafter.customcrafter;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class Events implements Listener {
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        new GUIclose().closeMain(event);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        new GUIclick().clickMain(event);
    }
}
