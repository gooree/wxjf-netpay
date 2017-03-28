package com.wxjfkg.payment.validate;

import java.lang.reflect.Method;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.InvalidConfigurationException;
import net.sf.oval.exception.OValException;
import net.sf.oval.internal.util.ReflectionUtils;

public class ValidMethodCheck extends AbstractAnnotationCheck<ValidMethod> {

	private static final long serialVersionUID = 6855069578300873451L;
	
	private String methodName;
	
	private Class<?> parameterType;
	
	public void configure(final ValidMethod constraintAnnotation) {
		super.configure(constraintAnnotation);
        setMethodName(constraintAnnotation.methodName());
        setParameterType(constraintAnnotation.parameterType());
    }

	@Override
	public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext context,
			Validator validator) throws OValException {
		final Class<?> clazz = validatedObject.getClass();
		Method method;
		method = ReflectionUtils.getMethodRecursive(clazz, methodName,
				parameterType);
        if (method == null)
            throw new InvalidConfigurationException("Method " + clazz.getName() + "." + methodName + "(" + parameterType + ") not found. Is [" + parameterType
                    + "] the correct value for [@ValidateWithMethod.parameterType]?");
        //explicit cast to workaround: type parameters of <T>T cannot be determined; no unique maximal instance exists for type variable T with upper bounds boolean,java.lang.Object
        return (Boolean) ReflectionUtils.invokeMethod(method, validatedObject, valueToValidate);
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Class<?> getParameterType() {
		return parameterType;
	}

	public void setParameterType(Class<?> parameterType) {
		this.parameterType = parameterType;
	}

}
