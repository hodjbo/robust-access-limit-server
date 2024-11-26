package com.hodbenor.project.robust.access.limit.data.dal;

import com.hodbenor.project.robust.access.limit.data.DataResourceService;
import com.hodbenor.project.robust.access.limit.data.DataResourceType;
import com.hodbenor.project.robust.access.limit.data.beans.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoMock implements DataResourceService<User> {
    private static final Logger LOG = LogManager.getLogger(UserDaoMock.class);

    @Override
    public User save(User user) {
        LOG.info("Mocking save user {}", user);

        return user;
    }

    @Override
    public DataResourceType getDataResourceType() {
        return DataResourceType.MOCK;
    }

    @Override
    public Optional<User> get(long id) {
        LOG.info("Mocking get user {}", id);

        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        LOG.info("Mocking getAll");

        return List.of();
    }

    @Override
    public void deleteById(long id) {
        LOG.info("Mocking deleteById for user {}", id);
    }

    @Override
    public boolean update(User user) {
        LOG.info("Mocking update for user {}", user.getId());

        return false;
    }
}
