package com.stepaniuk.zrobleno.shared;

import com.stepaniuk.zrobleno.feedback.exception.NoSuchFeedbackByIdException;
import com.stepaniuk.zrobleno.order.exception.IllegalOrderStatusException;
import com.stepaniuk.zrobleno.order.exception.NoSuchOrderByIdException;
import com.stepaniuk.zrobleno.order.exception.NoSuchOrderStatusByNameException;
import com.stepaniuk.zrobleno.service.exception.NoSuchServiceByIdException;
import com.stepaniuk.zrobleno.service.exception.NoSuchServiceCategoryByIdException;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralControllerExceptionHandler {
  @ExceptionHandler(value = {NoSuchServiceByIdException.class})
  public ProblemDetail handleNoSuchServiceByIdException(NoSuchServiceByIdException e) {
    var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
        "No service with id " + e.getId() + " found");
    problemDetail.setTitle("No such service");
    problemDetail.setInstance(URI.create("/services/" + e.getId()));
    return problemDetail;
  }

  @ExceptionHandler(value = {NoSuchServiceCategoryByIdException.class})
  public ProblemDetail handleNoSuchServiceCategoryByIdException(NoSuchServiceCategoryByIdException e) {
    var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
        "No service category with id " + e.getId() + " found");
    problemDetail.setTitle("No such service category");
    problemDetail.setInstance(URI.create("/services/categories/" + e.getId()));
    return problemDetail;
  }

  @ExceptionHandler(value = {IllegalOrderStatusException.class})
  public ProblemDetail handleIllegalOrderStatusException(IllegalOrderStatusException e) {
    var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
        "Illegal order status! From " + e.getPreviousStatus() + " to " + e.getStatus() + " in order with id: " + e.getId());
    problemDetail.setTitle("Illegal order status");
    problemDetail.setInstance(URI.create("/orders/" + e.getId()));
    return problemDetail;
  }

  @ExceptionHandler(value = {NoSuchOrderByIdException.class})
  public ProblemDetail handleNoSuchOrderByIdException(NoSuchOrderByIdException e) {
    var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
        "No order with id " + e.getId() + " found");
    problemDetail.setTitle("No such order");
    problemDetail.setInstance(URI.create("/orders/" + e.getId()));
    return problemDetail;
  }

  @ExceptionHandler(value = {NoSuchOrderStatusByNameException.class})
  public ProblemDetail handleNoSuchOrderStatusByNameException(NoSuchOrderStatusByNameException e) {
    var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
        "No order status with name " + e.getName() + " found");
    problemDetail.setTitle("No such order status");
    problemDetail.setInstance(URI.create("/orders/status/" + e.getName()));
    return problemDetail;
  }

  @ExceptionHandler(value = {NoSuchFeedbackByIdException.class})
  public ProblemDetail handleNoSuchFeedbackByIdException(NoSuchFeedbackByIdException e) {
    var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
        "No feedback with id " + e.getId() + " found");
    problemDetail.setTitle("No such feedback");
    problemDetail.setInstance(URI.create("/feedbacks/" + e.getId()));
    return problemDetail;
  }
}
