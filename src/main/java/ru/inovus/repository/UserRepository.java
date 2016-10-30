package ru.inovus.repository;

import ru.inovus.entities.User;

import java.sql.SQLException;

/**
 * Created by Vlad.M on 27.10.2016.
 */
public interface UserRepository {
    void addUser(User user) throws SQLException;
    User getUserByName(String name) throws SQLException;
    boolean contains (User user) throws SQLException;
}
