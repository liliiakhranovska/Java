//package Board;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//
//public class Test2 {
//    public static void main(String[] args) throws IOException {
//        HttpClient client = HttpClientBuilder.create().build();
//        String L = "GYYSUU";
//        HttpGet get = new HttpGet("http://localhost:8080/?gamestate=" + L);
//        HttpResponse response = client.execute(get);
////        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//    }
//}
//
