package umu.tds.componente;

import java.util.EventListener;

public interface VideosListListener extends EventListener{

	void notifiedChargedVideos(VideosListEvent event);
}
