public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Produk: " + product.getName() + ", Jumlah: " + quantity + ", Total Harga: Rp " + (product.getPrice() * quantity);
    }
}
