package KokoBot.Processes;

import KokoBot.KokoBot;
import KokoBot.Processes.BackgroundProcesses.CheckForVideos;
import net.dv8tion.jda.core.entities.Guild;
public class ProcessManager {

	
	public static void InitialiseProcesses(Guild guild) {
		KokoBot.backgroundProcesses.add(new CheckForVideos(1000,guild));
	}
	
	
}
