
package org.mule.module.mongo.processors;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import com.mongodb.DBObject;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.processor.MessageProcessor;
import org.mule.module.mongo.MongoCloudConnector;
import org.mule.module.mongo.api.WriteConcern;
import org.mule.module.mongo.connectivity.MongoCloudConnectorConnectionManager;
import org.mule.security.oauth.callback.ProcessCallback;


/**
 * UpdateObjectsByFunctionMessageProcessor invokes the {@link org.mule.module.mongo.MongoCloudConnector#updateObjectsByFunction(java.lang.String, java.lang.String, com.mongodb.DBObject, com.mongodb.DBObject, boolean, boolean, org.mule.module.mongo.api.WriteConcern)} method in {@link MongoCloudConnector }. For each argument there is a field in this processor to match it.  Before invoking the actual method the processor will evaluate and transform where possible to the expected argument type.
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.0-SNAPSHOT", date = "2014-02-14T12:14:34-06:00", comments = "Build UNKNOWN_BUILDNUMBER")
public class UpdateObjectsByFunctionMessageProcessor
    extends AbstractConnectedProcessor
    implements MessageProcessor
{

    protected Object collection;
    protected String _collectionType;
    protected Object function;
    protected String _functionType;
    protected Object query;
    protected DBObject _queryType;
    protected Object element;
    protected DBObject _elementType;
    protected Object upsert;
    protected boolean _upsertType;
    protected Object multi;
    protected boolean _multiType;
    protected Object writeConcern;
    protected WriteConcern _writeConcernType;

    public UpdateObjectsByFunctionMessageProcessor(String operationName) {
        super(operationName);
    }

    /**
     * Obtains the expression manager from the Mule context and initialises the connector. If a target object  has not been set already it will search the Mule registry for a default one.
     * 
     * @throws InitialisationException
     */
    public void initialise()
        throws InitialisationException
    {
    }

    @Override
    public void start()
        throws MuleException
    {
        super.start();
    }

    @Override
    public void stop()
        throws MuleException
    {
        super.stop();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    /**
     * Sets element
     * 
     * @param value Value to set
     */
    public void setElement(Object value) {
        this.element = value;
    }

    /**
     * Sets writeConcern
     * 
     * @param value Value to set
     */
    public void setWriteConcern(Object value) {
        this.writeConcern = value;
    }

    /**
     * Sets query
     * 
     * @param value Value to set
     */
    public void setQuery(Object value) {
        this.query = value;
    }

    /**
     * Sets multi
     * 
     * @param value Value to set
     */
    public void setMulti(Object value) {
        this.multi = value;
    }

    /**
     * Sets collection
     * 
     * @param value Value to set
     */
    public void setCollection(Object value) {
        this.collection = value;
    }

    /**
     * Sets upsert
     * 
     * @param value Value to set
     */
    public void setUpsert(Object value) {
        this.upsert = value;
    }

    /**
     * Sets function
     * 
     * @param value Value to set
     */
    public void setFunction(Object value) {
        this.function = value;
    }

    /**
     * Invokes the MessageProcessor.
     * 
     * @param event MuleEvent to be processed
     * @throws Exception
     */
    public MuleEvent doProcess(final MuleEvent event)
        throws Exception
    {
        Object moduleObject = null;
        try {
            moduleObject = findOrCreate(MongoCloudConnectorConnectionManager.class, true, event);
            final String _transformedCollection = ((String) evaluateAndTransform(getMuleContext(), event, UpdateObjectsByFunctionMessageProcessor.class.getDeclaredField("_collectionType").getGenericType(), null, collection));
            final String _transformedFunction = ((String) evaluateAndTransform(getMuleContext(), event, UpdateObjectsByFunctionMessageProcessor.class.getDeclaredField("_functionType").getGenericType(), null, function));
            final DBObject _transformedQuery = ((DBObject) evaluateAndTransform(getMuleContext(), event, UpdateObjectsByFunctionMessageProcessor.class.getDeclaredField("_queryType").getGenericType(), null, query));
            final DBObject _transformedElement = ((DBObject) evaluateAndTransform(getMuleContext(), event, UpdateObjectsByFunctionMessageProcessor.class.getDeclaredField("_elementType").getGenericType(), null, element));
            final Boolean _transformedUpsert = ((Boolean) evaluateAndTransform(getMuleContext(), event, UpdateObjectsByFunctionMessageProcessor.class.getDeclaredField("_upsertType").getGenericType(), null, upsert));
            final Boolean _transformedMulti = ((Boolean) evaluateAndTransform(getMuleContext(), event, UpdateObjectsByFunctionMessageProcessor.class.getDeclaredField("_multiType").getGenericType(), null, multi));
            final WriteConcern _transformedWriteConcern = ((WriteConcern) evaluateAndTransform(getMuleContext(), event, UpdateObjectsByFunctionMessageProcessor.class.getDeclaredField("_writeConcernType").getGenericType(), null, writeConcern));
            ProcessTemplate<Object, Object> processTemplate = ((ProcessAdapter<Object> ) moduleObject).getProcessTemplate();
            processTemplate.execute(new ProcessCallback<Object,Object>() {


                public List<Class<? extends Exception>> getManagedExceptions() {
                    return Arrays.asList(((Class<? extends Exception> []) new Class[] {IllegalStateException.class }));
                }

                public boolean isProtected() {
                    return false;
                }

                public Object process(Object object)
                    throws Exception
                {
                    ((MongoCloudConnector) object).updateObjectsByFunction(_transformedCollection, _transformedFunction, _transformedQuery, _transformedElement, _transformedUpsert, _transformedMulti, _transformedWriteConcern);
                    return null;
                }

            }
            , this, event);
            return event;
        } catch (Exception e) {
            throw e;
        }
    }

}