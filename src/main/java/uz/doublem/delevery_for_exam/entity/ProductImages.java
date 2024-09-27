package uz.doublem.delevery_for_exam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductImages {
    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    private String id= UUID.randomUUID().toString();
    @OneToMany(mappedBy = "id")
    private List<Product> product;
    @OneToMany(mappedBy = "id")
    private List<Combo> combos;
    private String attachmentName;
    private String prefix;
    private String attachmentSize;
    @CreationTimestamp
    private Timestamp createdAt;
}
