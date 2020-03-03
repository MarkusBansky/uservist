package com.markiian.benovskyi.auth.controller;

import com.markiian.benovskyi.api.ServicesApi;
import com.markiian.benovskyi.auth.security.CurrentUser;
import com.markiian.benovskyi.auth.service.ServicesPermissionService;
import com.markiian.benovskyi.auth.service.ServicesService;
import com.markiian.benovskyi.auth.util.ApplicationConstants;
import com.markiian.benovskyi.auth.util.ResponseUtil;
import com.markiian.benovskyi.model.ServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ServicesController implements ServicesApi {
    private final ServicesPermissionService servicesPermissionsService;
    private final ServicesService servicesService;

    @Autowired
    public ServicesController(ServicesPermissionService servicesPermissionsService, ServicesService servicesService) {
        this.servicesPermissionsService = servicesPermissionsService;
        this.servicesService = servicesService;
    }

    @Override
    public ResponseEntity servicesGetAll(@NotNull @Valid Integer page) {
        if (servicesPermissionsService.canGetAll(CurrentUser.getAuth())) {
            return ResponseUtil.buildResponse(() -> servicesService.getAllServices(page));
        }
        return ResponseUtil.buildErrorResponse(HttpStatus.FORBIDDEN, ApplicationConstants.UNAUTHORIZED_EXCEPTION_MESSAGE);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity servicesGetById(Long id) {
        if (servicesPermissionsService.canGetById(id, CurrentUser.getAuth())) {
            return ResponseUtil.buildResponse(() -> servicesService.getServiceById(id));
        }
        return ResponseUtil.buildErrorResponse(HttpStatus.FORBIDDEN, ApplicationConstants.FORBIDDEN_EXCEPTION_MESSAGE);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity servicesCreate(@Valid ServiceDto serviceDto) {
        if (servicesPermissionsService.canCreate(serviceDto, CurrentUser.getAuth())) {
            return ResponseUtil.buildResponse(() -> servicesService.createNewService(serviceDto));
        }
        return ResponseUtil.buildErrorResponse(HttpStatus.FORBIDDEN, ApplicationConstants.UNAUTHORIZED_EXCEPTION_MESSAGE);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity servicesUpdateById(@Valid ServiceDto serviceDto) {
        if (servicesPermissionsService.canUpdate(serviceDto, CurrentUser.getAuth())) {
            return ResponseUtil.buildResponse(() -> servicesService.updateExistingService(serviceDto));
        }
        return ResponseUtil.buildErrorResponse(HttpStatus.FORBIDDEN, ApplicationConstants.UNAUTHORIZED_EXCEPTION_MESSAGE);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity servicesDeleteById(Long id) {
        if (servicesPermissionsService.canDelete(id, CurrentUser.getAuth())) {
            return ResponseUtil.buildResponse(() -> servicesService.deleteService(id));
        }
        return ResponseUtil.buildErrorResponse(HttpStatus.FORBIDDEN, ApplicationConstants.FORBIDDEN_EXCEPTION_MESSAGE);
    }
}
