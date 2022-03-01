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

package com.cat.server.component;

import com.cat.server.MouBieCat;
import com.cat.server.component.api.ChannelSender;
import com.cat.server.component.api.HoverEventBuilder;
import com.cat.server.component.api.MaterialLang;
import com.cat.server.component.sender.BasicChannelSender;
import com.cat.server.component.sender.RangeChannelSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineacademy.chatcontrol.SimplePlayerCache;
import org.mineacademy.chatcontrol.api.CheckResult;
import org.mineacademy.chatcontrol.api.EventCancelledException;
import org.mineacademy.chatcontrol.api.channel.Channel;
import org.mineacademy.chatcontrol.lib.fo.Common;
import org.mineacademy.chatcontrol.lib.fo.PlayerUtil;
import org.mineacademy.chatcontrol.listener.ListenerChecker;
import org.mineacademy.chatcontrol.model.FormattedMessage;
import org.mineacademy.chatcontrol.model.InteractiveChat;
import org.mineacademy.chatcontrol.settings.Localization;

/**
 * 帶鰾一個顯示物品的專用頻道
 * @author MouBieCat
 */
public final class ShowItemChannel {

    // 頻道
    @NotNull
    private final Channel channel;

    // 範圍頻道發送者
    @NotNull
    private final ChannelSender rangeSender;

    // 基礎頻道發送者
    @NotNull
    private final ChannelSender basicSender;

    /**
     * 建構子
     * @param channel 頻道
     */
    public ShowItemChannel(final @NotNull Channel channel) {
        this.channel = channel;
        this.rangeSender = new RangeChannelSender(channel);
        this.basicSender = new BasicChannelSender(channel);
    }

    /**
     * 發送顯示物品訊息
     * @return 是否成功發送
     */
    public boolean sendMessage(final @NotNull Player player, final @NotNull ItemStack itemStack) {
        // 如果頻道為禁言狀態、或是玩家有權限繞過
        if (this.channel.isMuted() && !PlayerUtil.hasPerm(player, "chatcontrol.bypass.mute")) {
            Common.tellTimed(1, player, Localization.Channels.MUTED_CANNOT_SPEAK.replace(
                    "{channel}",
                    this.channel.getName()
            ));
            return false;
        }

        // 檢查玩家現在是否可以發言
        if (!this.checkPlayer(player))
            return false;

        // 以玩家狀態獲取該頻道的格式
        final TextComponent component = InteractiveChat.createComponent("", this.channel.getChatFormat(), player);

        // 處理翻譯組件
        final TranslatableComponent translatable = new TranslatableComponent(MaterialLang.get(itemStack.getType()).getTranslation());
        translatable.setHoverEvent(HoverEventBuilder.BuilderOptions.createHoverEvent(itemStack).build());

        // 處理文字
        component.addExtra("§f嘿！我手上有一個酷東西 [§b");
        component.addExtra(translatable);
        component.addExtra("§f]。");

        // 實例訊息發送物件
        final FormattedMessage messageObj = FormattedMessage.fromComponent(component);
        this.sendMessage0(player, messageObj);
        return true;
    }

    /**
     * 發送顯示物品訊息訊息
     * @param sender 發送者
     * @param messageObj 訊息
     */
    private void sendMessage0(final @NotNull Player sender, final @NotNull FormattedMessage messageObj) {
        // 使玩家發言時間設置為現在
        final SimplePlayerCache playerCache = SimplePlayerCache.getFor(sender);
        playerCache.lastMessageTime = System.currentTimeMillis() / 1000L;

        MouBieCat.getInstance().getDebugger().info("ShowItemChannel#show");

        // 處理範圍頻道 或 處理全局頻道
        if (this.channel.getRange() != null)
            this.rangeSender.sendMessage(sender, messageObj);

        else
            this.basicSender.sendMessage(sender, messageObj);
    }

    /**
     * 檢查玩家是否可以發送訊息
     * @return 是否可以
     */
    private boolean checkPlayer(final @NotNull Player player) {
        // 建立結果
        final CheckResult result = new CheckResult("show item command message", player);
        try {
            // 檢查結果
            final @Nullable CheckResult resultBuffer = ListenerChecker.checkMessage(result);

            // 如果原始訊息被修改
            if (resultBuffer == null || resultBuffer.hasMessageChanged())
                return false;

        } catch (final EventCancelledException ignored) {
            return false;
        }
        return true;
    }

    /**
     * 獲取頻道
     * @return 頻道
     */
    @NotNull
    public Channel getChannel() {
        return this.channel;
    }

}
