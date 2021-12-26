package gui;

import controller.AppVideo;
import gui.VideoPreview.VideoPreviewListPanel;
import model.Video;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RecentPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JFrame parent;
	VideoPreviewListPanel videoPreviewListPanel;

	/**
	 * Create the panel.
	 */
	public RecentPanel(JFrame parent) {
		setLayout(new BorderLayout(8, 8));
		// Necesitamos el JFrame para ocultar la ventana cuando lanzamos
		// el visualizador de video.
		this.parent = parent;
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));
		add(topPanel,BorderLayout.NORTH);

		JPanel textPanel = new JPanel();
		topPanel.add(textPanel);
		
		JLabel text = new JLabel("Top 5 videos mas recientes");
		text.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		textPanel.add(text);

		this.videoPreviewListPanel = new VideoPreviewListPanel(AppVideo.getInstance().getCurrentUserRencentVideo(),this::videoClickCallback);
		this.add(videoPreviewListPanel,BorderLayout.CENTER);
		// callback to update panel
		AppVideo.getInstance().subscribeRecentVideoChanged(()->SwingUtilities.invokeLater(()->showVideoPreview(AppVideo.getInstance().getCurrentUserRencentVideo())));
	}

	private void videoClickCallback(Video v){
		VideoPlayerWindow playerWindow = new VideoPlayerWindow(v);
		playerWindow.showPlayer(parent);
	}

	public void showVideoPreview(List<Video> videoList) {
		videoPreviewListPanel.setPrewviewList(videoList);
	}
}

	
