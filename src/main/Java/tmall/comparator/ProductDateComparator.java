package tmall.comparator;

import tmall.pojo.Product;

import java.util.Comparator;

public class ProductDateComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        int value = p1.getCreateDate().compareTo(p2.getCreateDate());
        return value;
    }
}
