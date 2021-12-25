package jp.mrik.inventoryiteminterface;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Consumer;

import static jp.mrik.inventoryiteminterface.I3API.updateTitle;

public class I3Instance implements Listener {

    private final JavaPlugin plugin;
    protected Inventory inv;
    private String name;
    private final int size;

    private HashMap<Integer,I3Item> itemMap = new HashMap<>();
    private HashMap<Integer, Consumer<InventoryClickEvent>> eventMap = new HashMap<>();
    private UUID opener = null;

    public I3Instance(JavaPlugin plugin,String name,int size){
        this.size = size;
        this.plugin = plugin;
        this.name = name;
        inv = Bukkit.createInventory(null, size, name);
    }

    //inventory clear
    public void clear(){
        inv.clear();
    }

    //updated inventory title
    public void updateTitle(Player p, String title) {
        updateTitle.sendTitleChangePacket(p,title,inv);
        this.name = title;
    }

    //setting item
    public void setItem(int i, I3Item item){
        inv.setItem(i,item.getItem());
        itemMap.put(i,item);
    }

    //get inventory name
    public String getName() {
        return name;
    }

    //get item
    public ItemStack getItem(int i){
        return itemMap.get(i).getItem();
    }

    //get i3item
    public I3Item getI3Item(int i){
        return itemMap.get(i);
    }

    //set item
    public void setItems(int[] i, I3Item item){
        for(int ii :i){
            setItem(ii,item);
        }
    }

    //inv fill item
    public void fillInv(I3Item item){
        for(int i = 0;i<getSize();i++){
            setItem(i,item);
        }
    }

    // get inventory size
    public int getSize(){
        return inv.getSize();
    }
}
