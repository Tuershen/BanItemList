package pers.tuershen.banitemlist.compoundlibrary.api;

import pers.tuershen.banitemlist.compoundlibrary.nms.nbt.TagBase;
import org.bukkit.inventory.ItemStack;

public interface SerializableItemApi  {

    ItemStack deserialize(String paramString);

    String serialize(ItemStack paramItemStack);

    <T extends TagBase> String getTagBaseByteStream(T t);

    <T extends TagBase> T deserializeTagBase(String data);



}
