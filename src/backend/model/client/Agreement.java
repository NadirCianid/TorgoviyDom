package backend.model.client;

public record Agreement(int id, String agreementNumber, String companyName, String companyAddress, String companyPhoneNumber) {

    @Override
    public String toString() {
        return "Agreement{" +
                "  agreementNumber='" + agreementNumber + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", companyPhoneNumber='" + companyPhoneNumber + '\'' +
                '}';
    }
}
