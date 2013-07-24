package com.archij.sample;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ClientCoookiesTest extends AbstractHandler {
	Server server;
	Cookie [] cookies;
	
	@Before
	public void setUp() throws Exception {
		server = new Server(9090);
        server.setHandler(this);
        server.start();
	}

	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	@Test
	public void test() {
		new ClientCoookies().run();
		assertThat(cookies, notNullValue());
		assertThat(cookies,hasItemInArray(hasProperty("value", equalTo("Tim"))));
	}

	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        cookies=request.getCookies();
        baseRequest.setHandled(true);
        response.getWriter().println("<h1>Hello World</h1>");
	}

}
