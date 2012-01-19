package ltguide.minebackup;

import java.util.concurrent.Callable;

import org.bukkit.World;
import org.bukkit.entity.Player;

public class CallSync implements Callable<Boolean> {
	private final MineBackup plugin;
	private final String action;
	private final World world;
	
	public CallSync(final MineBackup plugin, final String action, final World world) {
		this.plugin = plugin;
		this.action = action;
		this.world = world;
	}
	
	@Override public Boolean call() {
		if ("save".equals(action)) {
			world.save();
			
			for (final Player player : world.getPlayers())
				player.saveData();
		}
		else if ("count".equals(action)) plugin.persist.hasPlayers(world);
		
		return false;
	}
}