package pers.tuershen.banitemlist.panel.impl;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.banitemlist.BanItemListMain;
import pers.tuershen.banitemlist.configuration.BanItemGroup;
import pers.tuershen.banitemlist.configuration.ItemNode;
import pers.tuershen.banitemlist.util.InventoryUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PanelBanItemNode extends PanelFinal implements InventoryHolder {

    private BanItemGroup itemGroup;

    //页面总大小
    public static final int MAX_SLOT = 54;

    //上一页的按钮位置
    public static final int PREVIOUS_SLOT_POS = 48;

    //中间的按钮
    public static final int MIDDLE_SLOT_POS = 49;

    //下一页按钮位置
    public static final int NEXT_SLOT_POS = 50;

    //显示最大数量
    private static final int PANEL_SIZE = 45;

    public static Inventory setDefaultNodePanel(PanelBanItemNode itemNode){
        Inventory inventory = Bukkit.createInventory(itemNode, MAX_SLOT, itemNode.itemGroup.getName().replace('&','§'));
        for (int i = 45; i <= 53; i++) {
            inventory.setItem(i, InventoryUtil.functionItem());
        }
        inventory.setItem(PREVIOUS_SLOT_POS, InventoryUtil.previousButton());
        inventory.setItem(MIDDLE_SLOT_POS, InventoryUtil.middleButton());
        inventory.setItem(NEXT_SLOT_POS, InventoryUtil.nextButton());
        return inventory;
    }


    public PanelBanItemNode(BanItemGroup itemGroup){
        this.itemGroup = itemGroup;
        Map<String, ItemNode> itemNodeMap = this.itemGroup.getItemNodeMap();
        Iterator<String> iterator = itemNodeMap.keySet().iterator();
        int slot = 0;
        int pos = 1;
        Inventory inventory = setDefaultNodePanel(this);
        while (iterator.hasNext()) {
            String next = iterator.next();
            ItemNode itemNode = this.itemGroup.getItemNodeMap().get(next);
            ItemStack itemStack = InventoryUtil.setGroupMeta(BanItemListMain.libraryApi.getSerializeItem().deserialize(itemNode.getBase64()));
            List<String> lore  = itemNode.getLore();
            if (slot >= PANEL_SIZE || pos == itemNodeMap.size()) {
                inventory.setItem(slot,InventoryUtil.setMeta(itemStack,lore));
                this.inventoryList.add(inventory);
                inventory = setDefaultNodePanel(this);
                slot = 0;
                pos++;
                continue;
            }
            inventory.setItem(slot, InventoryUtil.setMeta(itemStack,lore));
            slot++;
            pos++;
        }
        if (this.inventoryList.size() <= 0) {
            this.inventoryList.add(inventory);
        }
        this.panel = this.inventoryList.get(nowPage);
    }


    @Override
    public Inventory getInventory() {
        return this.panel;
    }
}
