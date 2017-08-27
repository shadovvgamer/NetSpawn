package NetSpawn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class NetSpawn
  extends JavaPlugin
  implements Listener
{
  public static NetSpawn plugin;
  
  @Override
public void onEnable()
  {
    Bukkit.getServer().getPluginManager().registerEvents(this, this);
    getConfig().options().copyDefaults(true);
    saveConfig();
  }
  
  @Override
public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args)
  {
	  if (cmd.getName().equalsIgnoreCase("setspawn"))
	    {
	      Player p = (Player)sender;
	      if (!p.hasPermission("net.spawn"))
	      {
	        p.sendMessage(ChatColor.RED + "Nie masz uprawnien!");
	      }
	      else
	      {
	        World world = p.getWorld();
	        Location loc = p.getLocation();
	        world.setSpawnLocation(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ());
	        p.sendMessage(ChatColor.GREEN + "[NetSpawn] " + NetSpawn.this.getConfig().getString("MsgSetSpawn"));
	      }
	      return true;
	    }
    if (cmd.getName().equalsIgnoreCase("spawn"))
    {
      Player p = (Player)sender;
      p.sendMessage(ChatColor.RED + "[NetSpawn] "  + NetSpawn.this.getConfig().getString("Msg"));
    }
    getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable()
    {
      @Override
	public void run()
      {
        Player p = (Player)sender;
        p.teleport(p.getWorld().getSpawnLocation());
        sender.sendMessage(ChatColor.DARK_AQUA + "[NetSpawn] " + NetSpawn.this.getConfig().getString("MsgSpawn"));
      }
    }, 140L);
    return true;
  }
}