package fr.akirabane.plugin;

import fr.akirabane.plugin.commands.Commands;
import fr.akirabane.plugin.extras.CommandsGive;
import fr.akirabane.plugin.listeners.PluginListener;
import fr.akirabane.plugin.staff.CommandsStaff;
import fr.akirabane.plugin.staff.StaffListener;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
  @Override
  public void onEnable() {
    System.out.println("Le plugin est demarre !");
    saveDefaultConfig();
    
    getCommand("check").setExecutor((CommandExecutor)new Commands(this));
    getCommand("alerte").setExecutor((CommandExecutor)new Commands(this));
    getCommand("spawn").setExecutor((CommandExecutor)new Commands(this));
    getCommand("roll").setExecutor((CommandExecutor)new Commands(this));
    getCommand("setspawn").setExecutor((CommandExecutor)new Commands(this));
    
    getCommand("godsword").setExecutor((CommandExecutor)new CommandsGive(this));
    getCommand("godhoe").setExecutor((CommandExecutor)new CommandsGive(this));
    getServer().getPluginManager().registerEvents((Listener)new CommandsGive(this), (Plugin)this);
    
    getServer().getPluginManager().registerEvents((Listener)new PluginListener(this), (Plugin)this);
    
    getServer().getPluginManager().registerEvents((Listener)new StaffListener(this), (Plugin)this);
    
    getCommand("staff").setExecutor((CommandExecutor)new CommandsStaff(this));
    getCommand("unstaff").setExecutor((CommandExecutor)new CommandsStaff(this));
    getCommand("gban").setExecutor((CommandExecutor)new CommandsStaff(this));

    getServer().getMessenger().registerOutgoingPluginChannel((Plugin)this, "BungeeCord");
  }
  
  public void onDisable() {
    System.out.println("Le plugin est eteint");
  }
}
