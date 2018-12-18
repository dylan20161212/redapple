package com.thtf.app.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

import com.thtf.app.domain.Resource;



public class CustomGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private Resource res = null;

	@Override
	public String getAuthority() {
		if (null != res.getResOperate() && !"".equals(res.getResOperate())) {
			return res.getResRouterLink() + ":" + res.getResOperate();
		} else {
			return res.getResRouterLink();
		}
	}

	public CustomGrantedAuthority(String res) {
		Assert.hasText(res, "A granted authority textual representation is required");
		Resource resTemp = new Resource();
		if (res.indexOf(":") > 0) {
			String[] resource = res.split(":");
			resTemp.setResRouterLink(resource[0]);
			resTemp.setResOperate(resource[1]);
		} else {
			resTemp.setResRouterLink(res);
			resTemp.setResOperate("");
		}
		this.res = resTemp;
	}

	public CustomGrantedAuthority(Resource res) {
		Assert.hasText(res.getResText(), "A granted authority textual representation is required");
		this.res = res;
	}

	public CustomGrantedAuthority(CustomGrantedAuthority authority) {
		this.res = authority.res;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj instanceof CustomGrantedAuthority) {
			return res.getId() == (((CustomGrantedAuthority) obj).getRes().getId());
		}

		return false;
	}

	public int hashCode() {
		if (this.res.getResRouterLink() != null) {
			return this.res.getResRouterLink().hashCode();
		} else {
			return this.res.getResText().hashCode();
		}
	}

	public String toString() {
		if (this.res.getResRouterLink() != null) {
			return this.res.getResRouterLink();
		} else {
			return this.res.getResText();
		}
	}

	public Resource getRes() {
		return this.res;
	}

}
