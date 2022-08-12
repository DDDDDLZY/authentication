package com.utils.entity.pojo;


public class Test {
	private Integer testId;
	private Integer proId;
	private String testName;
	private String content;
	private Integer status;
	private String remark;

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public Integer getTestId() {
		return this.testId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public Integer getProId() {
		return this.proId;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestName() {
		return this.testName;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
	}


	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        Test that = (Test) o;
        return testId.equals(that.testId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(testId);
    }

    @Override
    public String toString() {
        return "Test{" +
				"testId=" + testId +
						",proId='" + proId + "'" +
						",testName='" + testName + "'" +
						",content='" + content + "'" +
						",status='" + status + "'" +
						",remark='" + remark + "'" +
		                '}';
    }

}
