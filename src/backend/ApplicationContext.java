package backend;

import backend.repository.AgreementRepository;
import backend.repository.CategoryRepository;
import backend.repository.ClientRepository;
import backend.repository.DataController;
import backend.service.CategoryService;
import backend.service.ClientService;
import backend.service.ClientValidator;

import java.sql.SQLException;

public class ApplicationContext {
    private DataController dataController;
    private ClientService clientService;
    private CategoryService categoryService;

    public ApplicationContext() throws DBInitException {
        initDataController();
        initClientService();
        initCategoryService();
    }

    private void initCategoryService() {
        CategoryRepository categoryRepository = new CategoryRepository(dataController.getConn());

        categoryService = new CategoryService(categoryRepository);
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

    public CategoryService getCategoryService() {
        return categoryService;
    }
}
