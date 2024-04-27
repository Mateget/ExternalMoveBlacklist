package externalmoveblacklist.utlis;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import externalmoveblacklist.ExternalMoveBlacklist;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.server.permission.DefaultPermissionLevel;
import net.minecraftforge.server.permission.PermissionAPI;

public class PermissionHandler {
	
	public static final String BASE =ExternalMoveBlacklist.MODID+".";
	public static final String ADMIN = "admin.";
	
	public static final String IGNORE_BLACKLIST = BASE + ADMIN + "ignoreblacklist";
	public static final String RELOAD = BASE + ADMIN + "reload";
	
	
	public static void init() {
		PermissionAPI.registerNode(IGNORE_BLACKLIST, DefaultPermissionLevel.NONE, "Ignore blacklist");
		PermissionAPI.registerNode(RELOAD, DefaultPermissionLevel.OP, "Reload mod");
	}
	
	public static boolean playerHasPermission(CommandSource source, String node) {
		 try {
			return PermissionAPI.hasPermission(source.getPlayerOrException(), node);
		} catch (CommandSyntaxException e) {
			
		}
		return true;
	}
		
	public static boolean playerHasPermission(PlayerEntity playerEntity, String node) {
		return PermissionAPI.hasPermission(playerEntity, node);
	}
	
	public static boolean playerHasIgnoreBlacklistPermission(PlayerEntity playerEntity) {
		return playerHasPermission(playerEntity,IGNORE_BLACKLIST);
	}
	
	public static boolean playerHasReloadPermission(CommandSource source) {
		return playerHasPermission(source,RELOAD);
	}


}
