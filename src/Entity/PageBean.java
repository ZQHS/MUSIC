package Entity;

import java.util.List;

public class PageBean<T> {
	private List<T> beanList;
	private int total;
	private int totalPage;
	private int pageIndex;
	private int pageSize;

	public PageBean() {
		super();
	}

	public List<T> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "PageBean [beanList=" + beanList + ", total=" + total + ", totalPage=" + totalPage + ", pageIndex="
				+ pageIndex + ", pageSize=" + pageSize + "]";
	}

}
