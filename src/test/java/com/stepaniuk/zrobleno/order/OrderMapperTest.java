package com.stepaniuk.zrobleno.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.stepaniuk.zrobleno.feedback.payload.FeedbackResponse;
import com.stepaniuk.zrobleno.order.status.OrderStatus;
import com.stepaniuk.zrobleno.order.status.OrderStatusName;
import com.stepaniuk.zrobleno.testspecific.MapperLevelUnitTest;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@MapperLevelUnitTest
@ContextConfiguration(classes = {OrderMapperImpl.class})
class OrderMapperTest {

  @Autowired
  private OrderMapper orderMapper;

  @Test
  void shouldMapOrderToOrderResponse() {
    // given
    Instant timeOfCreation = Instant.now().plus(Duration.ofHours(10));
    Instant timeOfModification = Instant.now().plus(Duration.ofHours(20));
    var orderStatus = new OrderStatus(1L, OrderStatusName.CREATED);
    var ownerId = UUID.randomUUID();

    var feedback = new FeedbackResponse(1L, 1L, ownerId, 5, "text", timeOfCreation,
        timeOfModification);

    Order orderToMap = new Order(
        1L, ownerId, orderStatus, 1L, "New comment", timeOfCreation, timeOfModification
    );

    // when
    var orderResponse = orderMapper.toResponse(orderToMap, feedback);

    // then
    assertNotNull(orderResponse);
    assertEquals(orderToMap.getId(), orderResponse.getId());
    assertEquals(orderToMap.getOwnerId(), orderResponse.getOwnerId());
    assertEquals(orderToMap.getStatus().getName(), orderResponse.getStatus());
    assertEquals(orderToMap.getServiceId(), orderResponse.getServiceId());
    assertEquals(orderToMap.getComment(), orderResponse.getComment());
    assertEquals(orderToMap.getCreatedAt(), orderResponse.getCreatedAt());
    assertEquals(orderToMap.getLastModifiedAt(), orderResponse.getLastModifiedAt());
    assertNotNull(orderResponse.getFeedback());
    assertTrue(orderResponse.hasLinks());
  }
}
