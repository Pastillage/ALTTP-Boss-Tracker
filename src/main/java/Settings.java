import javafx.scene.paint.Color;
import javafx.util.Pair;

/**
 * General Settings.
 */
public final class Settings
{
    // TODO: Load Settings when opening settings
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
    public static Coordinates layout_size_gui   = new Coordinates(128, 665);
    public static Coordinates layout_size_set   = new Coordinates(500, 400);

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
}
