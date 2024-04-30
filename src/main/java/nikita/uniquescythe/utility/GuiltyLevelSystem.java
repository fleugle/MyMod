package nikita.uniquescythe.utility;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardCriterion;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.text.Text;
import nikita.uniquescythe.UniqueScythe;

public class GuiltyLevelSystem {

	public static void addGuiltyLevelsToPlayer(PlayerEntity player, int amount){
		CommandsExecuter.executeCommand(player, "");

	}

	public static void createIntScoreboadIfMissing(Scoreboard scoreboard, ScoreboardCriterion criteria, String objectiveName){

		ScoreboardObjective objective = scoreboard.getObjective(objectiveName);

		if (objective == null) {
			// Create a new scoreboard named "GuiltyLevel"
			scoreboard.addObjective(objectiveName, criteria, Text.of(objectiveName), ScoreboardCriterion.RenderType.INTEGER);

			//additional check
			if (scoreboard.getObjective(objectiveName) != null){
				UniqueScythe.LOGGER.info("objective "+ objectiveName +" with criteria "+ criteria +" exists");
			}else UniqueScythe.LOGGER.info("failed objective "+ objectiveName + " with criteria " + criteria + " creation");
		}
	}


}
