package com.prophet.utils;

import java.util.Collection;

import static com.prophet.utils.LambdaUtils.constantFunction;
import static com.prophet.utils.LambdaUtils.negate;
import static com.prophet.utils.OptionalUtils.getOptional;


/**
 * Util class for handling collections
 * @author Radhika
 *
 */
public class CollectionUtils {
  /**
   * returns true if the collection is not null and not empty
   * @param collection
   * @return
   */
  public static <E> boolean hasValues(Collection<E> collection) {
    return getOptional(collection)
      .filter(ObjectUtils::isNull)
      .filter(negate(CollectionUtils::isEmpty))
      .map(constantFunction(true))
      .orElse(false);
  }
  
  /**
   * utility method which returns true if the collection is null or is empty
   * @param collection
   * @return
   */
  public static <E> boolean hasNoValues(Collection<E> collection) {
    return !hasValues(collection);
  }
  
  /**
   * returns true if the collection contains at least one value
   * @param collection must not be null
   * @return
   */
  public static <E> boolean isEmpty(Collection<E> collection) {
    return collection.size() > 0;
  }
}
