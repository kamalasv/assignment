package org.angularjs.poc.dao;


import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public class AbstractSpringDao extends HibernateDaoSupport {
    

    @Autowired
    public void init(SessionFactory factory) {
        setSessionFactory(factory);
    } 
        
    /**
     * 
     * @param obj
     */
    public void saveOrUpdate(Object obj){
        Session session = null;
        session = getHibernateTemplate().getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(obj);
            tx.commit();
            session.close();
        }
        catch(Exception e)
        {
        	if(tx !=null)
        	{
        		tx.rollback();
        		tx=null;
        	}
        }
        finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
        
    }
    
    /**
     * 
     * @param clazz
     * @param id
     * @return
     */
    public Object find(Class<Object> clazz, Long id) {
        return getHibernateTemplate().get(clazz, id);
    }
    
    /**
     * 
     * @param clazz
     * @param id
     * @return
     */
    public Object find(Class<? extends Object> clazz, Integer id) {
        return getHibernateTemplate().get(clazz, id);
    }
    
    /**
     * 
     * @param clazz
     * @return
     */
    
    public List<? extends Object> findAll(Class<?> clazz) {
        return getHibernateTemplate().loadAll(clazz);
    }
    
    /**
     * 
     * @param criteria
     * @return
     */
    public List<? extends Object> findByCriteria(DetachedCriteria criteria) {
        return getHibernateTemplate().findByCriteria(criteria);
    }
    
    
    /**
     * 
     * @param queryString
     * @param paramName
     * @param value
     * @return
     */
    
    public List<Object> findByNamedParam(String queryString, String[] paramName, Object[] value) {
        
        return (List<Object>) getHibernateTemplate().findByNamedParam(queryString, paramName, value);
        
    }
    
    /**
     * 
     * @param query
     * @return
     */
    public List<Object> findByHql(final String hqlQuery) {
        
        HibernateCallback<Object> hibernateCallback = new HibernateCallback<Object>() {
            
            public List<Object> doInHibernate(Session session) {
                Query hibernateQuery = session.createQuery(hqlQuery);
                return hibernateQuery.setCacheable(true).list();
            }
        };
        
        List<Object> list = (List<Object>) getHibernateTemplate().execute(hibernateCallback);
        if(list == null) {
            return Collections.emptyList();
        }
        return list;
    }
    
    /**
     * 
     * @param query
     * @param pageNum
     * @param noOfRecords
     * @return
     */
    
    public List<Object> findByHqlWithLimit(final String hqlQuery, final int pageNum, final int noOfRecords) {
        
        HibernateCallback<?> hibernateCallback = new HibernateCallback<Object>() {
            
            public List<Object> doInHibernate(Session session) {
                Query hibernateQuery = session.createQuery(hqlQuery);
                
                if(pageNum != 0 && noOfRecords != 0) {
                    hibernateQuery.setMaxResults(noOfRecords > 0 ? noOfRecords : 10);
                    final int index = (pageNum - 1) < 0 ? 1 : (pageNum - 1);
                    hibernateQuery.setFirstResult(index * noOfRecords);
                }
                return hibernateQuery.setCacheable(true).list();
            }
        };
        
        List<Object> list = (List<Object>) getHibernateTemplate().execute(hibernateCallback);
        if(list == null) {
            return Collections.emptyList();
        }
        return list;
        
    }
    
    /**
     * 
     * @param query
     * @return
     */
    public Long findByHqlRecordsCount(final String hqlQuery) {
        
        HibernateCallback<?> hibernateCallback = new HibernateCallback<Object>() {
            
            public Object doInHibernate(Session session) {
                Query hibernateQuery = session.createQuery(hqlQuery);
                
                return (Long) hibernateQuery.setCacheable(true).uniqueResult();
            }
        };
        
        return (Long) getHibernateTemplate().execute(hibernateCallback);
        
    }
    
    /**
     * To get the single result with the type of String
     * 
     * @param hqlQuery
     * @return
     */
    public String findByHqlSingleResult(final String hqlQuery) {
        
        HibernateCallback<?> hibernateCallback = new HibernateCallback<Object>() {
            
            public Object doInHibernate(Session session) {
                Query hibernateQuery = session.createQuery(hqlQuery);
                
                return (String) hibernateQuery.setCacheable(true).uniqueResult();
            }
        };
        
        return (String) getHibernateTemplate().execute(hibernateCallback);
        
    }
    
    /**
     * 
     * @param sql
     * @return
     */
    
    public List<Object> findByNativeSQL(final String sql) {
        
        HibernateCallback<?> hibernateCallback = new HibernateCallback<Object>() {
            
            public List<Object> doInHibernate(Session session) {
                SQLQuery sqlQuery = session.createSQLQuery(sql);
                return sqlQuery.setCacheable(true).list();
            }
        };
        
        List<Object> list = (List<Object>) getHibernateTemplate().execute(hibernateCallback);
        if(list == null) {
            return Collections.emptyList();
        }
        return list;
        
    }
    
    /**
     * 
     * @param sql
     * @param pageNum
     * @param noOfRecords
     * @return
     */
    
    public List<Object> findByNativeSQLWithLimit(final String sql, final int pageNum, final int noOfRecords) {
        
        HibernateCallback<?> hibernateCallback = new HibernateCallback<Object>() {
            
            public List<Object> doInHibernate(Session session) {
                SQLQuery sqlQuery = session.createSQLQuery(sql);
                
                sqlQuery.setMaxResults(noOfRecords > 0 ? noOfRecords : 10);
                final int index = (pageNum - 1) < 0 ? 1 : (pageNum - 1);
                sqlQuery.setFirstResult(index * noOfRecords);
                
                return sqlQuery.setCacheable(true).list();
            }
        };
        
        List<Object> list = (List<Object>) getHibernateTemplate().execute(hibernateCallback);
        if(list == null) {
            return Collections.emptyList();
        }
        return list;
        
    }
    
    /**
     * 
     * @param query
     * @return
     */
    public Number findByNativeSQLRecordsCount(final String sql) {
        
        HibernateCallback<?> hibernateCallback = new HibernateCallback<Object>() {
            
            public Number doInHibernate(Session session) {
                SQLQuery sqlQuery = session.createSQLQuery(sql);
                
                return (Number) sqlQuery.setCacheable(true).uniqueResult();
            }
        };
        return (Number) getHibernateTemplate().execute(hibernateCallback);
        
    }   
}
