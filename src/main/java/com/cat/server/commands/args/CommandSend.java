package com.cat.server.commands.args;

import com.cat.server.MouBieCat;
import com.cat.server.component.ShowItemChannel;
import com.moubieapi.api.commands.SenderType;
import com.moubieapi.moubieapi.commands.CommandNodeAbstract;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineacademy.chatcontrol.api.channel.Channel;

import java.util.ArrayList;
import java.util.List;

public final class CommandSend
        extends CommandNodeAbstract {

    /**
     * 建構子
     */
    public CommandSend(final int nodeId, final @NotNull String cmdName) {
        super(nodeId, cmdName, SenderType.PLAYER_SENDER, "將展示物品訊息發送到指定頻道", 2);
    }

    /**
     * 當指令被觸發
     * @param sender 指令發送者
     * @param args 參數
     * @return 是否運行成功
     */
    public boolean onCommand(final @NotNull CommandSender sender, final @NotNull String[] args) {
        final Player player = (Player) sender;

        final @Nullable Channel channel =
                MouBieCat.getInstance().getCanShowItemChannels().get(args[1]);

        if (channel != null) {
            final ShowItemChannel showItemChannel = new ShowItemChannel(channel);
            return showItemChannel.sendMessage(player, player.getInventory().getItemInMainHand());
        }

        sender.sendMessage(MouBieCat.getInstance().getMessageLoader().getNotChannelMessage());
        return false;
    }

    /**
     * 當指令幫助TAB被觸發
     * @param sender 指令發送者
     * @param args 參數
     * @return 幫助訊息集合
     */
    @NotNull
    public List<String> onTab(final @NotNull CommandSender sender, final @NotNull String[] args) {
        return new ArrayList<>(MouBieCat.getInstance().getCanShowItemChannels().keySet());
    }

}
