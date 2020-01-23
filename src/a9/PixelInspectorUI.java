package a9;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PixelInspectorUI extends JPanel {

	private JLabel x_label;
	private JLabel y_label;
	private JLabel pixel_info;
	private JButton copy;
	private PictureView magnify;

	public PixelInspectorUI(ActionListener x) {
		setLayout(new BorderLayout());
		x_label = new JLabel("X: ");
		y_label = new JLabel("Y: ");
		pixel_info = new JLabel("(r,g,b)");
		copy = new JButton("copy");

		magnify = new PictureView(new ObservablePictureImpl(51, 51));

		JPanel features = new JPanel();
		features.setLayout(new BorderLayout());
		JPanel info = new JPanel();
		info.setLayout(new GridLayout(3, 1));
		info.add(x_label);
		info.add(y_label);
		info.add(pixel_info);

		copy.addActionListener(x);

		features.add(magnify, BorderLayout.CENTER);
		features.add(copy, BorderLayout.EAST);
		add(features, BorderLayout.EAST);
		add(info, BorderLayout.CENTER);

	}

	public void setInfo(int x, int y, Pixel p) {
		x_label.setText("X: " + x);
		y_label.setText("Y: " + y);
		pixel_info.setText(String.format("(%3.2f, %3.2f, %3.2f)", p.getRed(), p.getBlue(), p.getGreen()));
	}

	public PictureView getMagnify() {
		return magnify;
	}

}
