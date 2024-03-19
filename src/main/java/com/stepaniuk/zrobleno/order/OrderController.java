package com.stepaniuk.zrobleno.order;

import com.stepaniuk.zrobleno.order.payload.OrderCreateRequest;
import com.stepaniuk.zrobleno.order.payload.OrderResponse;
import com.stepaniuk.zrobleno.order.payload.OrderUpdateRequest;
import com.stepaniuk.zrobleno.order.status.OrderStatusName;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/orders", produces = "application/json")
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderCreateRequest request) {
    return new ResponseEntity<>(orderService.createOrder(request),
        HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
    return ResponseEntity.ok(orderService.getOrder(id));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id,
      @RequestBody OrderUpdateRequest request) {
    return ResponseEntity.ok(orderService.updateOrder(id, request));
  }

  @PostMapping("/{id}/cancel")
  public ResponseEntity<OrderResponse> cancelOrderById(@PathVariable Long id) {
    return ResponseEntity.ok(orderService.changeStatus(id, OrderStatusName.CANCELED));
  }

  @PostMapping("/{id}/confirm")
  public ResponseEntity<OrderResponse> confirmOrderById(@PathVariable Long id) {
    return ResponseEntity.ok(orderService.changeStatus(id, OrderStatusName.CONFIRMED));
  }

  @PostMapping("/{id}/in-progress")
  public ResponseEntity<OrderResponse> setOrderInProgressById(@PathVariable Long id) {
    return ResponseEntity.ok(orderService.changeStatus(id, OrderStatusName.IN_PROGRESS));
  }

  @PostMapping("/{id}/complete")
  public ResponseEntity<OrderResponse> completeOrderById(@PathVariable Long id) {
    return ResponseEntity.ok(orderService.changeStatus(id, OrderStatusName.COMPLETED));
  }

  @GetMapping("/v1")
  public ResponseEntity<Page<OrderResponse>> getAllOrders(Pageable pageable) {
    return ResponseEntity.ok(orderService.getAllOrders(pageable, null));
  }

  @GetMapping("/v2")
  public ResponseEntity<Page<OrderResponse>> getAllOrders(Pageable pageable, UUID ownerId) {
    return ResponseEntity.ok(orderService.getAllOrders(pageable, ownerId));
  }
}
