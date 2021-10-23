package controller;

import model.VideoInfo;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class VideoInfoTest {
    @Test
    void validateAPI(){
        try {
            VideoInfo rtve = new VideoInfo("https://www.youtube.com/watch?v=Y-waKrzqRK8&t");
            assertEquals(rtve.author,"RTVE Noticias");
            assertEquals(rtve.title,"AUMENTA la INCIDENCIA y los expertos ADVIERTEN: \"Los vacunados se pueden seguir contagiando\" | RTVE");

            VideoInfo apple = new VideoInfo("https://www.youtube.com/watch?v=XKfgdkcIUxw");
            assertEquals(apple.title,"Introducing iPhone 13 Pro | Apple");
            assertEquals(apple.author,"Apple");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}