package tmall.pojo;

import tmall.dao.OrderDao;

import java.util.Date;
import java.util.List;

public class Order {
    private Integer id;
    private String orderCode;
    private String address;
    private String post;
    private String receiver;
    private String mobile;
    private String userMessage;
    private Date createDate;
    private Date payDate;
    private Date deliveryDate;
    private Date confirmDate;
    private Integer uid;
    private String status;
    //非数据库字段
    private List<OrderItem> orderItems;//订单项列表
    private User user;
    private Float total;//总金额
    private Integer totalNumber;//商品总数

    public String getStatusDesc(){
        String desc = "未知";
        switch (status){
            case OrderDao.WAIT_PAY:
                desc="待付款";
                break;
            case OrderDao.WAIT_DELIVERY:
                desc="待发货";
                break;
            case OrderDao.WAIT_CONFIRM:
                desc="待确认";
                break;
            case OrderDao.WAIT_REVIEW:
                desc="待评价";
                break;
            case OrderDao.FINISH:
                desc="已完成";
                break;
            case OrderDao.DELETE:
                desc="已删除";
                break;
            default:
                desc="未知";
        }
        return desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode==null?null:orderCode.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address==null?null:address.trim();
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post==null?null:post.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver==null?null:receiver.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile==null?null:mobile.trim();
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage==null?null:userMessage.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status==null?null:status.trim();
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }
}
