package uchopper.script;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class ChopTree implements Strategy {

	SceneObject[] willowTree = SceneObjects.getNearest(1308);

	public boolean activate() {

		return !Inventory.isFull()
				&& Players.getMyPlayer().getAnimation() == -1;
	}

	@Override
	public void execute() {
		if (willowTree.length > 0 && willowTree[0].distanceTo() > 0
				&& Players.getMyPlayer().getAnimation() == -1
				&& willowTree != null) {
			willowTree[0].interact(1);
			Time.sleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return Players.getMyPlayer().getAnimation() != -1;
				}
			}, 4250);
		}

	}

}
