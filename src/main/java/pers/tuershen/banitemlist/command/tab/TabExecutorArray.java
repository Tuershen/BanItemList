package pers.tuershen.banitemlist.command.tab;

import pers.tuershen.banitemlist.configuration.TabSetting;

import java.util.List;

public interface TabExecutorArray {

    List<String> getTabExecutorArray(TabSetting tabSetting, String... args);
}
