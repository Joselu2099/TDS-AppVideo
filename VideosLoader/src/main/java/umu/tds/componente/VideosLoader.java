package umu.tds.componente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class VideosLoader implements Serializable {

	private static VideosLoader instance;

	//Propiedades
	private List<Consumer<Videos>> videosListeners;
	
	private VideosLoader() {
		this.videosListeners = new ArrayList<>();
	}

	public static VideosLoader getInstance(){
		if (instance == null)
			instance = new VideosLoader();
		return instance;
	}

	public void loadVideoFromXML(String file){
		Videos loadedVideos = MapperVideosXMLtoJava.cargarVideos(file);
		if (loadedVideos == null)
			return;
		notifiedLoadedVideos(loadedVideos);

	}

	private void notifiedLoadedVideos(Videos videos) {
		videosListeners.stream().forEach(videosConsumer -> videosConsumer.accept(videos));
	}

	public synchronized void subscribeNewVideoLoaded(Consumer<Videos> listener) {
		if (listener!=null)
			this.videosListeners.add(listener);
	}

	public synchronized void unsubscribeNewVideoLoaded(Consumer<Videos> listener) {
		this.videosListeners.remove(listener);
	}
}
