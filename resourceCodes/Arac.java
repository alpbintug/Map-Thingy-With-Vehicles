package kaynakKodlari;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class Arac{
	private Bolge bolge;
	private Timer t;
	private String plate;
	private String type;
	private Konum currentLocation;
	private Gorev mission;
	public Arac(String plate, String type) {
		bolge = new Bolge();
		t = new Timer();
		mission = new Gorev();
		currentLocation = new Konum();
		this.plate = plate;
		this.type = type;
	}
	public Arac() {
		bolge = new Bolge();
		t = new Timer();
		mission = new Gorev();
		currentLocation = new Konum();
	}
	public Bolge getBolge() {
		return bolge;
	}
	public void setBolge(Bolge bolge) {
		this.bolge = bolge;
	}
	public Konum getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(Konum currentLocation) {
		this.currentLocation = currentLocation;
	}

	public Gorev getMission() {
		return mission;
	}
	public void setMission(Gorev mission) {
		this.mission = mission;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	//Aracın görevde olup olmadığı kontrol ediliyor.
	public  boolean isOnAMission() {
		if(!(mission.getMissionDescription().compareTo("Arac aktif bir gorevde degil")==0)||mission.getMissionDescription().isEmpty())
			return true;
			return false;
	}
	int j;
	double hareketSuresi;
	public void moveVehicle(Konum destination, double hiz, LinkedList<Timer> zamanlayicilar, LinkedList<Bolge> bolgeler) {
		j=0;
		hareketSuresi=0;
		Bolge enYakinBolge = currentLocation.findClosestRegion(bolgeler); //Başlangıç noktasına en yakın bölge bulunuyor
		Bolge hedefBolge = destination.findClosestRegion(bolgeler); //Hedef noktaya en yakın bölge bulunuyor
		LinkedList<Thread> threadList = new LinkedList<Thread>(); //Her bir hareket işlemini tutacak threadlerin tutulacağı thread listesi tanımlanıyor
		Konum goTo = new Konum();
		LinkedList<Double> hareketSureleri = new LinkedList<Double>(); //Her bir threadin ne kadar hayatta kalacağını belirten hareket sürelerinin tutulacağı dizi
		BFSShortestPath bfs = new BFSShortestPath(); //En kısa yolu hesaplayacak nesne yaratılıp yol oluşturuluyor
		ArrayList<Integer> parent = bfs.BFS(hedefBolge, enYakinBolge, bolgeler); //Yol üzerindeki bölgelerin indisleri tutuluyor
		goTo = currentLocation;
		int ctrl = bfs.getCtrl();
		if(parent!=null) {
			for(int i =0; i< bolgeler.size(); i++) {
				if(parent.get(ctrl)!=-1) {//Bölgenin geçerli olup olmadığı kontrol ediliyor
					hareketSuresi=goTo.getDistance(bolgeler.get(ctrl).getLocation())/hiz;//Hareketin ne kadar süreceği hesaplanıyor
					hareketSureleri.add(hareketSuresi);
					goTo=bolgeler.get(ctrl).getLocation();
					Move move = new Move(this, goTo, hiz, zamanlayicilar, bolgeler);//Yeni hareket tanımlanıyor
					Thread thread = new Thread(move);//Yeni Thread oluşturuluyor ve listeye ekleniyor
					threadList.add(thread);
				ctrl = parent.get(ctrl);//Bir sonraki bölgeye geçiliyor
				 
				}
			}
			}
		hareketSuresi=goTo.getDistance(hedefBolge.getLocation())/hiz; //Hedef bölgeye hareket için gerekli atamalar yapılıyor çünkü BFS, ürettiği yolda hedef bölgeyi bulundurmuyor
		goTo=bolgeler.get(ctrl).getLocation();
		hareketSureleri.add(hareketSuresi);
		Move move = new Move(this, hedefBolge.getLocation(), hiz, zamanlayicilar, bolgeler);
		Thread thread = new Thread(move);
		threadList.add(thread);
		//Hedef bölgeye ulaştıktan sonra gidilecek hedef konum için gerekli atamalar yapılıyor.
		hareketSuresi=goTo.getDistance(destination)/hiz;
		hareketSureleri.add(hareketSuresi);
		Move move2 = new Move(this, destination, hiz, zamanlayicilar, bolgeler);
		Thread thread2 = new Thread(move2);
		threadList.add(thread2);
		hareketSuresi=0;
		
		//Hareket işlemi başlatılıyor
		startTimer(threadList, hareketSureleri, destination);
			
	
	}
	private void startTimer(LinkedList<Thread> threadList,LinkedList<Double> hareketSureleri, Konum destination) {
		Timer timer = new Timer();//Yeni timer oluşturuluyor
		timer.schedule( new TimerTask() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(j<threadList.size()) {
					if(j>0) {
						threadList.get(j-1).stop();//Daha önceden çalışan threadler durduruluyor
						}
					threadList.get(j).start();//Sırası gelen thread çalıştırılıyor
				if(currentLocation==destination) {//Eğer hedef noktaya ulaşıldıysa bu timer kapatılıyor.
					timer.cancel();
					}
				else {
					if(j<hareketSureleri.size())
					hareketSuresi=hareketSureleri.get(j);//Yeni tanımlanacak timer için yeni bir hareket süresi alınıyor
					j++;//İndis artırılıp sıradaki öğeye geçilmesi sağlanıyor
					timer.cancel();//Bu timer iptal ediliyor
					startTimer(threadList, hareketSureleri, destination); //Fonksiyon artmış indisle çağrılarak sıradaki öğenin hareket işlemleri başlatılıyor.
				}
				}
				
			}
		}, (long)(hareketSuresi*1000)+300);//Timer, hareket süresi kadar gecikmeye sahip, yani hareket süresi kadar bekledikten sonra kendisini imha edip sıradaki öğenin timerini oluşturuyor.
	}
	public Timer getT() {
		return t;
	}
	public void setT(Timer t) {
		this.t = t;
	}
	public Bolge findClosestRegion(LinkedList<Bolge> bolgeler) {
		double yakin = 90000;
		int index=-1;
		for (Bolge bolge : bolgeler) {
			if(bolge.getDistance(currentLocation)<yakin) {
				yakin=bolge.getDistance(currentLocation);
				index=bolgeler.indexOf(bolge);
			}
		}
		return bolgeler.get(index);
	}

}
