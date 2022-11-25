package com.utm.gitfit.model.client.auth;

import com.utm.gitfit.model.client.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ApiIdAndSecretAuth implements Authentication {

    private final String appId;
    private final String apiSecret;

    private static final String appIdHeaderKey = "APP_ID";
    private static final String secretHeaderKey = "SECRET";

    public ApiIdAndSecretAuth(@Value("salt_edge.appId") String appId,@Value("salt_edge.secret") String apiSecret) {
        this.appId = appId;
        this.apiSecret = apiSecret;
    }

    @Override
    public void applyToParams(List<Pair> queryParams, Map<String, String> headerParams) {
        if(appId != null){
            headerParams.put(appIdHeaderKey, appId);
        }
        if(apiSecret != null){
            headerParams.put(secretHeaderKey, apiSecret);
        }
    }
}
