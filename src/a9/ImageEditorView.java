package a9;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageEditorView extends JPanel {

	private JFrame main_frame;
	private PictureView frame_view;
	private ImageEditorModel model;
	private ToolChooserWidget chooser_widget;
	private JPanel options;
	private JButton open;
	private JButton undo;
	private JPanel tool_ui_panel;
	private JPanel tool_ui;

	public ImageEditorView(JFrame main_frame, ImageEditorModel model) {
		this.main_frame = main_frame;

		setLayout(new BorderLayout());

		frame_view = new PictureView(model.getCurrent());

		add(frame_view, BorderLayout.CENTER);

		options = new JPanel();
		options.setLayout(new BorderLayout());
		open = new JButton("open");
		open.setActionCommand("open");

		undo = new JButton("undo");
		undo.setActionCommand("undo");

		options.add(open, BorderLayout.WEST);
		options.add(undo, BorderLayout.EAST);
		add(options, BorderLayout.NORTH);

		tool_ui_panel = new JPanel();
		tool_ui_panel.setLayout(new BorderLayout());

		chooser_widget = new ToolChooserWidget();
		tool_ui_panel.add(chooser_widget, BorderLayout.NORTH);
		add(tool_ui_panel, BorderLayout.SOUTH);

		tool_ui = null;
	}

	public void setModel(ImageEditorModel model) {
		this.model = model;
	}

	public void setPictureView() {
		this.remove(frame_view);
		frame_view.setPicture(model.getCurrent());

		add(frame_view, BorderLayout.CENTER);
		main_frame.pack();

	}

	public void addToolChoiceListener(ToolChoiceListener l) {
		chooser_widget.addToolChoiceListener(l);
	}

	public String getCurrentToolName() {
		return chooser_widget.getCurrentToolName();
	}

	public void installToolUI(JPanel ui) {
		if (tool_ui != null) {
			tool_ui_panel.remove(tool_ui);
		}
		tool_ui = ui;
		tool_ui_panel.add(tool_ui, BorderLayout.CENTER);
		validate();
		main_frame.pack();
	}

	@Override
	public void addMouseMotionListener(MouseMotionListener l) {
		frame_view.addMouseMotionListener(l);
	}

	@Override
	public void removeMouseMotionListener(MouseMotionListener l) {
		frame_view.removeMouseMotionListener(l);
	}

	@Override
	public void addMouseListener(MouseListener l) {
		frame_view.addMouseListener(l);
	}

	public void removeMouseListener(MouseListener l) {
		frame_view.removeMouseListener(l);
	}

	public void addActionListener(ActionListener l) {
		open.addActionListener(l);
		undo.addActionListener(l);
	}

	public void removeActionListener(ActionListener l) {
		open.removeActionListener(l);
		undo.removeActionListener(l);
	}
}