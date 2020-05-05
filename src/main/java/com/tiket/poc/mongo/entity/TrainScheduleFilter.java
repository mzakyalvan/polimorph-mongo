package com.tiket.poc.mongo.entity;

import java.util.List;
import lombok.Getter;
import org.springframework.data.annotation.PersistenceConstructor;

/**
 * @author zakyalvan
 */
@Getter
@SuppressWarnings("serial")
public class TrainScheduleFilter extends ScheduleFilter<String> {
  private final List<TrainRoute> routes;

  @PersistenceConstructor
  @lombok.Builder(builderClassName = "Builder")
  TrainScheduleFilter(String value, List<TrainRoute> routes) {
    super(FilterType.TRAIN, value);
    this.routes = routes;
  }
}
