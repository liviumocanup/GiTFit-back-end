/*
 * Salt Edge Payment Initiation API
 * API Reference for services
 *
 * OpenAPI spec version: 1.0.0
 * Contact: support@saltedge.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.utm.gitfit.model.client.api;

import com.google.gson.reflect.TypeToken;
import com.utm.gitfit.model.client.*;
import com.utm.gitfit.model.client.model.TemplateResponse;
import com.utm.gitfit.model.client.model.TemplatesResponse;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplatesApi {
    private ApiClient apiClient;

    public TemplatesApi() {
        this(Configuration.getDefaultApiClient());
    }

    public TemplatesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for templatesGet
     * @param fromId  (optional)
     * @param deprecated  (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call templatesGetCall(String fromId, Boolean deprecated, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/templates";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (fromId != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("from_id", fromId));
        if (deprecated != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("deprecated", deprecated));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "app_id", "secret" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call templatesGetValidateBeforeCall(String fromId, Boolean deprecated, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        com.squareup.okhttp.Call call = templatesGetCall(fromId, deprecated, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * List of templates
     * Returns all the available payment templates.
     * @param fromId  (optional)
     * @param deprecated  (optional)
     * @return TemplatesResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public TemplatesResponse templatesGet(String fromId, Boolean deprecated) throws ApiException {
        ApiResponse<TemplatesResponse> resp = templatesGetWithHttpInfo(fromId, deprecated);
        return resp.getData();
    }

    /**
     * List of templates
     * Returns all the available payment templates.
     * @param fromId  (optional)
     * @param deprecated  (optional)
     * @return ApiResponse&lt;TemplatesResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<TemplatesResponse> templatesGetWithHttpInfo(String fromId, Boolean deprecated) throws ApiException {
        com.squareup.okhttp.Call call = templatesGetValidateBeforeCall(fromId, deprecated, null, null);
        Type localVarReturnType = new TypeToken<TemplatesResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * List of templates (asynchronously)
     * Returns all the available payment templates.
     * @param fromId  (optional)
     * @param deprecated  (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call templatesGetAsync(String fromId, Boolean deprecated, final ApiCallback<TemplatesResponse> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = templatesGetValidateBeforeCall(fromId, deprecated, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<TemplatesResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for templatesTemplateIdentifierGet
     * @param templateIdentifier  (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call templatesTemplateIdentifierGetCall(String templateIdentifier, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/templates/{template_identifier}"
            .replaceAll("\\{" + "template_identifier" + "\\}", apiClient.escapeString(templateIdentifier.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "app_id", "secret" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call templatesTemplateIdentifierGetValidateBeforeCall(String templateIdentifier, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'templateIdentifier' is set
        if (templateIdentifier == null) {
            throw new ApiException("Missing the required parameter 'templateIdentifier' when calling templatesTemplateIdentifierGet(Async)");
        }
        
        com.squareup.okhttp.Call call = templatesTemplateIdentifierGetCall(templateIdentifier, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Show template
     * Returns a single payment template object.
     * @param templateIdentifier  (required)
     * @return TemplateResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public TemplateResponse templatesTemplateIdentifierGet(String templateIdentifier) throws ApiException {
        ApiResponse<TemplateResponse> resp = templatesTemplateIdentifierGetWithHttpInfo(templateIdentifier);
        return resp.getData();
    }

    /**
     * Show template
     * Returns a single payment template object.
     * @param templateIdentifier  (required)
     * @return ApiResponse&lt;TemplateResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<TemplateResponse> templatesTemplateIdentifierGetWithHttpInfo(String templateIdentifier) throws ApiException {
        com.squareup.okhttp.Call call = templatesTemplateIdentifierGetValidateBeforeCall(templateIdentifier, null, null);
        Type localVarReturnType = new TypeToken<TemplateResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Show template (asynchronously)
     * Returns a single payment template object.
     * @param templateIdentifier  (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call templatesTemplateIdentifierGetAsync(String templateIdentifier, final ApiCallback<TemplateResponse> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = templatesTemplateIdentifierGetValidateBeforeCall(templateIdentifier, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<TemplateResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
