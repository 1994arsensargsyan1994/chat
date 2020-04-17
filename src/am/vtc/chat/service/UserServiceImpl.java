package am.vtc.chat.service;

import am.vtc.chat.model.User;
import am.vtc.chat.repo.impl.UserRepo;
import am.vtc.chat.repo.impl.UserRepoSql;

public class UserServiceImpl  implements  UserService{
    private  final UserRepo userRepo;

    public UserServiceImpl() {
        this.userRepo = new UserRepoSql();
    }


    @Override
    public boolean userExist(String email) throws Exception {
        return this.userRepo.exits(email).isPresent();
    }

    @Override
    public void save(User user) {

    }
}
