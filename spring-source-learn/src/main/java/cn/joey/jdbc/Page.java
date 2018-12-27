package cn.joey.jdbc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther liujiji
 * @date 2018/12/24 14:47
 */
public class Page<T> implements Serializable {
    private static final int DEFAULT_PAGE_SIZE =20;
    private int pageSize = DEFAULT_PAGE_SIZE;
    private long start;
    private List<T> rows;
    private long total;//总记录数

    public Page(){
        this(0,0,DEFAULT_PAGE_SIZE,new ArrayList<T>());
    }

    public Page(long start, long totalSize, int pageSize, ArrayList<T> rows) {
        this.pageSize = pageSize;
        this.start = start;
        this.total = totalSize;
        this.rows =rows;
    }
    public long getTotal(){
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTotalPageCount(){
        if(total%pageSize==0){
            return total / pageSize;
        }else{
            return total/pageSize +1;
        }
    }
    public int getPageSize(){
        return pageSize;
    }
    public List<T> getRows(){
        return rows;
    }
    public void setRows(List<T> rows){
        this.rows = rows;
    }
    public long getPageNo(){
        return start/pageSize +1;
    }
    public boolean hasNextPage(){
        return this.getPageNo()<this.getTotalPageCount() -1;
    }

    public boolean hasPreviousPage(){
        return this.getPageNo()>1;
    }

    protected static int getStartOfPage(int pageNo){
        return getStartOfPage(pageNo,DEFAULT_PAGE_SIZE);
    }

    public static int getStartOfPage(int pageNo, int pageSize) {
        return (pageNo - 1) * pageSize;
    }

}
