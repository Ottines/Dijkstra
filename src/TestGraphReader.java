public class TestGraphReader {//fonction main
    public static void main(String[] args) {
        if (args.length < 1)
            return ;

        GraphReader gr = new GraphReader(args[0]);
        Graph graph = new Graph();
        gr.read(graph);
        
        graph.display();
        graph.applyDijkstra("Railroad_station", "Convention_center");
        
    }
}