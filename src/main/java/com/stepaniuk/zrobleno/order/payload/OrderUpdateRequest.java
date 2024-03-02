package com.stepaniuk.zrobleno.order.payload;

import com.stepaniuk.zrobleno.order.status.OrderStatusName;
import com.stepaniuk.zrobleno.validation.shared.Id;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderUpdateRequest {

  @Nullable
  private OrderStatusName status;

  @Nullable
  private List<@Id Long> serviceIds;

}
