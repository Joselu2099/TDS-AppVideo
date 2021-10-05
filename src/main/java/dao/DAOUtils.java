package dao;

import model.IFilter;
import model.Playlist;
import model.Video;
import model.VideoRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DAOUtils {
    private static Integer safeValueOf(String s){
        try{
            return Integer.valueOf(s);
        }catch (NumberFormatException e){
        }
        return null;
    }

    public static List<Integer> stringToList(String union) {
        return union != null ? Arrays.stream(union.split(";")).map(DAOUtils::safeValueOf).filter(Objects::nonNull).collect(Collectors.toList()) : new ArrayList<>();
    }

    public static String listToString(List<Integer> ids) {
        return ids != null ? ids.stream().map(i -> Integer.toString(i)).collect(Collectors.joining(";")) : "";
    }

    public static <K, V> Map<K, V> listsToMap(List<K> keys, List<V> values) {
        Iterator<K> keyIter = keys.iterator();
        Iterator<V> valIter = values.iterator();
        return IntStream.range(0, keys.size()).boxed()
                .collect(Collectors.toMap(_i -> keyIter.next(), _i -> valIter.next()));
    }

    public static List<Video> idsToVideos(List<Integer> idsVideos) {
        List<Video> videos = new ArrayList<>();
        for(Integer id: idsVideos) {
            videos.add(VideoRepository.getInstance().getVideo(id));
        }
        return videos;
    }

    public static List<Integer> videosToIds(List<Video> videos) {
        List<Integer> videosIds = new ArrayList<>();
        for(Video v: videos) {
            videosIds.add(v.getId());
        }
        return videosIds;
    }

    public static List<Integer> playlistsToIds(List<Playlist> playlists) {
        List<Integer> playlistsIds = new ArrayList<Integer>();
        for(Playlist p: playlists) {
            playlistsIds.add(p.getId());
        }
        return playlistsIds;
    }

    public static String FilterToString(IFilter filter) {
        if(filter==null) return "";
        return filter.getClass().getName();
    }

    public static IFilter stringToFilter(String filterString) {
        if(filterString==null) return null;
        try {
            Class<?> aClass = Class.forName(filterString);
            if (IFilter.class.isAssignableFrom(aClass)){
                return (IFilter) aClass.getConstructor().newInstance();
            }
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
//			e.printStackTrace();
        }
        return null;
    }
}
