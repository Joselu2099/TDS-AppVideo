package gui;

import com.formdev.flatlaf.IntelliJTheme;
import gui.VideoPreview.VideoPreviewListPanel;
import launcher.Launcher;
import model.Label;
import model.Playlist;
import model.Video;
import javax.swing.*;

import model.VideoRepository;
import org.jetbrains.annotations.NotNull;
import controller.AppVideo;
import pulsador.Luz;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class HomePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField textField;
	JFrame parent;
	VideoPreviewListPanel vidPanel;
	private List<Video> filteredVideos;

	/**
	 * Create the panel.
	 */
	public HomePanel(JFrame parent ,List<Video> list ) {
		filteredVideos = list;
		// Necesitamos el JFrame para ocultar la ventana cuando lanzamos
		// el visualizador de video.
		this.parent = parent;
		setLayout(new BorderLayout());
		
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BoxLayout(searchPanel,BoxLayout.Y_AXIS));
		JPanel searchBoxPanel = new JPanel();
		Set<Label> labelSet = new TreeSet<>();
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

		JButton btnSearchButton = new JButton("BUSCAR");
		btnSearchButton.addActionListener(l->setFilteredVideos(AppVideo.getInstance().searchVideos(textField.getText(),labelSet)));
		searchBoxPanel.add(btnSearchButton);
		
		Luz luz = new Luz();
		luz.setNombre("LoadVideo");
//		luz.addEncendidoListener(l-> AppVideo.getInstance().loadVideos("xml/videos.xml"));

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

		showVideoPreview(filteredVideos);
		JScrollPane scrollPane = new JScrollPane(vidPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		add(scrollPane,BorderLayout.CENTER);
	}

	public List<Video> getFilteredVideos() {
		return filteredVideos;
	}

	public void setFilteredVideos(@NotNull List<Video> filteredVideos) {
		this.filteredVideos = filteredVideos;
		showVideoPreview(filteredVideos);
	}

	public void showVideoPreview(List<Video> videoList) {
		//currentList = videoList;
		if (vidPanel == null){
			vidPanel = new VideoPreviewListPanel(videoList,vid->{
				VideoPlayerWindow player = new VideoPlayerWindow(vid);
				player.showPlayer(parent);
			});
		}else {
			vidPanel.setPrewviewList(videoList);
		}
	}

	public static void main(String[] args) {
		IntelliJTheme.setup(Launcher.class.getResourceAsStream("/themes/ArcPurple.theme.json"));
//        IntelliJTheme.setup(Launcher.class.getResourceAsStream("/themes/DarkPurple.theme.json"));
		EventQueue.invokeLater(() -> {
			try {
				String[] url = {
						"https://www.youtube.com/watch?v=bxF-pQSzSUM",
						"https://www.youtube.com/watch?v=XKfgdkcIUxw",
						"https://www.youtube.com/watch?v=56eIZKyhM6c",
						"https://www.youtube.com/watch?v=bxF-pQSzSUM",
						"https://www.youtube.com/watch?v=XKfgdkcIUxw",
						"https://www.youtube.com/watch?v=56eIZKyhM6c",
						"https://www.youtube.com/watch?v=bxF-pQSzSUM",
						"https://www.youtube.com/watch?v=XKfgdkcIUxw",
						"https://www.youtube.com/watch?v=56eIZKyhM6c",
						"https://www.youtube.com/watch?v=bxF-pQSzSUM",
						"https://www.youtube.com/watch?v=XKfgdkcIUxw",
						"https://www.youtube.com/watch?v=56eIZKyhM6c",
						"https://www.youtube.com/watch?v=bxF-pQSzSUM",
						"https://www.youtube.com/watch?v=XKfgdkcIUxw",
						"https://www.youtube.com/watch?v=56eIZKyhM6c",
						"https://www.youtube.com/watch?v=bxF-pQSzSUM",
						"https://www.youtube.com/watch?v=XKfgdkcIUxw",
						"https://www.youtube.com/watch?v=56eIZKyhM6c",
						"https://www.youtube.com/watch?v=bxF-pQSzSUM",
						"https://www.youtube.com/watch?v=XKfgdkcIUxw",
						"https://www.youtube.com/watch?v=56eIZKyhM6c",
						"https://www.youtube.com/watch?v=bxF-pQSzSUM",
						"https://www.youtube.com/watch?v=XKfgdkcIUxw",
						"https://www.youtube.com/watch?v=56eIZKyhM6c",
						"https://www.youtube.com/watch?v=bxF-pQSzSUM",
						"https://www.youtube.com/watch?v=XKfgdkcIUxw",
						"https://www.youtube.com/watch?v=56eIZKyhM6c",
						"https://www.youtube.com/watch?v=bxF-pQSzSUM",
						"https://www.youtube.com/watch?v=XKfgdkcIUxw",
						"https://www.youtube.com/watch?v=56eIZKyhM6c",
						"https://www.youtube.com/watch?v=bxF-pQSzSUM",
						"https://www.youtube.com/watch?v=XKfgdkcIUxw",
						"https://www.youtube.com/watch?v=56eIZKyhM6c",
						"https://www.youtube.com/watch?v=bxF-pQSzSUM",
						"https://www.youtube.com/watch?v=XKfgdkcIUxw",
						"https://www.youtube.com/watch?v=56eIZKyhM6c",
				};
				List<Video> videoList = Arrays.stream(url).map(Video::new).collect(Collectors.toList());
				JFrame f = new JFrame();
				HomePanel h = new HomePanel(f, AppVideo.getInstance().getCurrentVideos());
//				HomePanel h = new HomePanel(f,videoList);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setContentPane(h);
				f.setBounds(0, 0, 800, 600);
				f.setVisible(true);
				String[] urlX = {
						"https://www.youtube.com/watch?v=bxF-pQSzSUM",
						"https://www.youtube.com/watch?v=56eIZKyhM6c",
				};
				List<Video> videoListX = Arrays.stream(urlX).map(Video::new).collect(Collectors.toList());
//				h.updateVideoList(videoListX);
			} catch (Exception e) {
				throw e;
			}
		});
	}
}

	
