package model;

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
        if (checkShoppingCartCapacity()) {
            product.addProduct(count);
            shoppingCartItems.add(product);
        } else {
            throw new ValidationException();
        }
    }

    public void removeProduct(Integer idProduct, int count){

    }

    public Collection<ShoppingCartItem> getItems(){
        return shoppingCartItems;
    }

    public int getTotalCount(){
        return shoppingCartItems.size();
    }

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


}
