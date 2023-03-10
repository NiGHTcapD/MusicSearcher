package com.egl.music.musicsearcher.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService implements UserDetailsService {

    UsersRepository userRepository;
    AuthGroupRepo authGroupRepo;

    public UsersService(UsersRepository userRepository, AuthGroupRepo authGroupRepo) {
        this.userRepository = userRepository;
        this.authGroupRepo = authGroupRepo;
    }

    public List<Users> findAll() {

        return userRepository.findAll();
    }

    public Users findById(int id) {
        return userRepository.findById(id).get();
    }

    public boolean existsById(int id) {
        return userRepository.existsById(id);
    }

    public long count() {
        List<Users> usersCount = userRepository.findAll();

        return usersCount.size();
    }

    public void deleteById(int id) {
        if (existsById(id))
            userRepository.deleteById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) new UserPrincipal
                (userRepository.findByEmailAllIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException("Did not find the email" + username))
                        , authGroupRepo.findByEmail(username));
    }
}
