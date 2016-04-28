package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllJobsStrategy implements Strategy {
    private static final String URL_FORMAT = "http://www.alljobs.co.il/SearchResultsGuest.aspx?page=%d&freetxt=%s";
    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> res = new ArrayList<>();
        int page = 1;
        Document document;
        Elements elements;
        Vacancy v;
        while (true) {
            try {
                document = getDocument(searchString, page++);
                elements = document.getElementsByClass("open-board");
                if (elements.isEmpty()) return res;
                for (Element element : elements) {
                    v = new Vacancy();
                    v.setCity(element.select(".location").text());
                    v.setCompanyName(element.select(".company_name > a").text());
                    v.setSalary(element.select(".salary").text());
                    v.setSiteName("http://moikrug.ru.ru");
                    v.setTitle(element.select(".title").text());
                    v.setUrl("https://moikrug.ru"+element.select(".job_icon").attr("href"));
                    res.add(v);
//                    System.out.println(v);
                }
            } catch (IOException e) {
            }
        }
    }
    private Document getDocument(String searchString, int page) throws IOException {
        String url = String.format(URL_FORMAT, page, searchString);
        String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36";
        String referrer = "none";
        Document document = Jsoup.connect(url).userAgent(userAgent).referrer(referrer).get();
        return document;
    }

}
