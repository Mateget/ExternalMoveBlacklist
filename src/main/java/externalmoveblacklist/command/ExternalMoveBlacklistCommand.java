package externalmoveblacklist.command;

import java.util.List;
import java.util.stream.Collectors;

import com.mojang.brigadier.CommandDispatcher;
import com.pixelmonmod.pixelmon.api.moveskills.MoveSkill;

import externalmoveblacklist.config.ConfigHandler;
import externalmoveblacklist.utlis.ChatUtils;
import externalmoveblacklist.utlis.PermissionHandler;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class ExternalMoveBlacklistCommand {
		
	public ExternalMoveBlacklistCommand(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("externalmoveblacklist")
				.then(
						Commands.literal("reload").requires((source)->PermissionHandler.playerHasReloadPermission(source))
						.executes((command)->{
							ConfigHandler.readAllConfigs();
					        List<String> alerts = ConfigHandler.configValider();
					        command.getSource().getPlayerOrException().sendMessage(new StringTextComponent("Config reloaded check console for errors !"), command.getSource().getPlayerOrException().getUUID());
					        if(alerts != null && !alerts.isEmpty()) {
					        	for(String alert : alerts) {
					        		command.getSource().getPlayerOrException().sendMessage(new StringTextComponent(
					    					ChatUtils.translateColourCodes('&', alert)), command.getSource().getPlayerOrException().getUUID());
					        	}
					        	List<String> availableMoveSkillNames = MoveSkill.moveSkills.stream().map(ms->ms.name).collect(Collectors.toList());
					        	command.getSource().getPlayerOrException().sendMessage(new StringTextComponent(
				    					ChatUtils.translateColourCodes('&', "&cAvailable moveskills : " + String.join(", ", availableMoveSkillNames))), command.getSource().getPlayerOrException().getUUID());
					        }
							return 1;
						})
				));
	}
	

}
