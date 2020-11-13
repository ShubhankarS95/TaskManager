package pack;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import dbdetails.DatabaseConfig;
import dbdetails.HardCodeData;

public class Loginpg {
	Loginpg() {
		JFrame F1 = new JFrame("Login");
		JPanel panel = new javax.swing.JPanel() {
	         protected void paintComponent(Graphics g) {
	        	 
	            if (g instanceof Graphics2D) {               
	               Paint p =
	                  new GradientPaint(0, 0, new Color(255, 102, 102),
	                  getWidth(), getHeight(), new Color(255, 121, 77),true);
	               Graphics2D g2d = (Graphics2D)g;
	               g2d.setPaint(p);
	               g2d.fillRect(0, 0, getWidth(), getHeight());
	            } else {
	               super.paintComponent(g);
	            }
	         }
	      };
	    F1.setContentPane(panel);  
		F1.getContentPane().setBackground(new Color(255, 255, 255));

		ImageIcon Img1 = new ImageIcon(getClass().getResource("T6.png"));
		JLabel Image1 = new JLabel(Img1);
		Image1.setBounds(923, 34, 402, 527);
		Image1.setVisible(true);
		F1.getContentPane().add(Image1);

       /*
		ImageIcon Img2 = new ImageIcon(getClass().getResource("T8.png"));
		JLabel Image2 = new JLabel(Img2);
		Image2.setOpaque(true);
		Image2.setBounds(0, 20, 400, 650);
		Image2.setVisible(true);*/

		JLabel l1 = new JLabel("Task Manager");
		l1.setBounds(560, 0, 260, 50);
		l1.setFont(new Font("SERIF", Font.BOLD, 40));
		l1.setForeground(new Color(255, 255, 255));
		l1.setForeground(Color.black);
		F1.getContentPane().add(l1);

//panel start
		//JPanel jp1 = new JPanel(null);
		//jp1.setBounds(405, 100, 550, 300);
		//jp1.setBackground(new Color(153, 255, 153));

		JTextField jt1 = new JTextField("Email");
		jt1.setFont(new Font("SERIF", Font.PLAIN, 20));
		jt1.setForeground(Color.GRAY);
		jt1.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent k) {
				if (jt1.getText().equals("Email")) {
					jt1.setText("");
					jt1.setForeground(Color.BLACK);
				} /*else if (jt1.getText().isEmpty()) {
					jt1.setForeground(Color.GRAY);
					jt1.setText("Email");
				}*/
			}
		});
		jt1.setBounds(417, 131, 200, 40);

		JPasswordField jt2 = new JPasswordField("Password");
		jt2.setForeground(Color.GRAY);
		jt2.setFont(new Font("SERIF", Font.PLAIN, 20));

		jt2.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent k) {
				if (jt2.getText().equals("Password")) {
					jt2.setText("");
					jt2.setForeground(Color.BLACK);
				}/* else if (jt2.getText().isEmpty()) {
					jt2.setForeground(Color.GRAY);
					jt2.setText("Password");
				}*/
			}
		});
		jt2.setBounds(417, 195, 200, 40);

		JComboBox<String> cmbUserType = new JComboBox<String>();
		//cmbUserType.setForeground(Color.LIGHT_GRAY);
		cmbUserType.setBackground(Color.WHITE);
		cmbUserType.setModel(new DefaultComboBoxModel<String>(HardCodeData.usertype));
		cmbUserType.setBounds(417, 266, 200, 32);
		//jp1.add(cmbUserType);
		F1.getContentPane().add(cmbUserType);

		JButton jb1 = new JButton("Login");
		jb1.setBackground(new Color(255, 255, 255));
		jb1.setBounds(442, 323, 150, 40);
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = jt1.getText();
				String password = jt2.getText();
				String usertype = cmbUserType.getSelectedItem().toString();
				
				if(usertype.contentEquals("Choose User type"))
				{
					JOptionPane.showMessageDialog(null,"Select the Type of User ");
				}
				else if (usertype.contentEquals(HardCodeData.usertype[1])) {
					F1.dispose();
					//new Mainpg("Admin/Dean","DEAN");
					new NewProjectDetails("Admin/Dean","DEAN");
				} else {
					try {
						DatabaseConfig.initialize();
					
						System.out.println("Checking Details");
						DatabaseConfig.rs = DatabaseConfig.stmt.executeQuery("select email,Password from workhandler." + usertype + " where email='"+username+"';");
						int flag=0;
						while (DatabaseConfig.rs.next()) {
							//System.out.println("  " + DatabaseConfig.rs.getString(1) + "  " + DatabaseConfig.rs.getInt(2));
							
							if(DatabaseConfig.rs.getString(1).contentEquals(username))
							{	System.out.println("User Got");
								flag=1;
								if(DatabaseConfig.rs.getString(2).contentEquals(password))
								{
									//System.out.println("Password Got");
									flag=2;
									F1.dispose();
									new Mainpg(username,usertype);
									
									break;
								}
							}							
						}
						if(flag==0)
							JOptionPane.showMessageDialog(null,"User Not Found");
						else if(flag==1)
							JOptionPane.showMessageDialog(null,"Wrong Password !!!!Try Again");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		JLabel jl2 = new JLabel("New Student");
		jl2.setFont(new Font("SERIF", Font.BOLD, 18));
		jl2.setBounds(243, 449, 110, 20);
		JButton jb2 = new JButton("Student Registration");
		//jb2.setBackground(new Color(255, 255, 179));
		jb2.setBounds(243, 479, 150, 40);
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				F1.dispose();
				new Stureg();
			}
		});

		JLabel jl3 = new JLabel("New Faculty");
		jl3.setFont(new Font("SERIF", Font.BOLD, 18));
		jl3.setBounds(633, 449, 110, 20);
		JButton jb3 = new JButton("Faculty Registration");
		//jb3.setBackground(new Color(255, 255, 179));
		jb3.setBounds(633, 479, 150, 40);
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				F1.dispose();
				new Facreg();
			}
		});
		
		//jp1.add(jt1);
		//jp1.add(jt2);
		//jp1.add(jb1);
		F1.getContentPane().add(jt1);
		F1.getContentPane().add(jt2);
		F1.getContentPane().add(jb1);

		//F1.getContentPane().add(jp1);
		F1.getContentPane().add(jl2);
		F1.getContentPane().add(jb2);

		F1.getContentPane().add(jl3);
		F1.getContentPane().add(jb3);

		F1.getContentPane().setLayout(null);
		F1.setSize(1365, 730);
		F1.setVisible(true);
		F1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new Loginpg();
	}
}
