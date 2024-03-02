package com.stepaniuk.zrobleno.order;

import com.stepaniuk.zrobleno.feedback.payload.FeedbackResponse;
import com.stepaniuk.zrobleno.order.payload.OrderResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.hateoas.Link;
import org.springframework.lang.Nullable;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderMapper {
  @BeanMapping(qualifiedByName = "addLinks")
  @Mapping(target = "status", source = "order.status.name")
  @Mapping(target = "feedback", source = "feedback")
  @Mapping(target = "id", source = "order.id")
  @Mapping(target = "createdAt", source = "order.createdAt")
  @Mapping(target = "lastModifiedAt", source = "order.lastModifiedAt")
  @Mapping(target = "ownerId", source = "order.ownerId")
  OrderResponse toResponse(Order order, @Nullable FeedbackResponse feedback);

  @AfterMapping
  @Named("addLinks")
  default OrderResponse addLinks(Order order, @MappingTarget OrderResponse response) {
    response.add(Link.of("/orders/" + order.getId()).withSelfRel());
    return response;
  }
}
