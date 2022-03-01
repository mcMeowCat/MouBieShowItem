/**
 * LICENSE
 * MouBieShowItem
 * -------------
 * Copyright (C) 2021 MouBieCat(MouBie_Yuki)
 * -------------
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 */

package com.cat.server;

import com.cat.server.commands.CommandMain;
import com.cat.server.component.api.PluginMessage;
import com.moubieapi.api.plugin.PluginRegister;
import com.moubieapi.moubieapi.plugin.MouBiePluginBase;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.mineacademy.chatcontrol.api.channel.Channel;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 代表該插件的主要類
 * @author MouBieCat
 */
public final class MouBieCat
        extends MouBiePluginBase implements Listener {

    private PluginMessage messageLoader;

    // 展示物品的頻道集合
    private final Map<String, Channel> channels = new LinkedHashMap<>();

    @PluginRegister(name = "加載插件配置", type = PluginRegister.ActionType.ACTION_ENABLE, priority = PluginRegister.ActionPriority.HIGHEST)
    public void registerFiles() {
        messageLoader = new MessageLoader();
        final ChannelsLoader loader = new ChannelsLoader();

        loader.loadChannels(this.channels);
    }


    @PluginRegister(name = "註冊插件指令", type = PluginRegister.ActionType.ACTION_ENABLE)
    public void registerCommands() {
        final PluginCommand command = this.getCommand("MouBieShowItem");
        if (command != null)
            command.setExecutor(new CommandMain(command));
    }


    @PluginRegister(name = "重讀插件配置",type = PluginRegister.ActionType.ACTION_RELOAD)
    public void reloadFiles() {
        channels.clear();
        messageLoader = new MessageLoader();
        final ChannelsLoader loader = new ChannelsLoader();
        loader.loadChannels(this.channels);
    }


    /**
     * 獲取可以展示物品的頻道集合
     * @return 頻道集合
     */
    @NotNull
    public Map<String, Channel> getCanShowItemChannels() {
        return this.channels;
    }

    /**
     * 獲取插件訊訊息檔案(Message.yml)
     * @return Message.yml
     */
    @NotNull
    public PluginMessage getMessageLoader() {
        return this.messageLoader;
    }

    /**
     * 獲取插件本身
     * @return 插件本身
     */
    @NotNull
    public static MouBieCat getInstance() {
        return JavaPlugin.getPlugin(MouBieCat.class);
    }

}
