package services;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EavesdropMeetingsService {

	private String src = "http://eavesdrop.openstack.org/meetings";
	public String getData() {
		return "Hello from Eavesdrop service.";
	}
	
	public List<String> getProjects() {
		ArrayList<String> projects = new ArrayList<String>();
		
		URL url;
		URLConnection connection;
		Document doc;
		try {
			url = new URL(src);
			connection = url.openConnection();
			doc = Jsoup.parse(url, 5000);
		} catch(Exception e) {
			return null;
		}
		
		if(doc != null) {
			Element table = doc.select("table").get(0);
			Element tbody = table.select("tbody").get(0);
			Elements rows = tbody.select("tr");
			
			for(int i = 3; i < rows.size() - 1; i++) {
				Element row = rows.get(i);
				Elements cols = row.select("td");
				String projectName = cols.get(1).text();
				projects.add(projectName);
			}
		}
		return projects;
	}
	
	public List<String> getMeetings(String project) {
		String source = src + "/" + project;
		ArrayList<String> years = new ArrayList<String>();
		
		URL url;
		URLConnection connection;
		Document doc;
		try {
			url = new URL(source);
			connection = url.openConnection();
			doc = Jsoup.parse(url, 5000);
		} catch(Exception e) {
			return null;
		}
		
		if(doc != null) {
			Element table = doc.select("table").get(0);
			Element tbody = table.select("tbody").get(0);
			Elements rows = tbody.select("tr");
			
			for(int i = 3; i < rows.size() - 1; i++) {
				Element row = rows.get(i);
				Elements cols = row.select("td");
				String year = cols.get(1).text();
				years.add(year);
			}
		}
		return years;
	}
	
	public List<Integer> getMeetingCounts(String project) {
		List<String> years = getMeetings(project);
		
		URL url;
		URLConnection connection;
		Document doc;
		ArrayList<Integer> counts = new ArrayList<Integer>();
		for(int i = 0; i < years.size(); i++) {
			String source = src + "/" + project + "/" + years.get(i);
			try {
				url = new URL(source);
				connection = url.openConnection();
				doc = Jsoup.parse(url, 5000);
			} catch(Exception e) {
				return null;
			}
			counts.add(countMeetings(doc));
		}
		return counts;
	}
	
	private int countMeetings(Document doc) {
		int count = 0;
		String strcmp = "";
		if(doc != null) {
			Element table = doc.select("table").get(0);
			Element tbody = table.select("tbody").get(0);
			Elements rows = tbody.select("tr");
			
			for(int i = 3; i < rows.size() - 1; i++) {
				Element row = rows.get(i);
				Elements cols = row.select("td");
				String lastModified = cols.get(2).text().substring(0, 10);
				if(!lastModified.equals(strcmp)) {
					count++;
					strcmp = lastModified;
				}
			}
		}
		return count;
	}
}
