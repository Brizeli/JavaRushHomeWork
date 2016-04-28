package com.javarush.test.level19.lesson03.task05;

import java.util.HashMap;
import java.util.Map;

/* Закрепляем адаптер
Адаптировать Customer и Contact к RowItem.
Классом-адаптером является DataAdapter.
Инициализируйте countries перед началом выполнения программы. Соответствие кода страны и названия:
UA Ukraine
RU Russia
CA Canada
*/

public class Solution {
    public static void main(String[] args) {
        Customer customer=new Customer() {
            @Override
            public String getCompanyName() {
                return "Javarush";
            }

            @Override
            public String getCountryName() {
                return "Ukraine";
            }
        };
        Contact contact=new Contact() {
            @Override
            public String getName() {
                return "Ivanov, Ivan";
            }

            @Override
            public String getPhoneNumber() {
                return "+38(050)123-45-67";
            }
        };
        RowItem rowItem=new DataAdapter(customer,contact);
        System.out.println(rowItem.getContactFirstName());
        System.out.println(rowItem.getContactLastName());
        System.out.println(rowItem.getCompany());
        System.out.println(rowItem.getCountryCode());
        System.out.println(rowItem.getDialString());
    }
    private static Map<String,String> countries = new HashMap<String,String>();
    static{
        countries.put("UA","Ukraine");
        countries.put("RU","Russia");
        countries.put("CA","Canada");
    }

    public static class DataAdapter implements RowItem{
        Customer customer;
        Contact contact;

        public DataAdapter(Customer customer, Contact contact) {
            this.customer=customer;
            this.contact=contact;
        }

        @Override
        public String getCountryCode() {
            for (Map.Entry<String,String> entry:countries.entrySet()){
                if (entry.getValue().equals(customer.getCountryName()))
                    return entry.getKey();
            }
            return null;
        }

        @Override
        public String getCompany() {
            return customer.getCompanyName();
        }

        @Override
        public String getContactFirstName() {
            return contact.getName().split("\\W+")[1];
        }

        @Override
        public String getContactLastName() {
            return contact.getName().split("\\W+")[0];
        }

        @Override
        public String getDialString() {
            return "callto://"+contact.getPhoneNumber().replaceAll("[\\D+^+]}","");
        }
    }

    public static interface RowItem {
        String getCountryCode();        //example UA
        String getCompany();            //example JavaRush Ltd.
        String getContactFirstName();   //example Ivan
        String getContactLastName();    //example Ivanov
        String getDialString();         //example callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.
        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan
        String getPhoneNumber();        //example +38(050)123-45-67
    }
}