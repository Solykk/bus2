package com.loaders;

import com.domain.entities.FirstStartIndicator;
import com.repositories.FirstStartIndicatorRepository;
import com.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by burbulet on 4/30/17.
 */

@Component
public class PricesLoader implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    private static Map<String, Map<String, List<BigDecimal>>> allDestinations = allDest();
    private static Map<String, Map<String, List<BigDecimal>>> allDest()
    {
        Map<String,Map<String, List<BigDecimal>>> map = new HashMap<>();
        map.put("KIEV", kievDest());
        map.put("ZHYTOMIR", kievDest());
        map.put("ROVNO", kievDest());
        map.put("LVOV", lvovDest());
        map.put("ODESSA", odessaDest());
        map.put("UMAN", umanDest());
        map.put("VINNITSA", vinnitsaDest());
        map.put("KHMELNITSKIY", khmelDest());
        map.put("TERNOPOL", ternopolDest());
        return map;
    }

    private static Map<String, List<BigDecimal>> kievDest()
    {
        Map<String,List<BigDecimal>> map = new HashMap<>();
        map.put("KRAKOW", Arrays.asList(new BigDecimal(700), new BigDecimal(1200)));
        map.put("BRNO", Arrays.asList(new BigDecimal(1100), new BigDecimal(1900)));
        map.put("PRAGUE", Arrays.asList(new BigDecimal(1200), new BigDecimal(2000)));
        map.put("PLZEN", Arrays.asList(new BigDecimal(1300), new BigDecimal(2200)));
        map.put("NUREMBERG", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("INGOLSTADT", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("MUNICH", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("AUGSBURG", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("ULM", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("STUTTGART", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("KARLSRUHE", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("WURZBURG", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("FRANKFURT", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("DARMSTADT", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("KAISERSLAUTERN", Arrays.asList(new BigDecimal(1800), new BigDecimal(2800)));
        map.put("SAARBRUCKEN", Arrays.asList(new BigDecimal(    1800), new BigDecimal(2800)));
        map.put("TRIER", Arrays.asList(new BigDecimal(1800), new BigDecimal(2800)));
        map.put("RZESZOW", Arrays.asList(new BigDecimal(500), new BigDecimal(800)));
        map.put("WROCLAW", Arrays.asList(new BigDecimal(800), new BigDecimal(1400)));
        map.put("BERLIN", Arrays.asList(new BigDecimal(1400), new BigDecimal(2400)));
        map.put("SCHWERIN", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("HAMBURG", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("BREMEN", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("OSNABRUCK", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("MUNSTER", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("MAGDEBURG", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("BRAUNSCHWEIG", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("HANOVER", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("BIELEFELD", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("DORTMUND", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("ESSEN", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("DUSSELDORF", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        return map;
    }

    private static Map<String, List<BigDecimal>> lvovDest()
    {
        Map<String,List<BigDecimal>> map = new HashMap<>();
        map.put("KRAKOW", Arrays.asList(new BigDecimal(600), new BigDecimal(1000)));
        map.put("BRNO", Arrays.asList(new BigDecimal(1000), new BigDecimal(1700)));
        map.put("PRAGUE", Arrays.asList(new BigDecimal(1100), new BigDecimal(1900)));
        map.put("PLZEN", Arrays.asList(new BigDecimal(1200), new BigDecimal(2100)));
        map.put("NUREMBERG", Arrays.asList(new BigDecimal(1400), new BigDecimal(2400)));
        map.put("INGOLSTADT", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("MUNICH", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("AUGSBURG", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("ULM", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("STUTTGART", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("KARLSRUHE", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("WURZBURG", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("FRANKFURT", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("DARMSTADT", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("KAISERSLAUTERN", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("SAARBRUCKEN", Arrays.asList(new BigDecimal(    1700), new BigDecimal(2700)));
        map.put("TRIER", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("RZESZOW", Arrays.asList(new BigDecimal(400), new BigDecimal(700)));
        map.put("WROCLAW", Arrays.asList(new BigDecimal(700), new BigDecimal(1200)));
        map.put("BERLIN", Arrays.asList(new BigDecimal(1300), new BigDecimal(2300)));
        map.put("SCHWERIN", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("HAMBURG", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("BREMEN", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("OSNABRUCK", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("MUNSTER", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("MAGDEBURG", Arrays.asList(new BigDecimal(1400), new BigDecimal(2400)));
        map.put("BRAUNSCHWEIG", Arrays.asList(new BigDecimal(1400), new BigDecimal(2400)));
        map.put("HANOVER", Arrays.asList(new BigDecimal(1400), new BigDecimal(2400)));
        map.put("BIELEFELD", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("DORTMUND", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("ESSEN", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("DUSSELDORF", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        return map;
    }

    private static Map<String, List<BigDecimal>> odessaDest()
    {
        Map<String,List<BigDecimal>> map = new HashMap<>();
        map.put("KRAKOW", Arrays.asList(new BigDecimal(900), new BigDecimal(1500)));
        map.put("BRNO", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("PRAGUE", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("PLZEN", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("NUREMBERG", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        map.put("INGOLSTADT", Arrays.asList(new BigDecimal(2000), new BigDecimal(3300)));
        map.put("MUNICH", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        map.put("AUGSBURG", Arrays.asList(new BigDecimal(2000), new BigDecimal(3300)));
        map.put("ULM", Arrays.asList(new BigDecimal(2000), new BigDecimal(3300)));
        map.put("STUTTGART", Arrays.asList(new BigDecimal(2000), new BigDecimal(3300)));
        map.put("KARLSRUHE", Arrays.asList(new BigDecimal(2100), new BigDecimal(3400)));
        map.put("WURZBURG", Arrays.asList(new BigDecimal(2000), new BigDecimal(3300)));
        map.put("FRANKFURT", Arrays.asList(new BigDecimal(2000), new BigDecimal(3300)));
        map.put("DARMSTADT", Arrays.asList(new BigDecimal(2100), new BigDecimal(3400)));
        map.put("KAISERSLAUTERN", Arrays.asList(new BigDecimal(2200), new BigDecimal(3600)));
        map.put("SAARBRUCKEN", Arrays.asList(new BigDecimal(    2200), new BigDecimal(3600)));
        map.put("TRIER", Arrays.asList(new BigDecimal(2200), new BigDecimal(3600)));
        map.put("RZESZOW", Arrays.asList(new BigDecimal(700), new BigDecimal(1200)));
        map.put("WROCLAW", Arrays.asList(new BigDecimal(1000), new BigDecimal(1700)));
        map.put("BERLIN", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("SCHWERIN", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        map.put("HAMBURG", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        map.put("BREMEN", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        map.put("OSNABRUCK", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        map.put("MUNSTER", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        map.put("MAGDEBURG", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        map.put("BRAUNSCHWEIG", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        map.put("HANOVER", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        map.put("BIELEFELD", Arrays.asList(new BigDecimal(2000), new BigDecimal(3300)));
        map.put("DORTMUND", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        map.put("ESSEN", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        map.put("DUSSELDORF", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        return map;
    }

    private static Map<String, List<BigDecimal>> umanDest()
    {
        Map<String,List<BigDecimal>> map = new HashMap<>();
        map.put("KRAKOW", Arrays.asList(new BigDecimal(800), new BigDecimal(1400)));
        map.put("BRNO", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("PRAGUE", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("PLZEN", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("NUREMBERG", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("INGOLSTADT", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        map.put("MUNICH", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("AUGSBURG", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        map.put("ULM", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        map.put("STUTTGART", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        map.put("KARLSRUHE", Arrays.asList(new BigDecimal(2000), new BigDecimal(3300)));
        map.put("WURZBURG", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        map.put("FRANKFURT", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        map.put("DARMSTADT", Arrays.asList(new BigDecimal(2000), new BigDecimal(3300)));
        map.put("KAISERSLAUTERN", Arrays.asList(new BigDecimal(2100), new BigDecimal(3400)));
        map.put("SAARBRUCKEN", Arrays.asList(new BigDecimal(    2100), new BigDecimal(3400)));
        map.put("TRIER", Arrays.asList(new BigDecimal(2100), new BigDecimal(3400)));
        map.put("RZESZOW", Arrays.asList(new BigDecimal(600), new BigDecimal(1000)));
        map.put("WROCLAW", Arrays.asList(new BigDecimal(900), new BigDecimal(1500)));
        map.put("BERLIN", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("SCHWERIN", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("HAMBURG", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("BREMEN", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("OSNABRUCK", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("MUNSTER", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("MAGDEBURG", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("BRAUNSCHWEIG", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("HANOVER", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("BIELEFELD", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        map.put("DORTMUND", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("ESSEN", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("DUSSELDORF", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        return map;
    }

    private static Map<String, List<BigDecimal>> vinnitsaDest()
    {
        Map<String,List<BigDecimal>> map = new HashMap<>();
        map.put("KRAKOW", Arrays.asList(new BigDecimal(700), new BigDecimal(1200)));
        map.put("BRNO", Arrays.asList(new BigDecimal(1300), new BigDecimal(2300)));
        map.put("PRAGUE", Arrays.asList(new BigDecimal(1400), new BigDecimal(2400)));
        map.put("PLZEN", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("NUREMBERG", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("INGOLSTADT", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("MUNICH", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("AUGSBURG", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("ULM", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("STUTTGART", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("KARLSRUHE", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        map.put("WURZBURG", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("FRANKFURT", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("DARMSTADT", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        map.put("KAISERSLAUTERN", Arrays.asList(new BigDecimal(2000), new BigDecimal(3300)));
        map.put("SAARBRUCKEN", Arrays.asList(new BigDecimal(    2000), new BigDecimal(3300)));
        map.put("TRIER", Arrays.asList(new BigDecimal(2000), new BigDecimal(3300)));
        map.put("RZESZOW", Arrays.asList(new BigDecimal(600), new BigDecimal(1000)));
        map.put("WROCLAW", Arrays.asList(new BigDecimal(800), new BigDecimal(1400)));
        map.put("BERLIN", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("SCHWERIN", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("HAMBURG", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("BREMEN", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("OSNABRUCK", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("MUNSTER", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("MAGDEBURG", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("BRAUNSCHWEIG", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("HANOVER", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("BIELEFELD", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("DORTMUND", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("ESSEN", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("DUSSELDORF", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        return map;
    }

    private static Map<String, List<BigDecimal>> khmelDest()
    {
        Map<String,List<BigDecimal>> map = new HashMap<>();
        map.put("KRAKOW", Arrays.asList(new BigDecimal(700), new BigDecimal(1200)));
        map.put("BRNO", Arrays.asList(new BigDecimal(1100), new BigDecimal(2000)));
        map.put("PRAGUE", Arrays.asList(new BigDecimal(1300), new BigDecimal(2200)));
        map.put("PLZEN", Arrays.asList(new BigDecimal(1400), new BigDecimal(2400)));
        map.put("NUREMBERG", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("INGOLSTADT", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("MUNICH", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("AUGSBURG", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("ULM", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("STUTTGART", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("KARLSRUHE", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("WURZBURG", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("FRANKFURT", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("DARMSTADT", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("KAISERSLAUTERN", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        map.put("SAARBRUCKEN", Arrays.asList(new BigDecimal(    1900), new BigDecimal(3100)));
        map.put("TRIER", Arrays.asList(new BigDecimal(1900), new BigDecimal(3100)));
        map.put("RZESZOW", Arrays.asList(new BigDecimal(500), new BigDecimal(800)));
        map.put("WROCLAW", Arrays.asList(new BigDecimal(800), new BigDecimal(1400)));
        map.put("BERLIN", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("SCHWERIN", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("HAMBURG", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("BREMEN", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("OSNABRUCK", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("MUNSTER", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("MAGDEBURG", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("BRAUNSCHWEIG", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("HANOVER", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("BIELEFELD", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("DORTMUND", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("ESSEN", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("DUSSELDORF", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        return map;
    }

    private static Map<String, List<BigDecimal>> ternopolDest()
    {
        Map<String,List<BigDecimal>> map = new HashMap<>();
        map.put("KRAKOW", Arrays.asList(new BigDecimal(600), new BigDecimal(1000)));
        map.put("BRNO", Arrays.asList(new BigDecimal(1100), new BigDecimal(1900)));
        map.put("PRAGUE", Arrays.asList(new BigDecimal(1200), new BigDecimal(2000)));
        map.put("PLZEN", Arrays.asList(new BigDecimal(1300), new BigDecimal(2300)));
        map.put("NUREMBERG", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("INGOLSTADT", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("MUNICH", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("AUGSBURG", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("ULM", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("STUTTGART", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("KARLSRUHE", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("WURZBURG", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("FRANKFURT", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("DARMSTADT", Arrays.asList(new BigDecimal(1700), new BigDecimal(2700)));
        map.put("KAISERSLAUTERN", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));
        map.put("SAARBRUCKEN", Arrays.asList(new BigDecimal(    1800), new BigDecimal(2900)));
        map.put("TRIER", Arrays.asList(new BigDecimal(1800), new BigDecimal(2900)));

        map.put("RZESZOW", Arrays.asList(new BigDecimal(500), new BigDecimal(800)));
        map.put("WROCLAW", Arrays.asList(new BigDecimal(700), new BigDecimal(1200)));
        map.put("BERLIN", Arrays.asList(new BigDecimal(1400), new BigDecimal(2400)));
        map.put("SCHWERIN", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("HAMBURG", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("BREMEN", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("OSNABRUCK", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("MUNSTER", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("MAGDEBURG", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("BRAUNSCHWEIG", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("HANOVER", Arrays.asList(new BigDecimal(1500), new BigDecimal(2500)));
        map.put("BIELEFELD", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("DORTMUND", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("ESSEN", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        map.put("DUSSELDORF", Arrays.asList(new BigDecimal(1600), new BigDecimal(2600)));
        return map;
    }

    private CityService cityService;
    private FirstStartIndicatorRepository startIndicatorRepository;

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @Autowired
    public void setStartIndicatorRepository(FirstStartIndicatorRepository startIndicatorRepository) {
        this.startIndicatorRepository = startIndicatorRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        FirstStartIndicator indicator = startIndicatorRepository.findOne(0);
        if (indicator != null) return;

        for (String startCityCode : allDestinations.keySet()) {
            Map<String, List<BigDecimal>> destinations = allDestinations.get(startCityCode);
            for (String endCityCode : destinations.keySet()) {
                List<BigDecimal> prices = destinations.get(endCityCode);
                cityService.createDestination(startCityCode, endCityCode, prices.get(0), prices.get(1));
            }
        }

        indicator = new FirstStartIndicator(0, true);
        startIndicatorRepository.save(indicator);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
