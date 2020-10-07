package pers.tuershen.banitemlist.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import pers.tuershen.banitemlist.BanItemListMain;
import pers.tuershen.banitemlist.command.tab.TabExecutorEnum;

import java.util.ArrayList;
import java.util.List;

public class BaseCommand implements CommandExecutor, TabExecutor {

    private AdminCommands adminCommands;

    private HandlerCommands handlerCommands;

    public BaseCommand(){
        this.adminCommands = new AdminCommands();
        this.handlerCommands = new HandlerCommands();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String args, String[] params) {
        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            if (params.length > 0 ){
                if ("open".equalsIgnoreCase(params[0])){
                    handlerCommands.open(player);
                    return true;
                }else if ("add".equalsIgnoreCase(params[0])){
                    if (isAdmin(player)){
                        if (params.length >= 3){
                            if (adminCommands.addItemNode(player, params[1], params[2])) {
                                player.sendMessage("§7[§3BanItemList§7] §3添加§a " + params[2] + " §3成功!");
                            }
                            return true;
                        }
                        player.sendMessage("§7[§3BanItemList§7] §c参数不足!");
                        return true;
                    }
                    warning(player);
                    return true;
                } else if ("reload".equalsIgnoreCase(params[0])){
                    if (isAdmin(player)){
                        adminCommands.reload();
                        player.sendMessage("§7[§3BanItemList§7] §a重载成功!");
                        return true;
                    }
                    warning(player);
                    return true;
                }else if ("group".equalsIgnoreCase(params[0])){
                    if (isAdmin(player)){
                        if (params.length >= 2){
                            if (adminCommands.createGroup(player,params[1])){
                                player.sendMessage("§7[§3BanItemList§7] §3添加§a "+params[1]+" §3成功!");
                                return true;
                            }
                            return false;
                        }
                        player.sendMessage("§7[§3BanItemList§7] §c参数不足!");
                        return true;
                    }
                    warning(player);
                    return true;
                }else if ("all".equalsIgnoreCase(params[0])){
                    if (isAdmin(player)){
                        adminCommands.allGroup(player);
                        return true;
                    }
                    warning(player);
                    return true;
                }else if ("remove".equalsIgnoreCase(params[0])){
                    if (isAdmin(player)) {
                        if (params.length >= 3) {
                            if (params[1].equalsIgnoreCase("group")) {
                                adminCommands.removeGroup(player, params[2]);
                                if (adminCommands.removeGroup(player,params[2])) {
                                    player.sendMessage("§7[§3BanItemList§7] §3删除组 §a" + params[2] + " §3成功!");
                                }
                                return true;
                            } else if (params[1].equalsIgnoreCase("node")) {
                                if (params.length >= 4) {
                                    if (adminCommands.removeItemNode(player, params[2], params[3])) {
                                        player.sendMessage("§7[§3BanItemList§7] §3组: §3§a" + params[2] + " §3节点: §a" + params[3] + " §3删除成功!");
                                    }
                                    return true;
                                }
                                player.sendMessage("§7[§3BanItemList§7] §c参数不足!");
                                player.sendMessage("§3/blist remove node <组名> <节点名称>  §7-删除指定组的指定物品");
                                return true;
                            }
                        }
                        player.sendMessage("§3/blist remove group <组名>            §7-删除该组，包括该组的所有物品");
                        player.sendMessage("§3/blist remove node <组名> <节点名称>  §7-删除指定组的指定物品");
                        return true;
                    }
                    warning(player);
                    return true;
                }else if ("allNode".equalsIgnoreCase(params[0])){
                    if (isAdmin(player)){
                        if (params.length >= 2){
                            adminCommands.allNode(player,params[1]);
                            return true;
                        }
                        return true;
                    }
                    warning(player);
                    return true;
                }
            }else {
                if (isAdmin(player)){
                    handlerCommands.help(player);
                    adminCommands.help(player);
                    return true;
                }
                handlerCommands.help(player);
                return true;
            }
        }
        return false;
    }

    public boolean isAdmin(Player player){
        return player.isOp() || player.hasPermission("BanItemList");
    }

    public void warning(Player player){
        player.sendMessage("§7[§3BanItemList§7] §c你没有权限!");
    }


    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender.isOp() || commandSender.hasPermission("BanItemList")){
            return TabExecutorEnum.getInstance(command.getName(),
                    args.length <= 1 ? "null" : args[args.length - 2] ,
                    args.length - 1,
                    args.length).getTabExecutorArray(BanItemListMain.setting, args);
        }
        return new ArrayList<>();
    }
}
