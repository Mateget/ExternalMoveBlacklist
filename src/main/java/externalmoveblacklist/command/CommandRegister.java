package externalmoveblacklist.command;

import externalmoveblacklist.ExternalMoveBlacklist;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExternalMoveBlacklist.MODID)
public class CommandRegister {
		
	@SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
		new ExternalMoveBlacklistCommand(event.getDispatcher());
    }
}