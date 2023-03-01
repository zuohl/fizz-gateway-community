/*
 *  Copyright (C) 2020 the original author or authors.
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.fizzgate.config;

import com.fizzgate.proxy.FizzWebClient;
import com.fizzgate.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import com.fizzgate.config.WebClientConfig;

/**
 * @author hongqiaowei
 */

@Configuration
@ConfigurationProperties(prefix = ProxyWebClientConfig.prefix)
public class ProxyWebClientConfig extends WebClientConfig {


    private String httpProxyUrl = "";

    public String getHttpProxyUrl() {
        return httpProxyUrl;
    }

    public void setHttpProxyUrl(String httpProxyUrl) {
        this.httpProxyUrl = httpProxyUrl;
        if (StringUtils.isNotBlank(httpProxyUrl)) {
            FizzWebClient.setWebProxyClient(super.webClient(httpProxyUrl));
        }
    }
    protected static final String prefix         = "proxy-webclient";

    public static final String proxyWebClient = "proxyWebClient";

    @Bean(proxyWebClient)
    public WebClient webClient() {
        log.info("proxy web client: {}", this);
        return webClient("");
    }

    public WebClient getWebProxyClient() {
        log.info("proxy web client: {}", this);
        return super.webClient("");
    }
}
