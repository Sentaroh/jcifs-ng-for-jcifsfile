/*
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
package jcifsng.pac.kerberos;


import java.util.Map;

import javax.security.auth.kerberos.KerberosKey;

import jcifsng.pac.PACDecodingException;
import jcifsng.pac.Pac;


@SuppressWarnings ( "javadoc" )
public class KerberosPacAuthData extends KerberosAuthData {

    private Pac pac;


    public KerberosPacAuthData ( byte[] token, Map<Integer, KerberosKey> keys ) throws PACDecodingException {
        this.pac = new Pac(token, keys);
    }


    public Pac getPac () {
        return this.pac;
    }

}