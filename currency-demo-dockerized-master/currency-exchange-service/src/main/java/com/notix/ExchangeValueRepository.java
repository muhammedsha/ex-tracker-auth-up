package com.notix;

import com.notix.controller.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vinu Sagar
 * 28/02/18
 */
public interface ExchangeValueRepository extends JpaRepository <ExchangeValue,Long> {

    ExchangeValue findByFromAndTo(String from, String to);
}
