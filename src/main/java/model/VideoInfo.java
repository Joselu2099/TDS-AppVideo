package model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VideoInfo {

    public final String title;
    public final String author;
    public static final String YT_QUERY = "https://www.youtube.com/oembed?url=youtube.com/watch?v=%s&format=json";
    public static final Pattern YTID_PATTERN = Pattern.compile("^https?://(?:www\\.)?youtube.com/watch\\?v=(.{11})");

    public VideoInfo(String yturl) throws IOException {
        Matcher matcher = YTID_PATTERN.matcher(yturl);
        if (!matcher.find()){
            throw new InvalidParameterException("Invalid youtube url: "+yturl);
        }
        URL url = new URL(String.format(YT_QUERY, matcher.group(1))) ;
        InputStreamReader reader = new InputStreamReader(url.openStream());
        JsonElement jsonElement = JsonParser.parseReader(reader);
        JsonObject jsonObject = jsonElement.getAsJsonObject();


        this.title = jsonObject.get("title").getAsString();
        this.author = jsonObject.get("author_name").getAsString();
    }
}
