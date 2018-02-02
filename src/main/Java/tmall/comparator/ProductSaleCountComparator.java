package tmall.comparator;

import tmall.pojo.Product;

import java.util.Comparator;

public class ProductSaleCountComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        int value = p2.getSalesCount() - p1.getSalesCount();
        return value;
    }
}
