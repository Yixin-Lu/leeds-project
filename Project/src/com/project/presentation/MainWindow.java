package com.project.presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.project.operationCentre.Reshuffling;

/**
 * The MainWindow of client
 * Created on 15 Jul 2020
 * Revised on 12 Aug 2020
 * @author Yixin Lu
 *
 */

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	
	JFrame frame;
	
	JPanel panel_query, panel_query_cnt;
	
	JLabel label_query_name;
	JTextField text_in;
	JTextArea text_result;
	JButton btn_Ok;
	
	JLabel label;
	JTextArea text_cnt;

	public MainWindow() {

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		
		// Main frame
		frame = new JFrame("Container Reshuffling");
		frame.setBounds(screenWidth / 4, screenHeight / 5, 600, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.lightGray);
		frame.getContentPane().setLayout(null);
		frame.setResizable(true); 

		Font font = new Font("Arial", 1, 15);
		
		panel_query = new JPanel();
		panel_query.setBounds(2, 10, 700, 400);
		panel_query.setBackground(Color.lightGray);
		panel_query.setLayout(null);

		label_query_name = new JLabel("Input the total number of stacks: ");
		label_query_name.setBounds(10, 10, 300, 40);
		label_query_name.setFont(font);
		panel_query.add(label_query_name);

		text_in = new JTextField();
		text_in.setBounds(240, 10, 70, 40);
		panel_query.add(text_in);

		text_result = new JTextArea();
		text_result.setBounds(10, 100, 550, 240);
		text_result.setFont(font);
		text_result.setEditable(true);
		panel_query.add(text_result);

		btn_Ok = new JButton("Reshuffling");
		btn_Ok.setFont(font);
		btn_Ok.setBounds(360, 10, 180, 40);
		btn_Ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reshuffling r = new Reshuffling();
				text_result.setText(r.result(new Integer(text_in.getText())));
			}
		});
		panel_query.add(btn_Ok);

		panel_query.setVisible(true);
		frame.getContentPane().add(panel_query);

		panel_query_cnt = new JPanel();
		panel_query_cnt.setBounds(2, 45, 440, 225);
		panel_query_cnt.setBackground(Color.lightGray);
		panel_query_cnt.setLayout(null);

		label = new JLabel("Result: ");
		label.setBounds(10, 60, 150, 40);
		label.setFont(font);
		panel_query.add(label);

		text_cnt = new JTextArea();
		text_cnt.setFont(new Font("Arial", 1, 30));
		text_cnt.setBounds(10, 60, 800, 140);
		panel_query_cnt.add(text_cnt);

		frame.getContentPane().add(panel_query_cnt);
		panel_query_cnt.setVisible(true);

		frame.setVisible(true); 

	}

}
