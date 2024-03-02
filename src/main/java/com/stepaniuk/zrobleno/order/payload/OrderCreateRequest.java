package com.stepaniuk.zrobleno.order.payload;

import com.stepaniuk.zrobleno.validation.shared.Id;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderCreateRequest {

  @NotNull
  private UUID ownerId;

  @NotNull
  private List<@Id Long> serviceIds;

}
