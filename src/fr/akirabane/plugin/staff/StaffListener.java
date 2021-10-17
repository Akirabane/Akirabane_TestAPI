package fr.akirabane.plugin.staff;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import fr.akirabane.plugin.Main;

@SuppressWarnings({ "deprecation", "unused" })
public class StaffListener implements Listener {
	private Main main;
  public StaffListener(Main main) {
		// TODO Auto-generated constructor stub
	  this.main = main;
	}

@EventHandler
  public void onInteract(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    Action action = event.getAction();
    ItemStack it = event.getItem();
    if (it == null)
      return; 
    if (it.getType() == Material.COMPASS && 
      it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase(main.getConfig().getString("strings.commandsStaff.staffMenuName").replace("&", "�")) && (
      action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR)) {
      player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1.0F, 1.0F);
      Inventory inv = Bukkit.createInventory(null, 27, "staff");
      ItemStack paper = new ItemStack(Material.PAPER, 1);
      ItemMeta paperM = paper.getItemMeta();
      paperM.setDisplayName("Se TP au spawn");
      paperM.setLore(Arrays.asList(new String[] { "Permet de se TP au spawn" }));
      paperM.addEnchant(Enchantment.DAMAGE_ALL, 10, true);
      paperM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
      paper.setItemMeta(paperM);
      inv.setItem(11, paper);
      ItemStack gm = new ItemStack(Material.ENDER_EYE, 1);
      ItemMeta gmM = gm.getItemMeta();
      gmM.setDisplayName("Changer de gamemode");
      gmM.setLore(Arrays.asList(new String[] { "Permet de changer le Gamemode" }));
      gmM.addEnchant(Enchantment.DAMAGE_ALL, 10, true);
      gmM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
      gm.setItemMeta(gmM);
      inv.setItem(13, gm);
      ItemStack ban = new ItemStack(Material.ANVIL, 1);
      ItemMeta banM = gm.getItemMeta();
      banM.setDisplayName("Bannir un joueur");
      banM.setLore(Arrays.asList(new String[] { "Permet de bannir un joueur" }));
      banM.addEnchant(Enchantment.DAMAGE_ALL, 10, true);
      banM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
      ban.setItemMeta(banM);
      inv.setItem(15, ban);
      player.openInventory(inv);
    } 
  }
  
  @EventHandler
  public void onClick(InventoryClickEvent event) {
    Player player = (Player)event.getWhoClicked();
    ItemStack current = event.getCurrentItem();
    if (current == null)
      return; 
    if (player.getOpenInventory().getTitle().equalsIgnoreCase("staff")) {
      event.setCancelled(true);
      player.closeInventory();
      switch (current.getType()) {
        case PAPER:
          player.performCommand("spawn");
          break;
        case ENDER_EYE:
          player.setGameMode(GameMode.CREATIVE);
          break;
        case ANVIL:
        	Inventory inv2 = Bukkit.createInventory(null, 27, "Ban");
        	ItemStack i1 = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
        	for (Player p : Bukkit.getOnlinePlayers()) {
                SkullMeta meta = (SkullMeta) i1.getItemMeta();
                meta.setDisplayName(p.getName());
                i1.setItemMeta(meta);
                inv2.addItem(new ItemStack[] { i1 });
        		}
        	player.openInventory(inv2);
        	event.setCancelled(true);
        	break;
        default:
        	break;
      } 
    } 
  }
}
