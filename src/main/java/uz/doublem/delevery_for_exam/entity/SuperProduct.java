package uz.doublem.delevery_for_exam.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SuperProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "productId")
    private Product product;
    @ManyToMany
    @JoinTable(
            name = "SuperProduct_Combo",
            joinColumns = @JoinColumn(name = "superProductId"),
            inverseJoinColumns = @JoinColumn(name = "comboId")
    )
    private List<Combo> combo;
    private boolean isOptional;
    private boolean isActive;
}
