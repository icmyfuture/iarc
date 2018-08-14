package cn.icmyfuture.iarc.zzz.entity;

import cn.icmyfuture.iarc.zzz.JUnitTest;
import org.junit.Assert;
import org.junit.Test;

public class StudentTest extends JUnitTest {
    @Test
    public void setName(){
        Student student = new Student();
        String name = "ivan";
        student.setName(name);
        Assert.assertEquals(student.getName(), name);
    }
}
