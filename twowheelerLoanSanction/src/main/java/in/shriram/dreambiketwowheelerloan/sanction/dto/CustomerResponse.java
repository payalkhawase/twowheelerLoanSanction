package in.shriram.dreambiketwowheelerloan.sanction.dto;

import java.util.Date;

public class CustomerResponse {
	
	private String msg;
	private Date timestamp;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public CustomerResponse(String msg, Date timestamp) {
		
		this.msg=msg;
		this.timestamp=timestamp;
	}
	
	

}
