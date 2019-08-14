package kaynakKodlari;

import java.util.LinkedList;
import java.util.Timer;

public class InnerMove {
	private double toplamHareket;
	Arac a;
	Konum destination;
	double hiz;
	LinkedList<Timer> zamanlayicilar;
	
	public InnerMove(Arac a, Konum destination, double hiz, LinkedList<Timer> zamanlayicilar) {
		super();
		this.a = a;
		this.destination = destination;
		this.hiz = hiz;
		this.zamanlayicilar = zamanlayicilar;
	}
	public void run() {
		//Eğer o araç için zaten bir timer çalışıyorsa bu timer iptal ediliyor ve listeden siliniyor.
		if(zamanlayicilar.contains(a.getT())) {
			zamanlayicilar.remove(a.getT());
			a.getT().cancel();
		}
			//Hareket için gerekli hesaplamalar yapılıyor
			toplamHareket=0; //Hareketin bitip bitmediğini belirlemek için kullanılan değişken
			double uzaklik = destination.getDistance(a.getCurrentLocation());
			double yatayHareket = (destination.getX()-a.getCurrentLocation().getX())*(hiz/uzaklik)/40;
			double dikeyhareket = (destination.getY()-a.getCurrentLocation().getY())*(hiz/uzaklik)/40;
			
			//Saniyede 40 kere çağırılarak aracın konumunu güncelleyecek timer ayarlanıyor.
			a.setT( new Timer());
				a.getT().schedule( 
				        new java.util.TimerTask() {
				        	@Override
				            public void run() {
				            	if(toplamHareket+hiz/40<uzaklik) {//Bir sonraki adımda varış noktasının aşılıp aşılmayacağı kontrol ediliyor
				            		a.getCurrentLocation().setX(a.getCurrentLocation().getX()+yatayHareket); //Yatay hareket yapılıyor
					            	a.getCurrentLocation().setY(a.getCurrentLocation().getY()+dikeyhareket); //Dikey hareket yapılıyor
					            	toplamHareket += hiz/40;
				            	}
				            	else {//Eğer bir sonraki adımda varış noktası geçiliyorsa direkt o varış noktasının konum bilgilerine araç gönderiliyor.
				            		a.getCurrentLocation().setX(destination.getX());
				            		a.getCurrentLocation().setY(destination.getY());
				            		a.getT().cancel();
				            		zamanlayicilar.remove(a.getT()); //İşlem bittiği için Timer iptal ediliyor ve listeden siliniyor.
				            	}
				            	
				            }
				        }, 
				        0,25
				);

		
		zamanlayicilar.add(a.getT()); //Timer listeye ekleniyor. 
	}
	public Konum getDestination() {
		return destination;
	}
	public void setDestination(Konum destination) {
		this.destination = destination;
	}

}
