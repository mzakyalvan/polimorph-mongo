package com.tiket.poc.mongo.entity;

import java.util.List;
import lombok.Getter;
import org.springframework.data.annotation.PersistenceConstructor;

/**
 * @author zakyalvan
 */
@Getter
@SuppressWarnings("serial")
public class OriginScheduleFilter extends ScheduleFilter<RouteEndpoint> {
  private final List<RouteEndpoint> destinations;

  @PersistenceConstructor
  @lombok.Builder(builderClassName = "Builder")
  OriginScheduleFilter(RouteEndpoint value, List<RouteEndpoint> destinations) {
    super(FilterType.ORIGIN, value);
    this.destinations = destinations;
  }
}
