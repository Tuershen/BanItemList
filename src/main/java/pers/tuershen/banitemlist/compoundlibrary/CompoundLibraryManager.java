package pers.tuershen.banitemlist.compoundlibrary;

import pers.tuershen.banitemlist.compoundlibrary.api.CompoundLibraryApi;
import pers.tuershen.banitemlist.compoundlibrary.nms.Compound;
import pers.tuershen.banitemlist.compoundlibrary.nms.CraftItemStack;
import pers.tuershen.banitemlist.compoundlibrary.nms.MinecraftItemStack;
import pers.tuershen.banitemlist.compoundlibrary.nms.PluginManager;
import pers.tuershen.banitemlist.compoundlibrary.nms.block.MinecraftEntityTile;
import pers.tuershen.banitemlist.compoundlibrary.nms.entity.MinecraftEntity;
import pers.tuershen.banitemlist.compoundlibrary.nms.nbt.TagBase;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;

public class CompoundLibraryManager  {

    public static Server server;


    public static CompoundLibraryApi getPluginManager(Plugin plugin){
        server = plugin.getServer();
        initPluginManager(plugin.getServer());
        return new PluginManager();
    }

    protected static void initPluginManager(Server server){
        String version = paraphrase(server);
        CraftItemStack.initCraftItemStackClass(version);
        MinecraftItemStack.initMinecraftItemStackClass(version);
        Compound.initCompoundClass(version);
        MinecraftEntity.init(version);
        MinecraftEntityTile.init(version);
        TagBase.intiFactoryClass(version);
    }

    protected static String paraphrase(Server server){
        return server
                .getClass()
                .getPackage()
                .getName()
                .replace(".", ",")
                .split(",")[3];
    }

}
