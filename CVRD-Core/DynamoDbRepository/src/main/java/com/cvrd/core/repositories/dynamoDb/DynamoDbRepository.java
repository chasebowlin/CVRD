package com.cvrd.core.repositories.dynamoDb;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.cvrd.core.common.constants.AwsConstants;
import com.cvrd.core.common.models.BaseDataModel;
import com.cvrd.core.common.interfaces.database.IDatabaseInterface;

import java.util.List;
import java.util.Optional;


public class DynamoDbRepository implements IDatabaseInterface {

    private AmazonDynamoDB dynamoDb;
    private DynamoDBMapperConfig dynamoMapperConfig;
    private DynamoDBMapper dynamoDBMapper;
    private String environment;

    /**
     * Creates a Dynamo DB Repository using
     * keychain credentials / region
     */
    public DynamoDbRepository() {
        this.environment = Optional.ofNullable(System.getenv("profile")).orElse(AwsConstants.DEFAULT_ENVIRONMENT);
    }

    /**
     * Creates a Dynamo Db Repository using
     * predefined fields
     *
     * @param db
     * @param mapperConfig
     * @param mapper
     * @param env
     */
    public DynamoDbRepository(AmazonDynamoDB db, DynamoDBMapperConfig mapperConfig, DynamoDBMapper mapper, String env) {
        this.dynamoDb = db;
        this.dynamoMapperConfig = mapperConfig;
        this.dynamoDBMapper = mapper;
        this.environment = env;
    }

    /**
     * Sets the environment of the service
     *
     * @param env
     * @return
     */
    public IDatabaseInterface withEnvironment(String env) {
        this.environment = env;
        configureDynamoClient();

        return this;
    }

    /**
     * Sets up the dynamo db client with
     * table name prefix
     *
     */
    private void configureDynamoClient() {
        DynamoDBMapperConfig.TableNameOverride tableNameOverride = DynamoDBMapperConfig.TableNameOverride.withTableNamePrefix(
                AwsConstants.PRODUCT_ID + "-" + environment + "-");
        this.dynamoMapperConfig = DynamoDBMapperConfig.builder().withTableNameOverride(tableNameOverride).build();
        this.dynamoDb = AmazonDynamoDBClientBuilder.standard().build();
        this.dynamoDBMapper = new DynamoDBMapper(dynamoDb, dynamoMapperConfig);
    }

    /**
     * Creates a single item in DynamoDb table
     *
     * @param model the model being written
     * @return      string representing success/failure
     */
    @Override
    public String create(BaseDataModel model) {
        dynamoDBMapper.save(model);
        return AwsConstants.SUCCESS;
    }

    /**
     * Creates multiple items in DynamoDb table
     * from the passed in list
     *
     * @param data the models being written
     * @return     string representing success/failure
     */
    @Override
    public String create(List<BaseDataModel> data) {
        dynamoDBMapper.batchSave(data);
        return AwsConstants.SUCCESS;
    }


    /**
     * Gets item(s) from the DynamoDb table
     *
     * @param primaryKey
     * @param sortKey
     * @return              the items from the table
     */
    @Override
    public List<BaseDataModel> readFromTable(String primaryKey, String sortKey) {
        return dynamoDBMapper.batchLoad();
    }

    @Override
    public BaseDataModel remove(String primaryKey, String sortKey) {
        return null;
    }

    @Override
    public BaseDataModel update(BaseDataModel model) {
        return null;
    }
}
