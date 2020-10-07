package pers.tuershen.banitemlist;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pers.tuershen.banitemlist.bstats.Metrics;
import pers.tuershen.banitemlist.command.BaseCommand;
import pers.tuershen.banitemlist.compoundlibrary.CompoundLibraryManager;
import pers.tuershen.banitemlist.compoundlibrary.api.CompoundLibraryApi;
import pers.tuershen.banitemlist.configuration.BanItemGroup;
import pers.tuershen.banitemlist.configuration.BanItemListConfiguration;
import pers.tuershen.banitemlist.configuration.PluginSetting;
import pers.tuershen.banitemlist.listener.PlayerInsertPanel;
import pers.tuershen.banitemlist.panel.PanelApi;
import pers.tuershen.banitemlist.panel.impl.PanelBanItemList;

import java.util.Map;

public class BanItemListMain extends JavaPlugin {

    public static BanItemListMain plugin;

    public static CompoundLibraryApi libraryApi;

    public static PanelApi panelApi;

    public static PluginSetting setting;

    @Override
    public void onEnable() {
        plugin = this;
        libraryApi = CompoundLibraryManager.getPluginManager(this);
        initConfiguration();
        initPanelManager(setting.getItemGroup());
        getCommand("blist").setExecutor(new BaseCommand());
        registerEvents();
        registerBStats();
        getLogger().info("禁用表已加载!");
    }


    @Override
    public void onDisable() {
        getLogger().info("禁用表卸载成功");
    }

    public void initPanelManager(Map<String, BanItemGroup> groupMap){
        panelApi = PanelBanItemList.getInstance(groupMap);
    }

    public void initConfiguration(){
        setting = new BanItemListConfiguration();
        setting.init();
    }

    public void registerEvents(){
        Bukkit.getPluginManager().registerEvents(new PlayerInsertPanel(),this);
    }

    public void registerBStats(){
        new Metrics(this,9038);
    }





}
