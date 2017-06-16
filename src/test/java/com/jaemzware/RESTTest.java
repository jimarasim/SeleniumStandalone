package com.jaemzware;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * Created by jameskarasim on 6/16/17.
 */
public class RESTTest {

    //THIS TEST MAKES A REST REQUEST WITH THE HTTP CLIENT LIBRARIES FROM APACHE
    @Test
    public void GithubUsersResponseTest() throws Exception{
        String response = HttpGetReturnResponse("https://api.github.com/users/jimarasim");

        System.out.println(response);
    }

    protected String HttpGetReturnResponse(String url) throws Exception {
        // HTTPCLIENT/REST EXAMPLES
        // http://www.mkyong.com/java/apache-httpclient-examples/
        // http://www.mkyong.com/webservices/jax-rs/restful-java-client-with-apache-httpclient/

        // make the request
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);


        // read the reponse
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return result.toString();
    }

}
