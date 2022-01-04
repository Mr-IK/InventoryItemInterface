package jp.mrik.inventoryiteminterface;

import org.bukkit.entity.Player;

public class I3Executer {

    private final Player p;
    private final I3Instance inv;

    public I3Executer(Player p,I3Instance inv){
        this.p = p;
        this.inv = inv;
    }

    public Player getPlayer() {
        return p;
    }

    public I3Instance getInv() {
        return inv;
    }
}
