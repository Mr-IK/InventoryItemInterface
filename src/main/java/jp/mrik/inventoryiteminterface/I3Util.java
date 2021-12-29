package jp.mrik.inventoryiteminterface;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
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

    public static I3Instance get3_9Inv(Player p,I3Instance inv, String name){
        if(inv.getSize()!=27){
            return get3_9Inv(name);
        }
        inv.clearEvent();
        inv.updateTitle(p,name);
        inv.fillInv(new I3Item(createItem(" ", new String[]{}, Material.BLACK_STAINED_GLASS_PANE, 0, false,true)));
        inv.setClickCancel(true);
        return inv;
    }

    public static I3Instance getOnReturn3_9Inv(Player p,String name,String backUnique,String backInv,String[] args){
        I3Instance inv = get3_9Inv(name);
        ItemStack item = I3Util.createItem("§c§l戻る",lore("§e前のページに戻ります。"),
                Material.DARK_OAK_DOOR,0,false,true);
        I3Item i3 = new I3Item(item);
        i3.addClickEvent(event -> I3API.openInv(backUnique,p,inv,backInv,args));
        inv.setItem(18,i3);
        return inv;
    }

    public static I3Instance getOnReturn3_9Inv(Player p,I3Instance invs,String name,String backUnique,String backInv,String[] args){
        I3Instance inv = get3_9Inv(p,invs,name);
        ItemStack item = I3Util.createItem("§c§l戻る",lore("§e前のページに戻ります。"),
                Material.DARK_OAK_DOOR,0,false,true);
        I3Item i3 = new I3Item(item);
        i3.addClickEvent(event -> {
            I3API.openInv(backUnique,p,inv,backInv,args);
        });
        inv.setItem(18,i3);
        return inv;
    }

    public static I3Instance get6_9Inv(String name){
        I3Instance inv = new I3Instance(name,54);
        inv.fillInv(new I3Item(createItem(" ", new String[]{}, Material.BLACK_STAINED_GLASS_PANE, 0, false,true)));
        inv.setClickCancel(true);
        return inv;
    }

    public static I3Instance get6_9Inv(Player p,I3Instance inv, String name){
        if(inv.getSize()!=54){
            return get6_9Inv(name);
        }
        inv.clearEvent();
        inv.updateTitle(p,name);
        inv.fillInv(new I3Item(createItem(" ", new String[]{}, Material.BLACK_STAINED_GLASS_PANE, 0, false,true)));
        inv.setClickCancel(true);
        return inv;
    }

    public static I3Instance getListupInv(String name,int page,int max){
        I3Instance inv = get6_9Inv(name+" Page:"+page);
        inv.clearInv();
        ItemStack wall = createItem(" ",lore(), Material.BLACK_STAINED_GLASS_PANE,0,false,true);
        if(page>0){
            ItemStack back = createItem("§f§l§o前のページへ",lore(), Material.WHITE_STAINED_GLASS_PANE,0,false,true);
            inv.setItems(new int[]{45,46},new I3Item(back));
        }else{
            inv.setItems(new int[]{45,46},new I3Item(wall));
        }
        if(page<max){
            ItemStack walk = createItem("§f§l§o次のページへ",lore(), Material.WHITE_STAINED_GLASS_PANE,0,false,true);
            inv.setItems(new int[]{52,53},new I3Item(walk));
        }else{
            inv.setItems(new int[]{52,53},new I3Item(wall));
        }
        inv.setItems(new int[]{47,48,50,51},new I3Item(wall));
        inv.setItem(49, new I3Item(createItem("§c§l戻る",lore("§e前のページに戻ります。"),
                Material.DARK_OAK_DOOR,0,false,true)));
        return inv;
    }

    public static I3Instance getListupInv(Player p, I3Instance invs,String name,int page,int max){
        I3Instance inv = get6_9Inv(p,invs,name+" Page:"+page);
        inv.clearInv();
        ItemStack wall = createItem(" ",lore(), Material.BLACK_STAINED_GLASS_PANE,0,false,true);
        if(page>0){
            ItemStack back = createItem("§f§l§o前のページへ",lore(), Material.WHITE_STAINED_GLASS_PANE,0,false,true);
            inv.setItems(new int[]{45,46},new I3Item(back));
        }else{
            inv.setItems(new int[]{45,46},new I3Item(wall));
        }
        if(page<max){
            ItemStack walk = createItem("§f§l§o次のページへ",lore(), Material.WHITE_STAINED_GLASS_PANE,0,false,true);
            inv.setItems(new int[]{52,53},new I3Item(walk));
        }else{
            inv.setItems(new int[]{52,53},new I3Item(wall));
        }
        inv.setItems(new int[]{47,48,50,51},new I3Item(wall));
        inv.setItem(49, new I3Item(createItem("§c§l戻る",lore("§e前のページに戻ります。"),
                Material.DARK_OAK_DOOR,0,false,true)));
        return inv;
    }

}
