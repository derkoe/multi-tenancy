package mt.web.config;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import mt.tenant.TenantFilter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter
{
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addRedirectViewController("/", "/index.html");
        registry.addViewController("/index.html").setViewName("index");
    }

    @Bean
    public FilterRegistrationBean tenantFilter()
    {
        FilterRegistrationBean filter = new FilterRegistrationBean(new TenantFilter());
        filter.addUrlPatterns("/*");
        return filter;
    }
}
