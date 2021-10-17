package fr.akirabane.plugin.extras;

import fr.akirabane.plugin.Main;
import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandsGive implements Listener, CommandExecutor {
  private Main main;
  
  public CommandsGive(Main main) {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player)sender;
      if (cmd.getName().equalsIgnoreCase("GodSword")) {
        ItemStack customsword = new ItemStack(Material.NETHERITE_SWORD, 1);
        ItemMeta customM = customsword.getItemMeta();
        try {
          customM.setDisplayName(this.main.getConfig().getString("strings.commandsGive.godItems.godswordName").replace("&", "§"));
          customM.setLore(Arrays.asList(new String[] { this.main.getConfig().getString("strings.commandsGive.godItems.godsword1").replace("&", "§"), this.main.getConfig().getString("strings.commandsGive.godsword2").replace("&", "§"), this.main.getConfig().getString("strings.commandsGive.godsword3").replace("&", "§"), this.main.getConfig().getString("strings.commandsGive.godsword4").replace("&", "§"), this.main.getConfig().getString("strings.commandsGive.godsword5").replace("&", "§") }));
        } catch (Exception e) {
          System.out.println(e);
        } 
        customM.addEnchant(Enchantment.DAMAGE_ALL, 10, true);
        customM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        customsword.setItemMeta(customM);
        player.getInventory().setItem(0, customsword);
        player.updateInventory();
        player.sendMessage("<GodSword> Mon pouvoir t'appartient à présent " + player.getName());
        System.out.println("Commande réussie");
      } 
      if (cmd.getName().equalsIgnoreCase("GodHoe")) {
        ItemStack customsword = new ItemStack(Material.WOODEN_HOE, 1);
        ItemMeta customM = customsword.getItemMeta();
        try {
          customM.setDisplayName(this.main.getConfig().getString("strings.commandsGive.godItems.godhoeName").replace("&", "§"));
          customM.setLore(Arrays.asList(new String[] { this.main.getConfig().getString("strings.commandsGive.godItems.godhoe1").replace("&", "§"), this.main.getConfig().getString("strings.commandsGive.godItems.godhoe2").replace("&", "§"), this.main.getConfig().getString("strings.commandsGive.godItems.godhoe3").replace("&", "§"), this.main.getConfig().getString("strings.commandsGive.godItems.godhoe4").replace("&", "§"), this.main.getConfig().getString("strings.commandsGive.godItems.godhoe5").replace("&", "§") }));
        } catch (Exception e) {
          System.out.println(e);
        } 
        customM.addEnchant(Enchantment.DAMAGE_ALL, 10, true);
        customM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        customsword.setItemMeta(customM);
        player.getInventory().setItem(0, customsword);
        player.updateInventory();
        player.sendMessage("<GodHoe> Mon pouvoir t'appartient à présent " + player.getName());
        System.out.println("Commande réussie");
      }
    } 
    return false;
  }
}
