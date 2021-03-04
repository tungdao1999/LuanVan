package lucene.crawler;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lucene.indexer.IndexCrawl;
import resource.ResourcesUtils;

public class Crawler {
	public static void main(String[] args) {
		String dir = ResourcesUtils.resourcePath + "/lucene/crawled/";
		String url = "https://viblo.asia/newest";
		String domain = "https://viblo.asia";
		List<String> childUrls = new ArrayList<String>();
		List<String> title = new ArrayList<String>();
		try {
			Document document = Jsoup.connect(url).get();
			Elements feeds = document.getElementsByClass("post-title--inline");
		//	Element feed = feeds.get(0);
			
			for (Element feed : feeds) {
				Element aTag = feed.getElementsByAttribute("href").get(0);
				String text = aTag.text();
				System.out.println("text: " + text);
				String link = domain + aTag.attr("href");
				System.out.println("link: " + link);
				childUrls.add(link);
				title.add(text);
				String content = DeepCrawl(link);
				
				String symbol ="[!@#$%^&*(),.?\":{}|<>/]";
				String fileName = text.replaceAll(symbol, "");
				System.out.println(fileName);
				File f= new File(dir + fileName + ".txt");
				PrintWriter pw = new PrintWriter(f, Charset.forName("UTF-8"));
				pw.write(content);
				pw.close();
				
				IndexCrawl indexCrawl = new IndexCrawl();
				indexCrawl.writeCrawledDocument(domain, link, content);
			}
			
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String DeepCrawl(String url) {
		// TODO Auto-generated method stub
		try {
			Document document = Jsoup.connect(url).get();
			Element article = document.getElementsByTag("article").get(0);

			String text = article.text();
			//System.out.println(text);
			
			return text;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
