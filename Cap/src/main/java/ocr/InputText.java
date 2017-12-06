package ocr;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * �ٽ� ������ ��ǥ�� ��Ȯ���� ����!!!
 */

public class InputText {
	
	ResultData resultData;
	String path;
	String[] st;
	int[][] arr;
	double letterSizeH;//Width
	double letterSizeV;//Height
	public InputText(ResultData rd, String path, double h, double v){
		this.resultData = rd;
		this.path = path;
		convertTextData();
		convertPositionData();
		letterSizeH = h;
		letterSizeV = v;
	}
	
	public void convertTextData() {
		st = (String[]) resultData.getText().toArray(new String[resultData.getText().size()]);
		
	}
	
	public void convertPositionData() {
		arr = new int[resultData.getPosition().size()][resultData.getPosition().get(0).size()];
		for(int i = 0 ; i<resultData.getPosition().size(); i++) {
			for(int j = 0; j<resultData.getPosition().get(i).size();j++) {
				arr[i][j] = resultData.getPosition().get(i).get(j);
			}
		}		
	}
	
	public void start() {
		int firsttextX;
		int firsttextY;
		int firstX;
		int firstY;
		int width;
		int height;	

		
		
		for (int i = 0; i < arr.length; i++) {
			int x1 = arr[i][0];
			int x2 = arr[i][2];	
			
			firsttextX = arr[i][0];
			firsttextY = arr[i][1] + 10;
			
			firstX = arr[i][0] - 10;
			firstY = arr[i][1];
			width = arr[i][2] - arr[i][0] + 10;
			height = arr[i][5] - arr[i][3] + 10;		
					
			TextTogether tt = new TextTogether(x1, x2, st[i]);
			hj hj = new hj(tt, path);
			hj.go(arr,firsttextX,firsttextY,firstX,firstY,width,height);
		}	
	}
}

class hj {
	TextTogether tot;
	String path;
	
	hj(TextTogether tot, String path) {
		this.tot = tot;
		this.path = path;
	}

	public void go(int[][] arr,int firsttextX,int firsttextY,int firstX,int firstY,int width,int height) {
		
		
		File loadImage = new File(path+"/_2.jpg");
		File makeImage = new File(path+"/_2.jpg");
		//���� �����̸��� �޸� �ؼ� ���� �����ϰ� ������ �����ϸ� 
		//�����̱������ �Ϸ��� ������ �� �ȵǼ� �ϴ� �̸��� ���� �ؼ� �����̴� ������� ����
		//���� �˸� makeImage�κ��� �����ϱ� �ٶ�
		
		BufferedImage bi = null;
		
		/*for (int i = 0; i < arr.length; i++) {
			System.out.println("�迭 "+i+"��° "+arr[i]);
		}*/ //�׽�Ʈ
		
		try {
			bi = ImageIO.read(loadImage);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Graphics2D g1 = null;
		Graphics2D g2 = null;

		g1 = bi.createGraphics();
		g2 = bi.createGraphics();

		// text�� ������ ��Ʈ ����, �Ʒ� ��Ʈ�� �ý��ۿ� ��ġ �Ǿ� �־�� ����� �� ����
		Font font = new Font("Gungsuh", Font.BOLD, 20);

		// ��Ʈ ���� ����
		g1.setColor(Color.white); // ���
		g2.setColor(Color.DARK_GRAY); // ����

		// ��Ʈ ���� ����
		g2.setFont(font);

	
			
			g1.fillRect(firstX, firstY, width, height);
			
			tot.setOut();
			
			for (int j = 0; j < tot.getText().length; j++) {
				if(tot.getText()[j] == null){
					break;
				}
				g2.drawString(tot.getText()[j], firsttextX, firsttextY + 10 +(25*j));
			}
		/*}*/
		
		

		g1.dispose();
		g2.dispose();

		try {
			ImageIO.write(bi, "jpg", makeImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class TextTogether {
	int x1;
	int x2;
	String st;
	double alpa = 1.3;
	String[] result;

	TextTogether(int x1, int x2, String st) {
		this.x1 = x1;
		this.x2 = x2;
		this.st = st;
	}

	public double pointCalr() {
		double hap = 0;
		hap = (x2 - x1) * alpa;
		return hap;
	}
	public String[] getText() {
		return result;
	}

	public void setOut() {
		double totalLeng = Math.abs(pointCalr());
		result = new String[30];	//최종적으로 나올 값들!
		int resultCount;
		String stEX;
		int count;
		int j;
		String stArr[] = st.split(" ");
		System.out.println("이정도야"+totalLeng);
		System.out.println("들어왓어"+st);
		System.out.println("dddddd");
		stEX = "";
		count = 0;
		j = 0;
		resultCount = 0;
		
		

		for (;;) {

			if (j > stArr.length - 1)
				break;

			if (j == stArr.length - 1) {
				count = count + stArr[j].length();
			} else {
				count = count + stArr[j].length() + 1;
			}
			
			if (count > totalLeng) {
				result[resultCount] = stEX;
				System.out.println("11  "+result[resultCount]);
				stEX = "";
				count = 0;
				resultCount++;

			} else if (count == totalLeng) {
				if (j == stArr.length - 1) {
					stEX = stEX + stArr[j];
					System.out.println("22  "+result[resultCount]);
				} else {
					stEX = stEX + stArr[j] + " ";
					System.out.println("33  "+result[resultCount]);
				}
				result[resultCount] = stEX;
				stEX = "";
				count = 0;
				resultCount++;
				j++;
			}
			if (count < totalLeng) {
				if (j == stArr.length - 1) {
					stEX = stEX + stArr[j];
					result[resultCount] = stEX;
					System.out.println("44  "+result[resultCount]);
				} else {
					stEX = stEX + stArr[j] + " ";
				}
				j++;
			}
		}
	}
}