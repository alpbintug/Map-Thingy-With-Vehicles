package arayuz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class TiklamaYollayicisi extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMissionDescription;
	private JButton btnAraciGonder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TiklamaYollayicisi frame = new TiklamaYollayicisi();
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
	public TiklamaYollayicisi() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 166, 151);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblGorevAciklamasiGirin = new JLabel("Gorev Aciklamasi girin");
		
		txtMissionDescription = new JTextField();
		txtMissionDescription.setColumns(10);
		
		btnAraciGonder = new JButton("A\u00E7\u0131klamay\u0131 gir");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txtMissionDescription, Alignment.LEADING)
								.addComponent(lblGorevAciklamasiGirin, Alignment.LEADING))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAraciGonder, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
							.addGap(10))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblGorevAciklamasiGirin)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtMissionDescription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAraciGonder)
					.addContainerGap(74, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	public JTextField getTxtMissionDescription() {
		return txtMissionDescription;
	}

	public void setTxtMissionDescription(JTextField txtMissionDescription) {
		this.txtMissionDescription = txtMissionDescription;
	}


	public JButton getBtnAraciGonder() {
		return btnAraciGonder;
	}

	public void setBtnAraciGonder(JButton btnAraciGonder) {
		this.btnAraciGonder = btnAraciGonder;
	}
}
