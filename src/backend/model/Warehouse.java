package backend.model;

import java.util.Map;

public class Warehouse {
    private Long id;
    private String title;
    private String address;
    private Integer capacity;
    private Map<Long, Integer> productsStorage; //productId -> amount

    public Warehouse(Long id, String title, String address, Integer capacity, Map<Long, Integer> productsStorage) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.capacity = capacity;
        this.productsStorage = productsStorage;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Map<Long, Integer> getProductsStorage() {
        return productsStorage;
    }

    public void updateProductStorage(Map<Long, Integer> warehouseStorage) {
        productsStorage = warehouseStorage;
    }
}
