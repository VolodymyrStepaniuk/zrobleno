package com.stepaniuk.zrobleno.feedback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.stepaniuk.zrobleno.feedback.payload.FeedbackResponse;
import com.stepaniuk.zrobleno.service.payload.ServiceResponse;
import com.stepaniuk.zrobleno.testspecific.MapperLevelUnitTest;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@MapperLevelUnitTest
@ContextConfiguration(classes = {FeedbackMapperImpl.class})
class FeedbackMapperTest {

  @Autowired
  private FeedbackMapper feedbackMapper;

  @Test
  void shouldMapFeedbackToFeedbackResponse() {
    // given
    Instant timeOfCreation = Instant.now().plus(Duration.ofHours(10));
    Instant timeOfModification = Instant.now().plus(Duration.ofHours(20));

    Feedback feedbackToMap = new Feedback(
        1L, 1L, UUID.randomUUID(), 5, "comment", timeOfCreation, timeOfModification
    );

    // when
    FeedbackResponse feedbackResponse = feedbackMapper.toResponse(
        feedbackToMap);

    // then
    assertNotNull(feedbackResponse);
    assertEquals(feedbackToMap.getId(), feedbackResponse.getId());
    assertEquals(feedbackToMap.getOrderId(), feedbackResponse.getOrderId());
    assertEquals(feedbackToMap.getOwnerId(), feedbackResponse.getOwnerId());
    assertEquals(feedbackToMap.getRating(), feedbackResponse.getRating());
    assertEquals(feedbackToMap.getComment(), feedbackResponse.getComment());
    assertEquals(feedbackToMap.getCreatedAt(), feedbackResponse.getCreatedAt());
    assertEquals(feedbackToMap.getLastModifiedAt(),
        feedbackResponse.getLastModifiedAt());
    assertTrue(feedbackResponse.hasLinks());
  }
}
