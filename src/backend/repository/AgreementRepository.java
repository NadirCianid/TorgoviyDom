package backend.repository;

import backend.model.client.Agreement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AgreementRepository {
    private final Connection conn;

    public AgreementRepository(Connection connection) {
        conn = connection;
    }

    public Agreement findAgreementByNumber(String agreementNumber) {
        Agreement agreement = null;
        String query = """
                select id, agreement_number, company_name, company_address, company_phone_number 
                from agreement where agreement_number = ?
                """;

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, agreementNumber);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String agreementNum = rs.getString("agreement_number");
                    String companyName = rs.getString("company_name");
                    String companyAddress = rs.getString("company_address");
                    String companyPhoneNumber = rs.getString("company_phone_number");

                    agreement = new Agreement(id, agreementNum, companyName, companyAddress, companyPhoneNumber);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agreement;
    }


}
