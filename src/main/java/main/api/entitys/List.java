package main.api.entitys;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "list")
@ApiModel(description = "Class describes list of products in shop")
public class List {
    @ApiModelProperty(notes = "Unique identifier of the Product.",
            example = "1", required = true, position = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ApiModelProperty(notes = "Name of the list.",
            example = "Fruits", required = true, position = 1)
    @NotNull
    private String name;

    @ApiModelProperty(notes = "List of products.",
            example = "{}", required = true, position = 2)
    @OneToMany
    @Column(name = "product_list")
    private java.util.List<Product> productList;

    public List(String name, java.util.List<Product> productList) {
        this.name = name;
        this.productList = productList;
    }
}