package kaynakKodlari;

import java.util.ArrayList;
import java.util.LinkedList;

public class BFSShortestPath {
	private int i,j,rear,front;
	private ArrayList<Boolean> visited;
	private ArrayList<Integer> parent, queue;
	private int ctrl;
	private double weight;
	private int qItems;
	public BFSShortestPath() {
		visited = new ArrayList<Boolean>();
		parent = new ArrayList<Integer>();
		queue = new ArrayList<Integer>();
	}
	public int getqItems() {
		return qItems;
	}
	public void setqItems(int qItems) {
		this.qItems = qItems;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getRear() {
		return rear;
	}
	public void setRear(int rear) {
		this.rear = rear;
	}
	public int getFront() {
		return front;
	}
	public void setFront(int front) {
		this.front = front;
	}
	public ArrayList<Integer> getQueue() {
		return queue;
	}
	public void setQueue(ArrayList<Integer> queue) {
		this.queue = queue;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	public ArrayList<Boolean> getVisited() {
		return visited;
	}
	public void setVisited(ArrayList<Boolean> visited) {
		this.visited = visited;
	}
	public ArrayList<Integer> getParent() {
		return parent;
	}
	public void setParent(ArrayList<Integer> parent) {
		this.parent = parent;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public void enqueue(int index) {
		queue.add(index);
	}
	public int dequeue() {
		return queue.remove(0);
	}
	//Aşağıdaki kod parçası, Veri yapıları ve algoritmalar dersi projesinde yazmamız istenen BFS kodu ile aynıdır. O yüzden tekrar açıklaması yapılmamıştır.
	public ArrayList<Integer> BFS(Bolge source, Bolge destination, LinkedList<Bolge> regions) {
		qItems=0;
		rear=0;
		front=0;
		for (int k = 0; k < 10; k++) {
			parent.add(i, -1);
			visited.add(false);
		}
		i=regions.indexOf(source);
		j=regions.indexOf(destination);
		enqueue(i);
		visited.set(i, true);
		while(queue.size()!=0) {
			ctrl = dequeue();
			if(ctrl!=j) {
				for (int k = 0; k < regions.size(); k++) {
					if(!visited.get(k)&&regions.get(k).isNeighbour(regions.get(ctrl))) {
					enqueue(k);
					visited.set(k, true);
					parent.set(k, ctrl);
					}
				}
			}
			else
				return parent;
		}
		System.out.println(ctrl);
		System.err.println(parent);
		for (int integer = 0; integer < parent.size() ;integer++) {
			if(parent.get(ctrl)!=-1) {
			System.out.println(regions.get(parent.get(ctrl)).getName());
			ctrl=parent.get(ctrl);
			}
		}
		return parent;
	}
	public int getCtrl() {
		return ctrl;
	}
	public void setCtrl(int ctrl) {
		this.ctrl = ctrl;
	}
}
