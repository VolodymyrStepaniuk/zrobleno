package com.stepaniuk.zrobleno.feedback;

import com.stepaniuk.zrobleno.feedback.payload.FeedbackResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.hateoas.Link;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FeedbackMapper {
  @BeanMapping(qualifiedByName = "addLinks")
  FeedbackResponse toResponse(Feedback feedback);

  @AfterMapping
  @Named("addLinks")
  default FeedbackResponse addLinks(Feedback feedback, @MappingTarget FeedbackResponse response) {
    response.add(Link.of("/feedbacks/" + feedback.getId()).withSelfRel());
    return response;
  }
}
