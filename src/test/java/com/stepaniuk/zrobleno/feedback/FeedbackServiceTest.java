package com.stepaniuk.zrobleno.feedback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.AdditionalAnswers.answer;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stepaniuk.zrobleno.feedback.exception.NoSuchFeedbackByIdException;
import com.stepaniuk.zrobleno.feedback.payload.FeedbackCreateRequest;
import com.stepaniuk.zrobleno.feedback.payload.FeedbackUpdateRequest;
import com.stepaniuk.zrobleno.testspecific.ServiceLevelUnitTest;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;

@ServiceLevelUnitTest
@ContextConfiguration(classes = {FeedbackService.class, FeedbackMapperImpl.class})
class FeedbackServiceTest {

  @Autowired
  private FeedbackService feedbackService;

  @MockBean
  private FeedbackRepository feedbackRepository;

  private static Feedback getNewFeedbackWithAllFields(Long id, Long orderId, UUID ownerId) {
    return new Feedback(id, orderId, ownerId, 5, "comment", Instant.now(),
        Instant.now().plus(Duration.ofHours(15)));
  }
  @Test
  void shouldReturnFeedbackResponseWhenCreatingFeedback() {
    // given
    var ownerId = UUID.randomUUID();
    var request = new FeedbackCreateRequest(1L, ownerId, 5, "comment");
    var feedback = getNewFeedbackWithAllFields(1L, request.getOrderId(), ownerId);

    when(feedbackRepository.save(any())).thenAnswer(answer(getFakeSave(1L)));
    // when
    var response = feedbackService.createFeedback(request);
    // then
    assertNotNull(response);
    assertEquals(1L, response.getId());
    assertEquals(request.getOrderId(), response.getOrderId());
    assertEquals(request.getOwnerId(), response.getOwnerId());
    assertEquals(request.getRating(), response.getRating());
    assertEquals(request.getComment(), response.getComment());
    assertTrue(response.hasLinks());
  }

  @Test
  void shouldReturnFeedbackResponseWhenGettingFeedbackById(){
    // given
    var feedback = getNewFeedbackWithAllFields(1L, 1L, UUID.randomUUID());
    when(feedbackRepository.findById(1L)).thenReturn(Optional.of(feedback));
    // when
    var response = feedbackService.getFeedback(1L);
    // then
    assertNotNull(response);
    assertEquals(1L, response.getId());
    assertEquals(feedback.getOrderId(), response.getOrderId());
    assertEquals(feedback.getOwnerId(), response.getOwnerId());
    assertEquals(feedback.getRating(), response.getRating());
    assertEquals(feedback.getComment(), response.getComment());
    assertTrue(response.hasLinks());
  }

  @Test
  void shouldThrowNoSuchFeedbackByIdExceptionWhenGetByNonExistingId(){
    // given
    when(feedbackRepository.findById(1L)).thenReturn(Optional.empty());
    // when && then
    assertThrows(NoSuchFeedbackByIdException.class, () -> feedbackService.getFeedback(1L));
  }

  @Test
  void shouldReturnPageOfFeedbacksWhenGettingAllFeedbacks(){
    //given
    Feedback feedbackToFind = getNewFeedbackWithAllFields(1L, 1L, UUID.randomUUID());

    Pageable pageable = PageRequest.of(0, 2);
    Specification<Feedback> specification = Specification.where(null);

    when(feedbackRepository.findAll(specification, pageable)).thenReturn(
        new PageImpl<>(List.of(feedbackToFind), pageable, 1));

    var responsePage = feedbackService.getAllFeedbacks(pageable, null, null);
    var feedbackResponse = responsePage.getContent().iterator().next();

    assertNotNull(responsePage);
    assertNotNull(feedbackResponse);

    assertEquals(1, responsePage.getTotalElements());
    assertEquals(1, responsePage.getContent().size());

    assertEquals(feedbackToFind.getId(), feedbackResponse.getId());
    assertEquals(feedbackToFind.getOrderId(), feedbackResponse.getOrderId());
    assertEquals(feedbackToFind.getOwnerId(), feedbackResponse.getOwnerId());
    assertEquals(feedbackToFind.getRating(), feedbackResponse.getRating());
    assertEquals(feedbackToFind.getComment(), feedbackResponse.getComment());
    assertTrue(feedbackResponse.hasLinks());
  }

  @Test
  void shouldReturnPageOfFeedbackResponseWhenGettingAllFeedbacksWhenOwnerIdIsNotNull(){
    //given
    var ownerId = UUID.randomUUID();
    Feedback feedbackToFind = getNewFeedbackWithAllFields(1L, 1L, ownerId);

    Pageable pageable = PageRequest.of(0, 2);

    when(feedbackRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(
        new PageImpl<>(List.of(feedbackToFind), pageable, 1));

    var responsePage = feedbackService.getAllFeedbacks(pageable, ownerId, null);
    var feedbackResponse = responsePage.getContent().iterator().next();

    assertNotNull(responsePage);
    assertNotNull(feedbackResponse);

    assertEquals(1, responsePage.getTotalElements());
    assertEquals(1, responsePage.getContent().size());

    assertEquals(feedbackToFind.getId(), feedbackResponse.getId());
    assertEquals(feedbackToFind.getOrderId(), feedbackResponse.getOrderId());
    assertEquals(feedbackToFind.getOwnerId(), feedbackResponse.getOwnerId());
    assertEquals(feedbackToFind.getRating(), feedbackResponse.getRating());
    assertEquals(feedbackToFind.getComment(), feedbackResponse.getComment());
    assertTrue(feedbackResponse.hasLinks());
  }

  @Test
  void shouldReturnPageOfFeedbackResponseWhenGettingAllFeedbacksWhenOrderIdNotNull(){
    //given
    var ownerId = UUID.randomUUID();
    Feedback feedbackToFind = getNewFeedbackWithAllFields(1L, 1L, ownerId);

    Pageable pageable = PageRequest.of(0, 2);

    when(feedbackRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(
        new PageImpl<>(List.of(feedbackToFind), pageable, 1));

    var responsePage = feedbackService.getAllFeedbacks(pageable, null, 1L);
    var feedbackResponse = responsePage.getContent().iterator().next();

    assertNotNull(responsePage);
    assertNotNull(feedbackResponse);

    assertEquals(1, responsePage.getTotalElements());
    assertEquals(1, responsePage.getContent().size());

    assertEquals(feedbackToFind.getId(), feedbackResponse.getId());
    assertEquals(feedbackToFind.getOrderId(), feedbackResponse.getOrderId());
    assertEquals(feedbackToFind.getOwnerId(), feedbackResponse.getOwnerId());
    assertEquals(feedbackToFind.getRating(), feedbackResponse.getRating());
    assertEquals(feedbackToFind.getComment(), feedbackResponse.getComment());
    assertTrue(feedbackResponse.hasLinks());

  }

  @Test
  void shouldReturnVoidWhenDeleteFeedback(){
    //given
    var feedback = getNewFeedbackWithAllFields(1L, 1L, UUID.randomUUID());
    when(feedbackRepository.findById(1L)).thenReturn(Optional.of(feedback));
    //when
    feedbackService.deleteFeedback(1L);
    //then
    verify(feedbackRepository, times(1)).delete(feedback);
  }

  @Test
  void shouldReturnNoSuchFeedbackByIdExceptionWhenDeleteFeedbackByNonExistingId(){
    // given
    when(feedbackRepository.findById(1L)).thenReturn(Optional.empty());

    // when && then
    assertThrows(NoSuchFeedbackByIdException.class, () -> feedbackService.deleteFeedback(1L));
  }

  @Test
  void shouldUpdateAndReturnFeedbackResponseWhenChangingRating(){
    // given
    var feedback = getNewFeedbackWithAllFields(1L, 1L, UUID.randomUUID());
    var request = new FeedbackUpdateRequest(4, null);
    when(feedbackRepository.findById(1L)).thenReturn(Optional.of(feedback));
    when(feedbackRepository.save(any())).thenAnswer(answer(getFakeSave(1L)));
    // when
    var response = feedbackService.updateFeedback(1L, request);
    // then
    assertNotNull(response);
    assertEquals(1L, response.getId());
    assertEquals(feedback.getOrderId(), response.getOrderId());
    assertEquals(feedback.getOwnerId(), response.getOwnerId());
    assertEquals(request.getRating(), response.getRating());
    assertEquals(feedback.getComment(), response.getComment());
    assertTrue(response.hasLinks());
  }

  private Answer1<Feedback, Feedback> getFakeSave(long id) {
    return feedback -> {
      feedback.setId(id);
      return feedback;
    };
  }
}
