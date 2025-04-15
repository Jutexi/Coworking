package org.coworking.reservation.cache;

import org.coworking.reservation.model.CoworkingSpace;
import org.springframework.stereotype.Component;

@Component
public class CoworkingSpaceCache extends LfuCache<CoworkingSpace> {
  public CoworkingSpaceCache() {
    super(100);
  }
}
