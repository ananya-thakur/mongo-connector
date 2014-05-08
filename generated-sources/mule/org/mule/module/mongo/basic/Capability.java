
package org.mule.module.mongo.basic;

import javax.annotation.Generated;


/**
 *  Enumeration of possible capabilities of Mule modules. Each capability represents a bit in a bit array. The capabilities of a particular module can be queried using {@link Capabilities}
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.0", date = "2013-08-22T09:27:07-05:00", comments = "Build UNKNOWN_BUILDNUMBER")
public enum Capability {

     /**
     * This capability indicates that the module implements {@link org.mule.api.lifecycle.Lifecycle}
     */
    LIFECYCLE_CAPABLE(0),

    /**
     * This capability indicates that the module implements {@link ConnectionManager}
     */
    CONNECTION_MANAGEMENT_CAPABLE(1),

    /**
     * This capability indicates that the module implements {@link org.mule.api.oauth.OAuth1Adapter}
     */
    OAUTH1_CAPABLE(2),

    /**
     * This capability indicates that the module implements {@link org.mule.api.oauth.OAuth2Adapter}
     */
    OAUTH2_CAPABLE(3),

    /**
     * This capability indicates that the module implements {@link org.mule.api.adapter.PoolManager}
     */
    POOLING_CAPABLE(4),

    /**
     * This capability indicates that the module implements {@link org.mule.api.oauth.OAuthManager}
     */
    OAUTH_ACCESS_TOKEN_MANAGEMENT_CAPABLE(5);

    private int bit;

    Capability(int bit) {
        this.bit = bit;
    }

    public int getBit() {
        return this.bit;
    }
}