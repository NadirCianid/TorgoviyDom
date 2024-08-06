package backend.model;

public class Client {
    private final Long id;
    private final String fio;
    private final Agreement agreement;
    private final String phoneNumber;
    private final String email;

    public Basket basket;

    public Client(Long id, String fio, Agreement agreement, String phoneNumber, String email) {
        this.id = id;
        this.fio = fio;
        this.agreement = agreement;
        this.phoneNumber = phoneNumber;
        this.email = email;

        basket = new Basket();
    }

    public String getFio() {
        return fio;
    }

    public Agreement getAgreement() {
        return agreement;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Client{" +
                "fio='" + fio + '\'' +
                ", agreement=" + agreement +
                '}';
    }
}
