package a9;

public class ImageEditorModel {

	private Picture original;
	private ObservablePicture current;
	private ObservablePicture adjusted;
	private Undo undo;

	public ImageEditorModel(Picture f) {
		original = f;
		current = original.copy().createObservable();
		adjusted = original.copy().createObservable();
		undo = new Undo(adjusted.copy().createObservable());
	}

	public ObservablePicture getCurrent() {
		return current;
	}

	public void goBack() {
		ObservablePicture p = undo.getLastPicture();

		current = p;
		adjusted = p;
	}

	public Pixel getPixel(int x, int y) {
		return current.getPixel(x, y);
	}

	public ObservablePicture getPictrue() {
		return current;
	}

	public void setPicture(Picture p) {
		original = p;
		current = original.copy().createObservable();
		adjusted = original.copy().createObservable();
	}

	public void paintAt(int x, int y, Pixel brushColor, int brush_size, int opacity) {

		ObservablePicture copy = adjusted;
		for (int xpos = x - brush_size; xpos <= x + brush_size; xpos++) {
			for (int ypos = y - brush_size; ypos <= y + brush_size; ypos++) {
				if (xpos >= 0 && xpos < current.getWidth() && ypos >= 0 && ypos < current.getHeight()) {
					Pixel current_pixel = copy.getPixel(xpos, ypos);
					double red = current_pixel.getRed() * (100 - opacity) / 100 + brushColor.getRed() * opacity / 100;
					double blue = current_pixel.getBlue() * (100 - opacity) / 100
							+ brushColor.getBlue() * opacity / 100;
					double green = current_pixel.getGreen() * (100 - opacity) / 100
							+ brushColor.getGreen() * opacity / 100;
					Pixel apply_pixel = new ColorPixel(red, green, blue);

					current.setPixel(xpos, ypos, apply_pixel);

				}
			}
		}

	}

	public void blur(int x, int y, int blur_size, int size) {

		for (int xpos = x - size; xpos <= x + size; xpos++) {
			for (int ypos = y - size; ypos <= y + size; ypos++) {
				if (xpos >= 0 && xpos < adjusted.getWidth() && ypos >= 0 && ypos < adjusted.getHeight()) {
					this.pixelBlur(xpos, ypos, blur_size);
				}
			}
		}

	}

	public void setNewPicture() {
		adjusted = current.copy().createObservable();

	}

	public void pixelBlur(int x, int y, int blur_size) {
		double red_sum = 0;
		double blue_sum = 0;
		double green_sum = 0;
		int count = 0;

		for (int i = x - blur_size; i <= x + blur_size; i++) {
			for (int j = y - blur_size; j <= y + blur_size; j++) {

				if (i >= 0 && i < adjusted.getWidth() && j >= 0 && j < adjusted.getHeight()) {
					red_sum += adjusted.getPixel(i, j).getRed();
					blue_sum += adjusted.getPixel(i, j).getBlue();
					green_sum += adjusted.getPixel(i, j).getGreen();
					count++;
				}
			}
		}
		current.setPixel(x, y, new ColorPixel(red_sum / count, green_sum / count, blue_sum / count));
	}

	public void addHistory() {
		undo.addPicture(adjusted.copy().createObservable());

	}

}
