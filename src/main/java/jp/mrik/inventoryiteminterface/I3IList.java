package jp.mrik.inventoryiteminterface;

public class I3IList extends I3Instance{

    private int page;

    public I3IList(String name, int page) {
        super(name, 54);
        this.page = page;
        setClickCancel(true);
    }

    public void setPage(int page){
        this.page = page;
    }

    public int getPage(){
        return page;
    }

    @Override
    public void setItem(int i, I3Item item) {
        if(i < 9*5){
            super.setItem(i, item);
        }
    }
}
