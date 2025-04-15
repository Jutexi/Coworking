package org.coworking.reservation.cache;

import org.coworking.reservation.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserCache extends LfuCache<User> {
  public UserCache() {
    super(100);
  }
}