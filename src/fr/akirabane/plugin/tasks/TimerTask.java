package fr.akirabane.plugin.tasks;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class TimerTask extends BukkitRunnable {
  private int timer = 10;
  
  public void run() {
    Bukkit.broadcastMessage("Lancement dans " + this.timer + "s");
    if (this.timer == 0) {
      Bukkit.broadcastMessage("Le jeu se lance !");
      cancel();
    } 
    this.timer--;
  }
}
