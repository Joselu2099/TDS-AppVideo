package dao;

import beans.Entidad;
import beans.Propiedad;
import model.IFilter;
import model.Playlist;
import model.Video;
import model.VideoRepository;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DAOUtils {
    public static Integer safeValueOf(String s) {
        try {
            return Integer.valueOf(s);
        } catch (NumberFormatException | NullPointerException ignored) {
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
        return IntStream.range(0, keys.size()).boxed()
                .collect(Collectors.toMap(keys::get, values::get));
    }

    public static List<Video> idsToVideos(List<Integer> idsVideos) {
        return idsVideos == null ? new ArrayList<>() : idsVideos.stream().map(VideoRepository.getInstance()::getVideo).collect(Collectors.toList());
    }

    public static List<Integer> videosToIds(List<Video> videos) {
        return videos == null ? new ArrayList<>() : videos.stream().map(Video::getId).collect(Collectors.toList());
    }

    public static List<Integer> playlistsToIds(List<Playlist> playlists) {
        return playlists == null ? new ArrayList<>() : playlists.stream().map(Playlist::getId).collect(Collectors.toList());
    }

    public static String filterToString(IFilter filter) {
        return filter == null ? "" : filter.getClass().getName();
    }

    public static IFilter stringToFilter(String filterString) {
        if (filterString == null || filterString.equals("")) return null;
        try {
            Class<?> filterClass = Class.forName(filterString);
            if (IFilter.class.isAssignableFrom(filterClass)) {
                return (IFilter) filterClass.getConstructor().newInstance();
            }
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            System.err.println("Filter class not found: <" + filterString + ">");
//			e.printStackTrace();
        }
        return null;
    }

    public static Map<Integer, Playlist> idsToPlaylists(List<Integer> idsPlaylists) {
        return idsPlaylists == null ? new HashMap<>() : idsPlaylists.stream().collect(Collectors.toMap(Integer::intValue, AppVideoDAOPlaylist.getInstance()::get));
    }

    public static void modifyEntityProperty(Entidad entidad, String propertyName, String value) {
        if(entidad==null) return;
        Propiedad properties = entidad.getPropiedades().stream()
                .filter(property -> property.getNombre().equals(propertyName))
                .findAny().orElse(null);
        if (properties == null)
            return;
        properties.setValor(value);
    }

    public static List<String> splitString(String s) {
        return s == null ? new ArrayList<>() : Arrays.stream(s.split(";")).collect(Collectors.toList());
    }

    public static String joinString(List<String> list) {
        return list == null ? "" : String.join(";", list);
    }


}
