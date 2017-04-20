package me.catangelz.plugin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBucketEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.block.Sign;

import com.xxmicloxx.NoteBlockAPI.NBSDecoder;
import com.xxmicloxx.NoteBlockAPI.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.Song;
import com.xxmicloxx.NoteBlockAPI.SongPlayer;

import de.Herbystar.TTA.TTA_Methods;



public class CatListener implements Listener{
	CatPlugin pl;
	
	public void playCircularEffect(Location location, Effect effect, boolean v){
		for(int i = 0; i <= 8; i += ((!v && (i==3)) ? 2 : 1))location.getWorld().playEffect(location, effect, i);
	}
	@EventHandler
	public void Death(PlayerDeathEvent event){
		Player player = event.getEntity().getPlayer();
		
		if(player.getName().equalsIgnoreCase("_ZeroKarA")){
			for(Player player1 : Bukkit.getOnlinePlayers()){
				TTA_Methods.sendTitle(player1, ChatColor.GOLD+player.getName()+ChatColor.LIGHT_PURPLE+" D"+ChatColor.AQUA+"e"+ChatColor.GREEN+"a"+ChatColor.RED+"t"+ChatColor.BLUE+"h", 10, 100, 10, ChatColor.DARK_RED+event.getDeathMessage(), 10, 100, 10);
				
				//player1.playSound(player1.getLocation(), Sound.ENTITY_WITHER_DEATH, 10, 1);
				Song s = NBSDecoder.parse(new File("plugins/CatPlugin/dead_remix.nbs"));
				SongPlayer sp = new RadioSongPlayer(s);
				sp.setAutoDestroy(true);
				sp.addPlayer(player1);
				sp.setPlaying(true);
			
			}
			
		}else{
			for(Player player1 : Bukkit.getOnlinePlayers()){
			TTA_Methods.sendTitle(player1, ChatColor.GOLD+player.getName()+ChatColor.LIGHT_PURPLE+" D"+ChatColor.AQUA+"e"+ChatColor.GREEN+"a"+ChatColor.RED+"t"+ChatColor.BLUE+"h", 10, 100, 10, ChatColor.DARK_RED+event.getDeathMessage(), 10, 100, 10);
			
			//player1.playSound(player1.getLocation(), Sound.ENTITY_WITHER_DEATH, 10, 1);
			Song s = NBSDecoder.parse(new File("plugins/CatPlugin/dead.nbs"));
			SongPlayer sp = new RadioSongPlayer(s);
			sp.setAutoDestroy(true);
			sp.addPlayer(player1);
			sp.setPlaying(true);
		
		}
		}
		event.setDeathMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+event.getDeathMessage());
		
	}
	@EventHandler
	public void Join(PlayerJoinEvent event){
		Player player = event.getPlayer();
		player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
		TTA_Methods.sendTablist(player, ChatColor.YELLOW+"Player Online", null);	
		TTA_Methods.sendTitle(player, ChatColor.GREEN+"Smilekrub"+ChatColor.AQUA+" Server"+ChatColor.WHITE+" 2.1.1", 10, 40, 10, ChatColor.GREEN+"SK "+ChatColor.WHITE+"[2.1.1]", 10, 40, 10);
		
		if(player.getName().equalsIgnoreCase("CatAngelz")){
			player.setPlayerListName(ChatColor.GREEN+""+ChatColor.BOLD+"D"+ChatColor.WHITE+player.getName());
			event.setJoinMessage(ChatColor.AQUA+""+ChatColor.BOLD+"Join> "+ChatColor.GREEN+"[Developer]"+ChatColor.YELLOW+player.getName());
			
		}else if(player.getName().equalsIgnoreCase("BossMadwolf")){
			player.setPlayerListName(ChatColor.DARK_GREEN+""+ChatColor.BOLD+"M"+ChatColor.WHITE+player.getName());
			event.setJoinMessage(ChatColor.AQUA+""+ChatColor.BOLD+"Join> "+ChatColor.DARK_GREEN+"[Mod]"+ChatColor.YELLOW+player.getName());
			
		}else if(player.getName().equalsIgnoreCase("_ZeroKarA")){
			player.setPlayerListName(ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"M"+ChatColor.WHITE+player.getName());
			event.setJoinMessage(ChatColor.AQUA+""+ChatColor.BOLD+"Join> "+ChatColor.DARK_PURPLE+"[Miner]"+ChatColor.YELLOW+player.getName());
			
		}
		else if(player.getName().equalsIgnoreCase("Protazua")){
			player.setPlayerListName(ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"LS"+ChatColor.WHITE+player.getName());
			event.setJoinMessage(ChatColor.AQUA+""+ChatColor.BOLD+"Join> "+ChatColor.LIGHT_PURPLE+"[Livestock]"+ChatColor.YELLOW+player.getName());
			
		}
		else if(player.getName().equalsIgnoreCase("Phantom_120")){
			player.setPlayerListName(ChatColor.BLUE+"Phantom_Bot");
			event.setJoinMessage(ChatColor.AQUA+""+ChatColor.BOLD+"Join> "+ChatColor.BLUE+"[Bot]"+ChatColor.YELLOW+player.getName());
			
		}
		else if(player.getName().equalsIgnoreCase("N0cturnal_")){
			player.setPlayerListName(ChatColor.DARK_RED+""+ChatColor.BOLD+"FS"+ChatColor.WHITE+player.getName());
			event.setJoinMessage(ChatColor.AQUA+""+ChatColor.BOLD+"Join> "+ChatColor.DARK_RED+"[FiveStar]"+ChatColor.YELLOW+player.getName());
			
		}
		else if(player.getName().equalsIgnoreCase("SharpKunG1412")){
			player.setPlayerListName(ChatColor.GOLD+""+ChatColor.BOLD+"R"+ChatColor.WHITE+player.getName());
			event.setJoinMessage(ChatColor.AQUA+""+ChatColor.BOLD+"Join> "+ChatColor.GOLD+"[Redstoner]"+ChatColor.YELLOW+player.getName());
			
		}
		else{
			player.setPlayerListName(ChatColor.WHITE+player.getName());
			event.setJoinMessage(ChatColor.AQUA+""+ChatColor.BOLD+"Join> "+ChatColor.YELLOW+player.getName());
		}
	
	}
	
	@EventHandler
	public void Quit(PlayerQuitEvent event){
		Player player = event.getPlayer();
		//event.setQuitMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Quit> "+ChatColor.YELLOW+player.getName());
		if(player.getName().equalsIgnoreCase("CatAngelz")){
			player.setPlayerListName(ChatColor.GREEN+""+ChatColor.BOLD+"D"+ChatColor.WHITE+player.getName());
			event.setQuitMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Quit> "+ChatColor.GREEN+"[Developer]"+ChatColor.YELLOW+player.getName());
		}else if(player.getName().equalsIgnoreCase("BossMadwolf")){
			player.setPlayerListName(ChatColor.DARK_GREEN+""+ChatColor.BOLD+"M"+ChatColor.WHITE+player.getName());
			event.setQuitMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Quit> "+ChatColor.DARK_GREEN+"[Mod]"+ChatColor.YELLOW+player.getName());
		}else if(player.getName().equalsIgnoreCase("_ZeroKarA")){
			player.setPlayerListName(ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"M"+ChatColor.WHITE+player.getName());
			event.setQuitMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Quit> "+ChatColor.DARK_PURPLE+"[Miner]"+ChatColor.YELLOW+player.getName());
		}
		else if(player.getName().equalsIgnoreCase("Protazua")){
			player.setPlayerListName(ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"LS"+ChatColor.WHITE+player.getName());
			event.setQuitMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Quit> "+ChatColor.LIGHT_PURPLE+"[Livestock]"+ChatColor.YELLOW+player.getName());
		}
		else if(player.getName().equalsIgnoreCase("N0cturnal_")){
			player.setPlayerListName(ChatColor.DARK_RED+""+ChatColor.BOLD+"FS"+ChatColor.WHITE+player.getName());
			event.setQuitMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Quit> "+ChatColor.DARK_RED+"[FiveStar]"+ChatColor.YELLOW+player.getName());
		}
		else if(player.getName().equalsIgnoreCase("Phantom_120")){
			player.setPlayerListName(ChatColor.DARK_RED+""+ChatColor.BOLD+"FS"+ChatColor.WHITE+player.getName());
			event.setQuitMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Quit> "+ChatColor.BLUE+"[Bot]"+ChatColor.YELLOW+player.getName());
		}
		else if(player.getName().equalsIgnoreCase("SharpKunG1412")){
			player.setPlayerListName(ChatColor.GOLD+""+ChatColor.BOLD+"R"+ChatColor.WHITE+player.getName());
			event.setQuitMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Quit> "+ChatColor.GOLD+"[Redstoner]"+ChatColor.YELLOW+player.getName());
			
		}
		else{
			player.setPlayerListName(ChatColor.WHITE+player.getName());
			event.setQuitMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Quit> "+ChatColor.YELLOW+player.getName());
		}
	}
	/*@EventHandler
	public void Badword(PlayerChatEvent event){
		Player player = event.getPlayer();
		String newMessage = event.getMessage().replace("ควยคิม", ChatColor.RED+""+ChatColor.UNDERLINE+"ควยคิม"+ChatColor.WHITE);
		event.setMessage(newMessage);
		
		if(event.getMessage().equalsIgnoreCase("gg") || event.getMessage().equalsIgnoreCase("ggwp")){

			player.sendMessage(ChatColor.LIGHT_PURPLE+"+5 Karma!");
		}
		if(event.getMessage().equalsIgnoreCase("-ping")){

			player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Your ping is "+ChatColor.YELLOW+TTA_Methods.getPing(player)+ChatColor.GRAY+".");
		}
	}*/
	@EventHandler
	public void Rank(PlayerChatEvent event){
		Player player = event.getPlayer();
		String pmessage = event.getMessage();
		 
		    //pmessage = pmessage.replace("budder", "gold");
		    event.setMessage(pmessage);
		pmessage = event.getMessage().replaceAll("&", "§");
		if(player.getName().equalsIgnoreCase("CatAngelz")){
		event.setFormat(ChatColor.GREEN+"[Developer] "+player.getName()+ChatColor.WHITE+": "+pmessage);
		for(Player player1 : Bukkit.getOnlinePlayers()){
			player1.playSound(player1.getLocation(), Sound.ENTITY_ITEMFRAME_REMOVE_ITEM, 10, 1);
			
		}
		}else if(player.getName().equalsIgnoreCase("BossMadwolf")){
		event.setFormat(ChatColor.DARK_GREEN+"[Mod] "+player.getName()+ChatColor.WHITE+": "+pmessage);
		for(Player player1 : Bukkit.getOnlinePlayers()){
			player1.playSound(player1.getLocation(), Sound.ENTITY_ITEMFRAME_REMOVE_ITEM, 10, 1);
			
		}
		}else if(player.getName().equalsIgnoreCase("_ZeroKarA")){
			event.setFormat(ChatColor.DARK_PURPLE+"[Miner] "+player.getName()+ChatColor.WHITE+": "+pmessage);
			for(Player player1 : Bukkit.getOnlinePlayers()){
				player1.playSound(player1.getLocation(), Sound.ENTITY_ITEMFRAME_REMOVE_ITEM, 10, 1);
				
			}
		}
		else if(player.getName().equalsIgnoreCase("Protazua")){
			event.setFormat(ChatColor.LIGHT_PURPLE+"[Livestock] "+player.getName()+ChatColor.WHITE+": "+pmessage);
			for(Player player1 : Bukkit.getOnlinePlayers()){
				player1.playSound(player1.getLocation(), Sound.ENTITY_ITEMFRAME_REMOVE_ITEM, 10, 1);
				
			}
		}
		else if(player.getName().equalsIgnoreCase("N0cturnal_")){
			event.setFormat(ChatColor.DARK_RED+"[FiveStar] "+player.getName()+ChatColor.WHITE+": "+pmessage);
			for(Player player1 : Bukkit.getOnlinePlayers()){
				player1.playSound(player1.getLocation(), Sound.ENTITY_ITEMFRAME_REMOVE_ITEM, 10, 1);
				
			}
		}
		else if(player.getName().equalsIgnoreCase("Phantom_120")){
			event.setFormat(ChatColor.BLUE+"[Bot] "+player.getName()+ChatColor.WHITE+": "+pmessage);
		}else if(player.getName().equalsIgnoreCase("SharpKunG1412")){
			event.setFormat(ChatColor.GOLD+"[Redstoner] "+player.getName()+ChatColor.WHITE+": "+pmessage);
			for(Player player1 : Bukkit.getOnlinePlayers()){
				player1.playSound(player1.getLocation(), Sound.ENTITY_ITEMFRAME_REMOVE_ITEM, 10, 1);
				
			}
		}
		else{
			event.setFormat(ChatColor.GRAY+player.getName()+": "+pmessage);
		}
	}
	@EventHandler
	public void Health(PlayerMoveEvent event){
		Player player = event.getPlayer();
		double health = player.getHealth();
	
		/*s = Bukkit.getScoreboardManager().getMainScoreboard();
		Objective o = s.registerNewObjective("health", "health");
		o.setDisplayName(ChatColor.RED+"❤");
		o.setDisplaySlot(DisplaySlot.BELOW_NAME);*/
		if(player.getName().equalsIgnoreCase("CatAngelz")){
			player.setPlayerListName(ChatColor.GREEN+""+ChatColor.BOLD+"D"+ChatColor.WHITE+player.getName()+ChatColor.WHITE+" ["+ChatColor.RED+"❤"+Math.round(health)+ChatColor.WHITE+"]");
			
			
		}else if(player.getName().equalsIgnoreCase("BossMadwolf")){
			player.setPlayerListName(ChatColor.DARK_GREEN+""+ChatColor.BOLD+"M"+ChatColor.WHITE+player.getName()+ChatColor.WHITE+" ["+ChatColor.RED+"❤"+Math.round(health)+ChatColor.WHITE+"]");
			
			
		}else if(player.getName().equalsIgnoreCase("_ZeroKarA")){
			player.setPlayerListName(ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"M"+ChatColor.WHITE+player.getName()+ChatColor.WHITE+" ["+ChatColor.RED+"❤"+Math.round(health)+ChatColor.WHITE+"]");
			
			
		}
		else if(player.getName().equalsIgnoreCase("Protazua")){
			player.setPlayerListName(ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"LS"+ChatColor.WHITE+player.getName()+ChatColor.WHITE+" ["+ChatColor.RED+"❤"+Math.round(health)+ChatColor.WHITE+"]");
			
			
		}
		else if(player.getName().equalsIgnoreCase("Phantom_120")){
			player.setPlayerListName(ChatColor.BLUE+"Phantom_Bot");
			
			
		}
		else if(player.getName().equalsIgnoreCase("N0cturnal_")){
			player.setPlayerListName(ChatColor.DARK_RED+""+ChatColor.BOLD+"FS"+ChatColor.WHITE+player.getName()+ChatColor.WHITE+" ["+ChatColor.RED+"❤"+Math.round(health)+ChatColor.WHITE+"]");
			
			
		}
		else if(player.getName().equalsIgnoreCase("SharpKunG1412")){
			player.setPlayerListName(ChatColor.GOLD+""+ChatColor.BOLD+"R"+ChatColor.WHITE+player.getName()+ChatColor.WHITE+" ["+ChatColor.RED+"❤"+Math.round(health)+ChatColor.WHITE+"]");
			
			
		}
		else{
			player.setPlayerListName(ChatColor.WHITE+player.getName()+ChatColor.WHITE+" ["+ChatColor.RED+"❤"+Math.round(health)+ChatColor.WHITE+"]");
			
		}
		//server time
		/*Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
		for(Player player1 : Bukkit.getOnlinePlayers()){
			TTA_Methods.sendTablist(player1, ChatColor.YELLOW+"Player Online", ChatColor.AQUA+"Server Time "+time.format(now));
		}*/
		//Jump to teleport
		/*if((int) event.getPlayer().getLocation().getX() == -179){
	    	if((int) event.getPlayer().getLocation().getY() == 84){
	    		if((int) event.getPlayer().getLocation().getZ() == -23){
	    			if(event.getPlayer().getWorld().getName().equals("world")){
	    			Location loca = player.getLocation();
					loca.setX(-179);
					loca.setY(256);
					loca.setZ(-22);
					World wname = loca.getWorld();
					
					player.teleport(loca);
					player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.AQUA+"Whoo! "+ChatColor.GRAY+"Teleport complete.");
					player.playEffect(player.getLocation(), Effect.EXPLOSION_HUGE, 10);
					player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 10, 1);
	    			}
	    		}
	    	}
	    }*/
		
	}
	/*@EventHandler
	public void joox(PlayerInteractEvent event){
		Player player = event.getPlayer();
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK && player.getItemInHand().getType().equals(Material.RECORD_11)){
			if(event.getClickedBlock().getType() == Material.JUKEBOX) {
				event.setCancelled(true);
				player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"This record was blocked.");
				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BASS, 10, 1);
			}
		}
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK && player.getItemInHand().getType().equals(Material.GOLD_RECORD)){
			if(event.getClickedBlock().getType() == Material.JUKEBOX) {
				event.setCancelled(true);
				player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"This record was blocked.");
				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BASS, 10, 1);
			}
		}
		
	}*/
	//Check Befor teleport [Gold Plate]
	@EventHandler
	public void Checkbeforewarp(PlayerMoveEvent event){
		Player player = event.getPlayer();
    	Location loc = player.getLocation();
		loc.setY(loc.getY());
		if((int)event.getPlayer().getLocation().getX() == -179){
			if((int)event.getPlayer().getLocation().getZ() == -23){
				if(loc.getBlock().getType() == Material.GOLD_PLATE){
			TTA_Methods.sendActionBar(player, ChatColor.YELLOW+""+ChatColor.BOLD+"Hold"+ChatColor.GREEN+""+ChatColor.BOLD+" >>Shift<< "+ChatColor.AQUA+"To Teleport");
			player.addPotionEffect(new org.bukkit.potion.PotionEffect(PotionEffectType.CONFUSION, 100 ,10));
				}
			}
		}
	}
	//teleport [Gold plate]
	@EventHandler
	public void Teleport(PlayerToggleSneakEvent event){
		Player player = event.getPlayer();
	    if(event.getPlayer().getWorld().getName().equals("world")){
	    	Location loc = player.getLocation();
	    	loc.setY(loc.getY());
	    	   if(loc.getBlock().getType() == Material.GOLD_PLATE){
	    	    			if((int)event.getPlayer().getLocation().getX() == -179){
	    	    				if((int)event.getPlayer().getLocation().getZ() == -23){
	    	    					if(event.getPlayer().getWorld().getName().equals("world")){
	    	    		    			Location loca = player.getLocation();
	    	    						loca.setX(-179);
	    	    						loca.setY(256);
	    	    						loca.setZ(-22);
	    	    						World wname = loca.getWorld();
	    	    						
	    	    						player.teleport(loca);
	    	    						player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.AQUA+"Whoo! "+ChatColor.GRAY+"Teleport complete.");
	    	    						//player.playEffect(player.getLocation(), Effect.EXPLOSION_HUGE, 10);
	    	    						player.playSound(player.getLocation(), Sound.BLOCK_PORTAL_TRAVEL, 10, 1);
	    	    						player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);
	    	    						player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);
	    	    						player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);
	    	    						player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);
	    	    						player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);
	    	    						player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);
	    	    						player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);
	    	    						player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);
	    	    						player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);
	    	    						player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);
	    	    						player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);
	    	    						player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);
	    	    						player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);
	    	    						player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);
	    	    						player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);
	    	    						player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);
	    	    						player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);
	    	    						player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);
	    	    						player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);
	    	    						player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);player.playEffect(player.getLocation(), Effect.PORTAL, 10);
	    	    					}
	    	    				}
	    	    			}
	    	    }
	    }
	}
	@EventHandler
	public void Sign(BlockDamageEvent event){
		Block block = event.getBlock();
		Player player = event.getPlayer();
		if (block.getTypeId() == 63 || block.getTypeId() == 68) {
			Sign sign = (Sign) block.getState();
			String nsign0 = sign.getLine(0).replaceAll("&", "§");
			String nsign1 = sign.getLine(1).replaceAll("&", "§");
			String nsign2 = sign.getLine(2).replaceAll("&", "§");
			String nsign3 = sign.getLine(3).replaceAll("&", "§");
			sign.setLine(0, nsign0);
			sign.setLine(1, nsign1);
			sign.setLine(2, nsign2);
			sign.setLine(3, nsign3);
			sign.update(true);
	}
}
}

