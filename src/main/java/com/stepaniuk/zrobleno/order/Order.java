package com.stepaniuk.zrobleno.order;

import com.stepaniuk.zrobleno.order.status.OrderStatus;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
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
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_id_gen")
  @SequenceGenerator(name = "orders_id_gen", sequenceName = "orders_id_seq", allocationSize = 1)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "owner_id", nullable = false)
  private UUID ownerId;

  @ManyToOne
  @JoinColumn(name = "status_id")
  private OrderStatus status;

  @Type(ListArrayType.class)
  @Column(name = "service_ids", columnDefinition = "bigint[]", nullable = false)
  private List<Long> serviceIds;

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
    Order order = (Order) o;
    return getId() != null && Objects.equals(getId(), order.getId());
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
        "ownerId = " + ownerId + ", " +
        "status = " + status + ", " +
        "serviceIds = " + serviceIds + ", " +
        "createdAt = " + createdAt + ", " +
        "lastModifiedAt = " + lastModifiedAt + ")";
  }
}
