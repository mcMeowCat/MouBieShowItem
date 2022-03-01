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

import com.moubieapi.moubieapi.reflect.CraftBukkitReflect;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.ItemTag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

/**
 * 物品事件建構器
 * @author MouBieCat
 */
public record ItemHoverEventBuilder(@NotNull ItemStack itemStack)
        implements HoverEventBuilder<ItemStack> {

    private static final String CRAFT_ITEM_STACK_CLASS_PATH = "inventory.CraftItemStack";

    private static final String AS_NMSCopy = "asNMSCopy";

    /**
     * 建構子
     * @param itemStack 物品
     */
    public ItemHoverEventBuilder(final @NotNull ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * 獲取 HoverEvent 接收的物件
     * @return 物件
     */
    @NotNull
    public ItemStack getObject() {
        return this.itemStack;
    }

    /**
     * 建構對象
     * @return 建構好的對象
     */
    @NotNull
    public HoverEvent build() {
        final Class<?> craftItemStackClass = CraftBukkitReflect.getCraftBukkitClass(CRAFT_ITEM_STACK_CLASS_PATH);
        final Method asNMSCopy = CraftBukkitReflect.getMethod(craftItemStackClass, AS_NMSCopy, ItemStack.class);
        final net.minecraft.world.item.ItemStack nmsItemStack =
                (net.minecraft.world.item.ItemStack) CraftBukkitReflect.invoke(asNMSCopy, null, this.itemStack);

        final net.md_5.bungee.api.chat.hover.content.Item md5Item =
                new net.md_5.bungee.api.chat.hover.content.Item(
                        "minecraft:" + itemStack.getType().name().toLowerCase(),
                        nmsItemStack.getCount(),
                        ItemTag.ofNbt(nmsItemStack.getOrCreateTag().toString())
                );

        return new HoverEvent(HoverEvent.Action.SHOW_ITEM, md5Item);
    }

}
