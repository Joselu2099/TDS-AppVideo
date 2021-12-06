package model;

public class LongNameFilter implements IFilter{
    @Override
    public boolean test(Video video,User user) {
        return !(video.getTitle().length() > 16);
    }
}
