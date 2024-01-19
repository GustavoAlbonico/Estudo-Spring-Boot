package com.gm2.pdv.service;

import com.gm2.pdv.dto.UserDTO;
import com.gm2.pdv.dto.UserResponseDTO;
import com.gm2.pdv.entity.User;
import com.gm2.pdv.exceptions.NoItemException;
import com.gm2.pdv.repository.UserRepository;
import com.gm2.pdv.security.SecurityConfig;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private ModelMapper mapper = new ModelMapper();

    public List<UserResponseDTO> findAll(){
        return userRepository.findAll().stream().map(user ->
                new UserResponseDTO(user.getId(), user.getNome(),user.getUsername()
                        ,user.isEnable())).collect(Collectors.toList());
    }

    public UserDTO save(UserDTO user){

        user.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));
        User userToSave = mapper.map(user,User.class);

        userRepository.save(userToSave);
        return new UserDTO(userToSave.getId(), userToSave.getNome(),userToSave.getUsername(),
                userToSave.getPassword(), userToSave.isEnable());
    }

    public UserResponseDTO findById(long id){
        Optional<User> optional =  userRepository.findById(id);

        if(!optional.isPresent()){
            throw new NoItemException("Usuario não encontrado");
        }

        User user =  optional.get();
        return new UserResponseDTO(user.getId(), user.getNome(),user.getUsername(), user.isEnable());
    }

    public UserDTO update(UserDTO user){
        user.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));
        User userToSave =  mapper.map(user,User.class);

        Optional<User> userToEdit = userRepository.findById(userToSave.getId());

        if(!userToEdit.isPresent()){
            throw new NoItemException("Usuario não encontrado");
        }

        userRepository.save(userToSave);
        return new UserDTO(userToSave.getId(), userToSave.getNome(),user.getUsername(),
                user.getPassword(), userToSave.isEnable());
    }

    public void deleteById(long id){
        userRepository.deleteById(id);
    }

    public User getByUsername(String username){
        userRepository.findUserByUsername(username);
    }

}
