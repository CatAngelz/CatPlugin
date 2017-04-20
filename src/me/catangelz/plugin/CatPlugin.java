package me.catangelz.plugin;



import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.FireworkEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.xxmicloxx.NoteBlockAPI.NBSDecoder;
import com.xxmicloxx.NoteBlockAPI.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.Song;
import com.xxmicloxx.NoteBlockAPI.SongPlayer;

import DelayLoadConfig.DelayLoadConfig;
import de.Herbystar.TTA.TTA_Methods;





public class CatPlugin extends JavaPlugin{
	public final Logger logger = Logger.getLogger("Minecraft");
	public static CatPlugin plugin;
	
	public static HashMap<String, ArrayList<SongPlayer>> playingSongs = new HashMap<String, ArrayList<SongPlayer>>();
	public int number = 10;
	public static DelayLoadConfig delayLoadConfig = null;
	public static Thread delayLoadConnfig_Thread = null;

	@Override
	public void onEnable(){

		getLogger().info("CatPlugin has been enable!");
		Bukkit.broadcastMessage(ChatColor.BLUE+"Server> "+ChatColor.DARK_AQUA+"System has been "+ChatColor.GREEN+"Updated.");
		Bukkit.getServer().getPluginManager().registerEvents(new CatListener(), this);
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		for(Player player1 : Bukkit.getOnlinePlayers()){
			player1.playSound(player1.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
	
		}
		delayLoadConfig = new DelayLoadConfig();
		delayLoadConnfig_Thread = new Thread(delayLoadConfig);
		delayLoadConnfig_Thread.start();
		
	}
	@Override	
	public void onDisable(){
		saveConfig();
		Bukkit.broadcastMessage(ChatColor.BLUE+"Server> "+ChatColor.DARK_AQUA+"System is now "+ChatColor.YELLOW+"Updating.");
		delayLoadConfig.setRunning(false);
	}
	
	



	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args){
		Player player = (Player) sender;
		
			if(CommandLabel.equalsIgnoreCase("test")){
				player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Plugin run!");
				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 10, 0);
				/*for(Player player1 : Bukkit.getOnlinePlayers()){
					player1.playSound(player1.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
					TTA_Methods.sendTablist(player1, ChatColor.YELLOW+"Player Online", null);	
				}*/
				//Server Time
				/*Date now = new Date();
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
				player.sendMessage(time.format(now));*/
				//Chat all arg
				/*String message = "";
				message = "";
				for (int i = 1; i != args.length; i++)
				message += args[i] + " ";
				message = message.replaceAll("&", "§");
				player.chat(message);*/
				
				
			}
			
			if(CommandLabel.equalsIgnoreCase("gamemode") || CommandLabel.equalsIgnoreCase("gm")){
				if(player.isOp()){
					if(args.length == 1){
						if(args[0].equalsIgnoreCase("0")){
							player.setGameMode(GameMode.SURVIVAL);
							player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Set game mode "+ChatColor.YELLOW+"survival"+ChatColor.GRAY+" for "+ChatColor.YELLOW+player.getName());
						}
						if(args[0].equalsIgnoreCase("1")){
							player.setGameMode(GameMode.CREATIVE);
							player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Set game mode "+ChatColor.YELLOW+"creative"+ChatColor.GRAY+" for "+ChatColor.YELLOW+player.getName());
						}
						if(args[0].equalsIgnoreCase("2")){
							player.setGameMode(GameMode.ADVENTURE);
							player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Set game mode "+ChatColor.YELLOW+"adventure"+ChatColor.GRAY+" for "+ChatColor.YELLOW+player.getName());
						}
						if(args[0].equalsIgnoreCase("3")){
							player.setGameMode(GameMode.SPECTATOR);
							player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Set game mode "+ChatColor.YELLOW+"spectator"+ChatColor.GRAY+" for "+ChatColor.YELLOW+player.getName());
						}
					}
					else if(args.length == 2){
						if(args[0].equalsIgnoreCase("0")){
							if(player.getServer().getPlayer(args[1]) != null){
								Player targetplayer = player.getServer().getPlayer(args[1]);
								targetplayer.setGameMode(GameMode.SURVIVAL);
								player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Set game mode "+ChatColor.YELLOW+"survival"+ChatColor.GRAY+" for "+ChatColor.YELLOW+targetplayer.getName());

							}else{
								player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Player is not online.");
							}
													
						}
						if(args[0].equalsIgnoreCase("1")){
							if(player.getServer().getPlayer(args[1]) != null){
								Player targetplayer = player.getServer().getPlayer(args[1]);
								targetplayer.setGameMode(GameMode.CREATIVE);
								player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Set game mode "+ChatColor.YELLOW+"creative"+ChatColor.GRAY+" for "+ChatColor.YELLOW+targetplayer.getName());

							}else{
								player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Player is not online.");
							}
													
						}
						if(args[0].equalsIgnoreCase("2")){
							if(player.getServer().getPlayer(args[1]) != null){
								Player targetplayer = player.getServer().getPlayer(args[1]);
								targetplayer.setGameMode(GameMode.ADVENTURE);
								player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Set game mode "+ChatColor.YELLOW+"adventure"+ChatColor.GRAY+" for "+ChatColor.YELLOW+targetplayer.getName());

							}else{
								player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Player is not online.");
							}
													
						}
						if(args[0].equalsIgnoreCase("3")){
							if(player.getServer().getPlayer(args[1]) != null){
								Player targetplayer = player.getServer().getPlayer(args[1]);
								targetplayer.setGameMode(GameMode.SPECTATOR);
								player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Set game mode "+ChatColor.YELLOW+"spectator"+ChatColor.GRAY+" for "+ChatColor.YELLOW+targetplayer.getName());

							}else{
								player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Player is not online.");
							}
													
						}
					}else{
						player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Please use /gamemode [1/2/3] or /gm [1/2/3]");
					}
				}else{
				player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Sorry you don't have permission.");
			}
			}
			if(CommandLabel.equalsIgnoreCase("ctp")){
				if(player.isOp()){
				if(args.length == 1){
					if(player.getServer().getPlayer(args[0]) != null){
					Player targetplayer = player.getServer().getPlayer(args[0]);
					Location locp2 = targetplayer.getLocation();
					player.playEffect(player.getLocation(),Effect.ENDEREYE_LAUNCH, 0);
					player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_AMBIENT, 10, 0);
					player.teleport(locp2);
					player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Teleport to "+ChatColor.YELLOW+targetplayer.getName()+".");
				}else{
					player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Player is not online.");
				}
				}else{
					player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Please use /ctp [player name]");
				}
				}else{
					player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Sorry you don't have permission.");
				}
			}
			if(CommandLabel.equalsIgnoreCase("csay")){
				if(sender instanceof Player){
					if(player.getName().equalsIgnoreCase("CatAngelz")){
						if(args.length == 0){
							
							player.sendMessage(ChatColor.BLUE+"Server>"+ChatColor.GRAY+"Please use command /csay <Text>");
						}
						if(args.length != 0){
							//Bukkit.broadcastMessage(ChatColor.YELLOW+""+ChatColor.BOLD+"ChuWapp>"+ChatColor.WHITE+" "+args[0]);
							String message = "";
							message = "";
							for (int i = 0; i != args.length; i++)
							message += args[i] + " ";
							message = message.replaceAll("&", "§");
							Bukkit.broadcastMessage(ChatColor.YELLOW+""+ChatColor.BOLD+"ChuWapp>"+ChatColor.WHITE+" "+message);
						}
					
					}else 
						if(args.length == 0){
							player.sendMessage(ChatColor.BLUE+"Server>"+ChatColor.GRAY+"Please use command /csay <Text>");
						}
						else if(args.length == 1){
							Bukkit.broadcastMessage(ChatColor.YELLOW+""+ChatColor.BOLD+player.getName()+">"+ChatColor.WHITE+" "+args[0]);
							String message = "";
							message = "";
							for (int i = 0; i != args.length; i++)
							message += args[i] + " ";
							message = message.replaceAll("&", "§");
							Bukkit.broadcastMessage(ChatColor.YELLOW+""+ChatColor.BOLD+player.getName()+">"+ChatColor.WHITE+" "+message);
						}
												
					}
				}
			if(CommandLabel.equalsIgnoreCase("wdr") || CommandLabel.equalsIgnoreCase("watchdogreport")){
				player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"พ่อมึงอ่ะที่นี้ Smilekrub ไม่ใช่ Hypixel จาก Tacklezaza");
			}
			if(CommandLabel.equalsIgnoreCase("heal")){
				if(player.isOp()){
					if(args.length == 0){
						player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"You has been heal!");
						player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
						player.setHealth(20);
						player.setFoodLevel(20);
					}else if(args.length == 1){
						if(player.getServer().getPlayer(args[0]) != null){
							Player targetplayer = player.getServer().getPlayer(args[0]);
							targetplayer.setHealth(20);
							targetplayer.setFoodLevel(20);
							targetplayer.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"You has been heal!");
							targetplayer.playSound(targetplayer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
							player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.YELLOW+args[0]+ChatColor.GRAY+" has been heal!");
						}else{
							player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Player is not online.");
						}
					}else{
						player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"You has been heal!");
						player.setHealth(20);
						player.setFoodLevel(20);
						player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
					}
				}else
				{
					player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Sorry you don't have permission.");
				}
			}
			if(CommandLabel.equalsIgnoreCase("cstop")){
				if(player.isOp()){
					this.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
						public void run(){
								if(number !=-1){
									if(number !=0){
										if(number == 10){
											Bukkit.broadcastMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Will stop in "+ChatColor.YELLOW+number+ChatColor.GRAY+" second.");
											for(Player player1 : Bukkit.getOnlinePlayers()){
												player1.playSound(player1.getLocation(), Sound.ENTITY_CAT_AMBIENT, 10, 1);
												
											}
											
										}else if(number == 5){
											Bukkit.broadcastMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Will stop in "+ChatColor.YELLOW+number+ChatColor.GRAY+" second.");
											for(Player player1 : Bukkit.getOnlinePlayers()){
												player1.playSound(player1.getLocation(), Sound.ENTITY_CAT_AMBIENT, 10, 1);
												
											}
											
										}else if(number == 4){
											Bukkit.broadcastMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Will stop in "+ChatColor.YELLOW+number+ChatColor.GRAY+" second.");
											for(Player player1 : Bukkit.getOnlinePlayers()){
												player1.playSound(player1.getLocation(), Sound.ENTITY_CAT_AMBIENT, 10, 1);
												
											}
											
										}
										else if(number == 3){
											Bukkit.broadcastMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Will stop in "+ChatColor.YELLOW+number+ChatColor.GRAY+" second.");
											for(Player player1 : Bukkit.getOnlinePlayers()){
												player1.playSound(player1.getLocation(), Sound.ENTITY_CAT_AMBIENT, 10, 1);
												
											}
											
										}
										else if(number == 2){
											Bukkit.broadcastMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Will stop in "+ChatColor.YELLOW+number+ChatColor.GRAY+" second.");
											for(Player player1 : Bukkit.getOnlinePlayers()){
												player1.playSound(player1.getLocation(), Sound.ENTITY_CAT_AMBIENT, 10, 1);
												
											}
										
										}
										else if(number == 1){
											Bukkit.broadcastMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Will stop in "+ChatColor.YELLOW+number+ChatColor.GRAY+" second.");
											for(Player player1 : Bukkit.getOnlinePlayers()){
												player1.playSound(player1.getLocation(), Sound.ENTITY_CAT_AMBIENT, 10, 1);
												
											}
											
										}
										number--;
									}else{
										Bukkit.getServer().shutdown();
										Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "save-all");
										Bukkit.broadcastMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"SaveWorld Complete");
										for(Player player1 : Bukkit.getOnlinePlayers()){
											player1.playSound(player1.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 10, 1);
											
										}
										number--;
									}
								}
							}
					
					}, 0L, 20L);
				}else{
					player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Sorry you don't have permission.");
				}
			}
			if(CommandLabel.equalsIgnoreCase("plugin") || CommandLabel.equalsIgnoreCase("pl")){
				player.sendMessage(ChatColor.WHITE+"Plugins (2):"+ChatColor.GREEN+" ไม่เสือก"+ChatColor.WHITE+", "+ChatColor.GREEN+"เรื่องของกุ");
			}
			if(CommandLabel.equalsIgnoreCase("ping")){
				if(args.length == 0){
					player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Your ping is "+ChatColor.YELLOW+TTA_Methods.getPing(player)+ChatColor.GRAY+".");
				}
				else if(args.length == 1){
				if(player.isOp()){
				if(player.getServer().getPlayer(args[0]) != null){
					Player targetplayer = player.getServer().getPlayer(args[0]);
					player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.YELLOW+targetplayer.getName()+ChatColor.GRAY+" ping is "+ChatColor.YELLOW+TTA_Methods.getPing(targetplayer)+ChatColor.GRAY+".");	
					
				}else{
					player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Player is not online.");
				
				}
				}else{
					player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Sorry you don't have permission.");
				}
					
			}else{
				player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Please type /ping or /ping [player name]");
				}
			
			}
			if(CommandLabel.equalsIgnoreCase("ctitle")){
				if(player.isOp()){
					if(args.length == 0 || args.length == 1){
						player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Please type /ctitle <number> <subtitle>");
					}else if(args.length == 2){
						for(Player player1 : Bukkit.getOnlinePlayers()){
							TTA_Methods.sendTitle(player1, ChatColor.DARK_RED+"Count down "+ChatColor.YELLOW+args[0]+ChatColor.DARK_RED+" second.", 10, 100, 10, ChatColor.LIGHT_PURPLE+args[1], 10, 100, 10);
							
							player1.playSound(player1.getLocation(), Sound.ENTITY_WITHER_DEATH, 10, 1);
							
						}
					}else{
						player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Please type /ctitle <number> <subtitle>");
					}
				}else{
					player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Sorry you don't have permission.");
				}
			}
			/*if(CommandLabel.equalsIgnoreCase("music")){
				
					if(args.length == 0){
						player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Please type /music help");
					}else if(args.length == 1){
						if(args[0].equalsIgnoreCase("list")){
							player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Song List (2) :"+ChatColor.YELLOW+" dead"+ChatColor.GRAY+", "+ChatColor.YELLOW+"dead_remix");
						}else if(args[0].equalsIgnoreCase("stop")){
							player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Not available.[ยังหาวิธีไม่ได้]");	
							
						}else if(args[0].equalsIgnoreCase("help")){
							player.sendMessage(ChatColor.GOLD+""+ChatColor.STRIKETHROUGH+"============"+ChatColor.GREEN+""+ChatColor.BOLD+"Music Help"+ChatColor.GOLD+""+ChatColor.STRIKETHROUGH+"============");
							player.sendMessage(ChatColor.BLUE+"/music play <song name>"+ChatColor.GRAY+" : "+ChatColor.YELLOW+"To play music");
							player.sendMessage(ChatColor.BLUE+"/music list"+ChatColor.GRAY+" : "+ChatColor.YELLOW+"List of song");
							player.sendMessage(ChatColor.BLUE+"/music stop"+ChatColor.GRAY+" : "+ChatColor.YELLOW+"Stop song");
							player.sendMessage(ChatColor.GOLD+""+ChatColor.STRIKETHROUGH+"=================================");
							player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
						}else{
							player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Please type /music help");
						}
					}else if(args.length == 2){
						if(args[1].equalsIgnoreCase("dead")){
							for(Player player1 : Bukkit.getOnlinePlayers()){
								Song dead = NBSDecoder.parse(new File("plugins/CatPlugin/dead.nbs"));
								SongPlayer deadp = new RadioSongPlayer(dead);
								deadp.setAutoDestroy(true);
								deadp.addPlayer(player1);
								deadp.setPlaying(true);							
							}
							player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Complete.");
						}else if(args[1].equalsIgnoreCase("dead_remix")){
							for(Player player1 : Bukkit.getOnlinePlayers()){
								Song dead_remix = NBSDecoder.parse(new File("plugins/CatPlugin/dead.nbs"));
								SongPlayer dead_remixp = new RadioSongPlayer(dead_remix);
								dead_remixp.setAutoDestroy(true);
								dead_remixp.addPlayer(player1);
								dead_remixp.setPlaying(true);							
							}
						}else{
							player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Song name not found in system");
						}
			
						}else{
						player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Please type /music help");
					}
			}*/
			if(CommandLabel.equalsIgnoreCase("music")){
				if(args.length == 0){
					player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Please type /music help");
				}else if(args.length == 1){
					if(args[0].equalsIgnoreCase("help")){
						player.sendMessage(ChatColor.GOLD+""+ChatColor.STRIKETHROUGH+"============"+ChatColor.GREEN+""+ChatColor.BOLD+"Music Help"+ChatColor.GOLD+""+ChatColor.STRIKETHROUGH+"============");
						player.sendMessage(ChatColor.BLUE+"/music play <song name>"+ChatColor.GRAY+" : "+ChatColor.YELLOW+"To play music");
						player.sendMessage(ChatColor.BLUE+"/music list"+ChatColor.GRAY+" : "+ChatColor.YELLOW+"List of song");
						player.sendMessage(ChatColor.BLUE+"/music mute"+ChatColor.GRAY+" : "+ChatColor.YELLOW+"Mute/Un mute song");
						player.sendMessage(ChatColor.BLUE+"/music alert"+ChatColor.GRAY+" : "+ChatColor.YELLOW+"Alert Message");
						player.sendMessage(ChatColor.GOLD+""+ChatColor.STRIKETHROUGH+"=================================");
						player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
					}else if(args[0].equalsIgnoreCase("mute")){
						//player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Not available.[ยังหาวิธีไม่ได้]");	
						String mute = getConfig().getString(player.getName()+".music");
						if(mute == "unmute"){
							getConfig().set(player.getName()+".music","mute");
							saveConfig();
							Song dead = NBSDecoder.parse(new File("plugins/CatPlugin/dead.nbs"));
							SongPlayer deadp = new RadioSongPlayer(dead);
							deadp.removePlayer(player);
							player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Now you "+ChatColor.RED+""+ChatColor.BOLD+"MUTE"+ChatColor.GRAY+" music.");
						}else if(mute == "mute"){
							getConfig().set(player.getName()+".music","unmute");
							saveConfig();
							player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Now you "+ChatColor.GREEN+""+ChatColor.BOLD+"Un MUTE"+ChatColor.GRAY+" music.");
						
						}else{
							getConfig().set(player.getName()+".music","mute");
							saveConfig();
							player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Now you "+ChatColor.RED+""+ChatColor.BOLD+"MUTE"+ChatColor.GRAY+" music.");
						}
					}else if(args[0].equalsIgnoreCase("list")){
						player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Song List (8) :"+ChatColor.YELLOW+" dead"+ChatColor.GRAY+", "+ChatColor.YELLOW+"dead_remix"+ChatColor.GRAY+","
								+ChatColor.YELLOW+"bike_for_mom"+ChatColor.GRAY+","+ChatColor.YELLOW+"firework"+ChatColor.GRAY+","+ChatColor.YELLOW+"lalala"+ChatColor.GRAY+","+ChatColor.YELLOW+"still_alive"
								+ChatColor.GRAY+","+ChatColor.YELLOW+"t26"+ChatColor.GRAY+","+ChatColor.YELLOW+"let_it_go");
						
						player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.DARK_RED+"คำเตือน: "+ChatColor.YELLOW+"ห้ามเปิดเพลงในขณะที่เพลงอื่นเล่นอยู่!");
						player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.AQUA+"คำแนะนำ: "+ChatColor.GREEN+"ก่อนเปิดเพลงแนะนำให้พิมพ์ /music alert ก่อนที่จะเปิดเพลง");
					}else if(args[0].equalsIgnoreCase("play")){
						player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Please type /music play <song name>");
					}else if(args[0].equalsIgnoreCase("alert")){
						for(Player player1 : Bukkit.getOnlinePlayers()){
							player1.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"ในอีกสักครู่ระบบกำลังจะเปิดเพลง หากท่านไม่ต้องการฟังกรุณาพิมพ์ /music mute หาก /music mute อีกรอบจะเป็นการ un mute ระบบเพลง");
							player1.playSound(player1.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 10, 1);
						}
						
					}else{
						player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Please type /music help");
					}
				}else if(args.length == 2){
					//String mute = getConfig().getString(player.getName()+".music","mute");
					if(args[1].equalsIgnoreCase("dead")){
						
						for(Player player1 : Bukkit.getOnlinePlayers()){
							String mute = getConfig().getString(player1.getName()+".music");
							String checkm = getConfig().getString(player1.getName()+".music","unmute");
							if(mute == "mute"){
							Song dead = NBSDecoder.parse(new File("plugins/CatPlugin/dead.nbs"));
							SongPlayer deadp = new RadioSongPlayer(dead);
							deadp.setAutoDestroy(true);
							deadp.addPlayer(player1);
							deadp.setPlaying(false);
							TTA_Methods.sendActionBar(player1, ChatColor.AQUA+"Now playing : "+ChatColor.YELLOW+"Dead"+ChatColor.BLUE+" Made by :"+ChatColor.WHITE+" SharpKunG1412", 100);
							}else{
								Song dead = NBSDecoder.parse(new File("plugins/CatPlugin/dead.nbs"));
								SongPlayer deadp = new RadioSongPlayer(dead);
								deadp.setAutoDestroy(true);
								deadp.addPlayer(player1);
								deadp.setPlaying(true);
								TTA_Methods.sendActionBar(player1, ChatColor.AQUA+"Now playing : "+ChatColor.YELLOW+"Dead"+ChatColor.BLUE+" Made by :"+ChatColor.WHITE+" SharpKunG1412", 100);
							}
						}	
					}
					else if(args[1].equalsIgnoreCase("dead_remix")){
						for(Player player1 : Bukkit.getOnlinePlayers()){
							String mute = getConfig().getString(player1.getName()+".music");
							
							if(mute == "mute"){
							Song dead_remix = NBSDecoder.parse(new File("plugins/CatPlugin/dead_remix.nbs"));
							SongPlayer dead_remixp = new RadioSongPlayer(dead_remix);
							dead_remixp.setAutoDestroy(true);
							dead_remixp.addPlayer(player1);
							dead_remixp.setPlaying(false);	
							TTA_Methods.sendActionBar(player1, ChatColor.AQUA+"Now playing : "+ChatColor.YELLOW+"Dead Remix"+ChatColor.BLUE+" Made by :"+ChatColor.WHITE+" SharpKunG1412", 100);
							}else{
								Song dead = NBSDecoder.parse(new File("plugins/CatPlugin/dead_remix.nbs"));
								SongPlayer deadp = new RadioSongPlayer(dead);
								deadp.setAutoDestroy(true);
								deadp.addPlayer(player1);
								deadp.setPlaying(true);
								TTA_Methods.sendActionBar(player1, ChatColor.AQUA+"Now playing : "+ChatColor.YELLOW+"Dead Remix"+ChatColor.BLUE+" Made by :"+ChatColor.WHITE+" SharpKunG1412", 100);
							}
						}	
						}
					else if(args[1].equalsIgnoreCase("t26")){
						for(Player player1 : Bukkit.getOnlinePlayers()){
							String mute = getConfig().getString(player1.getName()+".music");
							
							if(mute == "mute"){
							Song t26 = NBSDecoder.parse(new File("plugins/CatPlugin/t26.nbs"));
							SongPlayer t26p = new RadioSongPlayer(t26);
							t26p.setAutoDestroy(true);
							t26p.addPlayer(player1);
							t26p.setPlaying(false);	
							TTA_Methods.sendActionBar(player1, ChatColor.AQUA+"Now playing : "+ChatColor.YELLOW+"T26"+ChatColor.BLUE+" Made by :"+ChatColor.WHITE+" SharpKunG1412", 100);
							}else{
								Song t26 = NBSDecoder.parse(new File("plugins/CatPlugin/t26.nbs"));
								SongPlayer t26p = new RadioSongPlayer(t26);
								t26p.setAutoDestroy(true);
								t26p.addPlayer(player1);
								t26p.setPlaying(true);
								TTA_Methods.sendActionBar(player1, ChatColor.AQUA+"Now playing : "+ChatColor.YELLOW+"T26"+ChatColor.BLUE+" Made by :"+ChatColor.WHITE+" SharpKunG1412", 100);
							}
						}	
						}
					else if(args[1].equalsIgnoreCase("lalala")){
						for(Player player1 : Bukkit.getOnlinePlayers()){
							String mute = getConfig().getString(player1.getName()+".music");
							
							if(mute == "mute"){
							Song lalala = NBSDecoder.parse(new File("plugins/CatPlugin/lalala.nbs"));
							SongPlayer lalalap = new RadioSongPlayer(lalala);
							lalalap.setAutoDestroy(true);
							lalalap.addPlayer(player1);
							lalalap.setPlaying(false);	
							TTA_Methods.sendActionBar(player1, ChatColor.AQUA+"Now playing : "+ChatColor.YELLOW+"La La La"+ChatColor.BLUE+" Made by :"+ChatColor.WHITE+" SharpKunG1412", 100);
							}else{
								Song lalala = NBSDecoder.parse(new File("plugins/CatPlugin/lalala.nbs"));
								SongPlayer lalalap = new RadioSongPlayer(lalala);
								lalalap.setAutoDestroy(true);
								lalalap.addPlayer(player1);
								lalalap.setPlaying(true);
								TTA_Methods.sendActionBar(player1, ChatColor.AQUA+"Now playing : "+ChatColor.YELLOW+"La La La"+ChatColor.BLUE+" Made by :"+ChatColor.WHITE+" SharpKunG1412", 100);
							}
						}	
						}
					else if(args[1].equalsIgnoreCase("still_alive")){
						for(Player player1 : Bukkit.getOnlinePlayers()){
							String mute = getConfig().getString(player1.getName()+".music");
							
							if(mute == "mute"){
							Song still_alive = NBSDecoder.parse(new File("plugins/CatPlugin/still_alive.nbs"));
							SongPlayer still_alivep = new RadioSongPlayer(still_alive);
							still_alivep.setAutoDestroy(true);
							still_alivep.addPlayer(player1);
							still_alivep.setPlaying(false);	
							TTA_Methods.sendActionBar(player1, ChatColor.AQUA+"Now playing : "+ChatColor.YELLOW+"Still Alive"+ChatColor.BLUE+" Made by :"+ChatColor.WHITE+" SharpKunG1412", 100);
							}else{
								Song still_alive = NBSDecoder.parse(new File("plugins/CatPlugin/still_alive.nbs"));
								SongPlayer still_alivep = new RadioSongPlayer(still_alive);
								still_alivep.setAutoDestroy(true);
								still_alivep.addPlayer(player1);
								still_alivep.setPlaying(true);	
								TTA_Methods.sendActionBar(player1, ChatColor.AQUA+"Now playing : "+ChatColor.YELLOW+"Still Alive"+ChatColor.BLUE+" Made by :"+ChatColor.WHITE+" SharpKunG1412", 100);
							}
						}	
						}
					else if(args[1].equalsIgnoreCase("firework")){
						for(Player player1 : Bukkit.getOnlinePlayers()){
							String mute = getConfig().getString(player1.getName()+".music");
							
							if(mute == "mute"){
							Song firework = NBSDecoder.parse(new File("plugins/CatPlugin/firework.nbs"));
							SongPlayer fireworkp = new RadioSongPlayer(firework);
							fireworkp.setAutoDestroy(true);
							fireworkp.addPlayer(player1);
							fireworkp.setPlaying(false);	
							TTA_Methods.sendActionBar(player1, ChatColor.AQUA+"Now playing : "+ChatColor.YELLOW+"Firework"+ChatColor.BLUE+" Made by :"+ChatColor.WHITE+" SharpKunG1412", 100);
							}else{
								Song firework = NBSDecoder.parse(new File("plugins/CatPlugin/firework.nbs"));
								SongPlayer fireworkp = new RadioSongPlayer(firework);
								fireworkp.setAutoDestroy(true);
								fireworkp.addPlayer(player1);
								fireworkp.setPlaying(true);
								TTA_Methods.sendActionBar(player1, ChatColor.AQUA+"Now playing : "+ChatColor.YELLOW+"Firework"+ChatColor.BLUE+" Made by :"+ChatColor.WHITE+" SharpKunG1412", 100);
							}
						}	
						}
					else if(args[1].equalsIgnoreCase("bike_for_mom")){
						for(Player player1 : Bukkit.getOnlinePlayers()){
							String mute = getConfig().getString(player1.getName()+".music");
							
							if(mute == "mute"){
							Song bike_for_mom = NBSDecoder.parse(new File("plugins/CatPlugin/bike_for_mom.nbs"));
							SongPlayer bike_for_momp = new RadioSongPlayer(bike_for_mom);
							bike_for_momp.setAutoDestroy(true);
							bike_for_momp.addPlayer(player1);
							bike_for_momp.setPlaying(false);	
							TTA_Methods.sendActionBar(player1, ChatColor.AQUA+"Now playing : "+ChatColor.YELLOW+"Bike For Mom"+ChatColor.BLUE+" Made by :"+ChatColor.WHITE+" SharpKunG1412", 100);
							}else{
								Song bike_for_mom = NBSDecoder.parse(new File("plugins/CatPlugin/bike_for_mom.nbs"));
								SongPlayer bike_for_momp = new RadioSongPlayer(bike_for_mom);
								bike_for_momp.setAutoDestroy(true);
								bike_for_momp.addPlayer(player1);
								bike_for_momp.setPlaying(true);
								TTA_Methods.sendActionBar(player1, ChatColor.AQUA+"Now playing : "+ChatColor.YELLOW+"Bike For Mom"+ChatColor.BLUE+" Made by :"+ChatColor.WHITE+" SharpKunG1412", 100);
							}
						}	
						}
					else if(args[1].equalsIgnoreCase("let_it_go")){
						for(Player player1 : Bukkit.getOnlinePlayers()){
							String mute = getConfig().getString(player1.getName()+".music");
							
							if(mute == "mute"){
							Song let_it_go = NBSDecoder.parse(new File("plugins/CatPlugin/let_it_go.nbs"));
							SongPlayer let_it_gop = new RadioSongPlayer(let_it_go);
							let_it_gop.setAutoDestroy(true);
							let_it_gop.addPlayer(player1);
							let_it_gop.setPlaying(false);	
							TTA_Methods.sendActionBar(player1, ChatColor.AQUA+"Now playing : "+ChatColor.YELLOW+"Let It Go"+ChatColor.BLUE+" Made by :"+ChatColor.WHITE+" BossMadWolf", 100);
							}else{
								Song let_it_go = NBSDecoder.parse(new File("plugins/CatPlugin/let_it_go.nbs"));
								SongPlayer let_it_gop = new RadioSongPlayer(let_it_go);
								let_it_gop.setAutoDestroy(true);
								let_it_gop.addPlayer(player1);
								let_it_gop.setPlaying(true);	
								TTA_Methods.sendActionBar(player1, ChatColor.AQUA+"Now playing : "+ChatColor.YELLOW+"Let It Go"+ChatColor.BLUE+" Made by :"+ChatColor.WHITE+" BossMadWolf", 100);
							}
						}	
						}else{
							player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Unknown music.");
						}
				}else{
					player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Please type /music help");
				}
			
			}
			if(CommandLabel.equalsIgnoreCase("pos") || CommandLabel.equalsIgnoreCase("position")){
				if(args.length == 0){
					Location pl = player.getLocation();
					double x = pl.getX();
					double y = pl.getY();
					double z = pl.getZ();
					
					player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Your Location is"+ChatColor.YELLOW+" X: "+Math.round(x)+" Y: "+Math.round(y)+" Z: "+Math.round(z)+" World: "+player.getWorld().getName());
					player.playSound(pl, Sound.ENTITY_SNOWBALL_THROW, 10, 0);
				}else if(args.length == 1){
					if(player.getServer().getPlayer(args[0]) != null){
						Player targetplayer = player.getServer().getPlayer(args[0]);
						
						Location pl = targetplayer.getLocation();
						double x = pl.getX();
						double y = pl.getY();
						double z = pl.getZ();
						player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.YELLOW+args[0]+ChatColor.GRAY+" Location is"+ChatColor.YELLOW+" X: "+Math.round(x)+" Y: "+Math.round(y)+" Z: "+Math.round(z)+" World: "+targetplayer.getWorld().getName());
						player.playSound(pl, Sound.ENTITY_SNOWBALL_THROW, 10, 0);

					}else{
						player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Player is not online.");
					}
				}else{
					Location pl = player.getLocation();
					double x = pl.getX();
					double y = pl.getY();
					double z = pl.getZ();
					player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"You Location is"+ChatColor.YELLOW+" X: "+Math.round(x)+" Y: "+Math.round(y)+" Z: "+Math.round(z));
					player.playSound(pl, Sound.ENTITY_SNOWBALL_THROW, 10, 0);
				}
			}
			if(CommandLabel.equalsIgnoreCase("sk")){
				if(args.length == 0){
					player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Please type /sk <warned/banned> <playername>");
				}else if(args.length == 1){
					player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Please type /sk <warned/banned> <playername>");
				}else if(args.length == 2){
					if(args[0].equalsIgnoreCase("warned")){
						for(Player player1 : Bukkit.getOnlinePlayers()){
							player1.sendMessage(ChatColor.BLUE+"Sk. Network> "+ChatColor.DARK_AQUA+args[1]+" has been warned.");
							player1.playSound(player1.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
							}
					}if(args[0].equalsIgnoreCase("banned")){
						for(Player player1 : Bukkit.getOnlinePlayers()){
							player1.sendMessage(ChatColor.BLUE+"Sk. Network> "+ChatColor.DARK_AQUA+args[1]+" has been banned.");
							player1.playSound(player1.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
							}
					}
				}else{
					player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"Please type /sk <warned/banned> <playername>");	
				}
			}
			if(CommandLabel.equalsIgnoreCase("color")){
				player.sendMessage(ChatColor.BLUE+"Server> "+ChatColor.GRAY+"All color :"+ChatColor.DARK_BLUE+" 1"+ChatColor.DARK_GREEN+" 2"
						+ChatColor.DARK_AQUA+" 3"+ChatColor.DARK_RED+" 4"+ChatColor.DARK_PURPLE+" 5"+ChatColor.GOLD+" 6 "+ChatColor.GRAY+" 7"
						+ChatColor.DARK_GRAY+" 8"+ChatColor.BLUE+" 9"+ChatColor.GREEN+" a"+ChatColor.AQUA+" b"+ChatColor.RED+" c"+ChatColor.LIGHT_PURPLE+" d"
						+ChatColor.YELLOW+" e"+ChatColor.WHITE+" f");
				player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 10, 1);
			}

		return false;
		
	}

		
}
