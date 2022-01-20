package jp.mrik.inventoryiteminterface;

import org.bukkit.entity.Player;

public class I3Executer {

    private final Player p;
    private final I3Instance inv;
    private final String[] args;

    public I3Executer(Player p,I3Instance inv){
        this.p = p;
        this.inv = inv;
        this.args = new String[]{};
    }

    public I3Executer(Player p, I3Instance inv, String[] args){
        this.p = p;
        this.inv = inv;
        this.args = args;
    }

    public Player getPlayer() {
        return p;
    }

    public I3Instance getInv() {
        return inv;
    }

    public String[] getArgs() {
        return args;
    }
}
