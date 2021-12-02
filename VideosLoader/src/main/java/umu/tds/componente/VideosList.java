package umu.tds.componente;

import java.util.ArrayList;
import java.util.List;

public class VideosList {

	private List<VideosListListener> videosListeners;
	//Propiedades
	List<Video> videos; //propiedad ligada
	
	public VideosList() {
		Videos video = MapperVideosXMLtoJava.cargarVideos("xml/canciones.xml");
		this.videos = video.getVideo();
		this.videosListeners = new ArrayList<VideosListListener>();
	}

	public List<Video> getVideos() {
		return new ArrayList<Video>(videos);
	}

	public void setVideos(List<Video> canciones) {
		List<Video> oldValue = this.videos;
		if(oldValue != canciones) {
			this.videos = canciones;
			VideosListEvent evento = new VideosListEvent(this, videos, oldValue);
			notifiedChargedVideos(evento);
		}
		
	}
	
	private void notifiedChargedVideos(VideosListEvent event) {
		List<VideosListListener> copy;
		synchronized (this.videosListeners) {
			copy = new ArrayList<VideosListListener>(this.videosListeners);
		}
		
		for(VideosListListener canciones : copy) {
			canciones.notifiedChargedVideos(event);
		}
		
	}

	public synchronized void addVideosListListener(VideosListListener listener) {
		this.videosListeners.add(listener);
	}

	public synchronized void removeVideosListListener(VideosListListener listener) {
		this.videosListeners.remove(listener);
	}
}
