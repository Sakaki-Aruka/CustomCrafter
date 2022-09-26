package customcrafter.customcrafter;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

import static customcrafter.customcrafter.CustomCrafter.FC;

public class SettingsLoad {
    public void fc(FileConfiguration fileConfiguration){
        FC = fileConfiguration;
        this.configLoad();
    }

    public static ArrayList<ArrayList<Object>> materialAndResult = new ArrayList<>();

    public void configLoad(){

        int count = 0;
        while (true){

            if(!(FC.contains("result"+count))){
                break;
            }

            ArrayList<Object> recipeArray = new ArrayList<>();
            HashMap<Integer, ItemStack> recipe = new HashMap<>();
            String resultPath = "result"+count+".";

            ItemStack result;
            ItemMeta resultMeta;

            result = new ItemStack(Material.valueOf(FC.getString(resultPath+"Material").toUpperCase(Locale.ROOT)));
            resultMeta = result.getItemMeta();
            resultMeta.setDisplayName(FC.getString(resultPath+"Name"));
            resultMeta.setLore(Arrays.asList(FC.getString(resultPath+"Lore").split("!&!")));

            //exist enchantment settings
            if(FC.contains(resultPath+"Enchant")){
                ArrayList<Enchantment> enchantments = new ArrayList<>();
                ArrayList<Integer> enchantLevel = new ArrayList<>();
                //get enchantments
                for(String loop : FC.getString(resultPath+"Enchantment").toUpperCase(Locale.ROOT).split(",")){
                    enchantments.add(Enchantment.getByName(loop));
                }
                //get enchantments level
                for(String loop: FC.getString(resultPath+"Enchant_level").split(",")){
                    enchantLevel.add(Integer.valueOf(loop));
                }

                for(int i=0;i<enchantments.size();i++){
                    result.addUnsafeEnchantment(enchantments.get(i),enchantLevel.get(i));
                }

            }
            //exist un-break setting
            if(FC.contains(resultPath+"Unbreak")){
                resultMeta.setUnbreakable(true);
            }

            //exist itemFlag settings
            if(FC.contains(resultPath+"ItemFlag")){
                for(String loop:FC.getString(resultPath+"ItemFlag").toUpperCase(Locale.ROOT).split(",")){
                    resultMeta.addItemFlags(ItemFlag.valueOf(loop));
                }
            }
            //make itemStack to perfect style
            result.setItemMeta(resultMeta);
            //add result to a recipeArray
            recipeArray.add(result);

            //recipe
            String recipePath = "recipe"+count+".";
            int craftType = FC.getInt(recipePath+"CraftType");

            for(int i=1;i<=craftType;i++){
                String slotPath = recipePath+"s"+i+".";
                //exist material scheme (not use "same")
                if(FC.contains(slotPath+"Material")){
                    Material material = Material.valueOf(FC.getString(slotPath+"Material").toUpperCase(Locale.ROOT));
                    ItemStack recipeStack = new ItemStack(material);
                    recipeStack.setAmount(FC.getInt(slotPath+"Amount"));

                    //exist enchantment settings
                    if(FC.contains(slotPath+"Enchant")){
                        ArrayList<Enchantment> enchantments = new ArrayList<>();
                        ArrayList<Integer> enchantLevel = new ArrayList<>();

                        //get enchantment
                        for(String loop:FC.getString(slotPath+"Enchant").toUpperCase(Locale.ROOT).split(",")){
                            enchantments.add(Enchantment.getByName(loop));
                        }
                        //get enchantment level
                        for(String loop:FC.getString(slotPath+"Enchant_level").split(",")){
                            enchantLevel.add(Integer.valueOf(loop));
                        }
                        //add enchantment and enchantmentLevel settings to itemMeta
                        for(int e=0;e<enchantments.size();e++){
                            recipeStack.addUnsafeEnchantment(enchantments.get(e),enchantLevel.get(e));
                        }
                    }
                    recipe.put(i,recipeStack);
                    recipeArray.add(recipe);
                }else{
                    //not exist material scheme (use "same")
                    int sameSlot = FC.getInt(recipePath+"Same");

                    ItemStack sameStack = recipe.get(sameSlot);

                    if(FC.contains(slotPath+"Change")) {
                        //exist "Change" scheme
                        ArrayList<String> changeArray = new ArrayList<>(Arrays.asList(FC.getString(slotPath + "Change").split("&")));
                        for (String loop : changeArray) {
                            if (loop.startsWith("Material")) {
                                //change Material
                                ItemMeta sameMeta = sameStack.getItemMeta();
                                //replace Material
                                ItemStack newSameStack = new ItemStack(Material.valueOf(loop.replace("Material", "")));
                                newSameStack.setItemMeta(sameMeta);

                            } else if (loop.startsWith("Amount,")) {
                                //change Amount
                                sameStack.setAmount(Integer.valueOf(loop.replace("Amount", "")));

                            }
                        }
                        recipe.put(i, sameStack);
                        recipeArray.add(recipe);
                    }
                }
            }
            count++;
            materialAndResult.add(recipeArray);
        }
    }
}
