package com.javarush.test.level22.lesson09.task02;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* Формируем Where
Сформируйте часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.
Пример:
{"name", "Ivanov", "country", "Ukraine", "city", "Kiev", "age", null}
Результат:
"name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'"
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("name","Ivanov");
        params.put("country", "Ukraine");
        params.put("city", "Kiev");
        params.put("age",null);
        System.out.println(getCondition(params));
    }
    public static StringBuilder getCondition(Map<String, String> params) {
        StringBuilder res=new StringBuilder();
        if (params == null || params.size() == 0)
            return res;
        for (Map.Entry<String,String> entry:params.entrySet()){
            if (entry.getKey() != null && entry.getValue()!=null)
                res.append(String.format("%s = \'%s\' and ", entry.getKey(), entry.getValue()));
        }
        res.setLength(res.length()-5);
        return res;
    }
}
