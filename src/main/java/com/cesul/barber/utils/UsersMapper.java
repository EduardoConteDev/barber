package com.cesul.barber.utils;

import com.cesul.barber.dto.UserCreationDTO;
import com.cesul.barber.dto.UserDTO;
import com.cesul.barber.entity.Users;

public class UsersMapper {

    public static Users userCreationDTOtoUsers(UserCreationDTO dto){
        return new Users(dto.name(), dto.email(), dto.avatarUrl());
    }

    public static UserCreationDTO usersToUserCreationDTO(Users user){
        return new UserCreationDTO(user.getName(), user.getEmail(), user.getAvatarUrl());
    }

    public static UserDTO usersToUserDTO(Users user){
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getAvatarUrl());
    }
}
