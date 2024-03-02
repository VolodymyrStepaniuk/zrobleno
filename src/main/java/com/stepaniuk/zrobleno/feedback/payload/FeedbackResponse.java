package com.stepaniuk.zrobleno.feedback.payload;

import com.stepaniuk.zrobleno.validation.feedback.Comment;
import com.stepaniuk.zrobleno.validation.feedback.Rating;
import com.stepaniuk.zrobleno.validation.shared.Id;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
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
@Relation(collectionRelation = "feedbacks", itemRelation = "feedback")
public class FeedbackResponse extends RepresentationModel<FeedbackResponse> {
  @Id
  @NotNull
  private Long id;

  @Id
  @NotNull
  private Long orderId;

  @NotNull
  private UUID ownerId;

  @Rating
  @NotNull
  private Integer rating;

  @Comment
  @Nullable
  private String comment;

  @NotNull
  private final Instant createdAt;

  @NotNull
  private final Instant lastModifiedAt;

}
