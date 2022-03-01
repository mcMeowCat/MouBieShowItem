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

/**
 * 代表該插件的主要指令
 * @author MouBieCat
 */
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
