package pers.tuershen.banitemlist.util;

public enum  HandlePanelEnum {

    PREVIOUS( 1),
    MIDDLE(2),
    NEXT( 3),
    NULL(4),
    BAN_ITEM(5);

    private int type;

    HandlePanelEnum(int type) {
        this.type = type;
    }

    public static HandlePanelEnum getInstance(int type){
        HandlePanelEnum[] panelEnums = HandlePanelEnum.values();
        for (HandlePanelEnum panelEnum : panelEnums){
            if (panelEnum.type == type){
                return panelEnum;
            }
        }
        return null;
    }

}
