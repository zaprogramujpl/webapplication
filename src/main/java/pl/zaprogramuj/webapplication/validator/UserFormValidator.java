package pl.zaprogramuj.webapplication.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.zaprogramuj.webapplication.model.form.user.UserProfileForm;

@Component
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
public class UserFormValidator implements Validator
{
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private Pattern pattern;
	private Matcher matcher;
	
	public UserFormValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	@Override
	public boolean supports(Class<?> paramClass)
	{
		return UserProfileForm.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors)
	{
		UserProfileForm form = (UserProfileForm) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "userFormValidator.field.emptyValue", new Object[] { "Login" });
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "userFormValidator.field.emptyValue", new Object[] { "First Name" });
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "userFormValidator.field.emptyValue", new Object[] { "Last Name" });
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", "userFormValidator.field.emptyValue", new Object[] { "Email Address" });
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "userFormValidator.field.emptyValue", new Object[] { "Password" });
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "userFormValidator.field.emptyValue", new Object[] { "Password Confirm" });

		if (!form.getPassword().equals(form.getPasswordConfirm()))
		{
			errors.rejectValue("password", "userFormValidator.notmatch.password");
			errors.rejectValue("passwordConfirm", "userFormValidator.notmatch.password");
		}

		// email validate
		matcher = pattern.matcher(form.getEmailAddress());

		if (!matcher.matches())
		{
			errors.rejectValue("emailAddress", "userFormValidator.incorrect");
		}
	}

}
