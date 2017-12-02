import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Launcher.
 */
public class Launcher extends Application
{
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("/GUI.fxml"));
        // TODO: Set defaults
        primaryStage.setScene(new Scene(root, 128, 665));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
