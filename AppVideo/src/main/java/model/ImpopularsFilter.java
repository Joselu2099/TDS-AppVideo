package model;

public class ImpopularsFilter implements IFilter {

    @Override
    public boolean test(Video video,User user) {
        return video.getViews()>=5;
    }
}
