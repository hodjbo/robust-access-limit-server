package com.hodbenor.project.robust.access.limit.data.beans;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "UserEndpoint")
@Table(name = "user_endpoint",
        indexes = {
                @Index(name = "idx_user", columnList = "user_id"),
                @Index(name = "idx_endpoint", columnList = "endpoint_id")
        })
@Data
public class UserEndpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "endpoint_id", nullable = false)
    private long endpointId;

    @Column(name = "num_requests", nullable = false)
    private int numRequests;
}
