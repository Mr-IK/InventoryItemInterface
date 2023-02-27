package jp.mrik.inventoryiteminterface.custom;

import jp.mrik.inventoryiteminterface.I3C;
import jp.mrik.inventoryiteminterface.I3Instance;
import jp.mrik.inventoryiteminterface.I3Item;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;

import java.util.HashMap;
import java.util.function.Consumer;

public class NumberInputer {

    private static HashMap<Integer, I3Item> banner = new HashMap<>();

    public static void init(){
        banner.put(0,I3C.create(Material.WHITE_BANNER).setBannerPattern(
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_DOWNLEFT),
                new Pattern(DyeColor.WHITE, PatternType.BORDER)
        ).setDisplay("0").getI3Item());
        banner.put(1,I3C.create(Material.WHITE_BANNER).setBannerPattern(
                new Pattern(DyeColor.BLACK, PatternType.SQUARE_TOP_LEFT),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_CENTER),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM),
                new Pattern(DyeColor.WHITE, PatternType.BORDER)
        ).setDisplay("1").getI3Item());
        banner.put(2,I3C.create(Material.WHITE_BANNER).setBannerPattern(
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP),
                new Pattern(DyeColor.WHITE, PatternType.RHOMBUS_MIDDLE),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_DOWNLEFT),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM),
                new Pattern(DyeColor.WHITE, PatternType.BORDER)
        ).setDisplay("2").getI3Item());
        banner.put(3,I3C.create(Material.WHITE_BANNER).setBannerPattern(
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_MIDDLE),
                new Pattern(DyeColor.WHITE, PatternType.STRIPE_LEFT),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP),
                new Pattern(DyeColor.WHITE, PatternType.BORDER)
        ).setDisplay("3").getI3Item());
        banner.put(4,I3C.create(Material.WHITE_BANNER).setBannerPattern(
                new Pattern(DyeColor.WHITE, PatternType.HALF_HORIZONTAL),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT),
                new Pattern(DyeColor.WHITE, PatternType.STRIPE_BOTTOM),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_MIDDLE),
                new Pattern(DyeColor.WHITE, PatternType.BORDER)
        ).setDisplay("4").getI3Item());
        banner.put(5,I3C.create(Material.WHITE_BANNER).setBannerPattern(
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_DOWNRIGHT),
                new Pattern(DyeColor.WHITE, PatternType.CURLY_BORDER),
                new Pattern(DyeColor.BLACK, PatternType.SQUARE_BOTTOM_LEFT),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP),
                new Pattern(DyeColor.WHITE, PatternType.BORDER)
        ).setDisplay("5").getI3Item());
        banner.put(6,I3C.create(Material.BLACK_BANNER).setBannerPattern(
                new Pattern(DyeColor.WHITE, PatternType.HALF_HORIZONTAL_MIRROR),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT),
                new Pattern(DyeColor.WHITE, PatternType.STRIPE_TOP),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT),
                new Pattern(DyeColor.WHITE, PatternType.BORDER)
        ).setDisplay("6").getI3Item());
        banner.put(7,I3C.create(Material.WHITE_BANNER).setBannerPattern(
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP),
                new Pattern(DyeColor.WHITE, PatternType.DIAGONAL_RIGHT),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_DOWNLEFT),
                new Pattern(DyeColor.BLACK, PatternType.SQUARE_BOTTOM_LEFT),
                new Pattern(DyeColor.WHITE, PatternType.BORDER)
        ).setDisplay("7").getI3Item());
        banner.put(8,I3C.create(Material.WHITE_BANNER).setBannerPattern(
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_MIDDLE),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM),
                new Pattern(DyeColor.WHITE, PatternType.BORDER)
        ).setDisplay("8").getI3Item());
        banner.put(9,I3C.create(Material.BLACK_BANNER).setBannerPattern(
                new Pattern(DyeColor.WHITE, PatternType.HALF_HORIZONTAL),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT),
                new Pattern(DyeColor.WHITE, PatternType.STRIPE_BOTTOM),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_TOP),
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_RIGHT),
                new Pattern(DyeColor.WHITE, PatternType.BORDER)
        ).setDisplay("9").getI3Item());
    }

    public static I3Item getBannerI3(int id){
        return banner.get(id).getCopy();
    }

    public static I3Item getBannerI3(I3Item item,int edit){
        int i = Integer.parseInt(item.getItem().getItemMeta().getDisplayName());
        i = i + edit;
        if(i<0){
            i = i+10;
        }else if(i>9){
            i = i-10;
        }
        return getBannerI3(i);
    }

    public static I3Instance changeToNII(I3Instance inv, Consumer<Integer> result){
        inv.clearInv();
        inv.clearEvent();
        inv.setClickCancel(true);
        inv.fillInv(I3C.create(Material.BLACK_STAINED_GLASS_PANE).setDisplay(" ").getI3Item());
        I3Item base_minus = I3C.create(Material.WHITE_BANNER).setBannerPattern(
                new Pattern(DyeColor.BLACK, PatternType.STRIPE_MIDDLE),new Pattern(DyeColor.WHITE, PatternType.BORDER))
                .setDisplay("§7§l数字を減らす").setLore("§e通常クリックで-1、シフトクリックで-5します").getI3Item();
        I3Item base_plus = I3C.create(Material.WHITE_BANNER).setBannerPattern(
                        new Pattern(DyeColor.BLACK, PatternType.STRAIGHT_CROSS),new Pattern(DyeColor.WHITE, PatternType.BORDER),
                        new Pattern(DyeColor.WHITE, PatternType.STRIPE_BOTTOM),new Pattern(DyeColor.WHITE, PatternType.STRIPE_TOP))
                .setDisplay("§a§l数字を増やす").setLore("§e通常クリックで+1、シフトクリックで+5します").getI3Item();
        for(int i = 9;i<15;i++) {
            int num = i+9;

            I3Item plus = base_plus.getCopy();
            plus.addClickEvent(event -> {
                if(event.isShiftClick()){
                    inv.setItem(num,getBannerI3(inv.getI3Item(num),5));
                }else{
                    inv.setItem(num,getBannerI3(inv.getI3Item(num),1));
                }
            });

            inv.setItem(i,plus);
            inv.setItem(num,getBannerI3(0));

            I3Item minus = base_minus.getCopy();
            minus.addClickEvent(event -> {
                if(event.isShiftClick()){
                    inv.setItem(num,getBannerI3(inv.getI3Item(num),-5));
                }else{
                    inv.setItem(num,getBannerI3(inv.getI3Item(num),-1));
                }
            });
            inv.setItem(i+18,minus);
        }

        I3Item ok = I3C.create(Material.GREEN_STAINED_GLASS_PANE).setDisplay("§a§l完了").getI3Item();
        ok.addClickEvent(event -> {
            int a = Integer.parseInt(inv.getItem(18).getItemMeta().getDisplayName())*100000;
            int b = Integer.parseInt(inv.getItem(19).getItemMeta().getDisplayName())*10000;
            int c = Integer.parseInt(inv.getItem(20).getItemMeta().getDisplayName())*1000;
            int d = Integer.parseInt(inv.getItem(21).getItemMeta().getDisplayName())*100;
            int e = Integer.parseInt(inv.getItem(22).getItemMeta().getDisplayName())*10;
            int f = Integer.parseInt(inv.getItem(23).getItemMeta().getDisplayName());
            int res = a+b+c+d+e+f;
            result.accept(res);
        });
        inv.setItem(25,ok);
        return inv;
    }
}
