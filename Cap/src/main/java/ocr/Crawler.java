package ocr;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	static int i = 0; //나중에 이미지 파일갯수가 됨 for문을 이용해서 이미지-텍스트OCR(147번 라인)
	
	private String saveDir = "H:/STS_Project/Cap/src/main/resources/webtoon_img"; //저장 디렉터리 경로(동일한 주소로 통일하기 위해서)
	
	//이부분이 필터가 필요////////직접 사이트에서 확인해야되는 불편함///	
	////////////////////////////////////////////////
	
	
	public Crawler(String ref, String path){
		/*
		 * 이 부분도 수정을 희망함 그래서 일단 생성자로 빼놓음
		 * (내가 보고 싶은 웹툰이 나오게 하고 싶다 -> 고유번호를 디비에 저장)
		 * toonName, Num 삭제 
		 */	
		saveDir = path;
		parse(ref);
		
		//parse("http://comic.naver.com/webtoon/detail.nhn?titleId="+webtoonId+"&no="+pagenum+"&weekday=tue");
	}
	
	
	//웹툰페이지 HTML불러오기
	public Document getDocument(String url) throws IOException {
        Document doc = null;
        doc = Jsoup.connect(url).get();
        return doc;
    }
	
	//HTML 파싱하는 부분
	public void parse(String url) {
		
        try {
            Document doc = getDocument(url);
            Elements images = doc.select("div.wt_viewer > img"); // 이미지를 가져온다.
            
            for(Element e : images) {
                String img = e.attr("src");
                String imageDir = saveDir;
                String imageName = img.substring(img.lastIndexOf("/"));
                 
                Thread t = new ImageSaver(img, imageDir, imageName);
               
                i++; //이미지 파일을 만들때마다 i값이 증가. 나중에 이미지 갯수가 됨
                
                t.start();
                t.join();
            } // end for
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        }  
    }	
}

//파일저장. 쓰레드이용하여 효율적으로 다운(오픈소스 참고해서 코딩한거라 간략하게 만들 필요 있음.)
class ImageSaver extends Thread {
    private String saveImageName;
    private String imageName;
    private String saveDir;
    private String status;
    
    public ImageSaver(String imageName, String saveDir, String saveImageName) {
        this.saveDir = saveDir;
        this.saveImageName = saveImageName;
        this.imageName = imageName;
    }
     
    public void run() {
    	String atr = saveImageName.substring(saveImageName.lastIndexOf("_"));
    	File saveFile = new File(saveDir + File.separator + atr);
         
        try {
            URL voImageURL = new URL(imageName);
            //이미지에 해당하는 url을 통하여 커넥션을 진행
            HttpURLConnection connection = (HttpURLConnection) voImageURL.openConnection();
            connection.setRequestProperty("User-Agent", "User-Agent Mozilla/5.0 "
            		+ "(Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) likeGecko");
            connection.setRequestProperty("Host", "imgcomic.naver.net");
            connection.setRequestProperty("Referer", "http://comic.naver.com/webtoon/list.nhn?"
            		+ "titleId=507638");
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            connection.setRequestProperty("DNT", "1");
            
            //200_OK 응답에 대해서만 처리
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = connection.getInputStream();
                 
                File f = new File(saveDir);
                if(!f.isDirectory()) {
                	f.mkdirs(); //디렉터리가 없는 초기때 디렉터리를 생성
                }else{
                	f.createNewFile(); //디렉터리가 있으면 다시 생성
                }
                 
                //응답 코드를 C:\1.jpg에 저장
                FileOutputStream fos = new FileOutputStream(saveFile);
                BufferedInputStream bis = new BufferedInputStream(is);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                 
                //응답 코드를 1024바이트 단위로 저장
                int len = 0;
                byte[] buf = new byte[1024];
                while ((len = bis.read(buf, 0, 1024)) != -1) {
                    bos.write(buf, 0, len);
                    bos.flush();
                }
                
                bos.close();
                bis.close();
                fos.close();
                
                status = "Success";
            } else {
                status = "fail";
                System.out.println("connection failed. code is : " + connection.getResponseCode());
                System.out.println("failed connection url : " + connection.getURL());
            }      
        } catch(Exception ex) {
            System.out.println("IMAGENAME : " + status);
            ex.printStackTrace();
        } finally {      
        	
        }
    }
}