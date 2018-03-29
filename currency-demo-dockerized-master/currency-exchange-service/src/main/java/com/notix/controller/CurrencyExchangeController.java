package com.notix.controller;


import com.notix.ExchangeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author Vinu Sagar
 * 28/02/18
 */
@RestController
public class CurrencyExchangeController{

    @Autowired
    private Environment env;
    @Autowired
    private ExchangeValueRepository repo;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable("from") String from,
    @PathVariable("to") String to) {
        ExchangeValue value=repo.findByFromAndTo(from,to);
         value.setPort(Integer.parseInt(env.getProperty("local.server.port")));
         logger.info("{}",value);
         return value;
    }


}
