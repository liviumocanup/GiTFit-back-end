package com.utm.gitfit.model.client.api;

import com.google.gson.reflect.TypeToken;
import com.utm.gitfit.model.client.*;
import com.utm.gitfit.model.client.model.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomersApi {

    private ApiClient apiClient;

    public CustomersApi() {
        this(Configuration.getDefaultApiClient());
    }

    public CustomersApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for customersCustomerIdDelete
     * @param customerId  (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call customersCustomerIdDeleteCall(Integer customerId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/customers/{customer_id}"
            .replaceAll("\\{" + "customer_id" + "\\}", apiClient.escapeString(customerId.toString()));

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
        return apiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call customersCustomerIdDeleteValidateBeforeCall(Integer customerId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'customerId' is set
        if (customerId == null) {
            throw new ApiException("Missing the required parameter 'customerId' when calling customersCustomerIdDelete(Async)");
        }
        
        com.squareup.okhttp.Call call = customersCustomerIdDeleteCall(customerId, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Remove a customer
     * Deletes a customer, returning the customer object. This route is available only for web applications. 
     * @param customerId  (required)
     * @return RemovedCustomerResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public RemovedCustomerResponse customersCustomerIdDelete(Integer customerId) throws ApiException {
        ApiResponse<RemovedCustomerResponse> resp = customersCustomerIdDeleteWithHttpInfo(customerId);
        return resp.getData();
    }

    /**
     * Remove a customer
     * Deletes a customer, returning the customer object. This route is available only for web applications. 
     * @param customerId  (required)
     * @return ApiResponse&lt;RemovedCustomerResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<RemovedCustomerResponse> customersCustomerIdDeleteWithHttpInfo(Integer customerId) throws ApiException {
        com.squareup.okhttp.Call call = customersCustomerIdDeleteValidateBeforeCall(customerId, null, null);
        Type localVarReturnType = new TypeToken<RemovedCustomerResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Remove a customer (asynchronously)
     * Deletes a customer, returning the customer object. This route is available only for web applications. 
     * @param customerId  (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call customersCustomerIdDeleteAsync(Integer customerId, final ApiCallback<RemovedCustomerResponse> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = customersCustomerIdDeleteValidateBeforeCall(customerId, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<RemovedCustomerResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for customersCustomerIdGet
     * @param customerId  (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call customersCustomerIdGetCall(Integer customerId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/customers/{customer_id}"
            .replaceAll("\\{" + "customer_id" + "\\}", apiClient.escapeString(customerId.toString()));

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
    private com.squareup.okhttp.Call customersCustomerIdGetValidateBeforeCall(Integer customerId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'customerId' is set
        if (customerId == null) {
            throw new ApiException("Missing the required parameter 'customerId' when calling customersCustomerIdGet(Async)");
        }
        
        com.squareup.okhttp.Call call = customersCustomerIdGetCall(customerId, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Show customer
     * Returns the customer object. 
     * @param customerId  (required)
     * @return CustomerResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public CustomerResponse customersCustomerIdGet(Integer customerId) throws ApiException {
        ApiResponse<CustomerResponse> resp = customersCustomerIdGetWithHttpInfo(customerId);
        return resp.getData();
    }

    /**
     * Show customer
     * Returns the customer object. 
     * @param customerId  (required)
     * @return ApiResponse&lt;CustomerResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<CustomerResponse> customersCustomerIdGetWithHttpInfo(Integer customerId) throws ApiException {
        com.squareup.okhttp.Call call = customersCustomerIdGetValidateBeforeCall(customerId, null, null);
        Type localVarReturnType = new TypeToken<CustomerResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Show customer (asynchronously)
     * Returns the customer object. 
     * @param customerId  (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call customersCustomerIdGetAsync(Integer customerId, final ApiCallback<CustomerResponse> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = customersCustomerIdGetValidateBeforeCall(customerId, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<CustomerResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for customersCustomerIdLockPut
     * @param customerId  (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call customersCustomerIdLockPutCall(Integer customerId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/customers/{customer_id}/lock"
            .replaceAll("\\{" + "customer_id" + "\\}", apiClient.escapeString(customerId.toString()));

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
        return apiClient.buildCall(localVarPath, "PUT", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call customersCustomerIdLockPutValidateBeforeCall(Integer customerId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'customerId' is set
        if (customerId == null) {
            throw new ApiException("Missing the required parameter 'customerId' when calling customersCustomerIdLockPut(Async)");
        }
        
        com.squareup.okhttp.Call call = customersCustomerIdLockPutCall(customerId, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Lock customer
     * Locks a customer and its data, returning the customer object.   All customer related data, including payments, will not be available for reading, updating or deleting even by Salt Edge. This route is available only for web applications. 
     * @param customerId  (required)
     * @return LockedCustomerResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public LockedCustomerResponse customersCustomerIdLockPut(Integer customerId) throws ApiException {
        ApiResponse<LockedCustomerResponse> resp = customersCustomerIdLockPutWithHttpInfo(customerId);
        return resp.getData();
    }

    /**
     * Lock customer
     * Locks a customer and its data, returning the customer object.   All customer related data, including payments, will not be available for reading, updating or deleting even by Salt Edge. This route is available only for web applications. 
     * @param customerId  (required)
     * @return ApiResponse&lt;LockedCustomerResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<LockedCustomerResponse> customersCustomerIdLockPutWithHttpInfo(Integer customerId) throws ApiException {
        com.squareup.okhttp.Call call = customersCustomerIdLockPutValidateBeforeCall(customerId, null, null);
        Type localVarReturnType = new TypeToken<LockedCustomerResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Lock customer (asynchronously)
     * Locks a customer and its data, returning the customer object.   All customer related data, including payments, will not be available for reading, updating or deleting even by Salt Edge. This route is available only for web applications. 
     * @param customerId  (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call customersCustomerIdLockPutAsync(Integer customerId, final ApiCallback<LockedCustomerResponse> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = customersCustomerIdLockPutValidateBeforeCall(customerId, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<LockedCustomerResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for customersCustomerIdUnlockPut
     * @param customerId  (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call customersCustomerIdUnlockPutCall(Integer customerId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/customers/{customer_id}/unlock"
            .replaceAll("\\{" + "customer_id" + "\\}", apiClient.escapeString(customerId.toString()));

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
        return apiClient.buildCall(localVarPath, "PUT", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call customersCustomerIdUnlockPutValidateBeforeCall(Integer customerId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'customerId' is set
        if (customerId == null) {
            throw new ApiException("Missing the required parameter 'customerId' when calling customersCustomerIdUnlockPut(Async)");
        }
        
        com.squareup.okhttp.Call call = customersCustomerIdUnlockPutCall(customerId, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Unlock customer
     * Unlocks a customer and its data, returning the customer object. This route is available only for web applications. 
     * @param customerId  (required)
     * @return UnlockedCustomerResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public UnlockedCustomerResponse customersCustomerIdUnlockPut(Integer customerId) throws ApiException {
        ApiResponse<UnlockedCustomerResponse> resp = customersCustomerIdUnlockPutWithHttpInfo(customerId);
        return resp.getData();
    }

    /**
     * Unlock customer
     * Unlocks a customer and its data, returning the customer object. This route is available only for web applications. 
     * @param customerId  (required)
     * @return ApiResponse&lt;UnlockedCustomerResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<UnlockedCustomerResponse> customersCustomerIdUnlockPutWithHttpInfo(Integer customerId) throws ApiException {
        com.squareup.okhttp.Call call = customersCustomerIdUnlockPutValidateBeforeCall(customerId, null, null);
        Type localVarReturnType = new TypeToken<UnlockedCustomerResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Unlock customer (asynchronously)
     * Unlocks a customer and its data, returning the customer object. This route is available only for web applications. 
     * @param customerId  (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call customersCustomerIdUnlockPutAsync(Integer customerId, final ApiCallback<UnlockedCustomerResponse> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = customersCustomerIdUnlockPutValidateBeforeCall(customerId, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<UnlockedCustomerResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for customersGet
     * @param identifier  (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call customersGetCall(String identifier, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/customers";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (identifier != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("identifier", identifier));

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
    private com.squareup.okhttp.Call customersGetValidateBeforeCall(String identifier, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        com.squareup.okhttp.Call call = customersGetCall(identifier, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * All customers.
     * List all of your app&#x27;s customers. This route is available only for web applications, not mobile ones. 
     * @param identifier  (optional)
     * @return CustomersResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public CustomersResponse customersGet(String identifier) throws ApiException {
        ApiResponse<CustomersResponse> resp = customersGetWithHttpInfo(identifier);
        return resp.getData();
    }

    /**
     * All customers.
     * List all of your app&#x27;s customers. This route is available only for web applications, not mobile ones. 
     * @param identifier  (optional)
     * @return ApiResponse&lt;CustomersResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<CustomersResponse> customersGetWithHttpInfo(String identifier) throws ApiException {
        com.squareup.okhttp.Call call = customersGetValidateBeforeCall(identifier, null, null);
        Type localVarReturnType = new TypeToken<CustomersResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * All customers. (asynchronously)
     * List all of your app&#x27;s customers. This route is available only for web applications, not mobile ones. 
     * @param identifier  (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call customersGetAsync(String identifier, final ApiCallback<CustomersResponse> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = customersGetValidateBeforeCall(identifier, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<CustomersResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for customersPost
     * @param body  (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call customersPostCall(CustomerRequestBody body, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = body;
        
        // create path and map variables
        String localVarPath = "/customers";

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
            "application/json"
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
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call customersPostValidateBeforeCall(CustomerRequestBody body, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        com.squareup.okhttp.Call call = customersPostCall(body, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Creates a customer.
     * Creates a customer, returning the customer object.
     * @param body  (optional)
     * @return CreatedCustomerResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public CreatedCustomerResponse customersPost(CustomerRequestBody body) throws ApiException {
        ApiResponse<CreatedCustomerResponse> resp = customersPostWithHttpInfo(body);
        return resp.getData();
    }

    /**
     * Creates a customer.
     * Creates a customer, returning the customer object.
     * @param body  (optional)
     * @return ApiResponse&lt;CreatedCustomerResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<CreatedCustomerResponse> customersPostWithHttpInfo(CustomerRequestBody body) throws ApiException {
        com.squareup.okhttp.Call call = customersPostValidateBeforeCall(body, null, null);
        Type localVarReturnType = new TypeToken<CreatedCustomerResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Creates a customer. (asynchronously)
     * Creates a customer, returning the customer object.
     * @param body  (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call customersPostAsync(CustomerRequestBody body, final ApiCallback<CreatedCustomerResponse> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = customersPostValidateBeforeCall(body, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<CreatedCustomerResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
