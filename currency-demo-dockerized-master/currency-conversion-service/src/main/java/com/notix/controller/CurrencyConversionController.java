package com.notix.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author Vinu Sagar
 * 28/02/18
 */
@RestController
public class CurrencyConversionController {

    @Autowired
    CurrencyExchangeServiceProxy proxy;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public ConverterBean ConvertCurrency(@PathVariable("from") String from,
                                         @PathVariable("to") String to,
                                         @PathVariable("quantity") BigDecimal quantity){
        ConverterBean response = proxy.retrieveExchangeValue(from, to);
        logger.info("{}", response);


        return new ConverterBean(response.getId(), from, to, response.getConversionMultiple(),
                quantity, quantity.multiply(response.getConversionMultiple()),response.getPort());

    }
}
