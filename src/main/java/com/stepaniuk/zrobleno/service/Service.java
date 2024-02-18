package com.stepaniuk.zrobleno.service;

import com.stepaniuk.zrobleno.price.Price;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "services")
public class Service {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "category_id", nullable = false)
  private Long categoryId;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "short_description", nullable = false)
  private String shortDescription;

  @Column(name = "long_description", nullable = false, columnDefinition = "text")
  private String longDescription;

  @Type(ListArrayType.class)
  @Column(name = "image_urls", columnDefinition = "text[]", nullable = false)
  private List<String> imageUrls;

  @Type(JsonType.class)
  @Column(name = "price", nullable = false, columnDefinition = "jsonb")
  private Price price;

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreatedDate
  private Instant createdAt;

  @Column(name = "last_modified_at", nullable = false)
  @LastModifiedDate
  private Instant lastModifiedAt;

  @Override
  public final boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    Class<?> oEffectiveClass = o instanceof HibernateProxy
        ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
        : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy
        ? ((HibernateProxy) this).getHibernateLazyInitializer()
        .getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) {
      return false;
    }
    Service service = (Service) o;
    return getId() != null && Objects.equals(getId(), service.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
        .getPersistentClass().hashCode() : getClass().hashCode();
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" +
        "id = " + id + ", " +
        "categoryId = " + categoryId + ", " +
        "title = " + title + ", " +
        "imageUrls = " + imageUrls + ", " +
        "price = " + price + ", " +
        "createdAt = " + createdAt + ", " +
        "lastModifiedAt = " + lastModifiedAt + ")";
  }
}
