package fr.akirabane.plugin.commands;

import fr.akirabane.plugin.Main;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
  private Main main;
  
  public Commands(Main main) {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
    if (sender instanceof Player) {
    	
      Player player = (Player)sender;
      
      if (cmd.getName().equalsIgnoreCase("Roll")) {
    	  if(args.length == 0) {
    		  Random ran = new Random();
    		  player.sendMessage("Vous avez fait : " + ran.nextInt(100));    		  
    	  } else {
    		  player.sendMessage("Trop d'arguments, veuillez juste écrire /roll.");
    	  }
      } 
      
      if (cmd.getName().equalsIgnoreCase("Setspawn")) {
    	  if(args.length == 0) {
    		  Location ploc = player.getLocation();
    		  Location spawn = new Location(player.getWorld(), ploc.getX(), ploc.getY(), ploc.getZ(), ploc.getPitch(), ploc.getYaw());
    		  player.getServer().getWorld("World").setSpawnLocation(spawn);
    		  player.sendMessage("Nouveau point de spawn défini");    		  
    	  } else {
    		  player.sendMessage("Trop d'arguments, veuillez juste écrire /setspawn.");
    	  }
      } 
      
      if (cmd.getName().equalsIgnoreCase("Spawn")) {
    	  if(args.length == 0) {
    		  Location spawnLoc = player.getServer().getWorld("World").getSpawnLocation();
    		  Location spawn = new Location(player.getWorld(), spawnLoc.getX(), spawnLoc.getY(), spawnLoc.getZ(), spawnLoc.getPitch(), spawnLoc.getYaw());
    		  player.teleport(spawn);    		  
    	  } else {
    		  player.sendMessage("Trop d'arguments, veuillez juste écrire /spawn.");
    	  }
      } 
      
      if (cmd.getName().equalsIgnoreCase("Check")) {
    	  if(args.length == 0) {
    		  player.sendMessage(this.main.getConfig().getString("strings.commands.check").replace("&", "§"));
    		  return true;    		  
    	  } else {
    		  player.sendMessage("Trop d'arguments, veuillez juste écrire /check.");
    	  }
      } 
      
      if (cmd.getName().equalsIgnoreCase("Alerte")) {
        if (args.length == 0)
          player.sendMessage("Erreur, tu dois prton message -> /Alerte <message>"); 
        if (args.length >= 1) {
          StringBuilder alerte = new StringBuilder();
          byte b;
          int i;
          String[] arrayOfString;
          for (i = (arrayOfString = args).length, b = 0; b < i; ) {
            String part = arrayOfString[b];
            alerte.append(String.valueOf(part) + " ");
            b++;
          } 
          Bukkit.broadcastMessage("[" + player.getName() + "] " + alerte.toString());
        } 
        return true;
      } 
    } 
    return false;
  }
  
}
