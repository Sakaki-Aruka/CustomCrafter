package customcrafter.customcrafter;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class RecipeClass {

    int total;
    int type;
    ArrayList<ItemStack> items;
    ItemStack result;

    RecipeClass(int total, int type, ArrayList<ItemStack> items,ItemStack result){

        this.total = total;
        this.type = type;
        this.items = items;
        this.result = result;
    }

    public ArrayList<ArrayList<ItemStack>> getItems(int type,ArrayList<RecipeClass> data){
        ArrayList<ArrayList<ItemStack>> result = new ArrayList<>();
        for(RecipeClass loop: data){
            if(loop.type==type){
                result.add(loop.items);
            }
        }
        return result;
    }

    public ItemStack getResult(RecipeClass recipe){
        try{
            return recipe.result;
        }catch (Exception e){
            return null;
        }
    }

    public int getMag(RecipeClass in,ItemStack model,ArrayList<RecipeClass> search){
        for(RecipeClass rc : search){
            if(in.total % rc.total == 0 && in.type == rc.type && rc.result == model){
                return (in.total % rc.total);
            }
        }
        return -1;
    }
}
