package gui;

import com.formdev.flatlaf.IntelliJTheme;
import controller.AppVideo;
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
	JPanel vidPanel;
	JFrame parent;
	List<Playlist> filteredPlaylist;
	
	/**
	 * Create the panel.
	 */
	public MyPlaylistPanel(JFrame parent ,List<Playlist> list ) {
		filteredPlaylist = list;
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
		btnSearchButton.addActionListener(l->{filteredPlaylist = AppVideo.getInstance().searchPlaylists(textField.getText());
											showPlaylistPreview(filteredPlaylist);});
		searchPanel.add(btnSearchButton);

		showPlaylistPreview(filteredPlaylist);
		JScrollPane scrollPane = new JScrollPane(vidPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);
	}

	public void showPlaylistPreview(List<Playlist> playlistList) {
		//TODO mostrar playlist
		
		/*
		vidPanel.swap(new VideoPreviewListPanel(playlistList,vid->{
			VideoPlayerWindow player = new VideoPlayerWindow(vid);
			player.showPlayer(parent);
		}));
		*/
	}
	
	public void setFilteredPlaylist(@NotNull List<Playlist> playlistList){
		this.filteredPlaylist = playlistList;
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

	
