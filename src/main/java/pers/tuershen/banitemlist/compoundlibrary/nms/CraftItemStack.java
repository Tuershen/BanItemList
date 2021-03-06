package pers.tuershen.banitemlist.compoundlibrary.nms;


import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CraftItemStack {

    private static Class<?> craftItemStackClass;

    private static Class<?> minecraftItemStackClass;

    private static Method asNMSCopy;

    private static Method asBukkitCopy;

    /**
     * 初始化CraftBukkit
     * @param version 服务器版本
     */
    public static void initCraftItemStackClass(String version){
        try {
            craftItemStackClass = Class.forName("org.bukkit.craftbukkit."+version+".inventory.CraftItemStack");
            minecraftItemStackClass = Class.forName("net.minecraft.server."+version+".ItemStack");
            asNMSCopy = craftItemStackClass.getDeclaredMethod("asNMSCopy",ItemStack.class);
            asBukkitCopy = craftItemStackClass.getDeclaredMethod("asBukkitCopy",minecraftItemStackClass);
        } catch (ClassNotFoundException
                | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取net.minecraft.server.ItemStack
     * @param itemStack org.bukkit.inventory.ItemStack
     * @return MinecraftItemStack
     */
    public static pers.tuershen.banitemlist.compoundlibrary.nms.MinecraftItemStack asNMSCopy(ItemStack itemStack){
        Object item = null;
        try {
            item = asNMSCopy.invoke(craftItemStackClass,itemStack);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return new pers.tuershen.banitemlist.compoundlibrary.nms.MinecraftItemStack(item);
    }

    /**
     * net.minecraft.server.ItemStack -> org.bukkit.inventory.ItemStack
     * @param minecraftItemStack MinecraftItemStack
     * @return org.bukkit.inventory.ItemStack
     */
    public static ItemStack asBukkitCopy(MinecraftItemStack minecraftItemStack){

        Object item = null;
        try {
            item = asBukkitCopy.invoke(craftItemStackClass,minecraftItemStack.getMinecraftItemStack());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return (ItemStack)item;
    }




}
