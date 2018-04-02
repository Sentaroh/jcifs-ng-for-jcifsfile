/*
 * © 2017 AgNO3 Gmbh & Co. KG
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
package jcifsng.internal.smb2.ioctl;


import jcifsng.Encodable;
import jcifsng.internal.util.SMBUtil;


/**
 * @author mbechler
 *
 */
public class SrvCopychunk implements Encodable {

    private long sourceOffset;
    private long targetOffset;
    private int length;


    /**
     * @param soff
     * @param toff
     * @param len
     */
    public SrvCopychunk ( long soff, long toff, int len ) {
        this.sourceOffset = soff;
        this.targetOffset = toff;
        this.length = len;
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Encodable#encode(byte[], int)
     */
    @Override
    public int encode ( byte[] dst, int dstIndex ) {
        int start = dstIndex;
        SMBUtil.writeInt8(this.sourceOffset, dst, dstIndex);
        dstIndex += 8;
        SMBUtil.writeInt8(this.targetOffset, dst, dstIndex);
        dstIndex += 8;
        SMBUtil.writeInt4(this.length, dst, dstIndex);
        dstIndex += 4;
        dstIndex += 4; // reserved
        return dstIndex - start;
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng.Encodable#size()
     */
    @Override
    public int size () {
        return 24;
    }

}
