package com.stepaniuk.zrobleno.feedback.payload;

import com.stepaniuk.zrobleno.validation.feedback.Comment;
import com.stepaniuk.zrobleno.validation.feedback.Rating;
import com.stepaniuk.zrobleno.validation.shared.Id;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class FeedbackCreateRequest {

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

}
