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

package com.cat.server.component.api;

import com.moubieapi.api.builder.Builder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * 懸停事件建構器
 * @param <O> 可以被 HoverEvent 接受的物件
 * @author MouBieCat
 */
public interface HoverEventBuilder<O>
        extends Builder<HoverEvent> {

    /**
     * 獲取 HoverEvent 接收的物件
     * @return 物件
     */
    @NotNull O getObject();


    /**
     * 代表為可建構的對象選項
     * @author MouBieCat
     */
    class BuilderOptions {

        /**
         * 創建新對象
         * @param itemStack 物品
         * @return 創建的對象
         */
        public static HoverEventBuilder<ItemStack> createHoverEvent(final @NotNull ItemStack itemStack) {
            return new ItemHoverEventBuilder(itemStack);
        }

    }

}
