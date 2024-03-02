package com.stepaniuk.zrobleno.feedback;

import com.stepaniuk.zrobleno.feedback.payload.FeedbackCreateRequest;
import com.stepaniuk.zrobleno.feedback.payload.FeedbackResponse;
import com.stepaniuk.zrobleno.feedback.payload.FeedbackUpdateRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/feedbacks", produces = "application/json")
public class FeedbackController {

  private final FeedbackService feedbackService;

  @PostMapping
  public ResponseEntity<FeedbackResponse> createFeedback(@RequestBody FeedbackCreateRequest request) {
    return ResponseEntity.ok(feedbackService.createFeedback(request));
  }

  @GetMapping("/{id}")
  public ResponseEntity<FeedbackResponse> getFeedbackById(@PathVariable Long id) {
    return ResponseEntity.ok(feedbackService.getFeedback(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
    feedbackService.deleteFeedback(id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<FeedbackResponse> updateFeedback(@PathVariable Long id,
      @RequestBody FeedbackUpdateRequest request) {
    return ResponseEntity.ok(feedbackService.updateFeedback(id, request));
  }

  @GetMapping("/v1")
  public ResponseEntity<Page<FeedbackResponse>> getAllFeedbacks(Pageable pageable,
      @Nullable @RequestParam(required = false) UUID ownerId) {
    return ResponseEntity.ok(feedbackService.getAllFeedbacks(pageable, ownerId, null));
  }

  @GetMapping("/v2")
  public ResponseEntity<Page<FeedbackResponse>> getAllFeedbacks(Pageable pageable,
      @Nullable @RequestParam(required = false) UUID ownerId,
      @Nullable @RequestParam(required = false) Long orderId) {
    return ResponseEntity.ok(feedbackService.getAllFeedbacks(pageable, ownerId, orderId));
  }
}
