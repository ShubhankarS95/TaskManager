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
	
	private static String section,useremail,usertype;

	private class TasksButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// JOptionPane.showMessageDialog(null," "+e.getActionCommand());
			// System.out.println(e.getActionCommand().split("-",-1)[0]);
			if(usertype.equals(HardCodeData.usertype[1]) || usertype.equals(HardCodeData.usertype[2]) )
			{
				if (e.getActionCommand().equals("ADD PROJECT"))
				{	
					F7.dispose();
					new NewProjectDetails();				
				}
				else
				{
					F7.dispose();
					new ProjectViewTeacher(Integer.parseInt(e.getActionCommand().split("-", -1)[0]),usertype);
				}
			}
			else if(usertype.equals(HardCodeData.usertype[3]) )
			{
				F7.dispose();
				new ProjectViewStudent(Integer.parseInt(e.getActionCommand().split("-", -1)[0]));
			}
		}
	}

	public Mainpg(String useremailname, String utype) {
		useremail=useremailname;
		usertype=utype;
		
		
		F7 = new JFrame("Main Dashboard of " + getUserName(useremailname, usertype));
		F7.getContentPane().setBackground(Color.WHITE);

		back1 = new JButton("Logout");
		back1.setBounds(600, 600, 150, 40);
		back1.setBorder(null);
		// back.setBackground(Color.WHITE);
		back1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				F7.dispose();
				new Loginpg();
			}
		});
		F7.getContentPane().add(back1);

		/*
		 * ImageIcon Img9=new ImageIcon("Icon.png"); JLabel Image9=new JLabel(Img9);
		 * Image9.setBounds(10,20, 350, 650); F7.getContentPane().add(Image9);
		 * 
		 * ImageIcon Img10=new ImageIcon("Icon.png"); JLabel Image10=new JLabel(Img10);
		 * Image10.setBounds(975,20, 350, 650); F7.getContentPane().add(Image10);
		 */

		l7 = new JLabel("TASK MANAGER");
		l7.setHorizontalAlignment(SwingConstants.CENTER);
		l7.setForeground(Color.PINK);
		l7.setBounds(328, 0, 700, 50);
		l7.setFont(new Font("SERIF", Font.BOLD, 40));
		F7.getContentPane().add(l7);

		panel7 = new JPanel(null);
		panel7.setBounds(328, 73, 700, 400);
		panel7.setBackground(new Color(153, 255, 153));
		F7.getContentPane().add(panel7);

		Todo = new JButton("List of Work");
		Todo.setBounds(24, 10, 170, 30);
		Todo.setBorder(null);
		Todo.setBackground(Color.YELLOW);
		Todo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				scpaneTaskList.setVisible(true);
				scpaneTaskinProgress.setVisible(false);
				scpaneTaskCompleted.setVisible(false);
				section=HardCodeData.projStatus[0];
				
				// System.out.println("Button Task List :"+projectNames.values());

				Component[] complist = gridpanelTaskList.getComponents();
				for (Component c : complist) {
					gridpanelTaskList.remove(c);
				}
				
				JButton buttontasklist[];
				if(usertype.equals(HardCodeData.usertype[2])  ||  usertype.equals(HardCodeData.usertype[1]))
				{
					buttontasklist= new JButton[projectNamesList.size()+1];
				}
				else
				{
					buttontasklist= new JButton[projectNamesList.size()];
				}
				
				int j = 0;
				TasksButtonListener taskbutListener = new TasksButtonListener();
				for (Map.Entry key : projectNamesList.entrySet()) {
					buttontasklist[j] = new JButton(key.getKey().toString() + "-" + key.getValue().toString());
					buttontasklist[j].addActionListener(taskbutListener);

					JPanel perpanel = new JPanel();
					perpanel.add(buttontasklist[j]);
					j++;

					gridpanelTaskList.add(perpanel);
				}
				if(usertype.equals(HardCodeData.usertype[2])  ||  usertype.equals(HardCodeData.usertype[1]))
				{
					buttontasklist[j] = new JButton("ADD PROJECT");
					buttontasklist[j].addActionListener(taskbutListener);

					JPanel perpanel = new JPanel();
					perpanel.add(buttontasklist[j]);
					
					gridpanelTaskList.add(perpanel);
				}
				if(usertype.equals(HardCodeData.usertype[3]) && projectNamesList.size()==0 )
				{
					JLabel lbl=new JLabel("No Project Found");

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
		Todoing.setBounds(245, 10, 170, 30);
		Todoing.setBorder(null);
		Todoing.setBackground(Color.YELLOW);
		Todoing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scpaneTaskList.setVisible(false);
				scpaneTaskinProgress.setVisible(true);
				scpaneTaskCompleted.setVisible(false);
				section=HardCodeData.projStatus[1];
				
				Component[] complist = gridpanelTaskinProgress.getComponents();
				for (Component c : complist) {
					gridpanelTaskinProgress.remove(c);
				}
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
				if(usertype.equals(HardCodeData.usertype[3]) && projectNamesProgress.size()==0 )
				{
					JLabel lbl=new JLabel("No Project Found");

					JPanel perpanel = new JPanel();
					perpanel.add(lbl);
					
					gridpanelTaskinProgress.add(perpanel);
				}
				
				gridpanelTaskinProgress.repaint();
				borderpanelTaskinProgress.repaint();
				scpaneTaskinProgress.repaint();
				scpaneTaskinProgress.revalidate();
				
			}
		});

		Done = new JButton("Completed Works");
		Done.setBounds(493, 10, 170, 30);
		Done.setBorder(null);
		Done.setBackground(Color.YELLOW);
		Done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				scpaneTaskList.setVisible(false);
				scpaneTaskinProgress.setVisible(false);
				scpaneTaskCompleted.setVisible(true);
				section=HardCodeData.projStatus[2];
				
				Component[] complist = gridpanelTaskCompleted.getComponents();
				for (Component c : complist) {
					gridpanelTaskCompleted.remove(c);
				}
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
				if(usertype.equals(HardCodeData.usertype[3]) && projectNamesCompleted.size()==0 )
				{
					JLabel lbl=new JLabel("No Project Found");

					JPanel perpanel = new JPanel();
					perpanel.add(lbl);
					
					gridpanelTaskCompleted.add(perpanel);
				}
				
				gridpanelTaskCompleted.repaint();
				borderpanelTaskCompleted.repaint();
				scpaneTaskCompleted.repaint();
				scpaneTaskCompleted.revalidate();
				
			}
		});
		panel7.add(Todo);
		panel7.add(Todoing);
		panel7.add(Done);

		scpaneTaskList = new JScrollPane();
		scpaneTaskList.setBounds(20, 60, 190, 300);
		scpaneTaskList.setVisible(false);
		panel7.add(scpaneTaskList);

		borderpanleTaskList = new JPanel();
		scpaneTaskList.setViewportView(borderpanleTaskList);
		borderpanleTaskList.setLayout(new BorderLayout(0, 5));

		gridpanelTaskList = new JPanel();
		borderpanleTaskList.add(gridpanelTaskList);
		gridpanelTaskList.setLayout(new GridLayout(0, 1));

		scpaneTaskinProgress = new JScrollPane();
		scpaneTaskinProgress.setBounds(250, 60, 190, 300);
		scpaneTaskinProgress.setVisible(false);
		panel7.add(scpaneTaskinProgress);

		borderpanelTaskinProgress = new JPanel();
		scpaneTaskinProgress.setViewportView(borderpanelTaskinProgress);
		borderpanelTaskinProgress.setLayout(new BorderLayout(0, 0));

		gridpanelTaskinProgress = new JPanel();
		borderpanelTaskinProgress.add(gridpanelTaskinProgress);
		gridpanelTaskinProgress.setLayout(new GridLayout(0, 1));

		scpaneTaskCompleted = new JScrollPane();
		scpaneTaskCompleted.setBounds(490, 60, 190, 300);
		scpaneTaskCompleted.setVisible(false);
		panel7.add(scpaneTaskCompleted);

		borderpanelTaskCompleted = new JPanel();
		scpaneTaskCompleted.setViewportView(borderpanelTaskCompleted);
		borderpanelTaskCompleted.setLayout(new BorderLayout(0, 0));

		gridpanelTaskCompleted = new JPanel();
		borderpanelTaskCompleted.add(gridpanelTaskCompleted);
		gridpanelTaskCompleted.setLayout(new GridLayout(0, 1));

		F7.getContentPane().setLayout(null);
		F7.setSize(1365, 730);
		F7.setVisible(true);
		F7.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getProjectName(useremailname, usertype);
		System.out.println("Consturctor");
	}

	public static void main(String[] args) {
		new Mainpg("Shubhankar@xyz.com","STUDENT");
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

		projectNamesList=new HashMap<Integer, String>();
		projectNamesProgress=new HashMap<Integer, String>();
		projectNamesCompleted=new HashMap<Integer, String>();
		
		try {
			DatabaseConfig.initialize();

			if (userType.equals(HardCodeData.usertype[3])) // for student
			{
				DatabaseConfig.rs = DatabaseConfig.stmt.executeQuery(
						"Select ps.ProjectId,p.Project_Name,ps.ProjStatus FROM workhandler.projectdetails p,workhandler.studprojectstatus ps "
						+ "where p.ProjectId=ps.ProjectId and ps.StudEmail='"+useremailname+"';");
				
				while (DatabaseConfig.rs.next()) {
					if(DatabaseConfig.rs.getString(3).equals(HardCodeData.projStatus[0]))
					{
						projectNamesList.put( DatabaseConfig.rs.getInt(1), DatabaseConfig.rs.getString(2) );
					}
					else if(DatabaseConfig.rs.getString(3).equals(HardCodeData.projStatus[1]))
					{
						projectNamesProgress.put( DatabaseConfig.rs.getInt(1), DatabaseConfig.rs.getString(2) );
					}
					else if(DatabaseConfig.rs.getString(3).equals(HardCodeData.projStatus[2]))
					{
						projectNamesCompleted.put( DatabaseConfig.rs.getInt(1), DatabaseConfig.rs.getString(2) );
					}
					
				}
			} else if (userType.equals(HardCodeData.usertype[2]))// for faculty
			{
				DatabaseConfig.rs = DatabaseConfig.stmt
						.executeQuery("select projectid,project_name,start_date,end_date from workhandler.projectdetails where TeamLeaderEmail='"+useremailname+"';");
				Date curr=new Date();
				Date startDate,endDate;
				
				while (DatabaseConfig.rs.next()) {
					startDate=DatabaseConfig.rs.getDate(3);
					endDate=DatabaseConfig.rs.getDate(4);
					if(startDate.compareTo(curr)>0)  
					{
						System.out.println("1 :"+DatabaseConfig.rs.getInt(1)+" "+startDate+" "+endDate);
						projectNamesList.put( DatabaseConfig.rs.getInt(1), DatabaseConfig.rs.getString(2) );
					}
					else if(startDate.compareTo(curr)<=0  && endDate.compareTo(curr)>=0)
					{
						System.out.println("2 "+DatabaseConfig.rs.getInt(1)+" "+startDate+" "+endDate);
						projectNamesProgress.put( DatabaseConfig.rs.getInt(1), DatabaseConfig.rs.getString(2) );
						
					}
					else if(endDate.compareTo(curr)<0)
					{
						System.out.println("3 "+DatabaseConfig.rs.getInt(1)+" "+startDate+" "+endDate);
						projectNamesCompleted.put( DatabaseConfig.rs.getInt(1), DatabaseConfig.rs.getString(2) );
					}
				}			
			} else // for admin
			{
				DatabaseConfig.rs = DatabaseConfig.stmt
						.executeQuery("select projectid,project_name,start_date,end_date from workhandler.projectdetails;");
				Date curr=new Date();
				Date startDate,endDate;
				
				while (DatabaseConfig.rs.next()) {
					startDate=DatabaseConfig.rs.getDate(3);
					endDate=DatabaseConfig.rs.getDate(4);
					if(startDate.compareTo(curr)>0)  
					{
						System.out.println("1 :"+DatabaseConfig.rs.getInt(1)+" "+startDate+" "+endDate);
						projectNamesList.put( DatabaseConfig.rs.getInt(1), DatabaseConfig.rs.getString(2) );
					}
					else if(startDate.compareTo(curr)<=0  && endDate.compareTo(curr)>=0)
					{
						System.out.println("2 "+DatabaseConfig.rs.getInt(1)+" "+startDate+" "+endDate);
						projectNamesProgress.put( DatabaseConfig.rs.getInt(1), DatabaseConfig.rs.getString(2) );
						
					}
					else if(endDate.compareTo(curr)<0)
					{
						System.out.println("3 "+DatabaseConfig.rs.getInt(1)+" "+startDate+" "+endDate);
						projectNamesCompleted.put( DatabaseConfig.rs.getInt(1), DatabaseConfig.rs.getString(2) );
					}
				}
				
			}
			DatabaseConfig.con.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
