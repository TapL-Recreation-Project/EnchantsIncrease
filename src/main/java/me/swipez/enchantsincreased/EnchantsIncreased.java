package me.swipez.enchantsincreased;

import me.swipez.enchantsincreased.items.ItemManager;
import me.swipez.enchantsincreased.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnchantsIncreased extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new AnvilPrepareListener(this), this);
        getServer().getPluginManager().registerEvents(new KillAbundanceListener(), this);
        getServer().getPluginManager().registerEvents(new EnchantmentListener(), this);
        getServer().getPluginManager().registerEvents(new EnchantmentExpanderListener(this), this);
        getServer().getPluginManager().registerEvents(new InteractListener(this), this);
        registerRecipe(this, "super_wheat_recipe", ItemManager.SUPER_WHEAT, Material.WHEAT);
        registerRecipe(this, "sugar_meal_recipe", ItemManager.SUGAR_MEAL, Material.SUGAR);
        saveDefaultConfig();
        getConfig().options().copyDefaults();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private static void registerRecipe(EnchantsIncreased plugin, String id, ItemStack cobblestoneResult, Material middleIngredient) {
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, id), cobblestoneResult)
                .shape("CCC", "CGC", "CCC")
                .setIngredient('C', new RecipeChoice.MaterialChoice(Material.BONE_MEAL))
                .setIngredient('G', new RecipeChoice.MaterialChoice(middleIngredient));
        Bukkit.addRecipe(recipe);
    }
}
