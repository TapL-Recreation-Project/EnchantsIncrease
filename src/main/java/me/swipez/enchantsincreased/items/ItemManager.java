package me.swipez.enchantsincreased.items;

import me.swipez.enchantsincreased.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class ItemManager {

    public static ItemStack SUGAR_MEAL = ItemBuilder.of(Material.SUGAR)
            .name(ChatColor.LIGHT_PURPLE+"Sugar Meal")
            .lore(ChatColor.GRAY+"Right click on sugar cane to make it groooow!")
            .enchantment(Enchantment.CHANNELING, 1)
            .flags(ItemFlag.HIDE_ENCHANTS)
            .build();

    public static ItemStack SUPER_WHEAT = ItemBuilder.of(Material.HAY_BLOCK)
            .name(ChatColor.GOLD+"Super Wheat")
            .lore(ChatColor.GRAY+"Right click two cows to make them have babies... A LOT of babies.")
            .enchantment(Enchantment.CHANNELING, 1)
            .flags(ItemFlag.HIDE_ENCHANTS)
            .build();
}
