package com.sd.sdhr.service.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.search.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

@Service
public class EmailSendUtil {

    @Autowired
    JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromUser;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${myfile.filePath}")
    private String filePath;


    /**
     * 发送简单html文本的邮箱验证码.
     * @return
     */
    public void sendSimpleHtmlMail(Map<String,String> map) throws MessagingException{
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
        Date parse = new Date();
        String nowDate=String.format("%tY年%tm月%td日", parse,parse,parse);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parse);
        calendar.add(Calendar.DATE,1);//明天
        String tomorrow=String.format("%tm月%td日", parse,parse);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setFrom(fromUser);
        //邮件主题
        helper.setSubject("沈阳东硕信息技术有限公司 聘任书");
        //组织的html内容
        String msgs=String.format("<P>沈阳东硕信息技术有限公司 聘任书 </P>" +
                        "<P> %s 先生/女士：</P>" +
                        "<p>我们很高兴向您正式发出聘任通知书，这是属于你的全新舞台，望你不忘初心，勇往直前！</p>" +
                        "请认真阅读以下内容，并确认以下相关细节，并在完全认可下述信息的情况下于%s上午9:30到公司报到，" +
                        "同时在确认邮件内容及员工手册内容后在%s 12:00前回复是否接受录用。如不接受录用请告知；如接受录用请在邮件中体现：" +
                        "<z style='color:red'>本人XXX已详尽阅读且完全理解员工手册各项内容，并接受录用</z> 等字样。逾期，公司将保留终止聘用的权利。<br><br>" +
                        "1、职位:  %s <br>2、工作地点：宝信软件 %s及部门所辖项目所在地现场 <br>3、报到地址：%s <br>" +
                        "4、工作时间：周一至周五，早上8:30-17:00。其他法定节假日公司将完全按照国家规定执行。 <br>" +
                        "5、劳动合同：依据《劳动合同法》，您将与公司签订正式的劳动合同为3年的固定期限劳动合同。 <br>" +
                        "6、试用期： <br> 在您任职期间，公司将会要求你通过规定的试用期，自您上班第一天算起3个月。试用期期间，工资按照转正工资的%s发放。" +
                        "公司将保留在试用期考核的权利以及依据考核情况决定是否终止劳动合同的权利。  公司将根据每月的考核结果来判定您的转正时间。公司保留在合法的前提下适当延长试用期的权利。 <br>" +
                        "7、入职体检：您在正式上班之前，要求您提前做一次全面的体检，只有体检合格，公司与您的之间的聘用关系方成立并有效，体检内容必须涵盖血常规、尿常规、肝功能、物理常规（耳鼻喉、血压、心率等）。 <br>" +
                        "8、薪酬福利： <br>（1）您的月基本工资为税前%s元。试用期工资%s元。 <br>（2）关于社会保险和公积金的缴费基数：入职后既为你办理社会保险及公积金（也就是社会上说的五险一金）。缴纳基数每年都会按照上海市公布的社会平均工资调整一次，所以这个基数每年都会调整。（总的来看是上浮）。 </P> <br>" +
                        "<b>其他事项说明：</b><P>一、沈阳东硕信息技术有限公司入职须知 <br>" +
                        "1、工资日:每月10号发工资 <br>2、工资卡:华夏银行（不限地区，但是必须是一类卡） <br>" +
                        "二、需要准备的材料 <br>1、身份证复印件正反面复印在一张A4纸上（4份） <br>2、华夏银行卡复印件 <br>3、体检报告一份（二级以上医院，或专业体检中心） <br>" +
                        "4、离职证明原件 <br>5、二寸照片4张（蓝色底） <br>6、毕业证、学位证及其他证件的复印件 <br>7、三方协议原件（应届生提供） <br>" +
                        "8、在校成绩单（应届生提供） <br>9、学生证复印件一份（应届生提供） <br>10、笔记本电脑一台 <br>以上资料除笔记本电脑外全部交给总务部，办理入职手续。 <br>" +
                        "提示说明：个人须提供的资料不齐全或虚假者不予办理入职手续。</P> <br><b>以上offer信息阅读完毕，确认无异议，请添加人事 俞经理的微信号：18019131681 </b> <br>" +
                        "日期：%s <br><br><br><img src='https://mail-online.nosdn.127.net/wzpmmc/5d9f993b26cdcaf85df67b1336028d2c.png'/><br>syds_liu|沈阳东硕信息技术有限公司<br>" +
                        "手机：17612129730<br>座机：024-23994399<br>邮箱：syds_liu@163.com<br>网址：www.dongshuoit.com <br>沈阳基地地址：沈阳市浑南区沈阳国际软件园A05区402室<br>邮编：110000<br>上海基地地址：上海市盘古路388号祥腾国际大厦5号楼703室 <br>邮编：201900<br>",
                map.get("userName"),map.get("arrivalDate"),tomorrow,map.get("job"),map.get("deptName"),map.get("jobAddress"),map.get("isDz"),map.get("salary"),map.get("probationPay"),nowDate);
        helper.setText(msgs, true);
        helper.setTo(map.get("email"));//对方邮箱
        //添加附件信息
        //String filePath="D:\\test_Jar\\syds.pdf";//本机附件地址
        FileSystemResource resource = new FileSystemResource(new File(filePath));
        helper.addAttachment("沈阳东硕-员工手册2022版.pdf",resource);
        mailSender.send(mimeMessage);

    }


    /**
     * 发送简单html文本的邮箱验证码
     */
    public void resceiveMail(String to,String text) throws Exception{
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";//ssl加密
        // 准备连接服务器的会话信息
        Properties props = new Properties();
        /*props.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.imap.socketFactory.fallback", "false");
        props.setProperty("mail.store.protocol", "imap");       // 使用imap协议
        props.setProperty("mail.imap.port", "993");           // 端口
        props.setProperty("mail.imap.host", "imap.qq.com");       // pop3服务器
        props.setProperty("mail.imap.auth", "true");*/

        props.setProperty("mail.store.protocol", "imap");
        props.setProperty("mail.imap.port", "465");
        props.setProperty("mail.imap.auth.plain.disable","true");


        // 创建Session实例对象
        Session session = Session.getInstance(props,null);
        //URLName url = new URLName("pop3", "pop.qq.com", 995, null, fromUser, password);
        Store store = session.getStore("imap");
        //store.connect(fromUser,password); //第三方登录所以这里的密码是授权密码而并非普通的登录密码

        store.connect("iamp.163.com",465,fromUser,password);


//      //获得收件箱
        Folder folder = store.getFolder("INBOX");
        /* Folder.READ_ONLY：只读权限
         * Folder.READ_WRITE：可读可写（可以修改邮件的状态）
         */
        folder.open(Folder.READ_WRITE); //打开收件箱
        //false 表示未读
        FlagTerm flagTerm = new FlagTerm(new Flags(Flags.Flag.SEEN),false);

        //根据时间搜索
        Calendar calendar = Calendar.getInstance();
        // 搜索1天前到今天收到的的所有邮件，根据时间筛选邮件
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        //SentDateTerm flagTerm = new SentDateTerm(ComparisonTerm.GE, new Date(calendar.getTimeInMillis()));

        //搜索发件人为“智联招聘“，而且邮件正文包含“Java工程师“的所有邮件//
        SearchTerm andTerm = new AndTerm( new FromStringTerm("招商"),new BodyTerm("先生"));
        // 搜索邮件内容包含"招聘"的邮件
        SearchTerm bodyTerm = new BodyTerm("同");

        //获得收件箱的邮件列表
        Message[] messages = folder.search(bodyTerm);
        // 打印不同状态的邮件数量
        System.out.println("收件箱中共" + messages.length + "封邮件!");
        System.out.println("收件箱中共" + folder.getUnreadMessageCount() + "封未读邮件!");
        System.out.println("收件箱中共" + folder.getNewMessageCount() + "封新邮件!");
        System.out.println("收件箱中共" + folder.getDeletedMessageCount() + "封已删除邮件!");

        for (int i = 0; i < messages.length; i++) {
            System.out.println("消息:"+messages[i].getSubject());
            String from = MimeUtility.decodeText(messages[i].getFrom()[0].toString());//邮件主题
            InternetAddress internetAddress = new InternetAddress(from);
            System.out.println("发件人：" + internetAddress.getPersonal() + '(' + internetAddress.getAddress() + ')');
            StringBuffer content = new StringBuffer(30);
            getMailTextContent(messages[i],content);
            System.out.println("邮件正文：" + (content.length() > 100 ? content.substring(0,100) + "..." : content));

        }


    }


    public static void getMailTextContent(Part part, StringBuffer content) throws MessagingException, IOException {
        //如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断
        boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;
        if (part.isMimeType("text/*") && !isContainTextAttach) {
            content.append(part.getContent().toString());
        } else if (part.isMimeType("message/rfc822")) {
            getMailTextContent((Part)part.getContent(),content);
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                getMailTextContent(bodyPart,content);
            }
        }
    }


    /**
     * 发送简单html文本的邮箱验证码
     */
    public void resceiveMailPop3(String to,String text) throws Exception{
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";//ssl加密
        // 准备连接服务器的会话信息
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "pop3");       // 协议
        props.setProperty("mail.pop3.port", "110");             // 端口
        props.setProperty("mail.pop3.host", "pop3.163.com");    // pop3服务器


        // 创建Session实例对象
        Session session = Session.getInstance(props);
        //URLName url = new URLName("pop3", "pop3.qq.com", 993, null, fromUser, "dltxczckvylvjijc");
        Store store = session.getStore("pop3");
        store.connect(fromUser,"awbcedkhhhjsggjj"); //第三方登录所以这里的密码是授权密码而并非普通的登录密码
        //获得收件箱
        Folder folder = store.getFolder("INBOX");
        /* Folder.READ_ONLY：只读权限
         * Folder.READ_WRITE：可读可写（可以修改邮件的状态）
         */
        folder.open(Folder.READ_WRITE); //打开收件箱
        //false 表示未读
        //FlagTerm flagTerm = new FlagTerm(new Flags(Flags.Flag.SEEN),false);

        //根据时间搜索
        Calendar calendar = Calendar.getInstance();
        // 搜索1天前到今天收到的的所有邮件，根据时间筛选邮件
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        SentDateTerm flagTerm = new SentDateTerm(ComparisonTerm.GE, new Date(calendar.getTimeInMillis()));

        //搜索发件人为“智联招聘“，而且邮件正文包含“Java工程师“的所有邮件//
        SearchTerm andTerm = new AndTerm( new FromStringTerm("招商"),new BodyTerm("先生"));
        // 搜索邮件内容包含"招聘"的邮件
        SearchTerm bodyTerm = new BodyTerm("武汉");

        //获得收件箱的邮件列表
        Message[] messages = folder.search(bodyTerm);
        // 打印不同状态的邮件数量
        System.out.println("收件箱中共" + messages.length + "封邮件!");
        System.out.println("收件箱中共" + folder.getUnreadMessageCount() + "封未读邮件!");
        System.out.println("收件箱中共" + folder.getNewMessageCount() + "封新邮件!");
        System.out.println("收件箱中共" + folder.getDeletedMessageCount() + "封已删除邮件!");

        for (int i = 0; i < messages.length; i++) {
            System.out.println("消息:"+messages[i].getSubject());
            String from = MimeUtility.decodeText(messages[i].getFrom()[0].toString());//邮件主题
            InternetAddress internetAddress = new InternetAddress(from);
            System.out.println("发件人：" + internetAddress.getPersonal() + '(' + internetAddress.getAddress() + ')');
        }


    }

}
