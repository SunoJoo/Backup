package ocr;

import java.util.ArrayList;

public class Coordinator {

	private ArrayList<String> des;	
	private ArrayList<String> coordChild;
	
	ArrayList<ArrayList<Integer>> coordParent;
	
	
	Coordinator(ArrayList<String> des, ArrayList<String> coord){
		this.des = des;
		this.coordChild = coord;			
	}
	/**
	 * Detect의 리턴값에는 x, y 좌표가 있는데 해당 데이터에서 우리에게 필요없는 "x: "와 "y: " 개행문자를 지우고 처리한 결과를 ArrayList<ArrayList<Integer>>형으로 반환하기 위한 메소드
	 */
	public void coordinate() {		
		coordParent = new ArrayList<ArrayList<Integer>>();		
		ArrayList<Integer> cLChild = new ArrayList<Integer>();
		
		for(int i = 1; i<=coordChild.size();i++) {	
			String[] tempString= new String[2];
			int[] tempInt= new int[2];
			
			tempString = (coordChild.get(i-1).toString().replaceAll("x: ", "").replaceAll("y: ", "").substring(0, coordChild.get(i-1).length()-7).split("\n"));
			
			for(int j = 0; j<tempString.length; j++)
				tempInt[j] = Integer.parseInt(tempString[j]);				
			
			for(int j = 0 ; j<tempInt.length;j++) {
				cLChild.add(tempInt[j]);
				}			
			
			if(i%4==0) {				//넘겨받은 coordChild에는 0번방에 x: 1, y: 1 와 같은 형식으로 저장되어있으므로, 위에서 분리해 String배열에 저장해 cLChild에 저장함 결과적으로 CoordParent의 각 방에는 한 형태소의 네 꼭짓점의 값이 들어감
				coordParent.add(cLChild);
				cLChild = new ArrayList<Integer>();
			}
		}		
	}
	public ArrayList<ArrayList<Integer>> getCoordParent() {
		return coordParent;
	}
	public void setCoordParent(ArrayList<ArrayList<Integer>> coordParent) {
		this.coordParent = coordParent;
	}
	public ArrayList<String> getDes() {
		return des;
	}
	public void setDes(ArrayList<String> des) {
		this.des = des;
	}
}
