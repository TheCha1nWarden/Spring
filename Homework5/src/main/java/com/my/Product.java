package com.my;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Table;

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

    @Column(nullable = false)
    private int weight;

    public Product(String title) {
        this.title = title;
    }

}
