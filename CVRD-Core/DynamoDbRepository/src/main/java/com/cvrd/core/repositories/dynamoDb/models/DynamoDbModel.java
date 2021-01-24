package com.cvrd.core.repositories.dynamoDb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.cvrd.core.common.models.BaseDataModel;

@DynamoDBTable(tableName = "cvrd-table")
public class DynamoDbModel extends BaseDataModel {

    @DynamoDBHashKey(attributeName = "partitionKey")
    protected String partitionKey;

    @DynamoDBRangeKey(attributeName = "sortKey")
    protected String sortKey;

    @DynamoDBAttribute(attributeName = "data")
    protected String data;

    @DynamoDBVersionAttribute(attributeName = "version")
    protected Long version;

}
