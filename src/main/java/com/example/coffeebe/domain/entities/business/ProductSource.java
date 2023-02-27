package com.example.coffeebe.domain.entities.business;

import com.example.coffeebe.domain.entities.BaseEntity;
import com.example.coffeebe.domain.entities.enums.ProductResourceState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@SuperBuilder
@Table(name = "product_source")
public class ProductSource extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "price")
  private Long price;

  @Column(name = "total_price")
  private Long totalPrice;

  @Column(name = "status")
  private Boolean status;

  @Column(name = "state")
  @Enumerated(EnumType.STRING)
  private ProductResourceState state;

}
