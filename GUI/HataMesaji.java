package arayuz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class HataMesaji extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblHataMesaji;
	private JButton btnTamam;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HataMesaji frame = new HataMesaji();
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
	public HataMesaji() {
		setResizable(false);
		setTitle("HATA!");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 295, 139);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblHataMesaji = new JLabel("New label");
		
		btnTamam = new JButton("Tamam");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblHataMesaji))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(88)
							.addComponent(btnTamam)))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblHataMesaji)
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addComponent(btnTamam)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

	public JButton getBtnTamam() {
		return btnTamam;
	}

	public void setBtnTamam(JButton btnTamam) {
		this.btnTamam = btnTamam;
	}

	public JLabel getLblHataMesaji() {
		return lblHataMesaji;
	}

	public void setLblHataMesaji(JLabel lblHataMesaji) {
		this.lblHataMesaji = lblHataMesaji;
	}
}
