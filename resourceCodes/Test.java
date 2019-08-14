package kaynakKodlari;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Timer;
import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.UIManager;
import arayuz.AnaEkran;
import arayuz.HataMesaji;
import arayuz.TiklamaYollayicisi;

public class Test 
{
	static FileWriter fw = null;//Dosyaya yazma işlemini yapan nesne


	public static void main(String[] args) 
	{
		final BufferedImage image = new BufferedImage(1280, 720, BufferedImage.TYPE_INT_BGR);//Harita çizdirmede kullanılan nesne
		JPanel canvas = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, this);
			}
		};
		//Kullanıcıya gerekli hataları verecek olan pencere nesnesi oluşturuluyor ve gerekli ayarlamalar yapılıyor.
		HataMesaji hataMesaji = new HataMesaji();
		hataMesaji.setLocation(640,360);
		hataMesaji.setAlwaysOnTop(true);//Hata mesajı açık olduğu sürece ana ekranın önünde duracak
		hataMesaji.setUndecorated(true);//Çerçeveleri kaldırılarak kontrolsüz kapatma işlemi engelleniyor ve tek kapatma işlemi "tamam" butonuna basmayla gerçekleşiyor.
		hataMesaji.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		AnaEkran anaEkran = new AnaEkran();//Birleştirilmiş arayüz nesnesi
		JFrame frame = new JFrame();//Haritayı boyamada kullanılacak Frame
		TiklamaYollayicisi tiklaGitsin = new TiklamaYollayicisi();//Haritada hareket ettirilen araçların görev bilgisini alması için oluşturulmuş arayüz parçası
		//Haritanın boyutları ayarlanıyor
		frame.setLayout(new BorderLayout());
		frame.add(canvas, BorderLayout.CENTER);
		frame.setSize(1280, 720);
		//Boyamada kullanılacak değişkenlere renk atamaları yapılıyor
		canvas.setBackground(Color.black);
		Graphics centers = image.getGraphics();
		centers.setColor(Color.green);
		Graphics vehicles = image.getGraphics();
		vehicles.setColor(Color.yellow);
		Graphics borders = image.getGraphics();
		borders.setColor(Color.blue);
		Graphics roads = image.getGraphics();
		roads.setColor(Color.DARK_GRAY);
		Graphics selectedVehicle = image.getGraphics();
		selectedVehicle.setColor(Color.red);
		//Araç hareketlerinin daha yumuşak hissettirmesi için saniyenin kırkta birinde harita sıfırlanıp tekrar yazdırılıyor, bu değişken her yeniden boyama işleminin başında haritanın sıfırlanması için kullanılıyor.
		Graphics resetCanvas = image.getGraphics();
		resetCanvas.clearRect(0, 0, 1280, 720);
		//Arayüzlerin işletim sisteminin kendi çerçevelerini kullanması için yazılan kod bloğu. Böylece Mac/Win10/Linux gibi işletim sistemlerinde Win7 çerçeveleri kullanılmayacak.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		//Kullanılan Linkli listelerin tanımlandığı kod bloğu
		LinkedList<Arac> araclar = new LinkedList<Arac>();//Mevcut araçları tutan linkli liste
		LinkedList<Bolge> bolgeler = new LinkedList<Bolge>();//Bölgeleri tutan linkli liste
		LinkedList<Konum> sinirlar = new LinkedList<Konum>();//Sınırları tutan linkli liste
		LinkedList<Timer> zamanlayicilar = new LinkedList<Timer>();//Araçların hareketini kontrol eden zamanlayıcılar listesi

		//Bölgelerin tanımlandığı kod bloğu
		Bolge regionA = new Bolge("Alleno", new Konum(98, 96));
		bolgeler.add(regionA);
		Bolge regionB = new Bolge("Bemeria", new Konum(412,322));
		bolgeler.add(regionB);
		Bolge regionC = new Bolge("Centuan", new Konum(1233,20));
		bolgeler.add(regionC);
		Bolge regionD = new Bolge("Dickos", new Konum(85,560));
		bolgeler.add(regionD);
		Bolge regionE = new Bolge("Eve", new Konum(493,596));
		bolgeler.add(regionE);
		Bolge regionF = new Bolge("Fespect", new Konum(698,570));
		bolgeler.add(regionF);
		Bolge regionG = new Bolge("Gespot", new Konum(1049,591));
		bolgeler.add(regionG);
		Bolge regionH = new Bolge("Hulkish", new Konum(1181,471));
		bolgeler.add(regionH);
		Bolge regionI = new Bolge("Ivory Boots", new Konum(744,329));
		bolgeler.add(regionI);
		Bolge regionJ = new Bolge("Janeica", new Konum(243,424));
		bolgeler.add(regionJ);
		
		
		//Komşulukların tanımlandığı kod blokları
		regionA.getNeighbours().add(new Komsu(regionB,regionA.getDistance(regionB.getLocation())));
		regionA.getNeighbours().add(new Komsu(regionD,regionA.getDistance(regionD.getLocation())));
		regionA.getNeighbours().add(new Komsu(regionJ,regionA.getDistance(regionJ.getLocation())));
		
		regionB.getNeighbours().add(new Komsu(regionJ,regionB.getDistance(regionJ.getLocation())));
		regionB.getNeighbours().add(new Komsu(regionE,regionB.getDistance(regionE.getLocation())));
		regionB.getNeighbours().add(new Komsu(regionF,regionB.getDistance(regionF.getLocation())));
		regionB.getNeighbours().add(new Komsu(regionI,regionB.getDistance(regionI.getLocation())));
		regionB.getNeighbours().add(new Komsu(regionA,regionB.getDistance(regionA.getLocation())));

		regionC.getNeighbours().add(new Komsu(regionI,regionC.getDistance(regionI.getLocation())));
		regionC.getNeighbours().add(new Komsu(regionH,regionC.getDistance(regionH.getLocation())));
		
		regionD.getNeighbours().add(new Komsu(regionA,regionD.getDistance(regionA.getLocation())));
		regionD.getNeighbours().add(new Komsu(regionJ,regionD.getDistance(regionJ.getLocation())));
		regionD.getNeighbours().add(new Komsu(regionE,regionD.getDistance(regionE.getLocation())));
		
		regionE.getNeighbours().add(new Komsu(regionD,regionE.getDistance(regionD.getLocation())));
		regionE.getNeighbours().add(new Komsu(regionJ,regionE.getDistance(regionJ.getLocation())));
		regionE.getNeighbours().add(new Komsu(regionB,regionE.getDistance(regionB.getLocation())));
		regionE.getNeighbours().add(new Komsu(regionF,regionE.getDistance(regionF.getLocation())));
		regionE.getNeighbours().add(new Komsu(regionI,regionE.getDistance(regionI.getLocation())));
		
		regionF.getNeighbours().add(new Komsu(regionE,regionF.getDistance(regionE.getLocation())));
		regionF.getNeighbours().add(new Komsu(regionB,regionF.getDistance(regionB.getLocation())));
		regionF.getNeighbours().add(new Komsu(regionI,regionF.getDistance(regionI.getLocation())));
		regionF.getNeighbours().add(new Komsu(regionG,regionF.getDistance(regionG.getLocation())));
		
		regionG.getNeighbours().add(new Komsu(regionF,regionG.getDistance(regionF.getLocation())));
		regionG.getNeighbours().add(new Komsu(regionI,regionG.getDistance(regionI.getLocation())));
		regionG.getNeighbours().add(new Komsu(regionH,regionG.getDistance(regionH.getLocation())));
		
		regionH.getNeighbours().add(new Komsu(regionG,regionH.getDistance(regionG.getLocation())));
		regionH.getNeighbours().add(new Komsu(regionI,regionH.getDistance(regionI.getLocation())));
		regionH.getNeighbours().add(new Komsu(regionC,regionH.getDistance(regionC.getLocation())));
		
		regionI.getNeighbours().add(new Komsu(regionB,regionI.getDistance(regionB.getLocation())));
		regionI.getNeighbours().add(new Komsu(regionF,regionI.getDistance(regionF.getLocation())));
		regionI.getNeighbours().add(new Komsu(regionH,regionI.getDistance(regionH.getLocation())));
		regionI.getNeighbours().add(new Komsu(regionG,regionI.getDistance(regionG.getLocation())));
		regionI.getNeighbours().add(new Komsu(regionC,regionI.getDistance(regionC.getLocation())));
		regionI.getNeighbours().add(new Komsu(regionE,regionI.getDistance(regionE.getLocation())));
		
		regionJ.getNeighbours().add(new Komsu(regionA,regionJ.getDistance(regionA.getLocation())));
		regionJ.getNeighbours().add(new Komsu(regionD,regionJ.getDistance(regionD.getLocation())));
		regionJ.getNeighbours().add(new Komsu(regionB,regionJ.getDistance(regionB.getLocation())));
		regionJ.getNeighbours().add(new Komsu(regionE,regionJ.getDistance(regionE.getLocation())));
		
		//Dosyadan okuma işlemi için kullanılacak FileInputStream'in kurulduğu kod bloğu
		FileInputStream input = null;
		try {
			input = new FileInputStream("input.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Okuma işleminin yapılması
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		String line2;
		try {
			while((line2 = br.readLine())!=null) {//Dosyanın sonuna gelene kadar satır satır okuma işlemi yapılıyor
				int k;//Stringin hangi indisleri arasında okuma yapacağını belirleyen değişken
				String plate = line2.substring(0, k=line2.indexOf(','));//ilk kez ',' işareti ile karşılaşılana kadar plaka okunuyor
				String type = line2.substring(k+1,k=line2.indexOf(',',k+1));//ilk ',' işaretinden bir sonra başlayan ve ikinci ',' işaretine kadar olan kısımda araç tipi okunuyor
				String missionDescription = line2.substring(k+1, k=line2.indexOf(',',k+1));//Sonraki ',' işaretine kadar görev açıklaması okunuyor.
				double x = Double.parseDouble(line2.substring(k+1, k=line2.indexOf(',', k+1)));//Yatay konum okunuyor
				double y = Double.parseDouble(line2.substring(k+1));//Dikey konum okunuyor
				//Gerekli nesneler oluşturulup araç listesine ekleme yapılıyor ve bu liste gösteriliyor
				Arac a = new Arac(plate, type);
				a.setCurrentLocation(new Konum(x, y));
				a.getMission().setMissionDescription(missionDescription);
				araclar.add(a);
				anaEkran.listModel.addElement("Plaka: " + araclar.getLast().getPlate()+ " Tip: " + araclar.getLast().getType());
			}
			//Okuma işlemleri bittikten sonra input kapatılıyor.
			try {
				input.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		anaEkran.setVisible(true);//Arayüz görünür yapılıyor
		frame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				//Fare ile araç seçilmesi ve hareket ettirilmesi için yazılmış kod bloğu
				int x = arg0.getX();//Fare tıklamasının gerçekleştiği yatay konum
				int y = arg0.getY();//Fare tıklamasının gerçekleştiği dikey konum
				if(arg0.getButton()==MouseEvent.BUTTON1) {//Eğer sol fare tıkı gerçekleştiyse farenin mevcut konumu, araçlar listesinde aranıyor, eğer yakın bir araç varsa o aracın bilgileri "Araç görüntüle" bölümüne bastırılıyor.
					for (Arac arac : araclar) {
						if((x-29<arac.getCurrentLocation().getX()&&x+29>arac.getCurrentLocation().getX())&&(y-58<arac.getCurrentLocation().getY()&&y+58>arac.getCurrentLocation().getY())) {
							anaEkran.getLblArac().setText(arac.getPlate());
							anaEkran.getTextAreaVehicleInfo().setText("Plaka: "+ arac.getPlate() +"\nTip: " + arac.getType() + "\nKonum: " + arac.getCurrentLocation().getX() + " , " + arac.getCurrentLocation().getY() + "\nGorev:" + arac.getMission().getMissionDescription());
							anaEkran.getBtnSendToLocation().setEnabled(true);
							anaEkran.getBtnSendToRegion().setEnabled(true);
						}
					}
				}
				//Eğer sağ tık yapılmışsa ve seçilen araç kısmı boş değilse son seçilen aracı istenilen bölgeye gönderiyor.
				else if(arg0.getButton()==MouseEvent.BUTTON3&&!anaEkran.getTextAreaVehicleInfo().getText().isEmpty()) {
					tiklaGitsin.setLocation(arg0.getLocationOnScreen());//Görev bilgisinin alınacağı Arayüzün konumu fare ile eşleniyor
					tiklaGitsin.setVisible(true);//Görev bilgisini alacak olan arayüz görünür yapılıyor
					for (Arac arac : araclar) {
						if(arac.getPlate().compareTo(anaEkran.getLblArac().getText())==0) {
							arac.moveVehicle(new Konum(x-9, y-38),30,zamanlayicilar,bolgeler);//Araç hareket ettiriliyor.
						}
					}
				}
				
			}
		});
		//Hata mesajı gösterildiğinde bu mesajın yeterince can sıkıcı ve dikkate alınacak bir mesaj olmasını sağlamak için ana ekran .setEnabled(false) komutu ile çalışamaz hale getiriliyor.
		//Ana ekranı tekrar çalışabilir hale getirmenin tek yolu ise hata mesajı arayüzünde bulunan tamam butonuna tıklamak olarak ayarlanıyor.
		hataMesaji.getBtnTamam().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				anaEkran.setEnabled(true);
				hataMesaji.setVisible(false);
			}
		});
		//Haritadan gönderilen Araç için görev açıklaması bilgisinin alınması için yazılmış kod bloğu
		tiklaGitsin.getBtnAraciGonder().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				for (Arac arac : araclar) {
					if(arac.getPlate().compareTo(anaEkran.getLblArac().getText())==0)
						if(!tiklaGitsin.getTxtMissionDescription().getText().isEmpty())
						arac.getMission().setMissionDescription(tiklaGitsin.getTxtMissionDescription().getText());
						else
						arac.getMission().setMissionDescription("Arac aktif bir gorevde degil");
				}
				tiklaGitsin.setVisible(false);
			}
		});

		//Araç ekleme, silme, hareket ettirme gibi değişiklikleri, dosyaya en son yapılmış kaydı okuyarak geri alan kod bloğu
		anaEkran.getBtnDegisiklikleriGeriAl().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FileInputStream input2 = null;
				
				try {
					input2 = new FileInputStream("input.txt");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				araclar.clear();
				BufferedReader br2 = new BufferedReader(new InputStreamReader(input2));
				anaEkran.listModel.setSize(0);
				String line2;
				try {
					while((line2 = br2.readLine())!=null) {
						int k;
						String plate = line2.substring(0, k=line2.indexOf(','));
						String type = line2.substring(k+1,k=line2.indexOf(',',k+1));
						String missionDescription = line2.substring(k+1, k=line2.indexOf(',',k+1));
						double x = Double.parseDouble(line2.substring(k+1, k=line2.indexOf(',', k+1)));
						double y = Double.parseDouble(line2.substring(k+1));
						Arac a = new Arac(plate, type);
						a.setCurrentLocation(new Konum(x, y));
						a.getMission().setMissionDescription(missionDescription);
						araclar.add(a);
						anaEkran.listModel.addElement("Plaka: " + araclar.getLast().getPlate()+ " Tip: " + araclar.getLast().getType());

					}
					try {
						input2.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}



				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
			}
		});
		//Yapılan değişiklikleri -Daha doğrusu araçlar listesinin son halini dosyaya yazdıran kod bloğu
		anaEkran.getBtnCikisYap().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					fw = new FileWriter("input.txt");
				} catch (IOException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
				for (Arac arac : araclar) {
					String s= arac.getPlate()+","+arac.getType()+","+arac.getMission().getMissionDescription()+","+arac.getCurrentLocation().getX()+","+arac.getCurrentLocation().getY()+"\n";
						try {
							fw.write(s);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}				
				}
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
			}
		});
		//Haritayı gösteren tuş işlevinin atandığı kod bloğu
		anaEkran.getBtnShowMap().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.setVisible(true);
			}
		});
		//Aracı istenilen konuma gönderme işlemini yapan butonun işlevinin atandığı kod bloğu
		anaEkran.getBtnSendToLocation().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!anaEkran.getTxtSendToLocationVelocity().getText().isEmpty()&&!anaEkran.getTxtSendToLocationX().getText().isEmpty()&&!anaEkran.getTxtSendToLocationY().getText().isEmpty()) {
					Konum target = new Konum();
					target.setX(Integer.parseInt(anaEkran.getTxtSendToLocationX().getText()));//Gerekli konum bilgileri alınıyor.
					target.setY(Integer.parseInt(anaEkran.getTxtSendToLocationY().getText()));
					String missionDescription = anaEkran.getTxtSendToLocationMissionDescription().getText();
					Gorev mission = new Gorev(missionDescription);
					for (Arac arac : araclar) {
						if(arac.getPlate().compareTo(anaEkran.getLblArac().getText())==0) {
							arac.moveVehicle(target, Double.parseDouble(anaEkran.getTxtSendToLocationVelocity().getText()),zamanlayicilar,bolgeler);
							arac.setMission(mission);
						}
					}
					
				}
				else if(anaEkran.getTxtSendToLocationVelocity().getText().isEmpty()) {
					hataMesaji.getLblHataMesaji().setText("HATALI GİRDİ - Lütfen hız girişi yapınız.");
					hataMesaji.setVisible(true);
					anaEkran.setEnabled(false);
				}
				else if(anaEkran.getTxtSendToLocationX().getText().isEmpty()) {
					hataMesaji.getLblHataMesaji().setText("HATALI GİRDİ - Lütfen yatay konum girişi yapınız.");
					hataMesaji.setVisible(true);
					anaEkran.setEnabled(false);
				}
				else if(anaEkran.getTxtSendToLocationY().getText().isEmpty()) {
					hataMesaji.getLblHataMesaji().setText("HATALI GİRDİ - Lütfen dikey konum girişi yapınız.");
					hataMesaji.setVisible(true);
					anaEkran.setEnabled(false);
				}
			}
		});
		//Seçilen aracın, seçilen bölgeye yollanması işlevinin yapıldığı kod bloğu
		anaEkran.getBtnSendToRegion().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				for (Arac arac : araclar) {//Hangi aracın seçildiği aranıyor
					if(arac.getPlate().compareTo(anaEkran.getLblArac().getText())==0&&anaEkran.getButtonGroup().getSelection()!=null) {
						for (Bolge bolge : bolgeler) {//Eğer araçlardan seçilmiş olan bulunursa hangi bölgenin seçildiği aranıyor
							for (Enumeration<AbstractButton> buttons = anaEkran.getButtonGroup().getElements();buttons.hasMoreElements();) {
								AbstractButton button = buttons.nextElement();//Seçilmiş olan butonun hangisi olduğu aranıyor
								System.out.println(button.getText());
								if(button.isSelected()&&!anaEkran.getTxtSendToRegionVelocity().getText().isEmpty()) {
									if(bolge.getName().compareTo(button.getText())==0) {//Eğer seçilen butonun adı bir bölge ile eşleşiyorsa araç o bölgeye yollanıyor
										arac.moveVehicle(bolge.getLocation(), Double.parseDouble(anaEkran.getTxtSendToRegionVelocity().getText()),zamanlayicilar,bolgeler);
									}
								}
								else if(anaEkran.getTxtSendToRegionVelocity().getText().isEmpty()) {
									hataMesaji.getLblHataMesaji().setText("HATALI GİRDİ - Lütfen hız girişi yapınız.");
									hataMesaji.setVisible(true);
									anaEkran.setEnabled(false);
								}
							}
						}
					}
					else if(anaEkran.getButtonGroup().getSelection()==null){
						hataMesaji.getLblHataMesaji().setText("HATALI GİRDİ - Lütfen bölge seçiniz.");
						hataMesaji.setVisible(true);
						anaEkran.setEnabled(false);
					}
				}
				
			}
		});		
		//Seçilen aracın bilgilerinin araçlar listesinden okunup gerekli yere yazdırılması işlevini yapan butonun işlevinin atandığı kod bloğu
		anaEkran.getBtnAraciGoruntule().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(anaEkran.getListVehicles().getSelectedIndex()!=-1) {
					Arac dummy = new Arac();
					dummy = araclar.get(anaEkran.getListVehicles().getSelectedIndex());
					anaEkran.getLblArac().setText(dummy.getPlate());
					anaEkran.getTextAreaVehicleInfo().setText("Plaka: "+ dummy.getPlate() +"\nTip: " + dummy.getType() +"\nBölge: " + dummy.getBolge().getName() + "\nKonum: " + dummy.getCurrentLocation().getX() + " , " + dummy.getCurrentLocation().getY() + "\nGorev:" + dummy.getMission().getMissionDescription());;
					anaEkran.getBtnSendToLocation().setEnabled(true);
					anaEkran.getBtnSendToRegion().setEnabled(true);
				}
				else {
					hataMesaji.getLblHataMesaji().setText("HATA - Lütfen görüntülenecek aracı seçiniz.");
					hataMesaji.setVisible(true);
					anaEkran.setEnabled(false);
				}
			}
		});
		//Aracın hem araçlar listesinden hem de JListten silinmesi işlevinin yapıldığı kod bloğu
		anaEkran.getBtnAraciSil().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if(anaEkran.getListVehicles().getSelectedIndex()!=-1) {
					if(anaEkran.getLblArac().getText().compareTo(araclar.get(anaEkran.getListVehicles().getSelectedIndex()).getPlate())==0) {
						anaEkran.getTextAreaVehicleInfo().setText("");
						anaEkran.getLblArac().setText("Arac");
						anaEkran.getBtnSendToLocation().setEnabled(false);
						anaEkran.getBtnSendToRegion().setEnabled(false);
					}
					araclar.remove(anaEkran.getListVehicles().getSelectedIndex());
					anaEkran.listModel.remove(anaEkran.getListVehicles().getSelectedIndex());

				}
				else {
					hataMesaji.getLblHataMesaji().setText("HATA - Lütfen listeden silinecek aracı seçiniz.");
					hataMesaji.setVisible(true);
					anaEkran.setEnabled(false);
				}
			}
		});
		//Araç ekleme işleminin gerçekleştiği bölüm
		anaEkran.getBtnAraciEkle().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!anaEkran.getTxtAddVehiclePlate().getText().isEmpty()&&!anaEkran.getTxtAddVehicleX().getText().isEmpty()&&!anaEkran.getTxtAddVehicleY().getText().isEmpty()&&!anaEkran.getTxtType().getText().isEmpty()) {
					String plate = anaEkran.getTxtAddVehiclePlate().getText();
					for (Arac arac : araclar) {
						if(plate.compareTo(arac.getPlate())==0) {
							return;
						}
					}
					String type = anaEkran.getTxtType().getText();
					String missionDescription = anaEkran.getTxtAddVehicleMissionDescription().getText();
					double x = Double.parseDouble(anaEkran.getTxtAddVehicleX().getText());
					double y = Double.parseDouble(anaEkran.getTxtAddVehicleY().getText());
					Konum konum = new Konum(x, y);
					if(missionDescription.isEmpty())
						missionDescription="Arac aktif bir gorevde degil";
					Gorev gorev = new Gorev(missionDescription);
					Arac arac2 = new Arac(plate, type);
					arac2.setMission(gorev);
					arac2.setCurrentLocation(konum);
					araclar.add(arac2);
					anaEkran.listModel.addElement("Plaka: " + araclar.getLast().getPlate()+ " Tip: " + araclar.getLast().getType());
					anaEkran.getTxtAddVehicleMissionDescription().setText("");
					anaEkran.getTxtAddVehiclePlate().setText("");
					anaEkran.getTxtAddVehicleX().setText("");
					anaEkran.getTxtAddVehicleY().setText("");
					anaEkran.getTxtType().setText("");
				}
				else if(anaEkran.getTxtAddVehiclePlate().getText().isEmpty()) {
					hataMesaji.getLblHataMesaji().setText("HATALI GİRDİ - Lütfen plaka girişi yapınız.");
					hataMesaji.setVisible(true);
					anaEkran.setEnabled(false);
				}
				else if(anaEkran.getTxtAddVehicleX().getText().isEmpty()) {
					hataMesaji.getLblHataMesaji().setText("HATALI GİRDİ - Lütfen yatay konum girişi yapınız.");
					hataMesaji.setVisible(true);
					anaEkran.setEnabled(false);
				}
				else if(anaEkran.getTxtAddVehicleY().getText().isEmpty()) {
					hataMesaji.getLblHataMesaji().setText("HATALI GİRDİ - Lütfen dikey konum girişi yapınız.");
					hataMesaji.setVisible(true);
					anaEkran.setEnabled(false);
				}
				else if(anaEkran.getTxtType().getText().isEmpty()) {
					hataMesaji.getLblHataMesaji().setText("HATALI GİRDİ - Lütfen tip girişi yapınız.");
					hataMesaji.setVisible(true);
					anaEkran.setEnabled(false);
				}
			}
		});
		//Bölgeler arasındaki sınırların belirlenip sınırlar listesine atandığı bölüm
		//Bir noktanın sınır noktası olarak kabul edilebilmesi için en az iki bölgeye yaklaşık uzaklıkta
		//ve bu bölgelerin o noktaya en yakın bölgeler olması gerekiyor
		for (int i = 0; i < 1280; i++) {
			for (int j = 0; j < 720; j++) {
				Konum a = new Konum(i,j);
				int esitUzaklik = 0;
				double minUzaklik = 9999;
				for (Bolge bolge2 : bolgeler) {
					if(bolge2.getDistance(a)<minUzaklik) {//Bölgenin en yakın olup olmadığı kontrolü
						minUzaklik=bolge2.getDistance(a);
						esitUzaklik=0;
					}
					//Bölge en yakın değilse bu noktaya uzaklığının başka bir bölgeyle yaklaşık olup olmadığı kontrol ediliyor
					else if(bolge2.getDistance(a)<=minUzaklik+2) {
						esitUzaklik++;
					}
				}
				if(esitUzaklik>0) {//Eğer en az iki bölge bir noktaya yaklaşık uzaklıktaysa
					//ve bu bölgelerden birisi o noktaya en yakın bölgeyse o nokta sınır olarak kabul ediliyor.
					sinirlar.add(new Konum(i,j));
				}
			}
		}

		//Haritanın tekrar çizdirilme işlemi
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		            	//Harita arka plan rengine döndürülüyor
		            	resetCanvas.clearRect(0, 0, 1280, 720);
		            	//Sınırlar çizdiriliyor
		            	for (Konum konum : sinirlar) {
		            		borders.drawRect((int)konum.getX(), (int)konum.getY(), 1, 1);
						}
		            	//Bölgelerin konumları ve adları çizdiriliyor
		            	for (Bolge bolge : bolgeler) {
		            		for (Komsu  komsu : bolge.getNeighbours()) {
								roads.drawLine((int)bolge.getLocation().getX(), (int)bolge.getLocation().getY(), (int)komsu.getBolge().getLocation().getX(), (int)komsu.getBolge().getLocation().getY());
							}
		        			centers.drawChars(bolge.getName().toCharArray(), 0, bolge.getName().length(),(int)bolge.getLocation().getX()-20 , (int)bolge.getLocation().getY()-10);
		        			centers.fillRect((int)bolge.getLocation().getX()-3,(int)bolge.getLocation().getY()-3, 6, 6);
		        		}
		            	//Araç plakaları, görev durumları, bulundukları bölge ve konumları çizdiriliyor
		            	for (Arac arac : araclar) {
		        			arac.setBolge(arac.findClosestRegion(bolgeler));
		        			if(arac.getPlate().compareTo(anaEkran.getLblArac().getText())==0) {

			        			selectedVehicle.drawRect((int)arac.getCurrentLocation().getX(), (int)arac.getCurrentLocation().getY(), 3, 3);
		        				if(arac.isOnAMission())
				        			selectedVehicle.drawChars((arac.getPlate() + " Gorevde " + arac.getBolge().getName() ).toCharArray(), 0, (arac.getPlate() + " Gorevde " + arac.getBolge().getName()).length(), (int)(arac.getCurrentLocation().getX())-50, (int)(arac.getCurrentLocation().getY())-5);
				        			else
				        			selectedVehicle.drawChars((arac.getPlate() + " Beklemede " + arac.getBolge().getName()).toCharArray(), 0, (arac.getPlate() + " Beklemede " + arac.getBolge().getName()).length(), (int)(arac.getCurrentLocation().getX())-50, (int)(arac.getCurrentLocation().getY())-5);
		        			}
		        			else {

			        			vehicles.drawRect((int)arac.getCurrentLocation().getX(), (int)arac.getCurrentLocation().getY(), 3, 3);
		        				if(arac.isOnAMission())
				        			vehicles.drawChars((arac.getPlate() + " Gorevde " + arac.getBolge().getName() ).toCharArray(), 0, (arac.getPlate() + " Gorevde " + arac.getBolge().getName()).length(), (int)(arac.getCurrentLocation().getX())-50, (int)(arac.getCurrentLocation().getY())-5);
				        			else
				        			vehicles.drawChars((arac.getPlate() + " Beklemede " + arac.getBolge().getName()).toCharArray(), 0, (arac.getPlate() + " Beklemede " + arac.getBolge().getName()).length(), (int)(arac.getCurrentLocation().getX())-50, (int)(arac.getCurrentLocation().getY())-5);
		        			}
		        		}
		            	//Harita tekardan boyanıyor
		                canvas.repaint();
		                canvas.revalidate();
		            }
		        }, 
		        0,25//Bu işlem saniyede 40 kere tekrar ediliyor
		);
	}


}
