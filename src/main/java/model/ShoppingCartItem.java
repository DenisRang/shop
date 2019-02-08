package model;

import static utils.Constants.SHOPPING_CART_PRODUCT_MAX_COUNT;

public class ShoppingCartItem {
    private int idProduct;
    private int count;

    public ShoppingCartItem(int idProduct, int count) {
        this.idProduct = idProduct;
        this.count = count;
    }

    public void addProduct(int count) throws ValidationException {
        if (checkProductCount(count)) {
            this.count += count;
        } else {
            throw new ValidationException();
        }
    }

    public int getIdProduct() {
        return idProduct;
    }

    private boolean checkProductCount(int count) {
        return this.count + count <= SHOPPING_CART_PRODUCT_MAX_COUNT;
    }
}
