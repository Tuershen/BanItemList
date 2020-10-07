package pers.tuershen.banitemlist.compoundlibrary.nms;

import pers.tuershen.banitemlist.compoundlibrary.api.*;
import pers.tuershen.banitemlist.compoundlibrary.nms.block.MinecraftEntityTile;
import pers.tuershen.banitemlist.compoundlibrary.nms.entity.MinecraftEntity;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;


import pers.tuershen.banitemlist.compoundlibrary.nms.nbt.SerializeTag;

public class PluginManager implements CompoundLibraryApi {

    @Override
    public NBTTagCompoundApi getCompound(ItemStack itemStack) {
        return CraftItemStack
                .asNMSCopy(itemStack)
                .getMinecraftItemStackTag();
    }

    @Override
    public ItemStack setCompound(ItemStack itemStack, NBTTagCompoundApi compoundTagApi) {
        MinecraftItemStack minecraftItemStack = CraftItemStack.asNMSCopy(itemStack);
        minecraftItemStack.setMinecraftItemStackTag(compoundTagApi.getNMSCompound());
        return CraftItemStack.asBukkitCopy(minecraftItemStack);
    }

    @Override
    public SerializableItemApi getSerializeItem() {
        return new SerializeTag();
    }

    @Override
    public EntityNBTTagCompoundApi getEntityCompoundApi(LivingEntity livingEntity) {
        return MinecraftEntity.getInstance(livingEntity);
    }

    @Override
    public TileEntityCompoundApi getTileEntityCompoundApi(Block block) {
        return MinecraftEntityTile.getInstance(block);
    }

    @Override
    public Object getMinecraftItem(ItemStack itemStack) {
        return CraftItemStack.asNMSCopy(itemStack).getMinecraftItemStack();
    }


}
