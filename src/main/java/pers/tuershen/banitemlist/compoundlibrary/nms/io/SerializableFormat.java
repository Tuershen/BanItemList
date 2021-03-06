package pers.tuershen.banitemlist.compoundlibrary.nms.io;

import pers.tuershen.banitemlist.compoundlibrary.nms.nbt.TagCompound;

import java.io.Serializable;

public class SerializableFormat implements Serializable {

    private static final long serialVersionUID = 6932895816314682365L;

    private String id;

    private TagCompound tagCompound;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TagCompound getTagCompound() {
        return tagCompound;
    }

    public void setTagCompound(TagCompound tagCompound) {
        this.tagCompound = tagCompound;
    }

    @Override
    public String toString() {
        return "SerializableFormat{" +
                "id='" + id + '\'' +
                ", tagCompound=" + tagCompound +
                '}';
    }
}
