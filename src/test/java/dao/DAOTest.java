package dao;

import controller.AppVideo;
import model.*;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class DAOTest {

    @Test
    void createDAOFactory() {
        try {
            DAOFactory factory = DAOFactory.getInstance();
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createUser(){
        User user1 = new User("___testUser", "___TDS", "test@example.com", "___testUserName1", "testpassword", "2020-10-10");
        try {
            DAOFactory factory = DAOFactory.getInstance();
            DAOUser daoUser = factory.getDAOUser();
            assertFalse(daoUser.delete(user1), "notDeleteUser");

            daoUser.create(user1);
            assertNotNull(daoUser.get(user1.getId()), "isUserRegistered");
            User user2 = daoUser.get(user1.getId());
            user2.setUsername("___testUserName2");
            user2.setFilter(new MinorsFilter());
            daoUser.create(user2);
            assertNotEquals(daoUser.get(user1.getId()), daoUser.get(user2.getId()), "userNotEquals");
            assertTrue(daoUser.delete(user1), "deleteUser1");
            assertTrue(daoUser.delete(user2), "deleteUser2");
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateUser(){
        User user1 = new User("___testUser", "___TDS", "test@example.com", "___testUserName1", "testpassword", "2020-10-10");

        try {
            DAOFactory factory = DAOFactory.getInstance();
            DAOUser daoUser = factory.getDAOUser();
            assertFalse(daoUser.delete(user1), "notDeleteUser");

            daoUser.create(user1);
            assertNotNull(daoUser.get(user1.getId()), "isUserRegistered");
            user1.setMail("otherTest@example.com");
            user1.setFilter(new MinorsFilter());
            daoUser.updateProfile(user1);
            assertEquals(daoUser.get(user1.getId()).getMail(), user1.getMail(), "modifiedMail");
            assertEquals(daoUser.get(user1.getId()).getFilter().getClass(), user1.getFilter().getClass(), "modifiedFilter");
            assertTrue(daoUser.delete(user1), "deleteUser");
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createVideo(){
        Video video1 = new Video("___testTitle","___testURL1");

        try {
            DAOFactory factory = DAOFactory.getInstance();
            DAOVideo daoVideo = factory.getDAOVideo();
            assertFalse(daoVideo.delete(video1), "notDeleteVideo");

            daoVideo.create(video1);
            assertNotNull(daoVideo.get(video1.getId()), "isVideoRegistered");
            Video video2 = new Video("__testTitle2","___testURL2");
            //Video video2 = daoVideo.get(video1.getId());
            //video2.setUrl("___testURL2");
            //video2.setViews(2);
            daoVideo.create(video2);
            assertNotEquals(daoVideo.get(video1.getId()), daoVideo.get(video2.getId()), "videoNotEquals");
            assertTrue(daoVideo.delete(video1), "deleteVideo1");
            assertTrue(daoVideo.delete(video2), "deleteVideo2");
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateVideo(){
        Video video1 = new Video("___testTitle","___testURL");

        try {
            DAOFactory factory = DAOFactory.getInstance();
            DAOVideo daoVideo = factory.getDAOVideo();
            assertFalse(daoVideo.delete(video1), "notDeleteVideo");

            daoVideo.create(video1);
            assertNotNull(daoVideo.get(video1.getId()), "isVideoRegistered");
            video1.setViews(5);
            HashSet<Label> labels = new HashSet<Label>();
            labels.add(Label.ADULTOS);
            video1.setLabels(labels);
            daoVideo.updateProfile(video1);
            assertEquals(daoVideo.get(video1.getId()).getViews(), video1.getViews(), "modifiedViews");
            assertEquals(daoVideo.get(video1.getId()).getLabels(), video1.getLabels(), "modifiedLabels");
            assertTrue(daoVideo.delete(video1), "deleteVideo1");
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
