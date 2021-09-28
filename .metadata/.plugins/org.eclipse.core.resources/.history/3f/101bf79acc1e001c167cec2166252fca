package launcher;

import java.awt.EventQueue;

import gui.Login;

public class Launcher {
	public static void main(final String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//AppVideo.getInstancia().cargarCanciones();
					Login ventana = new Login();
					ventana.showWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
