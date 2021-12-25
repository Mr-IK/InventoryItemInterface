package jp.mrik.inventoryiteminterface.rename;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface UpdateTitle {

    void sendTitleChangePacket(Player p, String title, Inventory inv);
}
