package am.vtc.chat.repo.impl;

import am.vtc.chat.model.User;

import java.util.Optional;

public interface UserRepo {
    Optional<User> exits(String mail) throws  Exception;
}
