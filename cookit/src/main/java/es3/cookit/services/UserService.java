package es3.cookit.services;

import java.util.Optional;
import es3.cookit.dto.UserDto;
import es3.cookit.entities.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

    public User listUserById(Long id) {
        return User.findById(id);
    }

    @Transactional
    public User logginUser(UserDto dto) {
        User user = new User();

        if (User.findByEmail(dto.getEmail()) != null) {
            Optional<User> userOptional = user.findByIdOptional(User.findByEmail(dto.getEmail()).id);
            user = userOptional.get();
        } else {
            user.setEmail(dto.getEmail());
            user.setName(dto.getName());
        }
        user.setLastLogin(new java.sql.Date(new java.util.Date().getTime()));
        user.persist();
        return user;
    }
    
    @Transactional
    public void loggoutUser(Long id) {
        User user = new User();

        Optional<User> userOptional = user.findByIdOptional(id);

        if (userOptional.isEmpty()) {
            throw new NullPointerException("User Not Found!");
        }

        user = userOptional.get();
        user.persist();

    }

    @Transactional
    public void updateUser(Long id, UserDto dto) {
        User user = new User();

        Optional<User> userOptional = user.findByIdOptional(id);

        if (userOptional.isEmpty()) {
            throw new NullPointerException("User Not Found!");
        }

        user = userOptional.get();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setLactoseIntolerant(dto.isLactoseIntolerant());
        user.setGlutenIntolerant(dto.isGlutenIntolerant());
        user.setOilseedsIntolerant(dto.isOilseedsIntolerant());
        user.setFavouritedRecipes(dto.getFavouritedRecipes());
        user.setPreparedRecipes(dto.getPreparedRecipes());
        user.persist();
    }

}
