package jp.mrik.inventoryiteminterface;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class I3Item implements I3Clicker{

    private ItemStack item;
    private List<Consumer<InventoryClickEvent>> events = new ArrayList<>();

    private boolean clickCancel = true;

    public I3Item(ItemStack item){
        this.item = item;
    }

    public void addClickEvent(Consumer<InventoryClickEvent> event){
        events.add(event);
    }

    public void clearClickEvent(){
        events.clear();
    }

    public void setClickCancel(boolean clickCancel){
        this.clickCancel = clickCancel;
    }

    public boolean getClickCancel(){
        return clickCancel;
    }

    @Override
    public boolean clickEvent(InventoryClickEvent e) {

        for(Consumer<InventoryClickEvent> event: events){
            event.accept(e);
        }

        return clickCancel;
    }

    public ItemStack getItem() {
        return item;
    }

    public ItemStack getItemCopy() {
        return item.clone();
    }

    public I3Item getCopy(){
        I3Item newData = new I3Item(getItemCopy());
        for(Consumer<InventoryClickEvent> event: events){
            newData.addClickEvent(event);
        }
        return newData;
    }
}
