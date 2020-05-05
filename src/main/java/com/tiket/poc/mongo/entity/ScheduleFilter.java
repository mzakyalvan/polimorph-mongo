package com.tiket.poc.mongo.entity;

import java.io.Serializable;
import lombok.Getter;

/**
 * @author zakyalvan
 */
@Getter
@SuppressWarnings("serial")
public abstract class ScheduleFilter<T> implements Serializable {
  protected FilterType type;
  protected T value;

  ScheduleFilter(FilterType type, T value) {
    this.type = type;
    this.value = value;
  }
}
