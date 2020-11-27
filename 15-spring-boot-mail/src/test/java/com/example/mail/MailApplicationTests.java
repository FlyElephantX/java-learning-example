package com.example.mail;

import com.example.mail.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MailApplicationTests {

    @Value("${mail.receive}")
    private String receive;

    @Autowired
    private MailService mailService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSimpleMail() throws Exception {
        mailService.sendTextMail(receive, "《稼说送张琥》---苏轼", "呜呼，吾子其去此而务学也哉！博观而约取，厚积而薄发，吾告子止于此矣。");
    }

    @Test
    public void testHtmlMail() throws Exception {
        String content = "<html>\n" +
                "<body>\n" +
                "    <h3>大江东去，浪淘尽，千古风流人物。故垒西边，人道是：三国周郎赤壁。乱石穿空，惊涛拍岸，卷起千堆雪。江山如画，一时多少豪杰。\n遥想公瑾当年，小乔初嫁了，雄姿英发。羽扇纶巾，谈笑间、强虏灰飞烟灭。故国神游，多情应笑我，早生华发。人间如梦，一樽还酹江月。</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail(receive, "《念奴娇·赤壁怀古》--苏轼", content);
    }

    @Test
    public void sendAttachmentsMail() {
        String filePath = "/Users/flyelephant/Desktop/Test.docx";
        mailService.sendAttachmentsMail(receive, "Word文件", "丙辰中秋⑵，欢饮达旦⑶，大醉，作此篇，兼怀子由⑷。明月几时有？把酒问青天⑸。不知天上宫阙⑹，今夕是何年？我欲乘风归去⑺，又恐琼楼玉宇⑻，高处不胜寒⑼。起舞弄清影⑽，何似在人间⑾？转朱阁，低绮户，照无眠⑿。不应有恨，何事长向别时圆⒀？人有悲欢离合，月有阴晴圆缺，此事古难全⒁。但愿人长久⒂，千里共婵娟⒃。", filePath);
    }

    @Test
    public void sendInlineResourceMail() {
        String rscId = "01";
        String content = "<html><body>哥谭之王--企鹅人：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "/Users/flyelephant/Desktop/企鹅人.jpeg";

        mailService.sendInlineResourceMail(receive, "哥谭", content, imgPath, rscId);
    }

}
