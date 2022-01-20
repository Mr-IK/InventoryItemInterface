package jp.mrik.inventoryiteminterface;

import jp.mrik.inventoryiteminterface.rename.UpdateTitle;
import jp.mrik.inventoryiteminterface.rename.UpdateTitle_1_16_5;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.function.Consumer;

public class I3API {

    public final static UpdateTitle updateTitle = new UpdateTitle_1_16_5();

    private static final HashMap<String, Consumer<I3Executer>> inventorys = new HashMap<>();

    public static boolean registerInv(String uniqueName,Consumer<I3Executer> inv){
        if(inventorys.containsKey(uniqueName)||uniqueName.equals("close")){
            return false;
        }
        inventorys.put(uniqueName,inv);
        return true;
    }

    public static void openInv(String uniqueName, Player p, I3Instance inv){
        if(uniqueName.equals("close")){
            Bukkit.getScheduler().runTask(InventoryItemInterface.plugin, (Runnable) p::closeInventory);
            return;
        }
        if(inventorys.containsKey(uniqueName)){
            inventorys.get(uniqueName).accept(new I3Executer(p,inv));
        }
    }

    public static void openInv(String uniqueName, Player p, I3Instance inv,String... args){
        if(uniqueName.equals("close")){
            Bukkit.getScheduler().runTask(InventoryItemInterface.plugin, (Runnable) p::closeInventory);
            return;
        }
        if(inventorys.containsKey(uniqueName)){
            inventorys.get(uniqueName).accept(new I3Executer(p,inv));
        }
    }
}
