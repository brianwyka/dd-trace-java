package datadog.trace.common.metrics;

import datadog.trace.api.Config;
import datadog.trace.api.WellKnownTags;

public class MetricsAggregatorFactory {
  public static MetricsAggregator createMetricsAggregator(
      WellKnownTags wellKnownTags, Config config) {
    if (config.isTracerMetricsEnabled()) {
      return new ConflatingMetricsAggregator(wellKnownTags, config);
    }
    return NoOpMetricsAggregator.INSTANCE;
  }
}
