package org.coworking.reservation.cache;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LfuCache<T> {

  private static final Logger logger = LoggerFactory.getLogger(LfuCache.class);
  private final int maxCapacity;

  private final Map<Long, CacheEntry<T>> cache = new ConcurrentHashMap<>();

  private static class CacheEntry<T> {
    T value;
    int frequency;

    CacheEntry(T value) {
      this.value = value;
      this.frequency = 1;
    }
  }

  public LfuCache(int maxCapacity) {
    this.maxCapacity = maxCapacity;
    logger.info("Initialized LFU Cache with capacity: {}", maxCapacity);
  }

  public synchronized T get(Long id) {
    CacheEntry<T> entry = cache.get(id);
    if (entry != null) {
      entry.frequency++;
      logger.debug("Cache hit for ID {} (freq: {})", id, entry.frequency);
      return entry.value;
    }
    logger.debug("Cache miss for ID {}", id);
    return null;
  }

  public synchronized void put(Long id, T value) {
    if (cache.containsKey(id)) {
      CacheEntry<T> entry = cache.get(id);
      entry.value = value;
      entry.frequency++;
      logger.debug("Updated cache entry for ID {} (new freq: {})", id, entry.frequency);
    } else {
      if (cache.size() >= maxCapacity) {
        evictLeastFrequentlyUsed();
      }
      cache.put(id, new CacheEntry<>(value));
      logger.debug("Inserted new cache entry for ID {}", id);
    }
  }

  public synchronized void remove(Long id) {
    cache.remove(id);
    logger.debug("Removed cache entry for ID {}", id);
  }

  public synchronized void clear() {
    cache.clear();
    logger.debug("Cleared all cache entries");
  }

  private void evictLeastFrequentlyUsed() {
    Long leastUsedId = cache.entrySet().stream()
        .min(Comparator.comparingInt(entry -> entry.getValue().frequency))
        .map(Map.Entry::getKey)
        .orElse(null);
    if (leastUsedId != null) {
      cache.remove(leastUsedId);
      logger.info("Evicted least frequently used entry with ID {}", leastUsedId);
    }
  }
}
