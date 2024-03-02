package com.stepaniuk.zrobleno.service.category;

import com.stepaniuk.zrobleno.service.payload.category.ServiceCategoryResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.hateoas.Link;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ServiceCategoryMapper {
  @BeanMapping(qualifiedByName = "addLinks")
  ServiceCategoryResponse toResponse(ServiceCategory serviceCategory);

  @AfterMapping
  @Named("addLinks")
  default ServiceCategoryResponse addLinks(ServiceCategory serviceCategory,
      @MappingTarget ServiceCategoryResponse response) {
    response.add(Link.of("/services/category/" + serviceCategory.getId()).withSelfRel());
    return response;
  }
}
