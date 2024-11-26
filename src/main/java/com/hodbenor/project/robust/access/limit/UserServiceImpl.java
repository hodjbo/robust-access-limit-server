package com.hodbenor.project.robust.access.limit;

import com.hodbenor.project.robust.access.limit.data.DataResourceManager;
import com.hodbenor.project.robust.access.limit.data.DataResourceService;
import com.hodbenor.project.robust.access.limit.data.DataResourceType;
import com.hodbenor.project.robust.access.limit.data.beans.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserServiceImpl implements UserService {
    private final Map<DataResourceType, DataResourceService<User>> userDalServices;

    public UserServiceImpl(Set<DataResourceService<User>> services) {
        this.userDalServices = new HashMap<>();
        services.forEach(service -> this.userDalServices.put(service.getDataResourceType(), service));
    }

    @Override
    public User createUser(User user) {
        return getDataService().save(user);
    }

    @Override
    public User getUser(long userId) {
        DataResourceService<User> dataService = getDataService();

        return dataService.get(userId).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        DataResourceService<User> dataService = getDataService();

        return dataService.getAll();
    }


    @Override
    public void removeUser(long userId) {
        DataResourceService<User> dataService = getDataService();

        dataService.deleteById(userId);
    }

    @Override
    public boolean updateUserNames(User user) {
        //TODO lock user before update
        DataResourceService<User> dataService = getDataService();
        Optional<User> optUser = dataService.get(user.getId());
        if (optUser.isEmpty()) {
            return false;
        }

        User userToUpdate = optUser.get();
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());

        return dataService.update(userToUpdate);
    }

    private DataResourceService<User> getDataService() {
        return userDalServices.get(DataResourceManager.getDataResourceType());
    }
}
