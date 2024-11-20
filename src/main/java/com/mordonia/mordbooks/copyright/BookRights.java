package com.mordonia.mordbooks.copyright;

import com.mordonia.mordbooks.MordBooks;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

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



