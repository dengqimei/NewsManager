package com.deng.bean;

public class News {

	private Long id;//新闻编号
	private String title;//新闻标题
	private String author;//新闻作者
	private String content;//新闻内容
	private String publishTime;//发布时间
	private String updateTime;//更新时间
	private Integer clickTimes;//点击次数
	private Long catalog_id;//所属栏目
//	private byte[] image;//新闻图片
	private String publishDate;//发布日期
	private String isPublish;//是否发布
	
	public News() {}

	/*public News(Long id, String title, String author, String content, String publishTime, String updateTime,
			Integer clickTimes, Long catalog_id, byte[] image) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.content = content;
		this.publishTime = publishTime;
		this.updateTime = updateTime;
		this.clickTimes = clickTimes;
		this.catalog_id = catalog_id;
		this.image = image;
	}*/

	public News(Long id, String title, String author, String content, String publishTime, String updateTime,
			Integer clickTimes, Long catalog_id) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.content = content;
		this.publishTime = publishTime;
		this.updateTime = updateTime;
		this.clickTimes = clickTimes;
		this.catalog_id = catalog_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getClickTimes() {
		return clickTimes;
	}

	public void setClickTimes(Integer clickTimes) {
		this.clickTimes = clickTimes;
	}

	public Long getCatalog_id() {
		return catalog_id;
	}

	public void setCatalog_id(Long catalog_id) {
		this.catalog_id = catalog_id;
	}

	/*public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}*/

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(String isPublish) {
		this.isPublish = isPublish;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", author=" + author + ", content=" + content + ", publishTime="
				+ publishTime + ", updateTime=" + updateTime + ", clickTimes=" + clickTimes + ", catalog_id="
				+ catalog_id + ", publishDate=" + publishDate + ", isPublish=" + isPublish + "]";
	}

/*	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", author=" + author + ", content=" + content + ", publishTime="
				+ publishTime + ", updateTime=" + updateTime + ", clickTimes=" + clickTimes + ", catalog_id="
				+ catalog_id + ", image=" + Arrays.toString(image) + ", publishDate=" + publishDate + ", isPublish="
				+ isPublish + "]";
	}*/
	
}
