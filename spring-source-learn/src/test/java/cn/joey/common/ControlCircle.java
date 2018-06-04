package cn.joey.common;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlCircle extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JButton jbtEnlarge = new JButton("Enlarge");
	private JButton jbtShrink = new JButton("Shrink");
	private CirclePanel canvas = new CirclePanel();

	public ControlCircle() {
		JPanel panel = new JPanel();
		panel.add(jbtEnlarge);
		panel.add(jbtShrink);
		this.add(canvas, BorderLayout.CENTER);
		this.add(panel, BorderLayout.SOUTH);
		jbtEnlarge.setActionCommand("woqu");
		jbtEnlarge.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				System.out.println(actionEvent.getActionCommand());
				canvas.enlarge();
			}
		});

	}

	public static void main(String[] args) {
		JFrame frame = new ControlCircle();
		frame.setTitle("ControlCircle");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 200);
		frame.setVisible(true);
	}

	class CirclePanel extends JPanel {
		private int radius = 5;

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawOval(getWidth() / 2 - radius, getHeight() / 2 - radius,
					2 * radius, 2 * radius);
		}

		public void enlarge() {
			radius++;
			repaint();
		}

		public void ensmall() {
			radius--;
			repaint();
		}
	}
}
