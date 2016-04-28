package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.*;
import com.javarush.test.level28.lesson15.big01.view.HtmlView;
import com.javarush.test.level28.lesson15.big01.view.View;

import java.io.IOException;

public class Aggregator {
    public static void main(String[] args)  {
        HtmlView view=new HtmlView();
//        Strategy strategy = new HHStrategy();
        Strategy strategy = new MoikrugStrategy();
        Model model=new Model(view,new Provider(strategy));
        Controller controller=new Controller(model);
        view.setController(controller);
        view.userCitySelectEmulationMethod();
    }
}
