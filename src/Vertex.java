import java.util.*;

public class Vertex implements IVertex {
	
	private String name;
    private Hashtable<IVertex,Integer> successors;
	private boolean marked; //Sommet marqu�
	private int distance; //Distance vecteur
    private Vertex previous; //Sommet pr�cedent
    
    public Vertex() {
        successors = new Hashtable<IVertex,Integer>();
        
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @SuppressWarnings("deprecation")
	public void addNeighbour(IVertex vertex,int weight) {
        successors.put(vertex,new Integer(weight));
    }

    public void display() {
        Iterator<IVertex> it = successors.keySet().iterator();
        while (it.hasNext()) {
            IVertex key = it.next();
            System.out.println("  - " + key.getName() + " (" + successors.get(key) +" min)");
        }
    }
    
    /**
     * Reset des sommets
     * Retire le marquage, distance infini , sommet pr�cedent null
     */
    public void reset() {
    	this.setMarked(false);
    	this.setDistance(Integer.MAX_VALUE);
    	this.previous = null;
    }
    
    /**
     * Initialisation de la distance du Sommet de d�part
     * Distance au sommet = 0
     */
    public void setAsStart() {
    	this.setDistance(0);
    }
    
    /**
     * M�thode pour parquer un sommet
     */
    public void mark() {
    	setMarked(true);
    }
    
    /**
     * M�thode pour savoir si un sommet est marqu�
     * @return
     */
    public boolean isMarked() {
    	if (this.marked == true)
    		return true;
    	return false;
    }
    
    /**
     * M�thode pour propager les distances
     */
    public void propagate() {
    	Iterator<IVertex> it = successors.keySet().iterator();
        while (it.hasNext()) {
        	Vertex v = (Vertex)it.next();
        	int dVertex = successors.get(v).intValue();
        	if (distance + dVertex < v.getDistance()) {
        		v.previous = this;
        		v.distance = distance + dVertex;
        	}
        }
    }
    
    /**
     * M�thode pour afficher le chemin optimal dans le bon ordre
     * On m'a piqu� mon travail.....
     */
    public void displayPath() {
    	Iterator<IVertex> it = successors.keySet().iterator();
    	Stack<String> pile = new Stack<String>();
    	while (it.hasNext() ) {
    		Vertex v = (Vertex)it.next();
    		pile.push(v.previous.getName());
    	}
    	while(!pile.isEmpty())
    		System.out.println(pile.pop());
    }

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}

}