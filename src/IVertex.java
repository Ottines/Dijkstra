public interface IVertex {
	
    public void setName(String name);
    public String getName();
    public void addNeighbour(IVertex vertex,int weight);
    public void reset();
    public void setAsStart();
    public int getDistance();
    public void setDistance(int distance);
    public void mark();
    public boolean isMarked();
    public void propagate();
    public void displayPath();
    
}