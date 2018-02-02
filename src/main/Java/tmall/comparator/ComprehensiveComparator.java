package tmall.comparator;

import tmall.pojo.Product;

import java.util.Comparator;

public class ComprehensiveComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        int value = p2.getSalesCount()*p2.getReviewCount()-p1.getReviewCount()*p1.getSalesCount();
        return value;
    }
}
