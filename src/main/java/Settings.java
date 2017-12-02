import javafx.scene.paint.Color;

/**
 * General Settings.
 */
public final class Settings
{
    public static final Settings INSTANCE = new Settings();
    private static Color bgColor = Color.BLACK;
    private static final Long POLLING_RATE = 100l;
    private static int[] max_chest_values = {3,2,2,5,6,2,4,3,2,5};

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
}
