package kunlun.action.support.data.fill;

import kunlun.action.support.AutoActionHandler;
import kunlun.util.Assert;

public abstract class AbstractAutoSingleFieldFillHandler
        extends AbstractSingleFieldFillHandler implements AutoActionHandler {
    private final HandlerConfigImpl handlerConfig;
    private final String actionName;

    public AbstractAutoSingleFieldFillHandler(String actionName) {

        this(actionName, new HandlerConfigImpl());
    }

    public AbstractAutoSingleFieldFillHandler(String actionName, HandlerConfigImpl handlerConfig) {
        Assert.notNull(handlerConfig, "Parameter \"handlerConfig\" must not null. ");
        Assert.notBlank(actionName, "Parameter \"actionName\" must not blank. ");
        this.handlerConfig = handlerConfig;
        this.actionName = actionName;
    }

    @Override
    public String getName() {

        return actionName;
    }

    @Override
    public HandlerConfig getConfig() {

        return handlerConfig;
    }

}
