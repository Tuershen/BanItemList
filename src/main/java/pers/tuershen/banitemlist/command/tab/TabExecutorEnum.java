package pers.tuershen.banitemlist.command.tab;

import pers.tuershen.banitemlist.configuration.TabSetting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum  TabExecutorEnum implements TabExecutorArray {
    COMMAND("blist","null",0,1){
        @Override
        public List<String> getTabExecutorArray(TabSetting tabSetting, String... args) {
            return new ArrayList<>(
                    Arrays.asList(
                    "group",
                    "add",
                    "all",
                    "allNode",
                    "remove",
                    "reload",
                    "help"
            ));
        }
    },
    ALL_NODE("blist","allNode",1,2){
        @Override
        public List<String> getTabExecutorArray(TabSetting tabSetting, String... args) {
            return new ArrayList<>(tabSetting.getAllGroup());
        }
    },
    REMOVE("blist","remove",1,2){
        @Override
        public List<String> getTabExecutorArray(TabSetting tabSetting, String... args) {
            return new ArrayList<>(
                    Arrays.asList(
                            "group",
                            "node"
                    ));
        }
    },
    REMOVE_GROUP("blist","group",2,3){
        @Override
        public List<String> getTabExecutorArray(TabSetting tabSetting, String... args) {
            return new ArrayList<>(tabSetting.getAllGroup());
        }
    },
    REMOVE_GROUP_NODE("blist","node",2,3){
        @Override
        public List<String> getTabExecutorArray(TabSetting tabSetting, String... args) {
            return new ArrayList<>(tabSetting.getAllGroup());
        }
    },
    REMOVE_GROUP_NODE_TAB("blist","node",3,4){
        @Override
        public List<String> getTabExecutorArray(TabSetting tabSetting, String... args) {
            return new ArrayList<>(tabSetting.getAllNode(args[2]));
        }
    },
    ADD_NODE("blist","add",1,2){
        @Override
        public List<String> getTabExecutorArray(TabSetting tabSetting, String... args) {
            return new ArrayList<>(tabSetting.getAllGroup());
        }
    },
    NULL("blist","null",0,1){
        @Override
        public List<String> getTabExecutorArray(TabSetting tabSetting, String... args) {
            return new ArrayList<>();
        }
    };

    private String command;
    private String args;
    private int slot;
    private int length;

    TabExecutorEnum(String command, String args, int slot, int length) {
        this.command = command;
        this.slot = slot;
        this.args = args;
        this.length = length;
    }

    public static TabExecutorEnum getInstance(String command, String args, int slot, int length){
        if (slot == 3 && length == 4){
            args = "node";
        }
        TabExecutorEnum[] executorEnums = TabExecutorEnum.values();
        for (TabExecutorEnum executorEnum : executorEnums){
            if (executorEnum.slot == slot
                    && executorEnum.command.equalsIgnoreCase(command)
                    && executorEnum.args.equalsIgnoreCase(args)
                    && executorEnum.length == length){
                return executorEnum;
            }
        }
        return TabExecutorEnum.NULL;
    }


}
