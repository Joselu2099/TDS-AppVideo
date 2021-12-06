package gui.VideoPreview;

import gui.Util.SwapLayout;
import gui.Util.WrapLayout;
import model.Video;
import javax.swing.*;
import java.util.List;
import java.util.function.Consumer;

public class VideoPreviewListPanel extends JPanel {


    protected Consumer<Video> videoConsumer;
    public VideoPreviewListPanel(List<Video> videoList,Consumer<Video> callback) {
        this.videoConsumer = callback;
        setLayout(new SwapLayout(this));
        setPrewviewList(videoList);
    }

    protected void callback( VideoPreview p){
        if (videoConsumer!=null)
            videoConsumer.accept(p.getVideo());
    }

    protected JPanel createNewPanel(){
        return new JPanel(new WrapLayout());
    }

    protected VideoPreview createPreview(Video v){
        return new VideoPreview(v,this::callback);
    }

    public void setPrewviewList(List<Video> prewviewList){
        JPanel panel = createNewPanel();
        prewviewList.stream().map(this::createPreview).forEach(panel::add);
        this.add(panel);
    }
}
