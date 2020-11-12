package pack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import dbdetails.DatabaseConfig;
import dbdetails.HardCodeData;

import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Paint;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProjectViewStudent extends JFrame {
	private JTextField txtFStartDate;
	private JTextField txtFEndDate;
	private JPanel panelMain;
	private JLabel lblstartdate;
	private JButton btnstart;
	private JButton btnSubmit;
	private JLabel lblendDate;
	private JScrollPane scrollPane;
	private JPanel panelBorder;
	private JPanel panelGridTaskList;
	private JLabel lblteamleader;
	private JTextField txtFTeamLeader;
	private JLabel lblteamleaderemail;
	private JTextField txtFTeamLeaderEmail;
	private JLabel lblprojName;
	private JButton btnSave;
	private JCheckBox[] tlist;

	public ProjectViewStudent(int projectid, String studemail, String section) {
		getContentPane().setLayout(null);

		panelMain = new JPanel() {
			protected void paintComponent(Graphics g) {
				if (g instanceof Graphics2D) {
					Paint p = new GradientPaint(0, 0, new Color(153, 235, 255), getWidth(), getHeight(),
							new Color(179, 0, 179), true);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setPaint(p);
					g2d.fillRect(0, 0, getWidth(), getHeight());
				} else {
					super.paintComponent(g);
				}
			}
		};
		panelMain.setBounds(30, 10, 775, 539);
		setContentPane(panelMain);
		panelMain.setLayout(null);

		lblstartdate = new JLabel("Start Date");
		lblstartdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblstartdate.setBounds(43, 100, 113, 25);
		lblstartdate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelMain.add(lblstartdate);

		txtFStartDate = new JTextField();
		txtFStartDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtFStartDate.setBounds(183, 100, 190, 29);
		txtFStartDate.setEditable(false);
		txtFStartDate.setColumns(10);
		panelMain.add(txtFStartDate);

		lblendDate = new JLabel("End Date");
		lblendDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblendDate.setBounds(426, 100, 95, 25);
		lblendDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelMain.add(lblendDate);

		txtFEndDate = new JTextField();
		txtFEndDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtFEndDate.setBounds(531, 101, 190, 31);
		txtFEndDate.setEditable(false);
		txtFEndDate.setColumns(10);
		panelMain.add(txtFEndDate);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(67, 220, 554, 181);
		panelMain.add(scrollPane);

		panelBorder = new JPanel();
		scrollPane.setViewportView(panelBorder);
		panelBorder.setLayout(new BorderLayout(0, 0));

		panelGridTaskList = new JPanel();
		panelBorder.add(panelGridTaskList, BorderLayout.NORTH);
		panelGridTaskList.setLayout(new GridLayout(0, 1));

		btnSubmit = new JButton("Submit Project");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblprojName.setText("Hello Shubhankar");
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSubmit.setBounds(360, 429, 213, 36);
		btnSubmit.setVisible(false);
		panelMain.add(btnSubmit);

		btnstart = new JButton("Start Project");
		btnstart.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnstart.setBounds(259, 476, 154, 31);
		btnstart.setVisible(false);
		panelMain.add(btnstart);

		lblteamleader = new JLabel("Team Leader");
		lblteamleader.setHorizontalAlignment(SwingConstants.CENTER);
		lblteamleader.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblteamleader.setBounds(10, 157, 146, 25);
		panelMain.add(lblteamleader);

		txtFTeamLeader = new JTextField();
		txtFTeamLeader.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtFTeamLeader.setEditable(false);
		txtFTeamLeader.setColumns(10);
		txtFTeamLeader.setBounds(183, 157, 190, 29);
		panelMain.add(txtFTeamLeader);

		lblteamleaderemail = new JLabel("Team Leader Email");
		lblteamleaderemail.setHorizontalAlignment(SwingConstants.CENTER);
		lblteamleaderemail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblteamleaderemail.setBounds(383, 157, 190, 25);
		panelMain.add(lblteamleaderemail);

		txtFTeamLeaderEmail = new JTextField();
		txtFTeamLeaderEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtFTeamLeaderEmail.setEditable(false);
		txtFTeamLeaderEmail.setColumns(10);
		txtFTeamLeaderEmail.setBounds(575, 159, 190, 29);
		panelMain.add(txtFTeamLeaderEmail);

		lblprojName = new JLabel("");
		lblprojName.setHorizontalAlignment(SwingConstants.CENTER);
		lblprojName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblprojName.setBounds(205, 10, 337, 59);
		panelMain.add(lblprojName);

		btnSave = new JButton("Save Project Status");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSave.setBounds(92, 426, 209, 36);
		btnSave.setVisible(false);
		panelMain.add(btnSave);

		addDetailsProject(projectid, studemail, section);

		setSize(867, 609);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle(" Project Details for " + getUserName(studemail));

		System.out.println("Project Student");
	}

	private String getUserName(String useremail) {
		String username = "";

		try {
			DatabaseConfig.initialize();

			DatabaseConfig.rs = DatabaseConfig.stmt.executeQuery(
					"select Firstname,Lastname FROM workhandler.student where email='" + useremail + "';");

			while (DatabaseConfig.rs.next()) {
				username = DatabaseConfig.rs.getString(1) + " " + DatabaseConfig.rs.getString(2);
			}
			DatabaseConfig.con.close();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return username;
	}

	public void addDetailsProject(int pid, String useremail, String projstatus) {
		try {
			DatabaseConfig.initialize();
			DatabaseConfig.rs = DatabaseConfig.stmt.executeQuery(
					"Select p.Project_Name,ps.ProjStatus,p.Team_leader,p.TeamLeaderEmail,p.start_date,p.end_date "
							+ "FROM workhandler.projectdetails p,workhandler.studprojectstatus ps "
							+ "where p.ProjectId=ps.ProjectId and ps.StudEmail='" + useremail + "' and ps.projectId="
							+ pid + ";");
			String status = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM,yyyy");

			while (DatabaseConfig.rs.next()) {
				lblprojName.setText("Project Name: " + DatabaseConfig.rs.getString(1).toUpperCase());
				// System.out.println(DatabaseConfig.rs.getString(3));
				txtFStartDate.setText(sdf.format(DatabaseConfig.rs.getDate(5)));
				txtFEndDate.setText(sdf.format(DatabaseConfig.rs.getDate(6)));
				txtFTeamLeader.setText(DatabaseConfig.rs.getString(3));
				txtFTeamLeaderEmail.setText(DatabaseConfig.rs.getString(4));
				status = DatabaseConfig.rs.getString(2);
				// break;
			}

			DatabaseConfig.rs = DatabaseConfig.stmt.executeQuery(
					"SELECT TaskNo , Task_Info FROM workhandler.project_tasks where Project_ID=" + pid + ";");
			HashMap<Integer, String> tasklist = new HashMap<>();

			while (DatabaseConfig.rs.next()) {
				tasklist.put(DatabaseConfig.rs.getInt(1), DatabaseConfig.rs.getString(2));
			}

			tlist = new JCheckBox[tasklist.size()];
			for (Map.Entry i : tasklist.entrySet()) {
				tlist[(int) i.getKey()] = new JCheckBox(((int) i.getKey() + 1) + " : - " + i.getValue().toString());
				if (status.equals(HardCodeData.projStatus[0]) || status.equals(HardCodeData.projStatus[2])) {
					tlist[(int) i.getKey()].setEnabled(false);
				}

				JPanel perpanel = new JPanel();
				perpanel.add(tlist[(int) i.getKey()]);
				panelGridTaskList.add(perpanel);
			}
			if (status.equals(HardCodeData.projStatus[0])) {
				btnstart.setVisible(true);

				DatabaseConfig.ps = DatabaseConfig.con.prepareStatement(
						"Insert into workhandler.studprojecttaskstatus (status,studemail,projid,TaskNo) values(?,?,?,?);");
				for (Map.Entry i : tasklist.entrySet()) {
					DatabaseConfig.ps.setInt(1, 0); // for student setting Task status as unchecked
					DatabaseConfig.ps.setString(2, useremail);
					DatabaseConfig.ps.setInt(3, pid);
					DatabaseConfig.ps.setInt(4, (int) i.getKey());
					DatabaseConfig.ps.executeUpdate();
				}

			} else {
				DatabaseConfig.rs = DatabaseConfig.stmt.executeQuery(
						"Select status,studemail,projid,TaskNo into workhandler.studprojecttaskstatus where studemail='"
								+ useremail + "'; ");
				for (Map.Entry i : tasklist.entrySet()) {
					DatabaseConfig.ps.setInt(1, 0); // for student setting Task status as unchecked
					DatabaseConfig.ps.setString(2, useremail);
					DatabaseConfig.ps.setInt(3, pid);
					DatabaseConfig.ps.setInt(4, (int) i.getKey());
					DatabaseConfig.ps.executeUpdate();
				}
			}

			if (status.equals(HardCodeData.projStatus[1])) {
				btnSave.setVisible(true);
				btnSubmit.setVisible(true);
			} else if (status.equals(HardCodeData.projStatus[2])) {
				btnSave.setVisible(true);
				btnSave.setEnabled(false);
				btnSubmit.setVisible(true);
				btnSubmit.setEnabled(false);
			}

			panelGridTaskList.repaint();
			panelBorder.repaint();
			scrollPane.repaint();
			scrollPane.revalidate();

			// System.out.println(tasklist);
			DatabaseConfig.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		new ProjectViewStudent(48, "Shubhankar@xyz.com", HardCodeData.projStatus[0]);

	}
}
