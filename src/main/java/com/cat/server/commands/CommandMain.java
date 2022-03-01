package com.cat.server.commands;

import com.cat.server.component.ShowItemChannel;
import com.moubieapi.api.commands.SenderType;
import com.moubieapi.moubieapi.commands.CommandMainNodeAbstract;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.mineacademy.chatcontrol.model.SimpleChannel;
import org.mineacademy.chatcontrol.settings.ChannelSettings;

import java.util.ArrayList;
import java.util.List;

public final class CommandMain
        extends CommandMainNodeAbstract {

    public CommandMain(final @NotNull PluginCommand pluginCommand) {
        super(pluginCommand, SenderType.PLAYER_SENDER);
    }

    public boolean onCommand(final @NotNull CommandSender sender, final @NotNull String[] strings) {
        final Player player = (Player) sender;
        final SimpleChannel channel = ChannelSettings.getChannel("遊戲頻道");

        final ShowItemChannel showItemChannel = new ShowItemChannel(channel);
        return showItemChannel.sendMessage(player, player.getInventory().getItemInMainHand());
    }

    @NotNull
    public List<String> onTab(final @NotNull CommandSender commandSender, final @NotNull String[] strings) {
        return new ArrayList<>();
    }

}
