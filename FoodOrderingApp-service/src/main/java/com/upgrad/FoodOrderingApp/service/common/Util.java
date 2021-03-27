package com.upgrad.FoodOrderingApp.service.common;

import com.upgrad.FoodOrderingApp.service.exception.AuthenticationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import org.apache.commons.lang3.StringUtils;

import java.util.Base64;

import static com.upgrad.FoodOrderingApp.service.common.GenericErrorCode.*;

public class Util {


    public static String getBearerAuthToken(String headerParam) throws AuthorizationFailedException {
        if (!headerParam.startsWith(Constants.PREFIX_BEARER)) {
            throw new AuthorizationFailedException("ATHR-001", "Customer is not Logged in");
        } else {
            String bearerToken = StringUtils.substringAfter(headerParam, Constants.PREFIX_BEARER);
            if (bearerToken == null || bearerToken.isEmpty()) {
                throw new UnexpectedException(GEN_001);
            } else {
                return bearerToken;
            }
        }
    }

}

