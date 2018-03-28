package org.bsshare.tv.model.front.web.sharing;

public class IksRequest {

	private String ac;
	private String ma;
	private String sn;

	public String getAc() {
		return ac;
	}

	public void setAc(String ac) {
		this.ac = ac;
	}

	public String getMa() {
		return ma;
	}

	public void setMa(String ma) {
		this.ma = ma;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	@Override
	public String toString() {
		return "IksRequest [ac=" + ac + ", ma=" + ma + ", sn=" + sn + "]";
	}

}
