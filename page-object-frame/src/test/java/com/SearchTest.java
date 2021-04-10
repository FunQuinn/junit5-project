package com;

import org.junit.jupiter.api.Test;
import util.WebMainPage;

/**
 * @Author FunQuinn
 * @Description:
 * @Date 2020/12/3 23:13
 */
public class SearchTest {
    @Test
    void search(){
        WebMainPage main = new WebMainPage();
        main.search().search("selenium");
    }
}
