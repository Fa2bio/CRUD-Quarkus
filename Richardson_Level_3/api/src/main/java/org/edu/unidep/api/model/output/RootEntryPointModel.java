package org.edu.unidep.api.model.output;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

public class RootEntryPointModel {

	private List<Link> links = new ArrayList<>();
	
	public void addLink(Link link) {
		this.links.add(link);
	}
	
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
}
