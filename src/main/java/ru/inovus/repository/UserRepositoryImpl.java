package ru.inovus.repository;

import ru.inovus.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Vlad.M on 27.10.2016.
 */
public class UserRepositoryImpl implements UserRepository {
    public UserRepositoryImpl(){
        UserRepositoryImpl.createTable();
    }
    public void addUser(User user) throws SQLException {
        StringBuilder s = new StringBuilder("insert into PUBLIC.myusers(username,password)")
                .append(" values (?,?);");
        System.out.println(user.getName()+user.getPassword());

        PreparedStatement p = Database.getConnection().prepareStatement(s.toString());
        p.setString(1,user.getName());
        p.setString(2,user.getPassword());
        p.executeUpdate();
    }

    public User getUserByName(String name) throws SQLException {
        StringBuilder s = new StringBuilder("select * from PUBLIC.myusers where username = ?;");
        PreparedStatement p = Database.getConnection().prepareStatement(s.toString());
        p.setString(1, name);
        ResultSet set = p.executeQuery();
        if (set.next()){
            String password = set.getString(2);
            return new User(name,password);
        }
        return null;
    }

    public boolean contains(User user) throws SQLException {
        if (getUserByName(user.getName()) != null) return true;
        return false;
    }
    public static void createTable(){
        try {
            PreparedStatement p1 = Database.getConnection().prepareStatement(
                    "DROP TABLE PUBLIC.myusers IF EXISTS ; ");
            PreparedStatement p2 = Database.getConnection().prepareStatement(
                            "CREATE TABLE PUBLIC.myusers"+
                            "( username VARCHAR(30), password VARCHAR(30));"
            );
            p1.execute();
            p2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
