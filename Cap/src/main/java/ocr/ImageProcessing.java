package ocr;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImageProcessing {
	final static int DIVIDENUM = 2;
	
	Graphics2D graphics;
	BufferedImage[] image;
	BufferedImage mergedImage;
	String resultPath;
	String imgPath;
	String[] filesName;
	int maxWidth= 0;
	int maxHeight = 0;	
	int size;
	int width;
	int height;
	ArrayList<Integer> imageNumber;
	
	ImageProcessing(String imgPath, String resultPath){
		this.imgPath = imgPath;
		this.resultPath = resultPath;
	}
	public void start() {	
		getFileName();
		/*for(int i = 0; i<filesName.length;i++) {
			System.out.println(filesName[i]);
		}*/
		image = new BufferedImage[filesName.length];	
		
		try {			
			insertBufferedImage();					
			for(int i = 0; i<image.length; i=i+DIVIDENUM) {
				if(image.length-i<DIVIDENUM) {
					switch (image.length-i) {
					case 1:
						imgSize(image[i]);
						mergedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
						graphics = (Graphics2D) mergedImage.getGraphics();
						graphics.setBackground(Color.WHITE);
						ImageIO.write(mergedImage, "jpg", new File(resultPath+"/_"+i+".jpg"));
						
					/*case 2: 
						imgSize(image[i],image[i+1]);
						mergedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
						graphics = (Graphics2D) mergedImage.getGraphics();
						graphics.setBackground(Color.WHITE);
						graphics.drawImage(image[i], 0, 0, null);
						graphics.drawImage(image[i+1], 0, image[i].getHeight(), null);
						ImageIO.write(mergedImage, "jpg", new File("C:/Users/kingt/git/Webtoon_Translator/Cap/src/main/resources/merged_img/_"+i+".jpg"));
						System.out.println(i);*///3개 이미지 합칠 때 사용
					}
				}else {
			
				imgSize(image[i],image[i+1]);
				
				mergedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				graphics = (Graphics2D) mergedImage.getGraphics();
				graphics.setBackground(Color.WHITE);
				graphics.drawImage(image[i], 0, 0, null);
				graphics.drawImage(image[i+1], 0, image[i].getHeight(), null);
				/*graphics.drawImage(image[i+2], 0, image[i].getHeight()+image[i+1].getHeight(), null);*///3개 이미지 합치기 때 사용
				if(i%DIVIDENUM==0) {
					ImageIO.write(mergedImage, "jpg", new File(resultPath+"/_"+i+".jpg"));
					maxWidth = 0;
					maxHeight = 0;
					width = 0;
					height = 0;	
					}
				}
			}			
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("이미지 입출력 오류");
		}

		System.out.println("이미지 합성이 완료되었습니다.");
	}
	
	public void getFileName() {
		File path = new File(this.imgPath);
		File[] files = path.listFiles();
		filesName = new String[files.length];
		for(int i = 0; i<filesName.length; i++) {
			if(files[i].isFile()) {
				filesName[i] = files[i].getName();			
			}
		}
		System.out.println("파일리스트를 모두 읽어들였습니다.");
	}
	
	public void insertBufferedImage() throws IOException {
		for(int i = 0; i<image.length; i++)
			 image[i] = ImageIO.read(new File(imgPath+"/_"+(i+1)+".jpg"));
	}	
	
	public void imgSize(BufferedImage img, BufferedImage img2, BufferedImage img3) {
		int maxWidthValue = maxWidthValue(img.getWidth(),img2.getWidth(),img3.getWidth());
		width = maxWidthValue;				
		maxHeight = img.getHeight()+img2.getHeight()+img3.getHeight();
		height = maxHeight;	
	}
	
	public void imgSize(BufferedImage img, BufferedImage img2) {
		int maxWidthValue = maxWidthValue(img.getWidth(),img2.getWidth());
		width = maxWidthValue;				
		maxHeight = img.getHeight()+img2.getHeight();
		height = maxHeight;	
	}
	
	public void imgSize(BufferedImage img) {
		int maxWidthValue = maxWidthValue(img.getWidth());
		width = maxWidthValue;				
		maxHeight = img.getHeight();
		height = maxHeight;	
	}
	
	public int maxWidthValue(int img, int img1, int img2) {
		
		int maxValue = 0;
		if(img>img1||img>img2) {
			maxValue = img;
		}else if(img1>img||img1>img2){
			maxValue = img1;
		}else
			maxValue = img2;
		return maxValue;
	}
	public int maxWidthValue(int img, int img1) {
			
			int maxValue = 0;
			if(img>img1) {
				maxValue = img;
			}else
				maxValue = img1;
			return maxValue;
		}
	public int maxWidthValue(int img) {
		
		int maxValue = img;
		return maxValue;
	}
}