package nikita.uniquescythe.utility;

import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.World;
import nikita.uniquescythe.UniqueScythe;

import java.util.Objects;

public class CommandsExecuter {

	public static void executeCommand(Entity entity, String command){
		UniqueScythe.LOGGER.info("Executing command "+ command + " for entity " + entity);//just a nice logger
		CommandManager commandManager = Objects.requireNonNull(entity.getServer()).getCommandManager();
		ServerCommandSource commandSource = entity.getServer().getCommandSource();


		commandManager.executePrefixedCommand(commandSource.withSilent(), command);
		//withSilent makes the command execution not appear in the game chat.
	}

	public static void executeCommandOnServer( World world, String command ){
		UniqueScythe.LOGGER.info("Executing command "+ command + " on server " );//just a nice logger
		CommandManager commandManager = Objects.requireNonNull(world.getServer()).getCommandManager();
		ServerCommandSource commandSource = world.getServer().getCommandSource();


		commandManager.executePrefixedCommand(commandSource.withSilent(), command);
		//withSilent makes the command execution not appear in the game chat.
	}

}
