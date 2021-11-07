//package Board;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import org.apache.commons.io.IOUtils;
//import org.apache.http.HttpHost;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.HttpClientBuilder;
//
//public class RestClient {
//
//    public String callService() {
//
//        /* TARGET URL AND JSON */
//        String url = "<<base url>>/ads.restapi/v1/adsRender/pdf";
//        String json = "<<your json (please find the Example JSON in the content section)>>";
//
//        /* HTTPCLIENT AND HTTPPOST OOBJECT */
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        HttpPost request = new HttpPost(url);
//
//        /* ADD HEADER INFO */
//        request.addHeader("authorization", "Bearer <<token>>");
//        request.addHeader("content-type", "application/json");
//
//        /* PROXY CONFIG */
//        HttpHost target = new HttpHost("proxy", 8080, "http");
//        RequestConfig config = RequestConfig.custom().setProxy(target).build();
//        request.setConfig(config);
//
//        /* JSON AS STRINGENTITY */
//        StringEntity input = null;
//        try {
//            input = new StringEntity(json);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        request.setEntity(input);
//
//        /* SEND AND RETRIEVE RESPONSE */
//        HttpResponse response = null;
//        try {
//            response = httpClient.execute(request);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        /* RESPONSE AS JSON STRING */
//        String result = null;
//        try {
//            result = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    public static void main(String[] args) {
//        RestClient restClient = new RestClient();
//        String response = restClient.callService();
//        System.out.println(response);
//    }
//}
