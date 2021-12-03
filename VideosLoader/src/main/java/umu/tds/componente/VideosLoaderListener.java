package umu.tds.componente;

import java.util.EventListener;

public interface VideosLoaderListener extends EventListener{

	void getLoadedVideos(VideosLoaderEvent event);
}
