package com.green.jpaexam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//이걸 안하면 creaAt, updadAt에 값이 안들어감
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfiguration {
}
