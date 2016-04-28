package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.List;

public class HtmlView implements View {
    private Controller controller;
    private final String filePath = "./src/" + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void update(List<Vacancy> vacancies) {
            updateFile(getUpdatedFileContent(vacancies));
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        String htmlBody = null;
        try {
            Document document=getDocument();
            Element vacancyTemplateElement = document.select(".template").first();
            Element vacancyTemplate = vacancyTemplateElement.clone();
            vacancyTemplate.removeClass("template").removeAttr("style");
            document.select("tr[class=vacancy]").remove();
            Element newVacancy;
            for (Vacancy v:vacancies){
                newVacancy=vacancyTemplate.clone();
                newVacancy.select(".city").first().text(v.getCity());
                newVacancy.select(".companyName").first().text(v.getCompanyName());
                newVacancy.select(".salary").first().text(v.getSalary());
                newVacancy.select("a[href]").first().text(v.getTitle()).attr("href",v.getUrl());
                vacancyTemplateElement.before(newVacancy.outerHtml());
            }
            htmlBody = document.html();
        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
        return htmlBody;
    }

    private void updateFile(String htmlBody){
        try {
            FileOutputStream output = new FileOutputStream(filePath);
            output.write(htmlBody.getBytes("utf-8"));
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Java junior");
    }

    protected Document getDocument() throws IOException{
        return Jsoup.parse(new File(filePath),"UTF-8");
    }
}
