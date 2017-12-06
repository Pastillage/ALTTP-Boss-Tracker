import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * Keysanity Helper.
 */
public final class Keysanity
{
    public static final Keysanity INSTANCE = new Keysanity();
    public static int[] keys = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};


    private Keysanity()
    {
    }

    public static Keysanity getINSTANCE()
    {
        return INSTANCE;
    }

    public void DP(ImageView img, Text txt)
    {
        keyHandlerGeneric(img, txt, 1, 0);
    }

    public void TH(ImageView img, Text txt)
    {
        keyHandlerGeneric(img, txt, 1, 1);
    }

    public void PD(ImageView img, Text txt)
    {
        keyHandlerGeneric(img, txt, 6, 2);
    }

    public void SP(ImageView img, Text txt)
    {
        keyHandlerGeneric(img, txt, 1, 3);
    }

    public void SW(ImageView img, Text txt)
    {
        keyHandlerGeneric(img, txt, 3, 4);
    }

    public void TT(ImageView img, Text txt)
    {
        keyHandlerGeneric(img, txt, 1, 5);
    }

    public void IP(ImageView img, Text txt)
    {
        keyHandlerGeneric(img, txt, 2, 6);
    }

    public void MM(ImageView img, Text txt)
    {
        keyHandlerGeneric(img, txt, 3, 7);
    }

    public void TR(ImageView img, Text txt)
    {
        keyHandlerGeneric(img, txt, 4, 8);
    }

    public void AGA(ImageView img, Text txt)
    {
        keyHandlerGeneric(img, txt, 2, 9);
    }

    public void GT(ImageView img, Text txt)
    {
        keyHandlerGeneric(img, txt, 4, 10);
    }

    public void keyHandlerGeneric(ImageView img, Text txt, int key_max, int keyReference)
    {
        if (keys[keyReference] == key_max)
        {
            keys[keyReference] = 0;
            img.setOpacity(Settings.getOpacity());
        }
        else
        {
            System.out.println(keys[keyReference]);
            ++keys[keyReference];
            System.out.println(keys[keyReference]);
            img.setOpacity(1);
        }

        txt.setText(keys[keyReference] + " / " + key_max);
    }
}
