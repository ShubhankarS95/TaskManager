package pack;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.*;

public class ProjectLogin {
	public ProjectLogin() {
		JFrame F6 = new JFrame();
		JLabel l6 = new JLabel("Project Login");
		l6.setBounds(550, 0, 750, 50);
		l6.setFont(new Font("SERIF", Font.BOLD, 40));
		l6.setForeground(Color.PINK);
		F6.add(l6);
		// panel start
		JPanel jp1 = new JPanel(null);
		jp1.setBounds(350, 100, 600, 300);
		jp1.setBackground(Color.PINK);

		JTextField j1 = new JTextField();
		j1.setFont(new Font("SERIF", Font.PLAIN, 20));
		j1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (j1.getText().equals("Project Id")) {
					j1.setText("");
					j1.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (j1.getText().isEmpty()) {
					j1.setForeground(Color.GRAY);
					j1.setText("Project Id");
				}
			}
		});
		j1.setBounds(205, 35, 200, 40);

		JTextField j2 = new JTextField();
		j2.setFont(new Font("SERIF", Font.PLAIN, 20));
		j2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (j2.getText().equals("Password")) {
					j2.setText("");
					j2.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (j2.getText().isEmpty()) {
					j2.setForeground(Color.GRAY);
					j2.setText("Password");
				}
			}
		});
		// j2.setEditable(true);
		j2.setBounds(205, 100, 200, 40);

		JButton jb1 = new JButton("Login");
		jb1.setBounds(225, 180, 150, 40);
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new Mainpg();
			}
		});

		jp1.add(j1);
		jp1.add(j2);
		jp1.add(jb1);

		F6.add(jp1);

		F6.setLayout(null);
		F6.setSize(1365, 730);
		F6.setVisible(true);
		F6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}