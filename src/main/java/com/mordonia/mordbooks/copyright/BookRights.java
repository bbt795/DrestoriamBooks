package com.mordonia.mordbooks.copyright;

import com.mordonia.mordbooks.MordBooks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;

public class BookRights implements Listener {
    MordBooks mordBooks;

    public BookRights(MordBooks mordBooks) {
        this.mordBooks = mordBooks;
    }

    @EventHandler
    public void createRecipie(CraftItemEvent event) {
        ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
        if (event.getInventory().getResult() == null) {
            return;
        }
        if(!event.getInventory().getResult().hasItemMeta()){
            return;
        }
        if(!event.getInventory().getResult().getItemMeta().hasLore()){
            return;
        }
        if (!event.getInventory().getResult().getItemMeta().getLore().contains(ChatColor.translateAlternateColorCodes('&', "&4All rights reserved!"))) {
            return;
        }
        event.getCursor().setAmount(0);
        event.getView().getPlayer().sendMessage(ChatColor.DARK_RED + "You have been copyright striked... better find a lawyer soon!");
        event.getView().getPlayer().closeInventory();

    }
}



