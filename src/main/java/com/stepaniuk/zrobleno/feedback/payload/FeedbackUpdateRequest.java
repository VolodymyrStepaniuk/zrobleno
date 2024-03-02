package com.stepaniuk.zrobleno.feedback.payload;

import com.stepaniuk.zrobleno.validation.feedback.Comment;
import com.stepaniuk.zrobleno.validation.feedback.Rating;
import com.stepaniuk.zrobleno.validation.shared.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.Nullable;
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class FeedbackUpdateRequest {

  @Id
  @Nullable
  private Long orderId;

  @Rating
  @Nullable
  private Integer rating;

  @Comment
  @Nullable
  private String comment;

}
