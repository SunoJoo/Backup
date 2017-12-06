package ocr;

import java.io.File;
import java.io.PrintStream;

public class Web_Run {
	/**
	 * @param args
	 * Crawler는 스레드가 따로 돌기 때문에 join해줘야함
	 * 아직 join을 안해줬으므로 Crawler먼저 main에서 실행 후 주석처리하고 Detect부터 실행하면 됨
	 * 구글 API는 각자 받은 json파일과 본인 계정을 사용하기 바람(제거는 이미 많이씀..)
	 */
	String ref;
	public Web_Run(String ref) {
		this.ref = ref;
	}
	public ResultData run() {
		// TODO Auto-generated method stub
	
		
		//new Crawler(ref);		
		
		String imgPath = "/Users/kingt/git/Cap/src/main/resources/webtoon_img";
		/*ImageProcessing ip = new ImageProcessing(imgPath);
		ip.start();*/
		Detect detect = new Detect();		
		String path = imgPath+"/_4.jpg";
		System.out.println(getClass().getResource(""));
		File f = new File("/Users/kingt/git/Cap/text/lines.txt");
		PrintStream p;	
		Coordinator coordinator;
		Calculator calculator;		
		ResultData result = new ResultData();
		
		try {		
			p = new PrintStream(f);
			detect.detectText(path, p);		
				
			coordinator = new Coordinator(detect.getDes(),detect.getCoord());
			coordinator.coordinate();
			calculator = new Calculator(coordinator.getDes(),coordinator.getCoordParent());
			calculator.calResult();		
			
			
			for(int i = 0 ; i<calculator.getSentenceArray().length; i++) {
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
			}*/
				
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;	
	}
}

