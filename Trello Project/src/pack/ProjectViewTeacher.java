package pack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import dbdetails.DatabaseConfig;
import dbdetails.HardCodeData;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class ProjectViewTeacher extends JFrame {

	private JTextField txtfStartDate;
	private JTextField txtfEndDate;
	private JLabel lblProjectName;
	private JLabel lblStartDate;
	private JLabel lblEndDate;
	private JScrollPane scrollPane;
	private JPanel panBorderTakList;
	private JPanel pangridTakList;
	private JLabel lbltaskList;
	private JLabel lblstudentsnotstarted;
	private JLabel lblstudinProgress;
	private JLabel lblstudComplete;
	private JComboBox cmbstudStart;
	private JComboBox cmstudinprogress;
	private JComboBox cmbstudComplete;
	private JPanel panreport;
	private JLabel lblteamLeader;
	private JTextField txtfTeamLeader;
	private JTextField txtfCourse;
	private JLabel lblCourse;
	private JPanel panelMain;

	private ArrayList<String> studStarted, studProgress, studCompleted;

	public ProjectViewTeacher(int projid, String useremail, String usertype, String section) {
		getContentPane().setLayout(null);
		setTitle("Dashboard of " + getUserName(useremail, usertype));

		panelMain = new JPanel() {
			protected void paintComponent(Graphics g) {
				if (g instanceof Graphics2D) {
					Paint p = new GradientPaint(0, 0, new Color(255, 255, 77), getWidth(), getHeight(),
							new Color(204, 0, 68), true);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setPaint(p);
					g2d.fillRect(0, 0, getWidth(), getHeight());
				} else {
					super.paintComponent(g);
				}
			}
		};

		panelMain.setLayout(null);
		setContentPane(panelMain);

		lblProjectName = new JLabel();
		lblProjectName.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjectName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProjectName.setBounds(316, 40, 439, 50);
		panelMain.add(lblProjectName);

		lblStartDate = new JLabel("Start Date");
		lblStartDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStartDate.setBounds(35, 139, 106, 24);
		panelMain.add(lblStartDate);

		txtfStartDate = new JTextField();
		txtfStartDate.setEditable(false);
		txtfStartDate.setBounds(134, 142, 271, 24);
		panelMain.add(txtfStartDate);
		txtfStartDate.setColumns(10);

		lblEndDate = new JLabel("End Date");
		lblEndDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEndDate.setBounds(598, 139, 86, 24);
		panelMain.add(lblEndDate);

		txtfEndDate = new JTextField();
		txtfEndDate.setEditable(false);
		txtfEndDate.setColumns(10);
		txtfEndDate.setBounds(713, 142, 244, 24);
		getContentPane().add(txtfEndDate);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 250, 450, 214);
		getContentPane().add(scrollPane);

		panBorderTakList = new JPanel();
		scrollPane.setViewportView(panBorderTakList);
		panBorderTakList.setLayout(new BorderLayout(0, 0));

		pangridTakList = new JPanel();
		panBorderTakList.add(pangridTakList, BorderLayout.NORTH);
		pangridTakList.setLayout(new GridLayout(0, 1));

		lbltaskList = new JLabel("Task List");
		lbltaskList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbltaskList.setHorizontalAlignment(SwingConstants.CENTER);
		lbltaskList.setBounds(167, 216, 188, 24);
		getContentPane().add(lbltaskList);

		lblstudentsnotstarted = new JLabel("Student didn't Start");
		lblstudentsnotstarted.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblstudentsnotstarted.setBounds(29, 486, 271, 24);
		getContentPane().add(lblstudentsnotstarted);

		lblstudinProgress = new JLabel("Student list of in-progress of Project");
		lblstudinProgress.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblstudinProgress.setBounds(32, 534, 280, 24);
		getContentPane().add(lblstudinProgress);

		lblstudComplete = new JLabel("Student List of Completed the Project");
		lblstudComplete.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblstudComplete.setBounds(32, 592, 293, 24);
		getContentPane().add(lblstudComplete);

		cmbstudStart = new JComboBox();
		cmbstudStart.setBounds(342, 490, 233, 32);
		getContentPane().add(cmbstudStart);

		cmstudinprogress = new JComboBox();
		cmstudinprogress.setBounds(342, 538, 233, 32);
		getContentPane().add(cmstudinprogress);

		cmbstudComplete = new JComboBox();
		cmbstudComplete.setBounds(342, 596, 233, 32);
		getContentPane().add(cmbstudComplete);

		panreport = new JPanel(new GridLayout(0, 1));
		panreport.setBounds(598, 230, 485, 404);
		getContentPane().add(panreport);

		lblteamLeader = new JLabel("Team Leader");
		lblteamLeader.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblteamLeader.setBounds(598, 185, 106, 19);
		getContentPane().add(lblteamLeader);

		txtfTeamLeader = new JTextField();
		txtfTeamLeader.setEditable(false);
		txtfTeamLeader.setBounds(713, 182, 255, 24);
		getContentPane().add(txtfTeamLeader);
		txtfTeamLeader.setColumns(10);

		txtfCourse = new JTextField();
		txtfCourse.setEditable(false);
		txtfCourse.setColumns(10);
		txtfCourse.setBounds(138, 180, 267, 24);
		getContentPane().add(txtfCourse);

		lblCourse = new JLabel("Course");
		lblCourse.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCourse.setBounds(35, 179, 86, 24);
		getContentPane().add(lblCourse);

		setSize(1107, 724);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				new Mainpg(useremail,usertype);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
			}
		});

		getStudName(projid);
		addDetails(projid, section);
	}

	public void getStudName(int projid) {
		studStarted = new ArrayList<>();
		studProgress = new ArrayList<>();
		studCompleted = new ArrayList<>();
		try {
			DatabaseConfig.initialize();
			DatabaseConfig.rs = DatabaseConfig.stmt.executeQuery(
					"SELECT ps.Projstatus,s.Firstname,s.LastName,ps.projectid FROM workhandler.studprojectstatus ps, workhandler.student s "
							+ "where  ps.StudEmail=s.email  and ps.projectid=" + projid + ";");

			while (DatabaseConfig.rs.next()) {
				if (DatabaseConfig.rs.getString(1).equals(HardCodeData.projStatus[0]))// Assigned
				{
					studStarted.add(DatabaseConfig.rs.getString(2) + " " + DatabaseConfig.rs.getString(3));
				}
				if (DatabaseConfig.rs.getString(1).equals(HardCodeData.projStatus[1])) // in Progress
				{
					studProgress.add(DatabaseConfig.rs.getString(2) + " " + DatabaseConfig.rs.getString(3));
				}
				if (DatabaseConfig.rs.getString(1).equals(HardCodeData.projStatus[2])) // Completed
				{
					studCompleted.add(DatabaseConfig.rs.getString(2) + " " + DatabaseConfig.rs.getString(3));
				}

			}
			if (studStarted.size() == 0) {
				studStarted.add("No Student Found");
			}
			if (studProgress.size() == 0) {
				studProgress.add("No Student Found");
			}
			if (studCompleted.size() == 0) {
				studCompleted.add("No Student Found");
			}

			cmbstudStart.setModel(new DefaultComboBoxModel<String>(studStarted.toArray(new String[0])));
			cmstudinprogress.setModel(new DefaultComboBoxModel<String>(studProgress.toArray(new String[0])));
			cmbstudComplete.setModel(new DefaultComboBoxModel<String>(studCompleted.toArray(new String[0])));

			// panreport.add(lineChart());

			DatabaseConfig.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public JPanel pieChart() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		if (studStarted.size() == 1   && studStarted.contains("No Student Found") ) 
		{
			dataset.setValue("Project Assigned", 0);
		}
		else {
			dataset.setValue("Project Assigned", studStarted.size());
		}
		if (studProgress.size() == 1  && studProgress.contains("No Student Found")) 
		{
			dataset.setValue("Project Started or in-Progress ", 0);
		}
		else {
			dataset.setValue("Project in-Progress ", studProgress.size());
		}
		if (studCompleted.size() == 1  && studCompleted.contains("No Student Found")) 
		{
			dataset.setValue("Project Completed", 0);
		}
		else {
			dataset.setValue("Project Completed", studCompleted.size());
		}
		
		JFreeChart chart = ChartFactory.createPieChart("Project Progress OverView ", // chart title
				dataset, // data
				true, // include legend
				true, false);

		return new ChartPanel(chart);
	}

	public JPanel lineChart() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.setValue(2, "", "Shubhankar Satvaya");

		dataset.setValue(7, "", "Kunal Passi");

		JFreeChart chart = ChartFactory.createLineChart("Project Analysis Report ", "Student Names", "Days", dataset,
				PlotOrientation.VERTICAL, false, true, false);

		chart.setBackgroundPaint(new Color(255, 255, 255));
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setRangeGridlinesVisible(false);

		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		renderer.setShapesVisible(true);
		renderer.setItemLabelsVisible(true);

		return new ChartPanel(chart);
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

	public void addDetails(int projid, String status) {
		try {
			DatabaseConfig.initialize();
			DatabaseConfig.rs = DatabaseConfig.stmt.executeQuery(
					"Select Project_Name,course,Team_Leader,Start_Date,End_Date from workhandler.projectdetails where ProjectId="
							+ projid + ";");

			SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM,yyyy");

			while (DatabaseConfig.rs.next()) {
				// setTitle("Project Report of "+DatabaseConfig.rs.getString(1));
				lblProjectName.setText("Project Name :" + DatabaseConfig.rs.getString(1));
				txtfStartDate.setText(sdf.format(DatabaseConfig.rs.getDate(4)));
				txtfEndDate.setText(sdf.format(DatabaseConfig.rs.getDate(5)));
				txtfTeamLeader.setText(DatabaseConfig.rs.getString(3));
				txtfCourse.setText(DatabaseConfig.rs.getString(2));

			}

			DatabaseConfig.rs = DatabaseConfig.stmt.executeQuery(
					"SELECT TaskNo , Task_Info FROM workhandler.project_tasks where Project_ID=" + projid + ";");
			HashMap<Integer, String> tasklist = new HashMap<>();

			while (DatabaseConfig.rs.next()) {
				tasklist.put(DatabaseConfig.rs.getInt(1), DatabaseConfig.rs.getString(2));
			}

			JLabel[] tlist = new JLabel[tasklist.size()];
			for (Map.Entry i : tasklist.entrySet()) {
				tlist[(int) i.getKey()] = new JLabel(((int) i.getKey() + 1) + " : - " + i.getValue().toString());

				JPanel perpanel = new JPanel();
				perpanel.add(tlist[(int) i.getKey()]);

				pangridTakList.add(perpanel);
			}

			pangridTakList.repaint();
			panBorderTakList.repaint();
			scrollPane.repaint();
			scrollPane.revalidate();

			if (status.equals(HardCodeData.projStatus[0])) {
				panreport.add(new JLabel("Project haven't Started"));
			} else if (status.equals(HardCodeData.projStatus[1])) {
				panreport.add(pieChart());
			} else if (status.equals(HardCodeData.projStatus[2])) {
				panreport.add(lineChart());
			}

			// System.out.println(tasklist);
			DatabaseConfig.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// new
		// ProjectViewTeacher(1,"Admin/Dean",HardCodeData.usertype[1],HardCodeData.projStatus[2]);
		new ProjectViewTeacher(1, "Kanika@gmail.com", HardCodeData.usertype[2], HardCodeData.projStatus[1]);
	}
}
