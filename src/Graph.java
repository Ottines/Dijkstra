import java.util.*;

public class Graph implements IGraph {
	
	private Hashtable<String,Vertex> vertices;
	
    public Graph() {
        vertices = new Hashtable<String,Vertex>();
    }

    public IVertex addVertex(String name) {
        if (vertices.containsKey(name)) {
            return vertices.get(name);
        }
        
        Vertex v = new Vertex();
        v.setName(name);
        vertices.put(name, v);
        return v;
    }

    public IVertex getVertex(String name) {
        return vertices.get(name);

    }

    public void display() {
        Iterator<Vertex> it = vertices.values().iterator();
        while(it.hasNext()) {            
            Vertex v = it.next();
            System.out.println(v.getName());
            v.display();
        }
    }
    
    /**
     * Source Ecampus
     * @param name
     * @return
     */
    private Vertex findByName(String name) {
    	Iterator<Vertex >it =  vertices.values().iterator();
    	while (it.hasNext()) {
    		Vertex v =  it.next();
    		if(v.getName().equals(name)) {
    			return v;
    			}
    		}
    	return null;
    }
    
    /**
     * Initialisation de l'algorithme de Dijkstra
     * @param startName
     */
    private void initDijkstra(String startName) {
    	Iterator<Vertex> it = vertices.values().iterator();
        while (it.hasNext()) {
        	Vertex v = it.next();
        	v.reset();
        	if(v.getName().equals(startName))
        		v.setAsStart();
        }
    }
    
    /**
     * Recherche du vecteur dont la distance est minimale
     * @return
     */
    private Vertex findMinimal() { //TODO problème dans la fonction je crois
    	Iterator<Vertex> it = vertices.values().iterator();
    	int mini = Integer.MAX_VALUE;
    	Vertex vMini = null;
        while (it.hasNext()) {
        	Vertex v = it.next();
        	int dVertex = v.getDistance();
        	if (dVertex < mini)
        	{
        		mini = dVertex;
    			vMini = v;
        	}	
        }
        return vMini;
    }
    
    /**
     * 
     * @param startName
     * @param endName
     */
    public void applyDijkstra(String startName, String endName) {
    	initDijkstra(startName);
    	Vertex v = findByName(endName);
    	while(!v.isMarked()) {
    		Vertex vv = findMinimal();
    		vv.mark();
    		vv.propagate();
    	}
    	v.displayPath();
    }

}
