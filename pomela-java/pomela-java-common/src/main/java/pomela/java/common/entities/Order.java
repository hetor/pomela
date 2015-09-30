package pomela.java.common.entities;

/**
 * Created by tao.he on 2015/9/30.
 */
public class Order {
	private String outId;
	private String title;
	private String userId;
	private Long createTime;
	private Long updateTime;

	public String getOutId() {
		return outId;
	}

	public void setOutId(String outId) {
		this.outId = outId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "outId: " + outId + " title: " + title + " userId: " + userId + " createTime: " + createTime
				+ " updateTime: " + updateTime;
	}
}
