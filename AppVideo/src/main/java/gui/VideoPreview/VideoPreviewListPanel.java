package gui.VideoPreview;

import gui.Util.SwapLayout;
import gui.Util.WrapLayout;
import model.Video;
import javax.swing.*;
import java.util.List;
import java.util.function.Consumer;

public class VideoPreviewListPanel extends JPanel {


    Consumer<Video> videoConsumer;
    public VideoPreviewListPanel(List<Video> videoList,Consumer<Video> callback) {
        this.videoConsumer = callback;
        setLayout(new SwapLayout(this));
        setPrewviewList(videoList);
    }

    public void setPrewviewList(List<Video> prewviewList){

        JPanel panel = new JPanel(new WrapLayout());
        prewviewList.stream().map(v -> new VideoPreview(v, videoConsumer)).forEach(panel::add);
        this.add(panel);
    }
}
