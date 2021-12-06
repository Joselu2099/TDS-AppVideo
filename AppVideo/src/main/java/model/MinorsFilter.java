package model;

public class MinorsFilter implements IFilter {

    @Override
    public boolean test(Video video,User user) {
        return !video.getLabels().contains(Label.ADULTOS);
    }
}
