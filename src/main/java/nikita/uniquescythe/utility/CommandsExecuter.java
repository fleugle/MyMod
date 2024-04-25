package nikita.uniquescythe.utility;

import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import nikita.uniquescythe.UniqueScythe;

import java.util.Objects;

public class CommandsExecuter {

	public static void executeCommand(Entity entity, String command){
		UniqueScythe.LOGGER.info("Executing command for "+ entity);
		CommandManager commandManager = Objects.requireNonNull(entity.getServer()).getCommandManager();
		ServerCommandSource commandSource = entity.getServer().getCommandSource();
		commandManager.executePrefixedCommand(commandSource, command);
	}


}
