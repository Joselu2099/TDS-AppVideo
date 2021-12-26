package gui;

import gui.VideoPreview.VideoPreviewListPanel;
import model.Label;
import model.Video;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controller.AppVideo;
import pulsador.Luz;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

public class HomePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField textField;
	private final VideoPreviewListPanel vidPanel;
	private Luz luz;
	private JPanel searchBoxPanel;
	Set<Label> labelSet = new TreeSet<>();

	public String getSearchText(){
		return textField.getText();
	}

	public Set<Label> getSearchLabelSet(){
		return Collections.unmodifiableSet(labelSet);
	}
	/**
	 * Create the panel.
	 */
	public HomePanel(JFrame parent) {
		// Necesitamos el JFrame para ocultar la ventana cuando lanzamos
		// el visualizador de video.
		setLayout(new BorderLayout(5,5));
		
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BoxLayout(searchPanel,BoxLayout.Y_AXIS));
		searchBoxPanel = new JPanel();
		searchPanel.add(searchBoxPanel);
		LabelEditorPanel labelManager = new LabelEditorPanel(labelSet,
				label -> {
					labelSet.add(label);
					AppVideo.getInstance().searchVideos(textField.getText(),labelSet);
				},
				label ->{
					labelSet.remove(label);
					AppVideo.getInstance().searchVideos(textField.getText(),labelSet);
				}
		);
		searchPanel.add(labelManager);
		add(searchPanel,BorderLayout.NORTH);

		textField = new JTextField();
		searchBoxPanel.add(textField);
		textField.setColumns(30);


		vidPanel = new VideoPreviewListPanel(new ArrayList<>(),vid->{
			VideoPlayerWindow player = new VideoPlayerWindow(vid);
			player.showPlayer(parent);
		});

		JButton btnSearchButton = new JButton("Buscar Video");
		btnSearchButton.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		btnSearchButton.addActionListener(l-> showVideoPreview(AppVideo.getInstance().searchVideos(textField.getText(),labelSet)));
		searchBoxPanel.add(btnSearchButton);

		JLabel loadVideos = new JLabel("                  ¿Quieres añadir nuevos videos? ");
		loadVideos.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
		searchBoxPanel.add(loadVideos);

		updateLuzComponent();

		JScrollPane scrollPane = new JScrollPane(vidPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(new EmptyBorder(0,0,0,0));

		showVideoPreview(AppVideo.getInstance().getFilteredVideoList());
		add(scrollPane,BorderLayout.CENTER);
//		add(vidPanel,BorderLayout.CENTER);
		AppVideo.getInstance().subscribeFilteredVideoChange(()->SwingUtilities.invokeLater(()-> showVideoPreview(AppVideo.getInstance().searchVideos(getSearchText(),getSearchLabelSet()))));
	}

	public void showVideoPreview(List<Video> videoList) {
		vidPanel.setPrewviewList(videoList);
	}

	public void updateLuzComponent(){
		if(luz!=null) searchBoxPanel.remove(luz);
		luz = new Luz();
		luz.setNombre("LoadVideo");
		luz.setEncendido(false);
		luz.setColor(Color.YELLOW);
		luz.addEncendidoListener(l-> {
			if (luz.isEncendido()){
				EventQueue.invokeLater(() -> {
					JFileChooser jFileChooser = new JFileChooser(".");
					int i = jFileChooser.showOpenDialog(this);
					if (i == JFileChooser.APPROVE_OPTION){
						File f = jFileChooser.getSelectedFile();
						AppVideo.getInstance().loadVideos(f.getPath());
					}
					luz.setEncendido(false);
					luz.revalidate();
					luz.repaint();
				});
			}
		});
		searchBoxPanel.add(luz);
	}
}

	
