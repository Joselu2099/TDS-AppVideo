package gui.VideoPreview;

import model.Video;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class SelectVideoPrewviewListPanel extends VideoPreviewListPanel{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SelectVideoPrewviewListPanel(List<Video> videoList, Consumer<Video> callback) {
        super(videoList, callback);
    }

    public SelectVideoPrewviewListPanel(List<Video> videoList, List<Video> selected, Consumer<Video> callback) {
        super(videoList, callback);
        setPrewviewList(videoList,selected);
    }

    @Override
    protected void callback(VideoPreview p) {
        if (p.getBorder() instanceof LineBorder){
            p.setBorder(new EmptyBorder(0,0,0,0));
        }else{
            p.setBorder(new LineBorder(Color.BLUE,4));
        }
        super.callback(p);
    }

    public void setPrewviewList(List<Video> prewviewList,List<Video> selected) {
        JPanel panel = createNewPanel();
        prewviewList.stream().map(v -> {
            VideoPreview preview = new VideoPreview(v, this::callback);
            if (selected.contains(v)){
                preview.setBorder(new LineBorder(Color.BLUE,4));
            }
            return preview;
        }).forEach(panel::add);
        this.add(panel);
    }

}
