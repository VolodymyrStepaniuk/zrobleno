package com.stepaniuk.zrobleno.service;

import com.stepaniuk.zrobleno.service.payload.ServiceResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.hateoas.Link;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ServiceMapper {
  @BeanMapping(qualifiedByName = "addLinks")
  ServiceResponse toResponse(Service service);

  @AfterMapping
  @Named("addLinks")
  default ServiceResponse addLinks(Service service, @MappingTarget ServiceResponse response) {
    response.add(Link.of("/services/" + service.getId()).withSelfRel());
    return response;
  }
}
