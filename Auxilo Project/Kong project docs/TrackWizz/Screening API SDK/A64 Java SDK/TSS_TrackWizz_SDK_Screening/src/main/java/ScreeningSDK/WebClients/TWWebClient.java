package ScreeningSDK.WebClients;

import ch.qos.logback.core.net.ssl.SSLContextFactoryBean;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class TWWebClient {

    public SSLContext trustSelfSignedSSL() {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {

                public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init(null, new TrustManager[] { tm }, null);
            SSLContext.setDefault(ctx);
            return ctx;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String WebClientPost(String data, String url) throws NoSuchAlgorithmException {

        HttpResponse<String> postResponse = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .timeout(Duration.ofMinutes(Integer.MAX_VALUE))
                    .setHeader("Accept","application/xml")
                    .setHeader("Content-Type","application/xml")
                    .POST(HttpRequest.BodyPublishers.ofString(data))
                    .build();

            postResponse = HttpClient.newBuilder()
                    .sslContext(trustSelfSignedSSL())
                    .connectTimeout(Duration.ofMinutes(Integer.MAX_VALUE))
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

        }catch (Exception e){
            e.printStackTrace();
        }
        return postResponse.body();
    }

    public CompletableFuture<HttpResponse<String>> WebClientPostAsync(String data, String url){
        CompletableFuture<HttpResponse<String>> postResponse = null;

        System.out.println("Async call made");
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://mlapidemo1.tssconsultancy.com:54322/crmapi/a64screeningapi/GetScreeningResult"))
                    .timeout(Duration.ofMinutes(Integer.MAX_VALUE))
                    .setHeader("Accept","application/xml")
                    .setHeader("Content-Type","application/xml")
                    .POST(HttpRequest.BodyPublishers.ofString(data))
                    .build();

             postResponse = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofMinutes(Integer.MAX_VALUE))
                    .build()
                    .sendAsync(request, HttpResponse.BodyHandlers.ofString());

        }catch (Exception e){
            e.printStackTrace();
        }
        return postResponse;
    }

}
