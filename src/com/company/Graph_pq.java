package com.company;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
class Graph_pq {
    int dist[];
    Set<Integer> visited;
    PriorityQueue<Node> pQueue;
    int V; // Number of vertices 
    List<List<Node> > adj_list;
    //class constructor
    public Graph_pq(int V) {
        this.V = V;
        dist = new int[V];
        visited = new HashSet<Integer>();
        pQueue = new PriorityQueue<Node>(V, new Node());
    }

    // Dijkstra's Algorithm implementation 
    public void algo_dijkstra(List<List<Node> > adj_list, int src_vertex)
    {
        this.adj_list = adj_list;

        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        // first add source vertex to PriorityQueue 
        pQueue.add(new Node(src_vertex, 0));

        // Distance to the source from itself is 0 
        dist[src_vertex] = 0;
        while (visited.size() != V) {

            // u is removed from PriorityQueue and has min distance
            int u = pQueue.remove().node;

            // add node to finalized list (visited)
            visited.add(u);
            graph_adjacentNodes(u);
        }
    }
    // this methods processes all neighbours of the just visited node
    private void graph_adjacentNodes(int u)   {
        int edgeDistance = -1;
        int newDistance = -1;

        // process all neighbouring nodes of u 
        for (int i = 0; i < adj_list.get(u).size(); i++) {
            Node v = adj_list.get(u).get(i);

            //  proceed only if current node is not in 'visited'
            if (!visited.contains(v.node)) {
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;

                // compare distances 
                if (newDistance < dist[v.node])
                    dist[v.node] = newDistance;

                // Add the current vertex to the PriorityQueue 
                pQueue.add(new Node(v.node, dist[v.node]));
            }
        }
    }
}
class Main{
    private final static String FILE_URL = "C:\\LICENCE 2\\S4\\Algorithmique 1\\graphe.txt";
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(FILE_URL);
        Scanner scanner = new Scanner(fileInputStream);
        int source = 0;
        int V = Integer.parseInt(scanner.nextLine());
        ArrayList<Integer> origine = new ArrayList<>();
        ArrayList<Integer> extremity = new ArrayList<>();
        ArrayList<Integer> longer = new ArrayList<>();
        try {
            while (scanner.hasNextLine()) {
                String[] result = scanner.nextLine().split(" ");
                origine.add(Integer.parseInt(result[0]));
                extremity.add(Integer.parseInt(result[1]));
                longer.add(Integer.parseInt(result[2]));
            }
        } finally {
            try {
                scanner.close();
                fileInputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        Graph_pq dpq = new Graph_pq(V);
        List<List<Node>> adj_list = adjacent(origine,extremity,longer,V);
        dpq.algo_dijkstra(adj_list, source);
        // Print the shortest path from source node to all the nodes 
        System.out.println("The shorted path from source node to other nodes:");
        System.out.println("Source\t\t" + "Node#\t\t" + "Distance");
        for (int i = 0; i < dpq.dist.length; i++)
            System.out.println(source + " \t\t\t " + i + " \t\t\t "  + dpq.dist[i]);
    }
    public static List<List<Node>> adjacent(ArrayList<Integer> origine, ArrayList<Integer> extremity, ArrayList<Integer> longer,int V){
        // adjacency list representation of graph
        List<List<Node>> adj_list = new ArrayList< >();
        // Initialize adjacency list for every node in the graph
        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<>();
            adj_list.add(item);
        }
        for (int i = 0; i < origine.size(); i++){
            adj_list.get(origine.get(i)).add(new Node(extremity.get(i),longer.get(i)));
        }
        return adj_list;

    }
}

// Node class  
class Node implements Comparator<Node> {
    public int node;
    public int cost;
    public Node() { } //empty constructor 

    public Node(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
    @Override
    public int compare(Node node1, Node node2)
    {
        if (node1.cost < node2.cost)
            return -1;
        if (node1.cost > node2.cost)
            return 1;
        return 0;
    }
}