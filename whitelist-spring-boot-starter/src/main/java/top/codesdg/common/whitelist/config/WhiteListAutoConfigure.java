package top.codesdg.common.whitelist.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.codesdg.common.whitelist.aop.WhiteListAspect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author shandeguo
 * @version V1.0
 * @date 2024/6/15 下午9:39
 * @description
 * @Copyright
 */

@Configuration
@ConditionalOnClass(WhiteListProperties.class)
@ConditionalOnProperty(prefix = "codesdg.whitelist", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(WhiteListProperties.class)
public class WhiteListAutoConfigure {

    @Bean("whiteListConfig")
    @ConditionalOnMissingBean
    public List<String> whiteListConfig(WhiteListProperties whiteListProperties) {
        return whiteListProperties.getUsers();
    }

    @Bean
    @ConditionalOnMissingBean
    public WhiteListAspect pointcut() {
        return new WhiteListAspect();
    }
}
