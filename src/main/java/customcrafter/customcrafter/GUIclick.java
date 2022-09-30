package customcrafter.customcrafter;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

import static customcrafter.customcrafter.OpenCraftingGUI.guiOpen;
import static customcrafter.customcrafter.SettingsLoad.recipeAndResult;

public class GUIclick {

    public void clickMain(InventoryClickEvent event){

        UUID uuid = event.getWhoClicked().getUniqueId();
        Player player = Bukkit.getPlayer(uuid);

        if(guiOpen.containsKey(player)){
            int type = guiOpen.get(player);
            int clickSlot = event.getRawSlot();
            //Map<Map<Integer,ItemStack>,Integer>  map = new HashMap<>();

            if(new createGUI().okSlot(type).contains(clickSlot) || clickSlot ==8){
                //
                if(clickSlot==8){
                    event.setCancelled(true);
                    Map<Integer,ItemStack> slotItem = new HashMap<>();
                    //get crafting table size
                    int slots = type * 9;


                    int count = 0;
                    for(int loop : new createGUI().okSlot(type)){
                        slotItem.put(count,event.getClickedInventory().getItem(loop));
                        count++;
                    }


                    ItemStack result = this.searchResult(type,slotItem);
                    if(this.searchResult(type,slotItem)!= null){
                        for(int i=0;i<slots;i++){
                            //clear inventory
                            event.getClickedInventory().setItem(i,null);
                        }
                        //replace gui menu and place result in a slot No.8
                        if(type==3 || type==4 || type==5 || type==6){
                            player.getWorld().dropItemNaturally(player.getLocation(),result);
                            //replace black-stained-glass-pane
                            event.getClickedInventory().setContents(new createGUI().fill(type));

                        }

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
                if(1 <= type*9 - clickSlot && type*9 - clickSlot <= 3){
                    event.setCancelled(true);
                    ArrayList<String> arr = new ArrayList<>(Arrays.asList("6","5","4","3"));
                    //arr.remove(type*9-clickSlot);
                    arr.remove(String.valueOf(type));

                    // example):27 -1 -24 = 2
                    int amethyst = type*9 - 1 - clickSlot;
                    String nextGUIType = arr.get(amethyst);

                    //nextPage.put(player,nextGUIType);
                    player.closeInventory();
                    this.inventoryOpen(Integer.valueOf(nextGUIType),player);
                    return;

                }else{
                    //click players inventory(can click and something)
                    if(clickSlot < type*9 -1){
                        event.setCancelled(true);
                        return;
                    }
                    return;
                }
            }

        }
        return;
    }

    private ItemStack searchResult(int type,Map<Integer,ItemStack> recipe){
        /*
        //set key (made from recipeMap and GUi size (int))

        ArrayList<Material> materials = new ArrayList<>();
        ArrayList<Integer> amounts = new ArrayList<>();

        for(Map.Entry<Integer,ItemStack> entry: recipe.entrySet()){
            if(entry.getValue()!=null){
                materials.add(entry.getValue().getType());
                amounts.add(entry.getValue().getAmount());
            }else{
                materials.add(null);
            }
        }


        top:
        for(Map.Entry<ArrayList<Material>,ArrayList<Object>> entry: materialList.entrySet()){
            if(entry.getKey().equals(materials)){
                //debug
                Bukkit.broadcastMessage("same pattern item found in recipes.");
                Bukkit.broadcastMessage("same pattern item is ->:"+entry.getValue().get(3));
                boolean check = amounts.equals(entry.getValue().get(1));
                ArrayList<Integer> integers = (ArrayList<Integer>) entry.getValue().get(1);
                if(!check){
                    for(int i=1;i<=64;i++){
                        int tempInt = i;
                        ArrayList<Integer> intClone = (ArrayList<Integer>)(integers.clone());
                        intClone.replaceAll(in -> in * tempInt);
                        if(amounts.equals(intClone)){
                            Bukkit.broadcastMessage("amount same :"+i+" times arr.");
                            break top;
                        }else{
                            continue;
                        }
                    }
                }

                Bukkit.broadcastMessage("amount check:"+check);

                return null;
            }
        }

         */
        //debug lines in(2022-09-30)

        Map<Map<Integer,ItemStack>,Integer> key = new HashMap<>();
        for(Map.Entry<Map<Map<Integer,ItemStack>,Integer>,ItemStack> entry:recipeAndResult.entrySet()){

            key.put(recipe,type);

            if(entry.getKey().equals(key)){
                return entry.getValue();
            }
        }


        return null;
    }

    public void inventoryOpen(int type,Player player){
        Inventory inventory = Bukkit.createInventory(null,type*9,"CustomCrafter");
        inventory.setContents(new createGUI().fill(type));
        player.openInventory(inventory);
        guiOpen.put(player,type);
    }
}
