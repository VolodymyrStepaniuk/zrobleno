package com.stepaniuk.zrobleno.order.exception;

import com.stepaniuk.zrobleno.order.status.OrderStatusName;
import lombok.Getter;

/**
 * Exception thrown when status of order illegal.
 *
 * @see RuntimeException
 */
@Getter
public class IllegalOrderStatusException extends RuntimeException{
  private final OrderStatusName status;
  private final OrderStatusName previousStatus;
  private final Long id;

  public IllegalOrderStatusException(OrderStatusName status, OrderStatusName previousStatus,Long id){
    super("Illegal order status! From " + previousStatus +" to "+ status+" in order with id: "+id);
    this.status = status;
    this.previousStatus = previousStatus;
    this.id = id;
  }
}
