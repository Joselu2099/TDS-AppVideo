package gui.VideoPreview;

import com.formdev.flatlaf.IntelliJTheme;
import gui.HomePanel;
import gui.VideoPlayerWindow;
import launcher.Launcher;
import model.Video;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class VideoPreviewListPanel extends JPanel {


    public VideoPreviewListPanel(List<Video> videoList,VideoPreviewCallback callback) {
        setLayout(new FlowLayout());
        videoList.stream().map(v -> new VideoPreview(v, callback)).forEach(this::add);
    }


    public static void main(String[] args) {
        IntelliJTheme.setup(Launcher.class.getResourceAsStream("/themes/ArcPurple.theme.json"));
//        IntelliJTheme.setup(Launcher.class.getResourceAsStream("/themes/DarkPurple.theme.json"));
        EventQueue.invokeLater(() -> {
            try {
                String[] url = {
                        "https://www.youtube.com/watch?v=56eIZKyhM6c",
                };
                List<Video> videoList = Arrays.stream(url).map(Video::new).collect(Collectors.toList());
                VideoPreviewListPanel videoPreviewListPanel = new VideoPreviewListPanel(videoList,null);
                videoPreviewListPanel.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
