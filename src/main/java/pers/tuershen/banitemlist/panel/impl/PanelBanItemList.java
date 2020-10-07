package pers.tuershen.banitemlist.panel.impl;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.banitemlist.BanItemListMain;
import pers.tuershen.banitemlist.configuration.BanItemGroup;
import pers.tuershen.banitemlist.panel.PanelApi;
import pers.tuershen.banitemlist.util.InventoryUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PanelBanItemList extends PanelFinal implements InventoryHolder, PanelApi {


    protected List<PanelBanItemNode> itemNodes;

    private Map<String, BanItemGroup> groupMap;

    private int size;

    //单例
    protected static PanelBanItemList banItemList;

    //获取单例
    public static PanelBanItemList getInstance(Map<String, BanItemGroup> groupMap){
        if (banItemList == null){
            banItemList = new PanelBanItemList(groupMap);
            return banItemList;
        }
        return banItemList;
    }

    public static PanelBanItemList reload(Map<String, BanItemGroup> groupMap) {
        banItemList = new PanelBanItemList(groupMap);
        return banItemList;
    }

    public static Inventory setDefault(PanelBanItemList panelFinal, int size){
        Inventory panel = Bukkit.createInventory(panelFinal, size + 9, "§c禁用表");
        for (int i = size ; i < size + 9; i++) {
            panel.setItem(i, InventoryUtil.functionItem());
        }
        panel.setItem(size + 3, InventoryUtil.previousButton());
        panel.setItem(size + 5, InventoryUtil.nextButton());
        return panel;
    }

    public PanelBanItemList(Map<String, BanItemGroup> groupMap){
        this.groupMap = groupMap;
        this.itemNodes = new ArrayList<>();
        newPanelFinal();
    }

    public void newPanelFinal(){
        int count = this.groupMap.size() / 9 ;
        size = count == 0 ? 1 : this.groupMap.size() % 9 == 0 ? count : count + 1;
        this.panel = setDefault(this, size * 9);
        int i = 0;
        for (Map.Entry<String,BanItemGroup> entry : this.groupMap.entrySet()){
            ItemStack itemStack = InventoryUtil.setGroupMeta(BanItemListMain.libraryApi.getSerializeItem().deserialize(entry.getValue().getBase64()));
            ItemStack dis = InventoryUtil.setDisPlay(itemStack, entry.getValue().getName());
            this.panel.setItem(i++,InventoryUtil.setMeta(dis,entry.getValue().getLore()));
            this.itemNodes.add(new PanelBanItemNode(entry.getValue()));
        }
        this.inventoryList.add(panel);
    }

    public PanelBanItemNode getBanItemNode(int index){
        return this.itemNodes.get(index);
    }


    @Override
    public Inventory getInventory() {
        return this.panel;
    }

    @Override
    public Inventory openPanel() {
        return this.panel;
    }
}
