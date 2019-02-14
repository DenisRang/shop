package model;

import com.sun.istack.internal.Nullable;

import java.util.Collection;
import java.util.LinkedList;

import static utils.Constants.SHOPPING_CART_MAX_CAPACITY;

public class ShoppingCart {
    private Collection<ShoppingCartItem> shoppingCartItems;

    public ShoppingCart() {
        shoppingCartItems = new LinkedList<>();
    }

    void addProduct(int idProduct, int count) throws ValidationException {
        ShoppingCartItem product = getShoppingCartItem(idProduct);
        if (product == null) {
            if (checkShoppingCartCapacity()) {
                shoppingCartItems.add(new ShoppingCartItem(idProduct, count));
            } else {
                throw new ValidationException();
            }
        } else {
            product.addProduct(count);
        }
    }

    public void removeProduct(Integer idProduct, int count) {
        ShoppingCartItem product = getShoppingCartItem(idProduct);
        if (product.getCount() > count) {
            product.removeProduct(count);
        } else {
            shoppingCartItems.remove(product);
        }
    }

    public Collection<ShoppingCartItem> getItems() {
        return shoppingCartItems;
    }

    public int getTotalCount() {
        int totalCount = 0;
        for (ShoppingCartItem product : shoppingCartItems) {
            totalCount += product.getCount();
        }
        return totalCount;
    }

    @Nullable
    private ShoppingCartItem getShoppingCartItem(int idProduct) {
        ShoppingCartItem result = null;
        for (ShoppingCartItem product : shoppingCartItems) {
            if (product.getIdProduct() == idProduct) {
                result = product;
            }
        }
        return result;
    }

    private boolean checkShoppingCartCapacity() {
        return shoppingCartItems.size() < SHOPPING_CART_MAX_CAPACITY;
    }

    public String shoppingCartToString(){
        return "ShoppingCart{" +
                "shoppingCartItems=" + shoppingCartItems +
                '}';
    };

    public static ShoppingCart shoppingCartFromString(String cookieValue){

    }

}
