package com.techaas.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "links")
class LinksConfig {
    lateinit var checkToken: String
    lateinit var checkUrl: String
    lateinit var checkDate: String
}