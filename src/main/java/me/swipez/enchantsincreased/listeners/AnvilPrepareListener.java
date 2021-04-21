package me.swipez.enchantsincreased.listeners;

import me.swipez.enchantsincreased.EnchantsIncreased;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class AnvilPrepareListener implements Listener {

    EnchantsIncreased plugin;

    public AnvilPrepareListener(EnchantsIncreased plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onAnvilPrepare(PrepareAnvilEvent event) {
        try {
            if (event.getInventory().getItem(0) != null) {
                if (event.getInventory().getItem(1) != null) {
                    if (event.getInventory().getItem(0).getType() == event.getInventory().getItem(1).getType()) {
                        ItemStack firstItem = event.getInventory().getItem(0);
                        ItemStack secondItem = event.getInventory().getItem(1);
                        List<String> firstList = firstItem.getItemMeta().getLore();
                        List<String> secondList = secondItem.getItemMeta().getLore();
                        if (firstList != null && secondList != null) {
                            for (String string : secondList) {
                                for (String firstString : firstList){
                                    if (firstString.contains(string.substring(0,3))){
                                        secondList.remove(string);
                                    }
                                }
                            }
                            firstList.addAll(secondList);
                            ItemStack resultStack = event.getResult().clone();
                            ItemMeta meta = resultStack.getItemMeta();
                            Map<Enchantment, Integer> firstEnchants = firstItem.getEnchantments();
                            Map<Enchantment, Integer> secondEnchants = secondItem.getEnchantments();
                            Map<Enchantment, Integer> totalEnchants = new HashMap<>();
                            for (Map.Entry<Enchantment, Integer> firstEnchantments : firstEnchants.entrySet()){
                                totalEnchants.put(firstEnchantments.getKey(), firstEnchantments.getValue());
                            }
                            for (Map.Entry<Enchantment, Integer> secondEnchantments : secondEnchants.entrySet()){
                                if (!totalEnchants.containsKey(secondEnchantments.getKey())){
                                    totalEnchants.put(secondEnchantments.getKey(), secondEnchantments.getValue());
                                }
                            }
                            meta.setLore(firstList);
                            for (Map.Entry<Enchantment, Integer> finalEnchantments : totalEnchants.entrySet()){
                                meta.addEnchant(finalEnchantments.getKey(), finalEnchantments.getValue(), true);
                            }
                            if (!event.getInventory().getRenameText().isEmpty()) {
                                meta.setDisplayName(event.getInventory().getRenameText());
                            } else {
                                meta.setDisplayName(firstItem.getItemMeta().getDisplayName());
                            }
                            resultStack.setItemMeta(meta);
                            event.setResult(resultStack);
                        }
                    }
                }
            }
        }
        catch (NullPointerException | ConcurrentModificationException exception){
            // IGNORED
        }
    }
}
