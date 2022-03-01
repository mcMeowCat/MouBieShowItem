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

import com.moubieapi.moubieapi.yaml.Loader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineacademy.chatcontrol.api.channel.Channel;
import org.mineacademy.chatcontrol.model.SimpleChannel;
import org.mineacademy.chatcontrol.settings.ChannelSettings;

import java.util.Map;

/**
 * 代表一個頻道加載器
 * @author MouBieCat
 */
public final class ChannelsLoader
        extends Loader {

    private static final String CHANNELS_PATH_CHANNELS = "Channels";

    /**
     * 建構子
     */
    public ChannelsLoader() {
        super(MouBieCat.getInstance(), "", "Channels.yml", false);
    }

    /**
     * 處理具有快捷符號的頻道
     * @param channelsBuffer 頻道集合(用於存放)
     */
    public void loadChannels(final @NotNull Map<String, Channel> channelsBuffer) {
        for (final String channelName : this.getStringList(CHANNELS_PATH_CHANNELS)) {
            final @Nullable SimpleChannel simpleChannel = ChannelSettings.getChannel(channelName);

            if (simpleChannel != null)
                channelsBuffer.put(channelName, simpleChannel);

                // 在控制台印出錯誤訊息
            else
                this.logErrorChannel(channelName);
        }
    }

    /**
     * 紀錄頻道沒有找到訊息
     * @param channelName 頻道頻稱
     */
    private void logErrorChannel(final @NotNull String channelName) {
        MouBieCat.getInstance().getDebugger().warning(
                "§e在插件中讀取到頻道 §6" + channelName + " §e，但是卻在 ChatControl 找不到該該頻道名稱。為了避免例外的狀況，因此跳過該頻道的處理。"
        );
    }

}
