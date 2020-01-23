package a9;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

public class ImageEditorController implements ToolChoiceListener, MouseListener, MouseMotionListener, ActionListener {

	private ImageEditorView view;
	private ImageEditorModel model;

	private Tool current_tool;
	private PixelInspectorTool inspector_tool;
	private PaintBrushTool paint_brush_tool;
	private BlurTool blur_tool;

	public ImageEditorController(ImageEditorView view, ImageEditorModel model) {
		this.view = view;
		this.model = model;

		inspector_tool = new PixelInspectorTool(model);
		paint_brush_tool = new PaintBrushTool(model);
		blur_tool = new BlurTool(model);
		inspector_tool.addObserver(paint_brush_tool);

		view.addToolChoiceListener(this);
		view.addMouseListener(this);
		view.addMouseMotionListener(this);
		view.addActionListener(this);

		this.toolChosen(view.getCurrentToolName());
	}

	@Override
	public void toolChosen(String tool_name) {
		if (tool_name.equals("Pixel Inspector")) {
			view.installToolUI(inspector_tool.getUI());
			current_tool = inspector_tool;
		} else if (tool_name.equals("Paint Brush")) {
			view.installToolUI(paint_brush_tool.getUI());
			current_tool = paint_brush_tool;
		} else if (tool_name.equals("Blur")) {
			view.installToolUI(blur_tool.getUI());
			current_tool = blur_tool;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		current_tool.mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		current_tool.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		current_tool.mouseReleased(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		current_tool.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		current_tool.mouseExited(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		current_tool.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		current_tool.mouseMoved(e);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("open")) {
			boolean invalid = true;
			while (invalid) {
				String url = JOptionPane.showInputDialog("URL of The Image");
				if (url == null) {
					break;
				}
				try {
					model.setPicture(PictureImpl.readFromURL(url));
					view.setModel(model);
					view.setPictureView();
					invalid = false;

				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "URL is invalid");

				}
			}
		} else if (command.equals("undo")) {
			model.goBack();
			view.setModel(model);
			view.setPictureView();
		}

	}

}
