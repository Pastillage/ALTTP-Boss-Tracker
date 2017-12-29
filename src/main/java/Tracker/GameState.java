package Tracker;

/**
 * Contains all info regarding gamestate.
 * Designed to simulate SRAM.
 */
public final class GameState
{
    private static final GameState INSTANCE = new GameState();

    private GameState()
    {}

    private static byte bow_state      = 0;
    private static byte boomerang      = 0;
    private static byte hookshot       = 0;
    private static byte bombs          = 0;
    private static byte magic_powder   = 0;
    private static byte mushroom       = 0;
    private static byte fire_rod       = 0;
    private static byte ice_rod        = 0;
    private static byte bombos         = 0;
    private static byte ether          = 0;
    private static byte quake          = 0;
    private static byte lamp           = 0;
    private static byte hammer         = 0;
    private static byte flute          = 0;
    private static byte bugnet         = 0;
    private static byte book_of_mudora = 0;
    private static byte bottles        = 0;
    private static byte cane_of_somaria= 0;
    private static byte cane_of_byrna  = 0;
    private static byte magic_cape     = 0;
    private static byte magic_mirror   = 0;
    private static byte gloves         = 0;
    private static byte pegasus_boots  = 0;
    private static byte zoras_flippers = 0;
    private static byte moon_pearl     = 0;
    private static byte sword          = 0;
    private static byte shield         = 0;
    private static byte tunic          = 0;
    private static byte rupee_count    = 0;

    private static byte heart_pieces   = 0;
    private static byte health         = 0;

    // TODO: HAVE GUI UPDATE THESE 3.
    private static boolean aga_dead = false;
    private static byte tr_medalion = 0;
    private static byte mm_medalion = 0;

    public GameState getINSTANCE()
    {
        return INSTANCE;
    }

    public static byte getBow_state()
    {
        return bow_state;
    }

    public static boolean DM_ACCESS()
    {
        return flute == 1 || (lamp == 1 && gloves >= 1);
    }

    /**
     * Locations such as: TT, SW, SP
     */
    public static boolean DW_WEST_ACCESS()
    {
        return DW_SOUTH_ACCESS() && (hookshot == 1 || gloves >= 1);
    }

    public static boolean DW_SOUTH_ACCESS()
    {
        return moon_pearl == 1 && (gloves == 2 || (gloves == 1 && hammer == 1) || (DW_EAST_ACCESS() && (hookshot == 1 || hammer == 1) ) );
    }

    /**
     * Locations such as: PD, Pyramid, Quake Fish
     */
    public static boolean DW_EAST_ACCESS()
    {
        return moon_pearl == 1 && (DW_EAST_ACCESS_MPLESS() || (gloves == 2 && zoras_flippers == 1) || (hammer == 1 && gloves >= 1) );
    }

    public static boolean DW_EAST_ACCESS_MPLESS()
    {
        return aga_dead;
    }

    public static boolean DESERT_ACCESS()
    {
        return book_of_mudora == 1 || (flute == 1 && magic_mirror == 1);
    }

    /**
     * The right side.
     */
    public static boolean DW_DM_ACCESS()
    {
        return moon_pearl == 1 && gloves == 2 && (DM_EAST_ACCESS());
    }

    public static boolean DM_EAST_ACCESS()
    {
        return DM_ACCESS() && ((hammer == 1 && magic_mirror == 1) || hookshot == 1);
    }

    public static boolean TR_ACCESS()
    {
        return DW_DM_ACCESS() && gloves == 2 && hammer == 1 && MEDALION(tr_medalion);
    }

    // Technically IP is beatable without hookshot, but it's super slow.
    public static boolean IP_ACCESS()
    {
        return zoras_flippers == 1 && gloves == 2 && hammer == 1 && (fire_rod == 1 || bombos == 1);
    }

    public static boolean PYRAMID_FAIRY()
    {
        return DW_SOUTH_ACCESS() && moon_pearl == 1 && (DW_EAST_ACCESS_MPLESS() || hammer == 1);
    }

    public static boolean MEDALION(byte relevant)
    {
        switch (relevant)
        {
            case 0 : return bombos + quake + ether == 3;
            case 1 : return bombos == 1;
            case 2 : return ether == 1;
            case 3 : return quake == 1;
            default : return false;
        }
    }

    public static boolean MM_ACCESS()
    {
        return (gloves == 2 && flute == 1 && MEDALION(mm_medalion) && (hookshot == 1 || pegasus_boots == 1));
    }

    public static void setBow_state(byte bow_state)
    {
        GameState.bow_state = bow_state;
    }

    public static byte getBoomerang()
    {
        return boomerang;
    }

    public static void setBoomerang(byte boomerang)
    {
        GameState.boomerang = boomerang;
    }

    public static byte getHookshot()
    {
        return hookshot;
    }

    public static void setHookshot(byte hookshot)
    {
        GameState.hookshot = hookshot;
    }

    public static byte getBombs()
    {
        return bombs;
    }

    public static void setBombs(byte bombs)
    {
        GameState.bombs = bombs;
    }

    public static byte getMagic_powder()
    {
        return magic_powder;
    }

    public static byte getMushroom()
    {
        return mushroom;
    }

    public static void setMushroom(byte mushroom)
    {
        GameState.mushroom = mushroom;
    }

    public static boolean isAga_dead()
    {
        return aga_dead;
    }

    public static void setAga_dead(boolean aga_dead)
    {
        GameState.aga_dead = aga_dead;
    }

    public static byte getTr_medalion()
    {
        return tr_medalion;
    }

    public static void setTr_medalion(byte tr_medalion)
    {
        GameState.tr_medalion = tr_medalion;
    }

    public static byte getMm_medalion()
    {
        return mm_medalion;
    }

    public static void setMm_medalion(byte mm_medalion)
    {
        GameState.mm_medalion = mm_medalion;
    }

    public static void setMagic_powder(byte magic_powder)
    {
        GameState.magic_powder = magic_powder;
    }

    public static byte getFire_rod()
    {
        return fire_rod;
    }

    public static void setFire_rod(byte fire_rod)
    {
        GameState.fire_rod = fire_rod;
    }

    public static byte getIce_rod()
    {
        return ice_rod;
    }

    public static void setIce_rod(byte ice_rod)
    {
        GameState.ice_rod = ice_rod;
    }

    public static byte getBombos()
    {
        return bombos;
    }

    public static void setBombos(byte bombos)
    {
        GameState.bombos = bombos;
    }

    public static byte getEther()
    {
        return ether;
    }

    public static void setEther(byte ether)
    {
        GameState.ether = ether;
    }

    public static byte getQuake()
    {
        return quake;
    }

    public static void setQuake(byte quake)
    {
        GameState.quake = quake;
    }

    public static byte getLamp()
    {
        return lamp;
    }

    public static void setLamp(byte lamp)
    {
        GameState.lamp = lamp;
    }

    public static byte getHammer()
    {
        return hammer;
    }

    public static void setHammer(byte hammer)
    {
        GameState.hammer = hammer;
    }

    public static byte getFlute()
    {
        return flute;
    }

    public static void setFlute(byte flute)
    {
        GameState.flute = flute;
    }

    public static byte getBugnet()
    {
        return bugnet;
    }

    public static void setBugnet(byte bugnet)
    {
        GameState.bugnet = bugnet;
    }

    public static byte getBook_of_mudora()
    {
        return book_of_mudora;
    }

    public static void setBook_of_mudora(byte book_of_mudora)
    {
        GameState.book_of_mudora = book_of_mudora;
    }

    public static byte getBottles()
    {
        return bottles;
    }

    public static void setBottles(byte bottles)
    {
        GameState.bottles = bottles;
    }

    public static byte getCane_of_somaria()
    {
        return cane_of_somaria;
    }

    public static void setCane_of_somaria(byte cane_of_somaria)
    {
        GameState.cane_of_somaria = cane_of_somaria;
    }

    public static byte getCane_of_byrna()
    {
        return cane_of_byrna;
    }

    public static void setCane_of_byrna(byte cane_of_byrna)
    {
        GameState.cane_of_byrna = cane_of_byrna;
    }

    public static byte getMagic_cape()
    {
        return magic_cape;
    }

    public static void setMagic_cape(byte magic_cape)
    {
        GameState.magic_cape = magic_cape;
    }

    public static byte getMagic_mirror()
    {
        return magic_mirror;
    }

    public static void setMagic_mirror(byte magic_mirror)
    {
        GameState.magic_mirror = magic_mirror;
    }

    public static byte getGloves()
    {
        return gloves;
    }

    public static void setGloves(byte gloves)
    {
        GameState.gloves = gloves;
    }

    public static byte getPegasus_boots()
    {
        return pegasus_boots;
    }

    public static void setPegasus_boots(byte pegasus_boots)
    {
        GameState.pegasus_boots = pegasus_boots;
    }

    public static byte getZoras_flippers()
    {
        return zoras_flippers;
    }

    public static void setZoras_flippers(byte zoras_flippers)
    {
        GameState.zoras_flippers = zoras_flippers;
    }

    public static byte getMoon_pearl()
    {
        return moon_pearl;
    }

    public static void setMoon_pearl(byte moon_pearl)
    {
        GameState.moon_pearl = moon_pearl;
    }

    public static byte getSword()
    {
        return sword;
    }

    public static void setSword(byte sword)
    {
        GameState.sword = sword;
    }

    public static byte getShield()
    {
        return shield;
    }

    public static void setShield(byte shield)
    {
        GameState.shield = shield;
    }

    public static byte getTunic()
    {
        return tunic;
    }

    public static void setTunic(byte tunic)
    {
        GameState.tunic = tunic;
    }

    public static byte getRupee_count()
    {
        return rupee_count;
    }

    public static void setRupee_count(byte rupee_count)
    {
        GameState.rupee_count = rupee_count;
    }

    public static byte getHeart_pieces()
    {
        return heart_pieces;
    }

    public static void setHeart_pieces(byte heart_pieces)
    {
        GameState.heart_pieces = heart_pieces;
    }

    public static byte getHealth()
    {
        return health;
    }

    public static void setHealth(byte health)
    {
        GameState.health = health;
    }
}
