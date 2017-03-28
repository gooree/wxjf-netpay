package com.wxjfkg.payment.utils;

import java.util.ArrayList;
import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import com.wxjfkg.payment.core.ValidateResult;

/**
 * 参数校验工具类
 * 
 * @author GuoRui
 *
 */
public class ValidationUtils {

	/**
	 * 校验对象是否合法
	 * @param object
	 * @return
	 */
	public static ValidateResult validate(Object object) {
		return validate(object, new String[] {});
	}
	
	/**
	 * 校验对象是否合法
	 * @param object
	 * @param profiles
	 * @return
	 */
	public static ValidateResult validate(Object object, String... profiles) {
		if(object == null) {
			return ValidateResult.SUCCESS;
		}
		Validator validator = new Validator();
		List<ConstraintViolation> violations = validator.validate(object, profiles);
		if (violations != null && violations.size() > 0) {
			ValidateResult result = new ValidateResult(false);
			List<String> msgs = new ArrayList<String>();
			for (ConstraintViolation cv : violations) {
				msgs.add(cv.getMessage());
			}
			result.setMessages(msgs);
			return result;
		}
		return ValidateResult.SUCCESS;
	}
	
}
