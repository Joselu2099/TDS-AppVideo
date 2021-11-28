package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MinorsFilter implements IFilter {

    @Override
    public boolean test(Video video) {
        return !video.getLabels().contains(Label.ADULTOS);
    }
}
