package com.easylotto.core.entity.user;

import javax.persistence.Table;

@Table(name="ecp_user_key")
public class EcpUserKey extends AbstractEcpUserKey implements java.io.Serializable {

	public EcpUserKey() {
	}

	public EcpUserKey(Long id) {
		super(id);
	}
	
	public EcpUserKey(Long id, String open_id, String key) {
		super(id, open_id, key);
	}

}
