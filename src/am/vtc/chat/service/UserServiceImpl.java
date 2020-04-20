package am.vtc.chat.service;

import am.vtc.chat.exception.DatabaseException;
import am.vtc.chat.model.User;
import am.vtc.chat.repo.impl.UserRepo;
import am.vtc.chat.repo.impl.UserRepoSql;

import java.sql.SQLException;
import java.util.Optional;

public class UserServiceImpl  implements  UserService{
    private  final UserRepo userRepo;

    public UserServiceImpl() {
        this.userRepo = new UserRepoSql();
    }


    @Override
    public boolean userExist(String email) throws DatabaseException {
          try {
              return  userRepo.exits(email);

          }catch (SQLException e){
               throw new DatabaseException(e);
          }
    }

    @Override
    public void save(User user) throws DatabaseException {
        try {

            if (user.getId() > 0) {
//todo update
            } else {
this.userRepo.insert(user);
            }
        }catch (Exception e){
 throw  new DatabaseException(e);
        }
    }

    @Override
    public Optional<User> getUser(String emil,String password) throws DatabaseException {
        try {
            return this.userRepo.findByEmailAndPassword(emil,password);
        } catch (SQLException e) {
           throw  new DatabaseException(e);
        }
    }

}
