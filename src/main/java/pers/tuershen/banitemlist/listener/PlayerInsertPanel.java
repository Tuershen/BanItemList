package pers.tuershen.banitemlist.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.banitemlist.BanItemListMain;
import pers.tuershen.banitemlist.panel.impl.PanelBanItemList;
import pers.tuershen.banitemlist.panel.impl.PanelBanItemNode;
import pers.tuershen.banitemlist.util.HandlePanelEnum;


public class PlayerInsertPanel implements Listener {

    @EventHandler
    public void onClickPanel(InventoryClickEvent event) {
        int slot = event.getRawSlot();
        if (slot >= 0) {
            ItemStack slotItemStack = event.getCurrentItem();
            if (slotItemStack.getType() != Material.AIR) {
                if (event.getInventory().getHolder() instanceof PanelBanItemList || event.getInventory().getHolder() instanceof PanelBanItemNode) {
                    if (BanItemListMain.libraryApi.getCompound(slotItemStack).hasKey("BanItemPanelButton")) {
                        int nbt = BanItemListMain.libraryApi.getCompound(slotItemStack).getInt("BanItemPanelButton");
                        HandlePanelEnum handlePanelEnum = HandlePanelEnum.getInstance(nbt);
                        if (event.getInventory().getHolder() instanceof PanelBanItemList) {
                            PanelBanItemList banItemList = (PanelBanItemList) event.getInventory().getHolder();
                            switch (handlePanelEnum) {
                                case NEXT:
                                    event.getWhoClicked().openInventory(banItemList.nextPage());
                                    break;
                                case PREVIOUS:
                                    event.getWhoClicked().openInventory(banItemList.thePreviousPage());
                                    break;
                                case BAN_ITEM:
                                    event.getWhoClicked().openInventory(banItemList.getBanItemNode(slot).getInventory());
                                    break;
                                case NULL:
                                default:
                                    break;
                            }
                        } else if (event.getInventory().getHolder() instanceof PanelBanItemNode) {
                            PanelBanItemNode banItemNode = (PanelBanItemNode) event.getInventory().getHolder();
                            switch (handlePanelEnum) {
                                case NEXT:
                                    event.getWhoClicked().openInventory(banItemNode.nextPage());
                                    break;
                                case MIDDLE:
                                    event.getWhoClicked().openInventory(BanItemListMain.panelApi.openPanel());
                                    break;
                                case PREVIOUS:
                                    event.getWhoClicked().openInventory(banItemNode.thePreviousPage());
                                    break;
                                case BAN_ITEM:
                                case NULL:
                                default:
                                    break;
                            }
                        }
                    }
                    event.setCancelled(true);
                }
            }
        }
    }
}
