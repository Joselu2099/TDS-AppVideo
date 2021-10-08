package controller;

import dao.DAOUser;
import model.UserRepository;
import org.junit.jupiter.api.*;
import tds.driver.FactoriaServicioPersistencia;

import static org.junit.jupiter.api.Assertions.*;

class AppVideoTest {

    @Test
    void isUserRegistered() {
    }

    @Test
    void login() {
    }

    @Test
    void registerUser() {
        AppVideo.getInstance().removeUser("___testUserName1");

        assertTrue(AppVideo.getInstance().registerUser("___testUser","___TDS","test@example.com","___testUserName1","testpassword","2020-10-10"));
        assertTrue(AppVideo.getInstance().isUserRegistered("___testUserName1"),"isRegistered" );
        assertTrue(AppVideo.getInstance().login("___testUserName1","testpassword"),"login");
        assertFalse(AppVideo.getInstance().registerUser("___testUser","___TDS","test@example.com","___testUserName1","testpassword","2020-10-10"));
        assertTrue(AppVideo.getInstance().removeUser("___testUserName1"),"remove");

    }
}
