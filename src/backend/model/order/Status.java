package backend.model.order;

public enum Status {
    PENDING(1, "Pending"),
    PROCESSING(2, "Processing"),
    SHIPPED(3, "Shipped"),
    DELIVERED(4, "Delivered"),
    CANCELLED(5, "Cancelled");

    private final int id;
    private final String name;

    Status(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Method to get Status enum by id
    public static Status getById(int id) {
        for (Status status : values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid Status ID: " + id);
    }

    @Override
    public String toString() {
        return name;
    }
}
