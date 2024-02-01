package com.danielks.headspaceprojectweb.HsWeb.services;

import com.danielks.headspaceprojectweb.HsWeb.entities.User;
import com.danielks.headspaceprojectweb.HsWeb.exceptions.user.UserNotFoundException;
import com.danielks.headspaceprojectweb.HsWeb.models.UserDTO;
import com.danielks.headspaceprojectweb.HsWeb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUserName(),
                user.getUserLogin(),
                user.getPassHash(),
                user.getPhone(),
                user.getAddress(),
                user.getEmail(),
                user.getAge(),
                user.getUser_role(),
                user.getCreate_time()
        );
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

    public UserDTO updateUser(UUID id, UserDTO updatedUserDTO) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User userToUpdate = optionalUser.get();

            userToUpdate.setUserName(updatedUserDTO.userName());
            userToUpdate.setUserLogin(updatedUserDTO.userLogin());
            userToUpdate.setPassHash(updatedUserDTO.passHash());
            userToUpdate.setPhone(updatedUserDTO.phone());
            userToUpdate.setAddress(updatedUserDTO.address());
            userToUpdate.setEmail(updatedUserDTO.email());
            userToUpdate.setAge(updatedUserDTO.age());
            userToUpdate.setUser_role(updatedUserDTO.user_role());

            User updatedUser = userRepository.save(userToUpdate);
            return convertToDTO(updatedUser);
        } else {
            throw new UserNotFoundException(id);
        }
    }
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
