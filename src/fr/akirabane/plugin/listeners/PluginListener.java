package fr.akirabane.plugin.listeners;

import fr.akirabane.plugin.Main;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class PluginListener implements Listener {
  private Main main;
  
  public PluginListener(Main main) {
    this.main = main;
  }
  
  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    Bukkit.broadcastMessage("[" + player.getName() + "] " + "viens de rejoindre le serveur.");
  }
  
  @EventHandler
  public void onInteract(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    Action action = event.getAction();
    ItemStack it = event.getItem();
    if (event.getClickedBlock() != null && action == Action.RIGHT_CLICK_BLOCK) {
      BlockState bs = event.getClickedBlock().getState();
      if (bs instanceof Sign) {
        Sign s = (Sign)bs;
        if (s.getLine(0).equalsIgnoreCase("[BedWars]") || s.getLine(1).equalsIgnoreCase("Rejoindre"))
          if (s.getLine(2) != null) {
            String servername = s.getLine(2);
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            try {
              out.writeUTF("Connect");
              out.writeUTF(servername);
            } catch (Exception e) {
              e.printStackTrace();
            } 
            player.sendPluginMessage((Plugin)this.main, "BungeeCord", b.toByteArray());
          }  
      } 
    } 
    if (it == null)
      return; 
    if (it.getType() == Material.WOODEN_HOE)
      if (it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase(main.getConfig().getString("strings.commandsGive.godItems.godhoeName").replace("&", "§")))
        if (action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) {
          player.sendMessage("Il s'agit de " + event.getClickedBlock());
          player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
        }   
  }
}
