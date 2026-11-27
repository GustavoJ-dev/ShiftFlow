package com.gestaoescalas.schedule.client;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;

public class EmployeeClientTest {

    private EmployeeClient employeeClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setUp(){

        RestClient.Builder builder = RestClient
                .builder()
                .baseUrl("http://localhost:8081");

        mockServer = MockRestServiceServer
                .bindTo(builder)
                .build();


        employeeClient = new EmployeeClient(builder.build());
    }

    @Test
    void shouldReturnTrueWhenEmployeeIsActive(){

        mockServer.expect(
                requestTo("http://localhost:8081/internal/employees/1/active")
        ).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(
                            """
                            {"active": true}
                            """,
                            MediaType.APPLICATION_JSON
                           )
                );

        boolean result = employeeClient.isEmployeeActive(1L);

        assertTrue(result);

        mockServer.verify();
    }

    @Test
    void shouldReturnFalseWhenEmployeeIsNotActive(){

        mockServer.expect(
                requestTo("http://localhost:8081/internal/employees/2/active")
        ).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(
                        """
                        {"active": false}
                        """,
                        MediaType.APPLICATION_JSON
                        )
                );

        boolean result = employeeClient.isEmployeeActive(2L);

        assertFalse(result);

        mockServer.verify();
    }

    @Test
    void shouldReturnFalseWhenResponseBodyIsNull(){

        mockServer.expect(
                requestTo("http://localhost:8081/internal/employees/3/active")
        ).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess());

        boolean result = employeeClient.isEmployeeActive(3L);

        assertFalse(result);
        mockServer.verify();
    }

    @Test
    void shouldThrowExceptionWhenAuthServiceReturnsServerError(){

        mockServer.expect(
                requestTo("http://localhost:8081/internal/employees/4/active")
        ).andExpect(method(HttpMethod.GET))
                .andRespond(withServerError());

        assertThrows(RestClientException.class,
                ()-> employeeClient.isEmployeeActive(4L));

        mockServer.verify();
    }
}
