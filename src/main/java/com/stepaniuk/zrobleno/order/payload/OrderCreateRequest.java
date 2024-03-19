package com.stepaniuk.zrobleno.order.payload;

import com.stepaniuk.zrobleno.validation.shared.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import java.util.UUID;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderCreateRequest {

  @NotNull
  private UUID ownerId;

  @NotNull
  @Id
  private Long serviceId;

  @Nullable
  private String comment;
}
