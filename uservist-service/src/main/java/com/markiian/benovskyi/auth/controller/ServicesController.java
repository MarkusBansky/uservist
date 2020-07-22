package com.markiian.benovskyi.auth.controller;

import com.markiian.benovskyi.auth.security.CurrentUser;
import com.markiian.benovskyi.auth.service.ServicesService;
import com.markiian.benovskyi.auth.util.ApplicationConstants;
import com.markiian.benovskyi.uservist.api.uservist_api.api.ServicesApi;
import com.markiian.benovskyi.uservist.api.uservist_api.model.ServiceCreateDto;
import com.markiian.benovskyi.uservist.api.uservist_api.model.ServiceDto;
import com.markiian.benovskyi.uservist.api.uservist_api.model.ServiceUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1")
public class ServicesController implements ServicesApi {
    private final ServicesService servicesService;

    @Autowired
    public ServicesController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity servicesGetAll(@NotNull @Valid Integer page) {
        return ResponseEntity.ok(servicesService.getAllServices(page));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ServiceDto> servicesGetById(Long id) {
        return ResponseEntity.ok(servicesService.getServiceById(id));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ServiceDto> servicesCreate(@Valid ServiceCreateDto serviceCreateDto) {
        return ResponseEntity.ok(servicesService.createNewService(serviceCreateDto));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ServiceDto> servicesUpdateById(Long id, @Valid ServiceUpdateDto serviceUpdateDto) {
        return ResponseEntity.ok(servicesService.updateExistingService(serviceUpdateDto));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> servicesDeleteById(Long id) {
        servicesService.deleteService(id);
        return ResponseEntity.ok().build();
    }
}
