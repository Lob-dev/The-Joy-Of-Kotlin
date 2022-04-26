package lob.study.kotlin.global.configuration

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement


@Configuration
@EntityScan("lob.study.kotlin")
@EnableJpaRepositories("lob.study.kotlin")
@EnableTransactionManagement
class DomainConfig
