package com.easylotto.core.entity.user;


public class AbstractEcpUserKey implements java.io.Serializable{


	// Fields

	private Long id; //用户ID 
	
	private String open_id;			//微信用户openId
	private String vc_key;			//UUID
	// Constructors

	/** default constructor */
	public AbstractEcpUserKey() {
	}

	/** minimal constructor */
	public AbstractEcpUserKey(Long id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractEcpUserKey(Long id, String open_id, String key) {
		this.id = id;
		this.open_id = open_id;
		this.vc_key = key;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	public String getVc_key() {
		return vc_key;
	}

	public void setVc_key(String vc_key) {
		this.vc_key = vc_key;
	}




}
