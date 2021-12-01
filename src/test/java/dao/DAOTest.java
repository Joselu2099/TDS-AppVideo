package dao;

import controller.AppVideo;
import model.MinorsFilter;
import model.User;
import org.junit.jupiter.api.Test;

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
            user2.setFilter(new MinorsFilter());
            daoUser.create(user2);
            assertNotEquals(daoUser.get(user1.getId()), daoUser.get(user2.getId()), "userNotEquals");
            assertTrue(daoUser.delete(user1), "deleteUser");
            assertTrue(daoUser.delete(user2), "deleteUser");
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
            User user2 = daoUser.get(user1.getId());
            user2.setFilter(new MinorsFilter());
            daoUser.create(user2);
            assertNotEquals(daoUser.get(user1.getId()), daoUser.get(user2.getId()), "userNotEquals");
            assertTrue(daoUser.delete(user1), "deleteUser");
            assertTrue(daoUser.delete(user2), "deleteUser");
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createVideo(){

    }

    @Test
    void updateVideo(){

    }
}
