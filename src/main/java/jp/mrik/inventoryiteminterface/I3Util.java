package jp.mrik.inventoryiteminterface;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class I3Util {

    public static I3Instance get3_9Inv(String name){
        I3Instance inv = new I3Instance(name,27);
        inv.fillInv(I3C.create(Material.BLACK_STAINED_GLASS_PANE).setDisplay(" ").getI3Item());
        inv.setClickCancel(true);
        return inv;
    }

    public static I3Instance get3_9Inv(Player p,I3Instance inv, String name){
        if(inv==null||inv.getSize()!=27){
            return get3_9Inv(name);
        }
        inv.clearEvent();
        inv.updateTitle(p,name);
        inv.fillInv(I3C.create(Material.BLACK_STAINED_GLASS_PANE).setDisplay(" ").getI3Item());
        inv.setClickCancel(true);
        return inv;
    }

    public static I3Instance getOnReturn3_9Inv(Player p,String name,String backUnique){
        I3Instance inv = get3_9Inv(name);
        I3Item i3 = I3C.create(Material.DARK_OAK_DOOR).setDisplay("§c§l戻る").setLore("§e前のページに戻ります。").getI3Item();
        i3.addClickEvent(event -> I3API.openInv(backUnique,p,inv));
        inv.setItem(18,i3);
        return inv;
    }

    public static I3Instance getOnReturn3_9Inv(Player p,I3Instance invs,String name,String backUnique){
        I3Instance inv = get3_9Inv(p,invs,name);
        I3Item i3 = I3C.create(Material.DARK_OAK_DOOR).setDisplay("§c§l戻る").setLore("§e前のページに戻ります。").getI3Item();
        i3.addClickEvent(event -> {
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

    public static I3Instance get6_9Inv(Player p,I3Instance inv, String name){
        if(inv==null||inv.getSize()!=54){
            return get6_9Inv(name);
        }
        inv.clearEvent();
        inv.updateTitle(p,name);
        inv.fillInv(I3C.create(Material.BLACK_STAINED_GLASS_PANE).setDisplay(" ").getI3Item());
        inv.setClickCancel(true);
        return inv;
    }

    public static I3Instance getListInv(Player p, I3Instance invs,String name,int page,int max,String listUnique,String backUnique){
        I3Instance inv = get6_9Inv(p,invs,name+" Page:"+page);
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
        i3.addClickEvent(event -> I3API.openInv(backUnique,p,inv));
        inv.setItem(49, i3);
        inv.setClickCancel(true);
        return inv;
    }

}
