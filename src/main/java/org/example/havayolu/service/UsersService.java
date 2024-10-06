package org.example.havayolu.service;

import java.util.Calendar;

import org.example.havayolu.dto.CreateUserDto;
import org.example.havayolu.dto.UserDto;
import org.example.havayolu.entity.Users;
import org.example.havayolu.entity.enums.Roles;
import org.example.havayolu.exception.WrongPassword;
import org.example.havayolu.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users createUser(CreateUserDto createUserDto){
        Users users = new Users(
                createUserDto.getName(),
                createUserDto.getLastname(),
                createUserDto.getTc(),
                createUserDto.getNumber(),
                createUserDto.getPassword()
        );
        return usersRepository.save(users);
    }

    public UserDto loginUser(String tc, String password){
        Users users = usersRepository.findByTc(tc).orElseThrow(() -> new RuntimeException("User not found"));
        if (users.getPassword().equals(password)){
            UserDto userDto = new UserDto();
            userDto.setId(users.getId());
            userDto.setName(users.getName());
            userDto.setLastname(users.getLastName());
            userDto.setTc(users.getTc());
            userDto.setNumber(users.getNumber());
            userDto.setRoles(users.getRoles());
            return userDto;
        } else {
            throw new WrongPassword("Wrong password");
        }
    }

    
    public void updateUserRole(int userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(user.getRegisterDate());
        int registrationYear = cal.get(Calendar.YEAR);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int yearsRegistered = currentYear - registrationYear;

        Roles newRole;
        if (yearsRegistered >= 10 && user.getBilets().size()+1 >= (yearsRegistered * 4)) {
            newRole = Roles.VIP;
        } else if (yearsRegistered >= 5 && user.getBilets().size()+1 >= (yearsRegistered * 1)) {
            newRole = Roles.DAIMI;
        } else {
            newRole = Roles.NORMAL;
        }

        user.setRoles(newRole);
        usersRepository.save(user);
    }


}

