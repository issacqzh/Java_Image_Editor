package a9;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.JPanel;

public class PixelInspectorTool extends Observable implements Tool, ActionListener {

	private PixelInspectorUI ui;
	private ImageEditorModel model;
	private Pixel current_pixel;

	public PixelInspectorTool(ImageEditorModel model) {
		this.model = model;
		ui = new PixelInspectorUI(this);

		current_pixel = null;
	}

	public Pixel getCopiedPixel() {
		return current_pixel;
	}

	public void changeMagnify(int i, int j) {
		Picture magnify = new PictureImpl(51, 51);

		int x = 0;
		int y = 0;
		for (int xpos = i - 25; xpos <= i + 25; xpos++) {

			for (int ypos = j - 25; ypos <= j + 25; ypos++) {
				if (xpos >= 0 && xpos < model.getPictrue().getWidth() && ypos >= 0
						&& ypos < model.getPictrue().getHeight()) {
					magnify.setPixel(x, y, model.getPixel(xpos, ypos));
				}
				y++;
			}
			y = 0;
			x++;
		}
		ui.getMagnify().setPicture(magnify.createObservable());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		try {
			changeMagnify(e.getX(), e.getY());
			ui.setInfo(e.getX(), e.getY(), model.getPixel(e.getX(), e.getY()));
			current_pixel = model.getPixel(e.getX(), e.getY());
		} catch (Exception ex) {
			// Click may have been out of bounds. Do nothing in this case.
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

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
	public String getName() {
		return "Pixel Inspector";
	}

	@Override
	public JPanel getUI() {
		return ui;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		try {
			Picture magnify = new PictureImpl(51, 51);
			int x = 0;
			int y = 0;
			for (int xpos = e.getX() - 25; xpos <= e.getX() + 25; xpos++) {

				for (int ypos = e.getY() - 25; ypos <= e.getY() + 25; ypos++) {
					if (xpos >= 0 && xpos < model.getPictrue().getWidth() && ypos >= 0
							&& ypos < model.getPictrue().getHeight()) {
						magnify.setPixel(x, y, model.getPixel(xpos, ypos));
					}
					y++;
				}
				y = 0;
				x++;
			}
			ui.getMagnify().setPicture(magnify.createObservable());
			ui.setInfo(e.getX(), e.getY(), model.getPixel(e.getX(), e.getY()));
			current_pixel = model.getPixel(e.getX(), e.getY());
		} catch (Exception ex) {
			// Click may have been out of bounds. Do nothing in this case.
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setChanged();
		this.notifyObservers();
	}

}
