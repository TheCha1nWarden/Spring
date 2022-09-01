package com.my;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "findAllProducts", query = "Select p from Product p"),
        @NamedQuery(name = "deleteProductById", query = "delete from Product p where p.id = :id")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private int cost;

    public Product(String title, int cost) {
        this.title = title;
        this.cost = cost;
    }

}
