package pl.zaprogramuj.webapplication.validator;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.zaprogramuj.webapplication.model.form.user.UserProfileForm;

@Component
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
public class UserFormValidator implements Validator
{

	@Autowired
	private MessageSource messageProperties;
	
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
		Locale locale = LocaleContextHolder.getLocale();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "userFormValidator.field.emptyValue", new Object[] { messageProperties.getMessage("userForm.label.login", null, locale) });
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "userFormValidator.field.emptyValue",  new Object[] { messageProperties.getMessage("userForm.label.firstName", null, locale)} );
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "userFormValidator.field.emptyValue", new Object[] { messageProperties.getMessage("userForm.label.lastName", null, locale) });
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", "userFormValidator.field.emptyValue", new Object[] { messageProperties.getMessage("userForm.label.emailAddress", null, locale) });
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "userFormValidator.field.emptyValue", new Object[] { messageProperties.getMessage("userForm.label.password", null, locale) });
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "userFormValidator.field.emptyValue", new Object[] { messageProperties.getMessage("userForm.label.passwordConfirm", null, locale) });


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
