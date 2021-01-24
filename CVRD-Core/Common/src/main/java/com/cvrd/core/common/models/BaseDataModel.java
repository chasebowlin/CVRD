package com.cvrd.core.common.models;

/**
 * This is the base class for all models in reference to
 * underlying data
 *
 */
public class BaseDataModel {
    protected String partitionKey;
    protected String sortKey;
    protected String data;

    public BaseDataModel() {

    }

    public void setPartitionKey(String partitionKey) {
        this.partitionKey = partitionKey;
    }

    public String getPartitionKey() {
        return partitionKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }

    public String getSortKey() {
        return sortKey;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
