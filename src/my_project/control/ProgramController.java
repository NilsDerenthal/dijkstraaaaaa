package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.Edge;
import KAGO_framework.model.abitur.datenstrukturen.Graph;
import KAGO_framework.model.abitur.datenstrukturen.Vertex;
import my_project.model.GraphVisualization;

import java.awt.event.MouseEvent;

/**
 * Ein Objekt der Klasse ProgramController dient dazu das Programm zu steuern. Die updateProgram - Methode wird
 * mit jeder Frame im laufenden Programm aufgerufen.
 */
public class ProgramController {

    //Attribute

    private ViewController viewController;

    private Graph graph;

    /**
     * Konstruktor
     * Dieser legt das Objekt der Klasse ProgramController an, das den Programmfluss steuert.
     * Damit der ProgramController auf das Fenster zugreifen kann, benötigt er eine Referenz auf das Objekt
     * der Klasse viewController. Diese wird als Parameter übergeben.
     * @param viewController das viewController-Objekt des Programms
     */
    public ProgramController(ViewController viewController){
        this.viewController = viewController;
    }

    /**
     * Diese Methode wird genau ein mal nach Programmstart aufgerufen. Achtung: funktioniert nicht im Szenario-Modus
     */
    public void startProgram() {
        graph = new Graph();
        graph.addVertex(new Vertex("a"));
        graph.addVertex(new Vertex("b"));
        graph.addVertex(new Vertex("c"));
        graph.addVertex(new Vertex("d"));
        graph.addVertex(new Vertex("e"));
        graph.addVertex(new Vertex("f"));
        graph.addVertex(new Vertex("g"));
        graph.addVertex(new Vertex("h"));
        graph.addVertex(new Vertex("i"));
        graph.addVertex(new Vertex("j"));
        graph.addVertex(new Vertex("k"));
        graph.addVertex(new Vertex("l"));

        GraphVisualization g = new GraphVisualization(graph);
        viewController.draw(g);
    }

    /**
     * Sorgt dafür, dass zunächst gewartet wird, damit der SoundController die
     * Initialisierung abschließen kann. Die Wartezeit ist fest und damit nicht ganz sauber
     * implementiert, aber dafür funktioniert das Programm auch bei falscher Java-Version
     * @param dt Zeit seit letzter Frame
     */
    public void updateProgram(double dt){

    }


    /**
     * Verarbeitet einen Mausklick.
     * @param e das Objekt enthält alle Informationen zum Klick
     */
    public void mouseClicked(MouseEvent e){

    }
}
