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

public class HomePanel extends JFrame {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	SwapLayoutPanelWrapper vidPanel = new SwapLayoutPanelWrapper();
	public HomePanel() {
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

	public void showVideoPreview(VideoPreviewListPanel videoPreviewListPanel){
		vidPanel.swap(videoPreviewListPanel);
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
				HomePanel h = new HomePanel();
				h.setBounds(0, 0, 800, 600);
				h.showVideoPreview(new VideoPreviewListPanel(videoList,null));
				h.setLocationRelativeTo(null);
				h.setVisible(true);
				String[] urlX = {
						"https://www.youtube.com/watch?v=bxF-pQSzSUM",
						"https://www.youtube.com/watch?v=56eIZKyhM6c",
				};
				List<Video> videoListX = Arrays.stream(urlX).map(Video::new).collect(Collectors.toList());
				h.showVideoPreview(new VideoPreviewListPanel(videoListX,null));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
