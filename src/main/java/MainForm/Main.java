package MainForm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application { ;
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainFormStyle.fxml"));
        AnchorPane view = loader.load();
        ((MainViewFxml) loader.getController()).setMain(this, loader);
        MainView.setAnchorPane(view);
        Pane pane = new Pane(view);
        StackPane root = new StackPane(pane);
        MainViewFxml.classRootInitialize(root);
        MainViewFxml.MethodRootInitialize(root);
        MainViewFxml.BodyRootInitialize(root);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        //primaryStage.getIcons().add(new Image("../icons/icon.ico"));
        primaryStage.setTitle("UMl to CODE");
        primaryStage.setResizable(false);
        primaryStage.toBack();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
