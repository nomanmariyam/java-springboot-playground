package de.anshaana.playground.infrastructure.configuration;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

@Component
public class WebClientConfiguration {
    private WebClientSettings webClientSettings;

    public WebClientConfiguration(WebClientSettings webClientSettings) {
        this.webClientSettings = webClientSettings;
    }

    @Bean
    @Primary
    public WebClient webClient() {
        HttpClient httpClient = HttpClient.from(TcpClient.create().wiretap(webClientSettings.isWiretapEnabled())
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, webClientSettings.getConnectionTimeout())
            .doOnConnected(connection -> connection.addHandlerLast(new ReadTimeoutHandler(webClientSettings.getConnectionTimeout()))))
            .doOnRequest((httpClientRequest, connection) -> connection.addHandlerFirst(new WebClientLoggingHandler()))
            ;
        return  WebClient.builder()
            .exchangeStrategies(exchangeStrategies())
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .build();
    }
    private ExchangeStrategies exchangeStrategies() {
        return ExchangeStrategies.builder()
            .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(webClientSettings.getBufferSizeInByte()))
            .build();
    }
}
