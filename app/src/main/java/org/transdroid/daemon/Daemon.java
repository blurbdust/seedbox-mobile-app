/*
 *	This file is part of Transdroid <http://www.transdroid.org>
 *
 *	Transdroid is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *
 *	Transdroid is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *
 *	You should have received a copy of the GNU General Public License
 *	along with Transdroid.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.transdroid.daemon;


import org.transdroid.daemon.Deluge.DelugeAdapter;
import org.transdroid.daemon.Rtorrent.RtorrentAdapter;

/**
 * Factory for new instances of server daemons, based on user settings.
 *
 * @author erickok
 *
 */
public enum Daemon {


	Deluge {
		public IDaemonAdapter createAdapter(DaemonSettings settings) {
			return new DelugeAdapter(settings);
		}
	},
	rTorrent {
		public IDaemonAdapter createAdapter(DaemonSettings settings) {
			return new RtorrentAdapter(settings);
		}
	};

	public abstract IDaemonAdapter createAdapter(DaemonSettings settings);

	/**
	 * Returns the code as used in preferences matching the given daemon type
	 * @return A string of the form 'daemon_<type>' that represents the daemon's enum value
	 */
	public static String toCode(Daemon type) {
		if (type == null)
			return null;
		switch (type) {

		case Deluge:
			return "daemon_deluge";

		case rTorrent:
			return "daemon_rtorrent";

		default:
			return "none";
		}
	}

	/**
	 * Returns the daemon enum type based on the code used in the user preferences.
	 * @param daemonCode The 'daemon_&lt;name&gt;' type code
	 * @return The corresponding daemon enum value, or null if it was an empty or invalid code
	 */
	public static Daemon fromCode(String daemonCode) {
		if (daemonCode == null) {
			return null;
		}

		if (daemonCode.equals("daemon_deluge")) {
			return Deluge;
		}

		if (daemonCode.equals("daemon_rtorrent")) {
			return rTorrent;
		}

		return null;
	}

	public static int getDefaultPortNumber(Daemon type, boolean ssl) {
		if (type == null) {
			return 8080; // Only happens when the daemon type isn't even set yet
		}
        switch (type) {

        case rTorrent:
			return 8080;
        case Deluge:
        	return 8112;

        }
		return 8080;
	}

	/* public static boolean supportsStats(Daemon type) {
		return type == null;
		//return type == Transmission || type == Bitflu || type == Dummy;
	}

	public static boolean supportsAvailability(Daemon type) {
		return type == null;
		//return type == uTorrent || type == BitTorrent || type == DLinkRouterBT || type == Transmission || type == Vuze || type == BuffaloNas || type == Dummy;
	}
 */
	public static boolean supportsFileListing(Daemon type) {
		return type == Deluge || type == rTorrent;
	}

	public static boolean supportsFineDetails(Daemon type) {
		return type == Deluge || type == rTorrent;
	}
/*
	public static boolean needsManualPathSpecified(Daemon type) {
		return type == null;
		//return type == uTorrent || type == BitTorrent || type == KTorrent || type == BuffaloNas;
	}
*/
	public static boolean supportsFilePaths(Daemon type) {
		return type == Deluge || type == rTorrent;
	}

	public static boolean supportsStoppingStarting(Daemon type) {
		return type == rTorrent;
	}
/*
	public static boolean supportsForcedStarting(Daemon type) {
		return type == null;
		//return type == uTorrent || type == BitTorrent || type == Dummy;
	}
*/
	public static boolean supportsCustomFolder(Daemon type) {
		return type == rTorrent || type == Deluge;
	}

	public static boolean supportsSetTransferRates(Daemon type) {
		return type == Deluge || type == rTorrent;
	}

	public static boolean supportsAddByFile(Daemon type) {
		// Supported by every client except Bitflu
		return type == Deluge || type == rTorrent;
	}

	public static boolean supportsAddByMagnetUrl(Daemon type) {
		return type == Deluge || type == rTorrent;
	}

	public static boolean supportsRemoveWithData(Daemon type) {
		return type == Deluge || type == rTorrent;
	}

	public static boolean supportsFilePrioritySetting(Daemon type) {
		return type == Deluge || type == rTorrent;
	}

	public static boolean supportsDateAdded(Daemon type) {
		return type == Deluge || type == rTorrent;
	}

	public static boolean supportsLabels(Daemon type) {
		return type == Deluge || type == rTorrent;
	}

	public static boolean supportsSetLabel(Daemon type) {
		return type == Deluge || type == rTorrent;
	}

	public static boolean supportsSetDownloadLocation(Daemon type) {
		return type == Deluge;
	}
/*
	public static boolean supportsSetAlternativeMode(Daemon type) {
		return type == null;
	}
*/
	public static boolean supportsSetTrackers(Daemon type) {
		return type == Deluge;
	}

	public static boolean supportsForceRecheck(Daemon type) {
		return type == Deluge || type == rTorrent;
	}

	public static boolean supportsExtraPassword(Daemon type) {
		return type == Deluge;
	}

	public static boolean supportsUsernameForHttp(Daemon type) {
		return type == Deluge;
	}

}
