package spark.course.entity.vo;

import spark.course.constants.CommonConstants;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName Pagination
 * @Description TODO
 * @Author Spark
 * @Date 1/14/2019 2:24 PM
 * @Version 1.0
 **/
public class Pagination implements Serializable {
    private static final Long serialVersionUID = -3781580387710565437L;
    private Integer pageSize;
    private Integer pageNo;
    private Integer totalPage;
    private Integer totalCount;
    private Integer begin;
    private  Integer end;
    private List<?> data;

    public Pagination() {
        this.pageNo = 1;
        this.pageSize = Integer.valueOf(CommonConstants.Pagination.DEFAULT_PAGE_SIZE);
        this.begin = (getPageNo() - 1) * getPageSize();
        this.end = getPageNo() * getPageSize();
    }

    public Pagination(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public boolean isLastPage() {
        return pageNo >= getTotalPage();
    }

    public boolean isFirstPage() {
        return pageNo <= 1;
    }

    public int getNextPage() {
        if (isLastPage()) {
            return pageNo;
        } else {
            return pageNo + 1;
        }
    }

    public int getPrePage() {
        if (isFirstPage()) {
            return pageNo;
        } else {
            return pageNo - 1;
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        this.begin = (getPageNo() - 1) * getPageSize();
        this.end = getPageNo() * getPageSize();
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        this.totalPage = totalCount / this.pageSize + (totalCount % this.pageSize == 0 ? 0 : 1);
    }

    public Integer getBegin() {
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
