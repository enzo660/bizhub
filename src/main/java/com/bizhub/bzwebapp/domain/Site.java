package com.bizhub.bzwebapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "site")
public class Site extends IdentifiableEntity{

	private static final long serialVersionUID = -1947421036228366609L;

	private String name;

	private String description;
	
	private User user;
	
	private String city;
	
	private String state;
	
	private String address;
	
	private String content;

	@Size(max = 64)
	@NotEmpty
	@NotNull
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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Size(max = 64)
	@NotEmpty
	@NotNull
	@Column(name = "city", length = 64, nullable = false)
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

	@Size(max = 64)
	@NotEmpty
	@NotNull
	@Column(name = "state", length = 64, nullable = false)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Size(max = 64)
	@Column(name = "address", length = 128, unique = true)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Lob
	@Column(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}




}
