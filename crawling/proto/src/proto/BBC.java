package proto;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BBC {
    Article data = null;
	
   public BBC(ArrayList<String> key) throws Exception {
	  // 찾으려고 하는 웹페이지의 url 입력
      String url = "https://www.bbc.co.uk/search?q=";
      data = new Article();
      //url을 담을 document
      Document doc = null;
      Elements element = null;

      
      
         for (int i = 0; i < key.size(); i++) {
            String urlTmp = url + key.get(i)+"&filter=sport&suggid=";
            try {
    			doc = Jsoup.connect(urlTmp).get(); // Document�� url �������� �����͸� �����´�.
    		}

    		catch (IOException e) {
    			e.printStackTrace();
    		}
            
          
			element = doc.select("article");
			for (Element el : element.select("article")) {
				data.setHeadline(el.select("h1 a").text());
				Elements elUrl = el.select("a[href]");
				data.setUrl(elUrl.first().absUrl("href"));
				String temp = el.select(".display-date").text();
				System.out.println(el.select(".display-date").text());
				data.setDate(changeDate(temp));
				data.setSite("BBC");
			}

		}
      
   }
   
   public int changeDate2(String date) {
     date = date.substring(0, 2) + date.substring(2 + 1);  
     date = date.substring(0, 4) + date.substring(4 + 1);
     String year = date.substring(4, 8);
     String month = date.substring(2,4);
     String day = date.substring(0, 2);
     String fdate = year + month + day;
     
     int mydate = Integer.parseInt(fdate);
     return mydate;
   }
   
   public int changeDate(String date) {
      int formdate = 0; 
        String sp[] = date.split(" ");
        System.out.println(sp[0]+sp[1]+sp[2]);
      formdate += Integer.parseInt(sp[2])%100 * 10000;
      formdate += Integer.parseInt(sp[0]);
      
      switch (sp[1]) {
      case "January":
      case "Jan":
         formdate += 100;
         break;
      case "February":
      case "Feb":
         formdate += 200;
         break;
      case "March":
      case "Mar":
         formdate += 300;
         break;
      case "April":
      case "Apr":
         formdate += 400;
         break;
      case "May":
         formdate += 500;
         break;
      case "June":
      case "Jun":
         formdate += 600;
         break;
      case "July":
      case "Jul":
         formdate += 700;
         break;
      case "August":
      case "Aug":
         formdate += 800;
         break;
      case "September":
      case "Sep":
         formdate += 900;
         break;
      case "October":
      case "Oct":
         formdate += 1000;
         break;
      case "November":
      case "Nov":
         formdate += 1100;
         break;
      case "December":
      case "Dec":
         formdate += 1200;
         break;

      }
      
      return formdate;
      
   }
   
   public Article getArticle() {
	   return data;
   }
}