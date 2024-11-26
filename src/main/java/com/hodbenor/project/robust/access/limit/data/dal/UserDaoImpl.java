package com.hodbenor.project.robust.access.limit.data.dal;

import com.hodbenor.project.robust.access.limit.data.DataResourceService;
import com.hodbenor.project.robust.access.limit.data.DataResourceType;
import com.hodbenor.project.robust.access.limit.data.beans.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements DataResourceService<User> {
    private final UserRepository userRepository;

    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public DataResourceType getDataResourceType() {
        return DataResourceType.MYSQL;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> get(long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean update(User user) {
        int rowUpdated = userRepository.updateUserNames(user.getId(), user.getFirstName(), user.getLastName());
        return rowUpdated > 0;
    }


}
