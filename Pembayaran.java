public abstract class Pembayaran {
    protected String id; // ID pembayaran

    public Pembayaran(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract String getJenisPembayaran();
    public abstract void prosesPembayaran();
}
