package vn.zon2008.core.data.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zon2008 on 8/3/2020.
 */
public interface GenericDao<ID extends Serializable, T> {
    List<T> findAll();

    T findByID(ID id);

    T update(T entity);

    Integer delete(List<ID> id);

    List<T> findByProperty(String property, Object value, String orderColumn, boolean asc);
}
