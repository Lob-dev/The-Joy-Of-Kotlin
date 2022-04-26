package com.login.social.sample.oauth.configuration

import com.login.social.sample.oauth.constant.SocialType
import com.login.social.sample.oauth.constant.SocialType.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod

/*
    base repo : https://github.com/cheese10yun/springboot-oauth2
 */
@Configuration
@EnableWebSecurity
class OauthSecurityConfiguration(
    private val googleOauth: GoogleOauthResources,
    private val kakaoOauth: KakaoOauthResources
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http?.run {
            authorizeRequests { it
                .antMatchers("/login", "/index").permitAll()
                .anyRequest().authenticated()
            }.oauth2Login { it
                .clientRegistrationRepository(clientRegistrationRepository())
                .authorizedClientService(authorizedClientService())
            }
        }
    }

    @Bean
    fun authorizedClientService() = InMemoryOAuth2AuthorizedClientService(
        clientRegistrationRepository()
    )

    @Bean
    fun clientRegistrationRepository() = InMemoryClientRegistrationRepository(
        googleClientRegistration(), kakaoClientRegistration()
    )

    private fun googleClientRegistration() = CommonOAuth2Provider.GOOGLE
        .getBuilder(GOOGLE.registrationId)
        .clientId(googleOauth.clientId)
        .clientSecret(googleOauth.clientSecret)
        .build()

    private fun kakaoClientRegistration() = ClientRegistration.withRegistrationId(KAKAO.registrationId)
        .clientId(kakaoOauth.clientId)
        .clientSecret(kakaoOauth.clientSecret)
        .tokenUri(kakaoOauth.tokenUri)
        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
        .authorizationUri(kakaoOauth.authorizationUri)
        .redirectUri(kakaoOauth.redirectUri)
        .userInfoUri(kakaoOauth.userInfoUri)
        .userNameAttributeName("id")
        .build()
}
