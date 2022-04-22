package com.soma.lightening.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean(name = "githubWebClient")
    public WebClient githubWebClient() {
        return WebClient.create("https://github.com");
    }
    @Bean(name = "apiGithubWebClient")
    public WebClient apiGithubWebClient() {
        return WebClient.create("https://api.github.com");
    }
}

