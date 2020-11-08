package pack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
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
	
	private ArrayList<String> studStarted,studProgress,studCompleted;

	public ProjectViewTeacher(int projid,String usertype) {
		getContentPane().setLayout(null);
		
		lblProjectName = new JLabel();
		lblProjectName.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjectName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProjectName.setBounds(316, 40, 439, 50);
		getContentPane().add(lblProjectName);
		
		lblStartDate = new JLabel("Start Date");
		lblStartDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStartDate.setBounds(35, 139, 106, 24);
		getContentPane().add(lblStartDate);
		
		txtfStartDate = new JTextField();
		txtfStartDate.setEditable(false);
		txtfStartDate.setBounds(134, 142, 271, 24);
		getContentPane().add(txtfStartDate);
		txtfStartDate.setColumns(10);
		
		lblEndDate = new JLabel("End Date");
		lblEndDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEndDate.setBounds(598, 139, 86, 24);
		getContentPane().add(lblEndDate);
		
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getStudName(projid);
		addDetails(projid);
	}
	public void getStudName(int projid)
	{
		studStarted=new ArrayList<>();
		studProgress=new ArrayList<>();
		studCompleted=new ArrayList<>();
		try {
			DatabaseConfig.initialize();
			DatabaseConfig.rs=DatabaseConfig.stmt.executeQuery("SELECT ps.Projstatus,s.Firstname,s.LastName,ps.projectid FROM workhandler.studprojectstatus ps, workhandler.student s "
					+ "where  ps.StudEmail=s.email  and ps.projectid="+projid+";");
			
			while(DatabaseConfig.rs.next())
			{
				if(DatabaseConfig.rs.getString(1).equals(HardCodeData.projStatus[0]))//Assigned
				{
					studStarted.add(DatabaseConfig.rs.getString(2)+" "+DatabaseConfig.rs.getString(3));
				}
				if(DatabaseConfig.rs.getString(1).equals(HardCodeData.projStatus[1]))     // in Progress
				{
					studProgress.add(DatabaseConfig.rs.getString(2)+" "+DatabaseConfig.rs.getString(3));
				}
				if(DatabaseConfig.rs.getString(1).equals(HardCodeData.projStatus[2]))      // Completed
				{
					studCompleted.add(DatabaseConfig.rs.getString(2)+" "+DatabaseConfig.rs.getString(3));
				}
				
			}		
			if(studStarted.size()==0)
			{
				studStarted.add("No Student Found");
			}
			if(studProgress.size()==0)
			{
				studProgress.add("No Student Found");
			}
			if(studCompleted.size()==0)
			{
				studCompleted.add("No Student Found");
			}
			
			System.out.println(studStarted);
			System.out.println(studProgress);
			System.out.println(studCompleted);
			
			cmbstudStart.setModel(  new DefaultComboBoxModel<String>(studStarted.toArray(new String[0])) );
			cmstudinprogress.setModel(  new DefaultComboBoxModel<String>(studProgress.toArray(new String[0]))  );
			cmbstudComplete.setModel( new DefaultComboBoxModel<String>(studCompleted.toArray(new String[0]))  );
			
			
			panreport.add(lineChart());
			
			DatabaseConfig.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public JPanel pieChart()
	{
		 DefaultPieDataset dataset = new DefaultPieDataset( );
	      dataset.setValue( "Project Assigned" ,  studStarted.size() );  
	      dataset.setValue( "Project Started or in-Progress " ,  studProgress.size() );   
	      dataset.setValue( "Project Completed" , studCompleted.size() );    
	        
		JFreeChart chart = ChartFactory.createPieChart(      
		         "Project OverView",   // chart title 
		         dataset,              // data    
		         true,                 // include legend   
		         true, 
		         false);
		
	      return new ChartPanel( chart ); 
	}
	public JPanel lineChart()
	{
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.setValue(6, "", "Alex");

        dataset.setValue(8, "", "Carmen");

        dataset.setValue(12, "", "Tony");

        JFreeChart chart = ChartFactory.createLineChart( "Promedio de calificaciones  2019-2020", "Alumnos", "Calificaciones", 
        		dataset,
        		PlotOrientation.VERTICAL,
        		false,
        		true,
        		false); 

        chart.setBackgroundPaint(new Color(0,200,0));
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinesVisible(false);
       
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setShapesVisible(true);
        renderer.setItemLabelsVisible(true);
       
        return new ChartPanel(chart);
	}
	public void addDetails(int projid)
	{
		try {
			DatabaseConfig.initialize();
			DatabaseConfig.rs=DatabaseConfig.stmt.executeQuery("Select Project_Name,course,Team_Leader,Start_Date,End_Date from workhandler.projectdetails where ProjectId="+projid+";");
			
			SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM,yyyy");
			
			while(DatabaseConfig.rs.next())
			{
				setTitle("Project Report of "+DatabaseConfig.rs.getString(1));
				lblProjectName.setText(DatabaseConfig.rs.getString(1));
				txtfStartDate.setText(  sdf.format(  DatabaseConfig.rs.getDate(4)  )   );
				txtfEndDate.setText(  sdf.format( DatabaseConfig.rs.getDate(5)  )  );
				txtfTeamLeader.setText(DatabaseConfig.rs.getString(3));
				txtfCourse.setText(DatabaseConfig.rs.getString(2));
				
			}		
			
			DatabaseConfig.rs=DatabaseConfig.stmt.executeQuery("SELECT TaskNo , Task_Info FROM workhandler.project_tasks where Project_ID="+projid+";");
			HashMap<Integer, String> tasklist=new HashMap<>();
			
			while(DatabaseConfig.rs.next())
			{
				tasklist.put(DatabaseConfig.rs.getInt(1), DatabaseConfig.rs.getString(2));
			}
			
			JLabel[] tlist=new JLabel[tasklist.size()];
			for(Map.Entry i:tasklist.entrySet())
			{
				tlist[(int)i.getKey()]=new JLabel( ((int)i.getKey() + 1) +" : - "+i.getValue().toString() );
				
				JPanel perpanel= new JPanel();
				perpanel.add(tlist[(int)i.getKey()]);
				
				pangridTakList.add(perpanel);				
			}
			
			pangridTakList.repaint();
			panBorderTakList.repaint();
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
		new ProjectViewTeacher(40,HardCodeData.usertype[1]);

	}
}
