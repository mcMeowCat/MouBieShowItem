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

package com.cat.server.component.sender;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.mineacademy.chatcontrol.api.channel.Channel;
import org.mineacademy.chatcontrol.model.FormattedMessage;

import java.util.List;

/**
 * 代表一個一般的頻道發送者(非範圍頻道)
 * @author MouBieCat
 */
public final class BasicChannelSender
        extends ChannelSenderBase {

    /**
     * 建構子
     * @param channel 頻道
     */
    public BasicChannelSender(final @NotNull Channel channel) {
        super(channel);
    }

    /**
     * 發送顯示物品訊息訊息
     * @param sender     發送者
     * @param messageObj 訊息
     */
    @Override
    public void sendMessage(@NotNull Player sender, @NotNull FormattedMessage messageObj) {
        final List<Player> notIgnoringPlayers = this.getNotIgnoringPlayers(
                this.channel.getPlayers().keySet().stream().toList()
        );

        for (final Player serverPlayer : notIgnoringPlayers) {
            // 發送訊息
            messageObj.send(serverPlayer);

            // 發送至 BungeeCord
            this.sendBungeeCord(serverPlayer, messageObj);
        }
    }

}
