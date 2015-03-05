package uchopper.script;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class Banking implements Strategy {

	SceneObject[] bank = SceneObjects.getNearest(2213);

	public boolean activate() {

		return Inventory.isFull() && Players.getMyPlayer().getAnimation() == -1;
	}

	@Override
	public void execute() {
		if (Players.getMyPlayer().getAnimation() == -1 && bank.length > 0
				&& bank[0].distanceTo() > 0 && Game.getOpenInterfaceId() == -1
				&& bank != null) {
			bank[0].interact(0);
			Time.sleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return Game.getOpenInterfaceId() == 5292;
				}
			}, 4250);
		} else {
			if (Game.getOpenInterfaceId() == 5292) {
				Bank.depositAll();
				Time.sleep(new SleepCondition() {
					@Override
					public boolean isValid() {
						return Inventory.isEmpty();
					}
				}, 4000);
				Bank.close();
				Time.sleep(new SleepCondition() {
					@Override
					public boolean isValid() {
						return Game.getOpenInterfaceId() != 5292;
					}
				}, 2000);
			}
		}

	}

}
