package com.nccs.test;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: logistics-srm
 * @author: xuzengsheng
 * @create: 2021-01-18 09:03
 * @description: 合同价格表明细excel导出实体类
 */

@Data
@ContentRowHeight(10) //内容行高
@ColumnWidth(25)    //全局列宽，也可添加在属性上面，表示指定列的列宽
@HeadRowHeight(20)  //标题栏行高
// 头背景设置成红色 IndexedColors.RED.getIndex()
//@HeadStyle(fillPatternType = FillPatternType.SOLID_FOREGROUND, fillForegroundColor = 10)
// 头字体设置成20
//@HeadFontStyle(fontHeightInPoints = 20)
// 内容的背景设置成绿色 IndexedColors.GREEN.getIndex()
//@ContentStyle(fillPatternType = FillPatternType.SOLID_FOREGROUND, fillForegroundColor = 17)
// 内容字体设置成20
//@ContentFontStyle(fontHeightInPoints = 20)
public class ContractPriceExcelDto {


    @ExcelProperty(value = "行号", index = 1)
    private Integer lineNum;

    @ExcelProperty(value = "标段")
    private String extStr1;

    @ExcelProperty(value = "预计业务量")
    private BigDecimal expectTraffic;

    @ExcelProperty(value = "生效日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date effectiveDate;

    @ExcelProperty(value = "线路名称")
    private String lineName;

    @ExcelProperty(value = "起始地（市）")
    private String esstFromCityName;

    @ExcelProperty(value = "起始地编码（市）")
    private String esstFromCityCode;

    @ExcelProperty(value = "起始地（区）")
    private String esstFromDistrictName;

    @ExcelProperty(value = "起始地编码（区）")
    private String esstFromDistrictCode;

    @ExcelProperty(value = "单价")
    private BigDecimal unitPrice;

    @ExcelProperty(value = "历史价格")
    private BigDecimal historyPrice;

    @ExcelProperty(value = "线路编码")
    private String lineCode;


    @ExcelProperty(value = "起始地省")
    private String esstFromProvinceName;


    @ExcelProperty(value = "起始地镇")
    private String esstFromStreetName;


    @ExcelProperty(value = "起始地省编码")
    private String esstFromProvinceCode;


    @ExcelProperty(value = "起始地镇编码")
    private String esstFromStreetCode;


    @ExcelProperty(value = "目的地省")
    private String esstToProvinceName;

    @ExcelProperty(value = "目的地市")
    private String esstToCityName;


    @ExcelProperty(value = "目的地区")
    private String esstToDistrictName;


    @ExcelProperty(value = "目的地镇")
    private String esstToStreetName;


    @ExcelProperty(value = "目的地省编码")
    private String esstToProvinceCode;

    @ExcelProperty(value = "目的地市编码")
    private String esstToCityCode;


    @ExcelProperty(value = "目的地区编码")
    private String esstToDistrictCode;


    @ExcelProperty(value = "目的地镇编码")
    private String esstToStreetCode;


    @ExcelProperty(value = "计费方式")
    private String calculateType;


    @ExcelProperty(value = "档位从")
    private String gearFrom;


    @ExcelProperty(value = "档位到")
    private String gearTo;


    @ExcelProperty(value = "零担整车标识")
    private String ltlCarFlag;


    @ExcelProperty(value = "商超标识")
    private String marketFlag;


    @ExcelProperty(value = "排序号")
    private Integer sortNumber;


    @ExcelProperty(value = "自定义字段2")
    private String extStr2;

    @ExcelProperty(value = "自定义字段3")
    private String extStr3;


    @ExcelProperty(value = "自定义字段4")
    private String extStr4;


    @ExcelProperty(value = "自定义字段5")
    private String extStr5;


    @ExcelProperty(value = "自定义字段6")
    private String extStr6;


    @ExcelProperty(value = "自定义字段7")
    private String extStr7;


    @ExcelProperty(value = "自定义字段8")
    private String extStr8;

    @ExcelProperty(value = "自定义字段9")
    private String extStr9;


    @ExcelProperty(value = "自定义字段10")
    private String extStr10;


    @ExcelProperty(value = "自定义字段11")
    private String extStr11;


    @ExcelProperty(value = "自定义字段12")
    private String extStr12;


    @ExcelProperty(value = "自定义字段13")
    private String extStr13;


    @ExcelProperty(value = "自定义字段14")
    private String extStr14;


    @ExcelProperty(value = "自定义字段15")
    private String extStr15;


    @ExcelProperty(value = "自定义字段16")
    private String extStr16;


    @ExcelProperty(value = "自定义字段17")
    private String extStr17;


    @ExcelProperty(value = "自定义字段18")
    private String extStr18;

    @ExcelProperty(value = "自定义字段19")
    private String extStr19;


    @ExcelProperty(value = "自定义字段20")
    private String extStr20;


    @ExcelProperty(value = "自定义字段21")
    private String extStr21;


    @ExcelProperty(value = "自定义字段22")
    private String extStr22;


    @ExcelProperty(value = "自定义字段23")
    private String extStr23;


    @ExcelProperty(value = "自定义字段24")
    private String extStr24;


    @ExcelProperty(value = "自定义字段25")
    private String extStr25;


    @ExcelProperty(value = "自定义字段26")
    private String extStr26;

    @ExcelProperty(value = "自定义字段27")
    private String extStr27;


    @ExcelProperty(value = "自定义字段28")
    private String extStr28;

    @ExcelProperty(value = "自定义字段29")
    private String extStr29;


    @ExcelProperty(value = "自定义字段30")
    private String extStr30;


    @ExcelProperty(value = "原明细ID")
    private Long originId;


    @ExcelProperty(value = "操作类别")
    private String operationType;


    @ExcelProperty(value = "明细状态")
    private String priceStatus;


    @ExcelProperty(value = "退线违约金")
    private BigDecimal pushingPenalty;


    @ExcelProperty(value = "失效日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expirationDate;
}
