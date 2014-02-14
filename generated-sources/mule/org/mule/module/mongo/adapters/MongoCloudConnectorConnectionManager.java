
package org.mule.module.mongo.adapters;

import org.apache.commons.pool.KeyedPoolableObjectFactory;
import org.apache.commons.pool.impl.GenericKeyedObjectPool;
import org.mule.api.Capabilities;
import org.mule.api.Capability;
import org.mule.api.ConnectionManager;
import org.mule.api.MuleContext;
import org.mule.api.construct.FlowConstruct;
import org.mule.api.context.MuleContextAware;
import org.mule.api.lifecycle.Disposable;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.lifecycle.Startable;
import org.mule.api.lifecycle.Stoppable;
import org.mule.config.PoolingProfile;
import org.mule.module.mongo.MongoCloudConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A {@code MongoCloudConnectorConnectionManager} is a wrapper around {@link MongoCloudConnector } that adds connection management capabilities to the pojo.
 * 
 */
public class MongoCloudConnectorConnectionManager
    implements Capabilities, ConnectionManager<MongoCloudConnectorConnectionManager.ConnectionKey, MongoCloudConnectorLifecycleAdapter> , MuleContextAware, Initialisable
{

    /**
     * 
     */
    private String username;
    /**
     * 
     */
    private String password;
    private String host;
    private int port;
    private String database;
    private Integer connectionsPerHost;
    private Integer threadsAllowedToBlockForConnectionMultiplier;
    private Integer maxWaitTime;
    private Integer connectTimeout;
    private Integer socketTimeout;
    private Boolean autoConnectRetry;
    private Boolean slaveOk;
    private Boolean safe;
    private Integer w;
    private Integer wtimeout;
    private Boolean fsync;
    private static Logger logger = LoggerFactory.getLogger(MongoCloudConnectorConnectionManager.class);
    /**
     * Mule Context
     * 
     */
    private MuleContext muleContext;
    /**
     * Flow construct
     * 
     */
    private FlowConstruct flowConstruct;
    /**
     * Connector Pool
     * 
     */
    private GenericKeyedObjectPool connectionPool;
    protected PoolingProfile connectionPoolingProfile;

    /**
     * Sets host
     * 
     * @param value Value to set
     */
    public void setHost(String value) {
        this.host = value;
    }

    /**
     * Retrieves host
     * 
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Sets port
     * 
     * @param value Value to set
     */
    public void setPort(int value) {
        this.port = value;
    }

    /**
     * Retrieves port
     * 
     */
    public int getPort() {
        return this.port;
    }

    /**
     * Sets database
     * 
     * @param value Value to set
     */
    public void setDatabase(String value) {
        this.database = value;
    }

    /**
     * Retrieves database
     * 
     */
    public String getDatabase() {
        return this.database;
    }

    /**
     * Sets connectionsPerHost
     * 
     * @param value Value to set
     */
    public void setConnectionsPerHost(Integer value) {
        this.connectionsPerHost = value;
    }

    /**
     * Retrieves connectionsPerHost
     * 
     */
    public Integer getConnectionsPerHost() {
        return this.connectionsPerHost;
    }

    /**
     * Sets threadsAllowedToBlockForConnectionMultiplier
     * 
     * @param value Value to set
     */
    public void setThreadsAllowedToBlockForConnectionMultiplier(Integer value) {
        this.threadsAllowedToBlockForConnectionMultiplier = value;
    }

    /**
     * Retrieves threadsAllowedToBlockForConnectionMultiplier
     * 
     */
    public Integer getThreadsAllowedToBlockForConnectionMultiplier() {
        return this.threadsAllowedToBlockForConnectionMultiplier;
    }

    /**
     * Sets maxWaitTime
     * 
     * @param value Value to set
     */
    public void setMaxWaitTime(Integer value) {
        this.maxWaitTime = value;
    }

    /**
     * Retrieves maxWaitTime
     * 
     */
    public Integer getMaxWaitTime() {
        return this.maxWaitTime;
    }

    /**
     * Sets connectTimeout
     * 
     * @param value Value to set
     */
    public void setConnectTimeout(Integer value) {
        this.connectTimeout = value;
    }

    /**
     * Retrieves connectTimeout
     * 
     */
    public Integer getConnectTimeout() {
        return this.connectTimeout;
    }

    /**
     * Sets socketTimeout
     * 
     * @param value Value to set
     */
    public void setSocketTimeout(Integer value) {
        this.socketTimeout = value;
    }

    /**
     * Retrieves socketTimeout
     * 
     */
    public Integer getSocketTimeout() {
        return this.socketTimeout;
    }

    /**
     * Sets autoConnectRetry
     * 
     * @param value Value to set
     */
    public void setAutoConnectRetry(Boolean value) {
        this.autoConnectRetry = value;
    }

    /**
     * Retrieves autoConnectRetry
     * 
     */
    public Boolean getAutoConnectRetry() {
        return this.autoConnectRetry;
    }

    /**
     * Sets slaveOk
     * 
     * @param value Value to set
     */
    public void setSlaveOk(Boolean value) {
        this.slaveOk = value;
    }

    /**
     * Retrieves slaveOk
     * 
     */
    public Boolean getSlaveOk() {
        return this.slaveOk;
    }

    /**
     * Sets safe
     * 
     * @param value Value to set
     */
    public void setSafe(Boolean value) {
        this.safe = value;
    }

    /**
     * Retrieves safe
     * 
     */
    public Boolean getSafe() {
        return this.safe;
    }

    /**
     * Sets w
     * 
     * @param value Value to set
     */
    public void setW(Integer value) {
        this.w = value;
    }

    /**
     * Retrieves w
     * 
     */
    public Integer getW() {
        return this.w;
    }

    /**
     * Sets wtimeout
     * 
     * @param value Value to set
     */
    public void setWtimeout(Integer value) {
        this.wtimeout = value;
    }

    /**
     * Retrieves wtimeout
     * 
     */
    public Integer getWtimeout() {
        return this.wtimeout;
    }

    /**
     * Sets fsync
     * 
     * @param value Value to set
     */
    public void setFsync(Boolean value) {
        this.fsync = value;
    }

    /**
     * Retrieves fsync
     * 
     */
    public Boolean getFsync() {
        return this.fsync;
    }

    /**
     * Sets connectionPoolingProfile
     * 
     * @param value Value to set
     */
    public void setConnectionPoolingProfile(PoolingProfile value) {
        this.connectionPoolingProfile = value;
    }

    /**
     * Retrieves connectionPoolingProfile
     * 
     */
    public PoolingProfile getConnectionPoolingProfile() {
        return this.connectionPoolingProfile;
    }

    /**
     * Sets username
     * 
     * @param value Value to set
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Retrieves username
     * 
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets password
     * 
     * @param value Value to set
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Retrieves password
     * 
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets flow construct
     * 
     * @param flowConstruct Flow construct to set
     */
    public void setFlowConstruct(FlowConstruct flowConstruct) {
        this.flowConstruct = flowConstruct;
    }

    /**
     * Set the Mule context
     * 
     * @param context Mule context to set
     */
    public void setMuleContext(MuleContext context) {
        this.muleContext = context;
    }

    public void initialise() {
        GenericKeyedObjectPool.Config config = new GenericKeyedObjectPool.Config();
        if (connectionPoolingProfile!= null) {
            config.maxIdle = connectionPoolingProfile.getMaxIdle();
            config.maxActive = connectionPoolingProfile.getMaxActive();
            config.maxWait = connectionPoolingProfile.getMaxWait();
            config.whenExhaustedAction = ((byte) connectionPoolingProfile.getExhaustedAction());
        }
        connectionPool = new GenericKeyedObjectPool(new MongoCloudConnectorConnectionManager.ConnectionFactory(this), config);
    }

    public MongoCloudConnectorLifecycleAdapter acquireConnection(MongoCloudConnectorConnectionManager.ConnectionKey key)
        throws Exception
    {
        return ((MongoCloudConnectorLifecycleAdapter) connectionPool.borrowObject(key));
    }

    public void releaseConnection(MongoCloudConnectorConnectionManager.ConnectionKey key, MongoCloudConnectorLifecycleAdapter connection)
        throws Exception
    {
        connectionPool.returnObject(key, connection);
    }

    public void destroyConnection(MongoCloudConnectorConnectionManager.ConnectionKey key, MongoCloudConnectorLifecycleAdapter connection)
        throws Exception
    {
        connectionPool.invalidateObject(key, connection);
    }

    /**
     * Returns true if this module implements such capability
     * 
     */
    public boolean isCapableOf(Capability capability) {
        if (capability == Capability.LIFECYCLE_CAPABLE) {
            return true;
        }
        if (capability == Capability.CONNECTION_MANAGEMENT_CAPABLE) {
            return true;
        }
        return false;
    }

    private static class ConnectionFactory
        implements KeyedPoolableObjectFactory
    {

        private MongoCloudConnectorConnectionManager connectionManager;

        public ConnectionFactory(MongoCloudConnectorConnectionManager connectionManager) {
            this.connectionManager = connectionManager;
        }

        public Object makeObject(Object key)
            throws Exception
        {
            if (!(key instanceof MongoCloudConnectorConnectionManager.ConnectionKey)) {
                throw new RuntimeException("Invalid key type");
            }
            MongoCloudConnectorLifecycleAdapter connector = new MongoCloudConnectorLifecycleAdapter();
            connector.setHost(connectionManager.getHost());
            connector.setPort(connectionManager.getPort());
            connector.setDatabase(connectionManager.getDatabase());
            connector.setConnectionsPerHost(connectionManager.getConnectionsPerHost());
            connector.setThreadsAllowedToBlockForConnectionMultiplier(connectionManager.getThreadsAllowedToBlockForConnectionMultiplier());
            connector.setMaxWaitTime(connectionManager.getMaxWaitTime());
            connector.setConnectTimeout(connectionManager.getConnectTimeout());
            connector.setSocketTimeout(connectionManager.getSocketTimeout());
            connector.setAutoConnectRetry(connectionManager.getAutoConnectRetry());
            connector.setSlaveOk(connectionManager.getSlaveOk());
            connector.setSafe(connectionManager.getSafe());
            connector.setW(connectionManager.getW());
            connector.setWtimeout(connectionManager.getWtimeout());
            connector.setFsync(connectionManager.getFsync());
            if (connector instanceof Initialisable) {
                connector.initialise();
            }
            if (connector instanceof Startable) {
                connector.start();
            }
            return connector;
        }

        public void destroyObject(Object key, Object obj)
            throws Exception
        {
            if (!(key instanceof MongoCloudConnectorConnectionManager.ConnectionKey)) {
                throw new RuntimeException("Invalid key type");
            }
            if (!(obj instanceof MongoCloudConnectorLifecycleAdapter)) {
                throw new RuntimeException("Invalid connector type");
            }
            try {
                ((MongoCloudConnectorLifecycleAdapter) obj).disconnect();
            } catch (Exception e) {
                throw e;
            } finally {
                if (((MongoCloudConnectorLifecycleAdapter) obj) instanceof Stoppable) {
                    ((MongoCloudConnectorLifecycleAdapter) obj).stop();
                }
                if (((MongoCloudConnectorLifecycleAdapter) obj) instanceof Disposable) {
                    ((MongoCloudConnectorLifecycleAdapter) obj).dispose();
                }
            }
        }

        public boolean validateObject(Object key, Object obj) {
            if (!(obj instanceof MongoCloudConnectorLifecycleAdapter)) {
                throw new RuntimeException("Invalid connector type");
            }
            try {
                return ((MongoCloudConnectorLifecycleAdapter) obj).isConnected();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return false;
            }
        }

        public void activateObject(Object key, Object obj)
            throws Exception
        {
            if (!(key instanceof MongoCloudConnectorConnectionManager.ConnectionKey)) {
                throw new RuntimeException("Invalid key type");
            }
            if (!(obj instanceof MongoCloudConnectorLifecycleAdapter)) {
                throw new RuntimeException("Invalid connector type");
            }
            try {
                if (!((MongoCloudConnectorLifecycleAdapter) obj).isConnected()) {
                    ((MongoCloudConnectorLifecycleAdapter) obj).connect(((MongoCloudConnectorConnectionManager.ConnectionKey) key).getUsername(), ((MongoCloudConnectorConnectionManager.ConnectionKey) key).getPassword());
                }
            } catch (Exception e) {
                throw e;
            }
        }

        public void passivateObject(Object key, Object obj)
            throws Exception
        {
        }

    }


    /**
     * A tuple of connection parameters
     * 
     */
    public static class ConnectionKey {

        /**
         * 
         */
        private String username;
        /**
         * 
         */
        private String password;

        public ConnectionKey(String username, String password) {
            this.username = username;
            this.password = password;
        }

        /**
         * Sets username
         * 
         * @param value Value to set
         */
        public void setUsername(String value) {
            this.username = value;
        }

        /**
         * Retrieves username
         * 
         */
        public String getUsername() {
            return this.username;
        }

        /**
         * Sets password
         * 
         * @param value Value to set
         */
        public void setPassword(String value) {
            this.password = value;
        }

        /**
         * Retrieves password
         * 
         */
        public String getPassword() {
            return this.password;
        }

        public int hashCode() {
            int hash = 1;
            hash = ((hash* 31)+ this.username.hashCode());
            return hash;
        }

        public boolean equals(Object obj) {
            return ((obj instanceof MongoCloudConnectorConnectionManager.ConnectionKey)&&(this.username == ((MongoCloudConnectorConnectionManager.ConnectionKey) obj).username));
        }

    }

}
