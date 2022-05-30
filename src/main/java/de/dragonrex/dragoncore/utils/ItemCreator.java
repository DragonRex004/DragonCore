package de.dragonrex.dragoncore.utils;

import de.dragonrex.dragoncore.DragonCore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;


public class ItemCreator {

    private ItemStack itemStack;
    private ItemMeta itemMeta;
    private DragonCore core = DragonCore.getInstance();

    public ItemCreator(Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public ItemCreator setName(String name) {
        this.itemMeta.setDisplayName(name);
        return this;
    }

    public ItemCreator setLocalizedName(String localizedName) {
        this.itemMeta.setLocalizedName(localizedName);
        return this;
    }

    public ItemCreator setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemCreator setLore(String ... lore) {
        this.itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemCreator setCustomModelData(int data) {
        this.itemMeta.setCustomModelData(data);
        return this;
    }

    public ItemCreator addEnchant(Enchantment enchantment, int level, boolean see) {
        itemMeta.addEnchant(enchantment, level, see);
        return this;
    }

    public ItemCreator setUnbreakable() {
        itemMeta.setUnbreakable(true);
        return this;
    }

    public ItemCreator addItemFlags(ItemFlag itemFlag) {
        itemMeta.addItemFlags(itemFlag);
        return this;
    }

    public ItemCreator setCustomCraftingShapedRecipe(HashMap<Integer, ItemStack> source, String key){
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(core, key), this.itemStack);
        shapedRecipe.shape("ABC","DEF","GHI");
        shapedRecipe.setIngredient('A', new RecipeChoice.ExactChoice(source.get(1)));
        shapedRecipe.setIngredient('B', new RecipeChoice.ExactChoice(source.get(2)));
        shapedRecipe.setIngredient('C', new RecipeChoice.ExactChoice(source.get(3)));
        shapedRecipe.setIngredient('D', new RecipeChoice.ExactChoice(source.get(4)));
        shapedRecipe.setIngredient('E', new RecipeChoice.ExactChoice(source.get(5)));
        shapedRecipe.setIngredient('F', new RecipeChoice.ExactChoice(source.get(6)));
        shapedRecipe.setIngredient('G', new RecipeChoice.ExactChoice(source.get(7)));
        shapedRecipe.setIngredient('H', new RecipeChoice.ExactChoice(source.get(8)));
        shapedRecipe.setIngredient('I', new RecipeChoice.ExactChoice(source.get(9)));
        Bukkit.addRecipe(shapedRecipe);
        return this;
    }

    public ItemCreator setCustomCraftingShapelessRecipe(HashMap<Integer, ItemStack> source, String key) {
        ShapelessRecipe shapelessRecipe = new ShapelessRecipe(new NamespacedKey(core, key), this.itemStack);
        shapelessRecipe.addIngredient(new RecipeChoice.ExactChoice(source.get(1)));
        shapelessRecipe.addIngredient(new RecipeChoice.ExactChoice(source.get(2)));
        shapelessRecipe.addIngredient(new RecipeChoice.ExactChoice(source.get(3)));
        shapelessRecipe.addIngredient(new RecipeChoice.ExactChoice(source.get(4)));
        shapelessRecipe.addIngredient(new RecipeChoice.ExactChoice(source.get(5)));
        shapelessRecipe.addIngredient(new RecipeChoice.ExactChoice(source.get(6)));
        shapelessRecipe.addIngredient(new RecipeChoice.ExactChoice(source.get(7)));
        shapelessRecipe.addIngredient(new RecipeChoice.ExactChoice(source.get(8)));
        shapelessRecipe.addIngredient(new RecipeChoice.ExactChoice(source.get(9)));
        Bukkit.addRecipe(shapelessRecipe);
        return this;
    }

    public ItemCreator setCraftingShapedRecipe(HashMap<Integer, Material> source, String key) {
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(core, key), this.itemStack);
        shapedRecipe.shape("ABC", "DEF", "GHI");
        shapedRecipe.setIngredient('A', source.get(1));
        shapedRecipe.setIngredient('B', source.get(2));
        shapedRecipe.setIngredient('C', source.get(3));
        shapedRecipe.setIngredient('D', source.get(4));
        shapedRecipe.setIngredient('E', source.get(5));
        shapedRecipe.setIngredient('F', source.get(6));
        shapedRecipe.setIngredient('G', source.get(7));
        shapedRecipe.setIngredient('H', source.get(8));
        shapedRecipe.setIngredient('I', source.get(9));
        Bukkit.addRecipe(shapedRecipe);
        return this;
    }

    public ItemCreator setCraftingShapelessRecipe(HashMap<Integer, Material> source, String key) {
        ShapelessRecipe shapelessRecipe = new ShapelessRecipe(new NamespacedKey(core, key), this.itemStack);
        shapelessRecipe.addIngredient(source.get(1));
        shapelessRecipe.addIngredient(source.get(2));
        shapelessRecipe.addIngredient(source.get(3));
        shapelessRecipe.addIngredient(source.get(4));
        shapelessRecipe.addIngredient(source.get(5));
        shapelessRecipe.addIngredient(source.get(6));
        shapelessRecipe.addIngredient(source.get(7));
        shapelessRecipe.addIngredient(source.get(8));
        shapelessRecipe.addIngredient(source.get(9));
        Bukkit.addRecipe(shapelessRecipe);
        return this;
    }

    public ItemCreator setFurnaceRecipe(Material source, int experience, int cookingTime, String key) {
        FurnaceRecipe furnaceRecipe = new FurnaceRecipe(new NamespacedKey(core, key), this.itemStack, source, experience, cookingTime);
        Bukkit.addRecipe(furnaceRecipe);
        return this;
    }

    public ItemCreator setCustomFurnaceRecipe(ItemStack source, int experience, int cookingTime, String key) {
        FurnaceRecipe furnaceRecipe = new FurnaceRecipe(new NamespacedKey(core, key), this.itemStack, new RecipeChoice.ExactChoice(source), experience, cookingTime);
        Bukkit.addRecipe(furnaceRecipe);
        return this;
    }

    public ItemStack buildItem() {
        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack;
    }
}
