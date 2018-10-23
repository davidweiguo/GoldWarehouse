package com.goldwarehouse.entity;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.ACCOUNT_ID
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private String accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.USER_ID
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private String userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.MARKET
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private String market;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.CASH
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Double cash;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.MARGIN
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Double margin;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Double pnl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.ALL_TIME_PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Double allTimePnl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.UR_PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Double urPnl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.CASH_DEPOSITED
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Double cashDeposited;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.UNIT_PRICE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Double unitPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.ACTIVE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Boolean active;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.CREATED
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Date created;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.CURRENCY
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private String currency;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.ROLL_PRICE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Double rollPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.BIGGESTWIN
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Double biggestwin;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.BIGGESTLOSE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Double biggestlose;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.DerbyID
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private String derbyid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.CASH_AVAILABLE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Double cashAvailable;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.MARGIN_HELD
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Double marginHeld;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.START_ACCOUNT_VALUE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Double startAccountValue;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.STATE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private String state;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.BOD_TOTAL_PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Double bodTotalPnl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.TOTAL_TURNOVER
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Double totalTurnover;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.MONTHLY_TURNOVER
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Double monthlyTurnover;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.DAILY_TURNOVER
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Double dailyTurnover;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.MONTHLY_PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Double monthlyPnl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.LOAN
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Double loan;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.ACCOUNT_TYPE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Short accountType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.SETTLEMENT_PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Double settlementPnl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.ACCUMULATED_PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Double accumulatedPnl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.BOD_ALL_TIME_PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private Double bodAllTimePnl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table accounts
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.ACCOUNT_ID
     *
     * @return the value of accounts.ACCOUNT_ID
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.ACCOUNT_ID
     *
     * @param accountId the value for accounts.ACCOUNT_ID
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.USER_ID
     *
     * @return the value of accounts.USER_ID
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.USER_ID
     *
     * @param userId the value for accounts.USER_ID
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.MARKET
     *
     * @return the value of accounts.MARKET
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public String getMarket() {
        return market;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.MARKET
     *
     * @param market the value for accounts.MARKET
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setMarket(String market) {
        this.market = market == null ? null : market.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.CASH
     *
     * @return the value of accounts.CASH
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Double getCash() {
        return cash;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.CASH
     *
     * @param cash the value for accounts.CASH
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setCash(Double cash) {
        this.cash = cash;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.MARGIN
     *
     * @return the value of accounts.MARGIN
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Double getMargin() {
        return margin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.MARGIN
     *
     * @param margin the value for accounts.MARGIN
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setMargin(Double margin) {
        this.margin = margin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.PNL
     *
     * @return the value of accounts.PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Double getPnl() {
        return pnl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.PNL
     *
     * @param pnl the value for accounts.PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setPnl(Double pnl) {
        this.pnl = pnl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.ALL_TIME_PNL
     *
     * @return the value of accounts.ALL_TIME_PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Double getAllTimePnl() {
        return allTimePnl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.ALL_TIME_PNL
     *
     * @param allTimePnl the value for accounts.ALL_TIME_PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setAllTimePnl(Double allTimePnl) {
        this.allTimePnl = allTimePnl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.UR_PNL
     *
     * @return the value of accounts.UR_PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Double getUrPnl() {
        return urPnl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.UR_PNL
     *
     * @param urPnl the value for accounts.UR_PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setUrPnl(Double urPnl) {
        this.urPnl = urPnl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.CASH_DEPOSITED
     *
     * @return the value of accounts.CASH_DEPOSITED
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Double getCashDeposited() {
        return cashDeposited;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.CASH_DEPOSITED
     *
     * @param cashDeposited the value for accounts.CASH_DEPOSITED
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setCashDeposited(Double cashDeposited) {
        this.cashDeposited = cashDeposited;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.UNIT_PRICE
     *
     * @return the value of accounts.UNIT_PRICE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Double getUnitPrice() {
        return unitPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.UNIT_PRICE
     *
     * @param unitPrice the value for accounts.UNIT_PRICE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.ACTIVE
     *
     * @return the value of accounts.ACTIVE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.ACTIVE
     *
     * @param active the value for accounts.ACTIVE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.CREATED
     *
     * @return the value of accounts.CREATED
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.CREATED
     *
     * @param created the value for accounts.CREATED
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.CURRENCY
     *
     * @return the value of accounts.CURRENCY
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.CURRENCY
     *
     * @param currency the value for accounts.CURRENCY
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.ROLL_PRICE
     *
     * @return the value of accounts.ROLL_PRICE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Double getRollPrice() {
        return rollPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.ROLL_PRICE
     *
     * @param rollPrice the value for accounts.ROLL_PRICE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setRollPrice(Double rollPrice) {
        this.rollPrice = rollPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.BIGGESTWIN
     *
     * @return the value of accounts.BIGGESTWIN
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Double getBiggestwin() {
        return biggestwin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.BIGGESTWIN
     *
     * @param biggestwin the value for accounts.BIGGESTWIN
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setBiggestwin(Double biggestwin) {
        this.biggestwin = biggestwin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.BIGGESTLOSE
     *
     * @return the value of accounts.BIGGESTLOSE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Double getBiggestlose() {
        return biggestlose;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.BIGGESTLOSE
     *
     * @param biggestlose the value for accounts.BIGGESTLOSE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setBiggestlose(Double biggestlose) {
        this.biggestlose = biggestlose;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.DerbyID
     *
     * @return the value of accounts.DerbyID
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public String getDerbyid() {
        return derbyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.DerbyID
     *
     * @param derbyid the value for accounts.DerbyID
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setDerbyid(String derbyid) {
        this.derbyid = derbyid == null ? null : derbyid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.CASH_AVAILABLE
     *
     * @return the value of accounts.CASH_AVAILABLE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Double getCashAvailable() {
        return cashAvailable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.CASH_AVAILABLE
     *
     * @param cashAvailable the value for accounts.CASH_AVAILABLE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setCashAvailable(Double cashAvailable) {
        this.cashAvailable = cashAvailable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.MARGIN_HELD
     *
     * @return the value of accounts.MARGIN_HELD
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Double getMarginHeld() {
        return marginHeld;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.MARGIN_HELD
     *
     * @param marginHeld the value for accounts.MARGIN_HELD
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setMarginHeld(Double marginHeld) {
        this.marginHeld = marginHeld;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.START_ACCOUNT_VALUE
     *
     * @return the value of accounts.START_ACCOUNT_VALUE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Double getStartAccountValue() {
        return startAccountValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.START_ACCOUNT_VALUE
     *
     * @param startAccountValue the value for accounts.START_ACCOUNT_VALUE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setStartAccountValue(Double startAccountValue) {
        this.startAccountValue = startAccountValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.STATE
     *
     * @return the value of accounts.STATE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.STATE
     *
     * @param state the value for accounts.STATE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.BOD_TOTAL_PNL
     *
     * @return the value of accounts.BOD_TOTAL_PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Double getBodTotalPnl() {
        return bodTotalPnl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.BOD_TOTAL_PNL
     *
     * @param bodTotalPnl the value for accounts.BOD_TOTAL_PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setBodTotalPnl(Double bodTotalPnl) {
        this.bodTotalPnl = bodTotalPnl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.TOTAL_TURNOVER
     *
     * @return the value of accounts.TOTAL_TURNOVER
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Double getTotalTurnover() {
        return totalTurnover;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.TOTAL_TURNOVER
     *
     * @param totalTurnover the value for accounts.TOTAL_TURNOVER
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setTotalTurnover(Double totalTurnover) {
        this.totalTurnover = totalTurnover;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.MONTHLY_TURNOVER
     *
     * @return the value of accounts.MONTHLY_TURNOVER
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Double getMonthlyTurnover() {
        return monthlyTurnover;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.MONTHLY_TURNOVER
     *
     * @param monthlyTurnover the value for accounts.MONTHLY_TURNOVER
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setMonthlyTurnover(Double monthlyTurnover) {
        this.monthlyTurnover = monthlyTurnover;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.DAILY_TURNOVER
     *
     * @return the value of accounts.DAILY_TURNOVER
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Double getDailyTurnover() {
        return dailyTurnover;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.DAILY_TURNOVER
     *
     * @param dailyTurnover the value for accounts.DAILY_TURNOVER
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setDailyTurnover(Double dailyTurnover) {
        this.dailyTurnover = dailyTurnover;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.MONTHLY_PNL
     *
     * @return the value of accounts.MONTHLY_PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Double getMonthlyPnl() {
        return monthlyPnl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.MONTHLY_PNL
     *
     * @param monthlyPnl the value for accounts.MONTHLY_PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setMonthlyPnl(Double monthlyPnl) {
        this.monthlyPnl = monthlyPnl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.LOAN
     *
     * @return the value of accounts.LOAN
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Double getLoan() {
        return loan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.LOAN
     *
     * @param loan the value for accounts.LOAN
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setLoan(Double loan) {
        this.loan = loan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.ACCOUNT_TYPE
     *
     * @return the value of accounts.ACCOUNT_TYPE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Short getAccountType() {
        return accountType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.ACCOUNT_TYPE
     *
     * @param accountType the value for accounts.ACCOUNT_TYPE
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setAccountType(Short accountType) {
        this.accountType = accountType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.SETTLEMENT_PNL
     *
     * @return the value of accounts.SETTLEMENT_PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Double getSettlementPnl() {
        return settlementPnl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.SETTLEMENT_PNL
     *
     * @param settlementPnl the value for accounts.SETTLEMENT_PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setSettlementPnl(Double settlementPnl) {
        this.settlementPnl = settlementPnl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.ACCUMULATED_PNL
     *
     * @return the value of accounts.ACCUMULATED_PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Double getAccumulatedPnl() {
        return accumulatedPnl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.ACCUMULATED_PNL
     *
     * @param accumulatedPnl the value for accounts.ACCUMULATED_PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setAccumulatedPnl(Double accumulatedPnl) {
        this.accumulatedPnl = accumulatedPnl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.BOD_ALL_TIME_PNL
     *
     * @return the value of accounts.BOD_ALL_TIME_PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public Double getBodAllTimePnl() {
        return bodAllTimePnl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.BOD_ALL_TIME_PNL
     *
     * @param bodAllTimePnl the value for accounts.BOD_ALL_TIME_PNL
     *
     * @mbg.generated Tue Oct 23 16:40:26 CST 2018
     */
    public void setBodAllTimePnl(Double bodAllTimePnl) {
        this.bodAllTimePnl = bodAllTimePnl;
    }
}