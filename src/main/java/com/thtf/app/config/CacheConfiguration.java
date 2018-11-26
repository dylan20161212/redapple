package com.thtf.app.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.thtf.app.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.thtf.app.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.thtf.app.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.thtf.app.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.thtf.app.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.thtf.app.domain.Attachment.class.getName(), jcacheConfiguration);
            cm.createCache(com.thtf.app.domain.Dictionary.class.getName(), jcacheConfiguration);
            cm.createCache(com.thtf.app.domain.Notification.class.getName(), jcacheConfiguration);
            cm.createCache(com.thtf.app.domain.NotificationRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.thtf.app.domain.Organization.class.getName(), jcacheConfiguration);
            cm.createCache(com.thtf.app.domain.Resource.class.getName(), jcacheConfiguration);
            cm.createCache(com.thtf.app.domain.UserRoleOrganization.class.getName(), jcacheConfiguration);
            cm.createCache(com.thtf.app.domain.Role.class.getName(), jcacheConfiguration);
            cm.createCache(com.thtf.app.domain.Role.class.getName() + ".resources", jcacheConfiguration);
            cm.createCache(com.thtf.app.domain.User.class.getName() + ".roles", jcacheConfiguration);
            cm.createCache(com.thtf.app.domain.Notification.class.getName()+ ".notificationRecords", jcacheConfiguration);
            cm.createCache(com.thtf.app.domain.User.class.getName() + ".userRoleOrganizations", jcacheConfiguration);
            
           
            
            // jhipster-needle-ehcache-add-entry
            
            
        };
    }
}
