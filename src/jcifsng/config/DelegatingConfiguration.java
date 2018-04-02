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
package jcifsng.config;


import java.net.InetAddress;
import java.security.SecureRandom;
import java.util.List;
import java.util.TimeZone;

import jcifsng.Configuration;
import jcifsng.DialectVersion;
import jcifsng.ResolverType;


/**
 * @author mbechler
 *
 */
public class DelegatingConfiguration implements Configuration {

    private final Configuration delegate;


    /**
     * @param delegate
     *            delegate to pass all non-overridden method calls to
     * 
     */
    public DelegatingConfiguration ( Configuration delegate ) {
        this.delegate = delegate;
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getRandom()
     */
    @Override
    public SecureRandom getRandom () {
        return this.delegate.getRandom();
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Configuration#getMinimumVersion()
     */
    @Override
    public DialectVersion getMinimumVersion () {
        return this.delegate.getMinimumVersion();
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Configuration#getMaximumVersion()
     */
    @Override
    public DialectVersion getMaximumVersion () {
        return this.delegate.getMaximumVersion();
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Configuration#isUseSMB2OnlyNegotiation()
     */
    @Override
    public boolean isUseSMB2OnlyNegotiation () {
        return this.delegate.isUseSMB2OnlyNegotiation();
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Configuration#isRequireSecureNegotiate()
     */
    @Override
    public boolean isRequireSecureNegotiate () {
        return this.delegate.isRequireSecureNegotiate();
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Configuration#isPort139FailoverEnabled()
     */
    @Override
    public boolean isPort139FailoverEnabled () {
        return this.delegate.isPort139FailoverEnabled();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getDfsTtl()
     */
    @Override
    public long getDfsTtl () {
        return this.delegate.getDfsTtl();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#isDfsStrictView()
     */
    @Override
    public boolean isDfsStrictView () {
        return this.delegate.isDfsStrictView();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#isDfsDisabled()
     */
    @Override
    public boolean isDfsDisabled () {
        return this.delegate.isDfsDisabled();
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Configuration#isDfsConvertToFQDN()
     */
    @Override
    public boolean isDfsConvertToFQDN () {
        return this.delegate.isDfsConvertToFQDN();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#isForceUnicode()
     */
    @Override
    public boolean isForceUnicode () {
        return this.delegate.isForceUnicode();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#isUseUnicode()
     */
    @Override
    public boolean isUseUnicode () {
        return this.delegate.isUseUnicode();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#isUseBatching()
     */
    @Override
    public boolean isUseBatching () {
        return this.delegate.isUseBatching();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getNativeOs()
     */
    @Override
    public String getNativeOs () {
        return this.delegate.getNativeOs();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getNativeLanman()
     */
    @Override
    public String getNativeLanman () {
        return this.delegate.getNativeLanman();
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Configuration#getMaximumBufferSize()
     */
    @Override
    public int getMaximumBufferSize () {
        return this.delegate.getMaximumBufferSize();
    }


    /**
     * {@inheritDoc}
     * 
     * @deprecated use getReceiveBufferSize instead
     */
    @Deprecated
    @Override
    public int getRecieveBufferSize () {
        return this.delegate.getReceiveBufferSize();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getReceiveBufferSize()
     */
    @Override
    public int getReceiveBufferSize () {
        return this.delegate.getReceiveBufferSize();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getSendBufferSize()
     */
    @Override
    public int getSendBufferSize () {
        return this.delegate.getSendBufferSize();
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Configuration#getNotifyBufferSize()
     */
    @Override
    public int getNotifyBufferSize () {
        return this.delegate.getNotifyBufferSize();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getSoTimeout()
     */
    @Override
    public int getSoTimeout () {
        return this.delegate.getSoTimeout();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getResponseTimeout()
     */
    @Override
    public int getResponseTimeout () {
        return this.delegate.getResponseTimeout();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getConnTimeout()
     */
    @Override
    public int getConnTimeout () {
        return this.delegate.getConnTimeout();
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Configuration#getSessionTimeout()
     */
    @Override
    public int getSessionTimeout () {
        return this.delegate.getSessionTimeout();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getLocalPort()
     */
    @Override
    public int getLocalPort () {
        return this.delegate.getLocalPort();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getLocalAddr()
     */
    @Override
    public InetAddress getLocalAddr () {
        return this.delegate.getLocalAddr();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getNetbiosHostname()
     */
    @Override
    public String getNetbiosHostname () {
        return this.delegate.getNetbiosHostname();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getLogonShare()
     */
    @Override
    public String getLogonShare () {
        return this.delegate.getLogonShare();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getDefaultDomain()
     */
    @Override
    public String getDefaultDomain () {
        return this.delegate.getDefaultDomain();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getDefaultUsername()
     */
    @Override
    public String getDefaultUsername () {
        return this.delegate.getDefaultUsername();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getDefaultPassword()
     */
    @Override
    public String getDefaultPassword () {
        return this.delegate.getDefaultPassword();
    }


    /**
     * 
     * @see jcifsng.Configuration#isDisablePlainTextPasswords()
     */
    @Override
    public boolean isDisablePlainTextPasswords () {
        return this.delegate.isDisablePlainTextPasswords();
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Configuration#isForceExtendedSecurity()
     */
    @Override
    public boolean isForceExtendedSecurity () {
        return this.delegate.isForceExtendedSecurity();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getLanManCompatibility()
     */
    @Override
    public int getLanManCompatibility () {
        return this.delegate.getLanManCompatibility();
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Configuration#isAllowNTLMFallback()
     */
    @Override
    public boolean isAllowNTLMFallback () {
        return this.delegate.isAllowNTLMFallback();
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Configuration#isUseRawNTLM()
     */
    @Override
    public boolean isUseRawNTLM () {
        return this.delegate.isUseRawNTLM();
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Configuration#isDisableSpnegoIntegrity()
     */
    @Override
    public boolean isDisableSpnegoIntegrity () {
        return this.delegate.isDisableSpnegoIntegrity();
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Configuration#isEnforceSpnegoIntegrity()
     */
    @Override
    public boolean isEnforceSpnegoIntegrity () {
        return this.delegate.isEnforceSpnegoIntegrity();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getResolveOrder()
     */
    @Override
    public List<ResolverType> getResolveOrder () {
        return this.delegate.getResolveOrder();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getBroadcastAddress()
     */
    @Override
    public InetAddress getBroadcastAddress () {
        return this.delegate.getBroadcastAddress();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getWinsServers()
     */
    @Override
    public InetAddress[] getWinsServers () {
        return this.delegate.getWinsServers();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getNetbiosLocalPort()
     */
    @Override
    public int getNetbiosLocalPort () {
        return this.delegate.getNetbiosLocalPort();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getNetbiosLocalAddress()
     */
    @Override
    public InetAddress getNetbiosLocalAddress () {
        return this.delegate.getNetbiosLocalAddress();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getVcNumber()
     */
    @Override
    public int getVcNumber () {
        return this.delegate.getVcNumber();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getCapabilities()
     */
    @Override
    public int getCapabilities () {
        return this.delegate.getCapabilities();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getFlags2()
     */
    @Override
    public int getFlags2 () {
        return this.delegate.getFlags2();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getSessionLimit()
     */
    @Override
    public int getSessionLimit () {
        return this.delegate.getSessionLimit();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getOemEncoding()
     */
    @Override
    public String getOemEncoding () {
        return this.delegate.getOemEncoding();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getLocalTimezone()
     */
    @Override
    public TimeZone getLocalTimezone () {
        return this.delegate.getLocalTimezone();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getPid()
     */
    @Override
    public int getPid () {
        return this.delegate.getPid();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getMaxMpxCount()
     */
    @Override
    public int getMaxMpxCount () {
        return this.delegate.getMaxMpxCount();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#isSigningEnabled()
     */
    @Override
    public boolean isSigningEnabled () {
        return this.delegate.isSigningEnabled();
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Configuration#isSigningEnforced()
     */
    @Override
    public boolean isSigningEnforced () {
        return this.delegate.isSigningEnforced();
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Configuration#isIpcSigningEnforced()
     */
    @Override
    public boolean isIpcSigningEnforced () {
        return this.delegate.isIpcSigningEnforced();
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Configuration#isEncryptionEnabled()
     */
    @Override
    public boolean isEncryptionEnabled () {
        return this.delegate.isEncryptionEnabled();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getLmHostsFileName()
     */
    @Override
    public String getLmHostsFileName () {
        return this.delegate.getLmHostsFileName();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getNetbiosScope()
     */
    @Override
    public String getNetbiosScope () {
        return this.delegate.getNetbiosScope();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getNetbiosSoTimeout()
     */
    @Override
    public int getNetbiosSoTimeout () {
        return this.delegate.getNetbiosSoTimeout();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getNetbiosSndBufSize()
     */
    @Override
    public int getNetbiosSndBufSize () {
        return this.delegate.getNetbiosSndBufSize();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getNetbiosRetryTimeout()
     */
    @Override
    public int getNetbiosRetryTimeout () {
        return this.delegate.getNetbiosRetryTimeout();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getNetbiosRetryCount()
     */
    @Override
    public int getNetbiosRetryCount () {
        return this.delegate.getNetbiosRetryCount();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getNetbiosRcvBufSize()
     */
    @Override
    public int getNetbiosRcvBufSize () {
        return this.delegate.getNetbiosRcvBufSize();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getNetbiosCachePolicy()
     */
    @Override
    public int getNetbiosCachePolicy () {
        return this.delegate.getNetbiosCachePolicy();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getTransactionBufferSize()
     */
    @Override
    public int getTransactionBufferSize () {
        return this.delegate.getTransactionBufferSize();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getBufferCacheSize()
     */
    @Override
    public int getBufferCacheSize () {
        return this.delegate.getBufferCacheSize();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getListCount()
     */
    @Override
    public int getListCount () {
        return this.delegate.getListCount();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getListSize()
     */
    @Override
    public int getListSize () {
        return this.delegate.getListSize();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getAttributeCacheTimeout()
     */
    @Override
    public long getAttributeCacheTimeout () {
        return this.delegate.getAttributeCacheTimeout();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#isIgnoreCopyToException()
     */
    @Override
    public boolean isIgnoreCopyToException () {
        return this.delegate.isIgnoreCopyToException();
    }


    /**
     * {@inheritDoc}
     * 
     * @see jcifsng.Configuration#getBatchLimit(java.lang.String)
     */
    @Override
    public int getBatchLimit ( String cmd ) {
        return this.delegate.getBatchLimit(cmd);
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Configuration#isAllowCompound(java.lang.String)
     */
    @Override
    public boolean isAllowCompound ( String command ) {
        return this.delegate.isAllowCompound(command);
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Configuration#isTraceResourceUsage()
     */
    @Override
    public boolean isTraceResourceUsage () {
        return this.delegate.isTraceResourceUsage();
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Configuration#isStrictResourceLifecycle()
     */
    @Override
    public boolean isStrictResourceLifecycle () {
        return this.delegate.isStrictResourceLifecycle();
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Configuration#getMaxRequestRetries()
     */
    @Override
    public int getMaxRequestRetries () {
        return this.delegate.getMaxRequestRetries();
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Configuration#getMachineId()
     */
    @Override
    public byte[] getMachineId () {
        return this.delegate.getMachineId();
    }
}
