package jp.mrik.inventoryiteminterface;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class I3Util {

    public static I3Instance getCustomSizeInv(String name,int size){
        I3Instance inv = new I3Instance(name,size);
        inv.fillInv(I3C.create(Material.BLACK_STAINED_GLASS_PANE).setDisplay(" ").getI3Item());
        inv.setClickCancel(true);
        return inv;
    }

    public static I3Instance getCustomSizeInv(Player p, I3Instance inv, String name, int size){
        if(inv==null||inv.getSize()!=size){
            return getCustomSizeInv(name,size);
        }
        inv.clearEvent();
        inv.updateTitle(p,name);
        inv.fillInv(I3C.create(Material.BLACK_STAINED_GLASS_PANE).setDisplay(" ").getI3Item());
        inv.setClickCancel(true);
        return inv;
    }

    public static I3Instance get3_9Inv(String name){
        I3Instance inv = new I3Instance(name,27);
        inv.fillInv(I3C.create(Material.BLACK_STAINED_GLASS_PANE).setDisplay(" ").getI3Item());
        inv.setClickCancel(true);
        return inv;
    }

    public static I3Instance get3_9Inv(Player p, I3Instance inv, String name){
        if(inv==null||inv.getSize()!=27){
            return get3_9Inv(name);
        }
        inv.clearEvent();
        inv.updateTitle(p,name);
        inv.fillInv(I3C.create(Material.BLACK_STAINED_GLASS_PANE).setDisplay(" ").getI3Item());
        inv.setClickCancel(true);
        return inv;
    }

    public static I3Instance getOnReturn3_9Inv(Player p, String name,String backUnique){
        I3Instance inv = get3_9Inv(name);
        I3Item i3 = I3C.create(Material.DARK_OAK_DOOR).setDisplay("§c§l戻る").setLore("§e前のページに戻ります。").getI3Item();
        i3.addClickEvent(event -> {
            p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK,1.0f,1.0f);
            I3API.openInv(backUnique,p,inv);
        });
        inv.setItem(18,i3);
        return inv;
    }

    public static I3Instance getOnReturn3_9Inv(Player p, I3Instance invs, String name, String backUnique){
        I3Instance inv = get3_9Inv(p,invs,name);
        I3Item i3 = I3C.create(Material.DARK_OAK_DOOR).setDisplay("§c§l戻る").setLore("§e前のページに戻ります。").getI3Item();
        i3.addClickEvent(event -> {
            p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK,1.0f,1.0f);
            I3API.openInv(backUnique,p,inv);
        });
        inv.setItem(18,i3);
        return inv;
    }

    public static I3Instance get6_9Inv(String name){
        I3Instance inv = new I3Instance(name,54);
        inv.fillInv(I3C.create(Material.BLACK_STAINED_GLASS_PANE).setDisplay(" ").getI3Item());
        inv.setClickCancel(true);
        return inv;
    }

    public static I3Instance get6_9Inv(Player p, I3Instance inv, String name){
        if(inv==null||inv.getSize()!=54){
            return get6_9Inv(name);
        }
        inv.clearEvent();
        inv.updateTitle(p,name);
        inv.fillInv(I3C.create(Material.BLACK_STAINED_GLASS_PANE).setDisplay(" ").getI3Item());
        inv.setClickCancel(true);
        return inv;
    }

    public static I3IList get6_9ListInv(Player p, I3Instance inv, String name){
        if(inv==null||inv.getSize()!=54||!(inv instanceof I3IList)){
            return new I3IList(name, 0);
        }
        I3IList in = (I3IList)inv;
        in.clearEvent();
        in.updateTitle(p,name);
        in.setClickCancel(true);
        return in;
    }

    public static I3IList getListInv(Player p, I3Instance invs, String name, int page, int max, String listUnique, String backUnique){
        I3IList inv = get6_9ListInv(p, invs, name+" Page:"+page);
        inv.clearInv();
        I3Item wall = I3C.create(Material.BLACK_STAINED_GLASS_PANE).setDisplay(" ").getI3Item();
        if(page>0){
            I3Item back = I3C.create(Material.WHITE_STAINED_GLASS_PANE).setDisplay("§f§l§o前のページへ").getI3Item();
            back.addClickEvent(event -> I3API.openInv(listUnique,p,inv,(page-1)+""));
            inv.setItems(back,45,46);
        }else{
            inv.setItems(wall,45,46);
        }
        if(page<max){
            I3Item walk = I3C.create(Material.WHITE_STAINED_GLASS_PANE).setDisplay("§f§l§o次のページへ").getI3Item();
            walk.addClickEvent(event -> I3API.openInv(listUnique,p,inv,(page+1)+""));
            inv.setItems(walk,52,53);
        }else{
            inv.setItems(wall,52,53);
        }
        inv.setItems(wall,47,48,50,51);
        I3Item i3 = I3C.create(Material.DARK_OAK_DOOR).setDisplay("§c§l戻る").setLore("§e前のページに戻ります。").getI3Item();
        i3.addClickEvent(event -> {
            p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK,1.0f,1.0f);
            I3API.openInv(backUnique,p,inv);
        });
        inv.setItem(49, i3);
        inv.setClickCancel(true);
        return inv;
    }

}
