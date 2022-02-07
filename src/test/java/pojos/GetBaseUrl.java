package pojos;

public class GetBaseUrl {
    private String cartId;
    private int totalAti;
    private String shopperId;
    private String shopperEmail;
    private String auth_v;
    private String auth_key;
    private String auth_ts;
    private String auth_sign;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public int getTotalAti() {
        return totalAti;
    }

    public void setTotalAti(int totalAti) {
        this.totalAti = totalAti;
    }

    public String getShopperId() {
        return shopperId;
    }

    public void setShopperId(String shopperId) {
        this.shopperId = shopperId;
    }

    public String getShopperEmail() {
        return shopperEmail;
    }

    public void setShopperEmail(String shopperEmail) {
        this.shopperEmail = shopperEmail;
    }

    public String getAuth_v() {
        return auth_v;
    }

    public void setAuth_v(String auth_v) {
        this.auth_v = auth_v;
    }

    public String getAuth_key() {
        return auth_key;
    }

    public void setAuth_key(String auth_key) {
        this.auth_key = auth_key;
    }

    public String getAuth_ts() {
        return auth_ts;
    }

    public void setAuth_ts(String auth_ts) {
        this.auth_ts = auth_ts;
    }

    public String getAuth_sign() {
        return auth_sign;
    }

    public void setAuth_sign(String auth_sign) {
        this.auth_sign = auth_sign;
    }

    @Override
    public String toString() {
        return "GetBaseUrl{" +
                "cartId='" + cartId + '\'' +
                ", totalAti=" + totalAti +
                ", shopperId='" + shopperId + '\'' +
                ", shopperEmail='" + shopperEmail + '\'' +
                ", auth_v='" + auth_v + '\'' +
                ", auth_key='" + auth_key + '\'' +
                ", auth_ts='" + auth_ts + '\'' +
                ", auth_sign='" + auth_sign + '\'' +
                '}';
    }
}
