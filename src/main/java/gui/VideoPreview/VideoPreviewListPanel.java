package gui.VideoPreview;

import gui.VideoPlayerWindow;
import model.Video;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VideoPreviewListPanel extends JPanel {


    public VideoPreviewListPanel(List<Video> videoList,VideoPreviewCallback callback) {
        super();
        setLayout(new FlowLayout());
        videoList.stream().map(v -> new VideoPreview(v, callback)).forEach(this::add);
    }

}
