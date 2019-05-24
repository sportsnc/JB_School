package proto;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AS {
	private Article data = null;
	
	public AS(ArrayList<String> key) {
		String url = "https://as.com/futbol/"; // 검색할 정보가 있는 key ArrayList
		data = new Article();


		// 가져오고 싶은 정보가 있는 웹페이지의 url
		Document doc = null;

		try {
			doc = Jsoup.connect(url).execute().parse(); // Document에 url 페이지의 데이터를 가져온다.
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		for (Element el : doc.select("div.pntc-content")) { // Elements zl =
			el.select("h2.title");
			for (int i = 0; i < key.size(); i++) {
				if ((el.select("a").text().contains(key.get(i).toLowerCase()))) {
					data.setHeadline(el.select("h2 a").text());
					Elements elUrl = el.select("h2 a");
					data.setUrl(elUrl.first().absUrl("href"));
					String temp = el.select("div p a .fecha").text();
					data.setDate(changeDate(temp));
					data.setSite("AS");
				}
			}
		}
	}

	public int changeDate(String date) {
		int formdate = 0;
		String sp[] = date.split("/");
		formdate += Integer.parseInt(sp[2]) * 10000;
		formdate += Integer.parseInt(sp[1]) * 100;
		formdate += Integer.parseInt(sp[0]);

		return formdate;
	}
	public Article getArticle() {
		return data;
	}
}