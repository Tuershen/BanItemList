package pers.tuershen.banitemlist.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pers.tuershen.banitemlist.BanItemListMain;

public class HandlerCommands {


    public void help(CommandSender sender){
        sender.sendMessage("§3/blist open                 §7-打开物品禁用列表");
    }

    public void open(Player player){
        player.openInventory(BanItemListMain.panelApi.openPanel());
    }


}
