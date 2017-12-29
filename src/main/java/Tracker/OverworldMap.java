package Tracker;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

/**
 *
 */
public class OverworldMap
{
    public static final OverworldMap INSTANCE = new OverworldMap();
    private ArrayList<Location> locations = new ArrayList<>();

    private OverworldMap()
    {}

    public static OverworldMap getINSTANCE()
    {
        return INSTANCE;
    }

    /*
    public void createXML() throws ParserConfigurationException, TransformerException
    {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();
        Element rootElement = document.createElement("map");
        document.appendChild(rootElement);

        for (Location l : locations)
        {
            Element location = document.createElement(l.getReference().getId());
            location.setAttribute("x", "0");
            location.setAttribute("y", "0");
            document.appendChild(location);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File("C:\\file.xml"));

        transformer.transform(source, result);
    }*/

    public ArrayList<Location> getLocations()
    {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations)
    {
        this.locations = locations;
    }

}
