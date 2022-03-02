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

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.mineacademy.chatcontrol.api.channel.Channel;
import org.mineacademy.chatcontrol.lib.fo.Common;
import org.mineacademy.chatcontrol.model.FormattedMessage;
import org.mineacademy.chatcontrol.settings.Localization;

import java.util.LinkedList;
import java.util.List;

/**
 * 代表一個範圍頻道的發送者類
 * @author MouBieCat
 */
public final class RangeChannelSender
        extends ChannelSenderBase {

    /**
     * 建構子
     * @param channel 頻道
     */
    public RangeChannelSender(final @NotNull Channel channel) {
        super(channel);
    }

    /**
     * 發送顯示物品訊息訊息
     * @param sender     發送者
     * @param messageObj 訊息
     */
    public void sendMessage(final @NotNull Player sender, final @NotNull FormattedMessage messageObj) {
        final List<Player> notIgnoringPlayers = this.getNotIgnoringPlayers(
                this.channel.getPlayers().keySet().stream().toList()
        );

        final List<Player> inRangePlayers = this.getInRangePlayers(sender, notIgnoringPlayers);
        for (final Player serverPlayer : inRangePlayers) {
            // 發送訊息
            messageObj.send(serverPlayer);

            // 發送至 BungeeCord
            this.sendBungeeCord(serverPlayer, messageObj);
        }

        // 判斷附近是否有人，否則發送附近無人接收的訊息
        if (inRangePlayers.size() <= 1)
            Common.tellTimed(4, sender, Localization.CHAT_OUT_OF_RANGE);
    }

    /**
     * 獲取在距離範圍內的玩家
     * @param sender 發送者
     * @param players 玩家集合
     * @return 範圍玩家集合
     */
    private List<Player> getInRangePlayers(final @NotNull Player sender, final @NotNull List<Player> players) {
        final List<Player> rangePlayers = new LinkedList<>();

        final Location senderLocation = sender.getLocation();
        for (final Player serverPlayer : players) {
            if (senderLocation.distance(serverPlayer.getLocation()) <= this.channel.getRange().getRangeBlocks())
                rangePlayers.add(serverPlayer);
        }
        return rangePlayers;
    }

}
