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

import com.cat.server.component.api.ChannelSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.mineacademy.chatcontrol.SimplePlayerCache;
import org.mineacademy.chatcontrol.api.channel.Channel;
import org.mineacademy.chatcontrol.model.FormattedMessage;

import java.util.LinkedList;
import java.util.List;

/**
 * 代表一個頻道發送者的基礎類
 * @author MouBieCat
 */
public abstract class ChannelSenderBase
        implements ChannelSender {

    // 頻道資訊
    @NotNull
    protected final Channel channel;

    /**
     * 建構子
     * @param channel 頻道
     */
    public ChannelSenderBase(final @NotNull Channel channel) {
        this.channel = channel;
    }

    /**
     * 獲取不是禁言該玩家的玩家列表
     * @param players 實體集合
     * @return 玩家集合
     */
    protected final <T extends Entity> List<Player> getNotIgnoringPlayers(final @NotNull List<T> players) {
        final List<Player> newPlayers = new LinkedList<>();
        for (final Entity entity : players)
            if (entity instanceof Player player)
                // 如果玩家沒有隱藏了該玩家的訊息
                if (!SimplePlayerCache.getFor(player).isIgnoring(player))
                    newPlayers.add(player);

        return newPlayers;
    }

    /**
     * 發送消息至 BungeeCord
     */
    protected final void sendBungeeCord(final @NotNull Player player, final @NotNull FormattedMessage messageObj) {
        if (this.channel.isBungee())
            messageObj.sendBungeeChannel(player, this.channel.getName());
    }

    /**
     * 獲取頻道
     * @return 頻道
     */
    @NotNull
    public final Channel getChannel() {
        return this.channel;
    }

}
