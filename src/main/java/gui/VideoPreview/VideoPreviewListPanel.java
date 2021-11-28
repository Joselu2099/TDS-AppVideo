package gui.VideoPreview;

import com.formdev.flatlaf.IntelliJTheme;
import gui.HomePanel;
import gui.Util.SwapLayoutPanelWrapper;
import gui.Util.WrapLayout;
import gui.VideoPlayerWindow;
import launcher.Launcher;
import model.Video;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class VideoPreviewListPanel extends JPanel {


    Consumer<Video> videoConsumer;
    SwapLayoutPanelWrapper swapLayoutPanelWrapper = new SwapLayoutPanelWrapper();
    public VideoPreviewListPanel(List<Video> videoList,Consumer<Video> callback) {
        this.videoConsumer = callback;
        setPrewviewList(videoList);
        add(swapLayoutPanelWrapper.getPanel());
    }

    public void setPrewviewList(List<Video> prewviewList){

        JPanel panel = new JPanel(new WrapLayout());
        prewviewList.stream().map(v -> new VideoPreview(v, videoConsumer)).forEach(panel::add);
        swapLayoutPanelWrapper.swap(panel);
    }
}
