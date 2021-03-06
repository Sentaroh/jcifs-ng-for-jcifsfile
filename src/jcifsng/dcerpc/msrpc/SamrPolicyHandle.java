/* jcifs msrpc client library in Java
 * Copyright (C) 2007  "Michael B. Allen" <jcifs at samba dot org>
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

package jcifsng.dcerpc.msrpc;


import java.io.IOException;

import jcifsng.dcerpc.DcerpcError;
import jcifsng.dcerpc.DcerpcException;
import jcifsng.dcerpc.DcerpcHandle;
import jcifsng.dcerpc.rpc;
import jcifsng.smb.SmbException;


@SuppressWarnings ( "javadoc" )
public class SamrPolicyHandle extends rpc.policy_handle implements AutoCloseable {

    private final DcerpcHandle handle;
    private boolean opened;


    public SamrPolicyHandle ( DcerpcHandle handle, String server, int access ) throws IOException {
        this.handle = handle;
        if ( server == null ) {
            server = "\\\\";
        }
        MsrpcSamrConnect4 rpc = new MsrpcSamrConnect4(server, access, this);
        try {
            handle.sendrecv(rpc);
        }
        catch ( DcerpcException de ) {
            if ( de.getErrorCode() != DcerpcError.DCERPC_FAULT_OP_RNG_ERROR ) {
                throw de;
            }
            MsrpcSamrConnect2 rpc2 = new MsrpcSamrConnect2(server, access, this);
            handle.sendrecv(rpc2);
        }
        this.opened = true;
    }


    @Override
    public synchronized void close () throws IOException {
        if ( this.opened ) {
            this.opened = false;
            samr.SamrCloseHandle rpc = new MsrpcSamrCloseHandle(this);
            this.handle.sendrecv(rpc);
            if ( rpc.retval != 0 ) {
                throw new SmbException(rpc.retval, false);
            }
        }
    }
}
