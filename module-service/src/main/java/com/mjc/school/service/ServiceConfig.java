package com.mjc.school.service;

import com.mjc.school.repository.RepositoryConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import(RepositoryConfig.class)
public class ServiceConfig {
}
