import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
    private Graph graph;

    @Override
    public void start(Stage primaryStage) {
        graph = new Graph(5);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 1);
        graph.addEdge(2, 1, 2);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 8);
        graph.addEdge(3, 4, 3);

        List<Integer> shortestPath = Dijkstra.findShortestPath(graph, 0, 4);

        Pane pane = new Pane();

        double[][] coordinates = {
                {100, 100}, {300, 100}, {200, 200}, {400, 200}, {500, 100}
        };

        // Draw nodes
        for (int i = 0; i < coordinates.length; i++) {
            Circle node = new Circle(coordinates[i][0], coordinates[i][1], 20, Color.LIGHTBLUE);
            Text label = new Text(coordinates[i][0] - 5, coordinates[i][1] - 25, String.valueOf(i));
            pane.getChildren().addAll(node, label);
        }

        // Draw edges
        for (int i = 0; i < graph.getAdjList().size(); i++) {
            for (Edge edge : graph.getAdjList().get(i)) {
                Line line = new Line(
                        coordinates[i][0], coordinates[i][1],
                        coordinates[edge.destination][0], coordinates[edge.destination][1]
                );
                pane.getChildren().add(line);
            }
        }

        // Highlight the shortest path
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            int u = shortestPath.get(i);
            int v = shortestPath.get(i + 1);
            Line highlight = new Line(
                    coordinates[u][0], coordinates[u][1],
                    coordinates[v][0], coordinates[v][1]
            );
            highlight.setStroke(Color.RED);
            highlight.setStrokeWidth(3);
            pane.getChildren().add(highlight);
        }

        // Display the shortest path as text
        StringBuilder pathString = new StringBuilder("Shortest path will be like from ");
        pathString.append(shortestPath.get(0)).append(" to ").append(shortestPath.get(shortestPath.size() - 1)).append(" is ");
        for (int i = 0; i < shortestPath.size(); i++) {
            pathString.append(shortestPath.get(i));
            if (i < shortestPath.size() - 1) {
                pathString.append(" → ");
            }
        }

        // Add text to the bottom of the window
        Text pathText = new Text(10, 350, pathString.toString());
        pathText.setStyle("-fx-font-size: 16px; -fx-font-family: Arial;");  // You can customize the style here
        pane.getChildren().add(pathText);

        // Set the scene and show the window
        primaryStage.setScene(new Scene(pane, 600, 400));
        primaryStage.setTitle("PathFinder Visualization");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
