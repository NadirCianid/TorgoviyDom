package backend.model;

public class Agreement {
    private int id;
    private String agreementNumber;
    private String companyName;
    private String companyAddress;
    private String companyPhoneNumber;

    // Constructors, getters, and setters
    public Agreement(int id, String agreementNumber, String companyName, String companyAddress, String companyPhoneNumber) {
        this.id = id;
        this.agreementNumber = agreementNumber;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyPhoneNumber = companyPhoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getAgreementNumber() {
        return agreementNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getCompanyPhoneNumber() {
        return companyPhoneNumber;
    }

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
