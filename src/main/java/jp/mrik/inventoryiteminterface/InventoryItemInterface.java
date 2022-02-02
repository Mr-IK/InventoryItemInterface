package jp.mrik.inventoryiteminterface;

import jp.mrik.inventoryiteminterface.custom.NumberInputer;
import org.bukkit.plugin.java.JavaPlugin;

public final class InventoryItemInterface extends JavaPlugin {

    static InventoryItemInterface plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        NumberInputer.init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
