package com.bizhub.bzwebapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "site")
public class Site extends IdentifiableEntity{

	private static final long serialVersionUID = -1947421036228366609L;

	private String name;

	private String description;

	private long userId;

	@Column(name = "name", length = 64, unique = true, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Lob
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (this.getName() == null) {
			return false;
		} else if (o instanceof Site) {
			Site that = (Site) o;
			return this.getName().equals(that.getName());
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return this.getName() == null ? System.identityHashCode(this)
				: 17 * this.getName().hashCode();
	}

	@Override
	public String toString() {
		return "site with name: " + this.name;
	}


}
