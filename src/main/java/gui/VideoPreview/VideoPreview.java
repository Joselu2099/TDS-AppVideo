package gui.VideoPreview;

import gui.Util.MultilineLabel;
import gui.Util.UIUtils;
import gui.Util.VideoWebFactory;
import model.Video;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VideoPreview extends JPanel {

	/**
	 * Create the panel.
	 */
	Video video;
	JLabel lblPreviewLabel = new JLabel("");
	public VideoPreview(Video v,VideoPreviewCallback videoCallback) {
		this.video = v;


		setLayout(new BorderLayout(0, 0));
		setMinimumSize(new Dimension(180,140));
		setMinimumSize(new Dimension(200,140));
		setMaximumSize(new Dimension(200,140));

		// 120x90
		Dimension thumbnailSize = new Dimension(120,90);
		lblPreviewLabel.setSize(thumbnailSize);
		lblPreviewLabel.setMaximumSize(thumbnailSize);
		lblPreviewLabel.setMinimumSize(thumbnailSize);
		lblPreviewLabel.setPreferredSize(thumbnailSize);
		lblPreviewLabel.setIcon(VideoWebFactory.getInstance().getThumb(v.getUrl()));
		lblPreviewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPreviewLabel, BorderLayout.NORTH);
		lblPreviewLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		MultilineLabel lblTitle = new MultilineLabel(v.getTitle());
		lblTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);
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
