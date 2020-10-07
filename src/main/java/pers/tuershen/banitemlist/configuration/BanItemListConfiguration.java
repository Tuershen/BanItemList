package pers.tuershen.banitemlist.configuration;

import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import pers.tuershen.banitemlist.BanItemListMain;

import java.util.*;

public class BanItemListConfiguration implements PluginSetting {

    private Map<String,BanItemGroup> allGroup;

    private FileConfiguration configuration;

    public BanItemListConfiguration(){
        allGroup = new HashMap<>();
    }

    public void init(){
        BanItemListMain.plugin.saveDefaultConfig();
        configuration = BanItemListMain.plugin.getConfig();
        load();
    }

    public void load(){
        configuration.getKeys(false).forEach(
                key -> {
                    BanItemGroup banItemGroup = new BanItemGroup();
                    banItemGroup.setName(configuration.getString(key+".name"));
                    banItemGroup.setBase64(configuration.getString(key+".base64"));
                    banItemGroup.setLore(configuration.getStringList(key+".lore"));
                    MemorySection groupSection = (MemorySection) configuration.get(key+".list");
                    Map<String, ItemNode> nodeMap = new HashMap<>();
                    if (groupSection != null){
                        groupSection.getKeys(false).forEach(
                                list -> {
                                    ItemNode itemNode = new ItemNode();
                                    String base64 = configuration.getString(key + ".list."+ list +".base64");
                                    if (base64 != null){
                                        itemNode.setBase64(base64);
                                        itemNode.setLore(configuration.getStringList(key + ".list."+ list +".lore"));
                                        nodeMap.put(list, itemNode);
                                    }
                                });
                    }
                    banItemGroup.setItemNodeMap(nodeMap);
                    this.allGroup.put(key, banItemGroup);
                }
        );
    }

    public void reload(){
        BanItemListMain.plugin.reloadConfig();
        allGroup = new HashMap<>();
        init();
    }

    public boolean hasGroup(String group){
        return this.allGroup.containsKey(group);
    }

    public boolean removeGroup(String group){
        this.allGroup.remove(group);
        this.configuration.set(group,null);
        return true;
    }

    public boolean removeNodeItem(String group, String node){
        this.allGroup.get(group).getItemNodeMap().remove(node);
        this.configuration.set(group+".list."+node,null);
        return true;
    }

    @Override
    public Map<String, BanItemGroup> getItemGroup() {
        return this.allGroup;
    }

    public void addGroup(String key, String base64){
        this.configuration.set(key+".name",key);
        List<String> lore = new ArrayList<>();
        lore.add("&e点击查看 &b"+key+" &e禁用列表");
        this.configuration.set(key+".base64", base64);
        this.configuration.set(key+".lore", lore);
        this.configuration.set(key+".list", null);
        BanItemGroup banItemGroup = new BanItemGroup();
        banItemGroup.setLore(lore);
        banItemGroup.setBase64(base64);
        banItemGroup.setName(key);
        banItemGroup.setItemNodeMap(new HashMap<>());
        this.allGroup.put(key, banItemGroup);
    }


    public boolean hasItemNode(String group, String itemNode){
        return this.allGroup.get(group).getItemNodeMap().containsKey(itemNode);
    }

    public void addItemNode(String group, String itemNode, String base64){
        this.configuration.set(group+".list."+itemNode+".base64", base64);
        List<String> lore = new ArrayList<>();
        lore.add("§3禁止原因: §b"+itemNode);
        this.configuration.set(group+".list."+itemNode+".lore", lore);
        this.allGroup.get(group).getItemNodeMap().put(itemNode, new ItemNode(base64,lore));
    }

    public Set<String> getAllGroup(){
        return this.allGroup.keySet();
    }


    public Set<String> getAllNode(String group){
        return this.allGroup.get(group).getItemNodeMap().keySet();
    }



}
