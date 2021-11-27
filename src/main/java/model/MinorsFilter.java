package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MinorsFilter implements IFilter {

    @Override
    public void filtrarVideos() {
        List<Video> videos = VideoRepository.getInstance().getVideos();
        List<Video> filteredVideos = videos.stream()
                .filter(v -> !v.getLabels().contains(Label.ADULTOS))
                .collect(Collectors.toList());

        VideoRepository.getInstance().setFilteredVideoList(filteredVideos);
    }

}
