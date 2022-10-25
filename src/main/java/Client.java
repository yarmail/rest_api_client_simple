import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Client {

    /**
     * Пример GET запроса к сервису reqres.in
     * Запрос: api/users?page=2
     *
     * ObjectMapper mapper = new ObjectMapper();
     * начинаем парсить ответ с помощью Jackson
     *
     */
    private void GetExample() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url="https://reqres.in/api/users?page=2";
        String response = restTemplate.getForObject(url, String.class);
        System.out.println("Вывод всего ответа сервиса:");
        System.out.println(response);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode obj = mapper.readTree(response);
        System.out.println("Вывод каких-то выборочных элементов:");
        System.out.println(obj.get("data").get(0).get("first_name"));
        System.out.println(obj.get("data").get(0).get("last_name"));
    }

    /**
     *  Пример POST запроса к сервису reqres.in
     *  Адрес запроса: /api/users
     *  Отправляем запрос с json
     *  {
     *     "name": "morpheus",
     *     "job": "leader"
     * }
     *
     * ObjectMapper mapper = new ObjectMapper();
     * начинаем парсить ответ с помощью Jackson
     */
    private void PostExample() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> jsonToSend = new HashMap<>();
        jsonToSend.put("name", "morpheus");
        jsonToSend.put("job", "leader");
        HttpEntity<Map<String, String>> request =  new HttpEntity<>(jsonToSend);
        String url="https://reqres.in/api/users/";
        String response = restTemplate.postForObject(url, request, String.class);
        System.out.println("Вывод всего ответа сервиса:");
        System.out.println(response);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode obj = mapper.readTree(response);
        System.out.println("Вывод каких-то выборочных элементов:");
        System.out.println(obj.get("name"));
        System.out.println(obj.get("job"));
    }

    public static void main(String[] args) throws JsonProcessingException {
        Client client = new Client();
        client.GetExample();
        client.PostExample();
    }
}