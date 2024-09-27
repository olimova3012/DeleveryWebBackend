package uz.doublem.delevery_for_exam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;
    @CreationTimestamp
    private Timestamp orderDate;
    private double totalAmount;
    @Enumerated(EnumType.STRING)
    private StatusOrder status;
    @OneToOne
    private Address address;
    @ManyToOne
    @JoinColumn(name = "basketId")
    private Basket basket;
    @ManyToOne
    private Cupon cupons;
    @OneToOne
    private Payment payment;
}


