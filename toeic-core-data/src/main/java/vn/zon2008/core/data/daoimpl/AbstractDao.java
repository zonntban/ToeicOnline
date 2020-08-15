package vn.zon2008.core.data.daoimpl;

import org.hibernate.*;
import vn.zon2008.common.utils.HibernateUtil;
import vn.zon2008.core.data.dao.GenericDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zon2008 on 8/3/2020.
 */
public class AbstractDao<ID extends Serializable, T> implements GenericDao<ID, T> {
    private Class<T> persistentClass;

    public AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[1];
    }

    protected Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    public List<T> findAll() {
        Session session = getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            StringBuilder sql = new StringBuilder(" FROM ").append(this.getPersistentClassName());
            Query query = session.createQuery(sql.toString());
            List<T> resultList = query.list();
            transaction.commit();

            return resultList;
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    public T findByID(ID id) {
        Session session = getSession();
        Transaction transaction = null;
        T result = null;
        try {
            transaction = session.beginTransaction();
            result = (T)session.get(persistentClass, id);
            if(result == null){
                throw new ObjectNotFoundException(id, getPersistentClassName());
            }
            transaction.commit();
            return result;
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    public T update(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        T t = null;
        try{
            Object object = session.merge(entity);
            t = (T)object;
            transaction.commit();
            return t;
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    public Integer delete(List<ID> ids){
        Integer result = 0;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try{
            for(ID id:ids){
                T t = (T)session.get(persistentClass, id);
                session.delete(t);
                result++;
            }
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return result;
    }

    public List<T> findByProperty(String property, Object value, String orderColumn, boolean asc){
        Session session = getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            StringBuilder sql = new StringBuilder(" FROM ").append(this.getPersistentClassName());
            if(property != null && value != null)
                sql.append(" WHERE ").append(property).append(" = :value");
            if(orderColumn != null) {
                sql.append(" ORDER BY ").append(orderColumn);
                sql.append(asc ? " ASC " : " DESC ");
            }

            Query query = session.createQuery(sql.toString());
            if(property != null && value != null)
                query.setParameter("value", value);
            List<T> resultList = query.list();
            transaction.commit();

            return resultList;
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
    }


    private String getPersistentClassName() {
        return persistentClass.getSimpleName();
    }
}
