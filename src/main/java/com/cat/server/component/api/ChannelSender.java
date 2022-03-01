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

package com.cat.server.component.api;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.mineacademy.chatcontrol.api.channel.Channel;
import org.mineacademy.chatcontrol.model.FormattedMessage;

/**
 * 代表一種頻道發送訊息的處理方式
 * @author MouBieCat
 */
public interface ChannelSender {

    /**
     * 發送顯示物品訊息訊息
     * @param sender 發送者
     * @param messageObj 訊息
     */
    void sendMessage(final @NotNull Player sender, final @NotNull FormattedMessage messageObj);

    /**
     * 獲取頻道
     * @return 頻道
     */
    @NotNull Channel getChannel();

}
