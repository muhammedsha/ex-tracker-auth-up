/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notix.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.notix.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


/**
 *
 * @author jibin
 */
@Component
public class AuthenticationFilter extends ZuulFilter{
    private static final int FILTER_ORDER =  2;
    private static final boolean  SHOULD_FILTER=true;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Autowired
    FilterUtils filterUtils;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String filterType() {
        return FilterUtils.PRE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    private boolean isAuthTokenPresent() {
        if (filterUtils.getAuthToken() !=null){
            return true;
        }

        return false;
    }

    private UserInfo isAuthTokenValid(){
        //ResponseEntity<UserInfo> restExchange ;
        try {
            ResponseEntity<UserInfo> restExchange =
                    restTemplate.exchange(
                            "http://authservice:8901/v1/validate/{token}",
                            HttpMethod.GET,
                            null, UserInfo.class, filterUtils.getAuthToken());
            return restExchange.getBody();
        }
        catch(HttpClientErrorException ex){
            if (ex.getStatusCode()==HttpStatus.UNAUTHORIZED) {
                return null;
            }

            throw ex;
        }


        //return restExchange.getBody();
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();

        //If we are dealing with a call to the authentication service, let the call go through without authenticating
        if ( ctx.getRequest().getRequestURI().equals("/authservice/authenticate")){
            return null;
        }

        if (isAuthTokenPresent()){
           logger.debug("Authentication token is present {}.",filterUtils.getAuthToken());
        }else{
            logger.debug("Authentication token is not present.");

            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            ctx.setSendZuulResponse(false);
        }

        UserInfo userInfo = isAuthTokenValid();
        if (userInfo!=null){
            filterUtils.setUserId(userInfo.getUserId());
            //filterUtils.setOrgId(userInfo.getOrganizationId());

           logger.debug("Authentication token is valid.");
            return null;
        }

        logger.debug("Authentication token is not valid.{}.", isAuthTokenValid());
        ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        ctx.setSendZuulResponse(false);

        return null;

    }
    
}
