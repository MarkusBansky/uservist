package com.markiian.benovskyi.auth.builder;

import com.markiian.benovskyi.auth.persistance.model.Service;

import java.time.OffsetDateTime;

public final class TestServiceBuilder {
    public static Service buildService(Long id) {
        Service service = new Service();
        service.setId(id);
        service.setName("Service_" + id);
        service.setKey("service_" + id);
        service.setDescription("Service " + id);
        service.setCreatedAt(OffsetDateTime.now());
        service.setUpdatedAt(OffsetDateTime.now());
        return service;
    }

}
