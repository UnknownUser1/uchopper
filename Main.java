package uchopper;

import uchopper.script.Banking;
import uchopper.script.ChopTree;
import java.util.ArrayList;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.api.events.listeners.MessageListener;


@ScriptManifest(author = "Unknown User", category = Category.WOODCUTTING, description = "Chops willows at draynor", name = "UWillows", servers = { "Ikov" }, version = 1.1D)
public class Main extends Script implements MessageListener {

	private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();

	private static int treeChops = 0;

	public boolean onExecute() {
		strategies.add(new Banking());
		strategies.add(new ChopTree());

		provide(strategies);

		return true;
	}

	public void onFinish() {
		System.out.println("Scriped Ended");
		System.out.println("You Chopped " + treeChops + " Logs");

	}

	public void messageReceived(MessageEvent m) {
		if (m.getMessage().contains("get some Willow logs")) {
			treeChops += 1;
		}
	}
}
