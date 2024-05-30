package com.r2s.javabackend09.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


@Component

public class UserSessionManager {
	private List<User> activeSessions;

    public UserSessionManager() {
        activeSessions = new ArrayList<>();
    }

    public void addSession(User session) {
        activeSessions.add(session);
    }

    public void removeSession(User session) {
        activeSessions.remove(session);
    }

    public List<User> getActiveSessions() {
        return activeSessions;
    }
    
    public List<User> getLoggedInUsers() {
        List<User> loggedInUsers = new ArrayList<>();
        for (User session : activeSessions) {
            User user = new User();
            user.setId(session.getId());
            user.setUserName(session.getUserName());
            user.setName(session.getName());
            user.setPassWord(session.getPassWord());
            loggedInUsers.add(user);
        }
        return loggedInUsers;
    }
    
}
