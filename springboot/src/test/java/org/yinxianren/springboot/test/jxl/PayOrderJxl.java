package org.yinxianren.springboot.test.jxl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jxl.Workbook;
import jxl.write.*;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;
import org.yinxianren.springboot.mapper.PayOrderMapper;
import org.yinxianren.springboot.service.*;
import org.yinxianren.springboot.table.*;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayOrderJxl {

    private final String filePath = "E:\\data\\logs\\";
    private final String fileNameSuffix = ".xls";

    @Resource
    private PayOrderService payOrderService;
    @Resource
    private AgentMerchantInfoService agentMerchantInfoService;
    @Resource
    private MerchantInfoService merchantInfoService;
    @Resource
    private OrganizationInfoService organizationInfoService;
    @Resource
    private ChannelInfoService channelInfoService;
    @Resource
    private MerchantRateService merchantRateService;
    @Resource
    private SystemOrderTrackService systemOrderTrackService;

    @Test
    public void task() throws Exception{
        //1.获取订单所有时间日记
        Set<String> timeGrou = getTimeGroup();
        //2.根据时间去处理业务逻辑
        for (String time : timeGrou) {
            handleBusiness(time);
        }
    }
    //
    private void handleBusiness(String time) throws Exception {
        //获取代理商信息
        List<AgentMerchantInfo> agentMerchantInfoList = getAgentMerInfo();
        //获取商户信息
        List<MerchantInfo> merchantInfoList = getMerInfo();
        //机构信息
        List<OrganizationInfo> organizationInfoList = getOrganizationInfo();
        //获取通道信息
        List<ChannelInfo> channelInfoList = getChannelInfo();
        //获取商户费率
        List<MerchantRate> merchantRateList = getMerRate();
        //获取同一天的数据
        List<PayOrder> payOrderList = getPayOrder(time);
        //按照商户号进行分门别类
        Map<String,List<PayOrder>> merIdGroup = payOrderList.stream().collect(Collectors.groupingBy(PayOrder::getMerId));
        //获取所有商户号
        Set<String> merIds = merIdGroup.keySet();
        List<String>  merIdList = new ArrayList<>(merIds);
        //存放目录对象
        FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath+time+fileNameSuffix));
        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(fileOutputStream);

        //每个商户都各自生成一个sheet
        for( int i = 0 ; i<merIdList.size();i++ ) {
            String merId = merIdList.get(i);
            //创建新的一页
            WritableSheet sheet = workbook.createSheet(merId, i);
            //创建表头
            sheet = createSheetHeader(sheet);
            //每个商户对应的数据
            List<PayOrder> merPayOrder = merIdGroup.get(merId);
            //每个商户在对应时间内的订单跟踪记录
            List<SystemOrderTrack> systemOrderTrackList = getSysOrderTrack(time,merId);
            //代理商名称
            MerchantInfo  merchantInfo = merchantInfoList.stream()
                    .filter(mer->mer.getMerId().equalsIgnoreCase(merId))
                    .findAny().get();
            String parantId = merchantInfo.getParentId();
            AgentMerchantInfo agentMerchantInfo = agentMerchantInfoList.stream()
                    .filter(agent->agent.getAgentMerchantId().equalsIgnoreCase(parantId))
                    .findAny().get();
            String agentMerName = agentMerchantInfo.getAgentMerchantName();
            //每一条数据都生成一行数据
            for(int r = 0; r<merPayOrder.size(); r++){
                PayOrder payOrder = merPayOrder.get(r);
                //日期
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                Label date = null;
                if( r%2 ==0 )
                    date = new Label(0,r+1,sdf.format(payOrder.getTradeTime()),titleFormate02(sheet,r+1,0));
                else
                    date = new Label(0,r+1,sdf.format(payOrder.getTradeTime()),titleFormate03(sheet,r+1,0));
                sheet.addCell(date);
                //商户号
                int col = 0;
                Label merIdLable = null;
                col++;
                if( r%2 ==0 )
                    merIdLable = new Label(col,r+1,(payOrder.getMerId()),titleFormate02(sheet,r+1,col));
                else
                    merIdLable = new Label(col,r+1,(payOrder.getMerId()),titleFormate03(sheet,r+1,col));
                sheet.addCell(merIdLable);
                //商户订单号
                col++;
                Label merOrderId = null;
                if( r%2 ==0 )
                    merOrderId = new Label(col,r+1,(payOrder.getMerOrderId()),titleFormate02(sheet,r+1,col));
                else
                    merOrderId = new Label(col,r+1,(payOrder.getMerOrderId()),titleFormate03(sheet,r+1,col));
                sheet.addCell(merOrderId);
                //平台订单号
                col++;
                Label payId = null;
                if( r%2 ==0 )
                    payId = new Label(col,r+1,(payOrder.getPayId()),titleFormate02(sheet,r+1,col));
                else
                    payId = new Label(col,r+1,(payOrder.getPayId()),titleFormate03(sheet,r+1,col));
                sheet.addCell(payId);
                //代理商名称
                col++;
                Label agentMerNameLabel = null;
                if( r%2 ==0 )
                    agentMerNameLabel = new Label(col,r+1,agentMerName,titleFormate02(sheet,r+1,col));
                else
                    agentMerNameLabel = new Label(col,r+1,agentMerName,titleFormate03(sheet,r+1,col));
                sheet.addCell(agentMerNameLabel);
                // "通道交易流水号",
                col++;
                Label channelTransIdLabel = null;
                if( r%2 ==0 )
                    channelTransIdLabel = new Label(col,r+1,payOrder.getOrgOrderId(),titleFormate02(sheet,r+1,col));
                else
                    channelTransIdLabel = new Label(col,r+1,payOrder.getOrgOrderId(),titleFormate03(sheet,r+1,col));
                sheet.addCell(channelTransIdLabel);
                //"机构名称",
                col++;
                OrganizationInfo organizationInfo = organizationInfoList.stream()
                        .filter(org->org.getOrganizationId().equalsIgnoreCase(payOrder.getOrganizationId()))
                        .findAny().get();
                Label organizationNameLabel = null;
                if( r%2 ==0 )
                    organizationNameLabel = new Label(col,r+1,organizationInfo.getOrganizationName(),titleFormate02(sheet,r+1,col));
                else
                    organizationNameLabel = new Label(col,r+1,organizationInfo.getOrganizationName(),titleFormate03(sheet,r+1,col));
                sheet.addCell(organizationNameLabel);

                // "通道名称",
                col++;
                ChannelInfo channelInfo =  channelInfoList.stream().filter(ch->ch.getChannelId().equalsIgnoreCase(payOrder.getChannelId()))
                        .findAny().get();
                Label channelNameLabel = null;
                if( r%2 ==0 )
                    channelNameLabel = new Label(col,r+1,channelInfo.getChannelName(),titleFormate02(sheet,r+1,col));
                else
                    channelNameLabel = new Label(col,r+1,channelInfo.getChannelName(),titleFormate03(sheet,r+1,col));
                sheet.addCell(channelNameLabel);
                //"支付方式",
                col++;
                String type4 = "代付",type5 = "快捷支付",type6 = "冲正" ,type7 = "线下体现" ,type8 = "无验证快捷";
                String payType = payOrder.getPayType();
                String type = null;
                switch (payType){
                    case "4": type = type4; break;
                    case "5": type = type5; break;
                    case "6": type = type6; break;
                    case "7": type = type7; break;
                    case "8": type = type8; break;
                    default:  type = "其他";
                }
                Label payTypeLabel = null;
                if( r%2 ==0 )
                    payTypeLabel = new Label(col,r+1,type,titleFormate02(sheet,r+1,col));
                else
                    payTypeLabel = new Label(col,r+1,type,titleFormate03(sheet,r+1,col));
                sheet.addCell(payTypeLabel);
                // "订单币种",
                col++;
                Label currencyLabel = null;
                if( r%2 ==0 )
                    currencyLabel = new Label(col,r+1,"CNY",titleFormate02(sheet,r+1,col));
                else
                    currencyLabel = new Label(col,r+1,"CNY",titleFormate03(sheet,r+1,col));
                sheet.addCell(currencyLabel);
                //  "订单金额",
                col++;
                Label orderAmountLabel = null;
                if( r%2 ==0 )
                    orderAmountLabel = new Label(col,r+1,payOrder.getAmount().toString(),titleFormate02(sheet,r+1,col));
                else
                    orderAmountLabel = new Label(col,r+1,payOrder.getAmount().toString(),titleFormate03(sheet,r+1,col));
                sheet.addCell(orderAmountLabel);
                // "实际支付金额",
                col++;
                Label realAmountLabel = null;
                if( r%2 ==0 )
                    realAmountLabel = new Label(col,r+1,payOrder.getRealAmount().toString(),titleFormate02(sheet,r+1,col));
                else
                    realAmountLabel = new Label(col,r+1,payOrder.getRealAmount().toString(),titleFormate03(sheet,r+1,col));
                sheet.addCell(realAmountLabel);
                //  "平台手续费收入",
                col++;
                Label  playformFeeLabel = null;
                if( r%2 ==0 )
                    playformFeeLabel = new Label(col,r+1,payOrder.getChannelFee().toString(),titleFormate02(sheet,r+1,col));
                else
                    playformFeeLabel = new Label(col,r+1,payOrder.getChannelFee().toString(),titleFormate03(sheet,r+1,col));
                sheet.addCell(playformFeeLabel);
                //  "平台毛利润",
                col++;
                Label  incomeLabel = null;
                if( r%2 ==0 )
                    incomeLabel = new Label(col,r+1,payOrder.getIncome().toString(),titleFormate02(sheet,r+1,col));
                else
                    incomeLabel = new Label(col,r+1,payOrder.getIncome().toString(),titleFormate03(sheet,r+1,col));
                sheet.addCell(incomeLabel);
                //  "通道成本",
                col++;
                Label channelFeeLabel = null;
                if( r%2 ==0 )
                    channelFeeLabel = new Label(col,r+1,payOrder.getChannelFee().toString(),titleFormate02(sheet,r+1,col));
                else
                    channelFeeLabel = new Label(col,r+1,payOrder.getChannelFee().toString(),titleFormate03(sheet,r+1,col));
                sheet.addCell(channelFeeLabel);
                // "订单状态",
                col++;
                String oderStatusStr0 = "成功",oderStatusStr1 = "失败",oderStatusStr2 = "初始化",oderStatusStr3 = "处理中";
                String oderStatus = null;
                switch (payOrder.getOrderStatus()){
                    case 0 : oderStatus = oderStatusStr0; break;
                    case 1 : oderStatus = oderStatusStr1; break;
                    case 2 : oderStatus = oderStatusStr2; break;
                    case 3 : oderStatus = oderStatusStr3; break;
                    default:  oderStatus = "其他";
                }
                Label orderStatusLabel = null;
                if( r%2 ==0 )
                    orderStatusLabel = new Label(col,r+1,oderStatus,titleFormate02(sheet,r+1,col));
                else
                    orderStatusLabel = new Label(col,r+1,oderStatus,titleFormate03(sheet,r+1,col));
                sheet.addCell(orderStatusLabel);
                //  "交易时间",  sdf
                col++;
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Label transDateLabel = null;
                Date tradeTime = payOrder.getTradeTime();
                if( r%2 ==0 )
                    transDateLabel = new Label(col,r+1,tradeTime != null ? sdf2.format(tradeTime) : "",titleFormate02(sheet,r+1,col));
                else
                    transDateLabel = new Label(col,r+1,tradeTime != null ? sdf2.format(tradeTime) : "",titleFormate03(sheet,r+1,col));
                sheet.addCell(transDateLabel);
                //   "通道交易时间",
                col++;
                Label channelDateLabel = null;
                Date bankTime = payOrder.getBankTime();
                if( r%2 ==0 )
                    channelDateLabel = new Label(col,r+1,bankTime !=null ? sdf2.format(bankTime) : "" ,titleFormate02(sheet,r+1,col));
                else
                    channelDateLabel = new Label(col,r+1,bankTime !=null ? sdf2.format(bankTime) : "" ,titleFormate03(sheet,r+1,col));
                sheet.addCell(channelDateLabel);
                //   "通道费率",
                col++;
                Label channelRateLabel = null;
                if( r%2 ==0 )
                    channelRateLabel = new Label(col,r+1,channelInfo.getChannelRateFee().toString(),titleFormate02(sheet,r+1,col));
                else
                    channelRateLabel = new Label(col,r+1,channelInfo.getChannelRateFee().toString(),titleFormate03(sheet,r+1,col));
                sheet.addCell(channelRateLabel);
                //   "商户费率",
                col++;
                MerchantRate merchantRate =  merchantRateList.stream().filter(mer->mer.getMerId().equalsIgnoreCase(merId))
                        .findAny().get();
                Label merRateLabel = null;
                if( r%2 ==0 )
                    merRateLabel = new Label(col,r+1,merchantRate.getRateFee().toString(),titleFormate02(sheet,r+1,col));
                else
                    merRateLabel = new Label(col,r+1,merchantRate.getRateFee().toString(),titleFormate03(sheet,r+1,col));
                sheet.addCell(merRateLabel);
                //   "子商户费率",
                col++;
                Label terMerRateLabel = null;
                SystemOrderTrack systemOrderTrack =  systemOrderTrackList.stream()//
                        .filter(sys-> sys.getRefer().contains("confirmFeePay")  ||   sys.getRefer().contains("payApply") )
                        .filter(sys-> sys.getMerOrderId().equals(payOrder.getMerOrderId()))
                        .findAny().orElse(new SystemOrderTrack());

                String tradeInfo = systemOrderTrack.getTradeInfo();
                String payFee  = "";
                if( !StringUtils.isEmpty(tradeInfo)){
                    Map<String,Object> map = JSON.parseObject(tradeInfo,Map.class);
                    payFee  = (String) map.get("payFee");
                }
                if( r%2 ==0 )
                    terMerRateLabel = new Label(col,r+1,payFee,titleFormate02(sheet,r+1,col));
                else
                    terMerRateLabel = new Label(col,r+1,payFee,titleFormate03(sheet,r+1,col));

                sheet.addCell(terMerRateLabel);
                //    "子商户成本"
                col++;
                Label terMerFeeLabel = null;
                if( r%2 ==0 )
                    terMerFeeLabel = new Label(col,r+1,payOrder.getTerminalFee().toString(),titleFormate02(sheet,r+1,col));
                else
                    terMerFeeLabel = new Label(col,r+1,payOrder.getTerminalFee().toString(),titleFormate03(sheet,r+1,col));
                sheet.addCell(terMerFeeLabel);
            }
        }
        //把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        fileOutputStream.close();
    }

    private WritableSheet createSheetHeader(WritableSheet sheet ) throws WriteException {
        String[] header ={
                "日期 Y/M/D",
                "商户号",
                "商户订单号",
                "平台订单号",
                "代理商名称",
                "通道交易流水号",
                "机构名称",
                "通道名称（通道标识符）",
                "支付方式",
                "订单币种",
                "订单金额",
                "实际支付金额",
                "平台手续费收入",
                "平台毛利润",
                "通道成本",
                "订单状态",
                "交易时间",
                "通道交易时间",
                "通道费率",
                "商户费率",
                "子商户费率",
                "子商户成本"
        };
        for(int i=0;i<header.length;i++){
            Label  label = new Label(i,0,header[i],titleFormate01(sheet,0,i));
            sheet.addCell(label);
            if(header[i].equals("交易时间"))  sheet.setColumnView(i, header[i].length()*7);
            else if(header[i].equals("商户订单号"))  sheet.setColumnView(i, header[i].length()*8);
            else  sheet.setColumnView(i, header[i].length()*5);

        }
        return sheet;
    }

    private  WritableCellFormat  titleFormate02(WritableSheet sheet,int row,int i) throws WriteException{
        WritableFont bold = new WritableFont(WritableFont.TAHOMA,12,WritableFont.NO_BOLD);//设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示
        WritableCellFormat titleFormate = new WritableCellFormat(bold);//生成一个单元格样式控制对象
        titleFormate.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
        titleFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
        titleFormate.setBorder(Border.BOTTOM,BorderLineStyle.THICK, Colour.GREY_25_PERCENT);
        sheet.setRowView(row, 450, false);//设置第一行的高度
//        titleFormate.setBackground(colour(i));
        return titleFormate;
    }

    private  WritableCellFormat  titleFormate03(WritableSheet sheet,int row,int i) throws WriteException{
        WritableFont bold = new WritableFont(WritableFont.TAHOMA,12,WritableFont.NO_BOLD);//设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示
        WritableCellFormat titleFormate = new WritableCellFormat(bold);//生成一个单元格样式控制对象
        titleFormate.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
        titleFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
        titleFormate.setBorder(Border.BOTTOM,BorderLineStyle.THICK, Colour.GREY_50_PERCENT);
        sheet.setRowView(row, 450, false);//设置第一行的高度
//        titleFormate.setBackground(colour(i));
        return titleFormate;
    }


    private  WritableCellFormat  titleFormate01(WritableSheet sheet,int row,int i) throws WriteException{
        WritableFont bold = new WritableFont(WritableFont.ARIAL,15,WritableFont.BOLD);//设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示
        WritableCellFormat titleFormate = new WritableCellFormat(bold);//生成一个单元格样式控制对象
        titleFormate.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
        titleFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
        titleFormate.setBorder(Border.ALL,BorderLineStyle.DOUBLE, Colour.BLACK);
        sheet.setRowView(row, 600, false);//设置第一行的高度
//        titleFormate.setBackground(colour(i));
        return titleFormate;
    }

    private List<PayOrder> getPayOrder(String time){
        LambdaQueryWrapper<PayOrder> queryWrapper =  new QueryWrapper<PayOrder>().lambda();
        queryWrapper.eq(PayOrder::getOrderStatus,0);
        queryWrapper.ge(PayOrder::getTradeTime,time+" 0:00:00");
        queryWrapper.le(PayOrder::getTradeTime,time+" 23:59:59");
        return payOrderService.list(queryWrapper);
    }
    //
    private List<SystemOrderTrack>  getSysOrderTrack(String time,String merId){
        LambdaQueryWrapper<SystemOrderTrack> queryWrapper =  new QueryWrapper<SystemOrderTrack>().lambda();
        queryWrapper.eq(SystemOrderTrack::getMerId,merId);
//        queryWrapper.eq(SystemOrderTrack::getOrderTrackStatus,0 );
//        queryWrapper.like(SystemOrderTrack::getRefer,"/payApply");
        queryWrapper.ge(SystemOrderTrack::getTradeTime,time+" 0:00:00");
        queryWrapper.le(SystemOrderTrack::getTradeTime,time+" 23:59:59");
        return systemOrderTrackService.list(queryWrapper);
    }


    private Set<String> getTimeGroup(){
        PayOrderMapper payOrderMapper = (PayOrderMapper) payOrderService.getBaseMapper();
        Set<String> set = payOrderMapper.getTimeGroup();
        set.forEach(System.out::println);
        return set;
    }

    private List<AgentMerchantInfo>  getAgentMerInfo(){
        return agentMerchantInfoService.list();
    }

    private List<MerchantInfo>  getMerInfo(){
        return merchantInfoService.list();
    }

    private List<OrganizationInfo> getOrganizationInfo(){
        return organizationInfoService.list();
    }

    private List<ChannelInfo> getChannelInfo(){
        return channelInfoService.list();
    }

    private List<MerchantRate> getMerRate(){
        return merchantRateService.list();
    }

    private jxl.format.Colour colour(int i){
        jxl.format.Colour[] clours = {
                Colour.BRIGHT_GREEN,
                Colour.YELLOW ,
                Colour.PINK ,
                Colour.TURQUOISE ,
                Colour.GREEN,
                Colour.DARK_YELLOW,
                Colour.TEAL,
//                Colour.GREY_25_PERCENT,
//                Colour.GREY_50_PERCENT,
                Colour.PERIWINKLE,
                Colour.PLUM2,
                Colour.IVORY ,
//                Colour.LIGHT_TURQUOISE2 ,
                Colour.CORAL,
//                Colour.OCEAN_BLUE,
//                Colour.ICE_BLUE ,
                Colour.PINK2 ,
                Colour.YELLOW2,
                Colour.TURQOISE2,
//                Colour.VIOLET2 ,
//                Colour.DARK_RED2,
                Colour.TEAL2,
//                Colour.BLUE2,
//                Colour.SKY_BLUE,
                Colour.LIGHT_TURQUOISE,
                Colour.LIGHT_GREEN,
                Colour.VERY_LIGHT_YELLOW,
                Colour.PALE_BLUE,
                Colour.ROSE ,
                Colour.LAVENDER,
                Colour.TAN,
                Colour.LIGHT_BLUE,
                Colour.AQUA,
                Colour.LIME,
                Colour.GOLD,
                Colour.LIGHT_ORANGE ,
                Colour.ORANGE,
                Colour.BLUE_GREY ,
                Colour.GREY_40_PERCENT,
//                Colour.DARK_TEAL,
                Colour.SEA_GREEN ,
//                Colour.DARK_GREEN,
                Colour.OLIVE_GREEN ,
                Colour.BROWN,
                Colour.PLUM,
                Colour.INDIGO,
                Colour.GREY_80_PERCENT,
                Colour.AUTOMATIC
        };
        return  clours[i];
    }
}
