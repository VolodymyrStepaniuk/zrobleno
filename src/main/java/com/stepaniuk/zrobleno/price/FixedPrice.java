package com.stepaniuk.zrobleno.price;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@JsonTypeName(FixedPrice.TYPE)
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FixedPrice extends Price{
  public static final String TYPE = "fixed";

  private final BigDecimal value;

  @ConstructorProperties({"value"})
  public FixedPrice(BigDecimal value) {
    this.value = value;
  }

  public String getType() {
    return TYPE;
  }
}
