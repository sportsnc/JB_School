package proto;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer; 
import java.util.ArrayList;

public class UI extends JFrame implements ActionListener  {
	
	public ArrayList<String> keywords = new ArrayList<String>();
	private JButton jbtOk = new JButton("Search");
	private JButton jbtCancel = new JButton("Exit");
	JTextField keyword = new JTextField(16);
	private ImageIcon logoIcon = new ImageIcon("src/image/logo.png");
	
	public UI() {
		// display first UI view //
		setTitle("Sports News Crawler");
		setSize(400,330);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		setLayout(new BorderLayout(2,0));
		
		JPanel p0 = new JPanel();
		JPanel p1 = new JPanel();
		p0.setLayout(new BorderLayout(2,0));
		p1.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 15));
		
		
		p0.add(new JLabel(" Please enter the keyword."), BorderLayout.NORTH);
		p0.add(keyword, BorderLayout.CENTER);
		
		p0.add(new JLabel(logoIcon), BorderLayout.SOUTH);
		p1.add(jbtOk);
		p1.add(jbtCancel);
		add(p0, BorderLayout.NORTH);
		add(p1, BorderLayout.SOUTH);
		jbtOk.addActionListener(this);
		jbtCancel.addActionListener(this);
		setVisible(true);
	

	}
	
public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(jbtOk)) {
			// keyword parsing //
			String input = new String(keyword.getText());
            try {
				new Output(input);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            keyword.setText("");
              
		} else if (e.getSource().equals(jbtCancel)) {
			System.exit(0);
		}
	}
}