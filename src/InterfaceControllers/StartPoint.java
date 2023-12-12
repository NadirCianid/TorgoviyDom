package InterfaceControllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StartPoint extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Получение FXMLLoader объекта  для перехода к новой сцене и доступа к котроллеру этой сцены
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("..//fxmls//logInPage.fxml")));


        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        stage.setResizable(false);

        stage.show();

        LogInPageController logInPageController = fxmlLoader.getController();
    }

    public static void main(String[] args) {
        launch();
    }
}
