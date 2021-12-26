package jp.mrik.inventoryiteminterface;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class I3Util {

    public static String[] lore(String... lore){
        return lore;
    }

    public static String getItemName(ItemStack item){
        if(!item.hasItemMeta()||!item.getItemMeta().hasDisplayName()){
            return item.getType().name();
        }
        return item.getItemMeta().getDisplayName();
    }

    public static ItemStack createItem(String name, String[] lore, Material item, int dura, boolean enchant,boolean unbreakable){
        ItemStack items = new ItemStack(item,1);
        ItemMeta meta = items.getItemMeta();
        if (meta instanceof Damageable){
            ((Damageable) meta).setDamage(dura);
        }
        meta.setLore(Arrays.asList(lore));
        meta.setDisplayName(name);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        if(enchant){
            meta.addEnchant(Enchantment.ARROW_FIRE,1,true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        if(unbreakable){
            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        }
        items.setItemMeta(meta);
        return items;
    }

    public static ItemStack createItem(String name, String[] lore, Material item, int dura, boolean enchant,boolean unbreakable,int customModel){
        ItemStack items = new ItemStack(item,1);
        ItemMeta meta = items.getItemMeta();
        if (meta instanceof Damageable){
            ((Damageable) meta).setDamage(dura);
        }
        meta.setCustomModelData(customModel);
        meta.setLore(Arrays.asList(lore));
        meta.setDisplayName(name);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        if(enchant){
            meta.addEnchant(Enchantment.ARROW_FIRE,1,true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        if(unbreakable){
            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        }
        items.setItemMeta(meta);
        return items;
    }

    public static ItemStack overhaulItem(ItemStack item,String name, String[] lore){
        ItemMeta meta = item.getItemMeta();
        if(lore!=null){
            meta.setLore(Arrays.asList(lore));
        }
        if(name!=null){
            meta.setDisplayName(name);
        }
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createBannerItem(Material banner_mt, List<Pattern> patterns){
        ItemStack banner = new ItemStack(banner_mt);
        if(banner.getItemMeta() instanceof BannerMeta){
            BannerMeta meta = (BannerMeta)banner.getItemMeta();
            for(Pattern pat : patterns){
                meta.addPattern(pat);
            }
            meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            banner.setItemMeta(meta);
            return banner;
        }
        return null;
    }

    public static ItemStack createSkullItem(String name, String[] lore, UUID playeruuid, boolean enchant){
        ItemStack items = new ItemStack(Material.PLAYER_HEAD,1);
        SkullMeta meta = (SkullMeta) items.getItemMeta();
        meta.setLore(Arrays.asList(lore));
        meta.setDisplayName(name);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        if(enchant){
            meta.addEnchant(Enchantment.ARROW_FIRE,1,true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        meta.setUnbreakable(true);
        meta.setOwningPlayer(Bukkit.getPlayer(playeruuid));
        items.setItemMeta(meta);
        return items;
    }

    public static I3Instance get3_9Inv(String name){
        I3Instance inv = new I3Instance(name,27);
        inv.fillInv(new I3Item(createItem(" ", new String[]{}, Material.BLACK_STAINED_GLASS_PANE, 0, false,true)));
        inv.setClickCancel(true);
        return inv;
    }

    public static I3Instance get3_9Inv(Player p, String name, I3Instance inv){
        if(inv.getSize()!=27){
            return null;
        }
        inv.updateTitle(p,name);
        inv.fillInv(new I3Item(createItem(" ", new String[]{}, Material.BLACK_STAINED_GLASS_PANE, 0, false,true)));
        inv.setClickCancel(true);
        return inv;
    }



}
