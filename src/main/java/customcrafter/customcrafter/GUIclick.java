package customcrafter.customcrafter;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static customcrafter.customcrafter.OpenCraftingGUI.guiOpen;
import static customcrafter.customcrafter.SettingsLoad.recipeAndResult;

public class GUIclick {
    public void clickMain(InventoryClickEvent event){

        UUID uuid = event.getWhoClicked().getUniqueId();
        Player player = Bukkit.getPlayer(uuid);

        if(guiOpen.containsKey(player)){
            int type = guiOpen.get(player);
            int clickSlot = event.getRawSlot();
            Map<Map<Integer,ItemStack>,Integer>  map = new HashMap<>();

            if(new createGUI().okSlot(type).contains(clickSlot) || clickSlot ==8){
                //
                if(clickSlot==8){
                    event.setCancelled(true);
                    Map<Integer,ItemStack> slotItem = new HashMap<>();
                    //get crafting table size
                    int slots = type * 9;

                    //debug
                    player.sendMessage("type:"+type);
                    player.sendMessage("slots:"+new createGUI().okSlot(type));

                    int count = 0;
                    for(int loop : new createGUI().okSlot(type)){
                        slotItem.put(count,event.getClickedInventory().getItem(loop));
                        count++;
                    }

                    //debug
                    map.put(slotItem,type);
                    player.sendMessage("TorF:"+recipeAndResult.containsKey(map));

                    ItemStack result = this.searchResult(type,slotItem);
                    if(this.searchResult(type,slotItem)!= null){
                        for(int i=0;i<slots;i++){
                            //clear inventory
                            event.getClickedInventory().setItem(i,null);
                        }
                        //replace gui menu and place result in a slot No.8
                        if(type==3){
                            event.getClickedInventory().setItem(8,result);
                            //replace black-stained-glass-pane
                            event.getClickedInventory().setContents(new createGUI().fill(type));

                        }else if(type==4){
                            new createGUI().create16();
                        }
                        //

                    }else{
                        if(event.getClickedInventory().getItem(8)!=null){
                            player.getWorld().dropItemNaturally(player.getLocation(),event.getClickedInventory().getItem(8));
                            event.getClickedInventory().setItem(8,null);
                        }
                        //no items found from result-items list.
                        return;
                    }
                }

            }else{
                if(type*9 <= clickSlot){
                    //click players inventory(can click and something)
                    return;
                }
                event.setCancelled(true);
                return;
            }

        }
        return;
    }

    private ItemStack searchResult(int type,Map<Integer,ItemStack> recipe){
        //set key (made from recipeMap and GUi size (int))
        Map<Map<Integer,ItemStack>,Integer> key = new HashMap<>();
        for(Map.Entry<Map<Map<Integer,ItemStack>,Integer>,ItemStack> entry:recipeAndResult.entrySet()){

            key.put(recipe,type);

            if(entry.getKey().equals(key)){
                return entry.getValue();
            }
        }
        return null;
    }
}
