package com.example.accountapi.infrastructure.client.transaction.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class TransactionClientConfig {
  @Value("transaction-api.url")
  private String url;

  @Bean(name = "webClientTransaction")
  public WebClient webClient() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    ExchangeStrategies exchangeStrategies =
        ExchangeStrategies.builder()
            .codecs(
                clientCodecConfigurer -> {
                  clientCodecConfigurer
                      .defaultCodecs()
                      .jackson2JsonEncoder(
                          new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON));
                  clientCodecConfigurer
                      .defaultCodecs()
                      .jackson2JsonDecoder(
                          new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_JSON));
                })
            .build();

    return WebClient.builder()
        .baseUrl(UriComponentsBuilder.fromUriString(this.url).toUriString())
        .exchangeStrategies(exchangeStrategies)
        .build();
  }
}
