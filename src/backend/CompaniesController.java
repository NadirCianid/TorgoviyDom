package backend;

import java.util.ArrayList;
import java.util.List;

public class CompaniesController {
    private List<Agreement> agreementList = new ArrayList<>();

    public void fillAgreementList() {
        agreementList.add(new Agreement("АВ1234567891234567", "ИП \"Варим вкусный кофе\""));
        agreementList.add(new Agreement("ВВ1111111111111111", "ИП \"Варим очень вкусный кофе\""));
        agreementList.add(new Agreement("ВК1234123412341234", "ИП \"Варим самый вкусный кофе\""));
    }

    public Agreement searchAgreement(String numberToSearch) {
        return agreementList.stream().filter(agreement ->
                agreement.getAgreementNumber().equals(numberToSearch)).
                findAny().
                orElse(null);
    }
}
