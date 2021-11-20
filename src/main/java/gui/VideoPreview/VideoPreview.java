package gui.VideoPreview;

import gui.UIUtils;
import model.Video;
import tds.video.VideoWeb;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class VideoPreview extends JPanel {

	/**
	 * Create the panel.
	 */
	Video video;
	JLabel lblPreviewLabel = new JLabel("");
	JLabel lblTitle = new JLabel("Title");
	public VideoPreview(Video v,VideoPreviewCallback videoCallback) {
		this.video = v;


		setLayout(new BorderLayout(0, 0));
		setMinimumSize(new Dimension(180,140));

		lblPreviewLabel.setIcon(new VideoWeb().getThumb(v.getUrl()));
		lblPreviewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPreviewLabel, BorderLayout.CENTER);

		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setText(UIUtils.stringToHTML(v.getTitle()));
		add(lblTitle, BorderLayout.SOUTH);


		// Observer Event
		// No usamos listener a panel por falso posivivos.
		lblPreviewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (videoCallback != null)
					videoCallback.clicked(v);
			}
		});
		lblTitle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (videoCallback != null)
					videoCallback.clicked(v);
			}
		});
	}

}
