package me.swipez.enchantsincreased.listeners;

import me.swipez.enchantsincreased.EnchantsIncreased;
import me.swipez.enchantsincreased.items.ItemManager;
import me.swipez.enchantsincreased.stored.StoredCows;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Breedable;
import org.bukkit.entity.Cow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class InteractListener implements Listener {

    EnchantsIncreased plugin;

    public InteractListener(EnchantsIncreased plugin) {
        this.plugin = plugin;
    }

    ItemStack storedItem;

    @EventHandler
    public void onPlayerInteract(PlayerInteractAtEntityEvent event){
        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (itemStack.getItemMeta() != null){
            if (itemStack.isSimilar(ItemManager.SUPER_WHEAT)){
                if (event.getRightClicked() instanceof Cow){
                    Breedable breedable = (Breedable) event.getRightClicked();
                    if (breedable.canBreed() && !StoredCows.allSpecialCows.contains(breedable.getUniqueId())){
                        StoredCows.allSpecialCows.add(breedable.getUniqueId());
                        itemStack.setType(Material.WHEAT);
                        storedItem = itemStack.clone();
                        BukkitTask task = new BukkitRunnable() {
                            @Override
                            public void run() {
                                itemStack.setType(Material.HAY_BLOCK);
                                itemStack.setAmount(itemStack.getAmount()-1);
                            }
                        }.runTaskLater(plugin, 1);
                    }
                }
            }
        }
    }
    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            return;
        }
        if (itemStack.getItemMeta() != null) {
            if (itemStack.isSimilar(ItemManager.SUGAR_MEAL)){
                if (event.getClickedBlock().getType().equals(Material.SUGAR_CANE)){
                    if (!event.getClickedBlock().getRelative(BlockFace.UP).getType().equals(Material.AIR)){
                        return;
                    }
                    itemStack.setAmount(itemStack.getAmount()-1);
                    for (int i = 0; i < 100; i++){
                        int loops = i;
                        BukkitTask blockPlaceTask = new BukkitRunnable() {
                            Block block = event.getClickedBlock();
                            @Override
                            public void run() {
                                block = block.getWorld().getBlockAt(block.getLocation().add(0,loops,0));
                                if (block.getType().equals(Material.AIR)){
                                    block.setType(Material.SUGAR_CANE);
                                    player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 0.3F, 1);
                                }
                            }
                        }.runTaskLater(plugin, i*2);
                    }
                }
            }
        }
    }
    @EventHandler
    public void onMobsFlushed(EntityBreedEvent event){
        if (!(event.getBreeder() instanceof Player)){
            return;
        }
        LivingEntity mother = event.getMother();
        LivingEntity father = event.getFather();
        Player player = (Player) event.getBreeder();
        if (StoredCows.allSpecialCows.contains(mother.getUniqueId()) || StoredCows.allSpecialCows.contains(father.getUniqueId())){
            Breedable breedable = (Breedable) event.getEntity();
            breedable.setBreed(true);
            for (int i = 0; i < 6; i++){
                mother.getWorld().spawnEntity(mother.getLocation(), mother.getType());
                BukkitTask plopSound = new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);
                    }
                }.runTaskLater(plugin, i*3);
            }
            StoredCows.allSpecialCows.remove(mother.getUniqueId());
            StoredCows.allSpecialCows.remove(father.getUniqueId());
            Breedable motherBreedable = (Breedable) mother;
            Breedable fatherBreedable = (Breedable) father;
            motherBreedable.setBreed(true);
            fatherBreedable.setBreed(true);
        }
    }
}
