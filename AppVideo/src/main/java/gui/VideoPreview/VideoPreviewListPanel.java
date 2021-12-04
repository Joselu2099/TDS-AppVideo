package gui.VideoPreview;

import gui.Util.SwapLayout;
import gui.Util.WrapLayout;
import model.Video;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
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
        prewviewList.stream().map(v -> new VideoPreview(v, videoPreview -> {
            if(videoPreview.getBorder() instanceof MatteBorder)
                videoPreview.setBorder(new EmptyBorder(0,0,0,0));
            else videoPreview.setBorder(new MatteBorder(5,5,5,5, Color.BLUE));
            videoConsumer.accept(videoPreview.getVideo());
        })).forEach(panel::add);
        this.add(panel);
    }
}
