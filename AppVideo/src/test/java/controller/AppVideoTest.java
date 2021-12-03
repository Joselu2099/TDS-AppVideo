package controller;

import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppVideoTest {

    @Test
    void isUserRegistered() {
        AppVideo.getInstance().removeUser("___testUserName2");
        User user = new User("___testUser", "___TDS", "test@example.com", "___testUserName2", "testpassword", "2020-10-10");
        assertFalse(AppVideo.getInstance().isUserRegistered(user.getUsername()),"isUserRegistered");
        assertTrue(AppVideo.getInstance().registerUser(user));
        assertTrue(AppVideo.getInstance().isUserRegistered("___testUserName2"), "isRegistered");
        assertTrue(AppVideo.getInstance().removeUser("___testUserName2"));
    }

    @Test
    void login() {
        AppVideo.getInstance().removeUser("___testUserName2");
        assertFalse(AppVideo.getInstance().login("___testUserName2", "testpassword"));
        assertTrue(AppVideo.getInstance().registerUser("___testUser", "___TDS", "test@example.com", "___testUserName2", "testpassword", "2020-10-10"));
        assertTrue(AppVideo.getInstance().login("___testUserName2", "testpassword"), "login");
        assertFalse(AppVideo.getInstance().login("___testUserName2", "badpassword"));
        assertTrue(AppVideo.getInstance().removeUser("___testUserName2"));

    }

    @Test
    void setPremium() {
        AppVideo.getInstance().removeUser("___testUserName2");
        assertTrue(AppVideo.getInstance().registerUser("___testUser", "___TDS", "test@example.com", "___testUserName2", "testpassword", "2020-10-10"));
        assertTrue(AppVideo.getInstance().login("___testUserName2", "testpassword"), "login");
        assertNotEquals("si", AppVideo.getInstance().getCurrentUser().getPremium());
        AppVideo.getInstance().becomePremium();
        assertEquals("si", AppVideo.getInstance().getCurrentUser().getPremium());


        assertTrue(AppVideo.getInstance().removeUser("___testUserName2"), "remove");
    }

    @Test
    void registerUser() {
        AppVideo.getInstance().removeUser("___testUserName1");

        assertTrue(AppVideo.getInstance().registerUser("___testUser", "___TDS", "test@example.com", "___testUserName1", "testpassword", "2020-10-10"));
        assertTrue(AppVideo.getInstance().isUserRegistered("___testUserName1"), "isRegistered");
        assertTrue(AppVideo.getInstance().login("___testUserName1", "testpassword"), "login");
        assertFalse(AppVideo.getInstance().registerUser("___testUser", "___TDS", "test@example.com", "___testUserName1", "testpassword", "2020-10-10"));


        assertTrue(AppVideo.getInstance().removeUser("___testUserName1"), "remove");

    }

    @Test
    void setNightMode(){
        AppVideo.getInstance().removeUser("___testUserName1");

        assertTrue(AppVideo.getInstance().registerUser("___testUser", "___TDS", "test@example.com", "___testUserName1", "testpassword", "2020-10-10"));
        assertTrue(AppVideo.getInstance().isUserRegistered("___testUserName1"), "isRegistered");
        assertTrue(AppVideo.getInstance().login("___testUserName1", "testpassword"), "login");
        AppVideo.getInstance().setNightMode(true);
        assertTrue(AppVideo.getInstance().getCurrentUser().isNightMode());

        AppVideo.getInstance().setNightMode(false);
        assertFalse(AppVideo.getInstance().getCurrentUser().isNightMode());

        assertTrue(AppVideo.getInstance().removeUser("___testUserName1"), "remove");
    }

    @Test
    void applyFilter(){
        AppVideo.getInstance().removeUser("___testUserName1");

        assertTrue(AppVideo.getInstance().registerUser("___testUser", "___TDS", "test@example.com", "___testUserName1", "testpassword", "2020-10-10"));
        assertTrue(AppVideo.getInstance().isUserRegistered("___testUserName1"), "isRegistered");
        assertTrue(AppVideo.getInstance().login("___testUserName1", "testpassword"), "login");

        assertInstanceOf(NoFilter.class, AppVideo.getInstance().getCurrentUser().getFilter(),"NoFilter1");
        AppVideo.getInstance().applyFilter(new NoFilter());
        assertInstanceOf(NoFilter.class, AppVideo.getInstance().getCurrentUser().getFilter(),"NoFilter2");
        AppVideo.getInstance().applyFilter(new MinorsFilter());
        assertInstanceOf(MinorsFilter.class, AppVideo.getInstance().getCurrentUser().getFilter(),"MinorsFilter");
        AppVideo.getInstance().applyFilter(new MyListsFilter());
        assertInstanceOf(MyListsFilter.class, AppVideo.getInstance().getCurrentUser().getFilter(),"MyListFilter");
        AppVideo.getInstance().applyFilter(new ImpopularsFilter());
        assertInstanceOf(ImpopularsFilter.class, AppVideo.getInstance().getCurrentUser().getFilter(),"ImpopularsFilter");


        assertTrue(AppVideo.getInstance().removeUser("___testUserName1"), "remove");
    }

    @Test
    void persistVideo(){
        AppVideo.getInstance().removeVideo("__testURL");

        assertFalse(AppVideo.getInstance().isVideoPersisted("__testURL"),"isVideoPersisted");
        AppVideo.getInstance().persistVideo(new Video("___testTitulo", "__testURL"));
        assertTrue(AppVideo.getInstance().isVideoPersisted("__testURL"),"isVideoPersisted");

        assertTrue(AppVideo.getInstance().removeVideo("__testURL"),"removePersistedVideo");
    }

    @Test
    void createPlaylist(){
        AppVideo.getInstance().createPlaylist("__testTitle");
        assertTrue(AppVideo.getInstance().isPlaylistRegistered("__testTitle"));
        AppVideo.getInstance().removePlaylist("__testTitle");
    }

    @Test
    void removePlaylist(){
        AppVideo.getInstance().removePlaylist("__testTitle");
        assertFalse(AppVideo.getInstance().isPlaylistRegistered("__testTitle"));
        AppVideo.getInstance().createPlaylist("__testTitle");
        assertTrue(AppVideo.getInstance().isPlaylistRegistered("__testTitle"));
        AppVideo.getInstance().removePlaylist("__testTitle");
        assertFalse(AppVideo.getInstance().isPlaylistRegistered("__testTitle"));
    }
}
