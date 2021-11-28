package gui;

import com.formdev.flatlaf.IntelliJTheme;
import gui.VideoPreview.VideoPreviewListPanel;
import launcher.Launcher;
import model.Label;
import model.Video;
import javax.swing.*;

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
	private List<Video> repoList;
	private List<Video> currentList;
	JFrame parent;
	VideoPreviewListPanel vidPanel;

	/**
	 * Create the panel.
	 */
	public HomePanel(JFrame parent ,List<Video> list ) {
		repoList = list;
		// Necesitamos el JFrame para ocultar la ventana cuando lanzamos
		// el visualizador de video.
		this.parent = parent;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BoxLayout(searchPanel,BoxLayout.Y_AXIS));
		JPanel searchBoxPanel = new JPanel();
		Set<Label> labelSet = new TreeSet<>();
		searchPanel.add(searchBoxPanel);
		LabelEditorPanel labelManager = new LabelEditorPanel(labelSet,
				label -> {
					labelSet.add(label);
					filter(textField.getText(),labelSet);
				},
				label ->{
					labelSet.remove(label);
					filter(textField.getText(),labelSet);
				}
		);
		searchPanel.add(labelManager);
		add(searchPanel);

		textField = new JTextField();
		searchBoxPanel.add(textField);
		textField.setColumns(30);

		JButton btnSearchButton = new JButton("BUSCAR");
		btnSearchButton.addActionListener(l->filter(textField.getText(),labelSet));
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

		showVideoPreview(repoList);
		JScrollPane scrollPane = new JScrollPane(vidPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		add(scrollPane);
	}

	private void showVideoPreview(List<Video> videoList) {
		currentList = videoList;
		if (vidPanel == null){
			vidPanel = new VideoPreviewListPanel(videoList,vid->{
				VideoPlayerWindow player = new VideoPlayerWindow(vid);
				player.showPlayer(parent);
			});
		}else {
			vidPanel.setPrewviewList(videoList);
		}

	}
	
	public void filter(String text,Set<Label> labelSet){
		currentList = repoList.stream()
				.parallel()
				.filter(v-> v.getTitle().contains(text))
				.filter(v -> {
					if (!labelSet.isEmpty())
						return v.getLabels().stream().parallel().anyMatch(labelSet::contains);
					return true;
				}) // OR filter
				.collect(Collectors.toList());
		showVideoPreview(currentList);
	}

	public void updateVideoList(@NotNull List<Video> videoList){
		this.repoList = videoList;
		showVideoPreview(videoList);
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
				HomePanel h = new HomePanel(f,videoList);
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
				e.printStackTrace();
			}
		});
	}
}

	
