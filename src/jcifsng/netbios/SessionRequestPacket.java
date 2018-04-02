/* jcifs smb client library in Java
 * Copyright (C) 2000  "Michael B. Allen" <jcifs at samba dot org>
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

package jcifsng.netbios;


import java.io.IOException;
import java.io.InputStream;

import jcifsng.Configuration;
import jcifsng.NetbiosName;


/**
 *
 */
public class SessionRequestPacket extends SessionServicePacket {

    private Name calledName, callingName;


    SessionRequestPacket ( Configuration config ) {
        this.calledName = new Name(config);
        this.callingName = new Name(config);
    }


    /**
     * 
     * @param config
     * @param calledName
     * @param callingName
     */
    public SessionRequestPacket ( Configuration config, NetbiosName calledName, NetbiosName callingName ) {
        this.type = SESSION_REQUEST;
        this.calledName = new Name(config, calledName);
        this.callingName = new Name(config, callingName);
    }


    @Override
    int writeTrailerWireFormat ( byte[] dst, int dstIndex ) {
        int start = dstIndex;
        dstIndex += this.calledName.writeWireFormat(dst, dstIndex);
        dstIndex += this.callingName.writeWireFormat(dst, dstIndex);
        return dstIndex - start;
    }


    @Override
    int readTrailerWireFormat ( InputStream in, byte[] buffer, int bufferIndex ) throws IOException {
        int start = bufferIndex;
        if ( in.read(buffer, bufferIndex, this.length) != this.length ) {
            throw new IOException("invalid session request wire format");
        }
        bufferIndex += this.calledName.readWireFormat(buffer, bufferIndex);
        bufferIndex += this.callingName.readWireFormat(buffer, bufferIndex);
        return bufferIndex - start;
    }
}
