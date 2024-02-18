package com.stepaniuk.zrobleno.price;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import lombok.ToString;
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type",
    include = JsonTypeInfo.As.EXISTING_PROPERTY
)
@JsonSubTypes({
    @Type(
        name = FixedPrice.TYPE,
        value = FixedPrice.class
    )
})
@EqualsAndHashCode
@ToString
public abstract class Price {
  public abstract String getType();
}
