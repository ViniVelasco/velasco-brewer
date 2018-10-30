package com.velasco.brewer.thymeleaf.processor;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

public class BrewerDialect extends AbstractProcessorDialect {

	public BrewerDialect() {
		super("Velasco Brewer", "brewer", StandardDialect.PROCESSOR_PRECEDENCE);
	}
 
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processors = new HashSet<>();
		processors.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		return processors;
	}

}
