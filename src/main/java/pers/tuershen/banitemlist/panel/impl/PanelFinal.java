package pers.tuershen.banitemlist.panel.impl;


import org.bukkit.inventory.Inventory;

import java.util.*;

public class PanelFinal {


    //页面集合
    protected List<Inventory> inventoryList = new ArrayList<>();

    //当前页面
    protected Inventory panel;

    //页面数量
    protected int page;

    //当前页面下标
    protected int nowPage;



    /**
     * 上一页
     * @return
     */
    public Inventory thePreviousPage() {
        if ((this.nowPage - 1) < 0){
            return this.inventoryList.get(this.nowPage);
        }
        return this.inventoryList.get(--this.nowPage);
    }

    /**
     * 下一页
     * @return 界面
     */
    public Inventory nextPage() {
        if ((this.nowPage + 1) >= this.inventoryList.size()){
            return this.inventoryList.get(this.nowPage);
        }
        return this.inventoryList.get(++this.nowPage);
    }




}
