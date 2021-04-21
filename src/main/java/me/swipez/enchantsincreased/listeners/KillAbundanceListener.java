package me.swipez.enchantsincreased.listeners;

import me.swipez.enchantsincreased.enchants.CustomEnchantManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class KillAbundanceListener implements Listener {

    @EventHandler
    public void onPlayerKillsMob(EntityDeathEvent event){
        if (event.getEntity().getKiller() != null){
            Player player = event.getEntity().getKiller();
            int xpMultiplier = 0;
            ItemStack itemStack = player.getInventory().getItemInMainHand();
            if (CustomEnchantManager.hasAbundanceLevel(itemStack, 1)){
                xpMultiplier = 3;
            }
            if (CustomEnchantManager.hasAbundanceLevel(itemStack, 2)){
                xpMultiplier = 19;
            }
            if (CustomEnchantManager.hasAbundanceLevel(itemStack, 3)){
                xpMultiplier = 188;
            }
            if (CustomEnchantManager.hasAbundanceLevel(itemStack, 4)){
                xpMultiplier = 1786;
            }
            if (CustomEnchantManager.hasAbundanceLevel(itemStack, 5)){
                xpMultiplier = 27129;
            }
            if (CustomEnchantManager.hasAbundanceLevel(itemStack, 6)){
                xpMultiplier = 266768;
            }
            if (CustomEnchantManager.hasAbundanceLevel(itemStack, 7)){
                xpMultiplier = 4483259;
            }
            if (CustomEnchantManager.hasAbundanceLevel(itemStack, 8)){
                xpMultiplier = 75094588;
            }
            if (CustomEnchantManager.hasAbundanceLevel(itemStack, 9)){
                xpMultiplier = 1257834349;
            }
            player.setLevel(player.getLevel() + xpMultiplier);
            if (xpMultiplier > 0){
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.3F, 1);
            }
        }
    }
    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event){
        if (event.getExpToDrop() > 0){
            Player player = event.getPlayer();
            int xpMultiplier = 0;
            ItemStack itemStack = player.getInventory().getItemInMainHand();
            if (CustomEnchantManager.hasAbundanceLevel(itemStack, 1)){
                xpMultiplier = 3;
            }
            if (CustomEnchantManager.hasAbundanceLevel(itemStack, 2)){
                xpMultiplier = 19;
            }
            if (CustomEnchantManager.hasAbundanceLevel(itemStack, 3)){
                xpMultiplier = 188;
            }
            if (CustomEnchantManager.hasAbundanceLevel(itemStack, 4)){
                xpMultiplier = 1786;
            }
            if (CustomEnchantManager.hasAbundanceLevel(itemStack, 5)){
                xpMultiplier = 27129;
            }
            if (CustomEnchantManager.hasAbundanceLevel(itemStack, 6)){
                xpMultiplier = 266768;
            }
            if (CustomEnchantManager.hasAbundanceLevel(itemStack, 7)){
                xpMultiplier = 4483259;
            }
            if (CustomEnchantManager.hasAbundanceLevel(itemStack, 8)){
                xpMultiplier = 75094588;
            }
            if (CustomEnchantManager.hasAbundanceLevel(itemStack, 9)){
                xpMultiplier = 1257834349;
            }
            player.setLevel(player.getLevel() + xpMultiplier);
            if (xpMultiplier > 0){
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.3F, 1);
            }
        }
    }
}
