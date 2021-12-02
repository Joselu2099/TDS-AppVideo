package model;

public class NoFilter implements IFilter {

    @Override
    public boolean test(Video video) {
        return true;
    }
}
