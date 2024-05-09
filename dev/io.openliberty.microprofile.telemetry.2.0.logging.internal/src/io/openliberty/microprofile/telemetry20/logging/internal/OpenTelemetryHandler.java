package io.openliberty.microprofile.telemetry20.logging.internal;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.logging.LogRecord;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.util.tracker.ServiceTracker;

import com.ibm.websphere.ras.Tr;
import com.ibm.websphere.ras.TraceComponent;
import com.ibm.ws.collector.Collector;
import com.ibm.ws.collector.Target;
import com.ibm.ws.logging.collector.CollectorJsonUtils;
import com.ibm.wsspi.collector.manager.Handler;

import io.openliberty.microprofile.telemetry.internal.common.info.OpenTelemetryInfo;
import io.openliberty.microprofile.telemetry.internal.interfaces.OpenTelemetryAccessor;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.logs.LogRecordBuilder;
import io.opentelemetry.api.logs.LoggerProvider;
import io.opentelemetry.sdk.autoconfigure.AutoConfiguredOpenTelemetrySdk;
import io.opentelemetry.sdk.autoconfigure.AutoConfiguredOpenTelemetrySdkBuilder;
import io.opentelemetry.sdk.logs.export.LogRecordExporter;
import io.opentelemetry.api.GlobalOpenTelemetry;
//import io.opentelemetry.exporter.logging.*;

@Component(name = OpenTelemetryHandler.COMPONENT_NAME, service = {
		Handler.class }, configurationPolicy = ConfigurationPolicy.OPTIONAL, property = { "service.vendor=IBM" })
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
		
		
		
		//ClassLoader otelClassLoader = openTelemetry.getClass().getClassLoader();
//
//		try {
//			Class<?> test = Class.forName("io.opentelemetry.exporter.otlp.logs.OtlpGrpcLogRecordExporter",false, otelClassLoader);
//			System.out.println("Otel classloader: " + test);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//ClassLoader newClassLoader = cc.getBundleContext().getClass().getClassLoader();
		//ClassLoader newClassLoader = Thread.currentThread().getContextClassLoader();
		//ClassLoader newClassLoader = this.getClass().getClassLoader();
				
		ClassLoader newClassLoader = OpenTelemetry.noop().getClass().getClassLoader();
		
		try {
			Class<?> test = Class.forName("io.opentelemetry.exporter.otlp.logs.OtlpGrpcLogRecordExporter",false, newClassLoader);
			System.out.println("Otel classloader: " + test);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//OpenTelemetryInfo openTelemetryInfo = OpenTelemetryAccessor.setServerOpenTelemetryInfo(newClassLoader);
		
		OpenTelemetryInfo openTelemetryInfo = OpenTelemetryAccessor.getServerOpenTelemetryInfo(newClassLoader);
		System.out.println("openTelemetryInfo: " + openTelemetryInfo);
        OpenTelemetry openTelemetry = openTelemetryInfo.getOpenTelemetry();
        System.out.println("OpenTelemetry: " + openTelemetry);
        
//		ClassLoader otelClassLoader2 = OpenTelemetry.noop().getClass().getClassLoader();

		
		//System.out.println("Otel class: " + otelClassLoader2.getName());
//		try {
//			Class<?> test = Class.forName("io.opentelemetry.exporter.otlp.logs.OtlpGrpcLogRecordExporter",false, otelClassLoader2);
//			System.out.println("Otel classloader: " + test);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
        
        
//        
//       LogRecordBuilder builder = openTelemetry.getLogsBridge().loggerBuilder("io.openliberty.microprofile.telemetry").build().logRecordBuilder();
//        
//       
//       
//        LoggerProvider logProvider = openTelemetry.getLogsBridge();
//		
//
//		
//		

//		Thread.currentThread().getContextClassLoader().toString();
//		
//		AutoConfiguredOpenTelemetrySdk.initialize();
		
		
		
		
//		
//		 this.openTelemetry = AutoConfiguredOpenTelemetrySdk.initialize().getOpenTelemetrySdk();
//         System.out.println("Getting logProvider");
//
//		 LoggerProvider logProvider = openTelemetry.getLogsBridge();
//		 
//		 
//		 
//		 
//		 
//		 
//		 
//		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
//		 GlobalOpenTelemetry.set(this.openTelemetry);
		 //OpenTelemetryInfo openTelemetryInfo = OpenTelemetryAccessor.getOpenTelemetryInfo();
		 
//			System.out.println("Global telemetry: " + openTelemetryInfo.getOpenTelemetry().getLogsBridge());

	//	System.out.println("OpenTelemetry: " + openTelemetry);
		// this.openTelemetry.getLogsBridge().loggerBuilder(getHandlerName()).build().logRecordBuilder();

		 
		 
		  //LogBridge/builder
//        OpenTelemetryInfo openTelemetryInfo = OpenTelemetryAccessor.getOpenTelemetryInfo();
//         LoggerProvider logProvider2 = openTelemetryInfo.getOpenTelemetry().getLogsBridge();
//         
//         LogRecordExporter test = null;
//                
//        LogRecordBuilder builder = logProvider2.loggerBuilder(getHandlerName()).build().logRecordBuilder();
//       mapLogRecord(builder);
//        builder.emit();
//        
//		System.out.println("CC: " + cc.getProperties());
//		//System.out.println(cc.getBundleContext().getService(null));
//		System.out.println();
//		
//		Enumeration<String> keys = cc.getProperties().keys();
//		Dictionary<String, Object> properties = cc.getProperties();
//		Map<String, Object> map = new HashMap<>();
//		
//		while(keys.hasMoreElements()) {
//			String key = keys.nextElement();
//			Object value = properties.get(key);
//			System.out.println(key.toString() + " -- " + value.toString());
//		}

//		BundleContext bundleContext = cc.getBundleContext();
//		ServiceReference<?>[] serviceReferences = null;
//		
//		 try {
//		        // Construct a filter based on the provided properties
//		        String filter = createFilter(cc);
//		        
//		        // Use the filter to find matching service references
//		        serviceReferences = bundleContext.getServiceReferences(getClass().getName(), filter);
//		        
//		        if (serviceReferences != null) {
//		            // Iterate over the matching service references
//		            for (ServiceReference<?> serviceReference : serviceReferences) {
//		                // Now you can use the service reference
//		                Object service = bundleContext.getService(serviceReference);
//		                // Do something with the service
//		                
//		            }
//		        }
//		    } catch (InvalidSyntaxException e) {
//		        // Handle invalid filter syntax
//		    }




	//	System.out.println(configuration.keySet());

		

		//OpenTelemetryInfo openTelemetryInfo = OpenTelemetryAccessor.getOpenTelemetryInfo();
	//	System.out.println("OpenTelemetryinfo: " + OpenTelemetryAccessor.getOpenTelemetryInfo().getEnabled());
//		LogRecordBuilder builder2 = openTelemetryInfo.getOpenTelemetry().getLogsBridge().loggerBuilder(getHandlerName())
//				.build().logRecordBuilder();

		// LogRecordBuilder builder2 =
		// GlobalOpenTelemetry.get().getLogsBridge().loggerBuilder(getHandlerName()).build().logRecordBuilder();

//		mapLogRecord(builder2);
//		builder2.emit();


		// final OpenTelemetryInfo openTelemetryInfo =
		// OpenTelemetryAccessor.getOpenTelemetryInfo();
        
     
        
//        for (Map.Entry<String, Object> entry : configuration.entrySet()) {
//            String key = entry.getKey();
//            Object value = entry.getValue();
//            System.out.println("Key: " + key + ", Value: " + value);
//        }
//		 super.activate(cc, configuration);
//		 System.out.println("AFTER SUPER ACTIVATE");
		// validateSources(configuration);
	}

	
	private String createFilter(ComponentContext context) {
	    // Construct a filter based on the provided properties
	    String filter = "";
	    Dictionary<String, Object> properties = context.getProperties();
	    Enumeration<String> keys = properties.keys();
	    while (keys.hasMoreElements()) {
	        String key = keys.nextElement();
	        Object value = properties.get(key);
	        if (value != null) {
	            if (!filter.isEmpty()) {
	                filter += " && ";
	            }
	            filter += "(" + key + "=" + value.toString() + ")";
	        }
	    }
	    return filter;
	}
	
	
	@Override
	@Deactivate
	protected void deactivate(ComponentContext cc, int reason) {
		// super.deactivate(cc, reason);
	}

	@Override
	@Modified
	protected void modified(Map<String, Object> configuration) {
		super.modified(configuration);
		validateSources(configuration);
	}

	private void mapLogRecord(LogRecordBuilder builder) {
		//
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