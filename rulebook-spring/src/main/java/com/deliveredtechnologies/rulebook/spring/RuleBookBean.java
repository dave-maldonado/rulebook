package com.deliveredtechnologies.rulebook.spring;

import com.deliveredtechnologies.rulebook.DecisionBook;
import com.deliveredtechnologies.rulebook.runner.RuleAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InvalidClassException;

/**
 * Created by clong on 2/23/17.
 *
 */
public class RuleBookBean extends DecisionBook {
  private static Logger LOGGER = LoggerFactory.getLogger(DecisionBook.class);

  @Override
  protected void defineRules() {
    //intentionally left blank
  }

  @SuppressWarnings("unchecked")
  public void addRule(Object rule) throws InvalidClassException {
    super.addRule(new RuleAdapter(rule));
  }
}
