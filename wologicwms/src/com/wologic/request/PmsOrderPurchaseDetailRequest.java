package  com.wologic.request;

import java.math.BigDecimal;
import java.util.Date;

/**
 * PmsOrderPurchaseDetailRequest锛氶噰璐鍗曟槑缁�(鏍囧搧)璇锋眰鍙傛暟
 * 
 * @author jinsicao
 * 
 */
public class PmsOrderPurchaseDetailRequest  {

    /** 搴忓垪鍖栨爣璇� */
    private static final long serialVersionUID = 1L;
    
    /** id */
    private Long id; 
    /** 璁㈠崟id */
    private String orderNo; 
    /** 鍟嗗搧缂栫爜 */
    private String skuCode; 
    /** 鍟嗗搧鍚嶇О */
    private String goodsName; 
    /** 瀹㈡埛鍟嗗搧缂栫爜 */
    private String customerSkuCode; 
    /** 瀹㈡埛鍟嗗搧鍚嶇О */
    private String customerGoodsName; 
    /** 渚涘簲鍟嗙紪鐮� */
    private String partnerCode; 
    /** 渚涘簲鍟嗗悕绉� */
    private String partnerName; 
    /** 鏄惁鐢熼矞 1锛氭槸 0锛氬惁 */
    private Integer isFresh; 
    /** 鏄惁璧犲搧 1锛氭槸 0锛氬惁 */
    private Integer isGift; 
    /** 璁″垝鏁伴噺 */
    private BigDecimal planNum; 
    /** 瀹為檯鏁伴噺 */
    private BigDecimal realityNum; 
    /** 绋庣巼 */
    private BigDecimal taxRate; 
    /** 璁″垝鍒拌揣鏃ユ湡 */
    private Date planArrivalDate; 
    /** 鍚◣鍗曚环 */
    private BigDecimal taxPrice; 
    /** 鏃犵◣鍗曚环 */
    private BigDecimal taxNoPrice; 
    /** 閲戦 */
    private BigDecimal amount; 
    /** 绋庨 */
    private BigDecimal tax; 
    /** 浠风◣鍚堣 */
    private BigDecimal taxCount; 
    /** 璁㈠崟鐘舵�� */
    private Integer orderState; 
    /** 鍒涘缓鏃堕棿 */
    private Date createTime; 
    /** 鍒涘缓浜� */
    private String createUser; 
    /** 鏇存柊鏃堕棿 */
    private Date updateTime; 
    /** 鏇存柊浜� */
    private String updateUser; 
    /** 鏄惁鏈夋晥 */
    private Integer yn; 
    
    public Long getId(){
        return id;
    }
        
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getOrderNo(){
        return orderNo;
    }
        
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    
    public String getSkuCode(){
        return skuCode;
    }
        
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }
    
    public String getGoodsName(){
        return goodsName;
    }
        
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    
    public String getCustomerSkuCode(){
        return customerSkuCode;
    }
        
    public void setCustomerSkuCode(String customerSkuCode) {
        this.customerSkuCode = customerSkuCode;
    }
    
    public String getCustomerGoodsName(){
        return customerGoodsName;
    }
        
    public void setCustomerGoodsName(String customerGoodsName) {
        this.customerGoodsName = customerGoodsName;
    }
    
    public String getPartnerCode(){
        return partnerCode;
    }
        
    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }
    
    public String getPartnerName(){
        return partnerName;
    }
        
    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }
    
    public Integer getIsFresh(){
        return isFresh;
    }
        
    public void setIsFresh(Integer isFresh) {
        this.isFresh = isFresh;
    }
    
    public Integer getIsGift(){
        return isGift;
    }
        
    public void setIsGift(Integer isGift) {
        this.isGift = isGift;
    }
    
    public BigDecimal getPlanNum(){
        return planNum;
    }
        
    public void setPlanNum(BigDecimal planNum) {
        this.planNum = planNum;
    }
    
    public BigDecimal getRealityNum(){
        return realityNum;
    }
        
    public void setRealityNum(BigDecimal realityNum) {
        this.realityNum = realityNum;
    }
    
    public BigDecimal getTaxRate(){
        return taxRate;
    }
        
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
    
    public Date getPlanArrivalDate(){
        return planArrivalDate;
    }
        
    public void setPlanArrivalDate(Date planArrivalDate) {
        this.planArrivalDate = planArrivalDate;
    }
    
    public BigDecimal getTaxPrice(){
        return taxPrice;
    }
        
    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
    }
    
    public BigDecimal getTaxNoPrice(){
        return taxNoPrice;
    }
        
    public void setTaxNoPrice(BigDecimal taxNoPrice) {
        this.taxNoPrice = taxNoPrice;
    }
    
    public BigDecimal getAmount(){
        return amount;
    }
        
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public BigDecimal getTax(){
        return tax;
    }
        
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }
    
    public BigDecimal getTaxCount(){
        return taxCount;
    }
        
    public void setTaxCount(BigDecimal taxCount) {
        this.taxCount = taxCount;
    }
    
    public Integer getOrderState(){
        return orderState;
    }
        
    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }
    
    public Date getCreateTime(){
        return createTime;
    }
        
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public String getCreateUser(){
        return createUser;
    }
        
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
    public Date getUpdateTime(){
        return updateTime;
    }
        
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public String getUpdateUser(){
        return updateUser;
    }
        
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    
    public Integer getYn(){
        return yn;
    }
        
    public void setYn(Integer yn) {
        this.yn = yn;
    }
}
