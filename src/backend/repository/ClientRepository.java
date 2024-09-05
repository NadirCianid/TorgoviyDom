package backend.repository;

import backend.model.client.Agreement;
import backend.model.client.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static InterfaceControllers.StartPoint.openSecondWindow;

public class ClientRepository {
    private final Connection conn;

    public ClientRepository(Connection conn) {
        this.conn = conn;
    }

    public Client save(Client client) {
        // Сначала проверяем, существует ли клиент с таким же номером телефона или электронной почтой
        String checkQuery = """
        SELECT c.id, c.fio, c.agreement, c.phone_number, c.email,
               a.agreement_number, a.company_name, a.company_address, a.company_phone_number
        FROM client c
        JOIN agreement a ON c.agreement = a.id
        WHERE c.phone_number = ? OR c.email = ?
        """;

        try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
            checkStmt.setString(1, client.getPhoneNumber());
            checkStmt.setString(2, client.getEmail());

            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    // Клиент уже существует, возвращаем существующего клиента
                    Long id = rs.getLong("id");
                    String fio = rs.getString("fio");
                    int agreementId = rs.getInt("agreement");
                    String phoneNumber = rs.getString("phone_number");
                    String email = rs.getString("email");

                    // Получаем данные соглашения
                    String agreementNumber = rs.getString("agreement_number");
                    String companyName = rs.getString("company_name");
                    String companyAddress = rs.getString("company_address");
                    String companyPhoneNumber = rs.getString("company_phone_number");

                    // Создаем объект Agreement
                    Agreement existingAgreement = new Agreement(
                            agreementId,
                            agreementNumber,
                            companyName,
                            companyAddress,
                            companyPhoneNumber
                    );

                    // Возвращаем существующего клиента с полными данными
                    return new Client(id, fio, existingAgreement, phoneNumber, email);
                }
            }
        } catch (SQLException e) {
            openSecondWindow("Ошибка при проверке существования пользователя: " + e.getMessage(),
                    "Ошибка сервера.");
            return null;
        }

        // Клиент не найден, вставляем нового клиента
        String insertQuery = """
        INSERT INTO client (fio, agreement, phone_number, email)
        VALUES (?, ?, ?, ?)
        RETURNING id
        """;

        try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
            insertStmt.setString(1, client.getFio());
            insertStmt.setInt(2, client.getAgreement().id());
            insertStmt.setString(3, client.getPhoneNumber());
            insertStmt.setString(4, client.getEmail());

            try (ResultSet rs = insertStmt.executeQuery()) {
                if (rs.next()) {
                    Long id = rs.getLong("id");

                    // Получаем только что вставленного клиента и его данные соглашения
                    String clientQuery = """
                    SELECT c.id, c.fio, c.agreement, c.phone_number, c.email,
                           a.agreement_number, a.company_name, a.company_address, a.company_phone_number
                    FROM client c
                    JOIN agreement a ON c.agreement = a.id
                    WHERE c.id = ?
                    """;

                    try (PreparedStatement clientStmt = conn.prepareStatement(clientQuery)) {
                        clientStmt.setLong(1, id);

                        try (ResultSet clientRs = clientStmt.executeQuery()) {
                            if (clientRs.next()) {
                                String fio = clientRs.getString("fio");
                                int agreementId = clientRs.getInt("agreement");
                                String phoneNumber = clientRs.getString("phone_number");
                                String email = clientRs.getString("email");

                                // Получаем данные соглашения
                                String agreementNumber = clientRs.getString("agreement_number");
                                String companyName = clientRs.getString("company_name");
                                String companyAddress = clientRs.getString("company_address");
                                String companyPhoneNumber = clientRs.getString("company_phone_number");

                                // Создаем объект Agreement
                                Agreement newAgreement = new Agreement(
                                        agreementId,
                                        agreementNumber,
                                        companyName,
                                        companyAddress,
                                        companyPhoneNumber
                                );

                                // Возвращаем нового клиента с полными данными
                                return new Client(id, fio, newAgreement, phoneNumber, email);
                            } else {
                                throw new SQLException("Не удалось получить только что вставленного клиента.");
                            }
                        }
                    }
                } else {
                    throw new SQLException("Не удалось вставить клиента, ID не получен.");
                }
            }
        } catch (SQLException e) {
            openSecondWindow("Ошибка при сохранении пользователя: " + e.getMessage(),
                    "Ошибка сервера.");
            return null;
        }
    }
}
