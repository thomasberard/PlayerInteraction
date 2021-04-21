package fr.mrflamme26.playerinteraction;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import fr.mrflamme26.playerinteraction.PlayerInteraction;

public class PlayerInteraction extends JavaPlugin implements Listener {

	public final Logger logger = Logger.getLogger("Minecraft");
	public static PlayerInteraction plugin;
	
	@Override
	public void onEnable() {

		PluginDescriptionFile pluginFile = this.getDescription();
		
		this.logger.info("§f[§e" + pluginFile.getName() + "§f] §aVersion " + pluginFile.getVersion() + ".");
		this.logger.info("§f[§e" + pluginFile.getName() + "§f] §aChargé avec succès.");
		this.logger.info("§f[§e" + pluginFile.getName() + "§f] §cUn plugin réalisé par MrFlamme26.");
		
		plugin = this;
		getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {
		
		PluginDescriptionFile pluginFile = this.getDescription();
		
		this.logger.info("§f[§e" + pluginFile.getName() + "§f] §aDéchargé avec succès.");
	}

	@EventHandler
	public void onPickupEvent(EntityPickupItemEvent event) {
		
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			
			if (!player.isOp() || !player.hasPermission("playerinteraction.itemPickup")) {
				event.setCancelled(true);
			}

		}
	}

	@EventHandler
	public void onDropEvent(PlayerDropItemEvent event) {
		
		Player player = event.getPlayer();
		
		if (!player.isOp() || !player.hasPermission("playerinteraction.itemDrop")) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if (!player.isOp() || !player.hasPermission("playerinteraction.blockBreak")) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if (!player.isOp() || !player.hasPermission("playerinteraction.blockPlace")) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (!player.isOp() || !player.hasPermission("playerinteraction.trapdoor")) {
			if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				Material block = event.getClickedBlock().getType();
				if (!player.isOp() && (block == Material.ACACIA_TRAPDOOR || block == Material.BIRCH_TRAPDOOR || block == Material.CRIMSON_TRAPDOOR || block == Material.DARK_OAK_TRAPDOOR || block == Material.IRON_TRAPDOOR || block == Material.JUNGLE_TRAPDOOR || block == Material.OAK_TRAPDOOR || block == Material.SPRUCE_TRAPDOOR || block == Material.WARPED_TRAPDOOR)) {
					event.setCancelled(true);
				}
			}

		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		
		if (!player.isOp() || !player.hasPermission("playerinteraction.farmland")) {
			if (event.getAction().equals(Action.PHYSICAL) && event.getClickedBlock().getType().equals(Material.FARMLAND)) {
				  event.setCancelled(true);
			}
		}
	}
}
