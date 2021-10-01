package launcher;

import java.awt.EventQueue;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.IntelliJTheme;
import controller.AppVideo;
import gui.LoginWindow;
import javax.swing.*;

public class Launcher {
	public static void main(final String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new FlatLightLaf());
					IntelliJTheme.setup(Launcher.class.getResourceAsStream("/themes/arc-theme.theme.json"));
//					IntelliJTheme.setup(Launcher.class.getResourceAsStream("/themes/DarkPurple.theme.json"));
					//AppVideo.getInstance().loadVideos();


					LoginWindow ventana = new LoginWindow();
					ventana.showWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
