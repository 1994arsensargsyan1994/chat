package am.vtc.chat.repo.impl;

import am.vtc.chat.model.User;

import java.util.Optional;

public class UserRepoSql implements UserRepo {
    @Override
    public Optional<User> exits(String mail) throws Exception {
        return Optional.empty();
    }
}
