package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";
    private static final String URL_TEST = "http://javarush.ru/testdata/big28data.html";

    protected Document getDocument(String searchString, int page) throws IOException {
        String url = String.format(URL_FORMAT, searchString, page);
        String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36";
        String referrer = "none";
        Document document = Jsoup.connect(url).userAgent(userAgent).referrer(referrer).get();
        return document;
    }

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> res = new ArrayList<>();
        int page = 0;
        Document document;
        Elements elements;
        Vacancy v;
        while (true) {
            try {
                document = getDocument(searchString, page++);
                elements = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                if (elements.isEmpty()) return res;
                for (Element element : elements) {
                    v = new Vacancy();
                    v.setCity(element.select("[data-qa=\"vacancy-serp__vacancy-address\"]").text());
                    v.setCompanyName(element.select("[data-qa=\"vacancy-serp__vacancy-employer\"]").text());
                    v.setSalary(element.select("[data-qa=\"vacancy-serp__vacancy-compensation\"]").text());
                    v.setSiteName("http://hh.ru");
                    v.setTitle(element.select("[data-qa=\"vacancy-serp__vacancy-title\"]").text());
                    v.setUrl(element.select("[data-qa=\"vacancy-serp__vacancy-title\"]").attr("href"));
                    res.add(v);
//                    System.out.println(v);
                }
            } catch (IOException e) {
            }
        }
    }
}
