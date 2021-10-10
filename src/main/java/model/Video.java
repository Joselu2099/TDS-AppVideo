package model;

import java.util.ArrayList;
import java.util.List;

public class Video {
	
	private int id;
	private String title;
	private String url;
	private List<Label> labels;
	
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

	public List<Label> getLabels() {
		return new ArrayList<>(labels);
	}

	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Video)) return false;

		Video video = (Video) o;

		if (getId() != video.getId()) return false;
		if (getTitle() != null ? !getTitle().equals(video.getTitle()) : video.getTitle() != null) return false;
		if (getUrl() != null ? !getUrl().equals(video.getUrl()) : video.getUrl() != null) return false;
		return getLabels() != null ? getLabels().equals(video.getLabels()) : video.getLabels() == null;
	}

	@Override
	public int hashCode() {
		int result = getId();
		result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
		result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
		result = 31 * result + (getLabels() != null ? getLabels().hashCode() : 0);
		return result;
	}
}
