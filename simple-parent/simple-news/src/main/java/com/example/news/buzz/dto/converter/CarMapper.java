package com.example.news.buzz.dto.converter;

import com.example.news.buzz.dto.Car;
import com.example.news.buzz.dto.CarDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper( CarMapper.class );

    //@Mapping(source = "numberOfSeats", target = "seatCount")
    CarDto carToCarDto(Car car);

}
