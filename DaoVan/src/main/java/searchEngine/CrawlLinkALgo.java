package searchEngine;

import java.util.List;

public interface CrawlLinkALgo {
	public List<String> crawLinkStrategy(String originLink);
	public boolean isLinkCrawled(String link);
}
