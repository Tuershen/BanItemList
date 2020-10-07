package pers.tuershen.banitemlist.command;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.banitemlist.BanItemListMain;
import pers.tuershen.banitemlist.panel.impl.PanelBanItemList;

public class AdminCommands  {

    public void help(CommandSender sender){
        sender.sendMessage("§3/blist group  <组名>        §7-创建组，不同mod分不同组");
        sender.sendMessage("§3/blist add    <组名> <名称> §7-把手中的物品添加到指定组中");
        sender.sendMessage("§3/blist all                  §7-列出所有组");
        sender.sendMessage("§3/blist allNode <组名>       §7-列出指定组的所有节点");
        sender.sendMessage("§3/blist remove group <组名>  §7-删除组，包括该组的所有物品");
        sender.sendMessage("§3/blist remove node <组名> <节点名称>  §7-删除指定组的指定节点");
        sender.sendMessage("§3/blist reload               §7-重载配置文件");
    }


    public boolean createGroup(Player player, String group){
        if (BanItemListMain.setting.hasGroup(group)) {
            player.sendMessage("§7[§3BanItemList§7] §c该组已经存在! 不能重名!");
            return false;
        }
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (itemStack.getType() == Material.AIR){
            player.sendMessage("§7[§3BanItemList§7] §c请手持一个物品再输入该指令!");
            return false;
        }
        BanItemListMain.setting.addGroup(group,BanItemListMain.libraryApi.getSerializeItem().serialize(itemStack));
        BanItemListMain.plugin.saveConfig();
        BanItemListMain.setting.reload();
        BanItemListMain.panelApi = PanelBanItemList.reload(BanItemListMain.setting.getItemGroup());
        return true;
    }

    public boolean addItemNode(Player player, String group, String node){
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (itemStack.getType() == Material.AIR){
            player.sendMessage("§7[§3BanItemList§7] §c请手持一个物品再输入该指令!");
            return false;
        }
        if (BanItemListMain.setting.hasGroup(group)) {
            if (BanItemListMain.setting.hasItemNode(group, node)){
                player.sendMessage("§7[§3BanItemList§7] §c该名称已经存在！换个名称吧！");
                return false;
            }
            BanItemListMain.setting.addItemNode(group, node, BanItemListMain.libraryApi.getSerializeItem().serialize(itemStack));
            BanItemListMain.plugin.saveConfig();
            BanItemListMain.setting.reload();
            BanItemListMain.panelApi = PanelBanItemList.reload(BanItemListMain.setting.getItemGroup());
            return true;
        }
        player.sendMessage("§7[§3BanItemList§7] §c该组不存在!");
        return false;
    }

    public void reload(){
        BanItemListMain.setting.reload();
        BanItemListMain.panelApi = PanelBanItemList.reload(BanItemListMain.setting.getItemGroup());
    }


    public void allGroup(Player player){
        BanItemListMain.setting.getAllGroup().forEach(key -> player.sendMessage("§3组节点名称: §e"+key));
    }

    public boolean removeGroup(Player player, String group){
        if (BanItemListMain.setting.hasGroup(group)) {
            BanItemListMain.setting.removeGroup(group);
            BanItemListMain.plugin.saveConfig();
            BanItemListMain.setting.reload();
            BanItemListMain.panelApi = PanelBanItemList.reload(BanItemListMain.setting.getItemGroup());
            return true;
        }
        player.sendMessage("§7[§3BanItemList§7] §c输入的组不存在!");
        return false;
    }


    public boolean removeItemNode(Player player, String group, String node){
        if (BanItemListMain.setting.hasGroup(group)) {
            if (BanItemListMain.setting.hasItemNode(group, node)){
                BanItemListMain.setting.removeNodeItem(group,node);
                BanItemListMain.plugin.saveConfig();
                BanItemListMain.setting.reload();
                BanItemListMain.panelApi = PanelBanItemList.reload(BanItemListMain.setting.getItemGroup());
                return true;
            }
            player.sendMessage("§7[§3BanItemList§7] §c组 §3"+group+"§c 中不存在 §b"+node+"§3 节点");
            return false;
        }
        player.sendMessage("§7[§3BanItemList§7] §c输入的组不存在!");
        return false;
    }

    public void allNode(Player player, String group){
        if (BanItemListMain.setting.hasGroup(group)) {
            BanItemListMain.setting.getAllNode(group).forEach(key -> player.sendMessage("§3组名称: §e"+group+" §3节点名称: §b"+key));
            return;
        }
        player.sendMessage("§7[§3BanItemList§7] §c输入的组不存在!");
    }



}
