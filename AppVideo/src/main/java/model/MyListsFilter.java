package model;

public class MyListsFilter implements IFilter {

    @Override
    public boolean test(Video video,User user) {
        return !user.getListOfPlaylist().stream().flatMap(p->p.getListOfVideos().stream()).anyMatch(video::equals);
    }
}
