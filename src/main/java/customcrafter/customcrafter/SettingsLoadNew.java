package customcrafter.customcrafter;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

import static customcrafter.customcrafter.CustomCrafter.FC;


public class SettingsLoadNew {

    public static Map<ArrayList<ItemStack>,ItemStack> recipeAndResultN = new HashMap<>();

    public void newLoadMain(){

        ArrayList<ArrayList<ItemStack>> itemStacks = new ArrayList<>();
        ArrayList<ItemStack> results = new ArrayList<>();

        ArrayList<ItemStack> itemStacksSetUp = new ArrayList<>();

        //collect recipe parts
        int partsCount = 0;
        while(true){
            if(FC.contains("p"+partsCount)){

                String pPath = "p"+partsCount+".";
                ItemStack itemStack;

                Material material = Material.valueOf(FC.getString(pPath+"material").toUpperCase(Locale.ROOT));
                int amount = FC.getInt(pPath+"amount");

                itemStack = new ItemStack(material);
                itemStack.setAmount(amount);

                //add enchants
                if(FC.contains(pPath+"enchant")){
                    itemStack.addUnsafeEnchantments(this.enchantFromConfig(pPath+"enchant"));
                }
                partsCount++;
                itemStacksSetUp.add(itemStack);

            }else{
                break;
            }
        }

        //collect result and recipe
        int resultCount = 0;
        while(true){
            if(FC.contains("result"+resultCount)){
                ItemStack result;
                String rPath = "result"+resultCount+".";
                int type = FC.getInt(rPath+"type");

                Material material = Material.valueOf(FC.getString(rPath+"material").toUpperCase(Locale.ROOT));
                result = new ItemStack(material);
                int amount = FC.getInt(rPath+"amount");
                result.setAmount(amount);
                ItemMeta itemMeta = result.getItemMeta();

                //set name
                if(FC.contains(rPath+"name")){
                    String name = FC.getString(rPath+"name");
                    itemMeta.setDisplayName(name);
                }

                //add enchants
                if(FC.contains(rPath+"enchant")){
                    result.addUnsafeEnchantments(this.enchantFromConfig(rPath+"enchant"));
                }

                //add lore
                if(FC.contains(rPath+"lore")){
                    ArrayList<String> lines = new ArrayList<>();
                    String lore = FC.getString(rPath+"lore");
                    for(String loop:lore.split("!&!")){
                        lines.add(loop);
                    }
                    itemMeta.setLore(lines);
                }
                //add ItemFlag
                if(FC.contains(rPath+"itemflag")){

                    String flags = FC.getString(rPath+"itemflag");
                    for(String loop:flags.toUpperCase(Locale.ROOT).split(",")){
                        ItemFlag itemFlag = ItemFlag.valueOf(loop);
                        itemMeta.addItemFlags(itemFlag);
                    }
                }
                result.setItemMeta(itemMeta);

                //collect recipe pattern
                ArrayList<ItemStack> temp = new ArrayList<>();
                for(int i=0;i<type;i++){
                    String line = FC.getString(rPath+"line"+i);
                    for(String loop:line.split(",")){
                        if(loop.equalsIgnoreCase("n")){
                            temp.add(null);
                        }else{
                            temp.add(itemStacksSetUp.get(Integer.valueOf(loop.replace("p",""))));
                        }
                    }
                }
                recipeAndResultN.put(temp,result);
            }else{
                break;
            }
        }


    }

    public Map<Enchantment,Integer> enchantFromConfig(String path){
        Map<Enchantment,Integer> es = new HashMap<>();
        String enchantments = FC.getString(path);

        for(String loop:enchantments.split(",")){
            String enchant = Arrays.asList(loop.split(";")).get(0).toUpperCase(Locale.ROOT);
            int level = Integer.valueOf(Arrays.asList(loop.split(";")).get(1));

            Enchantment enchantment = Enchantment.getByName(enchant);
            es.put(enchantment,level);
        }
        return es;
    }
}
