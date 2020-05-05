package com.tiket.poc.mongo.entity;

import java.io.Serializable;
import lombok.Getter;
import org.springframework.data.annotation.PersistenceConstructor;

/**
 * @author zakyalvan
 */
@Getter
@SuppressWarnings("serial")
public class RouteEndpoint implements Serializable {
  private final String code;
  private final EndpointType type;

  @PersistenceConstructor
  @lombok.Builder(builderClassName = "Builder")
  RouteEndpoint(String code, EndpointType type) {
    this.code = code;
    this.type = type;
  }
}
