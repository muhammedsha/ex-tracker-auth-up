package com.notix.controller;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Vinu Sagar
 * 28/02/18
 */

@FeignClient(name="zuul-gateway")
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {


    @GetMapping("currency-exchange-service/currency-exchange/from/{from}/to/{to}")
     ConverterBean retrieveExchangeValue(@PathVariable("from") String from,
                                               @PathVariable("to") String to);
}
