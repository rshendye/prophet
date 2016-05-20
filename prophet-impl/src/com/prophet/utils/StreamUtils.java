package com.prophet.utils;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static com.prophet.utils.OptionalUtils.getOptional;


/**
 * Utility functions for streams
 */
public class StreamUtils {
  public static <K,V> Stream<Map.Entry<K, V>> mapToEntryStream(Map<K, V> map) {
    return getOptional(map)
        .map(Map::entrySet)
        .map(Set::stream)
        .orElseGet(Stream::empty);
  }
}
