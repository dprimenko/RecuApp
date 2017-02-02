package es.dpinfo.recuapp.models;

import java.util.Comparator;

/**
 * Created by dprimenko on 2/02/17.
 */
public class Product implements Comparable {

    private String mCategory;
    private String mNombre;
    private Double mPrice;
    private int mImportance;

    public static final int VERY_IMPORTANT = 3;
    public static final int MEDIUM_IMPORTANT = 2;
    public static final int LOW_IMPORTANT = 1;

    public int getmImportance() {
        return mImportance;
    }

    public void setmImportance(int mImportance) {
        this.mImportance = mImportance;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getmNombre() {
        return mNombre;
    }

    public void setmNombre(String mNombre) {
        this.mNombre = mNombre;
    }

    public Double getmPrice() {
        return mPrice;
    }

    public void setmPrice(Double mPrice) {
        this.mPrice = mPrice;
    }

    public Product(String mCategory, String mNombre, Double mPrice, int importance) {
        this.mCategory = mCategory;
        this.mNombre = mNombre;
        this.mPrice = mPrice;
        this.mImportance = importance;
    }

    public Product() {
    }


    @Override
    public boolean equals(Object obj) {

        boolean result = false;

        if (obj instanceof Product) {

            Product product = (Product) obj;

            if (this.getmNombre().equalsIgnoreCase(product.getmNombre())) {
                result = true;
            }
        }

        return result;
    }

    public static Comparator<Product> priceComparator = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return (int) (o1.getmPrice() - o2.getmPrice());
        }
    };

    @Override
    public int compareTo(Object o) {

        Product productToCompare = (Product)o;
        return this.getmNombre().compareToIgnoreCase(productToCompare.getmNombre());
    }
}
