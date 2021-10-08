package model;

import java.util.ArrayList;

public class Video {
	
	private int id;
	private String title;
	private String url;
	private ArrayList<Label> labels;
	
	public Video(String title, String url) {
		this.id = 0;
		this.title = title;
		this.url = url;
		this.labels = new ArrayList<>();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ArrayList<Label> getLabels() {
		return new ArrayList<>(labels);
	}

	public void setLabels(ArrayList<Label> labels) {
		this.labels = labels;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Video)) return false;

		Video video = (Video) o;

		return getId() == video.getId();
	}

	@Override
	public int hashCode() {
		return getId();
	}
}
