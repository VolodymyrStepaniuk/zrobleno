package com.stepaniuk.zrobleno.order.payload;

import com.stepaniuk.zrobleno.feedback.payload.FeedbackResponse;
import com.stepaniuk.zrobleno.order.status.OrderStatus;
import com.stepaniuk.zrobleno.validation.shared.Id;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "orders", itemRelation = "order")
public class OrderResponse extends RepresentationModel<OrderResponse> {

  @Id
  @NotNull
  private Long id;

  @NotNull
  private UUID ownerId;

  @NotNull
  private OrderStatus status;

  @NotNull
  private List<Long> serviceIds;

  @Nullable
  private final FeedbackResponse feedback;

  @NotNull
  private final Instant createdAt;

  @NotNull
  private final Instant lastModifiedAt;

}
