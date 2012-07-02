package com.bizhub.bzwebapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bizhub.bzwebapp.domain.Site;


public class MySqlSiteDao extends AbstractDatabaseDao implements
		SiteDao {

	public void save(Site site) throws DataAccessException {
		synchronized (site) {
			try {
				Connection connection = super.getConnection();
				try {
					if (site.isIdSet()) {
						this.update(site, connection);
					} else {
						this.create(site, connection);
					}
				} finally {
					connection.close();
				}
			} catch (SQLException e) {
				String msg = e.getMessage();
				if (msg != null && msg.contains("Duplicate")) {
					throw new DuplicateIdException(site.getName(), e);
				}
				throw new DataAccessException("Failed to create: " + site,
						e);
			}
		}
	}

	private void create(Site site, Connection connection)
			throws SQLException {
		PreparedStatement stmt = connection
				.prepareStatement("INSERT INTO site (name, description) VALUES (?, ?)");
		try {
			stmt.setString(1, site.getName());
			stmt.setString(2, site.getDescription());
			stmt.executeUpdate();
		} finally {
			stmt.close();
		}
		site.setId(super.getLastInsertId(connection));
		if (super.logger.isDebugEnabled()) {
			super.logger.debug("Created: " + site + " with id "
					+ site.getId());
		}
	}

	private void update(Site site, Connection connection)
			throws SQLException {
		PreparedStatement stmt = connection
				.prepareStatement("UPDATE site SET name=?, description=? WHERE id=?");
		try {
			stmt.setString(1, site.getName());
			stmt.setString(2, site.getDescription());
			stmt.setLong(3, site.getId());
			stmt.executeUpdate();
			if (super.logger.isDebugEnabled()) {
				super.logger.debug("Updated: " + site);
			}
		} finally {
			stmt.close();
		}
	}

	public void delete(Site site) throws DataAccessException {
		this.deleteById(site.getId());
	}

	public void deleteById(Long id) throws DataAccessException {
		try {
			Connection connection = super.getConnection();
			try {
				PreparedStatement stmt = connection
						.prepareStatement("DELETE FROM site WHERE id=?");
				try {
					stmt.setLong(1, id);
					stmt.executeUpdate();
					if (super.logger.isDebugEnabled()) {
						super.logger.debug("Deleted site with id " + id);
					}
				} finally {
					stmt.close();
				}
			} finally {
				connection.close();
			}
		} catch (SQLException e) {
			throw new DataAccessException("Failed to delete site by id: "
					+ id, e);
		}
	}

	public List<Site> getAll() throws DataAccessException {
		try {
			Connection connection = super.getConnection();
			try {
				Statement stmt = connection.createStatement();
				try {
					ResultSet rs = stmt
							.executeQuery("SELECT id, name, description FROM site ORDER BY name");
					try {
						List<Site> siteList = new ArrayList<Site>();
						while (rs.next()) {
							siteList.add(this.loadFromRow(rs));
						}
						if (super.logger.isDebugEnabled()) {
							super.logger.debug("Got all sites");
						}
						return siteList;
					} finally {
						rs.close();
					}
				} finally {
					stmt.close();
				}
			} finally {
				connection.close();
			}
		} catch (SQLException e) {
			throw new DataAccessException("Failed to get all categories", e);
		}
	}

	public Site getById(Long id) throws DataAccessException {
		try {
			Connection connection = super.getConnection();
			try {
				PreparedStatement stmt = connection
						.prepareStatement("SELECT id, name, description FROM site WHERE id=?");
				try {
					stmt.setLong(1, id);
					ResultSet rs = stmt.executeQuery();
					try {
						return rs.next() ? this.loadFromRow(rs) : null;
					} finally {
						rs.close();
					}
				} finally {
					stmt.close();
				}
			} finally {
				connection.close();
			}
		} catch (SQLException e) {
			throw new DataAccessException(
					"Failed to get site by id: " + id, e);
		}
	}

	public Site getByName(String name) throws DataAccessException {
		try {
			Connection connection = super.getConnection();
			try {
				PreparedStatement stmt = connection
						.prepareStatement("SELECT id, name, description FROM site WHERE name LIKE ?");
				try {
					stmt.setString(1, name);
					ResultSet rs = stmt.executeQuery();
					try {
						return rs.next() ? this.loadFromRow(rs) : null;
					} finally {
						rs.close();
					}
				} finally {
					stmt.close();
				}
			} finally {
				connection.close();
			}
		} catch (SQLException e) {
			throw new DataAccessException("Failed to get site by name: "
					+ name, e);
		}
	}

	private Site loadFromRow(ResultSet row) throws SQLException {
		Site site = new Site();
		site.setId(row.getLong(1));
		site.setName(row.getString(2));
		site.setDescription(row.getString(3));
		if (super.logger.isTraceEnabled()) {
			super.logger.trace("Got " + site);
		}
		return site;
	}
}
