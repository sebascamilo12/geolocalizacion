package co.com.reto.consumer;


import co.com.reto.consumer.serviceipapi.RestConsumerIpApi;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;

import java.io.IOException;


public class RestConsumerTest {

    private static RestConsumerIpApi restConsumerIpApi;
    private static MockWebServer mockBackEnd;


//    @BeforeAll
//    static void setUp() throws IOException {
//        mockBackEnd = new MockWebServer();
//        mockBackEnd.start();
//        var webClient = WebClient.builder().baseUrl(mockBackEnd.url("/").toString()).build();
//        restConsumer = new RestConsumer(webClient);
//    }

    @AfterAll
    static void tearDown() throws IOException {

        mockBackEnd.shutdown();
    }

//    @Test
//    @DisplayName("Validate the function testGet.")
//    void validateTestGet() {
//
//        mockBackEnd.enqueue(new MockResponse()
//                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .setResponseCode(HttpStatus.OK.value())
//                .setBody("{\"state\" : \"ok\"}"));
//        var response = restConsumer.testGet();
//
//        StepVerifier.create(response)
//                .expectNextMatches(objectResponse -> objectResponse.getState().equals("ok"))
//                .verifyComplete();
//    }
//
//    @Test
//    @DisplayName("Validate the function testPost.")
//    void validateTestPost() {
//
//        mockBackEnd.enqueue(new MockResponse()
//                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .setResponseCode(HttpStatus.OK.value())
//                .setBody("{\"state\" : \"ok\"}"));
//        var response = restConsumer.testPost();
//
//        StepVerifier.create(response)
//                .expectNextMatches(objectResponse -> objectResponse.getState().equals("ok"))
//                .verifyComplete();
//    }
}