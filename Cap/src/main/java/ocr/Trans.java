package ocr;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class Trans {
	
	
public String translator(String data) throws Exception {
 // Instantiates a client
 Translate translate = TranslateOptions.getDefaultInstance().getService();
 String text = data;	 
 String result;
 // The text to translate
 
 // Translates some text into Russian
 Translation translation =
     translate.translate(
         text,
         TranslateOption.sourceLanguage("kor"),
         TranslateOption.targetLanguage("en"));


 //System.out.printf("Text: %s%n", text);

 result = translation.getTranslatedText();
 return result;
}
}