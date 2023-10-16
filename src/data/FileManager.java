/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package data;

// Business logic imports
import business.Arc;
import business.Global;
import business.InputArc;
import business.OutputArc;
import business.PetriNet;
import business.Place;
import business.Token;
import business.TokenSet;
import business.Transition;

// Java utilities and graphics
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// XML and Image IO related imports
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

// Presentation and figure-related imports
import presentation.figures.AbstractArcFigure;
import presentation.figures.AbstractFigure;
import presentation.figures.NormalArcFigure;
import presentation.figures.PathPoint;
import presentation.figures.PlaceFigure;
import presentation.figures.TextFigure;
import presentation.figures.TransitionFigure;

/**
 * FileManager handles the reading and writing of Petri Net data 
 * in XML format and provides methods for image saving.
 * @author Davain Pablo Edwards
 */
public class FileManager {

    private Document dom;  // Represents the entire XML document
    private Element pnml;  // Represents the root XML element for Petri Net

    /**
     * Loads a Petri Net Model from a file and returns a HashMap representation.
     * 
     * @param file The file containing the Petri Net Model data.
     * @return A HashMap with String keys and Object values representing the Petri Net Model data.
     */
    public HashMap<String, Object> loadFile(File file) {
        HashMap<String, Object> figures = new HashMap<>();

        // Initialize a new PetriNet object
        Global.petriNet = new PetriNet();

        try {
            // Setup XML document builder and parse the file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            dom = builder.parse(file);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, "Error parsing the file", ex);
        }


        NamedNodeMap attributes;
        NodeList nodeList, childNodes;

        // Loading PetriNet name
        nodeList = dom.getElementsByTagName("name");
        if (nodeList.getLength() > 0) {
            Node component = nodeList.item(0);
            Global.petriNet.setLabel(component.getTextContent().trim());
        }
        // Loading imports
        nodeList = dom.getElementsByTagName("import");
        if (nodeList.getLength() > 0) {
            Node component = nodeList.item(0);
            Global.petriNet.setImportText(component.getTextContent().trim());
        }

        // Loading implements
        nodeList = dom.getElementsByTagName("implement");
        if (nodeList.getLength() > 0) {
            Node component = nodeList.item(0);
            Global.petriNet.setImplementText(component.getTextContent().trim());
        }

        // Loading declarations
        nodeList = dom.getElementsByTagName("declaration");
        if (nodeList.getLength() > 0) {
            Node component = nodeList.item(0);
            Global.petriNet.setDeclarationText(component.getTextContent().trim());
        }

        // Loading places
        nodeList = dom.getElementsByTagName("place");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node component = nodeList.item(i);
            Place place;
            attributes = component.getAttributes();
            place = new Place(attributes.getNamedItem("id").getNodeValue());
            Global.petriNet.addPlace(place);
            //get position
            Point2D position = new Point2D.Double(0, 0);
            Point2D offset = new Point2D.Double(0, 0);
            childNodes = component.getChildNodes();
            for (int k = 0; k < childNodes.getLength(); k++) {
                Node node = childNodes.item(k);
                if (node.getNodeName().equals("graphics")) {
                    NodeList positions = node.getChildNodes();
                    for (int l = 0; l < positions.getLength(); l++) {
                        node = positions.item(l);
                        if (node.getNodeName().equals("position")) {
                            attributes = node.getAttributes();
                            int x, y;
                            x = Integer.parseInt(attributes.getNamedItem("x").getNodeValue());
                            y = Integer.parseInt(attributes.getNamedItem("y").getNodeValue());
                            position = new Point2D.Double(x, y);
                        }
                    }
                } else if (node.getNodeName().equals("name")) {
                    NodeList positions = node.getChildNodes();
                    for (int l = 0; l < positions.getLength(); l++) {
                        node = positions.item(l);
                        if (node.getNodeName().equals("text")) {
                            place.setLabel(node.getTextContent());
                        } else if (node.getNodeName().equals("graphics")) {
                            positions = node.getChildNodes();
                            for (int m = 0; m < positions.getLength(); m++) {
                                node = positions.item(m);
                                if (node.getNodeName().equals("offset")) {
                                    attributes = node.getAttributes();
                                    int x, y;
                                    x = Integer.parseInt(attributes.getNamedItem("x").getNodeValue());
                                    y = Integer.parseInt(attributes.getNamedItem("y").getNodeValue());
                                    offset = new Point2D.Double(x, y);
                                }
                            }
                        }
                    }
                } else if (node.getNodeName().equals("initialMarking")) {
                    NodeList positions = node.getChildNodes();
                    for (int l = 0; l < positions.getLength(); l++) {
                        node = positions.item(l);
                        if (node.getNodeName().equals("text")) {
                            Token token;
                            token = new Token(null);
                            token.setInitialMarkingExpression(node.getTextContent());
                            place.addToken(new TokenSet(token));

                        }
                    }
                } else if (node.getNodeName().equals("capacity")) {
                    NodeList positions = node.getChildNodes();
                    for (int l = 0; l < positions.getLength(); l++) {
                        node = positions.item(l);
                        if (node.getNodeName().equals("text")) {
                            place.setCapacity(Integer.parseInt(node.getTextContent()));
                        }
                    }
                }
            }
            PlaceFigure placeFigure = new PlaceFigure(place.getId(), position);
            figures.put(place.getId(), placeFigure);
            placeFigure.getLabel().setOffsetToParent(offset);
            placeFigure.getLabel().setRelativePosition(placeFigure.getPosition());
            figures.put(place.getId() + "label", placeFigure.getLabel());
        }

        // Loading transitions
        nodeList = dom.getElementsByTagName("transition");
        for (int i = 0; i < nodeList.getLength(); i++) {
            org.w3c.dom.Node component = nodeList.item(i);
            Transition transition;
            attributes = component.getAttributes();
            transition = new Transition(attributes.getNamedItem("id").getNodeValue());
            Global.petriNet.addTransition(transition);
            // get position

            Point2D position = new Point2D.Double(0, 0);
            Point2D offset = new Point2D.Double(0, 0);
            childNodes = component.getChildNodes();
            for (int k = 0; k < childNodes.getLength(); k++) {
                Node node = childNodes.item(k);
                if (node.getNodeName().equals("graphics")) {
                    NodeList positions = node.getChildNodes();
                    for (int l = 0; l < positions.getLength(); l++) {
                        node = positions.item(l);
                        if (node.getNodeName().equals("position")) {
                            attributes = node.getAttributes();
                            int x, y;
                            x = Integer.parseInt(attributes.getNamedItem("x").getNodeValue());
                            y = Integer.parseInt(attributes.getNamedItem("y").getNodeValue());
                            position = new Point2D.Double(x, y);
                        }
                    }
                } else if (node.getNodeName().equals("name")) {

                    NodeList positions = node.getChildNodes();
                    for (int l = 0; l < positions.getLength(); l++) {
                        node = positions.item(l);
                        if (node.getNodeName().equals("text")) {
                            transition.setLabel(node.getTextContent());
                        } else if (node.getNodeName().equals("graphics")) {
                            positions = node.getChildNodes();
                            for (int m = 0; m < positions.getLength(); m++) {
                                node = positions.item(m);
                                if (node.getNodeName().equals("offset")) {
                                    attributes = node.getAttributes();
                                    int x, y;
                                    x = Integer.parseInt(attributes.getNamedItem("x").getNodeValue());
                                    y = Integer.parseInt(attributes.getNamedItem("y").getNodeValue());
                                    offset = new Point2D.Double(x, y);
                                }
                            }
                        }
                    }

                } else if (node.getNodeName().equals("guard")) {
                    transition.setGuardText(node.getTextContent().trim());
                }
            }

            TransitionFigure transitionFigure = new TransitionFigure(transition.getId(), position);
            figures.put(transition.getId(), transitionFigure);
            transitionFigure.getLabel().setOffsetToParent(offset);
            transitionFigure.getLabel().setRelativePosition(transitionFigure.getPosition());
            figures.put(transition.getId() + "label", transitionFigure.getLabel());
        }
        // Loading arcs
        nodeList = dom.getElementsByTagName("arc");
        for (int i = 0; i < nodeList.getLength(); i++) {
            org.w3c.dom.Node component = nodeList.item(i);

            attributes = component.getAttributes();

            String id = attributes.getNamedItem("id").getNodeValue();
            String source = attributes.getNamedItem("source").getNodeValue();
            String target = attributes.getNamedItem("target").getNodeValue();
            NormalArcFigure arcFigure = new NormalArcFigure();
            arcFigure.setConnectionStart((AbstractFigure) figures.get(source));
            arcFigure.setConnectionEnd((AbstractFigure) figures.get(target));
            String expression = "";
            // get position
            Point2D position = new Point2D.Double(0, 0);
            childNodes = component.getChildNodes();
            for (int k = 0; k < childNodes.getLength(); k++) {
                Node node = childNodes.item(k);
                if (node.getNodeName().equals("graphics")) {
                    NodeList positions = node.getChildNodes();
                    for (int l = 0; l < positions.getLength(); l++) {
                        node = positions.item(l);
                        if (node.getNodeName().equals("position")) {
                            attributes = node.getAttributes();
                            int x, y;
                            x = Integer.parseInt(attributes.getNamedItem("x").getNodeValue());
                            y = Integer.parseInt(attributes.getNamedItem("y").getNodeValue());
                            position = new Point2D.Double(x, y);
                            arcFigure.addPoint(position);
                        }
                    }
                } else if (node.getNodeName().equals("expression")) {
                    expression = (node.getTextContent().trim());
                }
            }
            AbstractFigure start = arcFigure.getStartConnector();
            AbstractFigure end = arcFigure.getEndConnector();
            if (Global.petriNet.getNetElement(start.getElementId()) instanceof Place) {
                Place p = (Place) Global.petriNet.getNetElement(start.getElementId());
                Transition t = (Transition) Global.petriNet.getNetElement(end.getElementId());
                InputArc arc = new InputArc(id, p, t, expression);
                Global.petriNet.addInputArc(arc);
            } else {
                Place p = (Place) Global.petriNet.getNetElement(end.getElementId());
                Transition t = (Transition) Global.petriNet.getNetElement(start.getElementId());
                OutputArc arc = new OutputArc(id, p, t, expression);
                Global.petriNet.addOutputArc(arc);
            }
        // Set the element ID for the arcFigure and store it in the figures map
        arcFigure.setElementId(id);
        figures.put(id, arcFigure);
        
        // Loop through the path points of the arcFigure using explicit casting
        int k = 0;
        for (Object obj : arcFigure.getPoints()) {
            PathPoint pathPoint = (PathPoint) obj; // Explicit casting
            // Skip the first and last points
            if (k != 0 && k != arcFigure.getPoints().size() - 1) {
                // Set the element ID for the pathPoint and store it in the figures map
                pathPoint.setElementId(arcFigure.getElementId() + "_pathpoint_" + k);
                figures.put(pathPoint.getElementId(), pathPoint);
            }
            k++;
        }
    }
    
        return figures;
    }

        /**
     * Generates an XML representation of the Petri Net Model and saves it to the specified file.
     * 
     * @param figures A HashMap containing the Petri Net figures data.
     * @param file The target file to write the XML representation to.
     * @throws ParserConfigurationException if a DocumentBuilder cannot be created.
     * @throws TransformerConfigurationException if there are issues with the Transformer configuration.
     * @throws TransformerException if there's an error during the transformation process.
     */
    public void generateXML(HashMap figures, File file) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        // Initialize XML document builder factory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder loader = factory.newDocumentBuilder();
        
        // Create a new XML document
        dom = loader.newDocument();
        
        // Create root XML elements for the Petri Net
        pnml = dom.createElement("pnml");
        Element net = dom.createElement("net");
        net.setAttribute("id", Global.petriNet.getId());
        
        // Placeholder for further XML elements
        Element node;
        Element text;

        // Petri Net Label
        node = dom.createElement("name");
        text = dom.createElement("text");
        text.appendChild(dom.createTextNode(Global.petriNet.getLabel()));
        node.appendChild(text);
        net.appendChild(node);


        if (!Global.petriNet.getImportText().equals("")) {
            node = dom.createElement("import");
            text = dom.createElement("text");
            text.appendChild(dom.createTextNode(Global.petriNet.getImportText()));
            node.appendChild(text);
            net.appendChild(node);
        }

        if (!Global.petriNet.getImplementText().equals("")) {
            node = dom.createElement("implement");
            text = dom.createElement("text");
            text.appendChild(dom.createTextNode(Global.petriNet.getImplementText()));
            node.appendChild(text);
            net.appendChild(node);
        }


        if (!Global.petriNet.getDeclarationText().equals("")) {
            node = dom.createElement("declaration");
            text = dom.createElement("text");
            text.appendChild(dom.createTextNode(Global.petriNet.getDeclarationText()));
            node.appendChild(text);
            net.appendChild(node);
        }
        // Iterate over the figures and create appropriate XML elements
        for (Object obj : figures.values()) {
        AbstractFigure figure = (AbstractFigure) obj; // Explicit casting
        // Handle PlaceFigure
        if (figure instanceof PlaceFigure) {
            Element placeElement = dom.createElement("place");
            Place place = (Place) Global.petriNet.getNetElement(figure.getElementId());
            placeElement.setAttribute("id", place.getId());
            placeElement.appendChild(createGraphic(figure));
            placeElement.appendChild(saveLabel((TextFigure) figures.get(place.getId() + "label"), "name"));


            // Handle tokens
            if (!place.getTokens().isEmpty()) {
                Element markingElement = dom.createElement("initialMarking");
                for (Object obj2 : place.getTokens()) {
                    Token token = (Token) obj2; // Explicit casting
                    markingElement.appendChild(createTextElement(token.getInitialMarkingExpression()));
                }
                placeElement.appendChild(markingElement);
            }

            // Handle capacity
            if (place.getCapacity() > 0) {
                Element capacityElement = dom.createElement("capacity");
                capacityElement.appendChild(createTextElement("" + place.getCapacity()));
                placeElement.appendChild(capacityElement);
            }

            net.appendChild(placeElement);

            } else if (figure instanceof TransitionFigure) {
                Element transitionElement = dom.createElement("transition");
                Transition transition = (Transition) Global.petriNet.getNetElement(figure.getElementId());
                transitionElement.setAttribute("id", transition.getId());
                transitionElement.appendChild(createGraphic(figure));
                transitionElement.appendChild(saveLabel((TextFigure) figures.get(transition.getId() + "label"), "name"));
    
                Element guardElement = dom.createElement("guard");
                guardElement.appendChild(createTextElement(transition.getGuardText()));
                transitionElement.appendChild(guardElement);
    
                net.appendChild(transitionElement);
            } else if (figure instanceof NormalArcFigure) {
                Element arcElement = dom.createElement("arc");
                Arc arc = (Arc) Global.petriNet.getNetElement(figure.getElementId());
                arcElement.setAttribute("id", arc.getId());
    
                if (arc instanceof InputArc) {
                    arcElement.setAttribute("source", arc.getPlace().getId());
                    arcElement.setAttribute("target", arc.getTransition().getId());
                    arcElement.appendChild(createTextElement(((InputArc) arc).getEvaluateText()));
                    arcElement.appendChild(createTextElement(((InputArc) arc).getExecuteText()));
                } else {
                    arcElement.setAttribute("source", arc.getTransition().getId());
                    arcElement.setAttribute("target", arc.getPlace().getId());
                    arcElement.appendChild(createTextElement(((OutputArc) arc).getExecuteText()));
                }
    
                arcElement.appendChild(createGraphic(figure));
                net.appendChild(arcElement);
            }
        }

        pnml.appendChild(net);

        dom.appendChild(pnml);
        Source source = new DOMSource(dom);

        Result result = new StreamResult(file);
        Transformer tf = TransformerFactory.newInstance().newTransformer();

        tf.setOutputProperty(OutputKeys.INDENT, "yes");

        tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
                "2");
        tf.transform(source, result);

    }

     // Helper method to create and append a text element
     private Element createTextElement(String content) {
        Element textElement = dom.createElement("text");
        textElement.appendChild(dom.createTextNode(content));
        return textElement;
    }

    /**
     * Creates an XML node to represent a common label using the given TextFigure.
     * 
     * @param figure The TextFigure containing label data.
     * @param name The name for the XML node.
     * @return An XML Element representing the label.
     */
    private Element saveLabel(TextFigure figure, String name) {
        // Create the main node for the label
        Element node = dom.createElement(name);
        
        // Add text content to the label node
        Element text = dom.createElement("text");
        text.appendChild(dom.createTextNode(figure.getTextLabel()));
        node.appendChild(text);
        
        // Add graphical representation data to the label node
        Element graphics = dom.createElement("graphics");
        Element offset = dom.createElement("offset");
        
        Point2D p = figure.getOffsetToParent();
        offset.setAttribute("x", "" + (int) p.getX());
        offset.setAttribute("y", "" + (int) p.getY());
        
        graphics.appendChild(offset);
        node.appendChild(graphics);
        
        return node;
    }

    /**
     * Creates an XML node to represent a common graphical figure.
     * 
     * @param figure The AbstractFigure containing graphical data.
     * @return An XML Element representing the graphical figure.
     */
    private Element createGraphic(AbstractFigure figure) {
        Element graphics = dom.createElement("graphics");
        
        // Process figures and append position elements
        if (figure instanceof AbstractArcFigure) {
            AbstractArcFigure arcFigure = (AbstractArcFigure) figure;
            for (Object obj : arcFigure.getPoints()) {
                PathPoint pathPoint = (PathPoint) obj;
                appendPositionElement(graphics, pathPoint.getPosition());
            }
        } else {
            appendPositionElement(graphics, figure.getPosition());
        }
        
        return graphics;
    }

        // Helper method to create and append a position element
        private void appendPositionElement(Element graphics, Point2D point) {
            Element position = dom.createElement("position");
            position.setAttribute("x", "" + (int) point.getX());
            position.setAttribute("y", "" + (int) point.getY());
            graphics.appendChild(position);
        }

    /**
     * Saves the given BufferedImage to the specified file in PNG format.
     * 
     * @param bufferedImage The BufferedImage to be saved.
     * @param file The target file.
     */
    public void savePNG(BufferedImage bufferedImage, File file) {
        try {
            ImageIO.write(bufferedImage, "png", file);
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, "Error saving the image to file", ex);
        }
    }
}
