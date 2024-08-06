package backend.service;

import backend.model.Agreement;
import backend.model.Client;
import backend.repository.AgreementRepository;
import backend.repository.ClientRepository;

import static InterfaceControllers.StartPoint.openSecondWindow;

public class ClientService {
    private final ClientValidator clientValidator;
    private final AgreementRepository agreementRepository;
    private final ClientRepository clientRepository;


    public ClientService(ClientValidator clientValidator, AgreementRepository agreementRepository, ClientRepository clientRepository) {
        this.clientValidator = clientValidator;
        this.agreementRepository = agreementRepository;
        this.clientRepository = clientRepository;
    }

    public Client saveClientData(String fio, String agreementNumber, String phoneNumber, String email) {
        if(!clientValidator.isDataValid(fio, agreementNumber, phoneNumber, email)) {
            return null;
        }

        Agreement agreement = agreementRepository.findAgreementByNumber(agreementNumber);

        if(agreement == null) {
            openSecondWindow("Не найден введенный договор. Проверьте введенные данные.",
                    "Нет такого договора в базе.");

            return null;
        }

        var client = new Client(null, fio, agreement, phoneNumber, email);

        return clientRepository.save(client);
    }
}
