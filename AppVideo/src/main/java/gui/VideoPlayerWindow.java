package gui;

import com.formdev.flatlaf.IntelliJTheme;
import controller.AppVideo;
import gui.Util.VideoWebFactory;
import launcher.Launcher;
import model.Label;
import model.Video;
import tds.video.VideoWeb;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;

public class VideoPlayerWindow extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static VideoWeb videoWeb;
    private static final int BOX_PADDING = 20;

    public VideoPlayerWindow(Video video) {
        videoWeb = VideoWebFactory.getInstance();
        setIconImage(Toolkit.getDefaultToolkit().getImage(VideoPlayerWindow.class.getResource("/images/multimediavideoplayer_128px.png")));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                videoWeb.cancel(); // Stop playing in background when windows are closed.
            }
        });

        AppVideo.getInstance().incrementVideoViewAndAddToRecent(video);


    	JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(15,15,15,15));
        setBounds(100, 100, 780, 520);

        setSize( 780, 520);
    	getContentPane().add(panel, BorderLayout.CENTER);

    	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        VideoWeb videoPlayerPlaceholder = videoWeb;
        videoPlayerPlaceholder.setMinimumSize(new Dimension(366,220));
        videoPlayerPlaceholder.setMaximumSize(new Dimension(366,220));
        videoPlayerPlaceholder.setSize(new Dimension(366,220));

        JPanel videoPanel = new JPanel();
        videoPanel.setLayout(new BoxLayout(videoPanel,BoxLayout.Y_AXIS));
        videoPanel.setMinimumSize(new Dimension(366,220));
        videoPanel.setMaximumSize(new Dimension(366,220));
        videoPanel.setSize(new Dimension(366,220));
        videoPanel.add(videoPlayerPlaceholder);
        panel.add(videoPanel);
        videoPlayerPlaceholder.playVideo(video.getUrl());

        panel.add(Box.createVerticalStrut(BOX_PADDING));

    	JPanel titleViewPanel = new JPanel();
        titleViewPanel.setLayout(new GridLayout(1,2));
    	panel.add(titleViewPanel);

        // Usamos textarea por si el titulo es largo y necesitamos line wrap
        JTextArea lblVideoTitle = new JTextArea(video.getTitle());
        lblVideoTitle.setEditable(false);
        lblVideoTitle.setLineWrap(true);

    	titleViewPanel.add(lblVideoTitle);

        titleViewPanel.add(Box.createHorizontalStrut(10));
    	
    	JLabel lblViewList = new JLabel(video.getViews()+" Views",SwingConstants.RIGHT);
    	titleViewPanel.add(lblViewList);



        panel.add(Box.createVerticalStrut(BOX_PADDING));

        JPanel tagContainer = new JPanel();
        tagContainer.setLayout(new BorderLayout());
        Set<Label> labelSet = video.getLabels();


        JPanel labelManager = new LabelEditorPanel(labelSet, label -> {
            video.addLabels(label);
        },label -> {
            video.removeLabels(label);
        });

        tagContainer.add(labelManager,BorderLayout.CENTER);
        panel.add(tagContainer);
    }


    public void showPlayer(JFrame parentFrameToHide){
        parentFrameToHide.setVisible(false);
        this.setModal(true);
        this.setLocationRelativeTo(this);
        this.setVisible(true);
        parentFrameToHide.setVisible(true);
    }


//     public static void main(String[] args) {
//         IntelliJTheme.setup(Launcher.class.getResourceAsStream("/themes/ArcPurple.theme.json"));
// //        IntelliJTheme.setup(Launcher.class.getResourceAsStream("/themes/DarkPurple.theme.json"));
//         EventQueue.invokeLater(() -> {
//             try {
//                 Video v = new Video("https://www.youtube.com/watch?v=XKfgdkcIUxw");
//                 v.addLabels(Label.INFANTIL);
//                 v.addLabels(Label.VIDEOCLIP);
//                 VideoPlayerWindow frame = new VideoPlayerWindow(v);
//                 frame.setVisible(true);
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//         });
//     }
}

