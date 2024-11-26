package com.hodbenor.project.robust.access.limit;

import com.hodbenor.project.robust.access.limit.data.beans.UserEndpoint;

import java.util.Optional;

public interface UserEndpointService {
    UserEndpoint createUserEndpoint(UserEndpoint userEndpoint);
    Optional<UserEndpoint> getUserEndpoint(long userId, long endpointId);
    void increment(long id, int incrementBy);
}
