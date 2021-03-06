package tmall.util;

public class Page {
    private int start;//初始页第一条记录的行号（因为起始行号为0，故行号需要-1.）
    private int count;//每页显示页数
    private int total;//总个数
    private String param;//参数

    private static final int defaultCount=5;//默认每页显示5条

    public Page(){
        count=defaultCount;
    }

    public Page(int start,int count){
        this();
        this.start=start;
        this.count=count;
    }

    //判断是否有前一页
    public boolean isHasPrevious(){
        if(start==0)
            return false;
        return true;
    }

    //判断是否有后一页
    public boolean isHasNext(){
        if(start==getLast())
            return false;
        return true;
    }

    //计算出最后一页的数值是多少
    public int getLast(){
        int last;
        if(0==total%count){
            last=total-count;
        }else {
            last=total-total%count;
        }
        return last;
    }

    //计算出总共有多少页
    public int getTotalPage(){
        int totalPage;
        if (0 == total % count)
            totalPage = total / count;
        else
            totalPage = total / count + 1;

        if(totalPage==0)
            totalPage=1;
        return totalPage;
    }

    @Override
    public String toString() {
        return "Page [start=" + start + ", count=" + count + ", total=" + total + ", getStart()=" + getStart()
                + ", getCount()=" + getCount() + ", isHasPrevious()=" + isHasPrevious() + ", isHasNext()="
                + isHasNext() + ", getTotalPage()=" + getTotalPage() + ", getLast()=" + getLast() + "]";
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
