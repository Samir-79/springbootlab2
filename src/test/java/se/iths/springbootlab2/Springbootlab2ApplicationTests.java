package se.iths.springbootlab2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;
import se.iths.springbootlab2.dtos.BooksDto;

import se.iths.springbootlab2.entities.Författare;

import java.net.URI;
import java.sql.Date;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Springbootlab2ApplicationTests {
    Författare författare;

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testClient;

    @Test
    void contextLoads() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/xml");

        var result = testClient.getForEntity("http://localhost:" + port + "/Böcker/", BooksDto[].class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(result.getBody()).length).isGreaterThan(0);


    }


    @Test
    void postSomethingToService() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/xml");
        //testClient.exchange("localhost:8080/person", HttpMethod.GET, new HttpEntity<>(headers), PersonDto[].class);
        BooksDto booksDto = new BooksDto("1976543219877", "TestTitel", "TestSpråk", 0.0, Date.valueOf("2019-01-10"), författare);
        ;

        var result = testClient.postForEntity("http://localhost:" + port + "/Böcker/", booksDto, BooksDto.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        String resultISBN13 = Objects.requireNonNull(result.getBody()).getISBN13();

        var verifyPostQuery = testClient.getForEntity("http://localhost:" + port + "/Böcker/" + resultISBN13 + "/", BooksDto.class);

        assertThat(verifyPostQuery.getStatusCode()).isEqualTo(HttpStatus.OK);


    }


    @Test
    void deleteAndVerify() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/xml");
        //testClient.exchange("localhost:8080/person", HttpMethod.GET, new HttpEntity<>(headers), PersonDto[].class);
        BooksDto booksDto = new BooksDto("9999999999999", "TestTitel7", "TestSpråk7", 0.0, Date.valueOf("2021-02-10"), författare);

        var result = testClient.postForEntity("http://localhost:" + port + "/Böcker/", booksDto, BooksDto.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        String resultISBN13 = Objects.requireNonNull(result.getBody()).getISBN13();

        var verifyPostQuery = testClient.getForEntity("http://localhost:" + port + "/Böcker/" + resultISBN13 + "/", BooksDto.class);

        assertThat(verifyPostQuery.getStatusCode()).isEqualTo(HttpStatus.OK);

        testClient.delete("http://localhost:" + port + "/Böcker/" + "9999999999999" + "/", booksDto, BooksDto.class);


        var verifyDeleteQuery = testClient.getForEntity("http://localhost:" + port + "/Böcker/" + "9999999999999" + "/", BooksDto.class);

        assertThat(verifyDeleteQuery.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);


    }

    @Test
    void changeAndVerify() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/xml");
        //testClient.exchange("localhost:8080/person", HttpMethod.GET, new HttpEntity<>(headers), PersonDto[].class);
        BooksDto booksDto = new BooksDto("9999999999998", "TestTitel8", "TestSpråk8", 0.0, Date.valueOf("2021-03-10"), författare);

        var result = testClient.postForEntity("http://localhost:" + port + "/Böcker/", booksDto, BooksDto.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        String resultISBN13 = Objects.requireNonNull(result.getBody()).getISBN13();
        testClient.put("http://localhost:" + port + "/Böcker/" + resultISBN13, new BooksDto("9999999999998", "NewTestTitel", "NewTestSpråk", 1.0, Date.valueOf("2021-04-10"), författare), BooksDto.class);
        //assertThat(verifyPutQuery.getStatusCode()).isEqualTo(HttpStatus.OK);
        var verifyPutResult = testClient.getForEntity("http://localhost:" + port + "/Böcker/" + "9999999999998" + "/", BooksDto.class);

        assertThat(verifyPutResult.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateAndVerify() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/xml");
        //testClient.exchange("localhost:8080/person", HttpMethod.GET, new HttpEntity<>(headers), PersonDto[].class);
        BooksDto booksDto = new BooksDto("9999999998888", "TestTitel9", "TestSpråk9", 3.0, Date.valueOf("2021-07-11"), författare);

        var result = testClient.postForEntity("http://localhost:" + port + "/Böcker/", booksDto, BooksDto.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        String resultISBN13 = Objects.requireNonNull(result.getBody()).getISBN13();
        //testClient.patchForObject("http://localhost:" + port + "/Böcker" + "/9999999999988",new BooksDto("9999999999988","NewTestTitel","TestSpråk9",0.0,Date.valueOf("2021-07-11"),författare),BooksDto.class);
        testClient.patchForObject("http://localhost:" + port + "/Böcker" + "/9999999998888", new BooksDto("9999999998888", "TestPatchTitel9", "TestSpråk9", 3.0, Date.valueOf("2021-07-11"), författare), BooksDto.class);


        var verifyPutResult = testClient.getForEntity("http://localhost:" + port + "/Böcker/" + "9999999998888", BooksDto.class);

        assertThat(verifyPutResult.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


//    @Test
//    void searchAndVerify() {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept", "applications/xml");
//        BooksDto booksDto = new BooksDto("1111111111111", "TestTitel10", "TestSpråk10", 5.0, Date.valueOf("2021-08-11"), författare);
//        var post1 = testClient.postForEntity("http://localhost:" + port + "/Böcker/", booksDto, BooksDto.class);
//        assertThat(post1.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/Böcker").path("/foos")
//                .queryParam("isbn13", "1111111111111").build().toUri();
//        var result1 = testClient.getForEntity(uri, BooksDto.class);
//        //var result1= testClient.getForEntity("http://localhost:"+port+"/Böcker/foos?isbn13=1111111111111",BooksDto.class);
//        assertThat(result1.getStatusCode()).isEqualTo(HttpStatus.OK);
//        //assertThatObject(result1).isEqualTo(post1);
//
//    }

}





