package q1zz.klasa;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {
	
    public void onEnable() {
    	getServer().getPluginManager().registerEvents(this, this);
    }
    private HashMap<String, Long> cooldown = new HashMap<>();
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if(this.cooldown.containsKey(player.getName().toLowerCase())) {
            long time = this.cooldown.get(player.getName().toLowerCase());
            long pTime = time - System.currentTimeMillis();
            int t = (int) pTime / 1000;
            if (!player.hasPermission("zc.bypass") && time > System.currentTimeMillis()) {
                player.kickPlayer("" + org.bukkit.ChatColor.RED + "Kolejny raz bêdziesz móg³ siê po³¹czyæ za: " + org.bukkit.ChatColor.AQUA + t + "s" + org.bukkit.ChatColor.RED + "." +  "\n" + "Cooldown plugin by q1zZ");
                return;
            }
        }
        this.cooldown.put(player.getName().toLowerCase(), System.currentTimeMillis() + 30000);
    }
}