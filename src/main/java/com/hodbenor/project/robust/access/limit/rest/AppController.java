package com.hodbenor.project.robust.access.limit.rest;

import com.hodbenor.project.robust.access.limit.UserEndpointService;
import com.hodbenor.project.robust.access.limit.data.beans.User;
import com.hodbenor.project.robust.access.limit.data.beans.UserEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class AppController {

    private final UserEndpointService service;
    private static final int ENDPOINT_ID1 = 1;
    private static final int ENDPOINT_ID2 = 2;

    public AppController(UserEndpointService service) {
        this.service = service;
    }

    @GetMapping(path = "/resource-one")
    @RestValidator(endpointId = ENDPOINT_ID1)
    public ResponseEntity<Void> consumeQuotaResourceOne(User user, @RequestHeader(name = "user-id") long userId) {

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/resource-one/status")
    public ResponseEntity<Integer> statusQuotaResourceOne(@RequestHeader(name = "user-id") long userId) {
        int userNumRequests = service.getUserEndpoint(userId, ENDPOINT_ID1)
                .map(UserEndpoint::getNumRequests)
                .orElse(0);

        return ResponseEntity.ok(userNumRequests);
    }

    @GetMapping(path = "/resource-two")
    @RestValidator(endpointId = ENDPOINT_ID2)
    public ResponseEntity<Void> consumeQuotaResourceTwo(User user, @RequestHeader(name = "user-id") long userId) {

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/resource-two/status")
    public ResponseEntity<Integer> statusQuotaResourceTwo(@RequestHeader(name = "user-id") long userId) {
        int userNumRequests = service.getUserEndpoint(userId, ENDPOINT_ID2)
                .map(UserEndpoint::getNumRequests)
                .orElse(0);

        return ResponseEntity.ok(userNumRequests);
    }
}
