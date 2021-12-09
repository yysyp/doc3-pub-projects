package com.example.news.job.service;

import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class PageProcessorTemplate<T> {

    public void processByPage(ProcessorParam processorParam, PageCallback<T> callback) {
        int pageIndex = 0;
        boolean hasMorePages = true;
        int maxProcessPage = processorParam.getMaxProcessPages();

        while (pageIndex < maxProcessPage && hasMorePages) {
            pageIndex++;
            Sort sort = processorParam.getSort() != null? processorParam.getSort() : Sort.unsorted();
            Pageable pageable = PageRequest.of(0, processorParam.getPageSize(), sort);
            List<T> tList = callback.fetchPageData(pageable);
            if (CollectionUtils.isEmpty(tList)) {
                return;
            }
            hasMorePages = tList.size() == processorParam.getPageSize();
            callback.processPage(tList);
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class ProcessorParam {
        private int pageSize;
        private int maxProcessPages;
        private Sort sort;
    }

    public interface PageCallback<T> {
        List<T> fetchPageData(Pageable pageable);
        void processPage(List<T> tList);
    }

}
