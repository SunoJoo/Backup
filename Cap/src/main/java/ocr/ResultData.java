package ocr;

import java.util.ArrayList;

public class ResultData {
	ArrayList<String> text = new ArrayList<String>();
	ArrayList<ArrayList<Integer>> position = new ArrayList<ArrayList<Integer>>();
	
	
	
	
	public ArrayList<String> getText() {
		return text;
	}
	public void setText(ArrayList<String> text) {
		this.text = text;
	}
	public ArrayList<ArrayList<Integer>> getPosition() {
		return position;
	}
	public void setPosition(ArrayList<ArrayList<Integer>> position) {
		this.position = position;
	}
	public void addText(String data) {
		this.text.add(data);
	}
}

