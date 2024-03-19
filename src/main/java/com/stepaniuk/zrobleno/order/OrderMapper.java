package com.stepaniuk.zrobleno.order;

import com.stepaniuk.zrobleno.feedback.payload.FeedbackResponse;
import com.stepaniuk.zrobleno.order.payload.OrderResponse;
import com.stepaniuk.zrobleno.order.status.OrderStatusName;
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
  @Mapping(target = "comment", source = "order.comment")
  OrderResponse toResponse(Order order, @Nullable FeedbackResponse feedback);

  @AfterMapping
  @Named("addLinks")
  default OrderResponse addLinks(Order order, @MappingTarget OrderResponse response) {
    response.add(Link.of("/orders/" + order.getId()).withSelfRel());

    if(order.getStatus().getName().equals(OrderStatusName.CREATED)){
      response.add(Link.of("/orders/" + order.getId() + "/cancel").withRel("cancel"));
      response.add(Link.of("/orders/" + order.getId() + "/confirm").withRel("confirm"));
    }

    if (order.getStatus().getName().equals(OrderStatusName.CONFIRMED)) {
      response.add(Link.of("/orders/" + order.getId() + "/in-progress").withRel("in-progress"));
      response.add(Link.of("/orders/" + order.getId() + "/cancel").withRel("cancel"));
    }

    if (order.getStatus().getName().equals(OrderStatusName.IN_PROGRESS)) {
      response.add(Link.of("/orders/" + order.getId() + "/complete").withRel("complete"));
    }

    return response;
  }
}
