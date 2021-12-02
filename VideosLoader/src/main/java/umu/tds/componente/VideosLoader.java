package umu.tds.componente;

import java.util.ArrayList;
import java.util.List;

public class VideosLoader {

	//Propiedades
	private List<Video> videos; //propiedad ligada
	private List<VideosLoaderListener> videosListeners;
	
	public VideosLoader(String file) {
		Videos loadedVideos = MapperVideosXMLtoJava.cargarVideos(file);
		setVideos(loadedVideos.getVideo());
		this.videosListeners = new ArrayList<VideosLoaderListener>();
	}

	public List<Video> getVideos() {
		return new ArrayList<Video>(videos);
	}

	public void setVideos(List<Video> canciones) {
		List<Video> oldValue = this.videos;
		if(oldValue != canciones) {
			this.videos = canciones;
			VideosLoaderEvent evento = new VideosLoaderEvent(this, videos, oldValue);
			notifiedLoadedVideos(evento);
		}
		
	}
	
	private void notifiedLoadedVideos(VideosLoaderEvent event) {
		List<VideosLoaderListener> copy;
		synchronized (this.videosListeners) {
			copy = new ArrayList<VideosLoaderListener>(this.videosListeners);
		}
		
		for(VideosLoaderListener canciones : copy) {
			canciones.getLoadedVideos(event);
		}
		
	}

	public synchronized void addVideosLoaderListener(VideosLoaderListener listener) {
		this.videosListeners.add(listener);
	}

	public synchronized void removeVideosLoaderListener(VideosLoaderListener listener) {
		this.videosListeners.remove(listener);
	}
}
