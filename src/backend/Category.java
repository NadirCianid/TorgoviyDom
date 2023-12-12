package backend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Category {
    ALL,
    COFFEE,
    CUPS,
    MILK;

    public static ObservableList<Category> getCategories() {
        ObservableList<Category> categories = FXCollections.observableArrayList();
        categories.addAll(Arrays.asList(Category.values()));

        return  categories;
    }
}
