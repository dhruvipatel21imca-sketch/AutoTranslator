# DefaultApi

All URIs are relative to *https://AutoTranslator*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**translate**](DefaultApi.md#translate) | **GET** /api/translate | GET api/translate |


<a id="translate"></a>
# **translate**
> String translate(text, source, target)

GET api/translate

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://AutoTranslator");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String text = "text_example"; // String | 
    String source = "source_example"; // String | 
    String target = "target_example"; // String | 
    try {
      String result = apiInstance.translate(text, source, target);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#translate");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **text** | **String**|  | |
| **source** | **String**|  | |
| **target** | **String**|  | |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

