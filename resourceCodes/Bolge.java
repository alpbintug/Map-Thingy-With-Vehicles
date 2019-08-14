package kaynakKodlari;

import java.util.LinkedList;

public class Bolge {
	private String name;
	private Konum location;
	private boolean visited = false;
	private LinkedList<Komsu> neighbours;
	private LinkedList<Bolge> path;
	public Bolge(String name, Konum location) {
		this.location= new Konum();
		this.name = name;
		neighbours = new LinkedList<Komsu>();
		path = new LinkedList<Bolge>();
		this.location.setKonum(location);
	}
	public Bolge() {
		path = new LinkedList<Bolge>();
		location = new Konum();
		neighbours = new LinkedList<Komsu>();
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public LinkedList<Bolge> getPath() {
		return path;
	}
	public void setPath(LinkedList<Bolge> path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Konum getLocation() {
		return location;
	}
	public void setLocation(Konum location) {
		this.location.setKonum(location);
	}
	public void setBolge(Bolge bolge) {
		this.location.setKonum(bolge.getLocation());
		this.name=bolge.getName();
	}
	public LinkedList<Komsu> getNeighbours() {
		return neighbours;
	}
	public void setNeighbours(LinkedList<Komsu> neighbours) {
		this.neighbours = neighbours;
	}
	public double getDistance(Konum a) {
		return Math.sqrt((a.getX()-this.getLocation().getX())*(a.getX()-this.getLocation().getX())+(a.getY()-this.getLocation().getY())*(a.getY()-this.getLocation().getY()));
	}

	public boolean isNeighbour(Bolge control) {
		for (Komsu komsu : neighbours) {
			if(komsu.getBolge()==control)
				return true;
		}
		return false;
	}

}
