package com.jobfinder.infrastructure.offer.http;

import com.jobfinder.domain.offer.OfferFetchable;
import io.netty.channel.ChannelOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class OfferFetcherConfig {

    @Bean
    public OfferFetchable remoteOfferClient(
            @Value("${offer.http.client.uri}") String uri,
            @Value("${offer.http.client.port}") int port,
            @Value("${offer.http.client.connectionTimeout}") int connectionTimeout,
            @Value("${offer.http.client.readTimeout}") int readTimeout) {
        return new OffersHttpClient(buildWebClient(uri, port, connectionTimeout, readTimeout));
    }

    protected WebClient buildWebClient(String uri, int port, int connectionTimeout, int readTimeout) {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeout)
                .responseTimeout(Duration.ofMillis(readTimeout));
        return WebClient.builder()
                .baseUrl(uri + ":" + port)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
