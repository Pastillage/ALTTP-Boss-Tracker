import Tracker.Settings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Launcher.
 */
public class Launcher extends Application
{
    public void start(Stage primaryStage) throws Exception
    {
        // Load Tracker.Settings from .INI
        Settings.LoadSettings();

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("/GUI.fxml"));
        primaryStage.setTitle("ALTTP Boss Tracker v1.1");
        primaryStage.getIcons().add(new Image(getClass().getResource("decals/compass.png").toString()));
        primaryStage.setScene(new Scene(root, Settings.layout_size_gui.x, Settings.layout_size_gui.y));
        Settings.GUI = primaryStage;
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
