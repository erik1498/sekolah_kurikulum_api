package com.erickhene.dto.request;

import com.erickhene.util.ObjectMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DataTableReq{
    private String search;
    private Integer pageNumber;
    private Integer pageSize;

    public String getSearch() {
        return search == null ? "" : search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Integer getPageNumber() {
        return pageNumber == null ? 0 : pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize == null ? 10 : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    private static DataTableReq generateNew(){
        return new DataTableReq();
    }

    public static Map<String, Object> generateHashMap(DataTableReq dataTableReq){
        Map<String, Object> parameterMap = new HashMap<>();
        dataTableReq = dataTableReq == null ? generateNew() : dataTableReq;
        parameterMap.put("size", dataTableReq.getPageSize());
        parameterMap.put("number", dataTableReq.getPageNumber() * dataTableReq.getPageSize());
        parameterMap.put("search", dataTableReq.getSearch());
        log.info("DataTable = number {}, size {}, search {}", parameterMap.get("number"), parameterMap.get("size"), parameterMap.get("search"));
        return parameterMap;
    }

    public static Pageable generatePageableData(DataTableReq dataTableReq){
        dataTableReq = dataTableReq == null ? generateNew() : dataTableReq;
        log.info("DataTable = number {}, size {}, search {}", dataTableReq.getPageNumber(), dataTableReq.getPageSize(), dataTableReq.getSearch());
        return PageRequest.of(dataTableReq.getPageNumber(), dataTableReq.getPageSize());
    }
}
