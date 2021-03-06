package com.rajkonkret.basic;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
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

            elements1.forEach(p -> System.out.println(p.getElementsByAttribute("href").text()));
            final List<Elements> a = elements1.stream()
                    .map(p -> p.getElementsByTag("a"))
                    .collect(Collectors.toList());
            System.out.println(a.size());

            a.forEach(at -> System.out.println(at.eachAttr("href")));
            final List<String> href1 = a.get(0).eachAttr("href");
            href1.forEach(System.out::println);
            //pages = Integer.parseInt(elements1.text());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
//        System.out.println(String.valueOf(pages));
    }
}
