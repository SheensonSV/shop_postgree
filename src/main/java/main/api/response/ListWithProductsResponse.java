package main.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.api.entitys.Product;

import java.util.List;

@Data
@AllArgsConstructor
public class ListWithProductsResponse {
    private long id;
    private String name;
    private List<Product> productList;
    private int totalKCal = 0;

    private int sumKCal() {
        if (!productList.isEmpty()) {
            int summ = 0;
            for (Product product : productList) {
                summ += product.getKcal();
            }
            return summ;
        }
        return 0;
    }

    public ListWithProductsResponse(long id, String name, List<Product> productList) {
        this.id = id;
        this.name = name;
        this.productList = productList;
        this.totalKCal = sumKCal();
    }
}
