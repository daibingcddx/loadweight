package com.sie.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: com.odperson.wms.base.persistence.entity.ReturnData
 * @Description: 异步请求返回对象封装类
 * @author: brucey
 * @date: 2013-1-4 下午05:39:41 
 * @version: V1.0
 */
public class ReturnData implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 返回码 */
	private String key;
	/** 返回值 */
	private Object value;
	/** 返回信息 */
	private String msg;
	/** 返回的分页对象，此对象一般用于表格数据查询中 */
	private List list1;
	private List list2;
	private String msg2;
	
	public String getMsg2() {
		return msg2;
	}
	public void setMsg2(String msg2) {
		this.msg2 = msg2;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List getList1() {
		return list1;
	}
	public void setList1(List list1) {
		this.list1 = list1;
	}
	public List getList2() {
		return list2;
	}
	public void setList2(List list2) {
		this.list2 = list2;
	}
}
