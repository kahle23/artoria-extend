package artoria.data.desensitize.support;

import artoria.logging.Logger;
import artoria.logging.LoggerFactory;
import org.junit.Test;

public class BankCardNumberMaskerTest {
    private static final Logger log = LoggerFactory.getLogger(BankCardNumberMaskerTest.class);
    private static final BankCardNumberMasker bankCardNumberMasker = new BankCardNumberMasker();

    @Test
    public void test1() {
        log.info("{}", bankCardNumberMasker.mask("6228482898203884775"));
        log.info("{}", bankCardNumberMasker.mask("6228480010594620212"));
        log.info("{}", bankCardNumberMasker.mask("9559980210373015416"));
    }

}
