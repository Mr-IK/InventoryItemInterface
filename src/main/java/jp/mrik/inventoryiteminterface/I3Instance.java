package jp.mrik.inventoryiteminterface;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import static jp.mrik.inventoryiteminterface.I3API.updateTitle;

public class I3Instance implements Listener {

    protected Inventory inv;
    private String name;
    private final int size;

    private HashMap<Integer,I3Item> itemMap = new HashMap<>();
    private List<Consumer<InventoryClickEvent>> eventMap = new ArrayList<>();
    private List<Consumer<InventoryCloseEvent>> eventMap_c = new ArrayList<>();

    private UUID opener = null;
    private boolean clickCancel = false;

    public I3Instance(String name,int size){
        this.size = size;
        this.name = name;
        inv = Bukkit.createInventory(null, size, name);
        Bukkit.getPluginManager().registerEvents(this,InventoryItemInterface.plugin);
    }

    //inventory clear
    public void clearInv(){
        inv.clear();
        itemMap.clear();
    }

    public void clearEvent(){
        eventMap.clear();
        eventMap_c.clear();
    }

    //updated inventory title
    public void updateTitle(Player p, String title) {
        updateTitle.sendTitleChangePacket(p,title,inv);
        this.name = title;
    }

    public void setClickEvent(Consumer<InventoryClickEvent> event){
        eventMap.add(event);
    }

    public void addCloseEvent(Consumer<InventoryCloseEvent> event){
        eventMap_c.add(event);
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

    //event register and open inv
    public void openInv(Player p){
        if(opener!=null){
            return;
        }
        p.closeInventory();
        opener = p.getUniqueId();
        p.openInventory(inv);
    }

    //event register and open inv
    public void updateInv(Player p,I3Instance toInv){
        if(opener!=p.getUniqueId()||size!=toInv.getSize()){
            return;
        }

        clearEvent();
        clearInv();

        updateTitle(p,toInv.name);
        for(int i : toInv.itemMap.keySet()){
            setItem(i,toInv.itemMap.get(i).getCopy());
        }
        eventMap = new ArrayList<>(toInv.eventMap);
        eventMap_c = new ArrayList<>(toInv.eventMap_c);

        p.updateInventory();
    }

    public boolean isEmpty(){
        return inv.firstEmpty()==-1;
    }

    public void setClickCancel(boolean clickCancel){
        this.clickCancel = clickCancel;
    }

    public boolean getClickCancel(){
        return clickCancel;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(opener!=e.getWhoClicked().getUniqueId()){
            return;
        }
        for(Consumer<InventoryClickEvent> clickEvent: eventMap){
            clickEvent.accept(e);
        }

        if(!itemMap.containsKey(e.getRawSlot())){
            return;
        }
        I3Item item = itemMap.get(e.getRawSlot());
        e.setCancelled(item.clickEvent(e));
        if(clickCancel){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e){
        if(opener!=e.getPlayer().getUniqueId()){
            return;
        }

        opener = null;
        HandlerList.unregisterAll(this);

        for(Consumer<InventoryCloseEvent> event: eventMap_c){
            event.accept(e);
        }
    }


}
