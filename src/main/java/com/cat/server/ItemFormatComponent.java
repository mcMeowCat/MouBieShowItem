package com.cat.server;

import com.cat.server.component.MaterialLang;
import com.cat.server.component.api.HoverEventBuilder;
import com.moubieapi.api.builder.Builder;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineacademy.chatcontrol.SimplePlayerCache;
import org.mineacademy.chatcontrol.api.CheckResult;
import org.mineacademy.chatcontrol.api.EventCancelledException;
import org.mineacademy.chatcontrol.lib.fo.Common;
import org.mineacademy.chatcontrol.listener.ListenerChecker;
import org.mineacademy.chatcontrol.model.InteractiveChat;
import org.mineacademy.chatcontrol.model.SimpleChannel;
import org.mineacademy.chatcontrol.settings.Localization;

public final class ItemFormatComponent
        implements Builder<TextComponent>, ItemComponent {

    @NotNull
    private final Player sender;

    @NotNull
    private final SimpleChannel channel;

                                @NotNull
    private final ItemStack itemStack;

    public ItemFormatComponent(final @NotNull Player sender,
                               final @NotNull ItemStack itemStack,
                               final @NotNull SimpleChannel channel) {
        this.sender = sender;
        this.channel = channel;
        this.itemStack = itemStack;
    }

    public void sendItemMessage() {
        if (this.channel.isMuted())
            Common.tellTimed(1, sender, Localization.Channels.MUTED_CANNOT_SPEAK.replace("{channel}", this.channel.getName()));

        else {
            if (!this.checkPlayer(this.sender, "show item command message"))
                return;

            for (final Player player : this.channel.getPlayers().keySet()) {
                final SimplePlayerCache cache = SimplePlayerCache.getFor(player);

                if (!cache.getIgnoredPlayers().contains(this.sender.getName()))
                    player.spigot().sendMessage(this.build());
            }
        }
    }

    @NotNull
    public TextComponent build() {
        final TranslatableComponent translatableComponent =
                new TranslatableComponent(MaterialLang.get(this.itemStack.getType()).getTranslation());

        translatableComponent.setHoverEvent(
                HoverEventBuilder.BuilderOptions.createHoverEvent(this.itemStack).build()
        );

        final TextComponent component = new TextComponent();
        final TextComponent component_player_name = InteractiveChat.createComponent("", this.channel.getChatFormat(), sender);
        final TextComponent component_left = new TextComponent("§f嘿！我有一個很棒的道具，像你們展示 [§b");
        final TextComponent component_right = new TextComponent("§f]。");
        component.addExtra(component_player_name);
        component.addExtra(component_left);
        component.addExtra(translatableComponent);
        component.addExtra(component_right);

        return component;
    }

    /**
     * 檢查玩家是否可以發送訊息
     * @param player 玩家
     * @param message 訊息
     * @return 是否可以
     */
    public boolean checkPlayer(final @NotNull Player player, final @NotNull String message) {
        // 建立結果
        final CheckResult result = new CheckResult(message, player);
        try {
            // 檢查結果
            final @Nullable CheckResult resultBuffer = ListenerChecker.checkMessage(result);

            // 如果原始訊息被修改
            if (resultBuffer == null || resultBuffer.hasMessageChanged())
                return false;

        } catch (final EventCancelledException ignored) { return false; }
        return true;
    }


}
