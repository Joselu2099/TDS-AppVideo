package gui;

import com.formdev.flatlaf.IntelliJTheme;
import gui.Util.SwapLayoutPanelWrapper;
import gui.VideoPreview.VideoPreviewListPanel;
import launcher.Launcher;
import model.Label;
import model.Video;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HomePanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	JFrame parent;
	SwapLayoutPanelWrapper vidPanel = new SwapLayoutPanelWrapper();
	public HomePanel(JFrame parent) {
		// Necesitamos el JFrame para ocultar la ventana cuando lanzamos
		// el visualizador de video.
		this.parent = parent;
		setLayout(new BorderLayout(0, 0));
		

		JPanel searchPanel = new JPanel();
		add(searchPanel, BorderLayout.NORTH);
		
		textField = new JTextField();
		searchPanel.add(textField);
		textField.setColumns(30);
		
		JButton btnSearchButton = new JButton("BUSCAR");
		searchPanel.add(btnSearchButton);

		add(vidPanel.getPanel(), BorderLayout.CENTER);
	}

	public void showVideoPreview(List<Video> videoList) {
		vidPanel.swap(new VideoPreviewListPanel(videoList,vid->{
			this.setVisible(false);

			VideoPlayerWindow player = new VideoPlayerWindow(vid);
			player.setModal(true);
			player.setLocationRelativeTo(this);
			player.setVisible(true);
			this.setVisible(true);
		}));
	}

	public static void main(String[] args) {
		IntelliJTheme.setup(Launcher.class.getResourceAsStream("/themes/ArcPurple.theme.json"));
//        IntelliJTheme.setup(Launcher.class.getResourceAsStream("/themes/DarkPurple.theme.json"));
		EventQueue.invokeLater(() -> {
			try {
				String[] url = {
						"https://www.youtube.com/watch?v=bxF-pQSzSUM",
						"https://www.youtube.com/watch?v=XKfgdkcIUxw",
						"https://www.youtube.com/watch?v=56eIZKyhM6c",
				};
				List<Video> videoList = Arrays.stream(url).map(Video::new).collect(Collectors.toList());
				JFrame f = new JFrame();
				HomePanel h = new HomePanel();
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setContentPane(h);
				f.setBounds(0, 0, 800, 600);
				f.setVisible(true);
				String[] urlX = {
						"https://www.youtube.com/watch?v=bxF-pQSzSUM",
						"https://www.youtube.com/watch?v=56eIZKyhM6c",
				};
				List<Video> videoListX = Arrays.stream(urlX).map(Video::new).collect(Collectors.toList());
				h.showVideoPreview(videoListX);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
