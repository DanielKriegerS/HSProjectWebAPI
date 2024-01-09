package com.danielks.headspaceprojectweb.HsWeb.services;

import com.danielks.headspaceprojectweb.HsWeb.entities.User;
import com.danielks.headspaceprojectweb.HsWeb.exceptions.user.UserNotFoundException;
import com.danielks.headspaceprojectweb.HsWeb.models.UserDTO;
import com.danielks.headspaceprojectweb.HsWeb.repositories.UserRepository;
import com.danielks.headspaceprojectweb.HsWeb.security.PassEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PassEncoder passEncoder;

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setPassHash(user.getPassHash());
        userDTO.setPhone(user.getPhone());
        userDTO.setAddress(user.getAddress());
        userDTO.setEmail(user.getEmail());
        userDTO.setAge(user.getAge());
        userDTO.setCreate_time(user.getCreate_time());
        userDTO.setUserLogin(user.getUserLogin());
        return userDTO;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            return optionalUser.map(this::convertToDTO);
        } else {
            throw new UserNotFoundException(id);
        }
    }
    public UserDTO createUser(UserDTO userDTO) {
        User user = createUserFromDTO(userDTO);
        user.setPassHash(passEncoder.encode(userDTO.getPassHash()));
        user.setCreate_time(LocalDateTime.now());

        user = (User) userRepository.save(user);
        return convertToDTO(user);
    }

    public User createUserFromDTO(UserDTO userDTO) {
        User user = new User();

        user.setUserName(userDTO.getUserName());
        user.setPassHash(userDTO.getPassHash());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getEmail());
        user.setAge(userDTO.getAge());
        user.setUserLogin(userDTO.getUserLogin());

        user.setUser_role(userDTO.getUser_role());

        return user;
    }


    public UserDTO updateUser(UUID id, UserDTO updatedUserDTO) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            existingUser.setUserName(updatedUserDTO.getUserName());
            if (updatedUserDTO.getPassHash() != null) {
                existingUser.setPassHash(passEncoder.encode(updatedUserDTO.getPassHash()));
            }

            existingUser.setPhone(updatedUserDTO.getPhone());
            existingUser.setAddress(updatedUserDTO.getAddress());
            existingUser.setEmail(updatedUserDTO.getEmail());
            existingUser.setAge(updatedUserDTO.getAge());
            existingUser.setUserLogin(updatedUserDTO.getUserLogin());

            existingUser = (User) userRepository.save(existingUser);
            return convertToDTO(existingUser);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
