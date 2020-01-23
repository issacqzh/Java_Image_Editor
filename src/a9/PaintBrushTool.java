package a9;

import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class PaintBrushTool implements Tool, Observer {

	private PaintBrushToolUI ui;
	private ImageEditorModel model;
	private int brush_size;

	public PaintBrushTool(ImageEditorModel model) {
		this.model = model;
		ui = new PaintBrushToolUI();
		brush_size = ui.getBrushSize();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		brush_size = ui.getBrushSize();
		model.paintAt(e.getX(), e.getY(), ui.getBrushColor(), brush_size, ui.getOpacity());

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		model.setNewPicture();
		model.addHistory();

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {

		brush_size = ui.getBrushSize();

		model.paintAt(e.getX(), e.getY(), ui.getBrushColor(), brush_size, ui.getOpacity());

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return "Paint Brush";
	}

	@Override
	public JPanel getUI() {
		return ui;
	}

	@Override
	public void update(Observable o, Object arg) {
		PixelInspectorTool x = (PixelInspectorTool) o;
		ui.setBrushColor(x.getCopiedPixel());

	}

}
