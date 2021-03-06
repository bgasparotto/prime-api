package com.bgasparotto.primeapi.controller;

import com.bgasparotto.primeapi.Main;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.Header;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MyResourceTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        Map<String, String> responseMsg = target.path("myresource").request().get(new GenericType<>() {
        });
        assertEquals("Got it!", responseMsg.get("value"));
    }

    @Test
    public void shouldReturnJsonType() {
        String contentType = target.path("myresource").request().get().getHeaderString(Header.ContentType.toString());
        assertEquals(MediaType.APPLICATION_JSON, contentType);
    }
}
