package com.cat.server.commands;

import com.cat.server.MouBieCat;
import com.moubieapi.api.commands.SenderType;
import com.moubieapi.moubieapi.commands.CommandMainNodeAbstract;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class CommandMain
        extends CommandMainNodeAbstract {

    /**
     * 建構子
     * @param pluginCommand 插劍指令
     */
    public CommandMain(final @NotNull PluginCommand pluginCommand) {
        super(pluginCommand, SenderType.PLAYER_SENDER);
    }

    /**
     * 當指令被觸發
     * @param sender 指令發送者
     * @param strings 參數
     * @return 是否運行成功
     */
    public boolean onCommand(final @NotNull CommandSender sender, final @NotNull String[] strings) {
        final Player player = (Player) sender;
        return MouBieCat.getInstance().getShowItemChannel().sendMessage(player, player.getInventory().getItemInMainHand());
    }

    /**
     * 當指令幫助TAB被觸發
     * @param commandSender 指令發送者
     * @param strings 參數
     * @return 幫助訊息集合
     */
    @NotNull
    public List<String> onTab(final @NotNull CommandSender commandSender, final @NotNull String[] strings) {
        return new ArrayList<>();
    }

}
