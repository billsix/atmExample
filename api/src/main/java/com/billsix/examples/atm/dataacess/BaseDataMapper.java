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

/**
 *  Acts as a Layer Supertype for all Data Mappers
 *
 *
 *  Responsiblity - Data Mapper through which creates, retrieves, updates, or
 *    deletes a subgraph of the domain model object graph.  All operations on
 *    the object graph start with T nodes
 *
 *
 *  Any implementation of BaseDataMapper must be a Data Mapper whose fetched objects
 *  are thread-safe and transparently implement the Unit of Work pattern.
 *
 * @author Bill Six
 */
public interface BaseDataMapper<T> {
    /**
     *
     * @param id The identity of the object
     * @return The object of class <i>T</i> with id <i>id</i>
     */
    public T load(Long id);
    /**
     *
     * @return A List of all instances of <i>T</i>
     */
    public List<T> findAll() ;
    /**
     * Saves entity if it is not in the data store.  Updates the entity if it is
     * in the data store.
     *
     * @param entity
     * @return
     */
    public T saveOrUpdate(T entity) ;
    /**
     *  Deletes the entity from the data store.
     *
     * @param entity
     */
    public void delete(T entity) ;
    
    /**
     *  Clears the local cache.
     *
     * @return
     */
    public void clear() ;
}
