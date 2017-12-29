package Tracker;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.util.Properties;

/**
 * General Tracker.Settings.
 */
public final class Settings
{
    // TODO: Load Tracker.Settings when opening settings
    // TODO: Save to INI
    // TODO: Read from INI
    // TODO: SRAM

    public static final Settings INSTANCE = new Settings();
    public static Color bgColor = Color.BLACK;
    public static Long POLLING_RATE = 100l;
    public static int[] max_chest_values = {3,2,2,5,6,2,4,3,2,5};
    public static double opacity = 0.15d;
    public static Coordinates offset_chest      = new Coordinates(0,  32);
    public static Coordinates offset_label      = new Coordinates(0,  0);
    public static Coordinates offset_medallion  = new Coordinates(32, 0);
    public static Coordinates offset_prizes     = new Coordinates(32, 32);
    public static Coordinates layout_size_gui   = new Coordinates(192, 729);
    public static Coordinates layout_size_set   = new Coordinates(490, 390);

    public static Stage GUI;

    public static boolean labels = false;
    public static boolean chests = false;
    public static boolean prizes = true;
    public static boolean medallions = true;

    public static int[] getMax_chest_values()
    {
        // DO NOT GIVE THE DIRECT REFERENCE.
        return max_chest_values.clone();
    }

    private Settings()
    {}

    public static Settings getINSTANCE()
    {
        return INSTANCE;
    }

    public static Color getBgColor()
    {
        return bgColor;
    }

    public static void setBgColor(Color bg_color)
    {
        bgColor = bg_color;
    }

    public static Long getPollingRate()
    {
        return POLLING_RATE;
    }

    public static void setPollingRate(Long pollingRate)
    {
        POLLING_RATE = pollingRate;
    }

    public static void setMax_chest_values(int[] max_chest_values)
    {
        Settings.max_chest_values = max_chest_values;
    }

    public static double getOpacity()
    {
        return opacity;
    }

    public static void setOpacity(double opacity)
    {
        Settings.opacity = opacity;
    }

    public static void LoadSettings()
    {
        File f = new File("settings.alttp");
        if (!f.exists())
        {
            createDefaults();
        }

        Properties settings = new Properties();
        InputStream input = null;

        try
        {
            input = new FileInputStream("settings.alttp");
            settings.load(input);

            Settings.setBgColor(Color.web(settings.getProperty("BGC")));
            Settings.setPollingRate(Long.parseLong(settings.getProperty("POLLING_RATE")));
            Settings.setMax_chest_values(chestMaxima(settings.getProperty("CHEST_MAX")));
            Settings.setOpacity(Double.parseDouble(settings.getProperty("OPACITY"))/100.0);

            Coordinates c_gui = new Coordinates(Double.parseDouble(settings.getProperty("SIZE_GUI_X")), Double.parseDouble(settings.getProperty("SIZE_GUI_Y")));
            Coordinates c_chest = new Coordinates(Double.parseDouble(settings.getProperty("CHEST_OFFSET_X")), Double.parseDouble(settings.getProperty("CHEST_OFFSET_Y")));
            Coordinates c_label = new Coordinates(Double.parseDouble(settings.getProperty("LABEL_OFFSET_X")), Double.parseDouble(settings.getProperty("LABEL_OFFSET_Y")));
            Coordinates c_medal = new Coordinates(Double.parseDouble(settings.getProperty("MEDAL_OFFSET_X")), Double.parseDouble(settings.getProperty("MEDAL_OFFSET_Y")));
            Coordinates c_prize = new Coordinates(Double.parseDouble(settings.getProperty("PRIZE_OFFSET_X")), Double.parseDouble(settings.getProperty("PRIZE_OFFSET_Y")));

            Settings.layout_size_gui = c_gui;
            Settings.offset_chest = c_chest;
            Settings.offset_label = c_label;
            Settings.offset_medallion = c_medal;
            Settings.offset_prizes = c_prize;

            Settings.labels = convertBoolean(Integer.parseInt(settings.getProperty("LABELS")));
            Settings.chests = convertBoolean(Integer.parseInt(settings.getProperty("CHESTS")));
            Settings.prizes = convertBoolean(Integer.parseInt(settings.getProperty("MEDALS")));
            Settings.medallions = convertBoolean(Integer.parseInt(settings.getProperty("PRIZES")));
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (input != null)
            {
                try
                {
                    input.close();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static boolean convertBoolean(int x)
    {
        if (x == 1)
            return true;
        else
            return false;
    }

    public static int[] chestMaxima(String in)
    {
        in = in.substring(0, in.length()-1);
        String[] parts = in.split(";");
        int[] result = new int[10];

        for (int c = 0; c < 10; c++)
        {
            result[c] = Integer.parseInt(parts[c]);
        }
        return result;
    }

    public static void createDefaults()
    {
        Properties settings = new Properties();
        OutputStream output = null;

        try
        {
            output = new FileOutputStream("settings.alttp");

            settings.put("BGC", "0x000000ff");
            settings.put("POLLING_RATE", "100");
            settings.put("CHEST_MAX", "3;2;2;5;6;2;4;3;2;5;");
            settings.put("OPACITY", "15.0");
            settings.put("SIZE_GUI_X", "192");
            settings.put("SIZE_GUI_Y", "729");
            settings.put("CHEST_OFFSET_X", "32.0");
            settings.put("CHEST_OFFSET_Y", "0.0");
            settings.put("LABEL_OFFSET_X", "0.0");
            settings.put("LABEL_OFFSET_Y", "0.0");
            settings.put("MEDAL_OFFSET_X", "32.0");
            settings.put("MEDAL_OFFSET_Y", "0.0");
            settings.put("PRIZE_OFFSET_X", "32.0");
            settings.put("PRIZE_OFFSET_Y", "32.0");
            settings.put("LABELS", "0");
            settings.put("CHESTS", "0");
            settings.put("MEDALS", "1");
            settings.put("PRIZES", "1");

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
    }
}
