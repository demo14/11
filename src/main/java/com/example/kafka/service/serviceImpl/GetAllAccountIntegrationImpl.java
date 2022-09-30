package com.example.kafka.service.serviceImpl;

import com.example.kafka.dto.AccountDto;
import com.example.kafka.service.GetAllAccountIntegration;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllAccountIntegrationImpl implements GetAllAccountIntegration {

    private static final String url = "http://localhost:8080/v";

    private WebClient client;

    @PostConstruct
    public void init() {
        var timeoutClient = TcpClient.create();
        var mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        var strategies = ExchangeStrategies
                .builder()
                .codecs(clientDefaultCodecsConfigurer -> {
                    clientDefaultCodecsConfigurer
                            .defaultCodecs()
                            .jackson2JsonEncoder(new Jackson2JsonEncoder(mapper, MediaType.APPLICATION_JSON));
                    clientDefaultCodecsConfigurer
                            .defaultCodecs()
                            .jackson2JsonDecoder(new Jackson2JsonDecoder(mapper, MediaType.APPLICATION_JSON));
                    clientDefaultCodecsConfigurer.defaultCodecs().maxInMemorySize(1024 * 1024);
                })
                .build();

        client = WebClient.builder().exchangeStrategies(strategies)
                .baseUrl(url)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(timeoutClient).followRedirect(true))).build();
    }

    @Override
    public List<AccountDto> getAllAccount() throws ExecutionException, InterruptedException {
        var response = client.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/all")
                                .build())
                .exchange()
                .toFuture()
                .get();


        log.info("Account {}",response);
        return Objects.requireNonNull(response.bodyToFlux(AccountDto.class).collectList().toProcessor().block());

    }
}
