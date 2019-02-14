package model;

import static utils.Constants.SHOPPING_CART_PRODUCT_MAX_COUNT;

public class ShoppingCartItem {
    private int idProduct;
    private int count;

    public ShoppingCartItem(int idProduct, int count) throws ValidationException {
        this.idProduct = idProduct;
        increaseCount(count);
    }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "idProduct=" + idProduct +
                ", count=" + count +
                '}';
    }

    public void addProduct(int count) throws ValidationException {
        increaseCount(count);
    }

    public void removeProduct(int count) {
        this.count -= count;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public int getCount() {
        return count;
    }

    private boolean checkProductCount(int count) {
        return this.count + count <= SHOPPING_CART_PRODUCT_MAX_COUNT;
    }

    private void increaseCount(int count) throws ValidationException {
        if (checkProductCount(count)) {
            this.count += count;
        } else {
            throw new ValidationException();
        }
    }
}
