package backend;

public class Client {
    private String fio;
    private Agreement agreement;
    public Basket basket;

    public Client(String fio, Agreement agreement) {
        this.fio = fio;
        this.agreement = agreement;
        basket = new Basket();
    }

    public String getFio() {
        return fio;
    }

    public Agreement getAgreement() {
        return agreement;
    }

    @Override
    public String toString() {
        return "Client{" +
                "fio='" + fio + '\'' +
                ", agreement=" + agreement +
                '}';
    }
}
