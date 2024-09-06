package com.tangrl.pan.web.validator;

import com.tangrl.pan.core.constants.RPanConstants;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * 统一的参数校验器
 */
// @SpringBootConfiguration 和 @Configuration 都用于定义配置类。
// 配置类是一个包含 @Bean 方法的类，这些方法返回的对象会被注册为 Spring 容器中的 Bean。
// @SpringBootConfiguration 是专门为 Spring Boot 应用设计的
@SpringBootConfiguration
@Slf4j
public class WebValidatorConfig {

    // 快速失败模式：遇到请求参数第一个校验错误时就立即返回，而不是继续校验其他参数。
    private static final String FAIL_FAST_KEY = "hibernate.validator.fail_fast";

    // @Bean 注解 的方法返回的对象回注册到 IOC 容器中
    // @Component 注解 会将类注册为bean，SpringBoot 会自动检测
    // @Component 注解 的类中方法可以通过 @Autowired 来注入使用 bean
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        // 在 MethodValidationPostProcessor 中 设置 Validator
        postProcessor.setValidator(rPanValidator());
        log.info("The hibernate validator is loaded successfully!");
        return postProcessor;
    }

    /**
     * 构造项目的方法参数校验器
     *
     * @return
     */
    private Validator rPanValidator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .addProperty(FAIL_FAST_KEY, RPanConstants.TRUE_STR)
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        return validator;
    }


}
