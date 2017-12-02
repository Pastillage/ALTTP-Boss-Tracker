/**
 * SettingsController -> Now Settings due to changes lol.
 */
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController
        implements Initializable
{
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private ColorPicker colorpicker;

    @FXML
    private Text tbg;

    @FXML
    private Text tp;

    @FXML
    private TextField inputPollingRate;

    @FXML
    private Line line1;

    @FXML
    private Text tCM;

    @FXML
    private Line l2;

    @FXML
    private Text tep;

    @FXML
    private Text tdp;

    @FXML
    private Text tth;

    @FXML
    private Text tpod;

    @FXML
    private Text tsp;

    @FXML
    private Text tsw;

    @FXML
    private Text tt;

    @FXML
    private Text tip;

    @FXML
    private Text tmm;

    @FXML
    private Text ttr;

    @FXML
    private TextField maxEP;

    @FXML
    private TextField maxDP;

    @FXML
    private TextField maxTH;

    @FXML
    private TextField maxPD;

    @FXML
    private TextField maxSP;

    @FXML
    private TextField maxSW;

    @FXML
    private TextField maxTT;

    @FXML
    private TextField maxIP;

    @FXML
    private TextField maxMM;

    @FXML
    private TextField maxTR;

    @FXML
    private Line l3;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Platform.runLater(() -> colorpicker.setValue(Settings.getBgColor()));
        Platform.runLater(() -> updaterThread());
    }


    private void updaterThread()
    {
        Runnable r = () ->
        {
            while (true)
            {
                if (!colorpicker.getValue().equals(Settings.getBgColor()))
                {
                    Settings.setBgColor(colorpicker.getValue());
                }
                try
                {
                    Thread.sleep(Settings.getPollingRate());
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        Thread thread_updater = new Thread(r);
        thread_updater.setDaemon(true);
        thread_updater.start();
    }
}
