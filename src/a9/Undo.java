package a9;

import java.util.ArrayList;
import java.util.List;

public class Undo {

	private List<ObservablePicture> historyPicture;

	public Undo(ObservablePicture p) {
		historyPicture = new ArrayList<ObservablePicture>();
		historyPicture.add(p);
	}

	public void addPicture(ObservablePicture p) {
		historyPicture.add(p);
	}

	public ObservablePicture getLastPicture() {

		historyPicture.remove(historyPicture.size() - 1);
		return historyPicture.get(historyPicture.size() - 1);
	}

	public void removePicture(ObservablePicture p) {
		historyPicture.remove(p);

	}

}
