package am.vtc.chat.service;

import am.vtc.chat.model.User;

public interface UserService {
    boolean userExist(String email) throws Exception;
    void save(User user);
}
