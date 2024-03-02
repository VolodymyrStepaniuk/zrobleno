package com.stepaniuk.zrobleno.service.payload;

import com.stepaniuk.zrobleno.validation.service.Description;
import com.stepaniuk.zrobleno.validation.service.ImageUrl;
import com.stepaniuk.zrobleno.validation.service.Title;
import com.stepaniuk.zrobleno.validation.shared.Id;
import java.math.BigDecimal;
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
public class ServiceUpdateRequest {

  @Id
  @Nullable
  private Long categoryId;

  @Title
  @Nullable
  private String title;

  @Description
  @Nullable
  private String description;

  @Nullable
  private Integer priority;

  @Nullable
  private List<@ImageUrl String> imageUrls;

  @Nullable
  private BigDecimal price;
}
