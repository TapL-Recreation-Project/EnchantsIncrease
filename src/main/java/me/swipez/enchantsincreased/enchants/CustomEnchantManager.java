package me.swipez.enchantsincreased.enchants;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CustomEnchantManager {

    public static Integer getEnchantmentLength(ItemStack itemStack, String enchantment){
        int length = 0;
        if (itemStack.getItemMeta() != null){
            ItemMeta meta = itemStack.getItemMeta();
            List<String> lore = meta.getLore();
            if (lore != null){
                for (String string : lore){
                    if (string.toLowerCase().contains(enchantment.toLowerCase())){
                        length = string.length()-enchantment.length();
                        break;
                    }
                }
            }
        }
        return length;
    }

    public static Boolean isAppliedEnchantment(ItemStack itemStack, String enchantment){
        boolean bool = false;
        if (itemStack.getItemMeta() != null){
            ItemMeta meta = itemStack.getItemMeta();
            List<String> lore = meta.getLore();
            if (lore != null){
                for (String string : lore){
                    if (string.toLowerCase().contains(enchantment.toLowerCase())){
                        bool = true;
                        break;
                    }
                }
            }
        }
        return bool;
    }

    public static Integer getRequiredLevel(int surroundingBlocks){
        int requiredLevel = 0;
        if (surroundingBlocks >= 864){
            requiredLevel = 1000000000;
        }
        else if (surroundingBlocks >= 704){
            requiredLevel = 100000000;
        }
        else if (surroundingBlocks >= 560){
            requiredLevel = 10000000;
        }
        else if (surroundingBlocks >= 432){
            requiredLevel = 1000000;
        }
        else if (surroundingBlocks >= 320){
            requiredLevel = 100000;
        }
        else if (surroundingBlocks >= 224){
            requiredLevel = 10000;
        }
        else if (surroundingBlocks >= 144){
            requiredLevel = 1000;
        }
        else if (surroundingBlocks >= 80){
            requiredLevel = 100;
        }
        else if (surroundingBlocks >= 16*2){
            requiredLevel = 10;
        }
        return requiredLevel;
    }

    public static Integer getLayerLevel(int surroundingBlocks){
        int requiredLevel = 0;
        if (surroundingBlocks >= 864){
            requiredLevel = 9;
        }
        else if (surroundingBlocks >= 704){
            requiredLevel = 8;
        }
        else if (surroundingBlocks >= 560){
            requiredLevel = 7;
        }
        else if (surroundingBlocks >= 432){
            requiredLevel = 6;
        }
        else if (surroundingBlocks >= 320){
            requiredLevel = 5;
        }
        else if (surroundingBlocks >= 224){
            requiredLevel = 4;
        }
        else if (surroundingBlocks >= 144){
            requiredLevel = 3;
        }
        else if (surroundingBlocks >= 80){
            requiredLevel = 2;
        }
        else if (surroundingBlocks >= 16*2){
            requiredLevel = 1;
        }
        return requiredLevel;
    }

    public static Boolean hasAbundanceLevel(Material searchMaterial, Inventory inventory, int levelAbundance){
        boolean bool = false;
        String romanNumeral = null;
        if (levelAbundance == 1){
            romanNumeral = "I";
        }
        if (levelAbundance == 2){
            romanNumeral = "II";
        }
        if (levelAbundance == 3){
            romanNumeral = "III";
        }
        if (levelAbundance == 4){
            romanNumeral = "IV";
        }
        if (levelAbundance == 5){
            romanNumeral = "V";
        }
        if (levelAbundance == 6){
            romanNumeral = "VI";
        }
        if (levelAbundance == 7){
            romanNumeral = "VII";
        }
        if (levelAbundance == 8){
            romanNumeral = "VIII";
        }
        if (levelAbundance == 9){
            romanNumeral = "IX";
        }
        for (int i = 0; i < inventory.getSize(); i++){
            if (inventory.getItem(i) != null){
                if (inventory.getItem(i).getType().equals(searchMaterial)){
                    ItemMeta meta = inventory.getItem(i).getItemMeta();
                    List<String> lore = meta.getLore();
                    if (lore != null){
                        for (String string : lore){
                            String comparedAbundance = "ABUNDANCE "+romanNumeral;
                            if (string.toUpperCase().contains(comparedAbundance)) {
                                bool = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return bool;
    }

    public static Boolean hasAbundanceLevel(ItemStack itemStack, int levelAbundance) {
        boolean bool = false;
        String romanNumeral = null;
        if (levelAbundance == 1) {
            romanNumeral = "I";
        }
        if (levelAbundance == 2) {
            romanNumeral = "II";
        }
        if (levelAbundance == 3) {
            romanNumeral = "III";
        }
        if (levelAbundance == 4) {
            romanNumeral = "IV";
        }
        if (levelAbundance == 5) {
            romanNumeral = "V";
        }
        if (levelAbundance == 6) {
            romanNumeral = "VI";
        }
        if (levelAbundance == 7) {
            romanNumeral = "VII";
        }
        if (levelAbundance == 8) {
            romanNumeral = "VIII";
        }
        if (levelAbundance == 9) {
            romanNumeral = "IX";
        }
        ItemMeta meta = itemStack.getItemMeta();
        List<String> lore = meta.getLore();
        if (lore != null) {
            for (String string : lore) {
                String comparedAbundance = "ABUNDANCE " + romanNumeral;
                if (string.toUpperCase().contains(comparedAbundance)) {
                    bool = true;
                    break;
                }
            }
        }
        return bool;
    }

    public static void applyAbundance(ItemStack itemStack, int levelAbundance){
        String romanNumeral = null;
        if (levelAbundance == 1){
            romanNumeral = "I";
        }
        if (levelAbundance == 2){
            romanNumeral = "II";
        }
        if (levelAbundance == 3){
            romanNumeral = "III";
        }
        if (levelAbundance == 4){
            romanNumeral = "IV";
        }
        if (levelAbundance == 5){
            romanNumeral = "V";
        }
        if (levelAbundance == 6){
            romanNumeral = "VI";
        }
        if (levelAbundance == 7){
            romanNumeral = "VII";
        }
        if (levelAbundance == 8){
            romanNumeral = "VIII";
        }
        if (levelAbundance == 9){
            romanNumeral = "IX";
        }
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE+"Abundance "+romanNumeral);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
    }
}
