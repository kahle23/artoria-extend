package artoria.aop.support;

import artoria.aop.Enhancer;
import artoria.aop.ProxyFactory;
import net.sf.cglib.proxy.MethodInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The cglib aop auto configuration.
 * @author Kahle
 */
@Configuration
@ConditionalOnClass({MethodInterceptor.class})
@AutoConfigureAfter({SpringCglibAopAutoConfiguration.class})
public class CglibAopAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(CglibAopAutoConfiguration.class);

    @Bean
    @ConditionalOnMissingBean
    public ProxyFactory proxyFactory() {
        ProxyFactory proxyFactory = new CglibProxyFactory();
        Enhancer.setProxyFactory(proxyFactory);
        log.info("The cglib proxy factory was initialized success. ");
        return proxyFactory;
    }

}
