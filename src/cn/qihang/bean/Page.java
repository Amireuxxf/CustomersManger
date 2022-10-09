package cn.qihang.bean;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: qihang
 * @Date: 2022/9/24 14:50
 * @Desc: 封装分页
 */
public class Page {
    //当前页
    private int pageNum = 1;

    //每页显示的条数
    private int pageSize = 5;

    //总条数
    private int totalCounts;

    //总页码
    private int totalPages;

    //要显示的数据
    private List<Customer> list;

    //起始页
    private int startIndex;

    //上一页
    private int beforePage;

    //下一页
    private int nextPage;

    //分页栏
    private int pageBar[];


    public Page() {
    }

    public Page(int pageNum, int pageSize, int totalCounts, int totalPages, List<Customer> list, int startIndex, int beforePage, int nextPage, int[] pageBar) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCounts = totalCounts;
        this.totalPages = totalPages;
        this.list = list;
        this.startIndex = startIndex;
        this.beforePage = beforePage;
        this.nextPage = nextPage;
        this.pageBar = pageBar;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(int totalCounts) {
        this.totalCounts = totalCounts;
    }

    /**
     * 总页码
     * @return
     */
    public int getTotalPages() {
        return this.totalPages = (totalCounts % pageSize) == 0 ? totalCounts / pageSize : (totalCounts / pageSize) + 1;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Customer> getList() {
        return list;
    }

    public void setList(List<Customer> list) {
        this.list = list;
    }

    /**
     * 起始行数
     * @return
     */
    public int getStartIndex() {
        return startIndex = (pageNum - 1) * pageSize;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * 上一页
     * @return
     */
    public int getBeforePage() {
        this.beforePage = pageNum - 1;
        if (beforePage <= 0){
           this.beforePage = 1;
        }
        return beforePage;
    }

    public void setBeforePage(int beforePage) {
        this.beforePage = beforePage;
    }

    /**
     * 下一页
     * @return
     */
    public int getNextPage() {
        this.nextPage = pageNum + 1;
        if (nextPage >= totalPages){
            nextPage = totalPages;
        }
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    /**
     * 分页栏
     * @return
     */
    public int[] getPageBar() {
        //分页栏--开始页
        int beginPage;
        //分页栏--尾页
        int endPage;
        //判断当前页码是否满足前5后4
        if (totalPages <= 10){
            beginPage = 1;
            endPage = totalPages;
        }else {
            beginPage = pageNum - 5;
            endPage = pageNum + 4;

            //排除小于0
            if (beginPage <= 0) {
                beginPage = 1;
                endPage = beginPage + 9;
            }

            if (endPage >= totalPages) {
                endPage = totalPages;
                beginPage = endPage - 9;
            }
        }

        pageBar = new int[endPage - beginPage + 1];

        int index = 0;
        for (int i = beginPage; i <= endPage; i++) {
            pageBar[index++] = i;
        }
        return pageBar;
    }

    public void setPageBar(int[] pageBar) {
        this.pageBar = pageBar;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", totalCounts=" + totalCounts +
                ", totalPages=" + totalPages +
                ", list=" + list +
                ", startIndex=" + startIndex +
                ", beforePage=" + beforePage +
                ", nextPage=" + nextPage +
                ", pageBar=" + Arrays.toString(pageBar) +
                '}';
    }
}
