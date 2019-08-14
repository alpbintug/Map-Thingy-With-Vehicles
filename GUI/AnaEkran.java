package arayuz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import java.awt.Toolkit;

public class AnaEkran extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DefaultListModel<String> listModel = new DefaultListModel<String>();
	private JPanel contentPane;
	private JTextField txtAddVehiclePlate;
	private JTextField txtType;
	private JTextField txtAddVehicleX;
	private JTextField txtAddVehicleY;
	private JTextField txtAddVehicleMissionDescription;
	private JButton btnAraciEkle;
	private JLabel lblAraEkleme;
	private JScrollPane scrollPane;
	private JTextField txtSendToLocationX;
	private JTextField txtSendToLocationY;
	private JTextField txtSendToRegionVelocity;
	private JTextField txtSendToLocationMissionDescription;
	private JTextField txtSendToLocationVelocity;
	private JTextField txtSendToRegionMissionDescription;
	private JButton btnSendToRegion;
	private JButton btnShowMap;
	private JButton btnSendToLocation;
	private JButton btnAraciGoruntule;
	private JList<String> listVehicles;
	private JTextArea textAreaVehicleInfo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblArac;
	private JButton btnAraciSil;
	private JButton btnCikisYap;
	private JButton btnDegisiklikleriGeriAl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnaEkran frame = new AnaEkran();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AnaEkran() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnaEkran.class.getResource("/simgeler/van.png")));
		setTitle("Ara\u00E7 takip sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1082, 712);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblPlaka = new JLabel("Plaka:");
		lblPlaka.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblTip = new JLabel("Tip:");
		lblTip.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblKonumBilgileri = new JLabel("Konum Bilgileri:");
		lblKonumBilgileri.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblAddVehicleX = new JLabel("X:");
		lblAddVehicleX.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblY = new JLabel("Y:");
		lblY.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblGorevAciklamas = new JLabel("Gorev Aciklamas\u0131:");
		lblGorevAciklamas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtAddVehiclePlate = new JTextField();
		txtAddVehiclePlate.setColumns(10);
		
		txtType = new JTextField();
		txtType.setColumns(10);
		
		txtAddVehicleX = new JTextField();
		txtAddVehicleX.setColumns(10);
		
		txtAddVehicleY = new JTextField();
		txtAddVehicleY.setColumns(10);
		
		txtAddVehicleMissionDescription = new JTextField();
		txtAddVehicleMissionDescription.setColumns(10);
		
		btnAraciEkle = new JButton("Araci Ekle");
		
		lblAraEkleme = new JLabel("Ara\u00E7 Ekleme");
		lblAraEkleme.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		scrollPane = new JScrollPane();
		
		btnAraciGoruntule = new JButton("Araci Goruntule");
		
		btnAraciSil = new JButton("Araci Sil");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JLabel lblAracHedefeGnder = new JLabel("Arac\u0131 Hedefe G\u00F6nder:");
		lblAracHedefeGnder.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblX_1 = new JLabel("X:");
		
		txtSendToLocationX = new JTextField();
		txtSendToLocationX.setColumns(10);
		
		JLabel lblY_1 = new JLabel("Y:");
		
		txtSendToLocationY = new JTextField();
		txtSendToLocationY.setColumns(10);
		
		JRadioButton radioButton = new JRadioButton("Allene");
		buttonGroup.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("Bemeria");
		buttonGroup.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("Centuan");
		buttonGroup.add(radioButton_2);
		
		JRadioButton radioButton_3 = new JRadioButton("Dickos");
		buttonGroup.add(radioButton_3);
		
		JRadioButton radioButton_4 = new JRadioButton("Eve");
		buttonGroup.add(radioButton_4);
		
		JRadioButton radioButton_5 = new JRadioButton("Fespect");
		buttonGroup.add(radioButton_5);
		
		JRadioButton radioButton_6 = new JRadioButton("Gespot");
		buttonGroup.add(radioButton_6);
		
		JRadioButton radioButton_7 = new JRadioButton("Hulkish");
		buttonGroup.add(radioButton_7);
		
		JRadioButton radioButton_8 = new JRadioButton("Ivory Boots");
		buttonGroup.add(radioButton_8);
		
		JRadioButton radioButton_9 = new JRadioButton("Janeica");
		buttonGroup.add(radioButton_9);
		
		txtSendToRegionVelocity = new JTextField();
		txtSendToRegionVelocity.setColumns(10);
		
		btnSendToRegion = new JButton("Gonder");
		btnSendToRegion.setEnabled(false);
		
		JLabel lblGrevAklamas = new JLabel("G\u00F6rev A\u00E7\u0131klamas\u0131:");
		
		txtSendToLocationMissionDescription = new JTextField();
		txtSendToLocationMissionDescription.setColumns(10);
		
		JLabel lblHz = new JLabel("H\u0131z:");
		
		txtSendToLocationVelocity = new JTextField();
		txtSendToLocationVelocity.setColumns(10);
		
		JLabel lblHz_1 = new JLabel("H\u0131z:");
		
		JLabel lblGrevAklamas_1 = new JLabel("G\u00F6rev A\u00E7\u0131klamas\u0131:");
		
		txtSendToRegionMissionDescription = new JTextField();
		txtSendToRegionMissionDescription.setColumns(10);
		
		btnSendToLocation = new JButton("Gonder");
		btnSendToLocation.setEnabled(false);
		
		btnShowMap = new JButton("Haritayi Goster");
		
		lblArac = new JLabel("Arac:");
		
		btnCikisYap = new JButton("Degisiklikleri Kaydet");
		
		btnDegisiklikleriGeriAl = new JButton("Degisiklikleri Geri Al");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnShowMap, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
										.addComponent(btnAraciEkle, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblPlaka)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtAddVehiclePlate, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblTip)
											.addGap(18)
											.addComponent(txtType, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblAddVehicleX)
												.addComponent(lblY))
											.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
												.addComponent(txtAddVehicleY)
												.addComponent(txtAddVehicleX, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)))
										.addComponent(txtAddVehicleMissionDescription, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
									.addGap(32))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblKonumBilgileri)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblGorevAciklamas)
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAraEkleme)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAraciGoruntule)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnAraciSil, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblAracHedefeGnder)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblX_1)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(txtSendToLocationX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(lblY_1)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(txtSendToLocationY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(18)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
													.addComponent(btnSendToLocation, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
													.addComponent(txtSendToLocationVelocity)
													.addComponent(txtSendToLocationMissionDescription))))
										.addComponent(lblGrevAklamas)
										.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblArac))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(radioButton_9, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(radioButton_8, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
											.addComponent(radioButton_7, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
											.addComponent(radioButton, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
											.addComponent(radioButton_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
											.addComponent(radioButton_2, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
											.addComponent(radioButton_4, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
											.addComponent(radioButton_3, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
											.addComponent(radioButton_5, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
											.addComponent(radioButton_6, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblGrevAklamas_1)
											.addComponent(txtSendToRegionMissionDescription)
											.addComponent(txtSendToRegionVelocity, 0, 0, Short.MAX_VALUE)
											.addComponent(btnSendToRegion, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
											.addComponent(lblHz))))
								.addComponent(lblHz_1))
							.addGap(186))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(68)
							.addComponent(btnDegisiklikleriGeriAl, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnCikisYap, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
							.addGap(201))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 508, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblArac))
							.addGap(40)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAraciGoruntule, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAraciSil, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(25)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(radioButton)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(radioButton_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(radioButton_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(radioButton_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(radioButton_4)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(radioButton_5)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(radioButton_6)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(radioButton_7)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(radioButton_8)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(radioButton_9)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblGrevAklamas_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtSendToRegionMissionDescription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(5))
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE))
							.addGap(2)
							.addComponent(lblHz)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblAracHedefeGnder)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
											.addComponent(txtSendToLocationX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblY_1)
											.addComponent(txtSendToLocationY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblX_1))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblGrevAklamas))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txtSendToRegionVelocity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSendToRegion, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSendToLocationMissionDescription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblHz_1)
							.addGap(3)
							.addComponent(txtSendToLocationVelocity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSendToLocation, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnDegisiklikleriGeriAl, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
								.addComponent(btnCikisYap, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAraEkleme)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPlaka)
								.addComponent(txtAddVehiclePlate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTip)
								.addComponent(txtType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblKonumBilgileri)
							.addGap(14)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAddVehicleX)
								.addComponent(txtAddVehicleX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblY)
								.addComponent(txtAddVehicleY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblGorevAciklamas)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtAddVehicleMissionDescription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(20)
							.addComponent(btnAraciEkle, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnShowMap, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(30, GroupLayout.PREFERRED_SIZE))
		);
		
		textAreaVehicleInfo = new JTextArea();
		textAreaVehicleInfo.setEditable(false);
		scrollPane_1.setViewportView(textAreaVehicleInfo);
		
		listVehicles = new JList<String>(listModel);
		scrollPane.setViewportView(listVehicles);
		contentPane.setLayout(gl_contentPane);
	}


	public JButton getBtnDegisiklikleriGeriAl() {
		return btnDegisiklikleriGeriAl;
	}

	public void setBtnDegisiklikleriGeriAl(JButton btnDegisiklikleriGeriAl) {
		this.btnDegisiklikleriGeriAl = btnDegisiklikleriGeriAl;
	}

	public JButton getBtnCikisYap() {
		return btnCikisYap;
	}

	public void setBtnCikisYap(JButton btnCikisYap) {
		this.btnCikisYap = btnCikisYap;
	}

	public JButton getBtnAraciSil() {
		return btnAraciSil;
	}

	public void setBtnAraciSil(JButton btnAraciSil) {
		this.btnAraciSil = btnAraciSil;
	}

	public JLabel getLblArac() {
		return lblArac;
	}

	public void setLblArac(JLabel lblArac) {
		this.lblArac = lblArac;
	}

	public JList<String> getListVehicles() {
		return listVehicles;
	}

	public void setListVehicles(JList<String> listVehicles) {
		this.listVehicles = listVehicles;
	}

	public JTextArea getTextAreaVehicleInfo() {
		return textAreaVehicleInfo;
	}

	public void setTextAreaVehicleInfo(JTextArea textAreaVehicleInfo) {
		this.textAreaVehicleInfo = textAreaVehicleInfo;
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public JTextField getTxtAddVehiclePlate() {
		return txtAddVehiclePlate;
	}

	public void setTxtAddVehiclePlate(JTextField txtAddVehiclePlate) {
		this.txtAddVehiclePlate = txtAddVehiclePlate;
	}

	public JTextField getTxtType() {
		return txtType;
	}

	public void setTxtType(JTextField txtType) {
		this.txtType = txtType;
	}

	public JTextField getTxtAddVehicleX() {
		return txtAddVehicleX;
	}

	public void setTxtAddVehicleX(JTextField txtAddVehicleX) {
		this.txtAddVehicleX = txtAddVehicleX;
	}

	public JTextField getTxtAddVehicleY() {
		return txtAddVehicleY;
	}

	public void setTxtAddVehicleY(JTextField txtAddVehicleY) {
		this.txtAddVehicleY = txtAddVehicleY;
	}

	public JTextField getTxtAddVehicleMissionDescription() {
		return txtAddVehicleMissionDescription;
	}

	public void setTxtAddVehicleMissionDescription(JTextField txtAddVehicleMissionDescription) {
		this.txtAddVehicleMissionDescription = txtAddVehicleMissionDescription;
	}

	public JButton getBtnAraciEkle() {
		return btnAraciEkle;
	}

	public void setBtnAraciEkle(JButton btnAraciEkle) {
		this.btnAraciEkle = btnAraciEkle;
	}

	public JLabel getLblAraEkleme() {
		return lblAraEkleme;
	}

	public void setLblAraEkleme(JLabel lblAraEkleme) {
		this.lblAraEkleme = lblAraEkleme;
	}

	public JTextField getTxtSendToLocationX() {
		return txtSendToLocationX;
	}

	public void setTxtSendToLocationX(JTextField txtSendToLocationX) {
		this.txtSendToLocationX = txtSendToLocationX;
	}

	public JTextField getTxtSendToLocationY() {
		return txtSendToLocationY;
	}

	public void setTxtSendToLocationY(JTextField txtSendToLocationY) {
		this.txtSendToLocationY = txtSendToLocationY;
	}

	public JTextField getTxtSendToRegionVelocity() {
		return txtSendToRegionVelocity;
	}

	public void setTxtSendToRegionVelocity(JTextField txtSendToRegionVelocity) {
		this.txtSendToRegionVelocity = txtSendToRegionVelocity;
	}

	public JTextField getTxtSendToLocationMissionDescription() {
		return txtSendToLocationMissionDescription;
	}

	public void setTxtSendToLocationMissionDescription(JTextField txtSendToLocationMissionDescription) {
		this.txtSendToLocationMissionDescription = txtSendToLocationMissionDescription;
	}

	public JTextField getTxtSendToLocationVelocity() {
		return txtSendToLocationVelocity;
	}

	public void setTxtSendToLocationVelocity(JTextField txtSendToLocationVelocity) {
		this.txtSendToLocationVelocity = txtSendToLocationVelocity;
	}

	public JTextField getTxtSendToRegionMissionDescription() {
		return txtSendToRegionMissionDescription;
	}

	public void setTxtSendToRegionMissionDescription(JTextField txtSendToRegionMissionDescription) {
		this.txtSendToRegionMissionDescription = txtSendToRegionMissionDescription;
	}

	public JButton getBtnSendToRegion() {
		return btnSendToRegion;
	}

	public void setBtnSendToRegion(JButton btnSendToRegion) {
		this.btnSendToRegion = btnSendToRegion;
	}

	public JButton getBtnShowMap() {
		return btnShowMap;
	}

	public void setBtnShowMap(JButton btnShowMap) {
		this.btnShowMap = btnShowMap;
	}

	public JButton getBtnSendToLocation() {
		return btnSendToLocation;
	}

	public void setBtnSendToLocation(JButton btnSendToLocation) {
		this.btnSendToLocation = btnSendToLocation;
	}

	public JButton getBtnAraciGoruntule() {
		return btnAraciGoruntule;
	}

	public void setBtnAraciGoruntule(JButton btnAraciGoruntule) {
		this.btnAraciGoruntule = btnAraciGoruntule;
	}
}
