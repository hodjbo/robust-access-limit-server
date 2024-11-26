package com.hodbenor.project.robust.access.limit;

import com.hodbenor.project.robust.access.limit.data.DataResourceManager;
import com.hodbenor.project.robust.access.limit.data.DataResourceService;
import com.hodbenor.project.robust.access.limit.data.DataResourceType;
import com.hodbenor.project.robust.access.limit.data.beans.UserEndpoint;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
public class UserEndpointServiceImpl implements UserEndpointService {
    private final Map<DataResourceType, DataResourceService<UserEndpoint>> userEndpointDalServices;

    public UserEndpointServiceImpl(Set<DataResourceService<UserEndpoint>> services) {
        this.userEndpointDalServices = new HashMap<>();
        services.forEach(service -> this.userEndpointDalServices.put(service.getDataResourceType(), service));
    }

    @Override
    public UserEndpoint createUserEndpoint(UserEndpoint userEndpoint) {
        return getDataService().save(userEndpoint);
    }

    @Override
    public Optional<UserEndpoint> getUserEndpoint(long userId, long endpointId) {
        return getDataService().get(userId, endpointId);
    }

    @Override
    public void increment(long id, int incrementBy) {
        getDataService().increment(id, incrementBy);
    }

    private DataResourceService<UserEndpoint> getDataService() {
        return userEndpointDalServices.get(DataResourceManager.getDataResourceType());
    }
}
