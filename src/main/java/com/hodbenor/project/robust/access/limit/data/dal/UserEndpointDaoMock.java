package com.hodbenor.project.robust.access.limit.data.dal;

import com.hodbenor.project.robust.access.limit.data.DataResourceService;
import com.hodbenor.project.robust.access.limit.data.DataResourceType;
import com.hodbenor.project.robust.access.limit.data.beans.UserEndpoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserEndpointDaoMock implements DataResourceService<UserEndpoint> {
    private static final Logger LOG = LogManager.getLogger(UserEndpointDaoMock.class);

    @Override
    public DataResourceType getDataResourceType() {
        return DataResourceType.MOCK;
    }

    @Override
    public UserEndpoint save(UserEndpoint userEndpoint) {
        LOG.info("Mocking save UserEndpoint {}", userEndpoint);

        return null;
    }

    @Override
    public Optional<UserEndpoint> get(long firstId, long secondId) {
        LOG.info("Mocking get UserEndpoint for user {}, endpoint {}.", firstId, secondId);

        return DataResourceService.super.get(firstId, secondId);
    }

    @Override
    public List<UserEndpoint> getAll() {
        LOG.info("Mocking getAll UserEndpoint");

        return List.of();
    }

    @Override
    public void increment(long id, int incrementBy) {
        LOG.info("Mocking increment UserEndpoint for id {}", id);

        DataResourceService.super.increment(id, incrementBy);
    }

    @Override
    public void deleteById(long id) {
        LOG.info("Mocking deleteById UserEndpoint for id {}", id);
    }
}
