package com.easylotto.core.entity.user;
// Generated by MyEclipse - Hibernate Tools

import java.util.Date;

import javax.persistence.Table;


/**
 * EcpEcardOper generated by MyEclipse - Hibernate Tools
 */
@Table(name = "ecp_user_fund_log")
public class EcpUserFundLog extends AbstractEcpUserFundLog implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public EcpUserFundLog() {
    }

    
    /** full constructor */
    public EcpUserFundLog(Long intRecId,Long intAccountId, Integer intOperType, Date dtOperTime, Double decAmount, String vcBillNo, Double decBalance, Integer intAccountType) {
        super(intRecId,intAccountId, intOperType, dtOperTime, decAmount, vcBillNo, decBalance, intAccountType);        
    }
   
}
