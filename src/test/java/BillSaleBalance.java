

import java.io.Serializable;

import com.fise.utils.excel.ExcelCell;


/**
 * 销售类单据
 * @author wang.m1
 * @date  2014-10-18 12:32:47
 * @version 1.0.0
 * @copyright (C) 2013 YouGou Information Technology Co.,Ltd 
 * All Rights Reserved. 
 * 
 * The software for the YouGou technology development, without the 
 * company's written consent, and any other individuals and 
 * organizations shall not be used, Copying, Modify or distribute 
 * the software.
 * 
 */
public class BillSaleBalance  implements Serializable {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = -90364152870460702L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 是否对调
	 */
	private Boolean isSwap;
	
    /**
	 * FAS单据类型
	 */
	@ExcelCell("A")
	private String fasBillCode;
	
	/**
	 * FAS单据类型名称
	 */
	@ExcelCell("C")
	private String fasBillName;

    /**
     * 单据编号
     */
	@ExcelCell("B")
    private String billNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getIsSwap() {
		return isSwap;
	}

	public void setIsSwap(Boolean isSwap) {
		this.isSwap = isSwap;
	}

	public String getFasBillCode() {
		return fasBillCode;
	}

	public void setFasBillCode(String fasBillCode) {
		this.fasBillCode = fasBillCode;
	}

	public String getFasBillName() {
		return fasBillName;
	}

	public void setFasBillName(String fasBillName) {
		this.fasBillName = fasBillName;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}


	
}