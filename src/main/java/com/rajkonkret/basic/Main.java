package com.rajkonkret.basic;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // write your code here
        String link = "https://www.otomoto.pl/osobowe/hyundai/tucson/?search%5Bfilter_enum_generation%5D%5B0%5D=gen-ii-2015&search%5Bfilter_enum_damaged%5D=0&search%5Border%5D=filter_float_price%3Aasc&search%5Bbrand_program_id%5D%5B0%5D=&search%5Bcountry%5D=";
        int pages = 0;
        try {
            Document documentPage = Jsoup.connect(link).get();
            Elements elements1 = documentPage
                    .getElementsByClass("om-pager rel");

            final List<String> pageList = elements1.stream()
                    .map(p -> p.getElementsByAttribute("href").text())
                    .collect(Collectors.toList());
            final List<String> s = Arrays.asList(pageList.get(0).split(" "));
            //s.forEach(System.out::println);

            pages = Integer.parseInt(s.get(s.size() - 1));
            System.out.println("max page is " + pages);
            final List<Elements> a = elements1.stream()
                    .map(p -> p.getElementsByTag("a"))
                    .collect(Collectors.toList());
            //System.out.println(a.size());

            a.forEach(at -> System.out.println(at.eachAttr("href")));
            final List<String> href1 = a.get(0).eachAttr("href");
            System.out.println(href1.size());
            href1.forEach(System.out::println);
            //pages = Integer.parseInt(elements1.text());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
//        System.out.println(String.valueOf(pages));
        String linkToOfferPage = "https://www.otomoto.pl/osobowe/hyundai/tucson/?search%5Bfilter_enum_generation%5D%5B0%5D=gen-ii-2015&search%5Bfilter_enum_damaged%5D=0&search%5Border%5D=filter_float_price%3Aasc&search%5Bbrand_program_id%5D%5B0%5D=&search%5Bcountry%5D=&page=2";
        try {
            Document documentOffferOnPage = Jsoup.connect(linkToOfferPage).get();
            Elements elementsOnPage = documentOffferOnPage
                    .getElementsByClass("offer-item__content ds-details-container");
            System.out.println(String.valueOf(elementsOnPage.get(0).getElementsByTag("ul").tagName("li").get(0).getElementsByAttributeValue("data-code", "mileage").text()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
