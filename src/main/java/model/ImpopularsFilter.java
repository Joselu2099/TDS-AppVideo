package model;

public class ImpopularsFilter implements IFilter {

    @Override
    public boolean test(Video video) {
        return false;
    }
}
