package backend;

public class test {
    public static void main(String[] args) {
        WarehouseController warehouseController = new WarehouseController();
        warehouseController.fillWarehouse();

        System.out.println(warehouseController.filter(Category.CUPS));
    }
}
