package backend;

import backend.repository.AgreementRepository;
import backend.repository.CategoryRepository;
import backend.repository.ClientRepository;
import backend.repository.DataController;
import backend.repository.ProductRepository;
import backend.repository.WarehouseRepository;
import backend.service.CategoryService;
import backend.service.ClientService;
import backend.service.ClientValidator;
import backend.service.ProductService;
import backend.service.WarehouseService;

public class ApplicationContext {
    private DataController dataController;
    private ClientService clientService;
    private CategoryService categoryService;
    private ProductService productService;
    private WarehouseService warehouseService;

    public ApplicationContext() throws DBInitException {
        initDataController();
        initClientService();
        initCategoryService();
        initWarehouseService();
        initProductService();
    }

    private void initWarehouseService() {
        WarehouseRepository warehouseRepository = new WarehouseRepository(dataController.getConn());

        warehouseService = new WarehouseService(warehouseRepository);
    }

    private void initProductService() {
        ProductRepository productRepository = new ProductRepository(dataController.getConn());

        productService = new ProductService(productRepository, warehouseService);
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

    public ProductService getProductService() {return productService;}

    public WarehouseService getWarehouseService() {
        return warehouseService;
    }
}
