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

import org.jetbrains.annotations.NotNull;

/**
 * 代表一個可翻譯的
 * @param <T> 可翻譯物件
 * @author MouBieCat
 */
public interface Translatable<T> {

    /**
     * 獲取翻譯金鑰
     * @return 金鑰名稱
     */
    @NotNull String getTranslation();

    /**
     * 獲取材質
     * @return 材質
     */
    @NotNull T get();

}
