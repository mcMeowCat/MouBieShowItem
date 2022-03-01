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
import org.jetbrains.annotations.Nullable;
import org.mineacademy.chatcontrol.model.SimpleChannel;
import org.mineacademy.chatcontrol.settings.ChannelSettings;

/**
 * 代表一個頻道加載器
 * @author MouBieCat
 */
public final class ChannelLoader
        extends Loader {

    /**
     * 建構子
     */
    public ChannelLoader() {
        super(MouBieCat.getInstance(), "", "Channel.yml", false);
    }

    /**
     * 獲取用於顯示物品的頻道
     * @return 頻道
     */
    @Nullable
    public SimpleChannel getShowItemChannel() {
        final @Nullable SimpleChannel channel = ChannelSettings.getChannel(this.getString("Channel"));

        if (channel == null) {
            MouBieCat.getInstance().getDebugger().warning("");
            return null;
        }

        return channel;
    }

}
