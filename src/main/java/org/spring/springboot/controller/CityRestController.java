package org.spring.springboot.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spring.springboot.domain.City;
//import org.spring.springboot.rabbit.RabbitmqConfig;
import org.spring.springboot.rabbit.Send;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by LMH on 07/07/2019.
 */
@RestController
public class CityRestController {

    private static final Logger logger = LogManager.getLogger(CityRestController.class.getName());

    @Autowired
    private CityService cityService;
//    @Autowired
//    RabbitmqConfig rabbitmqConfig;
    @Autowired
    Send send;

    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.GET)
    public City findOneCity(@PathVariable("id") Long id) {
        logger.info("------------- "+cityService.findCityById(id));
        return cityService.findCityById(id);
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.POST)
    public void createCity(@RequestBody City city) {
        cityService.saveCity(city);
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.PUT)
    public void modifyCity(@RequestBody City city) {
        cityService.updateCity(city);
    }

    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.DELETE)
    public void modifyCity(@PathVariable("id") Long id) {
        cityService.deleteCity(id);
    }


    @RequestMapping(value = "/api/rabbit", method = RequestMethod.GET)
    public void  test(){
//        rabbitmqConfig.rabbitTemplate();
        send.sendMsg("hahaha");
    }
}
