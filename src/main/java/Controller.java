import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Controller.
 */
public class Controller implements Initializable
{

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private MenuBar menubar;

    @FXML
    private GridPane gridpane;

    @FXML
    private ImageView mMM;

    @FXML
    private ImageView mTR;

    @FXML
    private ImageView cEP;

    @FXML
    private ImageView cDP;

    @FXML
    private ImageView cTH;

    @FXML
    private ImageView cPD;

    @FXML
    private ImageView cSP;

    @FXML
    private ImageView cSW;

    @FXML
    private ImageView cTT;

    @FXML
    private ImageView cIP;

    @FXML
    private ImageView cMM;

    @FXML
    private ImageView cTR;

    @FXML
    private ImageView tEP;

    @FXML
    private ImageView tDP;

    @FXML
    private ImageView tTH;

    @FXML
    private ImageView tPD;

    @FXML
    private ImageView tSP;

    @FXML
    private ImageView tSW;

    @FXML
    private ImageView tTT;

    @FXML
    private ImageView tIP;

    @FXML
    private ImageView tMM;

    @FXML
    private ImageView tTR;

    @FXML
    private ImageView pEP;

    @FXML
    private ImageView pDP;

    @FXML
    private ImageView pTH;

    @FXML
    private ImageView pPD;

    @FXML
    private ImageView pSP;

    @FXML
    private ImageView pSW;

    @FXML
    private ImageView pTT;

    @FXML
    private ImageView pIP;

    @FXML
    private ImageView pMM;

    @FXML
    private ImageView pTR;

    @FXML
    private Text kEP;

    @FXML
    private Text kDP;

    @FXML
    private Text kTH;

    @FXML
    private Text kPD;

    @FXML
    private Text kSP;

    @FXML
    private Text kSW;

    @FXML
    private Text kTT;

    @FXML
    private Text kIP;

    @FXML
    private Text kMM;

    @FXML
    private Text kTR;

    @FXML
    private Text kAGA;

    private int currMM = 0;
    private int currTR = 0;
    private Color currBGColour;
    // Defaults: EP:3 DP:2 TH:2 PD:5 SP:6 SW:2 TT:4 IP:3 MM:2 TR:5
    private int[] chests = Settings.getMax_chest_values();

    HashMap<String, ImageView> lookupTextImageView = new HashMap<>();
    HashMap<String, ImageView> lookupPrizeImageView = new HashMap<>();

    /**
     * Runs upon first launch, handles initalization of everything on the GUI.
     * @param location URL
     * @param resources Resources
     */
    public void initialize(URL location, ResourceBundle resources)
    {
        initHashMaps();
        Platform.runLater(() -> setBGcolor(Color.BLACK));
        Platform.runLater(() -> fillGridpane());
        Platform.runLater(() -> setDefaultBosses());
        Platform.runLater(() -> setBigKeys("decals/bigkeyBack.png"));
        Platform.runLater(() -> setSmallKeys("decals/smallkeys/key"));
        Platform.runLater(() -> changeListenerThread());
    }

    // TODO: SRAM but for that I need the boss location flags.
    public void DaemonSRAM()
    {
        Runnable r = () ->
        {
            // Get SRAM file to read.

            // Read Relevant memory locations

            // Use them.
        };
        Thread tSRAM = new Thread(r);
        tSRAM.setDaemon(true);
        tSRAM.start();
    }

    public void initHashMaps()
    {
        initTextHM();
        initPrizeHM();
        InitPrizeValues();
    }

    public void initTextHM()
    {
        lookupTextImageView.put("ep", tEP);
        lookupTextImageView.put("dp", tDP);
        lookupTextImageView.put("th", tTH);
        lookupTextImageView.put("pd", tPD);
        lookupTextImageView.put("sp", tSP);
        lookupTextImageView.put("sw", tSW);
        lookupTextImageView.put("tt", tTT);
        lookupTextImageView.put("ip", tIP);
        lookupTextImageView.put("mm", tMM);
        lookupTextImageView.put("tr", tTR);
    }

    public void initPrizeHM()
    {
        lookupPrizeImageView.put("ep", pEP);
        lookupPrizeImageView.put("dp", pDP);
        lookupPrizeImageView.put("th", pTH);
        lookupPrizeImageView.put("pd", pPD);
        lookupPrizeImageView.put("sp", pSP);
        lookupPrizeImageView.put("sw", pSW);
        lookupPrizeImageView.put("tt", pTT);
        lookupPrizeImageView.put("ip", pIP);
        lookupPrizeImageView.put("mm", pMM);
        lookupPrizeImageView.put("tr", pTR);

    }

    public void changeListenerThread()
    {
        Runnable r = () ->
        {
            while (true)
            {
                if (!Settings.getBgColor().equals(currBGColour))
                {
                    setBGcolor(Settings.getBgColor());
                }

                try
                {
                    // The +100l is to prevent a situation where an infinite update loop happens.
                    Thread.sleep(Settings.getPollingRate() + 100l);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

            }
        };
        Thread t_change_listener = new Thread(r);
        t_change_listener.setDaemon(true);
        t_change_listener.start();
    }

    /**
     * Fills the gridpane with ImageViews.
     */
    public void fillGridpane()
    {
        for (int c = 0; c < 12; c++)
        {
            for (int i = 0; i < 12; i++)
            {
                ImageView img = new ImageView();
                img.setOpacity(Settings.getOpacity());
                gridpane.add(img, c, i);
            }
        }
    }

    /**
     * Sets the background color of the anchorpane, can be expanded upon.
     */
    public void setBGcolor(Color colour)
    {
        anchorpane.setBackground(new Background(new BackgroundFill(colour, CornerRadii.EMPTY, Insets.EMPTY)));
        currBGColour = colour;
    }

    /**
     * Sets default locations of bosses, can be expanded by using a save feature.
     */
    public void setDefaultBosses()
    {
        setBoss("boss/ep.png", 0,0);
        setBoss("boss/dp.png", 1,0);
        setBoss("boss/th.png", 2,0);
        setBoss("boss/pd.png", 3,0);
        setBoss("boss/sp.png", 4,0);
        setBoss("boss/sw.png", 5,0);
        setBoss("boss/tt.png", 6,0);
        setBoss("boss/ip.png", 7,0);
        setBoss("boss/mm.png", 8,0);
        setBoss("boss/tr.png", 9,0);
        setBoss("boss/aga.png", 10,0);
    }

    public void setBigKeys(String img_loc)
    {
        for (int c = 0; c < 11; c++)
        {
            ImageView img = (ImageView) getNodeByRowColumnIndex(c, 1, gridpane);
            img.setImage(new Image(getClass().getResource(img_loc).toString()));

            img.setOnMouseClicked(event ->
            {
                if (img.getOpacity() != 1.0d)
                {
                    img.setOpacity(1.0d);
                }
                else
                {
                    img.setOpacity(Settings.getOpacity());
                }
            });
        }
    }

    public void setSmallKeys(String img_loc)
    {
        for (int c = 0; c < 11; c++)
        {
            ImageView img;
            img = (ImageView) getNodeByRowColumnIndex(c, 2, gridpane);

            img.setImage(new Image(getClass().getResource(img_loc + c + ".png").toString()));

            setClickEvent(c, img);
        }
        setKeyText();
    }

    public void setClickEvent(int flag, ImageView img)
    {
        switch (flag)
        {
            case 0 : img.setOnMouseClicked(event -> Keysanity.getINSTANCE().GT(img, kEP)); break;
            case 1 : img.setOnMouseClicked(event -> Keysanity.getINSTANCE().DP(img, kDP)); break;
            case 2 : img.setOnMouseClicked(event -> Keysanity.getINSTANCE().TH(img, kTH)); break;
            case 3 : img.setOnMouseClicked(event -> Keysanity.getINSTANCE().PD(img, kPD)); break;
            case 4 : img.setOnMouseClicked(event -> Keysanity.getINSTANCE().SP(img, kSP)); break;
            case 5 : img.setOnMouseClicked(event -> Keysanity.getINSTANCE().SW(img, kSW)); break;
            case 6 : img.setOnMouseClicked(event -> Keysanity.getINSTANCE().TT(img, kTT)); break;
            case 7 : img.setOnMouseClicked(event -> Keysanity.getINSTANCE().IP(img, kIP)); break;
            case 8 : img.setOnMouseClicked(event -> Keysanity.getINSTANCE().MM(img, kMM)); break;
            case 9 : img.setOnMouseClicked(event -> Keysanity.getINSTANCE().TR(img, kTR)); break;
            case 10 : img.setOnMouseClicked(event -> Keysanity.getINSTANCE().AGA(img, kAGA)); break;
        }
    }

    // TODO: Based on layout.
    public void setKeyText()
    {
        xyKey(kEP, 128, 85, "0 / 4");
        xyKey(kDP, 128, 149, "0 / 1");
        xyKey(kTH, 128, 213, "0 / 1");
        xyKey(kPD, 128, 277, "0 / 6");
        xyKey(kSP, 128, 341, "0 / 1");
        xyKey(kSW, 128, 405, "0 / 3");
        xyKey(kTT, 128, 469, "0 / 1");
        xyKey(kIP, 128, 533, "0 / 2");
        xyKey(kMM, 128, 597, "0 / 3");
        xyKey(kTR, 128, 661, "0 / 4");
        xyKey(kAGA, 128, 725, "0 / 2");
    }

    public void xyKey(Text t, double x, double y, String text)
    {
        t.setLayoutX(x);
        t.setLayoutY(y);
        t.setText(text);
    }

    /**
     * Moves the medallions to their spots after the images for their bosses has been placed.
     * @param x X location (column * 64)
     * @param y Y location (row * 64)
     * @param img ImageView that corrosponds to the medallion.
     */
    public void setMedallions(double x, double y, ImageView img)
    {
        img.setImage(new Image(getClass().getResource("decals/medallion0.png").toString()));
        img.setLayoutX(x + Settings.offset_medallion.x);
        img.setLayoutY(y + 26 + Settings.offset_medallion.y);
        img.setVisible(Settings.medallions);
    }

    /**
     * Sets chests on top of the images in view.
     * @param val Amount of chests in dungeon with an item.
     * @param x X Location (colunm * 64)
     * @param y Y Location (row * 64)
     */
    public void setChests(int val, double x, double y, ImageView img)
    {
        // y + 32
        img.setImage(new Image(getClass().getResource("decals/chest" + chests[val] + ".png").toString()));
        img.setLayoutX(x + Settings.offset_chest.x);
        img.setLayoutY(y + 26 + Settings.offset_chest.y);
        img.setVisible(Settings.chests);
    }

    /**
     * Sets the labels to be shown on top of the boss images.
     * @param bossname Name of the boss, LOWERCASE.
     * @param x X Location (column * 64)
     * @param y Y Location (row * 64)
     */
    public void setLabels(String bossname, double x, double y)
    {
       ImageView img = lookupTextImageView.get(bossname);
       img.setImage(new Image(getClass().getResource("text/t" + bossname + ".png").toString()));
       img.setLayoutX(x + Settings.offset_label.x);
       img.setLayoutY(y + 26 + Settings.offset_label.y);
       img.setVisible(Settings.labels);
    }

    public void setPrices(String bossname, double x, double y)
    {
        ImageView img = lookupPrizeImageView.get(bossname);
        img.setImage(new Image(getClass().getResource("decals/p0.png").toString()));
        img.setLayoutX(x + Settings.offset_prizes.x);
        img.setLayoutY(y + 26 + Settings.offset_prizes.y);
        img.setVisible(Settings.prizes);
    }

    /**
     * Sets the images of the bosses on specified locations.
     * @param img_loc Img location in resources.
     * @param row row it needs to be put at.
     * @param column column it needs to be put at.
     */
    public void setBoss(String img_loc, int row, int column)
    {
        ImageView img = (ImageView) getNodeByRowColumnIndex(row, column, gridpane);
        String strBoss = img_loc;
        img.setImage(new Image(getClass().getResource(strBoss).toString()));
        String boss = img_loc.replace("boss/", "");
        boss = boss.replace(".png", "");

        double xCoord = column * 64;
        double yCoord = row * 64;

        if (boss.equals("mm"))
        {
            setMedallions(xCoord, yCoord, mMM);
            setChests(8, xCoord, yCoord, cMM);
            setLabels(boss,xCoord, yCoord);
            setPrices(boss, xCoord, yCoord);
        }
        else if (boss.equals("tr"))
        {
            setMedallions(xCoord, yCoord, mTR);
            setChests(9, xCoord, yCoord, cTR);
            setLabels(boss,xCoord, yCoord);
            setPrices(boss, xCoord, yCoord);
        }
        else if (boss.equals("aga"))
        {
            // Do Nothing.
        }
        else
        {
            setChests(bossToInt(boss), xCoord, yCoord, bossToImgViewChests(boss));
            setLabels(boss, xCoord, yCoord);
            setPrices(boss, xCoord, yCoord);
        }

        img.setId(boss + "." + row + "," + column + "-" + "false");
        img.setOnMouseClicked(event ->
        {
            if (img.getOpacity() != 1.0d)
            {
                img.setOpacity(1.0d);
            }
            else
            {
                img.setOpacity(Settings.getOpacity());
            }
        });
    }

    public int bossToInt(String bossname)
    {
        switch (bossname)
        {
            case "ep" : return 0;
            case "dp" : return 1;
            case "th" : return 2;
            case "pd" : return 3;
            case "sp" : return 4;
            case "sw" : return 5;
            case "tt" : return 6;
            case "ip" : return 7;
            case "mm" : return 8;
            case "tr" : return 9;
        }
        return 0;
    }

    public ImageView bossToImgViewChests(String bossname)
    {
        switch (bossname)
        {
            case "ep" : return cEP;
            case "dp" : return cDP;
            case "th" : return cTH;
            case "pd" : return cPD;
            case "sp" : return cSP;
            case "sw" : return cSW;
            case "tt" : return cTT;
            case "ip" : return cIP;
            case "mm" : return cMM;
            case "tr" : return cTR;
        }
        return cEP;
    }

    /**
     * Gets the node by row and column, credits to StackOverflow.
     * @param row Row.
     * @param column Column.
     * @param gridPane Gridpane.
     * @return Returns the selected Node.
     */
    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane)
    {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

    /**
     * Changes the Medallion when clicked.
     */
    public void medallionLogic(MouseEvent mouseEvent)
    {
        // Get current image
        ImageView img = (ImageView) mouseEvent.getSource();

        if (img.getId().equals("mMM"))
        {
            if (currMM != 3)
                img.setImage(new Image(getClass().getResource("decals/medallion" + ++currMM + ".png").toString()));
            else
            {
                img.setImage(new Image(getClass().getResource("decals/medallion0.png").toString()));
                currMM = 0;
            }
        }
        else
        {
            if (currTR != 3)
                img.setImage(new Image(getClass().getResource("decals/medallion" + ++currTR + ".png").toString()));
            else
            {
                img.setImage(new Image(getClass().getResource("decals/medallion0.png").toString()));
                currTR = 0;
            }
        }
    }

    //                          0     1     2     3       4      5    6
    private String[] prizes = {"p0", "p1", "p2", "p2a", "p2b", "p3", "p4"};

    private HashMap<String, Integer> prizeValues = new HashMap<>();

    /**
     * Initializes the Hashmap.
     */
    public void InitPrizeValues()
    {
        prizeValues.put("pEP", 0);
        prizeValues.put("pDP", 0);
        prizeValues.put("pTH", 0);
        prizeValues.put("pPD", 0);
        prizeValues.put("pSP", 0);
        prizeValues.put("pSW", 0);
        prizeValues.put("pTT", 0);
        prizeValues.put("pIP", 0);
        prizeValues.put("pMM", 0);
        prizeValues.put("pTR", 0);
    }

    /**
     * Normal Leftclicking cycles through all prizes.
     * CTRL + Clicking cycles through crystals.
     * SHIFT + Clicking cycles through pendants (includes blue/red).
     * @param mouseEvent
     */
    public void PrizeLogic(MouseEvent mouseEvent)
    {
        ImageView img = (ImageView) mouseEvent.getSource();
        String pID = img.getId();
        int n = prizeValues.get(pID);

        if (mouseEvent.isControlDown())
        {
            n = ctrlNumberLogic(n);
            img.setImage(new Image(getClass().getResource("decals/" + prizes[n] + ".png").toString()));

        }
        else if (mouseEvent.isShiftDown())
        {
            n = shftNumberLogic(n);
            img.setImage(new Image(getClass().getResource("decals/" + prizes[n] + ".png").toString()));
        }
        else
        {
            n = nrmlNumberLogic(n);
            img.setImage(new Image(getClass().getResource("decals/" + prizes[n] + ".png").toString()));
        }
        prizeValues.put(pID, n);
    }

    /**
     * 0,5,6 is the number sequence in ctrl+clicking.
     */
    public int ctrlNumberLogic(int in)
    {
        if (in == 0)
        {
            return 5;
        }
        else if (in == 5)
        {
            return 6;
        }
        else if (in == 6)
        {
            return 0;
        }
        else
        {
            return 5;
        }
    }

    /**
     * 0,1,3,4 is the number sequence in shft + clicking.
     */
    public int shftNumberLogic(int in)
    {
        if (in >= 4)
        {
            return 0;
        }
        else if (in == 1)
        {
            return 3;
        }
        else
        {
            return ++in;
        }
    }

    /**
     * 0 1 2 5 6 is the number sequence in normal clicking.
     */
    public int nrmlNumberLogic(int in)
    {
        if (in >= 2 && in <= 4)
        {
            return 5;
        }
        else if (in == 6)
        {
            return 0;
        }
        else
        {
            return ++in;
        }
    }

    public void ChestLogic(MouseEvent mouseEvent)
    {
        // Find out which image was clicked.
        System.out.println(mouseEvent);
        ImageView img = (ImageView) mouseEvent.getSource();
        String place = img.getId().replace("c", "");

        switch (place)
        {
            case "EP" : changeChestImage(0, img); break;
            case "DP" : changeChestImage(1, img); break;
            case "TH" : changeChestImage(2, img); break;
            case "PD" : changeChestImage(3, img); break;
            case "SP" : changeChestImage(4, img); break;
            case "SW" : changeChestImage(5, img); break;
            case "TT" : changeChestImage(6, img); break;
            case "IP" : changeChestImage(7, img); break;
            case "MM" : changeChestImage(8, img); break;
            case "TR" : changeChestImage(9, img); break;
        }

    }

    public void changeChestImage(int val, ImageView img)
    {
        int currentValue = chests[val];

        if (currentValue != 0)
        {
            chests[val]--;
            img.setImage(new Image(getClass().getResource("decals/chest" + chests[val] + ".png").toString()));
        }
        else
        {
            // Reset values.
            chests[val] = Settings.getMax_chest_values()[val];
            img.setImage(new Image(getClass().getResource("decals/chest" + chests[val] + ".png").toString()));

        }
    }

    public void ToggleMedallions(ActionEvent event)
    {
        mMM.setVisible(!mMM.isVisible());
        mTR.setVisible(!mTR.isVisible());
    }

    public void ToggleChests(ActionEvent event)
    {
        cEP.setVisible(!cEP.isVisible());
        cDP.setVisible(!cDP.isVisible());
        cTH.setVisible(!cTH.isVisible());
        cPD.setVisible(!cPD.isVisible());
        cSP.setVisible(!cSP.isVisible());
        cSW.setVisible(!cSW.isVisible());
        cTT.setVisible(!cTT.isVisible());
        cIP.setVisible(!cIP.isVisible());
        cMM.setVisible(!cMM.isVisible());
        cTR.setVisible(!cTR.isVisible());
    }

    public void ToggleNames(ActionEvent event)
    {
        tEP.setVisible(!tEP.isVisible());
        tDP.setVisible(!tDP.isVisible());
        tTH.setVisible(!tTH.isVisible());
        tPD.setVisible(!tPD.isVisible());
        tSP.setVisible(!tSP.isVisible());
        tSW.setVisible(!tSW.isVisible());
        tTT.setVisible(!tTT.isVisible());
        tIP.setVisible(!tIP.isVisible());
        tMM.setVisible(!tMM.isVisible());
        tTR.setVisible(!tTR.isVisible());
    }

    public void TogglePrizes(ActionEvent event)
    {
        pEP.setVisible(!pEP.isVisible());
        pDP.setVisible(!pDP.isVisible());
        pTH.setVisible(!pTH.isVisible());
        pPD.setVisible(!pPD.isVisible());
        pSP.setVisible(!pSP.isVisible());
        pSW.setVisible(!pSW.isVisible());
        pTT.setVisible(!pTT.isVisible());
        pIP.setVisible(!pIP.isVisible());
        pMM.setVisible(!pMM.isVisible());
        pTR.setVisible(!pTR.isVisible());
    }

    public void ColourPicker(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("/Settings.fxml"));
        // TODO: Set defaults
        Stage colorpicker_stage = new Stage();
        colorpicker_stage.setTitle("ALTTP Boss Tracker v1.1 - By: twitch.tv/Pastillage");
        colorpicker_stage.getIcons().add(new Image(getClass().getResource("decals/map.png").toString()));
        colorpicker_stage.setScene(new Scene(root, Settings.layout_size_set.x, Settings.layout_size_set.y));
        colorpicker_stage.setResizable(false);
        colorpicker_stage.show();
    }


}
