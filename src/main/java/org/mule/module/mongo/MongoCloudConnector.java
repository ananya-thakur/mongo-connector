/**
 * Mule MongoDB Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

/**
 * This file was automatically generated by the Mule Cloud Connector Development Kit
 */
package org.mule.module.mongo;

import org.mule.api.lifecycle.Initialisable;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.module.mongo.api.IndexOrder;
import org.mule.module.mongo.api.MongoClient;
import org.mule.module.mongo.api.MongoClientImpl;
import org.mule.module.mongo.api.WriteConcern;
import org.mule.tools.cloudconnect.annotations.Connector;
import org.mule.tools.cloudconnect.annotations.Operation;
import org.mule.tools.cloudconnect.annotations.Parameter;
import org.mule.tools.cloudconnect.annotations.Property;

import com.mongodb.DBObject;
import com.mongodb.MapReduceOutput;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

import java.util.Collection;

/**
 * A Mongo Connector Facade
 * @author flbulgarelli
 */
@Connector(namespacePrefix = "mongo")
public class MongoCloudConnector implements Initialisable
{
    @Property(name = "client-ref", optional = true)
    private MongoClient client;
    @Property(optional = true, defaultValue = "localhost")
    private String host;
    @Property(optional = true, defaultValue = "27017")
    private int port;
    @Property(optional = true, defaultValue = "test")
    private String database;

    // TODO auth
    // TODO document
    
    /**
     * Lists names of collections available at this database
     * 
     * {@code <list-collections/>}
     * @return the list of names of collections available at this database
     */
    @Operation
    public Collection<String> listCollections()
    {
        return client.listCollections();
    }

    /**
     * Answers if a collection exists given its name
     * 
     * {@code <exists-collection name="aColllection"/>}
     * @param collection the name of the collection
     * @return if the collection exists 
     */
    @Operation
    public boolean existsCollection(@Parameter String collection)
    {
        return client.existsCollection(collection);
    }

    /**
     * Deletes a collection and all the objects it contains.  
     * If the collection does not exist, does nothing.
     * 
     * {@code <drop-collection name="aCollection"/>}
     * @param collection the name of the collection to drop
     */
    @Operation
    public void dropCollection(@Parameter String collection)
    {
        client.dropCollection(collection);
    }

    /**
     * Creates a new collection. 
     * If the collection already exists, a MongoException will be thrown.
     * 
     * {@code <create-collection name="aCollection" capped="true"/>}
     * 
     * @param collection the name of the collection to create
     * @param capped if the collection will be capped 
     * @param maxObject the maximum number of documents the new collection is able to
     *            contain
     * @param size the maximum size of the new collection 
     */
    @Operation
    public void createCollection(@Parameter String collection,
                                 @Parameter(optional = true, defaultValue = "false") boolean capped,
                                 @Parameter(optional = true) Integer maxObjects,
                                 @Parameter(optional = true) Integer size)
    {
        client.createCollection(collection, capped, maxObjects, size);
    }
    
    /**
     * Inserts an object in a collection, setting its id if necessary.
     * 
     * {@code <insert-object collection="Employees" object="#[header:aBsonEmployee]" writeConcern="SAFE"/>}
     * @param collection the name of the collection where to insert the given object
     * @param dbObject the object to insert
     * @param writeConcern the optional write concern of insertion
     */
    @Operation
    public void insertObject(@Parameter String collection,
                             @Parameter(name = "object") DBObject dbObject,
                             @Parameter(optional = true, defaultValue = "DATABASE_DEFAULT") WriteConcern writeConcern)
    {
        client.insertObject(collection, dbObject, writeConcern);
    }

    /**
     * Updates objects that matches the given query. If parameter multi is set to true,
     * all the documents matching it will be updated. Otherwise, only the first document matching 
     * it will be updated 
     * 
     * {@code <update-object collection="#[map-payload:aCollectionName]" 
     *         query="#[variable:aBsonQuery]" object="#[variable:aBsonObject]" upsert="true"/>} 
     * @param collection the name of the collection to update
     * @param query the query object used to detect the element to update
     * @param dbObject the object that will replace that one which matches the query
     * @param upsert TODO
     * @param multi if all or just the first object matching the query will be updated
     * @param writeConcern the write concern used to update 
     */
    @Operation
    public void updateObject(@Parameter String collection,
                             @Parameter DBObject query,
                             @Parameter(name = "object") DBObject dbObject,
                             @Parameter(optional = true, defaultValue = "false") boolean upsert,
                             @Parameter(optional = true, defaultValue = "false") boolean multi,
                             @Parameter(optional = true, defaultValue = "DATABASE_DEFAULT") WriteConcern writeConcern)
    {
        client.updateObject(collection, query, dbObject, upsert, multi, writeConcern);
    }

    /**
     * Inserts or updates an object based on its object _id.
     *  
     * {@code <save-object 
     *          collection="#[map-payload:aCollectionName]"
     *          object="#[header:aBsonObject]"/>} 
     * @param collection
     * @param dbObject
     */
    @Operation
    public void saveObject(@Parameter String collection,
                           @Parameter(name = "object") DBObject dbObject,
                           @Parameter(optional = true, defaultValue = "DATABASE_DEFAULT") WriteConcern writeConcern)
    {
        client.saveObject(collection, dbObject, writeConcern);
    }

    /**
     * Removes all the objects that match the a given optional query. 
     * If query is not specified, all objects are removed. However, please notice that this is normally
     * less performant that dropping the collection and creating it and its indices again
     * 
     * 
     * {@code <remove-objects collection="#[map-payload:aCollectionName]" query="#[map-payload:aBsonQuery]"/>}
     * @param collection the collection whose elements will be removed 
     * @param query the query object. Objects that match it will be removed
     * @param writeConcern 
     */
    @Operation
    public void removeObjects(@Parameter String collection,
                              @Parameter(optional = true) DBObject query,
                              @Parameter(optional = true, defaultValue = "DATABASE_DEFAULT") WriteConcern writeConcern)
    {
        client.removeObjects(collection, query, writeConcern);
    }
    
    /**
     * Transforms a collection into a collection of aggregated groups, by
     * applying a supplied element-mapping function to each element, that transforms each one
     * into a key-value pair, grouping the resulting pairs by key, and finally 
     * reducing values in each group applying a suppling 'reduce' function.   
     * 
     * Each supplied function is coded in JavaScript.
     * 
     * Note that the correct way of writing those functions may not be obvious; please 
     * consult MongoDB documentation for writing them.
     *  
     * {@code  <map-reduce-objects 
     *      collection="myCollection"
     *      mapFunction="#[header:aJSMapFunction]"
     *      reduceFunction="#[header:aJSReduceFunction]"/>} 
     * @param collection the name of the collection to map and reduce
     * @param mapFunction a JavaScript encoded mapping function
     * @param reduceFunction a JavaScript encoded reducing function 
     * @param outputCollection the name of the output collection to write the results, replacing previous collection if existed,
     *          mandatory when results may be larger than 16MB. 
     *          If outputCollection is unspecified, the computation is performed in-memory and not persisted.  
     */
    @Operation
    public Iterable<DBObject> mapReduceObjects(@Parameter String collection,
                                               @Parameter String mapFunction,
                                               @Parameter String reduceFunction,
                                               @Parameter(optional = true) String outputCollection)
    {
        return client.mapReduceObjects(collection, mapFunction, reduceFunction, outputCollection);
    }
    
    /**
     * Counts the number of objects that match the given query. If no query
     * is passed, returns the number of elements in the collection
     * 
     * {@code <count-objects 
     *      collection="#[variable:aCollectionName]"
     *      query="#[variable:aBsonQuery]"/>}
     */
    @Operation
    public long countObjects(@Parameter String collection, @Parameter(optional = true) DBObject query)
    {
        return client.countObjects(collection, query);
    }

    /**
     * Finds all objects that match a given query. If no query is specified, all objects of the 
     * collection are retrieved. If no fields object is specified, all fields are retrieved. 
     * 
     * {@code <find-objects query="#[map-payload:aBsonQuery]" fields="#[header:aBsonFieldsSet]"/>}
     * @param collection
     * @param query the query object. If unspecified, all documents are returned
     * @param fields the fields to return. If unspecified, all fields are returned
     */
    @Operation
    public Iterable<DBObject> findObjects(@Parameter String collection,
                                          @Parameter(optional = true) DBObject query,
                                          @Parameter(optional = true) DBObject fields)
    {
        return client.findObjects(collection, query, fields);
    }

    /**
     * Finds the first object that matches a given query. 
     * Throws a {@link MongoException} if no one matches the given query 
     * 
     * {@code <find-one-object 
     *      query="#[variable:aBsonQuery]" 
     *      fields="#[map-payload:aBsonFieldsSet]"/>}   
     * @param collection
     * @param query
     * @param fields
     * @return a non-null DBObject that matches the query. 
     */
    @Operation
    public DBObject findOneObject(@Parameter String collection,
                                  @Parameter DBObject query,
                                  @Parameter DBObject fields)
    {
        return client.findOneObject(collection, query, fields);
    }
    
    /**
     * Creates a new index
     * 
     * {@code <create-index collection="myCollection" keys="#[header:aBsonFieldsSet]"/>}
     * @param the name of the collection where the index will be created
     * @param field the name of the field which will be indexed 
     * @param order the indexing order
     */
    @Operation
    public void createIndex(@Parameter String collection,
                            @Parameter String field,
                            @Parameter(optional = true, defaultValue = "ASC") IndexOrder order)
    {
        client.createIndex(collection, field, order);
    }
    
    /**
     * Drops an existing index
     * 
     * {@code <drop-index collection="myCollection" name="#[map-payload:anIndexName]"/>}
     * @param the name of the collection where the index is
     * @param index the name of the index to drop
     */
    @Operation
    public void dropIndex(@Parameter String collection, @Parameter String index)
    {
        client.dropIndex(collection, index);
    }
    
    /**
     * List existent indices in a collection
     * 
     * {@code <drop-index collection="myCollection" name="#[map-payload:anIndexName]"/>}
     * @param the name of the collection 
     */
    @Operation
    public Collection<DBObject> listIndices(@Parameter String collection)
    {
        return client.listIndices(collection);
    }

    public void initialise() throws InitialisationException
    {
        if (client == null)
        {
            // FIXME get from a weak hashmasp and use Holder
            try
            {
                Mongo m = new Mongo(host, port);
                client = new MongoClientImpl(m.getDB(database));
            }
            catch (Exception e)
            {
                throw new InitialisationException(e, this);
            }
        }
    }

    public MongoClient getClient()
    {
        return client;
    }

    public void setClient(MongoClient client)
    {
        this.client = client;
    }

    public String getDatabase()
    {
        return database;
    }

    public void setDatabase(String database)
    {
        this.database = database;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }
    
}
