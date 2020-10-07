package pers.tuershen.banitemlist.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.banitemlist.BanItemListMain;
import pers.tuershen.banitemlist.compoundlibrary.api.NBTTagCompoundApi;

import java.util.List;

public class InventoryUtil {

    public static ItemStack previousButton() {
        ItemStack itemStack = new ItemStack(Material.ANVIL);
        itemStack.setItemMeta(setItemMeta(itemStack.getItemMeta(), "§b§l上一页"));
        NBTTagCompoundApi compoundApi = BanItemListMain.libraryApi.getCompound(itemStack);
        compoundApi.setInt("BanItemPanelButton",  1);
        return BanItemListMain.libraryApi.setCompound(itemStack,compoundApi);
    }

    public static ItemStack middleButton() {
        ItemStack itemStack = new ItemStack(Material.ANVIL);
        itemStack.setItemMeta(setItemMeta(itemStack.getItemMeta(), "§c§l返回上一级"));
        NBTTagCompoundApi compoundApi = BanItemListMain.libraryApi.getCompound(itemStack);
        compoundApi.setInt("BanItemPanelButton",  2);
        return BanItemListMain.libraryApi.setCompound(itemStack,compoundApi);
    }

    public static ItemStack nextButton() {
        ItemStack itemStack = new ItemStack(Material.ANVIL);
        itemStack.setItemMeta(setItemMeta(itemStack.getItemMeta(), "§b§l下一页"));
        NBTTagCompoundApi compoundApi = BanItemListMain.libraryApi.getCompound(itemStack);
        compoundApi.setInt("BanItemPanelButton",  3);
        return BanItemListMain.libraryApi.setCompound(itemStack,compoundApi);
    }

    protected static ItemMeta setItemMeta(ItemMeta itemMeta, String data) {
        itemMeta.setDisplayName(data);
        return itemMeta;
    }

    public static ItemStack functionItem(){
        ItemStack itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0);
        NBTTagCompoundApi compoundApi = BanItemListMain.libraryApi.getCompound(itemStack);
        compoundApi.setInt("BanItemPanelButton",  4);
        return BanItemListMain.libraryApi.setCompound(itemStack,compoundApi);
    }

    public static ItemStack setGroupMeta(ItemStack itemStack){
        NBTTagCompoundApi compoundApi = BanItemListMain.libraryApi.getCompound(itemStack);
        compoundApi.setInt("BanItemPanelButton",  5);
        return BanItemListMain.libraryApi.setCompound(itemStack,compoundApi);
    }


    public static ItemStack setMeta(ItemStack itemStack, List<String> list){
        List<String> lore = list;
        if (lore == null) return itemStack;
        for (int i = 0; i < list.size() ; i++) {
            list.set(i,list.get(i).replace('&','§'));
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(list);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack setDisPlay(ItemStack itemStack, String name){
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name.replace('&','§'));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }


}
