package com.javarush.test.level08.lesson08.task05;

import java.util.*;

/* Удалить людей, имеющих одинаковые имена
Создать словарь (Map<String, String>) занести в него десять записей по принципу «фамилия» - «имя».
Удалить людей, имеющих одинаковые имена.
*/

public class Solution
{
    public static void main(String[] args) {
        HashMap<String,String> map=createMap();
        for (Map.Entry<String,String> entry:map.entrySet()) System.out.println(entry);
        removeTheFirstNameDuplicates(map);
        System.out.println("");
        for (Map.Entry<String,String> entry:map.entrySet()) System.out.println(entry);
    }
    public static HashMap<String, String> createMap()
    {
        HashMap<String ,String> map=new HashMap<String, String>();
        map.put("Крячко", "Сергей");
        map.put("Шумейко", "Ольга");
        map.put("Ендовицкий", "Максим");
        map.put("Дякун1", "Людмила");
        map.put("Дякун2", "Александр");
        map.put("Дякун3", "Ольга");
        map.put("Табаков", "Александр");
        map.put("Ярмантович", "Станислав");
        map.put("Скочеляс", "Ольга");
        map.put("Хомышин", "Антон");
        return map;
    }

    public static void removeTheFirstNameDuplicates(HashMap<String, String> map)
    {
        ArrayList<String> names=new ArrayList<String>();
        Iterator<Map.Entry<String,String>> iter=map.entrySet().iterator();
        while (iter.hasNext()) names.add(iter.next().getValue());
        for (int i=0;i<names.size()-1;i++){
            String name=names.get(i);
            for (int j=i+1;j<names.size();j++){
                if (names.get(j)==name){
                    removeItemFromMapByValue(map,name);
                }
            }
        }
    }

    public static void removeItemFromMapByValue(HashMap<String, String> map, String value)
    {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair: copy.entrySet())
        {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }
}
