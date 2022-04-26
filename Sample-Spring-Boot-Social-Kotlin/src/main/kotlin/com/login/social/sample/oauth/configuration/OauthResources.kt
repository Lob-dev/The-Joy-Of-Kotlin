package com.login.social.sample.oauth.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("oauth2.registration.kakao")
data class KakaoOauthResources (
    val clientId: String,
    val clientSecret: String,
    val tokenUri: String,
    val authorizationUri: String,
    val redirectUri: String,
    val userInfoUri: String
)

@ConstructorBinding
@ConfigurationProperties("oauth2.registration.google")
data class GoogleOauthResources (
    val clientId: String,
    val clientSecret: String
)

