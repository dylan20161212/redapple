package com.thtf.app.security;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import com.thtf.app.domain.Resource;



public class CustomVoter implements AccessDecisionVoter<Object> {

	public static final String IS_AUTHENTICATED = "authenticated";
	private static final String REGEX = "(\\D*)(\\/+\\d+(,)?)+(.*)";
	private static final Pattern p = Pattern.compile(REGEX);
	private final Logger log = LoggerFactory.getLogger(CustomVoter.class);

	public CustomVoter() {
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		if (attribute != null && IS_AUTHENTICATED.equals(attribute.toString())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
		int result = ACCESS_ABSTAIN;
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		for (ConfigAttribute attribute : attributes) {
			if (this.supports(attribute)) {
				result = ACCESS_DENIED;

				FilterInvocation fi = (FilterInvocation) object;
				log.info("{\"authentication\":\"{}\",\"remoteHost\":\"{}\",\"fullRequestUrl\":\"{}\"}", authentication.getName(),
						fi.getRequest().getRemoteHost(), fi.getFullRequestUrl());
				String theUrl = fi.getRequestUrl();
				javax.servlet.http.HttpServletRequest req = fi.getHttpRequest();
				int queIdx = theUrl.indexOf("?");
				Matcher m = p.matcher(theUrl);
				if (m.find()) {
					theUrl = m.group(1);
				} else if (queIdx > -1) {
					theUrl = theUrl.substring(0, queIdx);
				} else {
					queIdx = theUrl.indexOf("/api/usersget");
					if (queIdx > -1 && "GET".equals(req.getMethod())) {
						theUrl = "/api/usersget";
					} else {
						queIdx = theUrl.indexOf("/api/users/resetpass");
						if (queIdx > -1 && "PUT".equals(req.getMethod())) {
							theUrl = "/api/users/resetpass";
						} else {
							queIdx = theUrl.indexOf("/api/users");
							if (queIdx > -1 && "DELETE".equals(req.getMethod())) {
								theUrl = "/api/users";
							}
						}
					}
				}
				CustomGrantedAuthority re = null;
				final String matchAll = "*";
				for (GrantedAuthority authority : authorities) {
					if (authority instanceof CustomGrantedAuthority) {
						try {
							re = (CustomGrantedAuthority) authority;
							Resource theRes = re.getRes();
							
							if (null != authority.getAuthority() && (theUrl.equals(theRes.getResRouterLink()) || theRes.getResRouterLink().equals(matchAll))
									&& (req.getMethod().equals(theRes.getResOperate()) || theRes.getResOperate().equals(matchAll))) {
								return ACCESS_GRANTED;
							}
						} catch (Exception e) {
							log.error(e.getMessage());
							result = ACCESS_ABSTAIN;
						}
					} else {
						result = ACCESS_ABSTAIN;
					}
				}
			}
		}
		return result;
	}
}
