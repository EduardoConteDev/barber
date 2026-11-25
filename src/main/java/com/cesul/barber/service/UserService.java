package com.cesul.barber.service;

import com.cesul.barber.dto.UserCreationDTO;
import com.cesul.barber.dto.UserDTO;
import com.cesul.barber.dto.UserUpdateDTO;
import com.cesul.barber.entity.Users;
import com.cesul.barber.repository.UserRepository;
import com.cesul.barber.utils.UsersMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public UserDTO create(UserCreationDTO user){
        return UsersMapper.usersToUserDTO(
                repository.save(UsersMapper.userCreationDTOtoUsers(user))
        );
    }

    public List<UserDTO> findAll(){
        return repository.findAll().stream().map(UsersMapper::usersToUserDTO).toList();
    }

    public Optional<UserDTO> findById(UUID id){

        Optional<Users> users = repository.findById(id);

        return users.map(UsersMapper::usersToUserDTO);
    }

    public Optional<UserDTO> update(UUID id, UserUpdateDTO updatedUser){

        Optional<Users> user = repository.findById(id);
        if (user.isEmpty()) {
            return Optional.empty();
        }

        Optional<Users> userUpdated = user.map(users -> {
            users.setName(updatedUser.name());
            users.setEmail(updatedUser.email());
            users.setAvatarUrl(updatedUser.avatarUrl());
            return users;
        });
        return Optional.of(UsersMapper.usersToUserDTO(repository.save(userUpdated.get())));
    }

    public Optional<UserDTO> updateAvatar(UUID id, Map<String, Object> request){

        Optional<Users> userOptional = repository.findById(id);

        if (userOptional.isEmpty()){
            return Optional.empty();
        }

        Users user = userOptional.get();

        if (request.containsKey("avatarUrl")) {
            user.setAvatarUrl((String) request.get("avatarUrl"));
        }

        return Optional.of(UsersMapper.usersToUserDTO(repository.save(user)));
    }

    public boolean delete(UUID id){
       try{
           repository.deleteById(id);
           return true;
       } catch (IllegalArgumentException e){
           return false;
       }
    }

    public boolean clear(){
        repository.deleteAll();
        return true;
    }
}
