package dz.djezzydevs.hrplaning.services;


import dz.djezzydevs.hrplaning.entities.Authlogs;
import dz.djezzydevs.hrplaning.repositories.AuthLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.time.LocalDateTime.now;

@Service
public class LogService {

    @Autowired
    AuthLogsRepository authLogsRepository;

    public void login (String username) {
    Authlogs logUsername= new Authlogs();
        logUsername.setUsername(username);
        logUsername.setDatetime(now());
        logUsername.setLogname("Login");
         authLogsRepository.save(logUsername);
       // return  auth;

    }

    public void logout (String username) {
        Authlogs logUsername= new Authlogs();
        logUsername.setUsername(username);
        logUsername.setDatetime(now());
        logUsername.setLogname("Logout");
        authLogsRepository.save(logUsername);
        // return  auth;

    }
}
