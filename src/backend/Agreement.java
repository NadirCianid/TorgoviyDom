package backend;

public class Agreement {
    private String agreementNumber;
    private String companyName;

    public Agreement(String agreementNumber, String companyName) {
        this.agreementNumber = agreementNumber;
        this.companyName = companyName;
    }

    public String getAgreementNumber() {
        return agreementNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public String toString() {
        return "Agreement{" +
                "agreementNumber='" + agreementNumber + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
