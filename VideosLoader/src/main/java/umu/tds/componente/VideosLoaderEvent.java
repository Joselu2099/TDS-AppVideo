package umu.tds.componente;

import java.util.EventObject;
import java.util.List;

public class VideosLoaderEvent extends EventObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Propiedad
	private List<Video> oldValue, newValue;
	
	public VideosLoaderEvent(Object arg0, List<Video> newValue, List<Video> oldValue) {
		super(arg0);
		this.newValue = newValue;
		this.oldValue = oldValue;
	}
	
	public List<Video> getNewValue() {
		return newValue;
	}
	
	public List<Video> getOldValue() {
		return oldValue;
	}
}
