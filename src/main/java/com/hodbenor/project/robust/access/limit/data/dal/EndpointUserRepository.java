package com.hodbenor.project.robust.access.limit.data.dal;

import com.hodbenor.project.robust.access.limit.data.beans.UserEndpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface EndpointUserRepository extends JpaRepository<UserEndpoint, Long> {

    @Query("SELECT ue FROM UserEndpoint ue WHERE ue.userId = :userId AND ue.endpointId = :endpointId")
    Optional<UserEndpoint> findByIds(@Param("userId") long userId, @Param("endpointId") long endpointId);

    @Modifying
    @Transactional
    @Query("UPDATE UserEndpoint ue SET ue.numRequests = ue.numRequests + :increment WHERE ue.id = :userEndpointId")
    void incrementRequests(@Param("userEndpointId") long userEndpointId, @Param("increment") int increment);
}

