package com.example.news.util;

import com.example.news.BaseSpringTest;
import com.example.news.qpstest.QpsTest;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Ignore
class ReadWriteUtilTest extends BaseSpringTest {

    @Test
    void writeToFileInHomeDir() {
        ReadWriteUtil.writeToFileInHomeDir(MyFileUtil.getLogInHomeDir("test1"), QpsTest.QpsBean.builder().name("xxxyyy"+System.lineSeparator()+"yy").price(233).build());
    }

    @Test
    void readFromFileInHome() {
        File file = MyFileUtil.getFileInHomeDir("test1-2022-03-17_221635.log");
        QpsTest.QpsBean qpsBean = ReadWriteUtil.readObjectFromFileInHomeDir(file, QpsTest.QpsBean.class);
        System.out.println(qpsBean);
    }

    @Test
    void writeObjectsToFileInHomeDir() {
        List<QpsTest.QpsBean> qpsBeanList = new ArrayList<>();
        qpsBeanList.add(QpsTest.QpsBean.builder().name("aaa").price(124).build());
        qpsBeanList.add(QpsTest.QpsBean.builder().name("bbb"+System.lineSeparator()+"yy").price(233).build());
        qpsBeanList.add(QpsTest.QpsBean.builder().name("c\nx").price(232).build());
        qpsBeanList.add(QpsTest.QpsBean.builder().name("e\r\nee").price(156).build());
        ReadWriteUtil.writeObjectsToFileInHomeDir(MyFileUtil.getLogInHomeDir("test1"), qpsBeanList);

    }

    @Test
    void readObjectsFromFileInHomeDir() {
        File file = MyFileUtil.getFileInHomeDir("test1-2022-03-17_221820.log");
        List<QpsTest.QpsBean> qpsBeanList = ReadWriteUtil.readObjectsFromFileInHomeDir(file, QpsTest.QpsBean.class);
        System.out.println(qpsBeanList);
    }

}