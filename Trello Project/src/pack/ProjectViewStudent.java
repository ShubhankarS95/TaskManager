package pack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import dbdetails.DatabaseConfig;

import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
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

	public ProjectViewStudent(int projectid) {
		getContentPane().setLayout(null);
		
		panelMain = new JPanel();
		panelMain.setBounds(30, 10, 775, 539);
		getContentPane().add(panelMain);
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
		
		JButton btnNewButton = new JButton("Save Project Status");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(92, 426, 209, 36);
		panelMain.add(btnNewButton);
		
		addDetailsProject(projectid);
		
		setSize(867, 609);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle(" Project Details ");
	}
	public void addDetailsProject(int pid)
	{
		try {
			DatabaseConfig.initialize();
			DatabaseConfig.rs=DatabaseConfig.stmt.executeQuery("Select * from workhandler.projectdetails where ProjectId="+pid+";");
			
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
			
			while(DatabaseConfig.rs.next())
			{
				lblprojName.setText("Project Name: "+DatabaseConfig.rs.getString(3).toUpperCase());
				System.out.println(DatabaseConfig.rs.getString(3));
				txtFStartDate.setText(sdf.format(DatabaseConfig.rs.getDate(7)));
				txtFEndDate.setText(sdf.format(DatabaseConfig.rs.getDate(8)));
				txtFTeamLeader.setText(DatabaseConfig.rs.getString(5));
				txtFTeamLeaderEmail.setText(DatabaseConfig.rs.getString(6));
				//break;
			}		
			
			DatabaseConfig.rs=DatabaseConfig.stmt.executeQuery("SELECT TaskNo , Task_Info FROM workhandler.project_tasks where Project_ID="+pid+";");
			HashMap<Integer, String> tasklist=new HashMap<>();
			
			while(DatabaseConfig.rs.next())
			{
				tasklist.put(DatabaseConfig.rs.getInt(1), DatabaseConfig.rs.getString(2));
			}
			
			JCheckBox[] tlist=new JCheckBox[tasklist.size()];
			for(Map.Entry i:tasklist.entrySet())
			{
				tlist[(int)i.getKey()]=new JCheckBox( ((int)i.getKey() + 1) +" : - "+i.getValue().toString() );
				
				JPanel perpanel= new JPanel();
				perpanel.add(tlist[(int)i.getKey()]);
				panelGridTaskList.add(perpanel);				
			}
			
			panelGridTaskList.repaint();
			panelBorder.repaint();
			scrollPane.repaint();
			scrollPane.revalidate();
			
			
			//System.out.println(tasklist);
			DatabaseConfig.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		
		new ProjectViewStudent(12);

	}
}
