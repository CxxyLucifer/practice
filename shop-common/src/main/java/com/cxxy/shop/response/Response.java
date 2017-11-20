package com.cxxy.shop.response;

import com.cxxy.shop.dto.QueryResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Response {

    /**
     * 转为单Bean响应对象 {@link CentreResponse}
     *
     * @param result    原始对象
     * @param converter Bean转换器
     * @return {@link CentreResponse}
     */
    public static <T, V> CentreResponse<V> toResponse(T result, ResponseConverter<T, V> converter) {
        final CentreResponse<V> response = new CentreResponse<>();
        response.setData(null != result ? converter.convert(result) : null);
        return response;
    }

    /**
     * 转为单Bean响应对象 {@link CentreResponse}
     *
     * @param result 返回Bean对象
     * @return {@link CentreResponse}
     */
    public static <V> CentreResponse<V> toResponse(V result) {
        final CentreResponse<V> response = new CentreResponse<>();
        response.setData(result);
        return response;
    }

    /**
     * 转为单Bean响应对象 {@link CentreResponse}
     *
     * @param source    原始CentreResponse类型
     * @param converter Bean转换器
     * @return {@link CentreResponse}
     */
    public static <S, T> CentreResponse<T> toResponse(final CentreResponse<S> source, final ResponseConverter<S, T> converter) {
        return null != source ? toResponse(source.getData(), converter) : new CentreResponse<T>();
    }

    /**
     * 转为List<Bean>响应对象 {@link CentreListResponse}
     *
     * @param result    原始对象
     * @param converter Bean转换器
     * @return {@link CentreListResponse}
     */
    public static <T, V> CentreListResponse<V> toListResponse(List<T> result, ResponseConverter<T, V> converter) {
        final CentreListResponse<V> response = new CentreListResponse<>();
        response.setDataList(convert(result, converter));
        return response;
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
     * 转为List<Bean>响应对象 {@link CentreListResponse}
     *
     * @param result 返回Bean对象列表
     * @return {@link CentreListResponse}
     */
    public static <V> CentreListResponse<V> toListResponse(List<V> result) {
        final CentreListResponse<V> response = new CentreListResponse<>();
        response.setDataList(emptyIfNull(result));
        return response;
    }

    /**
     * 转为列表Bean响应对象 {@link CentreListResponse}
     *
     * @param source    原始CentreListResponse类型
     * @param converter Bean转换器
     * @return {@link CentreListResponse}
     */
    public static <S, T> CentreListResponse<T> toListResponse(final CentreListResponse<S> source, final ResponseConverter<S, T> converter) {
        return null != source ? toListResponse(source.getDataList(), converter) : new CentreListResponse<T>();
    }

    private static <T> List<T> emptyIfNull(final List<T> collection) {
        return collection == null ? Collections.<T>emptyList() : collection;
    }

    /**
     * 转为分页List<Bean>响应对象 {@link CentreCutPageResponse}
     *
     * @param result    {@link QueryResult}
     * @param converter Bean转换器
     * @return {@link CentreCutPageResponse}
     */
    public static <T, V> CentreCutPageResponse<V> toPageResponse(QueryResult<T> result, ResponseConverter<T, V> converter) {
        if (null != result) {
            return toPageResponse(result.getPageNum(), result.getPageSize(), result.getTotalCount(), result.getDataList(), converter);
        } else {
            return new CentreCutPageResponse<>();
        }
    }

    /**
     * 转为分页List<Bean>响应对象 {@link CentreCutPageResponse}
     *
     * @param pageNum    当前页
     * @param pageSize   每页条数
     * @param totalCount 记录总数
     * @param result     原始对象
     * @param converter  Bean转换器
     * @return {@link CentreCutPageResponse}
     */
    public static <T, V> CentreCutPageResponse<V> toPageResponse(int pageNum, int pageSize, int totalCount, List<T> result, ResponseConverter<T, V> converter) {
        final CentreCutPageResponse<V> response = new CentreCutPageResponse<>();
        response.setPageNum(pageNum);
        response.setPageSize(pageSize);
        response.setTotalCount(totalCount);
        response.setDataList(convert(result, converter));
        return response;
    }

    /**
     * 转为分页List<Bean>响应对象 {@link CentreCutPageResponse}
     *
     * @param result {@link QueryResult}
     * @return {@link CentreCutPageResponse}
     */
    public static <V> CentreCutPageResponse<V> toPageResponse(QueryResult<V> result) {
        if (null != result) {
            return toPageResponse(result.getPageNum(), result.getPageSize(), result.getTotalCount(), result.getDataList());
        } else {
            return new CentreCutPageResponse<>();
        }
    }

    /**
     * 转为分页List<Bean>响应对象 {@link CentreCutPageResponse}
     *
     * @param pageNum    当前页
     * @param pageSize   每页条数
     * @param totalCount 记录总数
     * @param result     原始对象
     * @return {@link CentreCutPageResponse}
     */
    public static <V> CentreCutPageResponse<V> toPageResponse(int pageNum, int pageSize, int totalCount, List<V> result) {
        final CentreCutPageResponse<V> response = new CentreCutPageResponse<>();
        response.setPageNum(pageNum);
        response.setPageSize(pageSize);
        response.setTotalCount(totalCount);
        response.setDataList(emptyIfNull(result));
        return response;
    }


    /**
     * 转为分页List<Bean>响应对象 {@link CentreCutPageResponse}
     *
     * @param pageNum    当前页
     * @param pageSize   每页条数
     * @param totalCount 记录总数
     * @param result     原始对象
     * @return {@link CentreCutPageResponse}
     */
    public static <V> CentreCutPageResponse<V> toPageResponse(int pageNum, int totalPage, int pageSize, int totalCount, List<V> result) {
        final CentreCutPageResponse<V> response = new CentreCutPageResponse<>();
        response.setPageNum(pageNum);
        response.setTotalPages(totalPage);
        response.setPageSize(pageSize);
        response.setTotalCount(totalCount);
        response.setDataList(emptyIfNull(result));
        return response;
    }

    /**
     * 转为分页List<Bean>响应对象 {@link CentreCutPageResponse}
     *
     * @param source    {@link CentreCutPageResponse}
     * @param converter Bean转换器
     * @return {@link CentreCutPageResponse}
     */
    public static <S, T> CentreCutPageResponse<T> toPageResponse(final CentreCutPageResponse<S> source, final ResponseConverter<S, T> converter) {
        return null != source ?
                toPageResponse(source.getPageNum(), source.getPageSize(), source.getTotalCount(), source.getDataList(), converter) :
                new CentreCutPageResponse<T>();
    }
}
