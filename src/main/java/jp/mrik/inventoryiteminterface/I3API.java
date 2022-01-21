package jp.mrik.inventoryiteminterface;

import jp.mrik.inventoryiteminterface.rename.UpdateTitle;
import jp.mrik.inventoryiteminterface.rename.UpdateTitle_1_16_5;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.function.Function;

public class I3API {

    public final static UpdateTitle updateTitle = new UpdateTitle_1_16_5();

    private static final HashMap<String, Function<I3Executer,I3Instance>> inventorys = new HashMap<>();

    public static boolean registerInv(String uniqueName,Function<I3Executer,I3Instance> inv){
        if(inventorys.containsKey(uniqueName)||uniqueName.equals("close")){
            return false;
        }
        inventorys.put(uniqueName,inv);
        return true;
    }

    public static void closeInv(Player p){
        Bukkit.getScheduler().runTask(InventoryItemInterface.plugin, (Runnable) p::closeInventory);
    }

    public static void openInv(String uniqueName, Player p, I3Instance inv){
        if(uniqueName.equals("close")){
            Bukkit.getScheduler().runTask(InventoryItemInterface.plugin, (Runnable) p::closeInventory);
            return;
        }
        if(inventorys.containsKey(uniqueName)){
            I3Instance result = inventorys.get(uniqueName).apply(new I3Executer(p,inv));

            if(inv==null||inv.getSize()!=result.getSize()){
                result.openInv(p);
            }else{
                result.updateInv(p);
            }
        }
    }

    public static void openInv(String uniqueName, Player p, I3Instance inv,String... args){
        if(uniqueName.equals("close")){
            Bukkit.getScheduler().runTask(InventoryItemInterface.plugin, (Runnable) p::closeInventory);
            return;
        }
        if(inventorys.containsKey(uniqueName)){
            I3Instance result = inventorys.get(uniqueName).apply(new I3Executer(p,inv,args));

            if(inv==null||inv.getSize()!=result.getSize()){
                result.openInv(p);
            }else{
                result.updateInv(p);
            }
        }
    }
}
