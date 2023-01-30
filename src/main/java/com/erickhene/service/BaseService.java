package com.erickhene.service;

import java.util.List;

import com.erickhene.dto.GlobalResponse;

public interface BaseService<T> {
    GlobalResponse<List<T>> getAll();

    GlobalResponse<T> create(T data);

    GlobalResponse<T> getByUuid(String id);

    GlobalResponse<T> update(String id, T data);
}
