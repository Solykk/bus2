package com.loaders;

import com.domain.entities.FirstStartIndicator;
import com.domain.request.CreateCityLocaleRequest;
import com.domain.request.CreateCityRequest;
import com.repositories.FirstStartIndicatorRepository;
import com.services.CityService;
import com.services.LocaleService;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by burbulet on 4/17/17.
 */

@Component
public class CitiesLoader implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    private static final Logger LOG = Logger.getLogger(CitiesLoader.class);

    private CityService cityService;
    private LocaleService localeService;
    private FirstStartIndicatorRepository startIndicatorRepository;

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }
    @Autowired
    public void setLocaleService(LocaleService localeService) {
        this.localeService = localeService;
    }
    @Autowired
    public void setStartIndicatorRepository(FirstStartIndicatorRepository startIndicatorRepository) {
        this.startIndicatorRepository = startIndicatorRepository;
    }

    private static final List<String> enCities = Arrays.asList(
            "KIEV",
            "ZHYTOMIR",
            "ROVNO",
            "LVOV",
            "KRAKOW",
            "BRNO",
            "PRAGUE",
            "PLZEN",
            "NUREMBERG",
            "INGOLSTADT",
            "MUNICH",
            "AUGSBURG",
            "ULM",
            "STUTTGART",
            "KARLSRUHE",
            "WURZBURG",
            "FRANKFURT",
            "DARMSTADT",
            "KAISERSLAUTERN",
            "SAARBRUCKEN",
            "TRIER",
            "ODESSA",
            "UMAN",
            "VINNITSA",
            "KHMELNITSKIY",
            "TERNOPOL",
            "RZESZOW",
            "WROCLAW",
            "BERLIN",
            "SCHWERIN",
            "HAMBURG",
            "BREMEN",
            "OSNABRUCK",
            "MUNSTER",
            "MAGDEBURG",
            "BRAUNSCHWEIG",
            "HANOVER",
            "BIELEFELD",
            "DORTMUND",
            "ESSEN",
            "DUSSELDORF"
    );

    private static final List<String> ruCities = Arrays.asList(
            "Киев",
            "Житомир",
            "Ровно",
            "Львов",
            "Краков",
            "Брно",
            "Прага",
            "Плзень",
            "Нюрнберг",
            "Ингольштадт",
            "Мюнхен",
            "Аугсбург",
            "Ульм",
            "Штуттгарт",
            "Карлсруе",
            "Вюрцбург",
            "Франкфурт",
            "Дармштадт",
            "Кайзерслаутерн",
            "Саарбрюкен",
            "Трир",
            "Одесса",
            "Умань",
            "Винница",
            "Хмельницкий",
            "Тернополь",
            "Жешув",
            "Вроцлав",
            "Берлин",
            "Шверин",
            "Гамбург",
            "Бремен",
            "Оснабрюк",
            "Мюнстер",
            "Магдебург",
            "Брауншвейг",
            "Ганновер",
            "Билефельд",
            "Дортмунд",
            "Эссен",
            "Дюссельдорф"
    );

    private static final Map<String, List<String>> localization = createMap();
    private static Map<String, List<String>> createMap()
    {
        Map<String,List<String>> map = new HashMap<>();
        map.put("en", enCities);
        map.put("ru", ruCities);
        return map;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        FirstStartIndicator indicator = startIndicatorRepository.findOne(0);
        if (indicator != null) return;

        createLocales();
        createCities();
    }

    private void createLocales() {
        for (String code : localization.keySet()) {
            localeService.createLocale(code);
        }
    }

    private void createCities() {
        for (int i = 0; i < enCities.size(); i++) {
            String cityCode = enCities.get(i);
            String imageUrl = "https://raw.githubusercontent.com/bus2eu/resources/master/" + WordUtils.capitalizeFully(cityCode) + ".jpg";
            List<CreateCityLocaleRequest> localeRequests = new ArrayList<>();
            for (String localeCode : localization.keySet()) {
                List<String> localizedNames = localization.get(localeCode);
                String localizedName = localizedNames.get(i);
                CreateCityLocaleRequest request = new CreateCityLocaleRequest(localeCode, WordUtils.capitalizeFully(localizedName));
                localeRequests.add(request);
            }
            CreateCityRequest request = new CreateCityRequest(cityCode, imageUrl, localeRequests);
            cityService.createCity(request);
        }
    }

        @Override
        public int getOrder() {
            return 0;
        }
}
