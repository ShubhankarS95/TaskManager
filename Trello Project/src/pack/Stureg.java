package pack;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import dbdetails.DatabaseConfig;
import dbdetails.HardCodeData;

public class Stureg {
	JFrame F2;

	private JComboBox cmbcourse;

	public Stureg() {

		// Frame Start
		F2 = new JFrame("Form");
		//F2.getContentPane().setBackground(Color.WHITE);
		JPanel panel = new javax.swing.JPanel() {
	         protected void paintComponent(Graphics g) {
	        	 
	            if (g instanceof Graphics2D) {               
	               Paint p =
	                  new GradientPaint(0, 0, new Color(255, 26, 26),
	                  getWidth(), getHeight(), new Color(51, 204, 204), true);
	               Graphics2D g2d = (Graphics2D)g;
	               g2d.setPaint(p);
	               g2d.fillRect(0, 0, getWidth(), getHeight());
	            } else {
	               super.paintComponent(g);
	            }
	         }
	      };
		F2.setContentPane(panel);
		
		JLabel l2 = new JLabel("Student Registration Form");
		l2.setFont(new Font("SERIF", Font.BOLD, 40));
		l2.setForeground(Color.BLACK);
		l2.setBounds(450, 0, 750, 50);
		F2.getContentPane().add(l2);

		ImageIcon Img7 = new ImageIcon(getClass().getResource("Stu.png"));
		JLabel Image7 = new JLabel(Img7);
		Image7.setBounds(960, 20, 400, 650);
		Image7.setVisible(true);
		F2.getContentPane().add(Image7);

		ImageIcon Img8 = new ImageIcon(getClass().getResource("Stu.png"));
		JLabel Image8 = new JLabel(Img8);
		Image8.setBounds(10, 20, 400, 650);
		Image8.setVisible(true);
		F2.getContentPane().add(Image8);
//panelcon Start
		//JPanel panelcon = new JPanel(null);
		//panelcon.setBounds(372, 60, 610, 500);
//panelcon End		
//Pane1 Start  
		//JPanel panel1 = new JPanel(null);
		//panel1.setBorder(new TitledBorder(null, "Personal Details", TitledBorder.LEADING, TitledBorder.ABOVE_TOP,
		//		new Font("SERIF", Font.BOLD, 24), Color.DARK_GRAY));
		//panel1.setBackground(new Color(179, 230, 255));
		//panel1.setBounds(0, 0, 610, 250);

		JLabel labelfn = new JLabel("Firstname");
		labelfn.setBounds(412, 61, 150, 70);
		labelfn.setFont(new Font("Serif", Font.PLAIN, 20));
		JTextField Text1 = new JTextField();
		Text1.setBounds(502, 86, 150, 30);

		JLabel labelln = new JLabel("Lastname");
		labelln.setBounds(752, 61, 150, 70);
		labelln.setFont(new Font("Serif", Font.PLAIN, 20));
		JTextField Text2 = new JTextField();
		Text2.setBounds(832, 86, 150, 30);

		JLabel labelEmail = new JLabel("Email");
		labelEmail.setBounds(412, 121, 150, 70);
		labelEmail.setFont(new Font("Serif", Font.PLAIN, 20));
		JTextField Text3 = new JTextField();
		Text3.setBounds(502, 141, 180, 30);

		JLabel labelPhone = new JLabel("Contact");
		labelPhone.setBounds(412, 181, 150, 70);
		labelPhone.setFont(new Font("Serif", Font.PLAIN, 20));
		JTextField Text4 = new JTextField();
		Text4.setBounds(502, 196, 150, 30);

		Text4.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent k) {
				char key = k.getKeyChar();
				if (!(Character.isDigit(key)) || key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE
						|| Text4.getText().length() > 9)
					k.consume();
			}
		});

		JLabel labelGender = new JLabel("Gender");
		labelGender.setBounds(412, 233, 150, 70);
		labelGender.setFont(new Font("Serif", Font.PLAIN, 20));
		JCheckBox check1 = new JCheckBox("Male", setFont(new Font("Serif", Font.PLAIN, 20)));
		check1.setBounds(502, 251, 150, 40);
		check1.setOpaque(false);
		//check1.setBackground(new Color(179, 230, 255));

		JCheckBox check2 = new JCheckBox("Female", setFont(new Font("Serif", Font.PLAIN, 20)));
		check2.setBounds(652, 251, 150, 40);
		check2.setOpaque(false);
		//check2.setBackground(new Color(179, 230, 255));

		//ButtonGroup grp = new ButtonGroup();
		
		//grp.add(check1);
		//grp.add(check2);

		F2.getContentPane().add(labelfn);
		F2.getContentPane().add(Text1);

		F2.getContentPane().add(labelln);
		F2.getContentPane().add(Text2);

		F2.getContentPane().add(labelEmail);
		F2.getContentPane().add(Text3);

		F2.getContentPane().add(labelPhone);
		F2.getContentPane().add(Text4);

		F2.getContentPane().add(labelGender);
		F2.getContentPane().add(check1);
		F2.getContentPane().add(check2);
//Panel1 end        

//Panel2 Start
		JPanel panel2 = new JPanel(null);
		panel2.setBorder(new TitledBorder(null, "College Details", TitledBorder.LEADING, TitledBorder.ABOVE_TOP,
				new Font("SERIF", Font.BOLD, 24), Color.DARK_GRAY));
		panel2.setBounds(0, 250, 610, 250);
		panel2.setBackground(new Color(179, 230, 255));

		JLabel labelCourse = new JLabel("Course");
		labelCourse.setBounds(412, 297, 150, 70);
		labelCourse.setFont(new Font("Serif", Font.PLAIN, 20));

		JLabel labelyear = new JLabel("Year");
		labelyear.setBounds(752, 297, 150, 70);
		labelyear.setFont(new Font("Serif", Font.PLAIN, 20));
		JTextField t6 = new JTextField();
		t6.setBounds(822, 320, 150, 30);
		t6.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent k) {
				char key = k.getKeyChar();
				if (!(Character.isDigit(key)) || key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE
						|| t6.getText().length() > 0)
					k.consume();
			}
		});

		JLabel labelsystemid = new JLabel("System Id");
		labelsystemid.setBounds(412, 365, 150, 70);
		labelsystemid.setFont(new Font("Serif", Font.PLAIN, 20));
		JTextField t7 = new JTextField();
		t7.setBounds(502, 389, 150, 30);
		t7.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent k) {
				char key = k.getKeyChar();
				if (!(Character.isDigit(key)) || key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE
						|| t7.getText().length() > 9)
					k.consume();
			}
		});

		JLabel labelschool = new JLabel("School");
		labelschool.setBounds(412, 443, 150, 70);
		labelschool.setFont(new Font("Serif", Font.PLAIN, 20));
		JComboBox l1 = new JComboBox(HardCodeData.schoolnames);
		l1.setBackground(Color.WHITE);
		l1.setBounds(502, 467, 150, 30);

		F2.getContentPane().add(labelCourse);

		F2.getContentPane().add(labelyear);
		F2.getContentPane().add(t6);

		F2.getContentPane().add(labelsystemid);
		F2.getContentPane().add(t7);

		F2.getContentPane().add(labelschool);
		F2.getContentPane().add(l1);
//Panel2 end

//Password Field Start	    
		JLabel labelps = new JLabel("Create Password");
		labelps.setBounds(372, 563, 150, 70);
		labelps.setFont(new Font("Serif", Font.PLAIN, 20));
		JPasswordField pswd1 = new JPasswordField();
		pswd1.setBounds(515, 587, 150, 30);

		JLabel labelpswd = new JLabel("Confirm password");
		labelpswd.setBounds(675, 563, 150, 70);
		labelpswd.setFont(new Font("Serif", Font.PLAIN, 20));
		JPasswordField pswd2 = new JPasswordField();
		pswd2.setBounds(832, 587, 150, 30);
//Password Field Ends	    

//Button Field Start

		JButton B1 = new JButton("Register");
		B1.setBounds(485, 644, 120, 40);

		B1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (pswd1.getText().contentEquals(pswd2.getText())) {

					String sysid = t7.getText();
					String firstname = Text1.getText();
					String lastname = Text2.getText();
					String email = Text3.getText();
					String contact = Text4.getText();

					String gender;
					if (check1.isSelected())
						gender = check1.getText();
					else
						gender = check2.getText();

					String course = cmbcourse.getSelectedItem().toString();
					String year = t6.getText();
					String school = l1.getSelectedItem().toString();
					String password = pswd1.getText();
					/*
					int mailotp = new MailOTPConfirmation().getOTP(email);
					int userenterotp = Integer
							.parseInt(JOptionPane.showInputDialog(F2, "Enter the OTP sent on your mail"));
					if (mailotp != userenterotp) {
						JOptionPane.showMessageDialog(F2,"OTP didn't match");
					} else {
					
						try {
							System.out.println("insert into student values('" + sysid + "','" + firstname + "','"
									+ lastname + "','" + email + "','" + contact + "','" + gender + "','" + course
									+ "','" + year + "','" + school + "','" + password + "')");
							DatabaseConfig.initialize();
							DatabaseConfig.stmt.executeUpdate("insert into student values(" + sysid + ",'" + firstname
									+ "','" + lastname + "','" + email + "','" + contact + "','" + gender + "','"
									+ course + "','" + year + "','" + school + "','" + password + "')");

							DatabaseConfig.con.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						JOptionPane.showMessageDialog(F2, firstname + "  Registration Successfull");
					}*/
				} else {
					JOptionPane.showMessageDialog(F2, "Password Not Matched");
				}
			}
		});

		JButton B2 = new JButton("Exit");
		B2.setBounds(801, 644, 120, 40);

		B2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// JOptionPane.showConfirmDialog(null,"OOPS!!");
				F2.dispose();
				new Loginpg();
			}
		});
		/*
		 * JButton B3=new JButton("Back"); B3.setBounds(395, 625,120,40);
		 * 
		 * B3.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { new Loginpg(); } });
		 */
//Button Field Ends        
		//panelcon.add(panel1);
		//panelcon.add(panel2);

		cmbcourse = new JComboBox(HardCodeData.coursenames);
		cmbcourse.setBounds(502, 324, 148, 24);
		F2.getContentPane().add(cmbcourse);
		//F2.getContentPane().add(panelcon);

		F2.getContentPane().add(labelps);
		F2.getContentPane().add(pswd1);
		F2.getContentPane().add(labelpswd);
		F2.getContentPane().add(pswd2);

		F2.getContentPane().add(B1);
		F2.getContentPane().add(B2);
		// F2.add(B3);

		F2.getContentPane().setLayout(null);
		F2.setSize(1365, 780);// increase the frame size
		F2.setVisible(true);
		F2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//Frame End		
	}

	private Icon setFont(Font font) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		new Stureg();
	}
}
