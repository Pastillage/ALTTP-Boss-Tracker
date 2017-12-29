package Tracker;

import javafx.scene.image.ImageView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;

/**
 * Class for locations on the map.
 */
public class Location
{
    private ImageView reference;
    private boolean visited = false;
    private LocationState current_state;
    private Logic logic;

    public Location(ImageView reference, boolean visited, LocationState current_state, Logic logic)
    {
        this.reference = reference;
        this.visited = visited;
        this.current_state = current_state;
        this.logic = logic;
    }

    public ImageView getReference()
    {
        return reference;
    }

    public void setReference(ImageView reference)
    {
        this.reference = reference;
    }

    public boolean isVisited()
    {
        return visited;
    }

    public void setVisited(boolean visited)
    {
        this.visited = visited;
    }

    public LocationState getCurrent_state()
    {
        return current_state;
    }

    public void setCurrent_state(LocationState current_state)
    {
        this.current_state = current_state;
    }
}
