package customcrafter.customcrafter;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class createGUI {
    private final String title = "CustomCrafter";

    private ArrayList<ItemStack> sizeSet(int now){

        Map<Integer,ItemStack> size = new HashMap<>();

        size.put(3,this.sizeSetSupport(Material.SMALL_AMETHYST_BUD,"§rCraft Size 3"));
        size.put(4,this.sizeSetSupport(Material.MEDIUM_AMETHYST_BUD,"§rCraft Size 4"));
        size.put(5,this.sizeSetSupport(Material.LARGE_AMETHYST_BUD,"§rCraft Size 5"));
        size.put(6,this.sizeSetSupport(Material.AMETHYST_CLUSTER,"§rCraft Size 6"));

        size.remove(now);
        ArrayList<ItemStack> itemStacks = new ArrayList<>();

        for(Map.Entry<Integer,ItemStack> entry:size.entrySet()){
            itemStacks.add(entry.getValue());
        }
        return itemStacks;
    }

    //set itemMeta to itemStack
    private ItemStack sizeSetSupport(Material material,String name){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack blackGlass(){
        return this.sizeSetSupport(Material.BLACK_STAINED_GLASS_PANE,"-");
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
        ArrayList<ItemStack> sizeAmethyst = this.sizeSet(type);

        for(int i=0;i<type*9;i++){
            if(!(array.contains(i))){
                if(i==8){
                    itemStacks[i]=null;
                }else{
                    if(i >= (type*9)-3) {
                        //set amethyst(gui size select)
                        if (i == (type * 9 - 3)) {
                            itemStacks[i] = sizeAmethyst.get(0);
                        } else if (i == (type * 9) - 2) {
                            itemStacks[i] = sizeAmethyst.get(1);
                        } else if (i == (type * 9) - 1) {
                            itemStacks[i] = sizeAmethyst.get(2);
                        }
                    }else {
                        itemStacks[i] = this.blackGlass();
                    }
                }
            }else{
                itemStacks[i] = null;
            }
        }
        return itemStacks;
    }
}
