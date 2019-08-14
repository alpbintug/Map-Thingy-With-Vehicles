package kaynakKodlari;

import java.util.LinkedList;
import java.util.Timer;

public class Move implements Runnable{
	private Arac a;
	Konum destination;
	double hiz;
	LinkedList<Timer> zamanlayicilar;
	double toplamHareket;
	LinkedList<Bolge> bolgeler;
	Konum goTo;
	public Move(Arac a, Konum destination, double hiz, LinkedList<Timer> zamanlayicilar, LinkedList<Bolge> bolgeler) {
		super();
		this.bolgeler=bolgeler;
		this.a = a;
		this.destination = destination;
		this.hiz = hiz;
		this.zamanlayicilar = zamanlayicilar;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		InnerMove movement = new InnerMove(a, destination, hiz, zamanlayicilar);
		movement.run();
		
	}
	public Konum getDestination() {
		return destination;
	}
	public void setDestination(Konum destination) {
		this.destination = destination;
	}
	public double getHiz() {
		return hiz;
	}
	public void setHiz(double hiz) {
		this.hiz = hiz;
	}
	public LinkedList<Timer> getZamanlayicilar() {
		return zamanlayicilar;
	}
	public void setZamanlayicilar(LinkedList<Timer> zamanlayicilar) {
		this.zamanlayicilar = zamanlayicilar;
	}
	public double getToplamHareket() {
		return toplamHareket;
	}
	public void setToplamHareket(double toplamHareket) {
		this.toplamHareket = toplamHareket;
	}

}
