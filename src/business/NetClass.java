/* Copyright Davain Pablo Edwards core8@gmx.net. Licensed https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en */
package business;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.janino.CompileException;
import org.codehaus.janino.Parser.ParseException;
import org.codehaus.janino.Scanner.ScanException;
import org.codehaus.janino.SimpleCompiler;


/**
 * Represents a class for generating PetriNet model source code.
 * The NetClass class is an example of the Builder design pattern. 
 * This pattern is used to construct a complex object step by step and the final step will return the object. 
 * The process of constructing an object should be generic so that it can create different representations of the same configuration.
 * In this case, NetClass is used to build a PetriNet object. 
 * The generateNetSource method (not shown in the provided code) presumably builds up the netSource string, 
 * which is then compiled into a PetriNet object in the compile method. This is typical of the Builder pattern.
 */
public class NetClass {

    /** Contains the code that represents the PetriNet. */
    private StringBuffer netSource;
    /** Represents a line separator. */
    static final String EOL = System.getProperty("line.separator");

    /**
     * Default constructor for the NetClass.
     */
    public NetClass() {
        this.generateNetSource();
    }

    /**
     * Compiles a string and creates a new PetriNet instance.
     *
     * @param javaSource The Java source code to compile.
     * @throws CompileException if there is a compilation error.
     * @throws ParseException if there is a parsing error.
     * @throws ScanException if there is a scanning error.
     * @throws ClassNotFoundException if the class is not found.
     * @throws InstantiationException if there is an instantiation error.
     * @throws IllegalAccessException if there is an access error.
     */
    public void compile(String javaSource) throws CompileException, ParseException, ScanException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        SimpleCompiler compiler = new SimpleCompiler();
        try {
            compiler.cook(new StringReader(javaSource));
            Class<?> cl = compiler.getClassLoader().loadClass(Global.petriNet.getLabel());
            Global.petriNet = (PetriNet) cl.newInstance();
        } catch (IOException ex) {
            Logger.getLogger(NetClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Generates a class that represents a PetriNet model.
     *
     * @return The generated source code as a string.
     */
    public String generateNetSource() {
        this.netSource = new StringBuffer();
        this.netSource.append("/* This is an autogenerated Petri-Netz (PetriNet) Simulator Java file. */").append(EOL);
        // Imports
        this.netSource.append("import business.*;").append(EOL);
        if (!Global.petriNet.getImportText().isEmpty()) {
            this.netSource.append(Global.petriNet.getImportText()).append(EOL);
        }

        if (!Global.petriNet.getImplementText().isEmpty()) {
            this.netSource.append("public class ").append(Global.petriNet.getLabel()).append(" extends PetriNet implements ").append(Global.petriNet.getImplementText()).append(" {").append(EOL).append(EOL);
        } else {
            this.netSource.append("public class ").append(Global.petriNet.getLabel()).append(" extends PetriNet {").append(EOL);
        }

        // Declarations
        this.netSource.append(EOL).append("  /** Custom declarations. */").append(EOL);
        if (!Global.petriNet.getDeclarationText().isEmpty()) {
            this.netSource.append("  ").append(Global.petriNet.getDeclarationText()).append(EOL);
        }

        // Places declaration
        ArrayList<Place> places = Global.petriNet.getPlaces();
        if (!places.isEmpty()) {
            this.netSource.append(EOL).append("  /** Places declaration. */").append(EOL);

            for (Place place : places) {
                String placeId = place.getId();
                this.netSource.append("  private Place ").append(placeId).append(" = new Place(\"").append(placeId).append("\");").append(EOL);
            }
        }

        // Transitions declaration
        ArrayList<Transition> transitions = Global.petriNet.getTransitions();
        if (!transitions.isEmpty()) {
            this.netSource.append(EOL).append("  /** Transitions declaration. */").append(EOL);
            for (int i = 0; i < transitions.size(); i++) {
                Transition transition = (Transition) transitions.get(i);
                this.netSource.append("  private Transition ").append(transition.getId()).append(" = new Transition(\"" + transition.getId() + "\",\"" + addSlashes(transition.getGuardText()) + "\") {").append(EOL);
                this.netSource.append("         public boolean evaluate(){").append(EOL);
                this.netSource.append("                 " + transition.getGuardText()).append(EOL);
                this.netSource.append("         }").append(EOL);
                this.netSource.append("  };").append(EOL);
            }
        }

        // Input Arcs declaration
        ArrayList<InputArc> inputArcs = Global.petriNet.getInputArcs();
        if (!inputArcs.isEmpty()) {
            this.netSource.append(EOL).append("  /** Input Arcs declaration. */").append(EOL);
            for (int i = 0; i < inputArcs.size(); i++) {
                InputArc inputArc = (InputArc) inputArcs.get(i);
                this.netSource.append("  private InputArc ").append(inputArc.getId()).append(" = new InputArc(\"" + inputArc.getId() + "\",").append(inputArc.getPlace().getId()).append(",").append(inputArc.getTransition().getId()).append(",").append(" \"" + addSlashes(inputArc.getExecuteText()) + "\" ").append(") {").append(EOL);
                this.netSource.append("         public boolean evaluate() {").append(EOL);
                this.netSource.append("                 return " + addSlashes(inputArc.getEvaluateText()) + ";").append(EOL);
                this.netSource.append("         }").append(EOL);
                this.netSource.append("         public TokenSet execute() {").append(EOL);
                this.netSource.append("                 return new TokenSet(" + inputArc.getExecuteText() + ");").append(EOL);
                this.netSource.append("         }").append(EOL);
                this.netSource.append("  };").append(EOL);
            }
        }

        // Output Arcs
        ArrayList<OutputArc> outputArcs = Global.petriNet.getOutputArcs();
        if (!outputArcs.isEmpty()) {
            this.netSource.append(EOL).append("  /** Output Arcs declaration. */").append(EOL);
            for (int i = 0; i < outputArcs.size(); i++) {

                OutputArc outputArc = (OutputArc) outputArcs.get(i);

                this.netSource.append("  private OutputArc ").append(outputArc.getId()).append(" = new OutputArc(\"" + outputArc.getId() + "\",").append(outputArc.getPlace().getId()).append(",").append(outputArc.getTransition().getId()).append(",").append(" \"" + addSlashes(outputArc.getExecuteText()) + "\" ").append("){").append(EOL);
                this.netSource.append("         public TokenSet execute() {").append(EOL);

                StringTokenizer expression = new StringTokenizer(outputArc.getExecuteText(), "@");
                String executeText = "";
                String time = "";
                if (expression.countTokens() == 2) {
                    executeText = expression.nextToken();
                    time = expression.nextToken();
                    this.netSource.append("                 return new TokenSet(" + executeText + "," + time + ");").append(EOL);

                } else {
                    this.netSource.append("                 return new TokenSet(" + outputArc.getExecuteText() + ");").append(EOL);
                }
                this.netSource.append("         }").append(EOL);
                this.netSource.append("  };").append(EOL);
            }
        }

        this.netSource.append("  /** Class " + Global.petriNet.getLabel() + " constructor. */").append(EOL);
        this.netSource.append("  public ").append(Global.petriNet.getLabel()).append("() {").append(EOL);
        this.netSource.append("     setId(\"").append(Global.petriNet.getId()).append("\");").append(EOL);
        this.netSource.append("     setLabel(\"").append(Global.petriNet.getLabel()).append("\");").append(EOL);
        if (!Global.petriNet.getDeclarationText().isEmpty()) {
            this.netSource.append("     setDeclarationText(\"").append(addSlashes(Global.petriNet.getDeclarationText())).append("\");").append(EOL);
        }
        if (!Global.petriNet.getImplementText().isEmpty()) {
            this.netSource.append("     setImplementText(\"").append(addSlashes(Global.petriNet.getImplementText())).append("\");").append(EOL);
        }
        if (!Global.petriNet.getImportText().isEmpty()) {
            this.netSource.append("     setImportText(\"").append(addSlashes(Global.petriNet.getImportText())).append("\");").append(EOL);
        }

        for (int i = 0; i < places.size(); i++) {
            Place place = (Place) places.get(i);
            for (int j = 0; j < place.getTokens().size(); j++) {
                Token token = (Token) place.getTokens().get(j);
                this.netSource.append("     " + place.getId() + ".addToken(new TokenSet(" + token.getInitialMarkingExpression() + ", \"" + addSlashes(token.getInitialMarkingExpression()) + "\"));").append(EOL);
            }
            if (place.getCapacity() > 0) {
                this.netSource.append("     " + place.getId() + ".setCapacity(" + place.getCapacity() + ");").append(EOL);
            }
            this.netSource.append("     " + place.getId() + ".setLabel(\"" + place.getLabel() + "\");").append(EOL);
            this.netSource.append("     addPlace(" + place.getId() + ");").append(EOL);

        }

        for (int i = 0; i < transitions.size(); i++) {
            Transition transition = (Transition) transitions.get(i);
            this.netSource.append("     " + transition.getId() + ".setLabel(\"" + transition.getLabel() + "\");").append(EOL);
            this.netSource.append("     addTransition(" + transition.getId() + ");").append(EOL);
        }

        for (int i = 0; i < inputArcs.size(); i++) {
            InputArc inputArc = (InputArc) inputArcs.get(i);
            this.netSource.append("     " + inputArc.getId() + ".setEvaluateText(\"" + addSlashes(inputArc.getEvaluateText()) + "\");").append(EOL);
            this.netSource.append("     " + inputArc.getId() + ".setExecuteText(\"" + addSlashes(inputArc.getExecuteText()) + "\");").append(EOL);
            this.netSource.append("     addInputArc(" + inputArc.getId() + ");").append(EOL);
        }

        for (int i = 0; i < outputArcs.size(); i++) {
            OutputArc outputArc = (OutputArc) outputArcs.get(i);
            this.netSource.append("     addOutputArc(" + outputArc.getId() + ");").append(EOL);
        }

        // End of the class
        this.netSource.append("  }").append(EOL);
        this.netSource.append(EOL).append("}").append(EOL);

        return this.netSource.toString();
    }

    /**
     * Returns a string with backslashes before characters that need to be quoted.
     *
     * @param s The input string.
     * @return The string with added backslashes.
     */
    public static String addSlashes(String s) {
        if (s.trim().equals("")) {
            return s;
        }
        String res = s.replace("\"", "\\\"");
        res = res.replace("\n", "\\n");
        return res;
    }

    /**
     * Gets the generated PetriNet source code.
     *
     * @return The generated source code as a StringBuffer.
     */
    public StringBuffer getNetSource() {
        this.generateNetSource();
        return netSource;
    }

    /**
     * Sets the PetriNet source code.
     *
     * @param netSource The PetriNet source code as a StringBuffer.
     */
    public void setNetSource(StringBuffer netSource) {
        this.netSource = netSource;
    }
}