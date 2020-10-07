package pers.tuershen.banitemlist.configuration;

import java.util.List;

public class ItemNode {

    private String base64;

    private List<String> lore;

    public ItemNode(String base64, List<String> lore) {
        this.base64 = base64;
        this.lore = lore;
    }

    public ItemNode(){

    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {


        this.lore = lore;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    @Override
    public String toString() {
        return "ItemNode{" +
                ", lore=" + lore +
                '}';
    }
}
