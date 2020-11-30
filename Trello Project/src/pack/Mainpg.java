package pack;

import java.awt.*;
import javax.swing.*;

import dbdetails.DatabaseConfig;
import dbdetails.HardCodeData;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Mainpg {
	private JButton Done;
	private JScrollPane scpaneTaskCompleted;
	private JScrollPane scpaneTaskinProgress;
	private JScrollPane scpaneTaskList;
	private JButton Todoing;
	private JButton Todo;
	private JPanel panel7;
	private JLabel l7;
	private JButton back1;
	private JPanel borderpanleTaskList;
	private JPanel gridpanelTaskList;
	private JPanel borderpanelTaskinProgress;
	private JPanel gridpanelTaskinProgress;
	private JPanel borderpanelTaskCompleted;
	private JPanel gridpanelTaskCompleted;
	private JFrame F7;

	private HashMap<Integer, String> projectNamesList;
	private HashMap<Integer, String> projectNamesProgress;
	private HashMap<Integer, String> projectNamesCompleted;

	private static String section, useremail, usertype;

	private class TasksButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (usertype.equals(HardCodeData.usertype[1]) || usertype.equals(HardCodeData.usertype[2])) {
				if (e.getActionCommand().equals("ADD PROJECT")) {
					F7.dispose();
					new NewProjectDetails(useremail, usertype);
				} else {
					F7.dispose();
					new ProjectViewTeacher(Integer.parseInt(e.getActionCommand().split("-", -1)[0]),useremail, usertype, section);
				}
			} else if (usertype.equals(HardCodeData.usertype[3])) {
				F7.dispose();
				new ProjectViewStudent(Integer.parseInt(e.getActionCommand().split("-", -1)[0]), useremail, section);
			}
		}
	}

	public Mainpg(String useremailname, String utype) {
		useremail = useremailname;
		usertype = utype;

		F7 = new JFrame("Main Dashboard of " + getUserName(useremailname, usertype));
		// F7.getContentPane().setBackground(Color.WHITE);
		JPanel panel = new javax.swing.JPanel() {
			protected void paintComponent(Graphics g) {

				if (g instanceof Graphics2D) {
					Paint p = new GradientPaint(0, 0, new Color(255, 51, 0), getWidth(), getHeight(),
							new Color(255, 0, 102), true);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setPaint(p);
					g2d.fillRect(0, 0, getWidth(), getHeight());
				} else {
					super.paintComponent(g);
				}
			}
		};
		F7.setContentPane(panel);

		back1 = new JButton("Logout");
		back1.setBounds(613, 561, 150, 40);
		back1.setBorder(null);
		// back.setBackground(Color.WHITE);
		back1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				F7.dispose();
				new Loginpg();
			}
		});
		F7.getContentPane().add(back1);

		
		l7 = new JLabel("TASK MANAGER");
		l7.setHorizontalAlignment(SwingConstants.CENTER);
		l7.setForeground(Color.black);
		l7.setBounds(328, 0, 700, 50);
		l7.setFont(new Font("SERIF", Font.BOLD, 40));
		F7.getContentPane().add(l7);
		
		Todo = new JButton("List of Work");
		Todo.setBounds(355, 140, 170, 30);
		Todo.setBorder(null);
		Todo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				scpaneTaskList.setVisible(true);
				scpaneTaskinProgress.setVisible(false);
				scpaneTaskCompleted.setVisible(false);
				section = HardCodeData.projStatus[0];

				// System.out.println("Button Task List :"+projectNames.values());

				Component[] complist = gridpanelTaskList.getComponents();
				for (Component c : complist) {
					gridpanelTaskList.remove(c);
				}

				JButton buttontasklist[];
				if (usertype.equals(HardCodeData.usertype[2]) || usertype.equals(HardCodeData.usertype[1])) {
					buttontasklist = new JButton[projectNamesList.size() + 1];
				} else {
					buttontasklist = new JButton[projectNamesList.size()];
				}

				int j = 0;
				TasksButtonListener taskbutListener = new TasksButtonListener();
				if (usertype.equals(HardCodeData.usertype[2]) || usertype.equals(HardCodeData.usertype[1])) {
					buttontasklist[j] = new JButton("ADD PROJECT");
					buttontasklist[j].addActionListener(taskbutListener);

					JPanel perpanel = new JPanel();
					perpanel.add(buttontasklist[j]);
					j++;
					gridpanelTaskList.add(perpanel);
				}

				for (Map.Entry key : projectNamesList.entrySet()) {
					buttontasklist[j] = new JButton(key.getKey().toString() + "-" + key.getValue().toString());
					buttontasklist[j].addActionListener(taskbutListener);

					JPanel perpanel = new JPanel();
					perpanel.add(buttontasklist[j]);
					j++;
					gridpanelTaskList.add(perpanel);
				}

				if (usertype.equals(HardCodeData.usertype[3]) && projectNamesList.size() == 0) {
					JLabel lbl = new JLabel("No Project Found");

					JPanel perpanel = new JPanel();
					perpanel.add(lbl);

					gridpanelTaskList.add(perpanel);
				}

				gridpanelTaskList.repaint();
				borderpanleTaskList.repaint();
				scpaneTaskList.repaint();
				scpaneTaskList.revalidate();

			}
		});

		Todoing = new JButton("In-Progress");
		Todoing.setBounds(588, 140, 170, 30);
		Todoing.setBorder(null);
		// Todoing.setBackground(Color.YELLOW);
		Todoing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scpaneTaskList.setVisible(false);
				scpaneTaskinProgress.setVisible(true);
				scpaneTaskCompleted.setVisible(false);
				section = HardCodeData.projStatus[1];

				Component[] complist = gridpanelTaskinProgress.getComponents();
				for (Component c : complist) {
					gridpanelTaskinProgress.remove(c);
				}
				if (projectNamesProgress.size() == 0) {
					JLabel lbl = new JLabel("No Project Found");

					JPanel perpanel = new JPanel();
					perpanel.add(lbl);

					gridpanelTaskinProgress.add(perpanel);
				} else {
					JButton buttontasklist[] = new JButton[projectNamesProgress.size()];

					int j = 0;
					TasksButtonListener taskbutListener = new TasksButtonListener();
					for (Map.Entry key : projectNamesProgress.entrySet()) {
						buttontasklist[j] = new JButton(key.getKey().toString() + "-" + key.getValue().toString());
						buttontasklist[j].addActionListener(taskbutListener);

						JPanel perpanel = new JPanel();
						perpanel.add(buttontasklist[j]);
						j++;

						gridpanelTaskinProgress.add(perpanel);
					}
				}

				gridpanelTaskinProgress.repaint();
				borderpanelTaskinProgress.repaint();
				scpaneTaskinProgress.repaint();
				scpaneTaskinProgress.revalidate();

			}
		});

		Done = new JButton("Completed Works");
		Done.setBounds(824, 140, 170, 30);
		Done.setBorder(null);
		// Done.setBackground(Color.YELLOW);
		Done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				scpaneTaskList.setVisible(false);
				scpaneTaskinProgress.setVisible(false);
				scpaneTaskCompleted.setVisible(true);
				section = HardCodeData.projStatus[2];

				Component[] complist = gridpanelTaskCompleted.getComponents();
				for (Component c : complist) {
					gridpanelTaskCompleted.remove(c);
				}
				if (projectNamesCompleted.size() == 0) {
					JLabel lbl = new JLabel("No Project Found");

					JPanel perpanel = new JPanel();
					perpanel.add(lbl);

					gridpanelTaskCompleted.add(perpanel);
				} else {
					JButton buttontasklist[] = new JButton[projectNamesCompleted.size()];

					int j = 0;
					TasksButtonListener taskbutListener = new TasksButtonListener();
					for (Map.Entry key : projectNamesCompleted.entrySet()) {
						buttontasklist[j] = new JButton(key.getKey().toString() + "-" + key.getValue().toString());
						buttontasklist[j].addActionListener(taskbutListener);

						JPanel perpanel = new JPanel();
						perpanel.add(buttontasklist[j]);
						j++;

						gridpanelTaskCompleted.add(perpanel);
					}
				}

				gridpanelTaskCompleted.repaint();
				borderpanelTaskCompleted.repaint();
				scpaneTaskCompleted.repaint();
				scpaneTaskCompleted.revalidate();

			}
		});
		F7.getContentPane().add(Todo);
		F7.getContentPane().add(Todoing);
		F7.getContentPane().add(Done);

		scpaneTaskList = new JScrollPane();
		scpaneTaskList.setBounds(351, 190, 190, 300);
		scpaneTaskList.setVisible(false);
		F7.getContentPane().add(scpaneTaskList);

		borderpanleTaskList = new JPanel();
		scpaneTaskList.setViewportView(borderpanleTaskList);
		borderpanleTaskList.setLayout(new BorderLayout(0, 5));

		gridpanelTaskList = new JPanel();
		borderpanleTaskList.add(gridpanelTaskList);
		gridpanelTaskList.setLayout(new GridLayout(0, 1));

		scpaneTaskinProgress = new JScrollPane();
		scpaneTaskinProgress.setBounds(581, 190, 190, 300);
		scpaneTaskinProgress.setVisible(false);
		F7.getContentPane().add(scpaneTaskinProgress);

		borderpanelTaskinProgress = new JPanel();
		scpaneTaskinProgress.setViewportView(borderpanelTaskinProgress);
		borderpanelTaskinProgress.setLayout(new BorderLayout(0, 0));

		gridpanelTaskinProgress = new JPanel();
		borderpanelTaskinProgress.add(gridpanelTaskinProgress);
		gridpanelTaskinProgress.setLayout(new GridLayout(0, 1));

		scpaneTaskCompleted = new JScrollPane();
		scpaneTaskCompleted.setBounds(821, 190, 190, 300);
		scpaneTaskCompleted.setVisible(false);
		F7.getContentPane().add(scpaneTaskCompleted);

		borderpanelTaskCompleted = new JPanel();
		scpaneTaskCompleted.setViewportView(borderpanelTaskCompleted);
		borderpanelTaskCompleted.setLayout(new BorderLayout(0, 0));

		gridpanelTaskCompleted = new JPanel();
		borderpanelTaskCompleted.add(gridpanelTaskCompleted);
		gridpanelTaskCompleted.setLayout(new GridLayout(0, 1));

		F7.getContentPane().setLayout(null);
		F7.setSize(1365, 730);
		F7.setVisible(true);
		F7.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		F7.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				F7.dispose();
				new Loginpg();
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		getProjectName(useremailname, usertype);
		System.out.println("Dashboard Consturctor");
	}

	private String getUserName(String useremail, String usertype) {
		String username = "";

		if (usertype.equals(HardCodeData.usertype[1]) == false) {
			try {
				DatabaseConfig.initialize();

				if (usertype.equals(HardCodeData.usertype[3])) // for student
				{
					DatabaseConfig.rs = DatabaseConfig.stmt.executeQuery(
							"select Firstname,Lastname FROM workhandler.student where email='" + useremail + "';");
				} else if (usertype.equals(HardCodeData.usertype[2]))// for faculty
				{
					DatabaseConfig.rs = DatabaseConfig.stmt.executeQuery(
							"SELECT Firstname, Lastname FROM workhandler.faculty where email ='" + useremail + "';");
				}

				while (DatabaseConfig.rs.next()) {
					// teacherNames.add(DatabaseConfig.rs.getString(1)+"
					// "+DatabaseConfig.rs.getString(2));
					username = DatabaseConfig.rs.getString(1) + " " + DatabaseConfig.rs.getString(2);
				}
				DatabaseConfig.con.close();

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else
			username = "ADMIN";

		return username;
	}

	private void getProjectName(String useremailname, String userType) {

		projectNamesList = new HashMap<Integer, String>();
		projectNamesProgress = new HashMap<Integer, String>();
		projectNamesCompleted = new HashMap<Integer, String>();

		try {
			DatabaseConfig.initialize();

			if (userType.equals(HardCodeData.usertype[3])) // for student
			{
				DatabaseConfig.rs = DatabaseConfig.stmt.executeQuery(
						"Select ps.ProjectId,p.Project_Name,ps.ProjStatus FROM workhandler.projectdetails p,workhandler.studprojectstatus ps "
								+ "where p.ProjectId=ps.ProjectId and ps.StudEmail='" + useremailname + "';");

				while (DatabaseConfig.rs.next()) {
					if (DatabaseConfig.rs.getString(3).equals(HardCodeData.projStatus[0])) {
						projectNamesList.put(DatabaseConfig.rs.getInt(1), DatabaseConfig.rs.getString(2));
					} else if (DatabaseConfig.rs.getString(3).equals(HardCodeData.projStatus[1])) {
						projectNamesProgress.put(DatabaseConfig.rs.getInt(1), DatabaseConfig.rs.getString(2));
					} else if (DatabaseConfig.rs.getString(3).equals(HardCodeData.projStatus[2])) {
						projectNamesCompleted.put(DatabaseConfig.rs.getInt(1), DatabaseConfig.rs.getString(2));
					}

				}
			} else if (userType.equals(HardCodeData.usertype[2]))// for faculty
			{
				DatabaseConfig.rs = DatabaseConfig.stmt.executeQuery(
						"select projectid,project_name,start_date,end_date from workhandler.projectdetails where TeamLeaderEmail='"
								+ useremailname + "';");
				Date curr = new Date();
				Date startDate, endDate;

				while (DatabaseConfig.rs.next()) {
					startDate = DatabaseConfig.rs.getDate(3);
					endDate = DatabaseConfig.rs.getDate(4);
					if (startDate.compareTo(curr) > 0) {
						System.out.println("1 :" + DatabaseConfig.rs.getInt(1) + " " + startDate + " " + endDate);
						projectNamesList.put(DatabaseConfig.rs.getInt(1), DatabaseConfig.rs.getString(2));
					} else if (startDate.compareTo(curr) <= 0 && endDate.compareTo(curr) >= 0) {
						System.out.println("2 " + DatabaseConfig.rs.getInt(1) + " " + startDate + " " + endDate);
						projectNamesProgress.put(DatabaseConfig.rs.getInt(1), DatabaseConfig.rs.getString(2));

					} else if (endDate.compareTo(curr) < 0) {
						System.out.println("3 " + DatabaseConfig.rs.getInt(1) + " " + startDate + " " + endDate);
						projectNamesCompleted.put(DatabaseConfig.rs.getInt(1), DatabaseConfig.rs.getString(2));
					}
				}
			} else // for admin
			{
				DatabaseConfig.rs = DatabaseConfig.stmt.executeQuery(
						"select projectid,project_name,start_date,end_date from workhandler.projectdetails;");
				Date curr = new Date();
				Date startDate, endDate;

				while (DatabaseConfig.rs.next()) {
					startDate = DatabaseConfig.rs.getDate(3);
					endDate = DatabaseConfig.rs.getDate(4);
					if (startDate.compareTo(curr) > 0) {
						System.out.println("1 :" + DatabaseConfig.rs.getInt(1) + " " + startDate + " " + endDate);
						projectNamesList.put(DatabaseConfig.rs.getInt(1), DatabaseConfig.rs.getString(2));
					} else if (startDate.compareTo(curr) <= 0 && endDate.compareTo(curr) >= 0) {
						System.out.println("2 " + DatabaseConfig.rs.getInt(1) + " " + startDate + " " + endDate);
						projectNamesProgress.put(DatabaseConfig.rs.getInt(1), DatabaseConfig.rs.getString(2));

					} else if (endDate.compareTo(curr) < 0) {
						System.out.println("3 " + DatabaseConfig.rs.getInt(1) + " " + startDate + " " + endDate);
						projectNamesCompleted.put(DatabaseConfig.rs.getInt(1), DatabaseConfig.rs.getString(2));
					}
				}
			}
			DatabaseConfig.con.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// new Mainpg("Shubhankar@xyz.com","STUDENT");
		new Mainpg("Admin/Dean", "DEAN");
	}

}
