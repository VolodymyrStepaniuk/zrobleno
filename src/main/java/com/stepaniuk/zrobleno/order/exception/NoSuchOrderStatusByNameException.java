package com.stepaniuk.zrobleno.order.exception;

import com.stepaniuk.zrobleno.order.status.OrderStatusName;
import lombok.Getter;

/**
 * Exception thrown when no order status with given name exists.
 *
 * @see RuntimeException
 */
@Getter
public class NoSuchOrderStatusByNameException extends RuntimeException {

  private final OrderStatusName name;

  public NoSuchOrderStatusByNameException(OrderStatusName name) {
    super("No such order status with name: " + name);
    this.name = name;
  }

}
