package me.swipez.enchantsincreased.listeners;

import me.swipez.enchantsincreased.enchants.CustomEnchantManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Breedable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.NumberFormat;
import java.util.*;

public class EnchantmentListener implements Listener {

    Random random = new Random();

    private static final Set<Material> ALLOWED_BLOCKS = EnumSet.of(
            Material.BOOKSHELF,
            Material.COAL_BLOCK,
            Material.QUARTZ_BLOCK,
            Material.DIAMOND_BLOCK,
            Material.IRON_BLOCK,
            Material.GOLD_BLOCK,
            Material.LAPIS_BLOCK,
            Material.REDSTONE_BLOCK,
            Material.EMERALD_BLOCK
    );

    @EventHandler
    public void onPlayerEnchant(EnchantItemEvent event) {
        Block enchantmentTable = event.getEnchantBlock();
        Player player = event.getEnchanter();
        int nearbyBlocks = getNearbyBooksAndOres(enchantmentTable, 9);
        int requiredLevel = CustomEnchantManager.getRequiredLevel(nearbyBlocks);
        int abundanceLevel = CustomEnchantManager.getLayerLevel(nearbyBlocks);
        if (requiredLevel == 0){
            return;
        }
        if (player.getLevel() < requiredLevel) {
            player.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "You need " + requiredLevel + " XP Levels to enchant!");
            event.setCancelled(true);
            return;
        }
        if (random.nextDouble() < 0.7){
            if (event.getItem().getType().toString().toLowerCase().contains("_sword") || event.getItem().getType().toString().toLowerCase().contains("_pickaxe")) {
                if (!CustomEnchantManager.hasAbundanceLevel(event.getItem().getType(), player.getInventory(), abundanceLevel)) {
                    CustomEnchantManager.applyAbundance(event.getItem(), abundanceLevel);
                    ItemMeta meta = event.getItem().getItemMeta();
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    event.getItem().setItemMeta(meta);
                    player.setLevel(player.getLevel() - requiredLevel);
                    return;
                }
            }
        }
        NumberFormat myFormat = NumberFormat.getInstance();
        myFormat.setGroupingUsed(true);
        List list = new ArrayList<>();
        for (Map.Entry<Enchantment, Integer> entry : event.getEnchantsToAdd().entrySet()) {
            int randomLevel = random.nextInt(requiredLevel / 2) + (requiredLevel / 2);
            String[] lowercaseWords = entry.getKey().toString().replace("Enchantment[minecraft:", "").replace(", ", "").replace(entry.getKey().getName(), "").replace("]", "").replace("_", " ").split(" ");
            List<String> uppercaseWords = new ArrayList<>();
            for (String s : lowercaseWords) {
                uppercaseWords.add(s.replaceFirst(s.charAt(0) + "", Character.toUpperCase(s.charAt(0)) + ""));
            }
            list.add(ChatColor.GRAY + String.join(" ", uppercaseWords) + " " + myFormat.format(randomLevel));
            int copy = requiredLevel;
            if (copy > 32767) {
                copy = 32767;
            }
            entry.setValue(copy);
        }
        ItemMeta meta = event.getItem().getItemMeta();
        meta.setLore(list);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        event.getItem().setItemMeta(meta);
        event.setExpLevelCost(requiredLevel);
    }

    public Integer getNearbyBooksAndOres(Block block, int range) {
        int blocksAndOres = 0;

        int firstx = block.getLocation().getBlockX() - range;
        int firsty = block.getLocation().getBlockY() - range;
        int firstz = block.getLocation().getBlockZ() - range;

        int secondx = block.getLocation().getBlockX() + range;
        int secondy = block.getLocation().getBlockY() + range;
        int secondz = block.getLocation().getBlockZ() + range;

        for (int x = firstx; x < secondx; x++) {
            for (int y = firsty; y < secondy; y++) {
                for (int z = firstz; z < secondz; z++) {
                    if (ALLOWED_BLOCKS.contains(block.getWorld().getBlockAt(x,y,z).getType())){
                        blocksAndOres++;
                    }
                }
            }
        }
        return blocksAndOres;
    }
}
