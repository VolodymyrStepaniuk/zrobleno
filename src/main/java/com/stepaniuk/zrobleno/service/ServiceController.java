package com.stepaniuk.zrobleno.service;

import com.stepaniuk.zrobleno.service.payload.ServiceCreateRequest;
import com.stepaniuk.zrobleno.service.payload.ServiceResponse;
import com.stepaniuk.zrobleno.service.payload.ServiceUpdateRequest;
import com.stepaniuk.zrobleno.service.payload.category.ServiceCategoryResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/services", produces = "application/json")
public class ServiceController {

  private final ServiceService service;

  @PostMapping
  public ResponseEntity<ServiceResponse> createService(@RequestBody ServiceCreateRequest request) {
    return ResponseEntity.ok(service.createService(request));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ServiceResponse> getServiceById(@PathVariable Long id) {
    return ResponseEntity.ok(service.getService(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteService(@PathVariable Long id) {
    service.deleteService(id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ServiceResponse> updateService(@PathVariable Long id,
      ServiceUpdateRequest request) {
    return ResponseEntity.ok(service.updateService(id, request));
  }

  @GetMapping("/v1")
  public ResponseEntity<Page<ServiceResponse>> getAllServices(Pageable pageable,
      @Nullable @RequestParam(required = false) Long categoryId) {
    return ResponseEntity.ok(service.getAllServices(pageable, categoryId, null));
  }

  @GetMapping("/v2")
  public ResponseEntity<Page<ServiceResponse>> getAllServices(Pageable pageable,
      @Nullable @RequestParam(required = false) Long categoryId,
      @Nullable @RequestParam(required = false) List<Long> serviceIds) {
    return ResponseEntity.ok(service.getAllServices(pageable, categoryId, serviceIds));
  }


  @GetMapping("/categories/{id}")
  public ResponseEntity<ServiceCategoryResponse> getCategoryById(@PathVariable Long id) {
    return ResponseEntity.ok(service.getServiceCategory(id));
  }

  @GetMapping("/v1/categories")
  public ResponseEntity<Page<ServiceCategoryResponse>> getAllServiceCategories(
      Pageable pageable) {
    return ResponseEntity.ok(service.getAllServiceCategories(pageable, null));
  }

  @GetMapping("/v2/categories")
  public ResponseEntity<Page<ServiceCategoryResponse>> getAllServiceCategories(
      Pageable pageable,
      @Nullable @RequestParam(required = false) List<Long> ids) {
    return ResponseEntity.ok(service.getAllServiceCategories(pageable, ids));
  }

}
