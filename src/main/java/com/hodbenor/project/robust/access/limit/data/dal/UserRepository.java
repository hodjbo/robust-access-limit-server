package com.hodbenor.project.robust.access.limit.data.dal;

import com.hodbenor.project.robust.access.limit.data.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.firstName = :firstName, u.lastName = :lastName WHERE u.id = :id")
    int updateUserNames(@Param("id") Long id, @Param("firstName") String firstName, @Param("lastName") String lastName);
}

