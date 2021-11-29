package gui;

import com.formdev.flatlaf.IntelliJTheme;
import gui.Util.SwapLayout;
import launcher.Launcher;
import model.Playlist;
import javax.swing.*;
import org.jetbrains.annotations.NotNull;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyPlaylistPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField textField;
	private List<Playlist> repoPlaylists;
	private List<Playlist> currentPlaylists;
	JPanel vidPanel;
	JFrame parent;
	
	/**
	 * Create the panel.
	 */
	public MyPlaylistPanel(JFrame parent ,List<Playlist> list ) {
		repoPlaylists = list;
		vidPanel = new JPanel();
		vidPanel.setLayout(new SwapLayout(vidPanel));
		// Necesitamos el JFrame para ocultar la ventana cuando lanzamos
		// el visualizador de video.
		this.parent = parent;
		setLayout(new BorderLayout(0, 0));
		
		JPanel searchPanel = new JPanel();
		add(searchPanel, BorderLayout.NORTH);
		
		textField = new JTextField();
		searchPanel.add(textField);
		textField.setColumns(30);
		
		JButton btnSearchButton = new JButton("BUSCAR");
		btnSearchButton.addActionListener(l->filterByName(textField.getText()));
		searchPanel.add(btnSearchButton);

		showPlaylistPreview(repoPlaylists);
		JScrollPane scrollPane = new JScrollPane(vidPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);
	}

	public void showPlaylistPreview(List<Playlist> playlistList) {
		currentPlaylists = playlistList;
		
		//TODO mostrar playlist
		
		/*
		vidPanel.swap(new VideoPreviewListPanel(playlistList,vid->{
			VideoPlayerWindow player = new VideoPlayerWindow(vid);
			player.showPlayer(parent);
		}));
		*/
	}
	
	public void filterByName(String text){
		currentPlaylists = repoPlaylists.stream()
				.filter(s-> s.getTitle().contains(text))
				.collect(Collectors.toList());
		showPlaylistPreview(currentPlaylists);
	}
	
	public void updateVideoList(@NotNull List<Playlist> playlistList){
		this.repoPlaylists = playlistList;
		showPlaylistPreview(playlistList);
	}

	public static void main(String[] args){
		IntelliJTheme.setup(Launcher.class.getResourceAsStream("/themes/ArcPurple.theme.json"));
//        IntelliJTheme.setup(Launcher.class.getResourceAsStream("/themes/DarkPurple.theme.json"));
		EventQueue.invokeLater(() -> {
			try {
//				Video v = new Video("https://www.youtube.com/watch?v=XKfgdkcIUxw");
//				v.addLabels(model.Label.INFANTIL);
//				v.addLabels(model.Label.VIDEOCLIP);
				JFrame f = new JFrame();
//				Set<Label> labelSet = v.getLabels();
//				LabalManager manager = new LabalManager(labelSet,
//						l->{v.addLabels(l);labelSet.add(l);},
//						l->{v.removeLabels(l);labelSet.remove(l);});
				MyPlaylistPanel manager = new MyPlaylistPanel(f,new ArrayList<Playlist>());
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setContentPane(manager);
				f.setBounds(0, 0, 800, 600);
				f.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}

	
