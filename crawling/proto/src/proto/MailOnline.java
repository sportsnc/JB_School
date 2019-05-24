package proto;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MailOnline {
   private Article data = null;
	
   public MailOnline(ArrayList<String> key) {
      String url = "https://www.dailymail.co.uk/home/search.html?offset=0&size=50&sel=site&searchPhrase=";
      data = new Article();


      url += key.get(0).toLowerCase();
      
      for(int i=1;i<key.size();i++) {
         url += "+"+key.get(i).toLowerCase();
         
      }
      url += "&sort=recent&type=article&days=all";
      
      // 가져오고 싶은 정보가 있는 웹페이지의 url
      Document doc = null;
      try {
         doc = Jsoup.connect(url).get(); // Document에 url 페이지의 데이터를 가져온다.
      } catch (IOException e) {
         e.printStackTrace();
      }

      for (Element el : doc.select("div .sch-result")) {

         data.setHeadline(el.select("h3 a").text());
         Elements elUrl = el.select("h3 a");
         data.setUrl(elUrl.first().absUrl("href"));
         String temp = el.select("h4").text();
         data.setDate(changeDate(temp));
         data.setSite("MailOnline");

      }
      }

      public static int changeDate(String date) {
      int formdate = 0;
      String sp[] = date.split("-");
      
      String sp_r[] = sp[1].split(" ");
      
      formdate += Integer.parseInt(sp_r[3].substring(2,4))* 10000;
      switch (sp_r[1]) {
      case "January":
           formdate += 100;
           break;
        case "February":
           formdate += 200;
           break;
        case "March":
           formdate += 300;
           break;
        case "April":
           formdate += 400;
           break;
        case "May":
           formdate += 500;
           break;
        case "June":
           formdate += 600;
           break;
        case "July":
           formdate += 700;
           break;
        case "August":
           formdate += 800;
           break;
        case "September":
           formdate += 900;
           break;
        case "October":
           formdate += 1000;
           break;
        case "November":
           formdate += 1100;
           break;
        case "December":
           formdate += 1200;
           break;

        }
      if(sp_r[2].length()==3) {
         formdate += Integer.parseInt(sp_r[2].substring(0,1));
      }
      else {
         formdate += Integer.parseInt(sp_r[2].substring(0,2));
      }
      return formdate;
   }
      
   public Article getArticle() {
	   return data;
   }
}