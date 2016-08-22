package com.games.demo.connect4.application;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.games.demo.connect4.resource.Connect4Resource;

/**
 * 
 * @author Kovalan
 * 
 * Application launcher that starts the server and deploys the resources to the server
 *
 */
public class Connect4Server extends
		Application<Connect4Configuration> {
	public static void main(String[] args) throws Exception {
		new Connect4Server().run( "server" , "src/main/resources/config.yml");
	}

	@Override
	public void run(Connect4Configuration config, Environment env)
			throws Exception {
		// message consumer endpoint
		Connect4Resource.REQUEST_TIME_OUT = Integer.parseInt(config.getRequestTimeOut());
		env.jersey().register(Connect4Resource.class);
	}
	
	@Override
	public void initialize(Bootstrap<Connect4Configuration> bootstrap) {
		super.initialize(bootstrap);
	}
	
}
