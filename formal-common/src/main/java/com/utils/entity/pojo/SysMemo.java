package com.utils.entity.pojo;


public class SysMemo {
	private Integer id;
	/** 记录名 */
	private String memoName;
	/** 记录内容 */
	private String memoContent;
	/*完成*/
	private boolean done;
	/*状态 删除-1*/
	private Integer status;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setMemoName(String memoName) {
		this.memoName = memoName;
	}

	public String getMemoName() {
		return this.memoName;
	}

	public void setMemoContent(String memoContent) {
		this.memoContent = memoContent;
	}

	public String getMemoContent() {
		return this.memoContent;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public boolean getDone() {
		return this.done;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        SysMemo that = (SysMemo) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SysMemo{" +
				"id=" + id +
						",memoName='" + memoName + "'" +
						",memoContent='" + memoContent + "'" +
						",status='" + status + "'" +
		                '}';
    }

}
