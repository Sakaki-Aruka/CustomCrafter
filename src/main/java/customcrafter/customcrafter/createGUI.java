package customcrafter.customcrafter;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class createGUI {
    private final String title = "CustomCrafter";

    private ItemStack blackGlass(){
        ItemStack itemStack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        itemStack.getItemMeta().setDisplayName("-");
        return itemStack;
    }

    public Inventory create9(){
        Inventory inventory = Bukkit.createInventory(null,27,title);
        this.setSupport(inventory,3,7,this.blackGlass());
        this.setSupport(inventory,12,17,this.blackGlass());
        this.setSupport(inventory,21,22,this.blackGlass());
        return inventory;
    }

    public Inventory create16(){
        Inventory inventory = Bukkit.createInventory(null,36,title);
        this.setSupport(inventory,4,7,this.blackGlass());
        this.setSupport(inventory,13,17,this.blackGlass());
        this.setSupport(inventory,22,26,this.blackGlass());
        inventory.setItem(31,new ItemStack(this.blackGlass()));
        return inventory;
    }

    private void setSupport(Inventory inventory, int start, int end, ItemStack itemStack){
        for(int i=start;i<=end;i++){
            inventory.setItem(i,itemStack);
        }
    }

    public ArrayList<Integer> okSlot(int type){
        ArrayList<Integer> arrayList = new ArrayList<>();

        for(int i1=0;i1<type*9;i1++){
            if(i1==0 || i1%9==0){
                for(int i2=0;i2<type;i2++){
                    arrayList.add(i1+i2);
                }
            }
        }

        return arrayList;
    }

    public ItemStack[] fill(int type){
        ArrayList<Integer> array = this.okSlot(type);
        ItemStack[] itemStacks = new ItemStack[type*9];

        for(int i=0;i<type*9;i++){
            if(!(array.contains(i))){
                itemStacks[i] = blackGlass();
            }else{
                itemStacks[i] = null;
            }
        }
        return itemStacks;
    }
}
