package artoria.data.desensitize.support;

import artoria.data.desensitize.DataMaskUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

/**
 * Data mask auto configuration.
 * @author Kahle
 * TODO: 2023/6/2 Deletable
 */
@Deprecated
@Configuration
public class DataMaskAutoConfiguration implements InitializingBean, DisposableBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        DataMaskUtils.register("PhoneNumber", new PhoneNumberMasker());
        DataMaskUtils.register("WithPhoneNumber", new WithPhoneNumberMasker());
        DataMaskUtils.register("BankCardNumber", new BankCardNumberMasker());
    }

    @Override
    public void destroy() throws Exception {
    }

}
