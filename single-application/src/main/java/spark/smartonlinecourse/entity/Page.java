package spark.smartonlinecourse.entity;

import java.util.List;

/**
 * @ClassName Page
 * @Description TODO
 * @Author Spark
 * @Date 2018/8/22 21:33
 * @Version 1.0
 **/
public class Page {
    //当前页
    private Integer page;
    //每页多少行
    private Integer rows;
    //一共多少页
    private Integer total;
    //一共多少数据
    private Integer records;
    // 按什么排序
    private String sidx;
    // 升序还是降序
    private String sord;
    //类对象
    private List<? extends Object> data;


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public List<? extends Object> getData() {
        return data;
    }

    public void setData(List<? extends Object> data) {
        this.data = data;
    }
}
