package com.erickhene.service;

import java.util.List;

import com.erickhene.dto.GlobalResponse;
import com.erickhene.dto.request.DataTableReq;

public interface BaseService<T> {
    GlobalResponse<List<T>> getAll(DataTableReq dataTableReq);

    GlobalResponse<T> create(T data);

    GlobalResponse<T> getByUuid(String id);

    GlobalResponse<T> update(String id, T data);

    GlobalResponse<Boolean> delete(String id);
}
