package io.openliberty.microprofile.telemetry20.logging.internal;

import java.util.Map;
import java.util.concurrent.ExecutorService;

import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.ibm.websphere.ras.Tr;
import com.ibm.websphere.ras.TraceComponent;
import com.ibm.ws.collector.Collector;
import com.ibm.ws.collector.Target;
import com.ibm.ws.logging.collector.CollectorJsonUtils;
import com.ibm.wsspi.collector.manager.Handler;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.sdk.autoconfigure.AutoConfiguredOpenTelemetrySdk;

@Component(name = OpenTelemetryHandler.COMPONENT_NAME, service = { Handler.class }, configurationPolicy = ConfigurationPolicy.OPTIONAL, property = { "service.vendor=IBM" })
public class OpenTelemetryHandler extends Collector {
    
    private static final TraceComponent tc = Tr.register(OpenTelemetryHandler.class);
    
    public static final String COMPONENT_NAME = "io.openliberty.microprofile.telemetry.2.0.logging.internal";
    
    private OpenTelemetry openTelemetry = null;
    
    @Override
    @Reference(name = EXECUTOR_SERVICE, service = ExecutorService.class)
    protected void setExecutorService(ServiceReference<ExecutorService> executorService) {
        executorServiceRef.setReference(executorService);
    }

    @Override
    protected void unsetExecutorService(ServiceReference<ExecutorService> executorService) {
        executorServiceRef.unsetReference(executorService);
    }
    
    @Override
    @Activate
    protected void activate(ComponentContext cc, Map<String, Object> configuration) {
        System.out.println("IN ACTIVATE!");
        this.openTelemetry = AutoConfiguredOpenTelemetrySdk.initialize().getOpenTelemetrySdk();
        System.out.println("OpenTelemetry: " + openTelemetry);
        //final OpenTelemetryInfo openTelemetryInfo = OpenTelemetryAccessor.getOpenTelemetryInfo();
        //super.activate(cc, configuration);
        //System.out.println("AFTER SUPER ACTIVATE");
        //validateSources(configuration);
    }
    
    @Override
    @Deactivate
    protected void deactivate(ComponentContext cc, int reason) {
        //super.deactivate(cc, reason);
    }
    
    @Override
    @Modified
    protected void modified(Map<String, Object> configuration) {
        super.modified(configuration);
        validateSources(configuration);
    }
    
    private void validateSources(Map<String, Object> config) {
        System.out.println("IN VALIDATE");
        if (config.containsKey(SOURCE_LIST_KEY)) {
            System.out.println("VALIDATE_CONFIG");
            String[] sourceList = (String[]) config.get(SOURCE_LIST_KEY);
            if (sourceList != null) {
                for (String source : sourceList) {
                    if (getSourceName(source.trim()).isEmpty()) {
                        Tr.warning(tc, "LOGSTASH_SOURCE_UNKNOWN", source);
                    }
                }
            }
        }
    }
    
    @Override
    public String getHandlerName() {
        return COMPONENT_NAME;
    }

    @Override
    public Object formatEvent(String source, String location, Object event, String[] tags, int maxFieldLength) {
        System.out.println("IN FORMATEVENT");
        String eventType = CollectorJsonUtils.getEventType(source, location);
        return null;
    }

    @Override
    public Target getTarget() {
        // TODO Auto-generated method stub
        return null;
    }

}
