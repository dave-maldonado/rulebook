package com.deliveredtechnologies.rulebook;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A FactMap is an extension of {@link HashMap}; it stores facts by their name and provides convenience methods for
 * accessing {@link Fact} objects.
 */
public class FactMap<T> implements Map<String, Fact<T>> {

  private Map<String, Fact<T>> _facts;

  public FactMap(Map<String, Fact<T>> facts) {
    _facts = facts;
  }

  public FactMap() {
    _facts = new HashMap<String, Fact<T>>();
  }

  /**
   * The method getOne() gets the value of the single Fact in the FactMap.
   * @return the value of a fact stored if only one fact is stored, otherwise null
   */
  public T getOne() {
    if (_facts.size() == 1) {
      return _facts.values().iterator().next().getValue();
    }
    return null;
  }

  /**
   * The method getValue() returns the value of the Fact associated with the name passed in.
   * @param name  the name of the Fact
   * @return      the value of the Fact
   */
  public T getValue(String name) {
    return Optional.ofNullable(_facts.get(name)).map(Fact::getValue).orElse(null);
  }

  /**
   * Method getStrVal() gets the String value of a Fact in the FactMap.
   * @param name  the name of the Fact in the FactMap
   * @return      the String value of the Fact specified
   */
  public String getStrVal(String name) {
    if (getValue(name) instanceof String) {
      return (String)getValue(name);
    }
    return String.valueOf(getValue(name));
  }

  /**
   * Method getIntVal() gets the Integer value of a Fact in the FactMap.
   * @param name  the name of the Fact in the FactMap
   * @return      the Integer value of the Fact specified
   */
  public Integer getIntVal(String name) {
    Object value = getValue(name);
    if (value != null) {
      if (Integer.class == value.getClass()) {
        return (Integer)value;
      }
      if (value.getClass() == String.class) {
        return Integer.valueOf((String) value);
      }
    }
    return null;
  }

  /**
   * Method getDblVal() gets the Double value of a Fact in the FactMap.
   * @param name  the name of the Fact in the FactMap
   * @return      the Double value of the Fact specified
   */
  public Double getDblVal(String name) {
    Object value = getValue(name);
    if (value != null) {
      if (Float.class == value.getClass()) {
        return Double.valueOf((Float) value);
      }
      if (Double.class == value.getClass()) {
        return (Double)value;
      }
      if (Integer.class == value.getClass()) {
        return Double.valueOf((Integer) value);
      }
      if (Long.class == value.getClass()) {
        return Double.valueOf((Long) value);
      }
      if (String.class == value.getClass()) {
        return Double.parseDouble((String)value);
      }
    }
    return null;
  }

  /**
   * The method setValue sets the value of the Fact but its name.<br/>
   * If no Fact exists with the associated name, a new fact is created with the specified name and value.<br/>
   * @param name  the name of the Fact
   * @param value the value object of the Fact
   */
  public void setValue(String name, T value) {
    Fact<T> fact = _facts.get(name);
    if (fact == null) {
      fact = new Fact<T>(name, value);
      _facts.put(name, fact);
      return;
    }
    fact.setValue(value);
  }

  /**
   * This put() method is a convenience method for adding a Fact to a FactMap.<br/>
   * It uses the name of the Fact as the key and the Fact as the value.
   * @param fact  the Fact to be added to the FactMap
   * @return      the Fact that was just added
   */
  public Fact<T> put(Fact<T> fact) {
    return _facts.put(fact.getName(), fact);
  }

  @Override
  public Fact<T> put(String key, Fact<T> value) {
    return _facts.put(key, value);
  }

  @Override
  public int size() {
    return _facts.size();
  }

  @Override
  public boolean isEmpty() {
    return _facts.isEmpty();
  }

  @Override
  public boolean containsKey(Object key) {
    return _facts.containsKey(key);
  }

  @Override
  public boolean containsValue(Object value) {
    return _facts.containsValue(value);
  }

  @Override
  public Fact<T> get(Object key) {
    return _facts.get(key);
  }

  @Override
  public Fact<T> remove(Object key) {
    return _facts.remove(key);
  }

  @Override
  public void putAll(Map<? extends String, ? extends Fact<T>> map) {
    _facts.putAll(map);
  }

  @Override
  public void clear() {
    _facts.clear();
  }

  @Override
  public Set<String> keySet() {
    return _facts.keySet();
  }

  @Override
  public Collection<Fact<T>> values() {
    return _facts.values();
  }

  @Override
  public Set<Entry<String, Fact<T>>> entrySet() {
    return _facts.entrySet();
  }

  /**
   * The toString() method gets the FactMap converted a string.<br/>
   * If there is only one Fact, the String value is the String value of the Fact.<br/>
   * Otherwise, the String value is the value of the parent {@link HashMap}.
   * @return  the String value of the FactMap
   */
  @Override
  public String toString() {
    return _facts.size() == 1 ? this.getOne().toString() : _facts.toString();
  }
}

