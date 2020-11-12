package pack;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ComponentSampleModel;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.toedter.components.JSpinField;

import dbdetails.DatabaseConfig;
import dbdetails.HardCodeData;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class NewProjectDetails {
	JPanel panelt;
	private JLabel Image5;
	private JLabel Image9;
	private JLabel l4;
	private JLabel lblNewLabel;
	private JSpinField spinField;
	private JButton btnNewButton;
	private JLabel labelpn;
	private JTextField Text1;
	private JLabel labelln;
	private JLabel labeld1;
	private JLabel labeld2;
	private JLabel labelcourse;
	private JLabel labelpswd;
	private JPasswordField pswd;
	private JButton sub;
	private JButton back1;
	private JDateChooser datechStartD;
	private JDateChooser dateChooserEndD;
	private JScrollPane scrollPane;
	private JPanel borderpanel;
	private JPanel gridpanel;

	private ArrayList<JTextField> tasks;
	private JComboBox cmbCourse;

	private static String[] coursenames = { "Btech-CS", "Btech-Mech", "MCA", "BCA", "Bsc", "MSC" };
	private JComboBox cmbTLeader;
	private HashMap<String, String> teacherNames;

	public NewProjectDetails() {
		JFrame F5 = new JFrame("Project Details");
		// F5.getContentPane().setBackground(Color.white);
		JPanel panel = new javax.swing.JPanel() {
			protected void paintComponent(Graphics g) {

				if (g instanceof Graphics2D) {
					Paint p = new GradientPaint(0, 0, new Color(255, 77, 77), getWidth(), getHeight(),
							new Color(255, 51, 51), true);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setPaint(p);
					g2d.fillRect(0, 0, getWidth(), getHeight());
				} else {
					super.paintComponent(g);
				}
			}
		};
		F5.setContentPane(panel);
		ImageIcon Img5 = new ImageIcon(getClass().getResource("T3.png"));
		Image5 = new JLabel(Img5);
		Image5.setBounds(1000, 20, 350, 650);
		// F5.getContentPane().add(Image5).setVisible(true);

		ImageIcon Img9 = new ImageIcon(getClass().getResource("T3.png"));
		Image9 = new JLabel(Img9);
		Image9.setBounds(0, 20, 400, 650);
		F5.getContentPane().add(Image9).setVisible(true);

		l4 = new JLabel("Task Manager");
		l4.setBounds(540, 1, 260,50);
		l4.setForeground(Color.black);
		l4.setFont(new Font("SERIF", Font.BOLD, 40));
		F5.getContentPane().add(l4);

//panel5 start
		/*
		 JPanel panel5 = new JPanel(null);
		panel5.setName("Enter Project Details");
		panel5.setBounds(394, 60, 577, 609);
		panel5.setBackground(new Color(255, 204, 255));
*/
		labelpn = new JLabel("Project Name");
		labelpn.setBounds(668, 62, 150, 70);
		labelpn.setFont(new Font("Serif", Font.PLAIN, 25));
		Text1 = new JTextField();
		Text1.setBounds(906, 88, 173, 30);

		labelln = new JLabel("Team Leader");
		labelln.setBounds(668, 113, 150, 70);
		labelln.setFont(new Font("Serif", Font.PLAIN, 25));

		labeld1 = new JLabel("Start Date");
		labeld1.setBounds(668, 174, 165, 70);
		labeld1.setFont(new Font("Serif", Font.PLAIN, 25));
		datechStartD = new JDateChooser(new Date());
		datechStartD.setBounds(906, 194, 173, 30);

		labeld2 = new JLabel("End Date");
		labeld2.setBounds(668, 228, 165, 70);
		labeld2.setFont(new Font("Serif", Font.PLAIN, 25));
		dateChooserEndD = new JDateChooser(new Date());
		dateChooserEndD.setBounds(906, 250, 173, 30);

		labelcourse = new JLabel("Set Course");
		labelcourse.setBounds(668, 290, 165, 64);
		labelcourse.setFont(new Font("SERIF", Font.PLAIN, 25));

		labelpswd = new JLabel("Set Password");
		labelpswd.setBounds(668, 345, 165, 70);
		labelpswd.setFont(new Font("SERIF", Font.PLAIN, 25));
		pswd = new JPasswordField();
		pswd.setBounds(906, 371, 173, 30);

		sub = new JButton("Submit");
		sub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // 2020-10-02
				// new SimpleDateFormat("dd-MMMM-yyyy"); // 02-October-2020

				String projectName = Text1.getText();
				String teamleader = cmbTLeader.getSelectedItem().toString();
				String startDate = sdf.format(datechStartD.getDate());
				String endDate = sdf.format(dateChooserEndD.getDate());
				String course = cmbCourse.getSelectedItem().toString();
				String ppass = pswd.getText();
				String teamLeaderName = cmbTLeader.getSelectedItem().toString();
				String teamLeaderEmail = null;

				for (Map.Entry s : teacherNames.entrySet()) {
					if (teamLeaderName.equalsIgnoreCase(s.getValue().toString())) {
						teamLeaderEmail = s.getKey().toString();
						break;
					}
				}

				// System.out.println(projectName+" "+teamleader+" "+startDate+" "+
				// endDate+" "+course+" "+ppass);

				try {
					DatabaseConfig.initialize();
					DatabaseConfig.stmt.executeUpdate("insert into workhandler.projectdetails\r\n"
							+ "(Project_Password,Course,Project_Name,Team_Leader,TeamLeaderEmail,Start_Date,End_Date)\r\n"
							+ "values('" + ppass + "','" + course + "','" + projectName + "','" + teamleader + "','"
							+ teamLeaderEmail + "',str_to_date('" + startDate + "','%d-%m-%Y'),str_to_date('" + endDate
							+ "','%d-%m-%Y'));");

					DatabaseConfig.rs = DatabaseConfig.stmt
							.executeQuery("Select ProjectID from workhandler.projectdetails where Team_Leader='"
									+ teamleader + "' and Project_Name='" + projectName + "';");
					int projId = 0;
					while (DatabaseConfig.rs.next()) {
						projId = DatabaseConfig.rs.getInt(1);
					}

					DatabaseConfig.ps = DatabaseConfig.con
							.prepareStatement("insert into workhandler.project_tasks values(?,?,?);");
					for (JTextField t : tasks) {
						DatabaseConfig.ps.setInt(1, projId);
						DatabaseConfig.ps.setInt(2, tasks.indexOf(t));
						DatabaseConfig.ps.setString(3, t.getText());

						DatabaseConfig.ps.executeUpdate();

						// System.out.println(t.getText());
					}

					DatabaseConfig.rs = DatabaseConfig.stmt
							.executeQuery("select email FROM workhandler.student where course='" + course + "';");
					ArrayList<String> studemailid = new ArrayList<>();
					while (DatabaseConfig.rs.next()) {
						studemailid.add(DatabaseConfig.rs.getString(1));
					}

					DatabaseConfig.ps = DatabaseConfig.con
							.prepareStatement("insert into workhandler.studprojectstatus values(?,?,?);");
					for (String em : studemailid) {
						DatabaseConfig.ps.setString(1, HardCodeData.projStatus[0]); // for student setting status as
																					// assigned
						DatabaseConfig.ps.setString(2, em);
						DatabaseConfig.ps.setInt(3, projId);

						DatabaseConfig.ps.executeUpdate();

						// System.out.println(t.getText());
					}

					DatabaseConfig.con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				JOptionPane.showMessageDialog(null, "Project " + projectName + " Assigned ");
			}
		});
		sub.setBorder(null);
		sub.setBounds(693, 616, 140, 30);

		back1 = new JButton("Logout");
		back1.setBounds(917, 616, 140, 30);
		back1.setBorder(null);
		back1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				F5.dispose();
				new Loginpg();
			}
		});

		lblNewLabel = new JLabel("No. of Tasks");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		lblNewLabel.setBounds(668, 421, 165, 30);
		F5.getContentPane().add(lblNewLabel);

		spinField = new JSpinField(0, 20);// min value and max value
		spinField.getSpinner().setFont(new Font("Serif", Font.PLAIN, 20));
		spinField.setBounds(910, 429, 57, 22);
		F5.getContentPane().add(spinField);

		btnNewButton = new JButton("Set Task");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int rows = (int) spinField.getValue();

				JLabel lbltasks[] = new JLabel[rows];
				JTextField txtfieldtasks[] = new JTextField[rows];

				tasks = new ArrayList<JTextField>();
				tasks.clear();

				Component[] complist = gridpanel.getComponents();
				for (Component c : complist) {
					gridpanel.remove(c);
				}

				for (int i = 0; i < rows; i++) {
					lbltasks[i] = new JLabel("Task No. " + String.format("%d", i + 1));
					txtfieldtasks[i] = new JTextField(30);

					tasks.add(txtfieldtasks[i]);
					JPanel row = new JPanel();

					row.add(lbltasks[i]);
					row.add(txtfieldtasks[i]);
					gridpanel.add(row);
				}
				/*
				 * gridpanel.setBackground(new Color(255, 204, 255));
				 * borderpanel.setBackground(new Color(255, 204, 255));
				 * scrollPane.setBackground(new Color(255, 204, 255));
				 */

				borderpanel.repaint();
				gridpanel.repaint();
				scrollPane.revalidate();
				scrollPane.repaint();

			}
		});
		btnNewButton.setBounds(991, 429, 88, 22);
		F5.getContentPane().add(btnNewButton);

		F5.getContentPane().add(labelpn);
		F5.getContentPane().add(Text1);

		F5.getContentPane().add(labelln);

		F5.getContentPane().add(labeld1);

		F5.getContentPane().add(labeld2);

		F5.getContentPane().add(labelcourse);

		F5.getContentPane().add(labelpswd);
		F5.getContentPane().add(pswd);

		F5.getContentPane().add(sub);
		F5.getContentPane().add(back1);

		F5.getContentPane().add(datechStartD);
		F5.getContentPane().add(dateChooserEndD);

		// panel5.setVisible(true);
		// panel5 end
		// new panel
		/*
		 * JPanel panelt =new JPanel(null); panelt.setBounds(0,416,550,126);
		 * panelt.setBackground(Color.blue); //panelt.setVisible(false);
		 * panel5.add(panelt);
		 */
		// new panel ends
		//F5.getContentPane().add(panel5);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(639, 462, 479, 122);
		F5.getContentPane().add(scrollPane);

		borderpanel = new JPanel();

		scrollPane.setViewportView(borderpanel);
		scrollPane.getViewport().setPreferredSize(new Dimension(479, 122));

		borderpanel.setLayout(new BorderLayout());

		gridpanel = new JPanel();

		borderpanel.add(gridpanel, BorderLayout.NORTH);
		borderpanel.setBackground(new Color(255, 204, 255));
		gridpanel.setLayout(new GridLayout(0, 1));

		cmbCourse = new JComboBox(coursenames);
		cmbCourse.setBackground(Color.WHITE);
		cmbCourse.setBounds(906, 313, 173, 30);
		F5.getContentPane().add(cmbCourse);

		cmbTLeader = new JComboBox(new DefaultComboBoxModel<String>(getTechersName().toArray(new String[0])));
		cmbTLeader.setBackground(Color.WHITE);
		cmbTLeader.setBounds(906, 139, 173, 30);
		F5.getContentPane().add(cmbTLeader);

		F5.getContentPane().setLayout(null);
		F5.setSize(1365, 730);
		F5.setVisible(true);
		F5.setLocationRelativeTo(null);
		F5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private ArrayList<String> getTechersName() {
		teacherNames = new HashMap<String, String>();
		try {
			DatabaseConfig.initialize();
			DatabaseConfig.rs = DatabaseConfig.stmt
					.executeQuery("select Firstname,Lastname,email from workhandler.faculty;");
			while (DatabaseConfig.rs.next()) {
				// teacherNames.add(DatabaseConfig.rs.getString(1)+"
				// "+DatabaseConfig.rs.getString(2));
				teacherNames.put(DatabaseConfig.rs.getString(3),
						DatabaseConfig.rs.getString(1) + " " + DatabaseConfig.rs.getString(2));
			}
			DatabaseConfig.con.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// System.out.println(teacherNames.values());
		return new ArrayList<String>(teacherNames.values());
	}

	public static void main(String[] args) {
		new NewProjectDetails();
	}
}