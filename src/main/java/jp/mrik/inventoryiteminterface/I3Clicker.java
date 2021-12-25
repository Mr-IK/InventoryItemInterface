package jp.mrik.inventoryiteminterface;

import org.bukkit.event.inventory.InventoryClickEvent;
/**
 * I.I.I クリックインターフェース
 * クリックイベントを含むクラスに適用するものです
 */
public interface I3Clicker {
    /**
     * clickEvent
     * @param event
     * InventoryClickEventを送信します
     *
     * @return
     * trueの場合、イベントをキャンセルします
     */
    boolean clickEvent(InventoryClickEvent event);

}
