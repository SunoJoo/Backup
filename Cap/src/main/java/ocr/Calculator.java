package ocr;

import java.util.ArrayList;



public class Calculator {
	final static int BUFFERSIZE = 2;
	private ArrayList<String> des;
	private ArrayList<ArrayList<Integer>> coordParent;
	private ArrayList<ArrayList<Integer>> sentenceIndex;
	private ArrayList<Integer> sentenceLocation;
	private ArrayList<String> desPack;
	private String[] sentenceArray;
	private int sentenceNum = 0;
	private int[] leftTop = new int[2];
	private int[] rightTop = new int[2];
	private int[] leftBottom = new int[2];
	private int[] rightBottom = new int[2];
	private boolean rightTopSet =false;
	private boolean leftTopSet =false;
	private boolean leftBottomSet =false;
	private boolean rightBottomSet =false;
	private double oneLetterV = 0;
	private double oneLetterH = 0;
	
		Calculator(ArrayList<String> des, ArrayList<ArrayList<Integer>> coordParent){
			this.des = des;
			this.coordParent = coordParent;
			sentenceNum = initSentenceSize();
		}	
		
	

		public double getOneLetterV() {
			return oneLetterV;
		}



		public void setOneLetterV(double oneLetterV) {
			this.oneLetterV = oneLetterV;
		}



		public double getOneLetterH() {
			return oneLetterH;
		}



		public void setOneLetterH(double oneLetterH) {
			this.oneLetterH = oneLetterH;
		}



		/**
		 * @param coordParent
		 * @return
		 * 왼쪽 상단 좌표를 얻기 위함
		 */
		public int[] returnLeftTopArray(ArrayList<Integer> coordParent) {
			int[] leftTop = new int[2];
			for(int i = 0; i<leftTop.length; i++)	
				leftTop[i] = coordParent.get(i);			
			return leftTop;
			}
		/**
		 * @param coordParent
		 * @return
		 * 좌측 하단 좌표를 얻기 위함
		 */
		public int[] returnLeftBottomArray(ArrayList<Integer> coordParent) {
			int[] leftButtom = new int[2];
			for(int i = 0; i<leftButtom.length; i++)	
				leftButtom[i] = coordParent.get(i+6);			
			return leftButtom;
			}
		/**
		 * @param coordParent
		 * @return
		 * 우측 상단 좌표를 얻기 위함
		 */
		public int[] returnRightTopArray(ArrayList<Integer> coordParent) {
			int[] rightTop = new int[2];
			for(int i = 0; i<rightTop.length; i++)	
				rightTop[i] = coordParent.get(i+2);			
			return rightTop;
			}
		/**
		 * @param coordParent
		 * @return
		 * 우측 하단 좌표를 얻기 위함
		 */
		public int[] returnRightBottomArray(ArrayList<Integer> coordParent) {
			int[] rightButtom = new int[2];
			for(int i = 0; i<rightButtom.length; i++)		
				rightButtom[i] = coordParent.get(i+4);			
			return rightButtom;			
		}
				
		/**
		 * @param coordParentCurrent
		 * @param coordParentNext
		 * @--- X축의 평균을 구함
		 * @return
		 */
		public int[] returnLeftTopBottom_MiddleArray(ArrayList<Integer> coordParentCurrent) {
			int[] leftTop = returnLeftTopArray(coordParentCurrent);
			int[] leftButtom = returnLeftBottomArray(coordParentCurrent);			
			int[] leftTopButtom_Middle = new int[2];
			for(int i = 0; i<leftTopButtom_Middle.length; i++) 	
				leftTopButtom_Middle[i] = Math.abs((leftTop[i]+leftButtom[i])/2);				
			return leftTopButtom_Middle;
			}
		
		/**
		 * @param coordParentCurrent
		 * @param coordParentNext
		 * @--- Y축의 평균을 구함
		 * @return
		 */
		public int[] returnLeftTopRightTop_MiddleArray(ArrayList<Integer> coordParentCurrent) {
			int[] leftTop = returnLeftTopArray(coordParentCurrent);
			int[] rightTop = returnRightTopArray(coordParentCurrent);
			int[] leftTopRightTop_Middle = new int[2];
			for(int i = 0; i<leftTopRightTop_Middle.length; i++)	
				leftTopRightTop_Middle[i] = Math.abs((leftTop[i]+rightTop[i])/2);			
			return leftTopRightTop_Middle;
			}
		
		/**
		 * @param coordParentCurrent
		 * @param coordParentNext
		 * @--- X축의 평균을 구함
		 * @return
		 */
		
		public int[] returnRightTopBottom_MiddleArray(ArrayList<Integer> coordParentCurrent) {
			int[] rightTop = returnRightTopArray(coordParentCurrent);
			int[] rightButtom = returnRightBottomArray(coordParentCurrent);
			int[] rightTopButtom_Middle = new int[2];
			for(int i = 0; i<rightTopButtom_Middle.length; i++)	
				rightTopButtom_Middle[i] = Math.abs((rightTop[i]+rightButtom[i])/2);			
			return rightTopButtom_Middle;
			}
		/**
		 * @param coordParentCurrent
		 * @param coordParentNext
		 * @--- Y축의 평균을 구함
		 * @return
		 */
		public int[] returnLeftBottomRightBottom_MiddleArray(ArrayList<Integer> coordParentCurrent) {
			int[] leftButtom = returnLeftBottomArray(coordParentCurrent);
			int[] rightButtom = returnRightBottomArray(coordParentCurrent);
			int[] leftButtomRightButton_Middle = new int[2];
			for(int i = 0; i<leftButtomRightButton_Middle.length; i++)	
				leftButtomRightButton_Middle[i] = Math.abs((leftButtom[i]+rightButtom[i])/2);			
			return leftButtomRightButton_Middle;
			}	
		
		public int setVerticalLetterLen(ArrayList<Integer> data) {
			int y1 = returnRightTopArray(data)[1];
			int y2 = returnRightBottomArray(data)[1];	
			int result = y2-y1;			
			return result;
		}
		public double setLineSpaceSize() {
			double result;
			double verticalLen = oneLetterV;
			result = verticalLen;		
			return result;
		}
		/**
		 * @param coordParent
		 * @return
		 * X축의 형태소와의 거리 판단
		 * 
		 */
		public boolean checkRow(ArrayList<Integer> coordParent, ArrayList<Integer> coordParentGet) {		
			int x1Data = returnRightTopBottom_MiddleArray(coordParent)[0];
			int x2Data = returnLeftTopBottom_MiddleArray(coordParentGet)[0];				
			
			if(Math.abs((x1Data-x2Data))<oneLetterH*0.9)			
				return true;
			else
				return false;
		}
		/**
		 * @param coordParent
		 * @return
		 * Y축의 형태소와 거리 판단
		 * 
		 */
		public boolean checkColumn(ArrayList<Integer> coordParent, ArrayList<Integer> coordParentGet) {
				
			int y1Data = returnLeftBottomRightBottom_MiddleArray(coordParent)[1];
			int y2Data = returnLeftTopRightTop_MiddleArray(coordParentGet)[1];	
			
			if(Math.abs((y1Data-y2Data)) < oneLetterV*1.2 )
				return true;
			else				
				return false;
		}
		/**
		 * @param coordParent
		 * @return
		 * Sentence와 Sentence위치를 저장할 공간의 크기 구함
		 * 
		 */
		public int initSentenceSize() {
			int sentenceNum = 0;
			for(int i = 2; i<coordParent.size(); i++) {
				if(i==2&&!coordParent.isEmpty()) {
					oneLetterV = setVerticalLetterLen(coordParent.get(1));					
					oneLetterH = setLineSpaceSize();
					sentenceNum = sentenceNum+1;
				}
				
				if(!checkRow(coordParent.get(i-1), coordParent.get(i))&&!checkColumn(coordParent.get(i-1), coordParent.get(i))) {
					sentenceNum = sentenceNum + 1;
					}	
				if(checkRow(coordParent.get(i-1), coordParent.get(i))&&!checkColumn(coordParent.get(i-1), coordParent.get(i))) {
					sentenceNum = sentenceNum + 1;
					}	
			
			}		
			return sentenceNum;
		}
		
		public int getAvgX(ArrayList<Integer> xData) {
			int sum = 0;
			for(int i = 0; i<xData.size();i++) {
				sum += xData.get(i);
			}
			return sum/xData.size();
		}
		
		public int getAvgY(ArrayList<Integer> yData) {
			int sum = 0;
			for(int i = 0; i<yData.size();i++) {
				sum += yData.get(i);
			}
			return sum/yData.size();
			
		}
		
		public void putDataX(ArrayList<Integer> data, ArrayList<Integer> position) {
			data.add(position.get(0));
			data.add(position.get(2));
			data.add(position.get(4));
			data.add(position.get(6));
		}
		public void putDataY(ArrayList<Integer> data, ArrayList<Integer> position) {
			data.add(position.get(1));
			data.add(position.get(3));
			data.add(position.get(5));
			data.add(position.get(7));
		}
		
		public void setSentenceLocation() {
			sentenceLocation.add(leftTop[0]);
			sentenceLocation.add(leftTop[1]);
			sentenceLocation.add(rightTop[0]);
			sentenceLocation.add(rightTop[1]);
			sentenceLocation.add(rightBottom[0]);
			sentenceLocation.add(rightBottom[1]);
			sentenceLocation.add(leftBottom[0]);
			sentenceLocation.add(leftBottom[1]);
		}
		
		public void initTopBottom(ArrayList<Integer> data) {
			leftTop[0] = data.get(0);
			leftTop[1] = data.get(1);
			rightTop[0] = data.get(2);
			rightTop[0] = data.get(3);
			rightBottom[0] = data.get(4);
			rightBottom[0] = data.get(5);
			leftBottom[0] = data.get(6);
			leftBottom[0] = data.get(7);
		}
		/**
		 * @param coordParent
		 * @return
		 * 실제 사용할 Sentence(Des)와 Sentence의 좌표 (SentenceIndex)를 구하는 메소드
		 * 
		 */
		public void calResult() {
			/*ArrayList<Integer> xValues = new ArrayList<Integer>();
			ArrayList<Integer> yValues = new ArrayList<Integer>();
			ArrayList<ArrayList<Integer>> buffer = new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> xAvgValues = new ArrayList<Integer>();
			ArrayList<Integer> yAvgValues = new ArrayList<Integer>();
			StringBuffer sentenceLine = new StringBuffer();				
			for(int i = 1; i<coordParent.size(); i++) {
				if(i==1) {
					initTopBottom(coordParent.get(i));
					setSentenceLocation();
				}
				if(checkColumn(coordParent.get(i), coordParent.get(i+1))) {
					sentenceLine.append(coordParent.get(i));
					putDataX(xValues, coordParent.get(i));
					putDataY(yValues, coordParent.get(i));
					checkLeftTop(i, coordParent);
					checkLeftBottom(i, coordParent);
					checkRightTop(i, coordParent);
					checkRightBottom(i, coordParent);	
					
				
				}else if(buffer.size()<=BUFFERSIZE||i==coordParent.size()-1){
					xAvgValues.add(getAvgX(xValues));
					yAvgValues.add(getAvgY(yValues));
					buffer.add(xValues);
					
					if(buffer.size()==BUFFERSIZE||i==coordParent.size()-1) {
						if((xAvgValues.get(0)-xAvgValues.get(1))<oneLetterH*3&&(yAvgValues.get(0)-yAvgValues.get(1))<oneLetterV*1.5) {
							modiSentenceLocation();
						}
						else if(!((xAvgValues.get(0)-xAvgValues.get(1))<oneLetterH*3)||!((yAvgValues.get(0)-yAvgValues.get(1))<oneLetterV*1.5)){
							desPack.add(sentenceLine.toString());
							sentenceIndex.add(sentenceLocation);
							setDeaultBooleanSet();
							sentenceLine.delete(0, sentenceLine.length());							
							setDefaultSentenceLocation();
							buffer.clear();
						}
					}
				}			
				
			}*/
			StringBuffer sentence = new StringBuffer();			
			int num=0;		
		
			sentenceArray = new String[sentenceNum];
			sentenceIndex = new ArrayList<ArrayList<Integer>>();
			sentenceLocation = new ArrayList<Integer>();	
			
			for(int i = 2; i<coordParent.size(); i++) {								
				if(i==2)	{
					sentence.append(des.get(1));							
				}
				
				if(!checkRow(coordParent.get(i-1), coordParent.get(i))&&!checkColumn(coordParent.get(i-1), coordParent.get(i))) {					
					sentenceArray[num] = sentence.toString()+"!!";
					insertSentenceLocation();				
					intsertSentenceIndex(num);
					setDeaultBooleanSet();
					num++;
					sentence.delete(0, sentence.length());
					sentence.append(des.get(i));					
					setDefaultSentenceLocation();					
				}	
				else if(checkRow(coordParent.get(i-1), coordParent.get(i))&&!checkColumn(coordParent.get(i-1), coordParent.get(i))) {
					sentenceArray[num] = sentence.toString()+"o!";
					insertSentenceLocation();				
					intsertSentenceIndex(num);
					setDeaultBooleanSet();
					num++;
					sentence.delete(0, sentence.length());
					sentence.append(des.get(i));						
					setDefaultSentenceLocation();					
				}								
								
				else if(!checkRow(coordParent.get(i-1), coordParent.get(i))&&checkColumn(coordParent.get(i-1), coordParent.get(i))) {					
					sentence.append(des.get(i));					
					checkLeftTop(i-1, coordParent);
					checkLeftBottom(i-1, coordParent);
					checkRightTop(i, coordParent);
					checkRightBottom(i, coordParent);							
				}	
				
				else if(checkRow(coordParent.get(i-1), coordParent.get(i))||checkColumn(coordParent.get(i-1), coordParent.get(i))){
					sentence.append(des.get(i));					
					checkLeftTop(i-1, coordParent);
					checkLeftBottom(i-1, coordParent);
					checkRightTop(i, coordParent);
					checkRightBottom(i, coordParent);												
				}	
				
					
				if(i==coordParent.size()-1) {
					sentenceArray[num] = sentence.toString();
					insertSentenceLocation();
					intsertSentenceIndex(num);
				}
			}			
		}
		
		public ArrayList<String> getDesPack() {
			return desPack;
		}



		public void setDesPack(ArrayList<String> desPack) {
			this.desPack = desPack;
		}



		public void checkLeftTop(int i, ArrayList<ArrayList<Integer>> coord) {
			int x = returnLeftTopArray(coord.get(i))[0];
			int y = returnLeftTopArray(coord.get(i))[1];
				if(!leftTopSet)	{			
					leftTop[0] = x;		
					leftTop[1] = y;	
					leftTopSet = true;
					}
				if(leftTopSet)
					if(leftTop[0]>x) 
						leftTop[0] = x;
					if(leftTop[1]>y)
						leftTop[1] = y;
				}

		public void checkRightTop(int i, ArrayList<ArrayList<Integer>> coord) {	
			int x = returnRightTopArray(coord.get(i))[0];
			int y = returnRightTopArray(coord.get(i))[1];
	 
			if(!rightTopSet)	{			
					rightTop[0] = x;		
					rightTop[1] = y;	
					rightTopSet = true;
			}			
			if(rightTopSet) {			
				if(rightTop[0]<x) 
					rightTop[0] = x;				
				if(rightTop[1]>y) 
					rightTop[1] = y;	
			}			
		}	
		
		public void checkRightBottom(int i, ArrayList<ArrayList<Integer>> coord) {
			int x = returnRightBottomArray(coord.get(i))[0];
			int y = returnRightBottomArray(coord.get(i))[1];
	
			if(!rightBottomSet)	{			
				rightBottom[0] = x;		
				rightBottom[1] = y;	
				rightBottomSet = true;
				}
			if(rightBottomSet)
				if(rightBottom[0]<x) 
					rightBottom[0] =x;			
				if(rightBottom[1]<y) 
					rightBottom[1] =y;		
		}
		
		public void checkLeftBottom(int i, ArrayList<ArrayList<Integer>> coord) {
			int x = returnLeftBottomArray(coord.get(i))[0];
			int y = returnLeftBottomArray(coord.get(i))[1];
	
			if(!leftBottomSet)	{			
				leftBottom[0] = x;		
				leftBottom[1] = y;	
				leftBottomSet = true;
				}
			if(leftBottomSet) {
				if(leftBottom[0]>x) 
					leftBottom[0] =x;			
				if(leftBottom[1]<y) 
					leftBottom[1] =y;	
			}
		}	
		
		public void insertSentenceLocation() {
			for(int i = 0; i<leftTop.length;i++) 
				sentenceLocation.add(leftTop[i]);				
			
			for(int i = 0; i<rightTop.length;i++)
				sentenceLocation.add(rightTop[i]);
			
			for(int i = 0; i<rightBottom.length;i++)
				sentenceLocation.add(rightBottom[i]);
			
			for(int i = 0; i<leftBottom.length;i++)
				sentenceLocation.add(leftBottom[i]);			
		}
		
		public void modiSentenceLocation() {
			for(int i = 0; i<leftTop.length;i++) 
				sentenceLocation.set(i,leftTop[i]);				
			
			for(int i = 0; i<rightTop.length;i++)
				sentenceLocation.set(i+2,rightTop[i]);
			
			for(int i = 0; i<rightBottom.length;i++)
				sentenceLocation.set(i+4,rightBottom[i]);
			
			for(int i = 0; i<leftBottom.length;i++)
				sentenceLocation.set(i+6,leftBottom[i]);			
		}
		
		public void intsertSentenceIndex(int num) {
			sentenceIndex.add(sentenceLocation);
		}
		
		public void setDeaultBooleanSet() {
			rightTopSet =false;
			leftTopSet =false;
			leftBottomSet =false;
			rightBottomSet =false;
		}
		public void setDefaultSentenceLocation() {			
				//sentenceLocation.clear();
				sentenceLocation = new ArrayList<Integer>();
		}
		
		public void setDefaultSentenceIndex() {
				sentenceIndex.clear();
		}		
	
		public ArrayList<ArrayList<Integer>> getSentenceIndex() {
			return sentenceIndex;
		}

		public void setSentenceIndex(ArrayList<ArrayList<Integer>> sentenceIndex) {
			this.sentenceIndex = sentenceIndex;
		}

		public String[] getSentenceArray() {
			return sentenceArray;
		}

		public void setSentenceArray(String[] sentenceArray) {
			this.sentenceArray = sentenceArray;
		}		
}


