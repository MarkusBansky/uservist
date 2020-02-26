package com.markiian.benovskyi.auth.controller;

import com.markiian.benovskyi.api.ServicesApi;
import com.markiian.benovskyi.auth.security.CurrentUser;
import com.markiian.benovskyi.auth.service.ServicesService;
import com.markiian.benovskyi.auth.util.ApplicationConstants;
import com.markiian.benovskyi.auth.util.ResponseUtil;
import com.markiian.benovskyi.model.ServiceDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class ServicesController implements ServicesApi {
    private final ServicesService servicesService;

    public ServicesController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity servicesCreate(@Valid ServiceDto serviceDto) {
        if (!CurrentUser.getUsername().equals(ApplicationConstants.SUPER_ADMIN_USERNAME)) {
            return ResponseUtil.buildErrorResponse(HttpStatus.FORBIDDEN, "You have not enough access rights");
        }
        return ResponseUtil.buildResponse(() -> servicesService.createNewService(serviceDto));
    }

    @Override
    public ResponseEntity servicesDeleteById(Long id) {
        return ResponseUtil.buildErrorResponse(HttpStatus.NOT_IMPLEMENTED, "NOT IMPLEMENTED");
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity servicesGetAll() {
        return ResponseUtil.buildResponse(() -> servicesService.getAllServicesForUser(CurrentUser.getUsername()));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity servicesGetById(Long id) {
        return ResponseUtil.buildResponse(() -> servicesService.getServiceForUserById(CurrentUser.getUsername(), id));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity servicesUpdateById(Long id, @Valid ServiceDto serviceDto) {
        if (!CurrentUser.getUsername().equals(ApplicationConstants.SUPER_ADMIN_USERNAME)) {
            return ResponseUtil.buildErrorResponse(HttpStatus.FORBIDDEN, "You have not enough access rights");
        }
        return ResponseUtil.buildResponse(() -> servicesService.updateExistingService(serviceDto, id));
    }
}
