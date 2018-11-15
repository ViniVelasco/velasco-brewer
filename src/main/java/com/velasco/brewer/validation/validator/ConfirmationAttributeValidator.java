package com.velasco.brewer.validation.validator;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.apache.commons.beanutils.BeanUtils;

import com.velasco.brewer.validation.ConfirmationAttribute;

public class ConfirmationAttributeValidator implements ConstraintValidator<ConfirmationAttribute, Object> {

	
	private String attribute;
	private String confirmAttribute;
	
	@Override
	public void initialize(ConfirmationAttribute constraintAnnotation) {
		this.attribute = constraintAnnotation.attribute();
		this.confirmAttribute = constraintAnnotation.confirmAttribute();
		
		
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		boolean valid = false;
		try {
			Object attributeValue = BeanUtils.getProperty(object, this.attribute);
			Object confirmAttributeValue = BeanUtils.getProperty(object, this.confirmAttribute);
			
			valid = allNull(attributeValue, confirmAttributeValue) || allEquals(attributeValue, confirmAttributeValue);
		} catch (Exception e) {
			throw new RuntimeException("Erro recuperando valores dos atributos", e);
		}
		
		if(!valid) {
			context.disableDefaultConstraintViolation();
			String message = context.getDefaultConstraintMessageTemplate();
			ConstraintViolationBuilder violationBuilder = context.buildConstraintViolationWithTemplate(message);
			violationBuilder.addPropertyNode(confirmAttribute).addConstraintViolation();
		}
		
		return valid;
	}

	private boolean allEquals(Object attributeValue, Object confirmAttributeValue) {
		return  attributeValue != null && attributeValue.equals(confirmAttributeValue) ;
	}

	private boolean allNull(Object attributeValue, Object confirmAttributeValue) {
		return attributeValue == null && confirmAttributeValue == null;
	}

}
