package utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.bootcamp.credit.dto.CustomerDto;

import reactor.core.publisher.Mono;

@Component
public class WebClientUtilsService {

  private WebClient webClient;

  @Value("${spring.cloud.config.uri}")
  private String clientService;

  public WebClientUtilsService() {
    this.webClient = WebClient.create(clientService);
  }

  public WebClientUtilsService(WebClient webClient) {
    this.webClient = webClient;
  }

  public Mono<CustomerDto> getCustomerByDni(String dni) {

    return webClient.mutate().baseUrl(clientService).build().get()
        .uri("/{dni}", dni).retrieve().bodyToMono(CustomerDto.class)
        .onErrorResume(WebClientResponseException.class,
            ex -> ex.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(ex));
  }

}
