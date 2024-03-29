package gui.VideoPreview;

import gui.Util.WrapLayout;
import model.Video;
import javax.swing.*;
import java.util.List;
import java.util.function.Consumer;

public class PlayListVideoPreviewPanel extends VideoPreviewListPanel{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlayListVideoPreviewPanel(List<Video> videoList, Consumer<Video> callback) {
        super(videoList, callback);
    }

    @Override
    protected JPanel createNewPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new WrapLayout(WrapLayout.LEFT));
        return panel;
    }
}
