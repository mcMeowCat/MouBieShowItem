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

package com.cat.server;

import com.cat.server.component.api.PluginMessage;
import com.moubieapi.moubieapi.yaml.utils.PluginFileLoaderAbstract;
import org.jetbrains.annotations.NotNull;

/**
 * 代表一個訊息加載器
 * @author MouBieCat
 */
public final class MessageLoader
        extends PluginFileLoaderAbstract
        implements PluginMessage {

    private static final String MESSAGE_NOT_CHANNEL = "NotChannel";

    private static final String MESSAGE_HELP_MESSAGE = "HelpMessage";

    private static final String MESSAGE_SHOW_MESSAGE = "ShowMessage";

    /**
     * 建構子
     */
    public MessageLoader() {
        super(MouBieCat.getInstance(), "", "Message.yml");
    }

    /**
     * 獲取找不到頻道的訊息
     * @return 訊息
     */
    @NotNull
    public String getNotChannelMessage() {
        return this.getString(MESSAGE_NOT_CHANNEL);
    }

    /**
     * 獲取幫助訊息
     * @return 訊息
     */
    @NotNull
    public String getHelpMessage() {
        return this.getString(MESSAGE_HELP_MESSAGE);
    }

    /**
     * 獲取展示物品訊息
     * @return 訊息
     */
    @NotNull
    public String getShowMessage() {
        return this.getString(MESSAGE_SHOW_MESSAGE);
    }

}
