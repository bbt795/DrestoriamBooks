package com.mordonia.mordbooks.commands;

import com.mordonia.mcore.MCore;
import com.mordonia.mcore.MCoreAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Commands implements CommandExecutor {
    private MCoreAPI mCoreAPI;
    private List<String> theme;
    public Commands(MCoreAPI mCoreAPI){
        this.mCoreAPI = mCoreAPI;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("mb")){
            Player player = (Player) sender;
            if(args.length < 1){
                return false;
            }
            if(args[0].equalsIgnoreCase("edit")){
                if(!player.hasPermission("mb.edit")){
                    player.sendMessage(ChatColor.GOLD + "[" + ChatColor.BLUE + "MordBooks" + ChatColor.GOLD  + "]" + ChatColor.DARK_RED + "You do not have enough permission to run this command!");
                    return false;
                }
               if(!player.getInventory().getItemInMainHand().getType().equals(Material.WRITTEN_BOOK)){
                   player.sendMessage(ChatColor.GOLD + "[" + ChatColor.BLUE + "MordBooks" + ChatColor.GOLD  + "]" + ChatColor.BLUE + "You must be holding a book to use this command!");
                   return false;
               }
               ItemStack book = player.getInventory().getItemInMainHand();
               BookMeta bookMeta = (BookMeta) book.getItemMeta();
               ItemStack newBook = new ItemStack(Material.WRITABLE_BOOK);
               newBook.setItemMeta(bookMeta);
               player.getInventory().setItemInMainHand(newBook);
               return false;
            }
            if(args[0].equalsIgnoreCase("sign")){
                if(!player.hasPermission("mb.sign")){
                    player.sendMessage(ChatColor.GOLD + "[" + ChatColor.BLUE + "MordBooks" + ChatColor.GOLD  + "]" + ChatColor.DARK_RED + "You do not have enough permission to run this command!");
                    return false;
                }
                if(!player.getInventory().getItemInMainHand().getType().equals(Material.WRITTEN_BOOK)){
                    player.sendMessage(ChatColor.GOLD + "[" + ChatColor.BLUE + "MordBooks" + ChatColor.GOLD  + "]" + ChatColor.BLUE + "You must be holding a book to use this command!");
                    return false;
                }
                String uuid = player.getUniqueId().toString();
                ItemStack book = player.getInventory().getItemInMainHand();
                BookMeta bookMeta = (BookMeta) book.getItemMeta();
                String name = mCoreAPI.getmPlayerManager().getPlayerMap().get(uuid).getmName().getName();
                bookMeta.setAuthor(ChatColor.translateAlternateColorCodes('&',name ));
                ItemStack newBook = new ItemStack(Material.WRITTEN_BOOK);
                newBook.setItemMeta(bookMeta);
                player.getInventory().setItemInMainHand(newBook);
                return false;
            }
            if(args[0].equalsIgnoreCase("copyright")){
                if(!player.hasPermission("mb.copyright")){
                    player.sendMessage(ChatColor.GOLD + "[" + ChatColor.BLUE + "MordBooks" + ChatColor.GOLD  + "]" + ChatColor.DARK_RED + "You do not have enough permission to run this command!");
                    return false;
                }
                if(!player.getInventory().getItemInMainHand().getType().equals(Material.WRITTEN_BOOK)){
                    player.sendMessage(ChatColor.GOLD + "[" + ChatColor.BLUE + "MordBooks" + ChatColor.GOLD  + "]" + ChatColor.BLUE + "You must be holding a book to use this command!");
                    return false;
                }
                ItemStack book = player.getInventory().getItemInMainHand();
                BookMeta bookMeta = (BookMeta) book.getItemMeta();
                ArrayList<String> copyrightLore = new ArrayList<>();
                copyrightLore.add(ChatColor.translateAlternateColorCodes('&', "&4All rights reserved!"));
                bookMeta.setLore(copyrightLore);
                ItemStack newBook = new ItemStack(Material.WRITTEN_BOOK);
                newBook.setItemMeta(bookMeta);
                player.getInventory().setItemInMainHand(newBook);
                return false;
            }
            if(args[0].equalsIgnoreCase("clear")){
                if(!player.hasPermission("mb.clear")){
                    player.sendMessage(ChatColor.GOLD + "[" + ChatColor.BLUE + "MordBooks" + ChatColor.GOLD  + "]" + ChatColor.DARK_RED + "You do not have enough permission to run this command!");
                    return false;
                }
                if(!player.getInventory().getItemInMainHand().getType().equals(Material.WRITTEN_BOOK)){
                    player.sendMessage(ChatColor.GOLD + "[" + ChatColor.BLUE + "MordBooks" + ChatColor.GOLD  + "]" + ChatColor.BLUE + "You must be holding a book to use this command!");
                    return false;
                }
                player.getInventory().setItemInMainHand(new ItemStack(Material.WRITABLE_BOOK));
            }
            if(args[0].equalsIgnoreCase("theme")){
                if(!player.hasPermission("mb.theme")){
                    player.sendMessage(ChatColor.GOLD + "[" + ChatColor.BLUE + "MordBooks" + ChatColor.GOLD  + "]" + ChatColor.DARK_RED + "You do not have enough permission to run this command!");
                    return false;
                }
                if(!player.getInventory().getItemInMainHand().getType().equals(Material.WRITTEN_BOOK)){
                    player.sendMessage(ChatColor.GOLD + "[" + ChatColor.BLUE + "MordBooks" + ChatColor.GOLD  + "]" + ChatColor.BLUE + "You must be holding a book to use this command!");
                    return false;
                }
                ItemStack book = player.getInventory().getItemInMainHand();
                BookMeta bookMeta = (BookMeta) book.getItemMeta();
                if(bookMeta.getLore() != null){
                    theme = bookMeta.getLore();
                }
                else{
                   theme = new ArrayList<>();
                }
                if(args.length < 2){
                    return false;
                }
                String themeString = args[1];
                if(themeString.equalsIgnoreCase("remove")){
                    List<String> newLore = new ArrayList<>();
                    bookMeta.setLore(newLore);
                    ItemStack newBook = new ItemStack(Material.WRITTEN_BOOK);
                    newBook.setItemMeta(bookMeta);
                    player.getInventory().setItemInMainHand(newBook);
                    return true;
                }
                theme.add( ChatColor.translateAlternateColorCodes('&', themeString));
                bookMeta.setLore(theme);
                ItemStack newBook = new ItemStack(Material.WRITTEN_BOOK);
                newBook.setItemMeta(bookMeta);
                player.getInventory().setItemInMainHand(newBook);
                return false;
            }
            if(args[0].equalsIgnoreCase("title")){
                if(!player.hasPermission("mb.title")){
                    player.sendMessage(ChatColor.GOLD + "[" + ChatColor.BLUE + "MordBooks" + ChatColor.GOLD  + "]" + ChatColor.DARK_RED + "You do not have enough permission to run this command!");
                    return false;
                }
                if(!player.getInventory().getItemInMainHand().getType().equals(Material.WRITTEN_BOOK)){
                    player.sendMessage(ChatColor.GOLD + "[" + ChatColor.BLUE + "MordBooks" + ChatColor.GOLD  + "]" + ChatColor.BLUE + "You must be holding a book to use this command!");
                    return false;
                }
                ItemStack book = player.getInventory().getItemInMainHand();
                BookMeta bookMeta = (BookMeta) book.getItemMeta();
                if(args.length < 2){
                    return false;
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < args.length; i++){
                    sb.append(args[i]).append(" ");
                }
                String allArgs = ChatColor.translateAlternateColorCodes('&', sb.toString().trim());
                bookMeta.setTitle(allArgs);
                ItemStack newBook = new ItemStack(Material.WRITTEN_BOOK);
                newBook.setItemMeta(bookMeta);
                player.getInventory().setItemInMainHand(newBook);
                return false;
            }

        }
        return false;
    }
}
