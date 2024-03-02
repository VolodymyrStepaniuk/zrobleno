package com.stepaniuk.zrobleno.feedback.payload;

import com.stepaniuk.zrobleno.validation.feedback.Comment;
import com.stepaniuk.zrobleno.validation.feedback.Rating;
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

  @Rating
  @Nullable
  private Integer rating;

  @Comment
  @Nullable
  private String comment;

}
