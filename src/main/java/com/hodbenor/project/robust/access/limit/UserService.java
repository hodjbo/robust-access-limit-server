package com.hodbenor.project.robust.access.limit;

import com.hodbenor.project.robust.access.limit.data.beans.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUser(long userId);

    List<User> getAllUsers();

    void removeUser(long userId);
    boolean updateUserNames(User user);
}
