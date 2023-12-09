package com.mjc.school.controller;

import com.mjc.school.service.ServiceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import(ServiceConfig.class)
public class WebConfig {
}
