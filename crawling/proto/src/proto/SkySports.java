package proto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SkySports {

	private Article data = null;

	public SkySports(ArrayList<String> key) throws Exception {
		String url = "https://www.skysports.com/search?q=";

		data = new Article();

		Document doc = null;
		Elements element = null;

		for (int i = 0; i < key.size(); i++) {
			String urlTmp = url + key.get(i);
			doc = Jsoup.connect(urlTmp).execute().parse(); // Document에 url 페이지의 데이터를 가져온다.

			element = doc.select("div.news-list");
			for (Element el : element.select("div.news-list__item")) {
				if (el.select("h4").select("a").text().toLowerCase().contains(key.get(i).toLowerCase())) {
					data.setHeadline(el.select("h4").select("a").text());
					Elements elUrl = el.select("a[href]");
					data.setUrl(elUrl.first().absUrl("href"));
					String temp = el.select(".label__timestamp").text();
					data.setDate(changeDate2(temp));
					data.setSite("SKYSPORTS");
				}
			}

		}

	}

	public int changeDate2(String date) {
		date = date.substring(0, 2) + date.substring(2 + 1);
		date = date.substring(0, 4) + date.substring(4 + 1);
		String year = date.substring(4, 8);
		String month = date.substring(2, 4);
		String day = date.substring(0, 2);
		String fdate = year + month + day;

		int mydate = Integer.parseInt(fdate);
		return mydate;
	}

	public int changeDate(String date) {
		int formdate = 0;
		String sp[] = date.split(" ");
		formdate += Integer.parseInt(sp[2]) * 10000;
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