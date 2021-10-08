package controller;

import dao.DAOUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tds.driver.FactoriaServicioPersistencia;

import static org.junit.jupiter.api.Assertions.*;

class AppVideoTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isUserRegistered() {
    }

    @Test
    void login() {
    }

    @Test
    void registerUser() {
        AppVideo.getInstance().registerUser("___testUser","___TDS","test@example.com","___testUserName1","testpassword","2020-10-10");

    }
}
