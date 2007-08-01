/*
 Copyright (c) 2007 Bill Six
 
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
 
The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.
 
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */
package com.billsix.examples.atm.dataacess;

import java.util.List;
import java.lang.reflect.ParameterizedType;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Criterion;

/**
 *
 * @author Bill Six
 */
public class HibernateBaseDataMapper<T> implements BaseDataMapper<T>{
    
    public HibernateBaseDataMapper(SessionFactory sessionFactory) {
        _persistentClass =  (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        _sessionFactory = sessionFactory;
    }
    
    public T load(Long id) {
        T entity = (T) _sessionFactory.getCurrentSession().load(_persistentClass, id);
        Hibernate.initialize(entity);
        return entity;
    }
    
    public List<T> findAll() {
        return findByCriteria();
    }
    
    protected List<T> findByExample(T exampleInstance, String[] excludeProperty) {
        Criteria crit = _sessionFactory.getCurrentSession().createCriteria(_persistentClass);
        Example example =  Example.create(exampleInstance);
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        return crit.list();
    }
    
    protected List<T> findByCriteria(Criterion... criterion) {
        Criteria crit = _sessionFactory.getCurrentSession().createCriteria(_persistentClass);
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
    }
    
    public T saveOrUpdate(T entity) {
        _sessionFactory.getCurrentSession().saveOrUpdate(entity);
        return entity;
    }
    
    public void delete(T entity) {
        _sessionFactory.getCurrentSession().delete(entity);
    }
    
    public void flush() {
        _sessionFactory.getCurrentSession().flush();
    }
    
    public void clear() {
        _sessionFactory.getCurrentSession().clear();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        _sessionFactory = sessionFactory;
    }

    protected SessionFactory _sessionFactory ;
    private Class<T> _persistentClass;
}
