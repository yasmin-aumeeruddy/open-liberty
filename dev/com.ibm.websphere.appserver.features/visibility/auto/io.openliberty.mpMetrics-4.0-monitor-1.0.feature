-include= ~${workspace}/cnf/resources/bnd/feature.props
symbolicName=io.openliberty.mpMetrics-4.0-monitor-1.0
visibility=private
IBM-Provision-Capability: \
  osgi.identity; filter:="(&(type=osgi.subsystem.feature)(osgi.identity=io.openliberty.mpMetrics-4.0))", \
  osgi.identity; filter:="(&(type=osgi.subsystem.feature)(|(osgi.identity=com.ibm.websphere.appserver.monitor-1.0)))"
-bundles=io.openliberty.microprofile.metrics.internal.3.0.monitor.jakarta
IBM-Install-Policy: when-satisfied
kind=ga
edition=core
