package proto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TheSun {

	private Article data = null;

	public TheSun(ArrayList<String> key) {
		data = new Article();
		String url = "https://www.thesun.co.uk/?s=";
		Document doc = null;
		Elements element = null;

		for (int i = 0; i < key.size(); i++) {
			url = url + key.get(i);
			try {
				doc = Jsoup.connect(url).get(); // Document에 url 페이지의 데이터를 가져온다.
			} catch (IOException e) {
				e.printStackTrace();
			}

			element = doc.select("div.search-results-wrap");

			for (Element el : element.select(".teaser-item")) {

				if (el.select("p").text().toLowerCase().contains(key.get(i).toLowerCase())) {
					data.setHeadline(el.select("p").text());
					Elements elUrl = el.select(".teaser__copy-container a");
					data.setUrl(elUrl.first().absUrl("href"));
					String temp = el.select(".search-date").text();
					data.setDate(changeDate(temp));
					data.setSite("The Sun");
				}

			}
			url = "https://www.thesun.co.uk/?s=";
		}

		for (int i = 0; i < data.getHowManyData(); i++) {
			System.out.println(data.getDate(i));
			System.out.println(data.getHeadline(i));
			System.out.println(data.getUrl(i));
			System.out.println(data.getSite(i));

		}
	}

	public int changeDate(String date) {
		int formdate = 0;
		String sp[] = date.split(" ");
		formdate += Integer.parseInt(sp[2]) * 10000;
		formdate += Integer.parseInt(sp[0]);

		switch (sp[1]) {
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

		return formdate;
	}
	
	public Article getArticle() {
		return data;
	}
}
