package gui;

import controller.AppVideo;
import gui.VideoPreview.VideoPreviewListPanel;
import model.Video;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TrendsPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    JFrame parent;
    VideoPreviewListPanel videoPreviewListPanel;


    /**
     * Create the panel.
     */

    private static final int TOP_VIDEO_COUNT = 10;

    public TrendsPanel(JFrame parent) {
        setLayout(new BorderLayout(8, 8));
        // Necesitamos el JFrame para ocultar la ventana cuando lanzamos
        // el visualizador de video.
        this.parent = parent;
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));
        add(topPanel,BorderLayout.NORTH);

        JPanel textPanel = new JPanel();
        topPanel.add(textPanel);

        JLabel text = new JLabel("Videos mas vistos");
        text.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
        textPanel.add(text);


        this.videoPreviewListPanel = new VideoPreviewListPanel(AppVideo.getInstance().getMostTopViewVideos(TOP_VIDEO_COUNT),this::videoClickCallback);
        this.add(videoPreviewListPanel,BorderLayout.CENTER);

        // callback to update panel
        AppVideo.getInstance().subscribeRecentVideoChanged(()->SwingUtilities.invokeLater(()->showVideoPreview(AppVideo.getInstance().getMostTopViewVideos(TOP_VIDEO_COUNT))));

    }

    private void videoClickCallback(Video v){
        VideoPlayerWindow playerWindow = new VideoPlayerWindow(v);
        playerWindow.showPlayer(parent);
    }

    public void showVideoPreview(List<Video> videoList) {
        videoPreviewListPanel.setPrewviewList(videoList);
    }
}


