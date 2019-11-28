package org.yinxianren.com.netty.nio;

import org.junit.Test;
import org.yinxianren.com.netty.tools.Println;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class TestIntBuffer implements Println {

    /**
     *  测试 buffer
     */
    @Test
    public void test_01(){
        //初始化
        IntBuffer  intBuffer = IntBuffer.allocate(15);
        //赋值
        for(int i=0;i<intBuffer.capacity();i++){
            intBuffer.put(i*15);
        }
        //读写切换
        intBuffer.flip();
        //读数据
        while(intBuffer.hasRemaining()){
            println(intBuffer.get());
        }
    }

    /**
     *   测试channelFile
     */
    @Test
    public void test_02() throws IOException {
        //创建一个文件流
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\data\\logs\\test.log");
        //包装成通道流
        FileChannel fileChannel = fileOutputStream.getChannel();
        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //获取数据
        String src = msg01();
        int position = 0;
        int limit = byteBuffer.capacity()/3;
        int size = src.length();
        //建数据放入数据
        while(true){
            byteBuffer.clear();
            byte[] bytes = src.substring(position,limit).getBytes();
            if(byteBuffer.position() == byteBuffer.limit() && byteBuffer.limit() > 0 ){
                byteBuffer.limit(bytes.length);
            }
            byteBuffer.put(bytes);
            byteBuffer.flip();
            //建缓冲区的数据写的通道里
            fileChannel.write(byteBuffer);
            if(limit == size){
                //关闭资源
                fileChannel.close();
                fileOutputStream.close();
                break;
            }
            position = limit;
            if(size-limit >= byteBuffer.capacity() ){
                limit = limit +  byteBuffer.capacity()/3;
            }else{
                limit =  limit+(size-limit);
            }
        }
    }

    /**
     *   nio 读文件
     * @throws Exception
     */
    @Test
    public void test_03() throws Exception{
        FileInputStream inputStream = new FileInputStream(new File("E:\\log\\spring.log"));
        FileChannel channel = inputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(5);
        while ( -1 != channel.read(buffer) ){
            buffer.flip();
            print(new String(buffer.array()));
            buffer.clear();
        }
        channel.close();
        inputStream.close();
    }

    /**
     *   测试 nio拷贝文件
     */
    @Test
    public void test_04()throws Exception{
        FileInputStream inputStream = new FileInputStream(new File("E:\\log\\spring.log"));
        FileChannel inputChannel = inputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(18);
        FileOutputStream outputStream = new FileOutputStream(new File("E:\\data\\logs\\spring.log"));
        FileChannel outChannel = outputStream.getChannel();
        while (-1 != inputChannel.read(buffer) ){
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }
        outChannel.close();
        outputStream.close();
        inputChannel.close();
        inputStream.close();
    }

    /**
     *  测试通道流之间拷贝数据
     */
    @Test
    public void test_05()throws Exception{
        FileInputStream inputStream = new FileInputStream(new File("E:\\log\\spring.log"));
        FileChannel inputChannel = inputStream.getChannel();
        FileOutputStream outputStream = new FileOutputStream(new File("E:\\data\\logs\\spring3.log"));
        FileChannel outChannel = outputStream.getChannel();
        //拷贝文件
//        outChannel.transferFrom(inputChannel,0,inputChannel.size());
        inputChannel.transferTo(0,inputChannel.size(),outChannel);

        outChannel.close();
        outputStream.close();
        inputChannel.close();
        inputStream.close();
    }



    private String msg01(){
        return "====================================================================\n" +
                "该订单不在钱包处理范围:\n" +
                "\n" +
                "队列中的订单:[PayOrderInfoTable(id=null, platformOrderId=B12RXH360*76f386c532284b4c910298b9d9fea0131574759683721, channelOrderId=19060000001375, merchantId=M1573460997327, terminalMerId=10015431686029844, merOrderId=4401510121620346, identityType=1, identityNum=350425199006015588, bankCode=ABC, bankCardType=1, bankCardNum=6221800385220693718, bankCardPhone=17605083553, validDate=2011, securityCode=123, deviceId=fd3123, deviceType=1, macAddr=fa:s21:fef, channelId=CH1572004300660, regPlatformOrderId=B3RXH3*8ae55e639e0f4172bf9c2c735a046dcf1574747750647, cardPlatformOrderId=B6RXH2*b37e5bf4b62a43828f54ec71be966b441574748632109, \n" +
                "\n" +
                "bussType=b12, \n" +
                "\n" +
                "productId=RH_QUICKPAY_SMALL_REP, productFee=0.00, currency=CNY, amount=-171.50, inAmount=-168.92, payFee=1.50, terFee=-2.58, channelRate=0.30, channelFee=-0.52, agentRate=0.01, agentFee=-0.02, merRate=0.60, merFee=-1.03, platformIncome=0.49, settleCycle=T0, settleStatus=1, channelRespResult={\"retcode\":\"FAIL\",\"retmsg\":\"商户号101005129021对应商户信息不存在\"}, crossRespResult={\"channelResponseMsg\":\"{\\\"retcode\\\":\\\"FAIL\\\",\\\"retmsg\\\":\\\"商户号101005129021对应商户信息不存在\\\"}\",\"crossResponseMsg\":\"快捷交易查询失败:商户号101005129021对应商户信息不存在\",\"crossStatusCode\":1,\"crossStatusMsg\":\"失败\",\"errorCode\":\"RXH99999\",\"errorMsg\":\"其他错误\"}, \n" +
                "\n" +
                "notifyStatus=1, status=7, \n" +
                "\n" +
                "notifyUrl=http://192.168.1.113:8090/pay/notify, returnUrl=http://192.168.1.113:8090/pay/notify, createTime=Tue Nov 26 17:14:43 CST 2019, updateTime=Tue Nov 26 17:14:43 CST 2019, smsCode=111111, merOrderIdCollect=null, statusCollect=null, bussTypeCollect=null, beginTime=null, endTime=null, organizationId=null, pageNum=null, pageSize=null)]\n" +
                "\n" +
                "数据库的订单:[PayOrderInfoTable(id=801, platformOrderId=B12RXH360*76f386c532284b4c910298b9d9fea0131574759683721, channelOrderId=19060000001375, merchantId=M1573460997327, terminalMerId=10015431686029844, merOrderId=4401510121620346, identityType=1, identityNum=350425199006015588, bankCode=ABC, bankCardType=1, bankCardNum=6221800385220693718, bankCardPhone=17605083553, validDate=2011, securityCode=123, deviceId=fd3123, deviceType=1, macAddr=fa:s21:fef, channelId=CH1572004300660, regPlatformOrderId=B3RXH3*8ae55e639e0f4172bf9c2c735a046dcf1574747750647, cardPlatformOrderId=B6RXH2*b37e5bf4b62a43828f54ec71be966b441574748632109, \n" +
                "\n" +
                "bussType=b12, productId=RH_QUICKPAY_SMALL_REP, productFee=0.00, currency=CNY, amount=-171.50, inAmount=-168.92, payFee=1.50, terFee=-2.58, channelRate=0.30, channelFee=-0.52, agentRate=0.01, agentFee=-0.02, merRate=0.60, merFee=-1.03, platformIncome=0.49, settleCycle=T0, settleStatus=1, channelRespResult={\"appid\":\"6666678\",\"fintime\":\"20190628100547\",\"retcode\":\"SUCCESS\",\"retmsg\":\"处理成功\",\"sign\":\"B64DFBAD0AFEEC07EBF5F88D7AF04677\",\"trxid\":\"19060000001375\",\"trxstatus\":\"1999\"}, crossRespResult=CrossResponseMsgDTO(crossStatusCode=0, crossStatusMsg=成功, crossResponseMsg=需获取短信验证码,进行下一步确认操作, channelOrderId=null, channelResponseTime=null, channelResponseMsg={\"appid\":\"6666678\",\"fintime\":\"20190628100547\",\"retcode\":\"SUCCESS\",\"retmsg\":\"处理成功\",\"sign\":\"B64DFBAD0AFEEC07EBF5F88D7AF04677\",\"trxid\":\"19060000001375\",\"trxstatus\":\"1999\"}, errorCode=null, errorMsg=null), \n" +
                "\n" +
                "notifyStatus=1, status=0,\n" +
                "\n" +
                " notifyUrl=http://192.168.1.113:8090/pay/notify, returnUrl=http://192.168.1.113:8090/pay/notify, createTime=Tue Nov 26 17:14:44 CST 2019, updateTime=Tue Nov 26 17:14:44 CST 2019, smsCode=null, merOrderIdCollect=null, statusCollect=null, bussTypeCollect=null, beginTime=null, endTime=null, organizationId=null, pageNum=null, pageSize=null)]\n" +
                "====================================================================";
    }
}
