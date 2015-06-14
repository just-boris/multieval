package com.catatron.probe.beans;

import java.util.List;

public class Browser {
    public String name;
    public String defaultVersion;
    public List<String> versions;

    public List<String> getVersions() {
        return versions;
    }

    public void setVersions(List<String> versions) {
        this.versions = versions;
    }

    public String getDefaultVersion() {
        return defaultVersion;
    }

    public void setDefaultVersion(String defaultVersion) {
        this.defaultVersion = defaultVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
