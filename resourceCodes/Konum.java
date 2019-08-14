package kaynakKodlari;

import java.util.LinkedList;

public class Konum {
	private double x;
	private double y;
	public Konum() {
	}
	public Konum(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public boolean isEqual(Konum test) {
		if(x==test.getX()&&y==test.getY()) {
			return true;
		}
		return false;
	}
	public double getDistance(Konum a) {
		return Math.sqrt((a.getX()-x)*(a.getX()-x)+(a.getY()-y)*(a.getY()-y));//İki nokta arasındaki uzaklığı iki boyutlu uzayda hesaplayan matematik formülü kullanılıyor
	}
	public void setKonum(Konum yer) {
		y=yer.getY();
		x=yer.getX();
	}
	//Konuma en yakın bölgenin bulunduğu metod
	public Bolge findClosestRegion(LinkedList<Bolge> bolgeler) {
		int index = -1;
		double dist = this.getDistance(bolgeler.getFirst().getLocation());
		for (Bolge bolge : bolgeler) {
			if(dist>=this.getDistance(bolge.getLocation())) {
				dist=this.getDistance(bolge.getLocation());
				index = bolgeler.indexOf(bolge);
			}
		}
		return bolgeler.get(index);
	}

}
