/*
 * Copyright (C) 2013 Glyptodon LLC
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.glyptodon.guacamole.net.auth;

import java.util.Collection;
import org.glyptodon.guacamole.GuacamoleException;

/**
 * The context of an active user. The functions of this class enforce all
 * permissions and act only within the rights of the associated user.
 *
 * @author Michael Jumper
 */
public interface UserContext {

    /**
     * Returns the User whose access rights control the operations of this
     * UserContext.
     *
     * @return The User whose access rights control the operations of this
     *         UserContext.
     */
    User self();

    /**
     * Retrieves a Directory which can be used to view and manipulate other
     * users, but only as allowed by the permissions given to the user of this
     * UserContext.
     *
     * @return A Directory whose operations are bound by the restrictions
     *         of this UserContext.
     *
     * @throws GuacamoleException If an error occurs while creating the
     *                            Directory.
     */
    Directory<User> getUserDirectory() throws GuacamoleException;

    /**
     * Retrieves a Directory which can be used to view and manipulate
     * connections and their configurations, but only as allowed by the
     * permissions given to the user.
     *
     * @return A Directory whose operations are bound by the permissions of 
     *         the user.
     *
     * @throws GuacamoleException If an error occurs while creating the
     *                            Directory.
     */
    Directory<Connection> getConnectionDirectory()
            throws GuacamoleException;

    /**
     * Retrieves a Directory which can be used to view and manipulate
     * connection groups and their members, but only as allowed by the
     * permissions given to the user.
     *
     * @return A Directory whose operations are bound by the permissions of
     *         the user.
     *
     * @throws GuacamoleException If an error occurs while creating the
     *                            Directory.
     */
    Directory<ConnectionGroup> getConnectionGroupDirectory()
            throws GuacamoleException;

    /**
     * Retrieves a connection group which can be used to view and manipulate
     * connections, but only as allowed by the permissions given to the user of 
     * this UserContext.
     *
     * @return A connection group whose operations are bound by the restrictions
     *         of this UserContext.
     *
     * @throws GuacamoleException If an error occurs while creating the
     *                            Directory.
     */
    ConnectionGroup getRootConnectionGroup() throws GuacamoleException;

    /**
     * Returns a collection of connection records associated with all active
     * connections to which the current user has access. For an administrative
     * user, this may include connections associated with other users.
     *
     * @return
     *     A collection of all connection records associated with active
     *     connections to which the current user has access.
     *
     * @throws GuacamoleException
     *     If an error occurs while reading active connection records, or if
     *     permission is denied.
     */
    Collection<ConnectionRecord> getActiveConnections()
            throws GuacamoleException;

    /**
     * Returns the connection record associated with the active connection
     * having the tunnel with the given UUID. The active connection will only
     * be returned if the current user has access.
     *
     * @param tunnelUUID
     *     The UUID of the tunnel whose associated connection record should be
     *     returned.
     *
     * @return
     *     The connection record associated with the active connection having
     *     the tunnel with the given UUID, if any, or null if no such
     *     connection exists.
     *
     * @throws GuacamoleException
     *     If an error occurs while reading active connection records, or if
     *     permission is denied.
     */
    ConnectionRecord getActiveConnection(String tunnelUUID)
            throws GuacamoleException;

}
