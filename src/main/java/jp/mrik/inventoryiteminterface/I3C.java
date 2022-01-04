package jp.mrik.inventoryiteminterface;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.UUID;

public class I3C {

    public static I3C create(Material mt){
        return new I3C(mt);
    }

    public static I3C create(ItemStack base){
        return new I3C(base);
    }


    public static String getItemName(ItemStack item){
        if(!item.hasItemMeta()||!item.getItemMeta().hasDisplayName()){
            return item.getType().name();
        }
        return item.getItemMeta().getDisplayName();
    }

    private ItemStack item;

    private I3C(Material mt){
        item = new ItemStack(mt);
    }

    private I3C(ItemStack base){
        item = base.clone();
    }

    public I3C reset(Material mt){
        item = new ItemStack(mt);
        return this;
    }

    public I3C setAmount(int amount){
        ItemStack after = item.clone();
        after.setAmount(amount);
        item = after;
        return this;
    }

    public I3C setDurability(short durability){
        ItemStack after = item.clone();
        after.setDurability(durability);
        item = after;
        return this;
    }

    public I3C setDisplay(String name){
        ItemStack after = item.clone();
        ItemMeta meta = after.getItemMeta();
        meta.setDisplayName(name);
        after.setItemMeta(meta);
        item = after;
        return this;
    }

    public I3C setCModel(int customModel){
        ItemStack after = item.clone();
        ItemMeta meta = after.getItemMeta();
        meta.setCustomModelData(customModel);
        after.setItemMeta(meta);
        item = after;
        return this;
    }

    public I3C setLore(String... lore){
        ItemStack after = item.clone();
        ItemMeta meta = after.getItemMeta();
        meta.setLore(Arrays.asList(lore));
        after.setItemMeta(meta);
        item = after;
        return this;
    }

    public I3C setGlow(){
        ItemStack after = item.clone();
        ItemMeta meta = after.getItemMeta();
        meta.addEnchant(Enchantment.ARROW_FIRE,1,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        after.setItemMeta(meta);
        item = after;
        return this;
    }

    public I3C setUnbreakable(){
        ItemStack after = item.clone();
        ItemMeta meta = after.getItemMeta();
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        after.setItemMeta(meta);
        item = after;
        return this;
    }

    public I3C setBannerPattern(Pattern... patterns){
        if(item.getItemMeta() instanceof BannerMeta){
            BannerMeta meta = (BannerMeta)item.getItemMeta();
            for(Pattern pat : patterns){
                meta.addPattern(pat);
            }
            meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            item.setItemMeta(meta);
        }
        return this;
    }

    public I3C setSkullMeta(UUID owner){
        if(item.getItemMeta() instanceof SkullMeta){
            SkullMeta meta = (SkullMeta)item.getItemMeta();
            meta.setOwningPlayer(Bukkit.getOfflinePlayer(owner));
            item.setItemMeta(meta);
        }
        return this;
    }

    public ItemStack getItem(){
        return item;
    }

    public I3Item getI3Item(){
        return new I3Item(item);
    }
}
