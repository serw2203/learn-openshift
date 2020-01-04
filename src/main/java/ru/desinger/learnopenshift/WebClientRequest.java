package ru.desinger.learnopenshift;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;

public class WebClientRequest {

    public static void localhost() throws SSLException {
        SslContext sslContext = SslContextBuilder.forClient()
                ///.trustManager(InsecureTrustManagerFactory.INSTANCE)
                .trustManager(WebClientRequest.class.getResourceAsStream("/keystore/guest.crt"))
                .build();

        HttpClient httpClient = HttpClient.create()
                .secure(sslContextSpec -> sslContextSpec.sslContext(sslContext));

        ClientHttpConnector httpConnector = new ReactorClientHttpConnector(httpClient);

        WebClient client = WebClient.builder().clientConnector(httpConnector)
                .baseUrl("https://localhost:8585")
                .build();

        Mono<String> resp = client.get().uri("/main/hello")
                .retrieve()
                .bodyToMono(String.class);

        System.out.println(resp.block());
    }

    public static void remote() throws SSLException {
        SslContext sslContext = SslContextBuilder.forClient()
                ///.trustManager(InsecureTrustManagerFactory.INSTANCE)
                .trustManager(WebClientRequest.class.getResourceAsStream("/keystore/guest1.crt"))
                .build();

        HttpClient httpClient = HttpClient.create()
                .secure(sslContextSpec -> sslContextSpec.sslContext(sslContext));

        ClientHttpConnector httpConnector = new ReactorClientHttpConnector(httpClient);

        WebClient client = WebClient.builder().clientConnector(httpConnector)
                .baseUrl("https://192.168.55.101:8585")
                .build();

        Mono<String> resp = client.post().uri("/main/hello-post")
                .retrieve()
                .bodyToMono(String.class);

        System.out.println(resp.block());
    }

    public static void main(String[] args) throws SSLException {
        remote();
    }
}
