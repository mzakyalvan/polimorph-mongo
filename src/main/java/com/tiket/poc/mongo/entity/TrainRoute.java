package com.tiket.poc.mongo.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.PersistenceConstructor;

/**
 * @author zakyalvan
 */
@Getter
@ToString
@SuppressWarnings("serial")
public class TrainRoute implements Serializable {
  private String originCode;
  private EndpointType originType;
  private String destinationCode;
  private EndpointType destinationType;

  @PersistenceConstructor
  @lombok.Builder(builderClassName = "Builder")
  TrainRoute(String originCode, EndpointType originType, String destinationCode,
      EndpointType destinationType) {
    this.originCode = originCode;
    this.originType = originType;
    this.destinationCode = destinationCode;
    this.destinationType = destinationType;
  }
}
