package com.stepaniuk.zrobleno.order.status;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {

  Optional<OrderStatus> findByName(OrderStatusName orderStatusName);

}