package am.vtc.chat.service;

import am.vtc.chat.exception.DatabaseException;
import am.vtc.chat.model.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserService {
    boolean userExist(String email) throws DatabaseException;
    void save(User user) throws DatabaseException;
    Optional<User> getUser(String emil,String password) throws  DatabaseException;
}
