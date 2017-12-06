package ocr;

import java.io.File;
import java.io.PrintStream;

public class Run {	
	public static void main(String[] args) {
		/**
		 * @param args
		 * Crawler�뒗 �뒪�젅�뱶媛� �뵲濡� �룎湲� �븣臾몄뿉 join�빐以섏빞�븿
		 * �븘吏� join�쓣 �븞�빐以ъ쑝誘�濡� Crawler癒쇱� main�뿉�꽌 �떎�뻾 �썑 二쇱꽍泥섎━�븯怨� Detect遺��꽣 �떎�뻾�븯硫� �맖
		 * 援ш� API�뒗 媛곸옄 諛쏆� json�뙆�씪怨� 蹂몄씤 怨꾩젙�쓣 �궗�슜�븯湲� 諛붾엺(�젣嫄곕뒗 �씠誘� 留롮씠��..)
		 */		
			// TODO Auto-generated method stub

				String imgPath = "C:/STS_Project/Cap/src/main/resources/webtoon_img";
				String imgPathData = "C:/STS_Project/Cap/src/main/resources/merged_img";
				String linesPath = "‪line.txt";			
				
				Detect detect = new Detect();		
				String path = imgPath+"/_2.jpg";
				
				File f = new File(linesPath);
				PrintStream p;	
				Coordinator coordinator;
				Calculator calculator;		
				ResultData result = new ResultData();
				
				new Crawler("http://comic.naver.com/webtoon/detail.nhn?titleId=25455&no=488&weekday=tue",imgPath);
				ImageProcessing ip = new ImageProcessing(imgPath,imgPathData);
				ip.start();
				
				try {		
					p = new PrintStream(f);
					detect.detectText(path, p);		
						
					coordinator = new Coordinator(detect.getDes(),detect.getCoord());
					coordinator.coordinate();
					calculator = new Calculator(coordinator.getDes(),coordinator.getCoordParent());
					calculator.calResult();		
					
					
					for(int i = 0 ; i<calculator.getSentenceArray().length;i++) {
						System.out.println(calculator.getSentenceArray()[i]);
					}
					
					System.out.println(calculator.getSentenceIndex());
					/*for(int i = 0; i<calculator.getSentenceArray().length;i++) {				
						Trans translator = new Trans();
						result.addText(translator.translator(calculator.getSentenceArray()[i]));				
					}
					result.setPosition(calculator.getSentenceIndex());		

					for(int i = 0; i<result.getText().size();i++) {
						System.out.println(result.getText().get(i));
						System.out.println(result.getPosition().get(i));
					}
					if(!result.getText().isEmpty()) {
						InputText it = new InputText(result,imgPath,calculator.getOneLetterH(), calculator.getOneLetterV());
						it.start();
					}else {
						System.out.println("이미지가 없습니다.");
					}*/
					
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
