package fr.akirabane.plugin.staff;

import fr.akirabane.plugin.Main;
import java.util.Arrays;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CommandsStaff implements CommandExecutor {
  private Main main;
  
  public CommandsStaff(Main main) {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player)sender;
      if (cmd.getName().equalsIgnoreCase("Staff")) {
        ItemStack customsword = new ItemStack(Material.COMPASS, 1);
        ItemMeta customM = customsword.getItemMeta();
        try {
          customM.setDisplayName(this.main.getConfig().getString("strings.commandsStaff.staffMenuName").replace("&", "§"));
          customM.setLore(Arrays.asList(new String[] { this.main.getConfig().getString("strings.commandsStaff.menuLore1").replace("&", "§") }));
        } catch (Exception e) {
          System.out.println(e);
        } 
        customM.addEnchant(Enchantment.DAMAGE_ALL, 10, true);
        customM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        customsword.setItemMeta(customM);
        player.getInventory().clear();
        player.getInventory().setItem(4, customsword);
        player.updateInventory();
        player.sendMessage("<Server> Mode staff activé pour " + player.getName());
        System.out.println("Commande réussie");
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 9999999, 2));
        player.playSound(player.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1.0F, 1.0F);
      } else if (cmd.getName().equalsIgnoreCase("Unstaff")) {
        player.getInventory().clear();
        player.updateInventory();
        player.sendMessage("<Server> Mode staff désactivé pour " + player.getName());
        System.out.println("Commande réussie");
        player.removePotionEffect(PotionEffectType.INVISIBILITY);
        player.playSound(player.getLocation(), Sound.ENTITY_SPLASH_POTION_BREAK, 1.0F, 1.0F);
      } 
      
      if (cmd.getName().equalsIgnoreCase("Gban")) {
    	  if(args.length == 0) {
    		  player.sendMessage("Veuillez indiquer le pseudo du joueur à bannir.");
    	  } else if(args.length == 1) {
    		  Player target = Bukkit.getServer().getPlayer(args[0]);
    		  target.kickPlayer("Vous avez été banni par " + sender.getName());
    		  Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), "Banni à vie", null, sender.getName());
    	  } else if (args.length == 2) {
    		  Player target = Bukkit.getServer().getPlayer(args[0]);
    		  String reason = args[1];
    		  target.kickPlayer("Vous avez été banni par " + sender.getName());
    		  Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), reason, null, sender.getName());
    	  } /*else if (args.length == 3) {
    		  Player target = Bukkit.getServer().getPlayer(args[0]);
    		  String reason = args[1];
    		  String time = args[2];
    		  target.kickPlayer("Vous avez été banni par " + sender.getName());
    		  Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), reason, null, time);
    	  }*/
      }
      
    } 
    return false;
  }
}
