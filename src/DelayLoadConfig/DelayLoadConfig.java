package DelayLoadConfig;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import de.Herbystar.TTA.TTA_Methods;
import me.catangelz.plugin.CatPlugin;

public class DelayLoadConfig implements Runnable{

	CatPlugin pl;

	private boolean isRunning = true;

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	@Override
	public void run() {

		while (isRunning == true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			int player = Bukkit.getServer().getOnlinePlayers().size();
			/*if (player > 0) {
				System.out.println("Player count: " + player);
				for (Player p : Bukkit.getOnlinePlayers()) {
					p.sendMessage(ChatColor.BLUE + "World> " + ChatColor.GRAY + "Starting Auto-Save " + ChatColor.YELLOW
							+ "World and Player data.");
					p.sendMessage(ChatColor.BLUE + "World> " + ChatColor.GRAY + "This system will auto-run every "
							+ ChatColor.AQUA + "10 minutes.");
					p.sendMessage(
							ChatColor.BLUE + "World> " + ChatColor.GREEN + "Saving World and Player Data Complete.");
				}
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "save-all");

			}*/
			if(player > 0){
				Date now = new Date();
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
				for(Player player1 : Bukkit.getOnlinePlayers()){
					TTA_Methods.sendTablist(player1, ChatColor.YELLOW+"Player Online", ChatColor.AQUA+"Server Time : "+time.format(now));
					
				}
				if(now.getMinutes() == 0){
					if(now.getSeconds() == 0){
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "save-all");
					Bukkit.broadcastMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"AutoSaving");
					Bukkit.broadcastMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"AutoSave Complete");
					
					}
				}else if(now.getMinutes() == 15){
					if(now.getSeconds() == 0){
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "save-all");
					Bukkit.broadcastMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"AutoSaving");
					Bukkit.broadcastMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"AutoSave Complete");
					
					}
				}else if(now.getMinutes() == 30){
					if(now.getSeconds() == 0){
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "save-all");
					Bukkit.broadcastMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"AutoSaving");
					Bukkit.broadcastMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"AutoSave Complete");
					
					}
				}else if(now.getMinutes() == 45){
					if(now.getSeconds() == 0){
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "save-all");
					Bukkit.broadcastMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"AutoSaving");
					Bukkit.broadcastMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"AutoSave Complete");
					
					}
				}
			}
		}

	}


}
