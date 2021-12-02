package com.example.news.buzz.dto.converter;

import com.example.news.BaseSpringTest;
import com.example.news.buzz.dto.Car;
import com.example.news.buzz.dto.CarDto;
import com.example.news.buzz.dto.CarType;
import org.junit.Test;


import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CarMapperTest extends BaseSpringTest {


    @Test
    public void shouldMapCarToDto() {
        //given
        Car car = new Car( "Morris", 5, CarType.SEDAN );

        //when
        CarDto carDto = CarMapper.INSTANCE.carToCarDto( car );

        //then
        assertThat( carDto ).isNotNull();
        assertThat( carDto.getMake() ).isEqualTo( "Morris" );
        //assertThat( carDto.getSeatCount() ).isEqualTo( 5 );
        assertThat( carDto.getType() ).isEqualTo( "SEDAN" );
    }

}