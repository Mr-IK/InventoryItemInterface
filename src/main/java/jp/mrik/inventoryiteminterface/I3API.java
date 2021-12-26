package jp.mrik.inventoryiteminterface;

import jp.mrik.inventoryiteminterface.rename.UpdateTitle;
import jp.mrik.inventoryiteminterface.rename.UpdateTitle_1_16_5;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class I3API {

    public final static UpdateTitle updateTitle = new UpdateTitle_1_16_5();

    private static final HashMap<String,I3GUI> inventorys = new HashMap<>();

    public static boolean registerInv(String uniqueName,I3GUI inv){
        if(inventorys.containsKey(uniqueName)){
            return false;
        }
        inventorys.put(uniqueName,inv);
        return true;
    }

    public static void openInv(String uniqueName, Player p, I3Instance inv, String name, String[] args){
        if(inventorys.containsKey(uniqueName)){
            inventorys.get(uniqueName).openGUI(p,inv,name,args);
        }
    }
}
