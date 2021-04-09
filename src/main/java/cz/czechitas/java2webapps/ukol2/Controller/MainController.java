package cz.czechitas.java2webapps.ukol2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
public class MainController {

    //generátor náhodných čísel
    private final Random random = new Random();

    //vytvoření seznamu obrázků
    List<String> seznamObrazku = Arrays.asList("DtW-E6BcSNE", "Z_ydQotnsqk", "bg4Vz54j9x8", "Y9CEWzhftAo", "p5v8DENKY60", "_Q5b2wE-tb8", "BnATLYwyyjo", "ZHdMovHAHT0");

    //vytvoření seznamu z textového souboru s citáty
    private static List<String> readAllLines(String resource) throws IOException {
        //Soubory z resources se získávají pomocí classloaderu. Nejprve musíme získat aktuální classloader.
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        //Pomocí metody getResourceAsStream() získáme z classloaderu InpuStream, který čte z příslušného souboru.
        //Následně InputStream převedeme na BufferedRead, který čte text v kódování UTF-8
        try (InputStream inputStream = classLoader.getResourceAsStream(resource);
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8))) {

            //Metoda lines() vrací stream řádků ze souboru. Pomocí kolektoru převedeme Stream<String> na List<String>.
            return reader
                    .lines()
                    .collect(Collectors.toList());
        }
    }


    //mapování na šablonu
    @GetMapping("/")
    public ModelAndView citaty() throws IOException {
        List<String> citaty = readAllLines("citaty.txt");
        int cisloCitat = random.nextInt(citaty.size());
        int cisloObrazek = random.nextInt(seznamObrazku.size());

        ModelAndView result = new ModelAndView("citaty");
        result.addObject("citat", citaty.get(cisloCitat));
        result.addObject("obrazek", String.format("background-image: url(https://source.unsplash.com/%s/1600x900)", seznamObrazku.get(cisloObrazek)));
        return result;

    }

}


