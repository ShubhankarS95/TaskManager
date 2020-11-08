package pack;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SetTask extends JFrame {
	

	public SetTask(int n) 
	{
		JFrame F8=new JFrame();
		F8.getContentPane().setBackground(Color.white);
		JLabel AddTask=new JLabel("Add Task");
		AddTask.setFont(new Font("SERIF",Font.BOLD,25));
		AddTask.setForeground(Color.black);
		AddTask.setBounds(190,5,130,40);
		F8.add(AddTask);
		
		JPanel Task=new JPanel(new FlowLayout());
		Task.setBounds(7,50,420,510);
		Task.setBackground(new Color(255, 255, 102));
		F8.add(Task);
		
		for(int i =1; i<=n;i++)
		{
			JLabel  l=new JLabel("Task "+i);
			Task.add(l);
			JTextField txt=new JTextField(30);
			Task.add(txt);
		}
		
		JButton Submit=new JButton("Submit");
		Submit.setBounds(150, 600, 150, 30);
		Submit.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
			F8.dispose();	
			//new Mainpg();	
			}

			
				});
		F8.add(Submit);
		
		
		F8.setLayout(null);
		F8.setSize(450,720);
		F8.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		F8.setVisible(true);
		F8.setResizable(false);
		
		
	}
}
