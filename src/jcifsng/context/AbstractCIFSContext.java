/*
 * © 2016 AgNO3 Gmbh & Co. KG
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package jcifsng.context;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jcifsng.CIFSContext;
import jcifsng.CIFSException;
import jcifsng.Credentials;
import jcifsng.smb.NtlmPasswordAuthenticator;


/**
 * @author mbechler
 *
 */
public abstract class AbstractCIFSContext extends Thread implements CIFSContext {

    private static final Logger log = LoggerFactory.getLogger(AbstractCIFSContext.class);
    private boolean closed;


    /**
     * 
     */
    public AbstractCIFSContext () {
        Runtime.getRuntime().addShutdownHook(this);
    }


    /**
     * @param creds
     * @return a wrapped context with the given credentials
     */
    @Override
    public CIFSContext withCredentials ( Credentials creds ) {
        return new CIFSContextCredentialWrapper(this, creds);
    }


    /**
     * 
     * {@inheritDoc}
     *
     * @see jcifsng.CIFSContext#withAnonymousCredentials()
     */
    @Override
    public CIFSContext withAnonymousCredentials () {
        return withCredentials(new NtlmPasswordAuthenticator());
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.CIFSContext#withDefaultCredentials()
     */
    @Override
    public CIFSContext withDefaultCredentials () {
        return withCredentials(getDefaultCredentials());
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.CIFSContext#withGuestCrendentials()
     */
    @Override
    public CIFSContext withGuestCrendentials () {
        return withCredentials(new NtlmPasswordAuthenticator(null, "GUEST", ""));
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.CIFSContext#getCredentials()
     */
    @Override
    public Credentials getCredentials () {
        return getDefaultCredentials();
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.CIFSContext#hasDefaultCredentials()
     */
    @Override
    public boolean hasDefaultCredentials () {
        return this.getDefaultCredentials() != null && !this.getDefaultCredentials().isAnonymous();
    }


    /**
     * @return
     */
    protected abstract Credentials getDefaultCredentials ();


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.CIFSContext#renewCredentials(java.lang.String, java.lang.Throwable)
     */
    @Override
    public boolean renewCredentials ( String locationHint, Throwable error ) {
        return false;
    }


    /**
     * {@inheritDoc}
     * 
     *
     * @see jcifsng.CIFSContext#close()
     */
    @Override
    public boolean close () throws CIFSException {
        if ( !this.closed ) {
            Runtime.getRuntime().removeShutdownHook(this);
        }
        return false;
    }


    /**
     * {@inheritDoc}
     *
     * @see java.lang.Thread#run()
     */
    @Override
    public void run () {
        try {
            this.closed = true;
            close();
        }
        catch ( CIFSException e ) {
            log.warn("Failed to close context on shutdown", e);
        }
    }
}
