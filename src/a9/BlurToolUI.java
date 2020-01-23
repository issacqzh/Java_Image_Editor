package a9;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class BlurToolUI extends JPanel {
	private JSlider size_slider;
	private JSlider blur_size_slider;

	public BlurToolUI() {
		setLayout(new GridLayout(0, 1));
		JPanel size_panel = new JPanel();
		JLabel size_label = new JLabel("Size: ");
		size_slider = new JSlider(0, 20);
		size_slider.setPaintTicks(true);
		size_slider.setMajorTickSpacing(5);
		size_slider.setMinorTickSpacing(1);
		size_slider.setPaintLabels(true);
		size_panel.setLayout(new BorderLayout());
		size_panel.add(size_slider, BorderLayout.CENTER);
		size_panel.add(size_label, BorderLayout.WEST);

		JPanel blur_size_panel = new JPanel();
		JLabel blur_size_label = new JLabel("Blur size:");
		blur_size_slider = new JSlider(0, 5);
		blur_size_slider.setPaintTicks(true);
		blur_size_slider.setMajorTickSpacing(1);
		blur_size_slider.setPaintLabels(true);

		blur_size_panel.setLayout(new BorderLayout());
		blur_size_panel.add(blur_size_slider, BorderLayout.CENTER);
		blur_size_panel.add(blur_size_label, BorderLayout.WEST);

		Dimension d = size_label.getPreferredSize();
		blur_size_slider.setPreferredSize(d);

		add(blur_size_panel);
		add(size_panel);

	}

	public int getPaintSize() {

		return size_slider.getValue();
	}

	public int getBlurSize() {
		return blur_size_slider.getValue();
	}

}
