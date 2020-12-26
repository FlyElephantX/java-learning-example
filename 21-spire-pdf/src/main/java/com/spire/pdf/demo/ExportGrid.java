package com.spire.pdf.demo;

import com.spire.pdf.PdfBorders;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPaddings;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.graphics.*;
import com.spire.pdf.grid.PdfGrid;

import java.awt.*;


public class ExportGrid {

    public void createGrid() {
        //创建PDF文档
        PdfDocument doc = new PdfDocument();
        PdfPageBase page = doc.getPages().add();

        //创建PdfGrid对象
        PdfGrid grid = new PdfGrid();

        //设置单元格内边距、默认字体、字体颜色和默认背景色
        grid.getStyle().setCellPadding(new PdfPaddings(3,3,3,3));
        grid.getStyle().setFont(new PdfTrueTypeFont(new Font("宋体", Font.PLAIN,10), true));
        grid.getStyle().setTextBrush(PdfBrushes.getBlack());
        grid.getStyle().setBackgroundBrush(PdfBrushes.getLightGray());

        //创建PdfBorders对象，并设置颜色及粗细
        PdfBorders borders= new PdfBorders();
        borders.setAll(new PdfPen(PdfBrushes.getWhite(),1f));

        //定义数据
        String[] data = {"洲;国家;人口;世界人口占比;国旗",
                "亚洲;中国;1,391,190,000;18.2%; ",
                "亚洲;日本;126,490,000;1.66%; ",
                "欧洲;英国;65,648,054;0.86%; ",
                "欧洲;德国;82,665,600;1.08%; ",
                "北美洲; 加拿大; 37,119,000; 0.49%; ",
                "北美洲; 美国; 327,216,000; 4.29%; "
        };

        String[][] dataSource = new String[data.length][];
        for (int i = 0; i < data.length; i++) {
            dataSource[i] = data[i].split("[;]", -1);
        }

        //填充数据到表格
        grid.setDataSource(dataSource);

        //在表格最后一列填充图片
        grid.getRows().get(1).getCells().get(4).getStyle().setBackgroundImage(PdfImage.fromFile("/Users/flyelephant/Desktop/Java/GitHub/1.jpg"));
        grid.getRows().get(2).getCells().get(4).getStyle().setBackgroundImage(PdfImage.fromFile("/Users/flyelephant/Desktop/Java/GitHub/2.jpeg"));
        grid.getRows().get(3).getCells().get(4).getStyle().setBackgroundImage(PdfImage.fromFile("/Users/flyelephant/Desktop/Java/GitHub/3.jpg"));
        grid.getRows().get(4).getCells().get(4).getStyle().setBackgroundImage(PdfImage.fromFile("/Users/flyelephant/Desktop/Java/GitHub/4.jpeg"));
        grid.getRows().get(5).getCells().get(4).getStyle().setBackgroundImage(PdfImage.fromFile("/Users/flyelephant/Desktop/Java/GitHub/5.jpg"));
        grid.getRows().get(6).getCells().get(4).getStyle().setBackgroundImage(PdfImage.fromFile("/Users/flyelephant/Desktop/Java/GitHub/1.jpg"));

        //设置最后一列列宽
        grid.getColumns().get(grid.getColumns().getCount()-1).setWidth(60f);

        //纵向合并单元格
        grid.getRows().get(1).getCells().get(0).setRowSpan(2);
        grid.getRows().get(3).getCells().get(0).setRowSpan(2);
        grid.getRows().get(5).getCells().get(0).setRowSpan(2);

        for (int i = 0; i < data.length ; i++) {

            //设置每一行的高度
            grid.getRows().get(i).setHeight(30f);
            //设置第一列的背景色
            grid.getRows().get(i).getCells().get(0).getStyle().setBackgroundBrush(PdfBrushes.getDarkGray());
            //设置第一列的字体
            grid.getRows().get(i).getCells().get(0).getStyle().setFont(new PdfTrueTypeFont(new Font("Arial Unicode MS",Font.PLAIN,12),true));

            grid.getRows().get(i).getCells().get(1).getStyle().setFont(new PdfTrueTypeFont(new Font("Arial Unicode MS",Font.PLAIN,12),true));

            for (int j = 0; j < grid.getColumns().getCount(); j++) {

                //设置所有单元格的边框样式
                grid.getRows().get(i).getCells().get(j).getStyle().setBorders(borders);
                //设置所有单元格内的文字对齐方式
                grid.getRows().get(i).getCells().get(j).setStringFormat(new PdfStringFormat(PdfTextAlignment.Center,PdfVerticalAlignment.Middle));
                //设置第一行的字体
                grid.getRows().get(0).getCells().get(j).getStyle().setFont(new PdfTrueTypeFont(new Font("Arial Unicode MS",Font.PLAIN,12),true));
                //设置第一行的背景色
                grid.getRows().get(0).getCells().get(j).getStyle().setBackgroundBrush(PdfBrushes.getDarkGray());

            }
        }

        //绘制表格到PDF
        grid.draw(page,0,30);

        //保存文档
        doc.saveToFile("Grid.pdf");
        doc.close();

        System.out.println("PDF 导出成功");
    }
}
