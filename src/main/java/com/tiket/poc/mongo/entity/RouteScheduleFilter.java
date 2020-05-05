package com.tiket.poc.mongo.entity;

import org.springframework.data.annotation.PersistenceConstructor;

/**
 * @author zakyalvan
 */
@SuppressWarnings("serial")
public class RouteScheduleFilter extends ScheduleFilter<TrainRoute> {
  @PersistenceConstructor
  @lombok.Builder(builderClassName = "Builder")
  RouteScheduleFilter(TrainRoute value) {
    super(FilterType.ROUTE, value);
  }
}
