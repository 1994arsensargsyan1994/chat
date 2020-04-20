package am.vtc.chat.repo.impl;

import java.sql.*;

import am.vtc.chat.exception.DatabaseException;
import am.vtc.chat.model.User;
import am.vtc.chat.util.DatabaseConnectionFactory;

import java.sql.Connection;
import java.util.Optional;

public class UserRepoSql implements UserRepo {
    @Override
    public boolean exits(String email) throws SQLException {
        String query = "select count(*) from user_chat where email = ?";
        try (Connection connection = DatabaseConnectionFactory.getInstance().openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) == 1;
            }
        }
    }

    private static User toUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setSurname(resultSet.getString("email"));
        user.setSurname(resultSet.getString("password"));
        return user;
    }

    @Override
    public void insert(User user) throws SQLException {
        String query = "insert into user_chat(name,surname,email,password) values (?,?,?,?);";
        try (Connection connection = DatabaseConnectionFactory.getInstance().openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.execute();
        }
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) throws SQLException {
        String query = "select * from user_chat where email = ? and password = ?";
        try (Connection connection = DatabaseConnectionFactory.getInstance().openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
               if(resultSet.next()){
                   return Optional.of(UserRepoSql.toUser(resultSet));
               }
            }
        }
        return  Optional.empty();
    }
}