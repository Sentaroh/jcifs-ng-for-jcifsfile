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


import java.net.MalformedURLException;
import java.net.URLStreamHandler;

import jcifsng.BufferCache;
import jcifsng.CIFSException;
import jcifsng.Configuration;
import jcifsng.Credentials;
import jcifsng.DfsResolver;
import jcifsng.NameServiceClient;
import jcifsng.SidResolver;
import jcifsng.SmbPipeResource;
import jcifsng.SmbResource;
import jcifsng.SmbTransportPool;
import jcifsng.netbios.NameServiceClientImpl;
import jcifsng.smb.BufferCacheImpl;
import jcifsng.smb.CredentialsInternal;
import jcifsng.smb.DfsImpl;
import jcifsng.smb.Handler;
import jcifsng.smb.NtlmPasswordAuthenticator;
import jcifsng.smb.SIDCacheImpl;
import jcifsng.smb.SmbFile;
import jcifsng.smb.SmbNamedPipe;
import jcifsng.smb.SmbTransportPoolImpl;


/**
 * @author mbechler
 *
 */
public class BaseContext extends AbstractCIFSContext {

    private final Configuration config;
    private final DfsResolver dfs;
    private final SidResolver sidResolver;
    private final Handler urlHandler;
    private final NameServiceClient nameServiceClient;
    private final BufferCache bufferCache;
    private final SmbTransportPool transportPool;
    private final CredentialsInternal defaultCredentials;


    /**
     * Construct a context
     * 
     * @param config
     *            configuration for the context
     * 
     */
    public BaseContext ( Configuration config ) {
        this.config = config;
        this.dfs = new DfsImpl(this);
        this.sidResolver = new SIDCacheImpl(this);
        this.urlHandler = new Handler(this);
        this.nameServiceClient = new NameServiceClientImpl(this);
        this.bufferCache = new BufferCacheImpl(this.config);
        this.transportPool = new SmbTransportPoolImpl();
        this.defaultCredentials = new NtlmPasswordAuthenticator();
    }


    /**
     * {@inheritDoc}
     * 
     * @throws CIFSException
     * 
     * @see jcifsng.CIFSContext#get(java.lang.String)
     */
    @Override
    public SmbResource get ( String url ) throws CIFSException {
        try {
            return new SmbFile(url, this);
        }
        catch ( MalformedURLException e ) {
            throw new CIFSException("Invalid URL " + url, e);
        }
    }


    /**
     * 
     * {@inheritDoc}
     *
     * @see jcifsng.CIFSContext#getPipe(java.lang.String, int)
     */
    @Override
    public SmbPipeResource getPipe ( String url, int pipeType ) throws CIFSException {
        try {
            return new SmbNamedPipe(url, pipeType, this);
        }
        catch ( MalformedURLException e ) {
            throw new CIFSException("Invalid URL " + url, e);
        }
    }


    @Override
    public SmbTransportPool getTransportPool () {
        return this.transportPool;
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.CIFSContext#getConfig()
     */
    @Override
    public Configuration getConfig () {
        return this.config;
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.CIFSContext#getDfs()
     */
    @Override
    public DfsResolver getDfs () {
        return this.dfs;
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.CIFSContext#getNameServiceClient()
     */
    @Override
    public NameServiceClient getNameServiceClient () {
        return this.nameServiceClient;
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.CIFSContext#getBufferCache()
     */
    @Override
    public BufferCache getBufferCache () {
        return this.bufferCache;
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.CIFSContext#getUrlHandler()
     */
    @Override
    public URLStreamHandler getUrlHandler () {
        return this.urlHandler;
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.CIFSContext#getSIDResolver()
     */
    @Override
    public SidResolver getSIDResolver () {
        return this.sidResolver;
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.context.AbstractCIFSContext#getDefaultCredentials()
     */
    @Override
    protected Credentials getDefaultCredentials () {
        return this.defaultCredentials;
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.CIFSContext#close()
     */
    @Override
    public boolean close () throws CIFSException {
        boolean inUse = super.close();
        inUse |= this.transportPool.close();
        return inUse;
    }

}
