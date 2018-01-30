package KokoBot.Processes;

import KokoBot.KokoBot;
import KokoBot.Processes.BackgroundProcesses.CheckForVideos;
public class ProcessManager {

	
	public static void InitialiseProcesses() {
		KokoBot.backgroundProcesses.add(new CheckForVideos(1000));
	}
	
	
}
