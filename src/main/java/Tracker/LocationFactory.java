package Tracker;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 *
 */
public final class LocationFactory
{
    private static final LocationFactory INSTANCE = new LocationFactory();

    private LocationFactory()
    {}

    public Location createLocation(ImageView img, boolean visited, LocationState curr_state)
    {
        return new Location(img, visited, curr_state, () -> true);
    }

    public Location createLocation(ImageView img, boolean visited, LocationState curr_state, Logic l)
    {
        return new Location(img, visited, curr_state, l);
    }

    public void defaultLocations(ImageView... imageViews)
    {
        ArrayList<Location> map = new ArrayList<>();
        for (ImageView i : imageViews)
        {
            map.add(createDefaultLocations(i));
        }
        OverworldMap.getINSTANCE().setLocations(map);
    }

    public Location createDefaultLocations(ImageView img)
    {
        System.out.println(img.getId());
        switch (img.getId())
        {
            case "loc_aga" : return createLocation(img, false, LocationState.RED, () -> GameState.getSword() >= 2 || (GameState.getMagic_cape() == 1 && GameState.getSword() >= 1));
            case "loc_darkescape" : return createLocation(img, false, LocationState.RED, () -> GameState.getLamp() == 1);
            case "loc_backescape" : return createLocation(img, false, LocationState.RED, () -> GameState.getLamp() == 1 || GameState.getGloves() > 0);
            case "loc_graveyardledge" : return createLocation(img, false, LocationState.RED, () -> GameState.getMagic_mirror() == 1 && (GameState.DW_WEST_ACCESS()));
            case "loc_kingtomb" : return createLocation(img, false, LocationState.RED, () -> GameState.getPegasus_boots() == 1 && (GameState.getGloves() == 2 || (GameState.getMagic_mirror() == 1 && GameState.DW_WEST_ACCESS())));
            case "loc_bonk" : return createLocation(img, false, LocationState.RED, () -> GameState.getPegasus_boots() == 1);
            case "loc_lumberjack" : return createLocation(img, false, LocationState.YELLOW);
            case "loc_sick" : return createLocation(img, false, LocationState.RED);
            case "loc_boots" : return createLocation(img, false, LocationState.YELLOW);
            case "loc_shovel" : return createLocation(img, false, LocationState.RED);
            case "loc_grove" : return createLocation(img, false, LocationState.RED, () -> GameState.DW_SOUTH_ACCESS() && GameState.getMagic_mirror() == 1);
            case "loc_ped" : return createLocation(img, false, LocationState.RED);
            case "loc_hylia_island" : return createLocation(img, false, LocationState.RED, () -> GameState.getZoras_flippers() + GameState.getMagic_mirror() == 2);
            case "loc_hylia_hobo" : return createLocation(img, false, LocationState.RED, () -> GameState.getZoras_flippers() == 1);
            case "loc_ep" : return createLocation(img, false, LocationState.YELLOW);
            case "loc_dp" : return createLocation(img, false, LocationState.RED, () -> GameState.DESERT_ACCESS());
            case "loc_th" : return createLocation(img, false, LocationState.RED, () -> GameState.DM_ACCESS() && (GameState.getMagic_mirror() == 1 || GameState.DM_EAST_ACCESS()));
            case "loc_witch" : return createLocation(img, false, LocationState.RED, () -> GameState.getMushroom() == 1);
            case "loc_wf" : return createLocation(img, false, LocationState.RED, () -> GameState.getZoras_flippers() == 1);
            case "loc_z_ledge" : return createLocation(img, false, LocationState.RED, () -> GameState.getZoras_flippers() == 1);
            case "loc_scam" : return createLocation(img, false, LocationState.RED, () -> GameState.getGloves() >= 1 || GameState.getZoras_flippers() == 1);
            case "loc_oldman" : return createLocation(img, false, LocationState.RED, () -> GameState.DM_ACCESS() && GameState.getLamp() == 1);
            case "loc_spectacle" : return createLocation(img, false, LocationState.RED, () -> GameState.DM_ACCESS());
            case "loc_th_mirror" : return createLocation(img, false, LocationState.RED, () -> GameState.DM_ACCESS() && GameState.getMagic_mirror() == 1);
            case "loc_e_tablet" : return createLocation(img, false, LocationState.RED, () -> GameState.getSword() >= 2 && GameState.getBook_of_mudora() == 1 && GameState.DM_ACCESS() && (GameState.getMagic_mirror() == 1 || (GameState.DM_EAST_ACCESS() && GameState.getHammer() == 1)));
            case "loc_mimic" : return createLocation(img, false, LocationState.RED, () -> GameState.getMagic_mirror() == 1 && GameState.TR_ACCESS());
            case "loc_spiral" : return createLocation(img, false, LocationState.RED, () -> GameState.DM_EAST_ACCESS());
            case "loc_paradox" : return createLocation(img, false, LocationState.RED, () -> GameState.DM_EAST_ACCESS());
            case "loc_b_tablet" : return createLocation(img, false, LocationState.RED, () -> GameState.DW_SOUTH_ACCESS() && GameState.getMagic_mirror() == 1 && GameState.getBook_of_mudora() == 1 && GameState.getSword() >= 2);
            case "loc_check" : return createLocation(img, false, LocationState.RED, () -> GameState.getFlute() == 1 && GameState.getGloves() == 2 && GameState.getMagic_mirror() == 1);
            case "loc_ledge" : return createLocation(img, false, LocationState.YELLOW, () -> GameState.DESERT_ACCESS());
            case "loc_smith" : return createLocation(img, false, LocationState.RED, () -> GameState.getGloves() >= 2 && GameState.DW_WEST_ACCESS());
            case "loc_batcave" : return createLocation(img, false, LocationState.RED, () -> GameState.getMagic_powder() == 1 && ( (GameState.DW_WEST_ACCESS() && GameState.getMagic_mirror() == 1) || GameState.getHammer() == 1));
            case "loc_pd" : return createLocation(img, false, LocationState.RED, () -> GameState.DW_EAST_ACCESS());
            case "loc_ip" : return createLocation(img, false, LocationState.RED, () -> GameState.IP_ACCESS());
            case "loc_sp" : return createLocation(img, false, LocationState.RED, () -> GameState.getZoras_flippers() == 1 && GameState.getMagic_mirror() == 1 && GameState.DW_SOUTH_ACCESS());
            case "loc_mm" : return createLocation(img, false, LocationState.RED, () -> GameState.MM_ACCESS());
            case "loc_sw" : return createLocation(img, false, LocationState.RED, () -> GameState.DW_WEST_ACCESS() && GameState.getFire_rod() == 1);
            case "loc_tt" : return createLocation(img, false, LocationState.RED, () -> GameState.DW_WEST_ACCESS());
            case "loc_tr" : return createLocation(img, false, LocationState.RED, () -> GameState.TR_ACCESS());
            case "loc_gt" : return createLocation(img, false, LocationState.RED);
            case "loc_chest_game" : return createLocation(img, false, LocationState.RED, () -> GameState.DW_WEST_ACCESS());
            case "loc_c_house" : return createLocation(img, false, LocationState.RED, () -> GameState.DW_WEST_ACCESS());
            case "loc_bombhut" : return createLocation(img, false, LocationState.RED, () -> GameState.DW_WEST_ACCESS());
            case "loc_dig" : return createLocation(img, false, LocationState.RED, () -> GameState.DW_SOUTH_ACCESS());
            case "loc_stumpy" : return createLocation(img, false, LocationState.RED, () -> GameState.DW_SOUTH_ACCESS());
            case "loc_hypecave" : return createLocation(img, false, LocationState.RED, () -> GameState.DW_SOUTH_ACCESS());
            case "loc_mireshed" : return createLocation(img, false, LocationState.RED, () -> GameState.getFlute() == 1 && GameState.getGloves() == 2);
            case "loc_pyramid_item" : return createLocation(img, false, LocationState.RED, () -> GameState.DW_EAST_ACCESS_MPLESS());
            case "loc_fatfairy" : return createLocation(img, false, LocationState.RED, () -> GameState.PYRAMID_FAIRY());
            case "loc_bumper" : return createLocation(img, false, LocationState.RED, () -> GameState.getMagic_cape() == 1 && GameState.DW_WEST_ACCESS());
            case "loc_spikecave" : return createLocation(img, false, LocationState.RED, () -> GameState.DM_ACCESS() && GameState.getMoon_pearl() == 1 && GameState.getHammer() == 1);
            case "loc_hs_top" : return createLocation(img, false, LocationState.RED, () -> GameState.getHookshot() == 1 && GameState.DW_DM_ACCESS());
            case "loc_hs_bot" : return createLocation(img, false, LocationState.RED, () -> GameState.DW_DM_ACCESS() && (GameState.getPegasus_boots() == 1 || GameState.getHookshot() == 1));
            case "loc_hs_mirror" : return createLocation(img, false, LocationState.RED, () -> GameState.DW_DM_ACCESS() && GameState.getMagic_mirror() == 1);
            case "loc_superbunny" : return createLocation(img, false, LocationState.RED, () -> GameState.DM_EAST_ACCESS() && GameState.getGloves() == 2);
            case "loc_catfish" : return createLocation(img, false, LocationState.RED, () -> GameState.DW_EAST_ACCESS());
            case "loc_lunchbox" : return createLocation(img, false, LocationState.RED, () -> GameState.DW_WEST_ACCESS() && GameState.getGloves() == 2);
            case "loc_hammerpegs" : return createLocation(img, false, LocationState.RED, () -> GameState.DW_WEST_ACCESS() && GameState.getGloves() == 2 && GameState.getHammer() == 1);
            default : return createLocation(img, false, LocationState.GREEN);
        }
    }



    public static LocationFactory getINSTANCE()
    {
        return INSTANCE;
    }
}
