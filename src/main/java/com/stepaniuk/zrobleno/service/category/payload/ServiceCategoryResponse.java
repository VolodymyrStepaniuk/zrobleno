package com.stepaniuk.zrobleno.service.category.payload;

import com.stepaniuk.zrobleno.validation.service.ImageUrl;
import com.stepaniuk.zrobleno.validation.service.LongDescription;
import com.stepaniuk.zrobleno.validation.service.ShortDescription;
import com.stepaniuk.zrobleno.validation.service.Title;
import com.stepaniuk.zrobleno.validation.shared.Id;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ServiceCategoryResponse {
  @Id
  @NotNull
  private Long id;
  @Title
  @NotNull
  private String title;
  @ShortDescription
  @NotNull
  private String shortDescription;
  @LongDescription
  @NotNull
  private String longDescription;
  @NotNull
  private List<@ImageUrl String> imageUrls;
  @NotNull
  private final Instant createdAt;
  @NotNull
  private final Instant lastModifiedAt;
}
