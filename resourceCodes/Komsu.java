package kaynakKodlari;

public class Komsu {
	private Bolge bolge;
	private double uzaklik=90000;
	public Komsu(Bolge bolge, double uzaklik) {
		this.bolge = bolge;
		this.uzaklik = uzaklik;
	}
	public Komsu() {
	}
	public double getUzaklik(){
		return uzaklik;
	}
	public void setUzaklik(double uzaklik) {
		this.uzaklik = uzaklik;
	}
	public Bolge getBolge() {
		return bolge;
	}
	public void setBolge(Bolge bolge) {
		this.bolge = bolge;
	}
	

}
