package uz.doublem.delevery_for_exam.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Combo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
    @CreationTimestamp
    private Timestamp createdAt;
    @Builder.Default
    private boolean isActive = true;
    @ManyToOne
    @JoinColumn(name = "productImagesId")
    private ProductImages productImages;

}
