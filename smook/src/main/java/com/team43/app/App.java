package com.team43.app;

import java.io.*;
import javax.swing.*;
import java.awt.EventQueue;
import com.team43.app.frontend.MainFrame;
class App {

	public static void main(String[] args)
	{
		MainFrame frame = new MainFrame(1200,800);
		frame.setVisible(true);
		// EventQueue.invokeLater(new Runnable() {
		// 	public void run() {
		// 		try {
		// 			MainFrame frame = new MainFrame(1200,800);
		// 			frame.setVisible(true);
		// 		} catch (Exception e) {
		// 			e.printStackTrace();
		// 		}
		// 	}
		// });
	}
}
