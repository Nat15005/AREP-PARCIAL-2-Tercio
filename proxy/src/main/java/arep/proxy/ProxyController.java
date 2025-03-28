package arep.proxy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class ProxyController {
     private final List<String> mathServices = Arrays.asList(
             System.getenv().getOrDefault("MATH_SERVICE_1", "http://localhost:8081"),
             System.getenv().getOrDefault("MATH_SERVICE_2", "http://localhost:8082")

     );

    private Integer currentIndex = 0;
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/factors")
    public Map<String, Object> proxyFactors(
            @RequestParam("value") String value) {
        String serviceUrl = getNextServiceUrl() + "/factors";
        String url = String.format("%s?value=%s", serviceUrl, value);
        return restTemplate.getForObject(url, Map.class);
    }

    @GetMapping("/primes")
    public Map<String, Object> proxyPrimes(
            @RequestParam("value") String value) {
        String serviceUrl = getNextServiceUrl() + "/primes";
        String url = String.format("%s?value=%s", serviceUrl, value);
        return restTemplate.getForObject(url, Map.class);
    }

    private String getNextServiceUrl() {
        String serviceUrl = mathServices.get(currentIndex);
        currentIndex = (currentIndex + 1) % 2;
        return serviceUrl;
    }


}


