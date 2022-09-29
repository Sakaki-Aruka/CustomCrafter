package customcrafter.customcrafter;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

import static customcrafter.customcrafter.CustomCrafter.FC;

public class SettingsLoad {
    public void fc(FileConfiguration fileConfiguration){
        FC = fileConfiguration;
        this.configLoad();
    }

    public static Map<Map<Map<Integer,ItemStack>,Integer>,ItemStack> recipeAndResult = new HashMap<>();

    public void configLoad(){
        int count = 0;

        while (true){
            if(FC.contains("result"+count)){
                int guiSize = FC.getInt("recipe"+count+".CraftType");
                Map<Map<Integer,ItemStack>,Integer> key = new HashMap<>();
                key.put(recipeLoad(count),guiSize);

                recipeAndResult.put(key,resultLoad(count));
                count++;
            }else{
                break;
            }
        }
    }

    //load recipe
    public Map recipeLoad(int number){
        //Map<Integer,Map<Integer,ItemStack>> returnMap = new HashMap<>();
        Map<Integer,ItemStack> tempMap = new HashMap<>();
        String path = "recipe"+number+".";

        int craftType = FC.getInt(path+"CraftType");

        for(int i=1;i<=(craftType * craftType);i++){
            String sPath = path+"s"+i+".";
            ItemStack itemStack = null;
            if(FC.contains(sPath)){
                if(FC.contains(sPath+"Same")){
                    //exist "Same"
                    int slotNumber = FC.getInt(sPath+"Same");
                    String samePath = path+"s"+slotNumber+".";
                    itemStack = this.recipeSupport(samePath);
                    if(FC.contains(sPath+"Change")){
                        for(String loop:FC.getString(sPath+"Change").toUpperCase(Locale.ROOT).split(",")){
                            if(loop.startsWith("MATERIAL")){
                                String id = Arrays.asList(loop.split(";")).get(1);
                                Material material = Material.valueOf(id);

                                itemStack.setType(material);

                            }else if(loop.startsWith("AMOUNT")){
                                int amount = Integer.valueOf(Arrays.asList(loop.split(";")).get(1));
                                itemStack.setAmount(amount);
                            }else if(loop.startsWith("ENCHANT")){
                                //remove all enchantments
                                for(Map.Entry<Enchantment,Integer> entry:itemStack.getEnchantments().entrySet()){
                                    itemStack.removeEnchantment(entry.getKey());
                                }
                                //add enchantment
                                //Map<Enchantment,Integer> eMap = new HashMap<>();
                                for(String eLoop:loop.replace("ENCHANT;","").split("&")){
                                    Enchantment enchant = Enchantment.getByName(Arrays.asList(eLoop.toUpperCase(Locale.ROOT).split("~")).get(0));
                                    int level = Integer.valueOf(Arrays.asList(eLoop.split("~")).get(1));
                                    ItemMeta itemMeta = itemStack.getItemMeta();
                                    itemMeta.addEnchant(enchant,level,true);
                                    itemStack.setItemMeta(itemMeta);
                                }
                            }
                        }
                    }

                }else{
                    itemStack = this.recipeSupport(sPath);
                }
            }
            tempMap.put(i-1,itemStack);
        }
        //returnMap.put(craftType,tempMap);
        return tempMap;
    }

    public ItemStack recipeSupport(String sPath){
        ItemStack itemStack = null;
        itemStack = new ItemStack(Material.valueOf(FC.getString(sPath+"Material").toUpperCase(Locale.ROOT)));
        ItemMeta itemMeta = itemStack.getItemMeta();
        int amount = FC.getInt(sPath+"Amount");
        itemStack.setAmount(amount);
        if(FC.contains(sPath+"Enchant")){
            for(String loop:FC.getString(sPath+"Enchant").toUpperCase(Locale.ROOT).split(",")){
                Enchantment enchant = Enchantment.getByName(Arrays.asList(loop.split(";")).get(0));
                int level =Integer.valueOf(Arrays.asList(loop.split(";")).get(1));
                itemMeta.addEnchant(enchant,level,true);

            }
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack resultLoad(int number){
        String path = "result"+number+".";

        ItemStack itemStack = new ItemStack(Material.valueOf(FC.getString(path+"Material").toUpperCase(Locale.ROOT)));
        itemStack.setAmount(FC.getInt(path+"Amount"));
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(FC.getString(path+"Name"));
        //exist enchant settings
        if(FC.contains(path+"Enchant")){
            for(String loop:FC.getString(path+"Enchant").split(",")){
                Enchantment enchant = Enchantment.getByName(Arrays.asList(loop.split(";")).get(0).toUpperCase(Locale.ROOT));
                int level = Integer.valueOf(Arrays.asList(loop.split(";")).get(1));
                itemMeta.addEnchant(enchant,level,true);
            }
        }
        //exist lore settings
        if(FC.contains(path+"Lore")){
            itemMeta.setLore(Arrays.asList(FC.getString(path+"Lore").split("!&!")));
        }
        //exist unbreakable settings
        if(FC.contains(path+"Unbreak")){
            itemMeta.setUnbreakable(true);
        }
        //exist itemFlag settings
        if(FC.contains(path+"ItemFlag")){
            for(String loop:FC.getString(path+"ItemFlag").toUpperCase(Locale.ROOT).split(",")){
                itemMeta.addItemFlags(ItemFlag.valueOf(loop));
            }
        }

        //itemMeta set to a ItemStack
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
