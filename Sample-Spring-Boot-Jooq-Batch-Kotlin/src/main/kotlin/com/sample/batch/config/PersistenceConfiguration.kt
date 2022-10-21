package com.sample.batch.config

import com.sample.batch.jooq.SlowQueryListener
import org.jooq.ConnectionProvider
import org.jooq.SQLDialect
import org.jooq.impl.*
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer
import org.springframework.boot.autoconfigure.batch.BatchDataSourceScriptDatabaseInitializer
import org.springframework.boot.autoconfigure.batch.BatchProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
import javax.sql.DataSource

@Configuration
class PersistenceConfiguration {

    @Bean
    protected fun query(configuration: DefaultConfiguration): DefaultDSLContext =
        DefaultDSLContext(configuration)

    @Bean
    protected fun configuration(connectionProvider: ConnectionProvider): DefaultConfiguration =
        DefaultConfiguration().apply {
            set(connectionProvider)
            set(SQLDialect.MARIADB)
            set(DefaultRecordListenerProvider(DefaultRecordListener()))
            set(DefaultExecuteListenerProvider(SlowQueryListener()))
        }

    @Bean
    protected fun connectionProvider(dataSource: DataSource): DataSourceConnectionProvider =
        DataSourceConnectionProvider(TransactionAwareDataSourceProxy(dataSource))

    @Bean
    protected fun defaultBatchConfigurer(dataSource: DataSource): DefaultBatchConfigurer =
        DefaultBatchConfigurer(dataSource)

    @Bean
    protected fun batchDataSourceInitializer(
        dataSource: DataSource,
        properties: BatchProperties,
    ): BatchDataSourceScriptDatabaseInitializer =
        BatchDataSourceScriptDatabaseInitializer(dataSource, properties.jdbc)
}