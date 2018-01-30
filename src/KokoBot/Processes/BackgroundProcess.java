package KokoBot.Processes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import net.dv8tion.jda.core.entities.MessageChannel;

public abstract class BackgroundProcess implements ActionListener {

	public MessageChannel channel;
	private Timer timer;
	public BackgroundProcess(int timemillis) {
		timer = new Timer(timemillis, this);
		timer.setRepeats(true);
		timer.start();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		
	}

}
