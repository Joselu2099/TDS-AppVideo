package umu.tds.componente;

import java.util.EventObject;
import java.util.List;

public class VideosListEvent extends EventObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Propiedad
	private List<Video> oldValue, newValue;
	
	public VideosListEvent(Object arg0, List<Video> newValue, List<Video> oldValue) {
		super(arg0);
		this.newValue = newValue;
		this.oldValue = oldValue;
		// TODO Auto-generated constructor stub
	}
	
	public List<Video> getNewValue() {
		return newValue;
	}
	
	public List<Video> getOldValue() {
		return oldValue;
	}
}
