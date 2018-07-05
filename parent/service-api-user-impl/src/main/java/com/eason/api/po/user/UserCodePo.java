package com.eason.api.po.user;


import java.util.Date;


/**
 * UserCode对象定义
 * @author eason
 *
 */
public class UserCodePo {

    /** uid */
	private static final long serialVersionUID = 1L;
    private Integer	id;
    private Integer	userId;
    private String	code;
    private Integer	type;
    private Date sendTime;
    private String	phone;
    private String	mac;
    private Integer state;
    /**更新时间 */
    private Date updateTime;
    /**时间数*/
    private Integer timeNumber;
    
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
    
    public Integer getType() {
  		return type;
  	}

  	public void setType(Integer type) {
  		this.type = type;
  	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public Date getSendTime() {
		return sendTime;
	}
	
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}

	public Integer getTimeNumber() {
		return timeNumber;
	}

	public void setTimeNumber(Integer timeNumber) {
		this.timeNumber = timeNumber;
	}
	
}
