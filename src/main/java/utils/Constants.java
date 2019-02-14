package utils;

public final class Constants {

    public static final String CURRENT_SHOPPING_CART = "CURRENT_SHOPPING_CART";
    public static final int SHOPPING_CART_MAX_CAPACITY = 20;
    public static final int SHOPPING_CART_PRODUCT_MAX_COUNT = 10;

    public enum Cookie {

        SHOPPING_CART("SCC", 60 * 60 * 24 * 365);

        private final String name;
        private final int ttl;

        Cookie(String name, int ttl) {
            this.name = name;
            this.ttl = ttl;
        }

        public String getName() {
            return name;
        }

        public int getTtl() {
            return ttl;
        }
    }
}
