/**
 * SettingsController -> Now Settings due to changes lol.
 */
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class SettingsController
        implements Initializable
{
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private ColorPicker colorpicker;

    @FXML
    private TextField inputPollingRate;

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
    private TextField off_c_x;

    @FXML
    private TextField off_c_y;

    @FXML
    private TextField off_l_x;

    @FXML
    private TextField off_l_y;

    @FXML
    private TextField off_m_x;

    @FXML
    private TextField off_m_y;

    @FXML
    private TextField off_p_x;

    @FXML
    private TextField off_p_y;

    @FXML
    private CheckBox checkbox_labels;

    @FXML
    private CheckBox checkbox_chests;

    @FXML
    private CheckBox checkbox_medals;

    @FXML
    private CheckBox checkbox_prizes;

    @FXML
    private TextField opacity_boss;

    @FXML
    private TextField boss_gui_x;

    @FXML
    private TextField boss_gui_y;

    @FXML
    private TextField boss_gui_curr_x;

    @FXML
    private TextField boss_gui_curr_y;

    @FXML
    private Button b_use_current_gui_size;

    @FXML
    private TextField pathSaveFolder;

    @FXML
    private Button bBrowseSaveLocation;

    @FXML
    private TextField inputSRAMrate;

    @FXML
    private CheckBox checkboxUseMostRecent;

    @FXML
    private TextField pathCustomSaveLocation;

    @FXML
    private Button bBrowseCustomSave;

    @FXML
    private Button bClosewoSaving;

    @FXML
    private Button bSaveSettings;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Platform.runLater(() -> settingsPlacer());
        Platform.runLater(() -> updaterThread());
    }

    public void settingsPlacer()
    {
        colorpicker.setValue(Settings.getBgColor());

        pathSaveFolder.setDisable(true);
        pathCustomSaveLocation.setDisable(true);
        bBrowseSaveLocation.setDisable(true);
        bBrowseCustomSave.setDisable(true);
        checkboxUseMostRecent.setDisable(true);

        inputPollingRate.setText(Settings.getPollingRate() + "");

        maxEP.setText(Settings.getMax_chest_values()[0] + "");
        maxDP.setText(Settings.getMax_chest_values()[1] + "");
        maxTH.setText(Settings.getMax_chest_values()[2] + "");
        maxPD.setText(Settings.getMax_chest_values()[3] + "");
        maxSP.setText(Settings.getMax_chest_values()[4] + "");
        maxSW.setText(Settings.getMax_chest_values()[5] + "");
        maxTT.setText(Settings.getMax_chest_values()[6] + "");
        maxIP.setText(Settings.getMax_chest_values()[7] + "");
        maxMM.setText(Settings.getMax_chest_values()[8] + "");
        maxTR.setText(Settings.getMax_chest_values()[9] + "");

        checkbox_chests.setSelected(Settings.chests);
        checkbox_labels.setSelected(Settings.labels);
        checkbox_medals.setSelected(Settings.medallions);
        checkbox_prizes.setSelected(Settings.prizes);

        boss_gui_curr_x.setText(Settings.GUI.getWidth() - 16 + "");
        boss_gui_curr_y.setText(Settings.GUI.getHeight() - 39 + "");
        boss_gui_x.setText(Settings.layout_size_gui.x + "");
        boss_gui_y.setText(Settings.layout_size_gui.y + "");

        opacity_boss.setText(Settings.getOpacity()*100 + "");

        off_c_x.setText((int) Settings.offset_chest.x + "");
        off_c_y.setText((int) Settings.offset_chest.y + "");
        off_l_x.setText((int) Settings.offset_label.x + "");
        off_l_y.setText((int) Settings.offset_label.y + "");
        off_m_x.setText((int) Settings.offset_medallion.x + "");
        off_m_y.setText((int) Settings.offset_medallion.y + "");
        off_p_x.setText((int) Settings.offset_prizes.x + "");
        off_p_y.setText((int) Settings.offset_prizes.y + "");
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

                if (Settings.GUI.getWidth() - 16 != Double.parseDouble(boss_gui_curr_x.getText()))
                {
                    boss_gui_curr_x.setText(Settings.GUI.getWidth() - 16 + "");
                }

                if (Settings.GUI.getHeight() - 39 != Double.parseDouble(boss_gui_curr_y.getText()))
                {
                    boss_gui_curr_y.setText(Settings.GUI.getHeight() - 39 + "");
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

    public void buttonGUIcurrentSize()
    {
        boss_gui_x.setText(boss_gui_curr_x.getText());
        boss_gui_y.setText(boss_gui_curr_y.getText());
    }

    public String generateChestMaximaString()
    {
        // Delimit with ;
        StringBuilder str = new StringBuilder();

        str.append(sanatizeMaxChest(maxEP.getText()));          str.append(";");
        str.append(sanatizeMaxChest(maxDP.getText()));          str.append(";");
        str.append(sanatizeMaxChest(maxTH.getText()));          str.append(";");
        str.append(sanatizeMaxChest(maxPD.getText()));          str.append(";");
        str.append(sanatizeMaxChest(maxSP.getText()));          str.append(";");
        str.append(sanatizeMaxChest(maxSW.getText()));          str.append(";");
        str.append(sanatizeMaxChest(maxTT.getText()));          str.append(";");
        str.append(sanatizeMaxChest(maxIP.getText()));          str.append(";");
        str.append(sanatizeMaxChest(maxMM.getText()));          str.append(";");
        str.append(sanatizeMaxChest(maxTR.getText()));          str.append(";");

        return str.toString();
    }

    public int sanatizeMaxChest(String in)
    {
        // Means input is malformed.
        if (!in.matches("^[0-9 ]+$"))
        {
            return 6;
        }

        int result = Integer.parseInt(in);
        if (result > 6)
        {
            return 6;
        }
        else if (result < 0)
        {
            return 0;
        }
        return result;
    }

    public void buttonSave()
    {
        Properties settings = new Properties();
        OutputStream output = null;

        try
        {
            output = new FileOutputStream("settings.alttp");

            settings.put("BGC", colorpicker.getValue() + "");
            settings.put("POLLING_RATE", Settings.getPollingRate() + "");
            settings.put("CHEST_MAX", generateChestMaximaString());
            settings.put("OPACITY", opacity_boss.getText());
            settings.put("SIZE_GUI_X", boss_gui_x.getText());
            settings.put("SIZE_GUI_Y", boss_gui_y.getText());
            settings.put("CHEST_OFFSET_X", off_c_x.getText());
            settings.put("CHEST_OFFSET_Y", off_c_y.getText());
            settings.put("LABEL_OFFSET_X", off_l_x.getText());
            settings.put("LABEL_OFFSET_Y", off_l_y.getText());
            settings.put("MEDAL_OFFSET_X", off_m_x.getText());
            settings.put("MEDAL_OFFSET_Y", off_m_y.getText());
            settings.put("PRIZE_OFFSET_X", off_p_x.getText());
            settings.put("PRIZE_OFFSET_Y", off_p_y.getText());
            settings.put("LABELS", checkbox_labels.isSelected() ? "1" : "0");
            settings.put("CHESTS", checkbox_chests.isSelected() ? "1" : "0");
            settings.put("MEDALS", checkbox_medals.isSelected() ? "1" : "0");
            settings.put("PRIZES", checkbox_prizes.isSelected() ? "1" : "0");

            // TODO: SNES SAVE LOCATION

            settings.store(output, null);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (output != null)
            {
                try
                {
                    output.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        close();
    }

    public void close()
    {
        Stage stage = (Stage) bClosewoSaving.getScene().getWindow();
        stage.close();
    }
}
