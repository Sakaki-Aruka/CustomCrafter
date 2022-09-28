package customcrafter.customcrafter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static customcrafter.customcrafter.GUIclick.nextPage;
import static customcrafter.customcrafter.OpenCraftingGUI.guiOpen;

public class GUIclose {
    public static Map<Player,Integer> newPageBuffer = new HashMap<>();

    public void closeMain(InventoryCloseEvent event){
        UUID uuid = event.getPlayer().getUniqueId();
        Player player = Bukkit.getPlayer(uuid);


        if(guiOpen.containsKey(player)){
            guiOpen.remove(player);

            /*
            if(nextPage.containsKey(player)){
                int type =  Integer.valueOf(nextPage.get(player)).intValue();
                Inventory inventory = null;
                inventory = Bukkit.createInventory(null,type*9,"CustomCrafter");

                if(type==3){
                    inventory.setContents(new createGUI().fill(3));
                }else if(type==4){
                    inventory.setContents(new createGUI().fill(4));

                    //debug
                    player.sendMessage("now:4");
                    ItemStack[] test = new createGUI().fill(4);
                    for(int i=0;i<36;i++){
                        player.sendMessage(i+"Slot:"+test[i]);
                    }
                    player.openInventory(inventory);

                }else if(type==5){
                    inventory.setContents(new createGUI().fill(5));
                }else if(type==6){
                    inventory.setContents(new createGUI().fill(6));
                }



                //debug
                player.sendMessage("new:"+type);

                guiOpen.put(player,type);
                nextPage.remove(player);
            }
            */
        }
    }
}
