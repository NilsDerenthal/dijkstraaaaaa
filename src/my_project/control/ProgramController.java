package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.*;
import my_project.model.VertexData;
import my_project.view.GraphVisualization;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Arrays;

/**
 * Ein Objekt der Klasse ProgramController dient dazu das Programm zu steuern. Die updateProgram - Methode wird
 * mit jeder Frame im laufenden Programm aufgerufen.
 */
public class ProgramController {

    //Attribute

    private ViewController viewController;

    private Graph graph;
    private GraphVisualization graphVis;

    private Queue<Edge> edges;
    private Queue<Edge> result;
    private double timer;

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


        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");
        Vertex f = new Vertex("f");
        Vertex g = new Vertex("g");
        Vertex h = new Vertex("h");

        var l = Arrays.asList(a, b, c, d, e, f, g, h);

        for (Vertex vertex : l) {
            graph.addVertex(vertex);
        }

        add(a, b, 1);
        add(b, c, 2);
        add(c, d, 3);
        add(d, e, 4);
        add(a, e, 25);
        add(a, f, 2);
        add(f, e, 10);
        add(f, g, 2);
        add(g, h, 3);
        add(h, e, 1);


        graphVis = new GraphVisualization(graph);
        graphVis.loadCoordinates();
        viewController.draw(graphVis);

        timer = 0;
        result = dijkstra(a, e);
    }

    private void add (Vertex a, Vertex b, double weight) {
        graph.addEdge(new Edge(a, b, weight));
    }

    private Queue<Edge> dijkstra (Vertex start, Vertex end) {
        var worker = new Dijkstra(graph, start, end);
        var tmp = worker.execute();

        this.edges = worker.getEdges();

        Queue<Edge> res = new Queue<>();
        while (tmp.getPrev() != null) {
            res.enqueue(graph.getEdge(tmp.getSrc(), tmp.getPrev().getSrc()));
            tmp = tmp.getPrev();
        }
        return res;
    }


    /**
     * Sorgt dafür, dass zunächst gewartet wird, damit der SoundController die
     * Initialisierung abschließen kann. Die Wartezeit ist fest und damit nicht ganz sauber
     * implementiert, aber dafür funktioniert das Programm auch bei falscher Java-Version
     * @param dt Zeit seit letzter Frame
     */
    public void updateProgram(double dt) {
        timer += dt;

        if (!edges.isEmpty()) {
            edges.front().setMark(true);
            if (timer > 0.25) {

                edges.front().setMark(false);
                edges.dequeue();

                timer = 0;
            }
        } else {
            graphVis.setMarkColor(Color.GREEN);
            while (!result.isEmpty()) {
                result.front().setMark(true);
                result.dequeue();
            }
        }
    }


    /**
     * Verarbeitet einen Mausklick.
     * @param e das Objekt enthält alle Informationen zum Klick
     */
    public void mouseClicked(MouseEvent e){}
}
