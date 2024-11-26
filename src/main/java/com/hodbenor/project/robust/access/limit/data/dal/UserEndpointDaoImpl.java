package com.hodbenor.project.robust.access.limit.data.dal;

import com.hodbenor.project.robust.access.limit.data.DataResourceService;
import com.hodbenor.project.robust.access.limit.data.DataResourceType;
import com.hodbenor.project.robust.access.limit.data.beans.UserEndpoint;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class UserEndpointDaoImpl implements DataResourceService<UserEndpoint> {
    private final EndpointUserRepository repository;

    public UserEndpointDaoImpl(EndpointUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public DataResourceType getDataResourceType() {
        return DataResourceType.MYSQL;
    }

    @Override
    public UserEndpoint save(UserEndpoint userEndpoint) {
        return repository.save(userEndpoint);
    }

    @Override
    @Transactional()
    public Optional<UserEndpoint> get(long firstId, long secondId) {
        return repository.findByIds(firstId, secondId);
    }

    @Override
    public List<UserEndpoint> getAll() {
        return repository.findAll();
    }

    @Override
    public void increment(long id, int incrementBy) {
        repository.incrementRequests(id, incrementBy);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
