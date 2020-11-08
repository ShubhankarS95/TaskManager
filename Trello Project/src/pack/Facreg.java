package pack;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import dbdetails.DatabaseConfig;
import dbdetails.HardCodeData;

public class Facreg {

	Facreg() {

		JFrame F3 = new JFrame("Form");
		F3.getContentPane().setBackground(Color.white);

		ImageIcon Img4 = new ImageIcon(getClass().getResource("Fac.png"));
		JLabel Image4 = new JLabel(Img4);
		Image4.setBounds(1010, 20, 330, 650);
		Image4.setVisible(true);
		F3.getContentPane().add(Image4);

		ImageIcon Img6 = new ImageIcon(getClass().getResource("Fac.png"));
		JLabel Image6 = new JLabel(Img6);
		Image6.setBounds(0, 20, 330, 650);
		Image6.setVisible(true);
		F3.getContentPane().add(Image6);

		JLabel l3 = new JLabel("Faculty Registration");
		l3.setFont(new Font("Serif", Font.BOLD, 40));
		l3.setForeground(Color.BLACK);
		l3.setBounds(500, 0, 750, 50);
		F3.getContentPane().add(l3);

//panel Start

		JPanel panel3 = new JPanel(null);
		panel3.setBounds(372, 60, 610, 500);
		panel3.setBackground(new Color(255, 153, 51));

		JLabel j1 = new JLabel("Firstname");
		j1.setBounds(10, 7, 150, 70);
		j1.setFont(new Font("Serif", Font.PLAIN, 20));
		JTextField t1 = new JTextField();
		t1.setBounds(100, 25, 150, 30);

		JLabel j2 = new JLabel("Lastname");
		j2.setBounds(370, 7, 150, 70);
		j2.setFont(new Font("Serif", Font.PLAIN, 20));
		JTextField t2 = new JTextField();
		t2.setBounds(455, 25, 150, 30);

		JLabel j3 = new JLabel("Email");
		j3.setBounds(10, 76, 150, 70);
		j3.setFont(new Font("Serif", Font.PLAIN, 20));
		JTextField t3 = new JTextField();
		t3.setBounds(100, 100, 200, 30);

		JLabel j4 = new JLabel("Department");
		j4.setBounds(10, 157, 150, 70);
		j4.setFont(new Font("Serif", Font.PLAIN, 20));
		JComboBox b1 = new JComboBox(HardCodeData.Department);
		b1.setBounds(105, 181, 150, 30);

		JLabel j5 = new JLabel("Designation");
		j5.setBounds(10, 229, 150, 70);
		j5.setFont(new Font("Serif", Font.PLAIN, 20));
		JComboBox b2 = new JComboBox(HardCodeData.Profile);
		b2.setBounds(105, 253, 150, 30);

		JLabel j6 = new JLabel("Faculty Id");
		j6.setBounds(10, 302, 150, 70);
		j6.setFont(new Font("Serif", Font.PLAIN, 20));
		JTextField t4 = new JTextField();
		t4.setBounds(105, 326, 150, 30);
		t4.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent k) {
				char key = k.getKeyChar();
				if (!(Character.isDigit(key)) || key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE
						|| t4.getText().length() > 9)
					k.consume();
			}
		});

		JLabel j7 = new JLabel("Contact");
		j7.setBounds(10, 380, 150, 70);
		j7.setFont(new Font("Serif", Font.PLAIN, 20));
		JTextField t5 = new JTextField();
		t5.setBounds(105, 404, 150, 30);
		t5.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent k) {
				char key = k.getKeyChar();
				if (!(Character.isDigit(key)) || key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE
						|| t5.getText().length() > 9)
					k.consume();
			}
		});
		/*
		 * JButton b5=new JButton("Back"); b5.setBounds(355, 400,150,40);
		 * 
		 * b5.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { new Loginpg(); } });
		 */
		panel3.add(j1);
		panel3.add(t1);

		panel3.add(j2);
		panel3.add(t2);

		panel3.add(j3);
		panel3.add(t3);

		panel3.add(j4);
		panel3.add(b1);

		panel3.add(j5);
		panel3.add(b2);

		panel3.add(j6);
		panel3.add(t4);

		panel3.add(j7);
		panel3.add(t5);
//panel3.add(b5);

		F3.getContentPane().add(panel3);

		F3.getContentPane().setLayout(null);

		// Password Field Start
		JLabel labelps = new JLabel("Create Password");
		labelps.setBounds(372, 563, 150, 70);
		labelps.setFont(new Font("Serif", Font.PLAIN, 20));
		F3.getContentPane().add(labelps);

		JPasswordField pswd1 = new JPasswordField();
		pswd1.setBounds(515, 587, 150, 30);
		F3.getContentPane().add(pswd1);

		JLabel labelpswd = new JLabel("Confirm password");
		labelpswd.setBounds(675, 563, 150, 70);
		labelpswd.setFont(new Font("Serif", Font.PLAIN, 20));
		F3.getContentPane().add(labelpswd);

		JPasswordField pswd2 = new JPasswordField();
		pswd2.setBounds(832, 587, 150, 30);
		F3.getContentPane().add(pswd2);
//Password Field Ends

		JButton b4 = new JButton("Exit");
		b4.setBounds(725, 630, 150, 40);
		F3.getContentPane().add(b4);

		JButton b3 = new JButton("Register");
		b3.setBounds(544, 630, 150, 40);
		F3.getContentPane().add(b3);
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pswd1.getText().contentEquals(pswd2.getText())) {
					String facultyid = t4.getText();
					String firstname = t1.getText();
					String lastname = t2.getText();
					String email = t3.getText();
					String contact = t5.getText();
					String department = b1.getSelectedItem().toString();
					String designation = b2.getSelectedItem().toString();
					String password = pswd1.getText();

					/*
					int mailotp = new MailOTPConfirmation().getOTP(email);
					int userenterotp = Integer
							.parseInt(JOptionPane.showInputDialog(F3, "Enter the OTP sent on your mail"));
					if (mailotp != userenterotp) {
						JOptionPane.showMessageDialog(F3, "OTP didn't match");
					} else {

						try {
							System.out.println("insert into faculty values('" + facultyid + "','" + firstname + "','"
									+ lastname + "','" + email + "','" + contact + "','" + department + "','"
									+ designation + "','" + password + "')");
							DatabaseConfig.initialize();
							DatabaseConfig.stmt.executeUpdate("insert into faculty values('" + facultyid + "','"
									+ firstname + "','" + lastname + "','" + email + "','" + contact + "','"
									+ department + "','" + designation + "','" + password + "')");
							JOptionPane.showMessageDialog(null, firstname + "  Registration Succesful");

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					*/
					
				} else {
					JOptionPane.showMessageDialog(null, "Password Not Matched");
				}
			}
		});
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// JOptionPane.showMessageDialog(b4,"OOPS");
				F3.dispose();
				new Loginpg();
			}
		});
		F3.setVisible(true);
		F3.setSize(1365, 750);
		F3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Facreg();
	}
}
