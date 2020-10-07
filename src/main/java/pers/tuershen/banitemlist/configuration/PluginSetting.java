package pers.tuershen.banitemlist.configuration;

import java.util.Map;

public interface PluginSetting extends TabSetting {

    void init();

    void reload();

    boolean hasGroup(String key);

    boolean removeGroup(String key);

    boolean removeNodeItem(String group, String node);

    Map<String,BanItemGroup> getItemGroup();

    void addGroup(String group, String base64);

    boolean hasItemNode(String group, String itemNode);

    void addItemNode(String group, String itemNode, String base64);



}
