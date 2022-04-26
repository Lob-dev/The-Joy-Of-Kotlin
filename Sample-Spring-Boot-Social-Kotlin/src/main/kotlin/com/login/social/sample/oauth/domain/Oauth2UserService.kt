package com.login.social.sample.oauth.domain

import com.login.social.sample.account.domain.persistence.AccountEntity
import com.login.social.sample.account.domain.persistence.AccountRepository
import com.login.social.sample.oauth.constant.SocialType
import org.slf4j.LoggerFactory
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class Oauth2UserService(
    private val accountRepository: AccountRepository
) : DefaultOAuth2UserService() {
    private var log = LoggerFactory.getLogger(Oauth2UserService::class.java)

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        log.info(" accessToken : {}", userRequest.accessToken)
        log.info(" accessToken type : {}", userRequest.accessToken.tokenType.value)
        log.info(" accessToken value : {}", userRequest.accessToken.tokenValue)

        val loadUser = super.loadUser(userRequest)

        when (userRequest.clientRegistration.registrationId as String) {
            SocialType.KAKAO.registrationId -> {

                // 다른 필드도 주도록 설정한다면 엔티티 변환이 가능
                println(loadUser.attributes["id"])

                // val account = loadUser.attributes["kakao_account"]
                // val name = account.attributes["nickname"]
                // if (accountRepository.existsByUsernameLike(name)) return loadUser

                // else .... saved
                  // val savedEntity = accountRepository.save(AccountEntity.toEntity(loadUser))
                  // log.info("{}", savedEntity)
            }
            SocialType.GOOGLE.registrationId -> {
                TODO()
            }
            else -> throw RuntimeException()
        }
        return loadUser
    }
}
