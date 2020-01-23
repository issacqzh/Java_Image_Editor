package a9;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class BlurTool implements Tool {
	private BlurToolUI ui;
	private ImageEditorModel model;
	private int blur_size;
	private int size;

	public BlurTool(ImageEditorModel model) {
		this.model = model;
		ui = new BlurToolUI();
		blur_size = ui.getBlurSize();
		size = ui.getPaintSize();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

		blur_size = ui.getBlurSize();
		size = ui.getPaintSize();
		model.blur(e.getX(), e.getY(), blur_size, size);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
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

		blur_size = ui.getBlurSize();
		size = ui.getPaintSize();

		model.blur(e.getX(), e.getY(), blur_size, size);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {

		return "Blur";
	}

	@Override
	public JPanel getUI() {
		// TODO Auto-generated method stub
		return ui;
	}

}
