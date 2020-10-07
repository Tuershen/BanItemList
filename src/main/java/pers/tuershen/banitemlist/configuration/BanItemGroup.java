package pers.tuershen.banitemlist.configuration;

import java.util.List;
import java.util.Map;

public class BanItemGroup {

    private String name;

    private String base64;

    private List<String> lore;

    private Map<String,ItemNode> itemNodeMap;

    public Map<String, ItemNode> getItemNodeMap() {
        return itemNodeMap;
    }

    public void setItemNodeMap(Map<String, ItemNode> itemNodeMap) {
        this.itemNodeMap = itemNodeMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }


    @Override
    public String toString() {
        return "BanItemGroup{" +
                "name='" + name + '\'' +
                ", base64='" + base64 + '\'' +
                ", lore=" + lore +
                ", itemNodeMap=" + itemNodeMap +
                '}';
    }
}
