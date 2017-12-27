package com.cxxy.practice.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import com.cxxy.practice.response.Response;
import com.cxxy.practice.response.CentreCutPageResponse;
import com.cxxy.practice.response.ResponseConverter;

public final class ResponseBuilder extends Response {

    private ResponseBuilder() {}

    /**
     * spring data jpa 项目，转为分页响应对象 {@link CentreCutPageResponse}
     *
     * @param page      {@link Page}
     * @param converter 转换器
     * @return {@link CentreCutPageResponse}
     */
    public static <T, V> CentreCutPageResponse<V> toPageResponse(final Page<T> page, ResponseConverter<T, V> converter) {
        return toPageResponse(page.getNumber(), page.getTotalPages(), page.getSize(), (int) page.getTotalElements(), convert(page.getContent(), converter));
    }

    private static <S, T> List<T> convert(final Collection<S> sources, final ResponseConverter<S, T> converter) {
        if (null != sources && !sources.isEmpty()) {
            if (null == converter) {
                throw new NullPointerException("程序代码错误，converter转换器为null");
            }
            final List<T> result = new ArrayList<>(sources.size());
            for (S s : sources) {
                T v = converter.convert(s);
                if (v != null) {
                    result.add(v);
                }
            }
            return result;
        }
        return Collections.emptyList();
    }

    /**
     * spring data 项目，转为分页响应对象 {@link CentreCutPageResponse}
     *
     * @param page {@link Page}
     * @return {@link CentreCutPageResponse}
     */
    public static <V> CentreCutPageResponse<V> toPageResponse(final Page<V> page) {
        return toPageResponse(page.getNumber(), page.getTotalPages(), page.getSize(), (int) page.getTotalElements(), page.getContent());
    }

}
