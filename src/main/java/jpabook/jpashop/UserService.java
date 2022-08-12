package jpabook.jpashop;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user){
        if(user.getUsername().isEmpty()){
            return null;
        }

        return userRepository.save(user);
    }

    public User findUser(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }


}
