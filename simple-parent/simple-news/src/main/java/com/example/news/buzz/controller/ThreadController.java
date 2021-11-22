package com.example.news.buzz.controller;


import com.example.news.buzz.dto.NewsThreadDto;
import com.example.news.buzz.dto.Person;
import com.example.news.buzz.dto.request.NewsThreadRequest;
import com.example.news.buzz.dto.response.NewsThreadResponse;
import com.example.news.buzz.dto.response.PersonResponse;
import com.example.news.buzz.service.impl.ThreadService;
import com.example.news.exception.BadRequestException;
import com.example.news.exception.CodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Tag(name = "This is a thread controller")
@Slf4j
@Controller
@RequestMapping(value = "/api/thread")
public class ThreadController {

    @Autowired
    ThreadService threadService;

    @RequestMapping(value = "/save", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public NewsThreadResponse saveNewsThread(@RequestBody NewsThreadRequest newsThreadRequest) {
        log.info("--->>NewsThreadRequest = {}", newsThreadRequest);

        NewsThreadDto newsThreadDto = new NewsThreadDto();
        BeanUtils.copyProperties(newsThreadRequest, newsThreadDto);
        NewsThreadDto resultNewThreadDto = threadService.saveNewsThread(newsThreadDto);
        NewsThreadResponse newsThreadResponse = new NewsThreadResponse();
        BeanUtils.copyProperties(resultNewThreadDto, newsThreadResponse);
        return newsThreadResponse;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public NewsThreadResponse saveNewsThread(@PathVariable Integer id) {
        log.info("--->>NewsThreadRequest search id = {}", id);

        NewsThreadDto resultNewThreadDto = threadService.findNewsThreadById(id);
        NewsThreadResponse newsThreadResponse = new NewsThreadResponse();
        BeanUtils.copyProperties(resultNewThreadDto, newsThreadResponse);
        return newsThreadResponse;

    }


}
