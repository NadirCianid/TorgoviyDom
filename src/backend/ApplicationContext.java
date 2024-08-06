package backend;

import backend.repository.AgreementRepository;
import backend.repository.ClientRepository;
import backend.repository.DataController;
import backend.service.ClientService;
import backend.service.ClientValidator;

import java.sql.SQLException;

public class ApplicationContext {
    private DataController dataController;
    private ClientService clientService;

    public ApplicationContext() throws DBInitException {
        initDataController();
        initClientService();
    }

    private void initDataController() throws DBInitException {
        dataController = new DataController();
        dataController.initializeDataBase();
    }

    private void initClientService() {
        ClientValidator clientValidator = new ClientValidator();
        ClientRepository clientRepository = new ClientRepository(dataController.getConn());
        AgreementRepository agreementRepository = new AgreementRepository(dataController.getConn());

        clientService  = new ClientService(clientValidator, agreementRepository, clientRepository);
    }

    public ClientService getClientService() {
        return clientService;
    }
}
