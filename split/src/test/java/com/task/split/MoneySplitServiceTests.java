package com.task.split;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashMap;

@SpringBootTest
public class MoneySplitServiceTests {

    @Test
    void getSplitInfo_CalculateSingleNumber_NormalCase(){
        //Arrange
        MoneySplitService service = new MoneySplitService();
        LinkedHashMap<Double, Integer> resultHashMap = new LinkedHashMap<>();
        resultHashMap.put(200.0, 1);
        resultHashMap.put(20.0, 1);
        resultHashMap.put(10.0, 1);
        resultHashMap.put(2.0, 2);
        resultHashMap.put(0.2, 1);
        resultHashMap.put(0.02, 1);
        resultHashMap.put(0.01, 1);
        SplitEntity expected = new SplitEntity(resultHashMap,"Anzahl");

        //Act
        SplitEntity result = service.getSplitInfo(234.23);

        //Assert
        Assertions.assertThat(result.getSplitInfo()).isEqualTo(expected.getSplitInfo());
        Assertions.assertThat(result.getResultType()).isEqualTo(expected.getResultType());

    }

    @Test
    void getSplitInfo_CalculateSingleNumber_EdgeCaseZero(){

        //Arrange
        MoneySplitService service = new MoneySplitService();
        LinkedHashMap<Double, Integer> resultHashMap = new LinkedHashMap<>();
        SplitEntity expected = new SplitEntity(resultHashMap,"Anzahl");

        //Act
        SplitEntity result = service.getSplitInfo(0);

        //Assert
        Assertions.assertThat(result.getSplitInfo()).isEqualTo(expected.getSplitInfo());
        Assertions.assertThat(result.getResultType()).isEqualTo(expected.getResultType());
    }
    @Test
    void getSplitInfo_CalculateTwoNumbers_NormalCase(){
        //Arrange
        MoneySplitService service = new MoneySplitService();
        LinkedHashMap<Double, Integer> resultHashMap = new LinkedHashMap<>();
        resultHashMap.put(200.0, 1);
        resultHashMap.put(20.0, -1);
        resultHashMap.put(10.0, 1);
        resultHashMap.put(5.0, -1);
        resultHashMap.put(2.0, 2);
        resultHashMap.put(0.2, 0);
        resultHashMap.put(0.1, -1);
        resultHashMap.put(0.02, 0);
        resultHashMap.put(0.01, 1);

        SplitEntity expected = new SplitEntity(resultHashMap,"Differenz");

        //Act
        service.getSplitInfo(234.23);
        SplitEntity result = service.getSplitInfo(45.32);

        //Assert
        Assertions.assertThat(result.getSplitInfo()).isEqualTo(expected.getSplitInfo());
        Assertions.assertThat(result.getResultType()).isEqualTo(expected.getResultType());
    }

}
