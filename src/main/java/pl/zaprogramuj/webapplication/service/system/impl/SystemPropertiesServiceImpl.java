package pl.zaprogramuj.webapplication.service.system.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import pl.zaprogramuj.webapplication.service.system.SystemPropertiesService;

@Service("systemPropertiesService")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class SystemPropertiesServiceImpl implements SystemPropertiesService {

	@Value("${system.version}")
	private String systemVersion;

	@Override
	public String getSystemVersion() {
		return systemVersion.substring(0, systemVersion.indexOf('-'));
	}
}
