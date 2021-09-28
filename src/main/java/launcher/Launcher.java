package launcher;

import java.awt.EventQueue;

import gui.LoginWindow;

public class Launcher {
	public static void main(final String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//AppVideo.getInstancia().cargarCanciones();
					LoginWindow ventana = new LoginWindow();
					ventana.showWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
