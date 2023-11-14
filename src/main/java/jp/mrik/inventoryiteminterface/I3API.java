package jp.mrik.inventoryiteminterface;

import jp.mrik.timerthreadtools.T3Util;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.function.Function;

public class I3API {

    private static final HashMap<String, Function<I3Executer,I3Instance>> inventorys = new HashMap<>();

    public static boolean registerInv(String uniqueName,Function<I3Executer,I3Instance> inv){
        if(inventorys.containsKey(uniqueName)||uniqueName.equals("close")){
            return false;
        }
        inventorys.put(uniqueName,inv);
        return true;
    }

    public static void closeInv(Player p){
        T3Util.sync(p::closeInventory);
    }

    public static void openInv(String uniqueName, Player p, I3Instance inv){
        if(uniqueName.equals("close")){
            T3Util.sync(p::closeInventory);
            return;
        }
        if(inventorys.containsKey(uniqueName)){
            I3Instance result = inventorys.get(uniqueName).apply(new I3Executer(p,inv));

            if(result==null){
                return;
            }

            if(inv==null||inv.getSize()!=result.getSize()){
                result.openInv(p);
            }else{
                result.updateInv(p);
            }
        }
    }

    public static void openInv(String uniqueName, Player p, I3Instance inv,String... args){
        if(uniqueName.equals("close")){
            T3Util.sync(p::closeInventory);
            return;
        }
        if(inventorys.containsKey(uniqueName)){
            I3Instance result = inventorys.get(uniqueName).apply(new I3Executer(p,inv,args));

            if(result==null){
                return;
            }

            if(inv==null||inv.getSize()!=result.getSize()){
                result.openInv(p);
            }else{
                result.updateInv(p);
            }
        }
    }
}
