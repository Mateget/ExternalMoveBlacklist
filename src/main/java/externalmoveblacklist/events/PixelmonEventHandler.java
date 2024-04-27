package externalmoveblacklist.events;

import com.pixelmonmod.pixelmon.api.events.moveskills.UseMoveSkillEvent;

import externalmoveblacklist.config.ConfigHandler;
import externalmoveblacklist.utlis.ChatUtils;
import externalmoveblacklist.utlis.PermissionHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PixelmonEventHandler {
	
	
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onExternalMove(final UseMoveSkillEvent event) {
				
		if(ConfigHandler.config.getBlacklist() == null || !ConfigHandler.config.getBlacklist().contains(event.moveSkill.id) || !event.isCancelable()) {
			return;
		}
		
		if(!event.pixelmon.hasOwner()) {
			event.setCanceled(true);
			return;
		} 
		
		if(event.pixelmon.getOwner() instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity) event.pixelmon.getOwner();
			if(PermissionHandler.playerHasIgnoreBlacklistPermission(player)) {
				return;
			}
			event.setCanceled(true);
			StringTextComponent message = new StringTextComponent(
					ChatUtils.translateColourCodes('&', ConfigHandler.config.getBlacklistedMoveMessage()));
			player.sendMessage(message, player.getUUID());
		} else {
			event.setCanceled(true);
			return;
		}
	}
	
}