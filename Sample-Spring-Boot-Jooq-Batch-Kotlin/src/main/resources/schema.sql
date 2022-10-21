CREATE DATABASE IF NOT EXISTS `sample` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

use sample;

CREATE TABLE `sample_job`
(
    `id`   bigint(20)  NOT NULL        AUTO_INCREMENT,
    `is_success`       boolean DEFAULT FALSE NOT NULL,
    `create_date`      datetime(6)           NOT NULL,
    `update_date`      datetime(6)           NOT NULL,
    PRIMARY KEY (`id`),
    KEY `IDX_POINT_ACTIVITY_USER_ID_EXPIRE_AT_POINT_STATUS` (`create_date`, `is_success`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO sample_job(create_date, update_date) VALUES (now(), now())