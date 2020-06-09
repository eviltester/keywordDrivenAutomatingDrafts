package com.eviltester.keyword.initial;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CanExecuteKeywords {

    @Test
    public void canExecuteKeywords(){

        List<String> keywordScript = new ArrayList();

        keywordScript.add("open \t https://testpages.herokuapp.com/");
        keywordScript.add("sleep \t 2000");
        keywordScript.add("close");



    }

}
